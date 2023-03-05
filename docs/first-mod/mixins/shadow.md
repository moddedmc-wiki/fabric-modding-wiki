---
title: Using existing fields and methods
description: Accessing fields and methods from your target class is possible in two ways, casting and the @Shadow annotation.
layout: default
permalink: /introduction/shadow
authors:
    - "friendly-banana"
page_nav:
  prev:
      content: Mixins
      url: /introduction/mixins
  next:
      content: Access Wideners
      url: /introduction/access-wideners
---

## General

You often need to use fields and methods from your target class. This is possible in two ways:

* [The @Shadow annotation](#the-shadow-annotation)
* [Casting this](#casting-this)

`@Shadow` is preferable as it allows for compile-time type-safety and doesn't need casts.

For the examples we will use a property of the `MinecraftServer` class:

```java
public class MinecraftServer {
    public final long[] tickTimes;
}
```

## The @Shadow annotation

The `@Shadow` annotation allows you to use fields and methods from your target class in the mixin. You simply declare the field in your
mixin class. `final` becomes `@Final` so you don't have to initialize the field, which would cause errors as the target class also tries to
initialize the field.

### Example

```java
@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Shadow
    @Final
    public long[] tickTimes;
}
```

The same goes for all methods from your target class. The Minecraft Development plugin provides autocompletion which will create the
shadowed field/method for you.

## Casting this

Your mixin class gets merged into the original class, so at runtime `this` will have the type of your target. At compile-time though the
 type of `this` is your mixin class. To prevent compiler errors you can cast `this` into your target.

### Example

```java
((MinecraftServer) (Object) this).tickTimes
```