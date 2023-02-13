---
title: Introduction to Modding
description: A general rundown on what modding is - and how Fabric allows you to achieve it.
layout: default
permalink: /introduction/welcome
page_nav:
    prev:
        content: Home
        url: /introduction/
    next:
        content: Creating Your First Project
        url: /introduction/setup
---

Before you can even start creating your mod - you have to understand a few concepts that may be mentioned quite a lot throughout the pages of this wiki.

These concepts will be extended upon in their individual pages.

Obviously, these pages expect you have knowledge of basic java - [please have a read on the home page if you don't.](/#maximising-your-use-of-this-wiki)

## What is Fabric?

Fabric is a toolchain consisting of:

- Fabric Loader

  Manages the injection of modded code into java-based games.
  Fabric Loader isn't just for Minecraft! It can be applied to other games such as Mindustry.

- Fabric API
  
  Fabric API is a set of events and utility methods that can aid modders in creating content.

- Fabric Loom

  Fabric Loom manages the development environment by merging the client and server jars together and applying mappings, such as yarn.

- Fabric Installer

  Fabric Installer simply installs the Fabric Loader for users to use.

## Identifiers

Identifiers are used to uniquely identify content in Minecraft - for example, a grass block has the identifier `minecraft:grass_block` and the forest biome has the identifier `minecraft:forest`.

Identifiers consist of a namespace and path.

<div align="center">
<img src="/docs/first-mod/introduction-to-modding/index_0.svg" class="mermaid"/>
</div>

## Registries

Nearly all the content in Minecraft, from biomes to items, are stored in registries. These registries are writable to during the game startup and then become frozen and uneditable.

When you want to add an object to a registry, you use the `Registry.register(...)` method which takes in the following:

- The registry you want to register the object to, all base-game registries can be found in the `Registries` class.
- The identifier of the item.

The general rule of thumb is: If you want to add something, you will more than likely have to register it.

## Environment

Minecraft has a split jar - a server jar and client jar. However, Fabric Loom merges these two jars together and adds annotations stating if code only exists on the client or server:

```java
// This method only exists on the server.
@Environment(EnvType.SERVER)
public void hello() {
    System.out.println("Hi!")
}

// This method only exists on the client.
@Environment(EnvType.CLIENT)
public void bye() {
    System.out.println("Bye!")
}

// This method exists on both because it isn't annotated.
public void yolo() {
    System.out.println("YOLO!")
}
```

## Mixins

Mixins are a way to manipulate code in classes through annotations.

More information can be found at [SpongePowered's Mixin Wiki](https://github.com/SpongePowered/Mixin/wiki/Introduction-to-Mixins---Understanding-Mixin-Architecture)

### Mixin Example 

Let's say that I want to inject into `Greetings#sayHello()` to say "Bye!" at the end of the method.

```java
@Mixin(Greetings.class)
public static class GreetingsMixin {
    @Inject(method = "sayHello", at = @At("TAIL"), cancelable = false)
    public void $greetingsMixin_sayHello_tail() {
        System.out.println("Bye!");
    }
}
```

##### Before Mixin Application

```java
public void sayHello() {
    System.out.println("Hello!");
}
```

##### After Mixin Application

```java
public void sayHello() {
    System.out.println("Hello!");

    // Injected by GreetingsMixin
    System.out.println("Bye!");
}
```