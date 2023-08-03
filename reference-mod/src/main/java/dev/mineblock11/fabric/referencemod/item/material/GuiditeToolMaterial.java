package dev.mineblock11.fabric.referencemod.item.material;

import dev.mineblock11.fabric.referencemod.item.ModItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class GuiditeToolMaterial implements ToolMaterial {

    public static final GuiditeToolMaterial INSTANCE = new GuiditeToolMaterial();

    @Override
    public int getDurability() {
        return 455;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 5.0F;
    }

    @Override
    public float getAttackDamage() {
        return 1.5F;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 22;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.POOP);
    }
}
