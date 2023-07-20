package dev.mineblock11.fabric.referencemod.tools;

import dev.mineblock11.fabric.referencemod.ModItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class GuiditeMaterial implements ToolMaterial {

    public static final GuiditeMaterial INSTANCE = new GuiditeMaterial();

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
