package dev.mineblock11.fabric.referencemod.util;

import dev.mineblock11.fabric.referencemod.block.custom.AdvancedTestBlock;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;

public class MyModProperties {
    public static final IntProperty ARROWS;
    public static final EnumProperty<AdvancedTestBlock.HitBoxVariant> HITBOX_SIZE;

    static {
        ARROWS = IntProperty.of("arrows", 0, AdvancedTestBlock.MAX_ARROWS_COUNT);
        HITBOX_SIZE = EnumProperty.of("hitbox_size", AdvancedTestBlock.HitBoxVariant.class);
    }
}
