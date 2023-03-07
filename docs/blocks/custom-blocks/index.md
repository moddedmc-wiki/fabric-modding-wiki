<!-- ---
title: Custom Blocks
description: This page will guide you through the creation of a custom block class - and why and when you should consider using a custom block class.
authors:
    - "mineblock11"
permalink: /wip/blocks/custom-blocks
layout: default
page_nav:
    prev:
        content: Block States
        url: /blocks/blockstates
    next:
        content: Block Entities
        url: /blocks/block-entities
---

When you need to create functionality that isn't already provided by vanilla block classes (such as `PillarBlock` or `CropBlock`) you will have to create a custom class that extends `Block`

Custom classes are required for custom block states and the usage of block entities, handled screens and inventories. They are also required if your block has an unusual shape, eg: a slab-like shape.

As an example, we will create the "Advanced Composter" block - which will be expanded upon in the next few pages.

Currently, to keep it simple, this page will implement the following features for the composter:

- When a player right clicks with a compostable item, the block gives the player one bonemeal.
- The 

## Creating the Custom Block class.

You'll need to create a class which extends `Block` and create a constructor.

```java
public class AdvancedComposter extends Block {

}
``` -->