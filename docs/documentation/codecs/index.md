---
title: Codecs 
description: An advanced guide to using Mojang's Codec system for serializing and deserializing java objects. 
layout: default 
permalink: /documentation/codecs 
page_nav:
  prev:
    content: Home 
    url: /documentation
---

Codec is a system for easily and functionally serializing java objects, and is included in Mojang's DataFixerUpper (DFU)
library. In a modding context they are usually used as an alternative to GSON and Jankson when reading and writing
custom json files, but they tend to be useful in many other situations as well.

They are used in conjunction with another API from DFU, `DynamicOps`. A codec defines the structure of an object, while
dynamic ops are used to define a format to be serialized to and from, such as json or NBT. This means any codec can be
used with any dynamic ops, and vice versa, allowing for great flexibility.

## Using Codecs

### Serializing and Deserializing

The basic usage of a codec is to serialize and deserialize objects to and from a specific format.

Since a few vanilla classes already have codecs defined, we can use those as an example. Mojang has also provided us
with two dynamic ops classes by default, `JsonOps` and `NbtOps`, which tend to cover most use cases.

Now, let's say we want to serialize a `BlockPos` to json and back. We can do this using the codec statically stored
at `BlockPos.CODEC` with the `Codec#encodeStart` and `Codec#parse` methods, respectively.

```java
BlockPos pos = new BlockPos(1, 2, 3);

// Serialize the BlockPos to a JsonElement
DataResult<JsonElement> result = BlockPos.CODEC.encodeStart(JsonOps.INSTANCE, pos);
```

When using a codec, values are returned in the form of a `DataResult`. This is a wrapper that can represent either a
success or a failure. We can use this in several ways: If we just want our serialized value, `DataResult#result` will
simply return an `Optional` containing our value, while `DataResult#resultOrPartial` also lets us supply a function to
handle any errors that may have occurred. The latter is particularly useful for custom datapack resources, where we'd
want to log errors without causing issues elsewhere.

So let's grab our serialized value and turn it back into a `BlockPos`:

```java
// When actually writing a mod, you'll want to properly handle empty Optionals of course
JsonElement json = result.resultOrPartial(LOGGER::error).orElseThrow();

// Here we have our json value, which should correspond to `[1, 2, 3]`, 
// as that's the format used by the BlockPos codec.
LOGGER.info("Serialized BlockPos: {}", json);

// Now we'll deserialize the JsonElement back into a BlockPos
DataResult<BlockPos> result = BlockPos.CODEC.parse(JsonOps.INSTANCE, json);

// Again, we'll just grab our value from the result
BlockPos pos = result.resultOrPartial(LOGGER::error).orElseThrow();

// And we can see that we've successfully serialized and deserialized our BlockPos!
LOGGER.info("Deserialized BlockPos: {}", pos);
```

### Built-in Codecs

As mentioned earlier, Mojang has already defined codecs for several vanilla and standard Java classes, including but not
limited to `BlockPos`, `BlockState`, `ItemStack`, `Identifier`, `Text`, and regex `Pattern`s. Codecs for Mojang's own
classes are usually found as static fields named `CODEC` on the class itself, while most others are kept in the `Codecs`
class. It should also be noted that all vanilla registries directly implement the `Codec` interface, for example, you
can use `Registries.BLOCK` as a `Codec<Block>`.

The codec api itself also contains some codecs for primitive types, such as `Codec.INT` and `Codec.STRING`. These are
available as statics on the `Codec` class, and are usually used as the base for more complex codecs, as explained below.

## Building Codecs

Now that we've seen how to use codecs, let's take a look at how we can build our own. Suppose we have the following
class, and we want to deserialize instances of it from json files:

```java
public class CoolBeansClass {
    
    private final int beansAmount;
    private final Item beanType;
    private final List<BlockPos> beanPositions;

    public CoolBeansClass(int beansAmount, Item beanType, List<BlockPos> beanPositions) {...}

    public int getBeansAmount() { return this.beansAmount; }
    public Item getBeanType() { return this.beanType; }
    public List<BlockPos> getBeanPositions() { return this.beanPositions; }
}
```

The corresponding json file might look something like this:

```json
{
  "beans_amount": 5,
  "bean_type": "beanmod:mythical_beans",
  "bean_positions": [
    [1, 2, 3],
    [4, 5, 6]
  ]
}
```

We can make a codec for this class by putting together multiple smaller codecs into a larger one. In this case, we'll
need one for each field:

- a `Codec<Integer>`
- a `Codec<Item>`
- a `Codec<List<BlockPos>>`

