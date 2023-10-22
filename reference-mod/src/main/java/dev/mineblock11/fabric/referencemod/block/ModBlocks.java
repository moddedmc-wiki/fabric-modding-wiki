package dev.mineblock11.fabric.referencemod.block;

import dev.mineblock11.fabric.referencemod.MyMod;
import dev.mineblock11.fabric.referencemod.block.custom.AdvancedTestBlock;
import dev.mineblock11.fabric.referencemod.block.custom.PrismarineLampBlock;
import dev.mineblock11.fabric.referencemod.block.custom.TestBlock;
import dev.mineblock11.fabric.referencemod.util.helper.LoggerUtil;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block CONDENSED_DIRT = register("condensed_dirt", true,
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.GRASS)));

    public static final PillarBlock CONDENSED_OAK_LOG = register("condensed_oak_log", true,
            new PillarBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD)));

    public static final PrismarineLampBlock PRISMARINE_LAMP = register("prismarine_lamp", true,
            new PrismarineLampBlock());

    public static final Block TEST_BLOCK = register("test_block", true,
            new TestBlock(AbstractBlock.Settings.create()));

    public static final Block ADVANCED_TEST_BLOCK = register("advanced_test_block", true,
            new AdvancedTestBlock(AbstractBlock.Settings.create().nonOpaque()));


    public static <T extends Block> T register( String name, boolean registerItem, T block) {
        Identifier id = new Identifier(MyMod.MOD_ID, name);

        if (registerItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {
        LoggerUtil.devLogger("Initializing Blocks");
    }

    public static void initializeClientRendering() {
        BlockRenderLayerMap.INSTANCE.putBlock(ADVANCED_TEST_BLOCK, RenderLayer.getCutout());
    }
}
