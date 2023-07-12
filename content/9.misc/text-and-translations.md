---
title: Text and Translations 
description: Comprehensive documentation for Minecraft's handling of formatted text and translations.
---

# Text and Translations 

Whenever Minecraft displays text ingame, it's probably defined using a `Text` object.
This custom type is used instead of a `String` to allow for more advanced formatting, 
including colors, boldness, obfuscation, and click events. They also allow easy access
to the translation system, making it simple to translate any interface elements into 
different languages.

If you've worked with datapacks or functions before, you may see parallels with the
json text format used for displayNames, books, and signs among other things. As you
can probably guess, this is just a json representation of a `Text` object, and can be
converted to and from using `Text.Serializer`.

When making a mod, it is generally preferred to construct your `Text` objects directly 
in code, making use of translations whenever possible.

## Text literals

The simplest way to create a `Text` object is to make a literal. This is just a string
that will be displayed as-is, by default without any formatting.

These are created using the `Text.of` or `Text.literal` methods, which both act slightly
differently. `Text.of` accepts nulls as input, and will return a `Text` instance. In 
contrast, `Text.literal` should not be given a null input, but returns a `MutableText`, 
this being a subclass of `Text` that can be easily styled and concatenated. More about
this later.

```java
Text literal = Text.of("Hello, world!");
MutableText mutable = Text.literal("Hello, world!");
// Keep in mind that a MutableText can be used as a Text, making this valid:
Text mutableAsText = mutable;
```

## Translatable Text

When you want to provide multiple translations for the same string of text, you can use the `Text.translatable` method to reference a translation key in any language file. If the key doesn't exist, the translation key is converted to a literal.

```java
Text translatable = Text.translatable("my_mod.text.hello");

// Similarly to literals, translatable text can be easily made mutable.
MutableText mutable = Text.translatable("my_mod.text.bye");
```

The language file, `en_us.json`, looks like the following:

```json
{
    "my_mod.text.hello": "Hello!",
    "my_mod.text.bye": "Goodbye :("
}
```

## Serializing Text

As mentioned before, you can serialize text to JSON using the `Text.Serializer` class:

```java
MutableText mutable = Text.translatable("my_mod.text.bye");
String jsonString = Text.Serializer.toJson(mutable);
```

This produces JSON that can be used datapacks, commands and other places that accept the JSON format of text instead of literal or translatable text.

## Deserializing Text

Furthermore, to deserialize a JSON text object into an actual `Text` class, you can use the `fromJson` method:

```java
String jsonString = Text.Serializer.toJson(mutable);

// Deserializing from JSON will always produce a mutable text object.
MutableText result = Text.Serializer.fromJson(jsonString);
```

## Formatting

You may be familiar with Minecraft's formatting standards:

<details>
  <summary>View Formatting Code Table</summary>
  
  | Color                        | Name                        | Chat Code | MOTD Code | Hex Code |
  | ---------------------------- | --------------------------- | --------- | --------- | -------- |
  | :color-swatch{color=#000000} | Black (black)               | §0        | \u00A70   | #000000  |
  | :color-swatch{color=#0000AA} | Dark Blue (dark_blue)       | §1        | \u00A71   | #0000AA  |
  | :color-swatch{color=#00AA00} | Dark Green (dark_green)     | §2        | \u00A72   | #00AA00  |
  | :color-swatch{color=#00AAAA} | Dark Aqua (dark_aqua)       | §3        | \u00A73   | #00AAAA  |
  | :color-swatch{color=#AA0000} | Dark Red (dark_red)         | §4        | \u00A74   | #AA0000  |
  | :color-swatch{color=#AA00AA} | Dark Purple (dark_purple)   | §5        | \u00A75   | #AA00AA  |
  | :color-swatch{color=#FFAA00} | Gold (gold)                 | §6        | \u00A76   | #FFAA00  |
  | :color-swatch{color=#AAAAAA} | Gray (gray)                 | §7        | \u00A77   | #AAAAAA  |
  | :color-swatch{color=#555555} | Dark Gray (dark_gray)       | §8        | \u00A78   | #555555  |
  | :color-swatch{color=#5555FF} | Blue (blue)                 | §9        | \u00A79   | #5555FF  |
  | :color-swatch{color=#55FF55} | Green (green)               | §a        | \u00A7a   | #55FF55  |
  | :color-swatch{color=#55FFFF} | Aqua (aqua)                 | §b        | \u00A7b   | #55FFFF  |
  | :color-swatch{color=#FF5555} | Red (red)                   | §c        | \u00A7c   | #FF5555  |
  | :color-swatch{color=#FF55FF} | Light Purple (light_purple) | §d        | \u00A7d   | #FF55FF  |
  | :color-swatch{color=#FFFF55} | Yellow (yellow)             | §e        | \u00A7e   | #FFFF55  |
  | :color-swatch{color=#FFFFFF} | White (white)               | §f        | \u00A7f   | #FFFFFF  |
  |                              | Reset                       | §r        |           |          |
  |                              | **Bold**                    | §l        |           |          |
  |                              | ~~Strikethrough~~           | §m        |           |          |
  |                              | <u>Underline</u>            | §n        |           |          |
  |                              | *Italic*                    | §o        |           |          |
  |                              | Obfuscated                  | §k        |           |          |
  
</details>

You can apply these formattings using the `Formatting` enum on the `MutableText` class:

```java
MutableText result = Text.literal("Hello World!").formatted(Formatting.AQUA, Formatting.BOLD, Formatting.UNDERLINE);
```