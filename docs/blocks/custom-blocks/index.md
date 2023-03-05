---
title: Custom Blocks
description: This page will guide you through the creation of a custom block class - and why and when you should consider using a custom block class.
authors:
    - "mineblock11"
permalink: /blocks/custom-blocks
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

Custom classes are required for custom block states and the usage of block entities, handled screens and inventories.

<!-- advanced composter -->