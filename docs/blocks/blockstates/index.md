---
title: Block States
description: Learn why blockstates are a great way to add visual functionality to your blocks.
authors:
    - "mineblock11"
permalink: /blocks/blockstates
layout: default
page_nav:
    prev:
        content: Creating Your First Block
        url: /blocks/first-block
    next:
        content: Custom Blocks
        url: /blocks/custom-blocks
---

## What are block states?

A block state is a piece of data attached to a singular block in the Minecraft world containing information on the block - some examples of information vanilla stores in block states:

- Rotation: Mostly used for logs and other natural blocks.
- Activated: Heavily used in redstone devices, and blocks such as the furnace or smoker.
- Age: Used in crops, plants, saplings, kelp etc.
  
You can probably see why they are useful - they avoid the need to store NBT data in a block entity - reducing the world size, and preventing TPS issues!

Blockstate definitions are found in the `assets/<mod id here>/blockstates` folder.

## Example: Pillar Block

Minecraft has some custom classes already that allow you quickly create certain types of blocks - this example goes through the creation of a block with the `axis` property by creating a "Condensed Oak Log" block.

The vanilla `PillarBlock` class allows the block to be placed in the X, Y or Z axis.

```java
public static final PillarBlock CONDENSED_OAK_LOG = register(
    new PillarBlock(
        AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD)
    ), "condensed_oak_log", true
);
```

Pillar blocks have two textures, top and side - they use the `block/cube_column` model.

As always, with all block textures, the texture files can be found in `assets/<mod id here>/textures/block`

![](/docs/blocks/blockstates/index_0_large.png)

<div align="center">
    <a href="/docs/blocks/blockstates/condensed_oak_log_textures.zip" target="_blank">Download Example Textures</a>
</div>
<br>

Since the pillar block has two positons, horizontal and vertical, we'll need to make two seperate model files:

- `condensed_oak_log_horizontal.json` which extends the `block/cube_column_horizontal` model.
- `condensed_oak_log.json` which extends the `block/cube_column` model.

```json
{
    "parent": "block/cube_column",
    "textures": {
        "end": "mod_id:block/condensed_oak_log_top",
        "side": "mod_id:block/condensed_oak_log"
    }
}
```

The blockstate file is where the magic happens - pillar blocks have three axis, so we'll use these models accordingly.

```jsonc
{
  "variants": {
    "axis=x": {
      "model": "mod_id:block/condensed_oak_log_horizontal",
      // We'll rotate the model so that it faces towards the positive-x direction
      "x": 90,
      "y": 90
    },
    "axis=y": {
      "model": "mod_id:block/condensed_oak_log"
    },
    "axis=z": {
      "model": "mod_id:block/condensed_oak_log_horizontal",
      // Rotate the model so it faces towards the positive-x direction.
      "x": 90
    }
  }
}
```

As always, you'll need to create a translation for your block, and an item model which parents either of the two models.

![](/docs/blocks/blockstates/index_1.png)

<div align="center">
    <small>Sorta looks like spruce...</small>
</div>


## Custom Block States

<div class="callout callout--danger">
    <p><strong>Note</strong>This section assumes you have completed the <a href="/blocks/custom-blocks">Custom Blocks</a> guide.</p>
</div>

