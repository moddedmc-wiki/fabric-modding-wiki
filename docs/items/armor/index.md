---
layout: default
title: Armor
description: Learn how to create your own armor sets.
permalink: /items/armor
page_nav:
    prev:
        content: Tools
        url: /items/tools
    next:
        content: Custom Item Groups
        url: /items/item-groups
---

<!-- Similarly to tools, armor items require a material, this time though - they use `ArmorMaterial`. -->

Armor provides the player with increased defense against attacks from mobs and other players.

## Creating an armor material.

All armor items - like tools - have an armor material.

The armor material tells the game what protection and durability the armor item should have depending on the slot.

You'll need to create a class that inherits `ArmorMaterial` - and add the following fields.

```java
public class GuideArmorMaterial extends ArmorMaterial {
    // Base durability values for all the slots.
    // Boots, Leggings, Chestplate, Helmet
    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};

    // Protection values for all the slots.
    // For reference, diamond uses 3 for boots, 6 for leggings, 8 for chestplate
    // and 3 for helmet.
    private static final int PROTECTION_BOOTS = ...;
    private static final int PROTECTION_LEGGINGS = ...;
    private static final int PROTECTION_CHESTPLATE = ...;
    private static final int PROTECTION_HELMET = ...;

    // Storing the protection and durability values in an array allows
    // you to quickly get them by slot ID.
    private static final int[] PROTECTION_VALUES = new int[] {
        PROTECTION_BOOTS,
        PROTECTION_LEGGINGS,
        PROTECTION_CHESTPLATE,
        PROTECTION_HELMET
    }
}
```

The following methods will have to be implemented as well - these methods tell the game vital information on your armor items.

- #### Durability - `getDurability(EquipmentSlot slot)`
  
  Returns the durability of an armor item that is in a specified slot - in hit points.

  The hit points specify the amount of hits the armor item can take before breaking.

  **Example**

  ```java
    @Override
    public int getDurability(EquipmentSlot slot) {
        // Replace X with a multiplier that you see fit!
        // For reference, diamond uses a multiplier of 33, whilst
        // leather uses 11.
        return BASE_DURABILITY[slot.getEntitySlotId()] * X;
    }
  ```

- #### Protection - `getProtectionAmount(EquipmentSlot slot)`
  
  Returns the protection value for an armor item in a slot.

  Usually this is always the same, regardless of your armor material.

  **Example**

  ```java
    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        // This will get the protection value for the slot from
        // our array.
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }
  ```
- #### Enchantability - `getEnchantability()`

  How easy is it to get better and higher level enchantments with this item?

  **Example**

  ```java
	@Override
	public int getEnchantability() {
		return 5;
	}
  ```

- #### Equip Sound - `getEquipsound()`
  
  What sound should be played when the armor is equipped?

  **Example**

  ```java
    @Override
	public SoundEvent getEquipSound() {
        // Example for Iron Armor
		return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
	}
  ```

- #### Repair Ingredient - `getRepairIngredient()`
  
  What item or items can be used in an anvil to repair the armor items?

  **Example**

  ```java
  	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(Items.IRON_INGOT, Items.DIRT, ...);
	}
  ```

- #### Name - `getName()`
  
  The name of the armor material - must be lowercase.

  ```java
  	@Override
	public String getName() {
		return "iron";
	}
  ```

- #### Toughness - `getToughness()`
  
  How much protection should be given for high-damage attacks?

  