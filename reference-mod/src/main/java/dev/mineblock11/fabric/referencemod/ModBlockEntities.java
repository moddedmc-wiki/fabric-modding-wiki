package dev.mineblock11.fabric.referencemod;

import dev.mineblock11.fabric.referencemod.block.TestBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<TestBlockEntity> TEST_BLOCK_ENTITY_TYPE;

    public static void registerBlockEntityTypes() {
        TEST_BLOCK_ENTITY_TYPE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE, new Identifier(MyMod.MOD_ID, "test_block_entity"),
                FabricBlockEntityTypeBuilder.create(TestBlockEntity::new, ModBlocks.TEST_BLOCK).build()
        );
        // add more BlockEntityTypes here, if needed
    }
}
