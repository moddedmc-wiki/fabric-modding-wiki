---
title: Food
description: Learn how to add a FoodComponent to an item to make it edible, and configure it.
prev:
  text: "Creating Your First Item"
  link: "/items/index"
next:
  text: "Tools and Weapons"
  link: "/items/tools"
---

# Food Items

Food is a core aspect of survival Minecraft, so when creating edible items you have to consider the food's usage with other edible items.

Unless you're making a mod with overpowered items, you should consider:

- How much hunger your edible item adds or removes.
- What potion effect(s) does it grant?
- Is it early-game or endgame accessible?

## Adding the food component.

To add a food component to an item, we can pass it to the `Item.Settings` instance:

```java
new Item.Settings().food(new FoodComponent.Builder().build())
```

Right now, this just makes the item edible and nothing more.

The `FoodComponent.Builder` class has many methods that allow you to modify what happens when a player eats your item:

| Method               | Description                                                    |
| -------------------- | -------------------------------------------------------------- |
| `hunger`             | Sets the amount of hunger points your item will replenish.     |
| `saturationModifier` | Sets the amount of saturation points your item will add.       |
| `meat`               | Declares your item as meat. Carnivourous entities will eat it. |
| `alwaysEdible`       | Allows your item to be eaten regardless of hunger level.       |
| `snack`              | Declares your item as a snack.                                 |
| `statusEffect`       | Adds a status effect when you eat your item.                   |

When you've modified the builder to your likings, you can call the `build()` method to get the `FoodComponent`

For the example created in the previous page, I'll be using the following options for the builder:

```java
new FoodComponent.Builder()
    .alwaysEdible()
    .snack()
    // The duration is in ticks, 20 ticks = 1 second
    .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 6*20, 1), 1.0f)
    .build();
```

This makes the item:

- Always edible, it can be eaten regardless of hunger level.
- A snack.
- Always give Poision II for 6 seconds when eaten.

![](./_assets/food_0.webp)