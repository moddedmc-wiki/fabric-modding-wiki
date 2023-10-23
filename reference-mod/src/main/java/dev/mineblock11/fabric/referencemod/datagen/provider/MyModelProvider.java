package dev.mineblock11.fabric.referencemod.datagen.provider;

import dev.mineblock11.fabric.referencemod.MyMod;
import dev.mineblock11.fabric.referencemod.block.ModBlocks;
import dev.mineblock11.fabric.referencemod.util.MyModProperties;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class MyModelProvider extends FabricModelProvider {
    public MyModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.blockStateCollector
                .accept(VariantsBlockStateSupplier.create(ModBlocks.ADVANCED_TEST_BLOCK)
                        .coordinate(horizontalFacingMap())
                        .coordinate(arrowsAndBoxesMap())
                );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModBlocks.ADVANCED_TEST_BLOCK.asItem(), Blocks.BARRIER.asItem(), Models.GENERATED);
    }

    /**
     * Creates BlocksState entries for cardinal directions
     *
     * @return Map of Rotation axis and the rotation values
     */
    private BlockStateVariantMap horizontalFacingMap() {
        return BlockStateVariantMap.create(Properties.HORIZONTAL_FACING).register(direction -> switch (direction) {
            case EAST -> BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90);
            case SOUTH -> BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180);
            case WEST -> BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270);
            default -> BlockStateVariant.create();
        });
    }

    /**
     * Creates BlocksState entries. It considers the custom Arrows and Boxes BlockState Properties at the same time.
     *
     * @return Map of Model and the Identifier of the model
     */
    private BlockStateVariantMap arrowsAndBoxesMap() {
        return BlockStateVariantMap.create(MyModProperties.ARROWS, MyModProperties.HITBOX_SIZE)
                .register((arrows, boxes) -> switch (boxes) {
                    case SMALL -> BlockStateVariant.create().put(VariantSettings.MODEL,
                            new Identifier(MyMod.MOD_ID, String.format("block/advanced_test_block_a%sb1", arrows)));
                    case BIG -> BlockStateVariant.create().put(VariantSettings.MODEL,
                            new Identifier(MyMod.MOD_ID, String.format("block/advanced_test_block_a%sb2", arrows)));
                    case FULL -> BlockStateVariant.create().put(VariantSettings.MODEL,
                            new Identifier(MyMod.MOD_ID, String.format("block/advanced_test_block_a%sb3", arrows)));
                });
    }
}