We can get the first one from the aforementioned primitive codecs in the `Codec` class, specifically `Codec.INT`. While
the second one can be obtained from the `Registries.ITEM` registry, which directly implements `Codec<Item>`. We don't
have a default codec for `List<BlockPos>`, but we can make one from `BlockPos.CODEC`.

### Lists

`Codec#listOf` can be used to create a list version of any codec:

```java
Codec<List<BlockPos>> listCodec = BlockPos.CODEC.listOf();
```

It should be noted that codecs created in this way will always deserialize to an `ImmutableList`. If you need a mutable
list instead, you can make use of [xmap](#functionally-equivalent-types-and-xmap) to convert to one during
deserialization.

### Merging Codecs for Record-like Classes

Now that we have separate codecs for each field, we can combine them into one codec for our class using
a `RecordCodecBuilder`. This assumes that our class has a constructor containing every field we want to serialize, and
that every field has a corresponding getter method. This makes it perfect to use in conjunction with records, but it can
also be used with regular classes.

Let's take a look at how to create a codec for our `CoolBeansClass`:

```java
public static final Codec<CoolBeansClass> CODEC = RecordCodecBuilder.create(instance -> instance.group(
    Codec.INT.fieldOf("beans_amount").forGetter(CoolBeansClass::getBeansAmount),
    Registries.ITEM.fieldOf("bean_type").forGetter(CoolBeansClass::getBeanType),
    BlockPos.CODEC.listOf().fieldOf("bean_positions").forGetter(CoolBeansClass::getBeanPositions)
    // Up to 16 fields can be declared here
).apply(instance, CoolBeansClass::new));
```

Each line in the group specifies a codec, a field name, and a getter method. The `Codec#fieldOf` call is used to convert
the codec into a [map codec](#mapcodec-not-to-be-confused-with-codecmap), and the `forGetter` call specifies the getter 
method used to retrieve the value of the field from an instance of the class. Meanwhile, the `apply` call specifies the 
constructor used to create new instances. Note that the order of the fields in the group should be the same as the order 
of the arguments in the constructor.

You can also use `Codec#optionalFieldOf` in this context to make a field optional, as explained in
the [Optional Fields](#optional-fields) section.

### MapCodec, not to be confused with Codec&lt;Map&gt;

Calling `Codec#fieldOf` will convert a `Codec<T>` into a `MapCodec<T>`, which is a variant, but not direct
implementation of `Codec<T>`. It essentially boxes the value of the source codec inside a map, with the given field name
as the key. For example, a `Codec<BlockPos>` when serialized into json would look like this:

```json
[1, 2, 3]
```

But when converted into a `MapCodec<BlockPos>` using `BlockPos.CODEC.fieldOf("pos")`, it would look like this:

```json
{
  "pos": [1, 2, 3]
}
```

While the main use for map codecs is to be merged with other map codecs to construct a codec for a full class worth of
fields, as explained in the [Merging Codecs for Record-like Classes](#merging-codecs-for-record-like-classes) section
above, they can also be turned back into regular codecs using `MapCodec#codec`, which will retain the same behavior of
boxing their input value.

#### Optional Fields

`Codec#optionalFieldOf` can be used to create an optional map codec. This will, when the specified field is not present
in the container during deserialization, either be deserialized as an empty `Optional` or a specified default value.

```java
// Without a default value
MapCodec<Optional<BlockPos>> optionalCodec = BlockPos.CODEC.optionalFieldOf("pos");

// With a default value
MapCodec<BlockPos> optionalCodec = BlockPos.CODEC.optionalFieldOf("pos", BlockPos.ORIGIN);
```

Do note that optional fields will silently ignore any errors that may occur during deserialization. This means that if
the field is present, but the value is invalid, the field will always be deserialized as the default value.

### Unit

`Codec#unit` can be used to create a codec that always deserializes to a constant value, regardless of the input. When
serializing, it will do nothing.

```java
Codec<Integer> theMeaningOfCodec = Codec.unit(42);
```

### Functionally Equivalent Types and xmap

// TODO

## References

- A much more comprehensive documentation of codecs and related APIs can be found at the
  [Unofficial DFU JavaDoc](https://kvverti.github.io/Documented-DataFixerUpper/snapshot/com/mojang/serialization/Codec.html)
- The general structure of this guide was heavily inspired by the
  [Forge Community Wiki's page on codecs](https://forge.gemwire.uk/wiki/Codecs), a more Forge-specific take on the same
  topic.
