package mine.block.mymod.armor;

import mine.block.mymod.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class GuiditeArmorMaterial implements ArmorMaterial {
    public static final GuiditeArmorMaterial INSTANCE = new GuiditeArmorMaterial();


    // Base durability values for all the slots.
    // Boots, Leggings, Chestplate, Helmet
    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};

    // Protection values for all the slots.
    // For reference, diamond uses 3 for boots, 6 for leggings, 8 for chestplate
    // and 3 for helmet.
    private static final int PROTECTION_BOOTS = 2;
    private static final int PROTECTION_LEGGINGS = 5;
    private static final int PROTECTION_CHESTPLATE = 6;
    private static final int PROTECTION_HELMET = 1;

    // Storing the protection and durability values in an array allows
    // you to quickly get them by slot ID.
    private static final int[] PROTECTION_VALUES = new int[] {
        PROTECTION_BOOTS,
        PROTECTION_LEGGINGS,
        PROTECTION_CHESTPLATE,
        PROTECTION_HELMET
    };

    @Override
    public int getDurability(EquipmentSlot slot) {
        // Replace X with a multiplier that you see fit!
        // For reference, diamond uses a multiplier of 33, whilst
        // leather uses 11.
        return BASE_DURABILITY[slot.getEntitySlotId()] * 22;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        // This will get the protection value for the slot from
        // our array.
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 5;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.POOP);
    }

    @Override
    public String getName() {
        return "guidite";
    }

    @Override
    public float getToughness() {
        return 2.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}