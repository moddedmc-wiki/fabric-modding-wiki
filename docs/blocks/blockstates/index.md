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

## Custom Block States

<div class="callout callout--danger">
    <p><strong>Note</strong>This part assumes you have completed the <a href="/blocks/custom-blocks">Custom Blocks</a> guide.</p>
</div>

