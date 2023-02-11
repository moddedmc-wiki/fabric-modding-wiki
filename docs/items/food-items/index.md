---
layout: default
title: Edible Items
description: Learn how to add a <code>FoodComponent</code> to an item to make it edible, and configure it.
permalink: /items/food
page_nav:
    prev:
        content: Creating Your First Item
        url: /items/
    next:
        content: Tools
        url: /items/tools
---

Food! Yum. Food is a core aspect of survival Minecraft, so when creating edible items you have to consider the food's usage with other edible items.

Unless you're making a mod with overpowered items, you should consider:

- How much hunger your edible item adds or removes.
- What potion effect(s) does it grant?
- Is it early-game or endgame accessible?

With those points in mind, we can start - I'll be building off of the "Poop" item created in the previous page - yuck!

## Adding the food component.

To add a food component to an item, we can pass it to the `Item.Settings` class:

