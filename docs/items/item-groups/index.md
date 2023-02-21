---
layout: default
title: Custom Item Groups
description: Now that you've added many items - they should probably be put together.
permalink: /items/item-groups
authors:
    - "mineblock11"
page_nav:
    prev:
        content: Armor
        url: /items/armor
    next:
        content: Interactive Items
        url: /items/interactive-items
---

In the last few pages - you've added quite a few items, it's time to consider making your own item group.

## Creating the item group.

It's surprisingly easy to create an item group. Simply create a new static final field in your items class to store the item group.

I'll be using the "Guidite Sword" from the previous tools tutorial as the icon for the group.

```java
public static final ItemGroup MY_MOD_ITEMGROUP = FabricItemGroup.builder(new Identifier("my_mod", "item_group"))
	.icon(() -> new ItemStack(ModItems.GUIDITE_SWORD))
	.build();
```

## Adding items.

To add items to the group, you can use the modify item group event similarly to how you added your items to the vanilla item groups:

```java
ItemGroupEvents.modifyEntriesEvent(MY_MOD_ITEMGROUP).register(itemGroup -> {
    itemGroup.add(ModItems.POOP);
    itemGroup.add(ModItems.GUIDITE_SWORD);
    // .. other items you've made
});
```

<hr />

You should see the item group is now in the creative inventory menu. However, it is untranslated - you must add a translation key to your translations file - similarly to how you translated your first item.

![](/docs/items/item-groups/index_0.png)

## Adding a translation key.

The translation key is shown when a translation does not exist, in this case - the translation key is: `itemGroup.my_mod.item_group`

```json
{
    "itemGroup.my_mod.item_group": "Fabric Community Wiki Items"
}
```

Now, as you can see, the item group should be correctly named:

![](/docs/items/item-groups/index_1.png)

## Next Steps

- Why dont you try to make two item groups? One for tools and one specifically for other items?
- Try change the icon to a vanilla item. *Vanilla items can be found in the `Items` class*
- Figure out a way to change the order of the items.