package dev.mineblock11.fabric.referencemod;

import dev.mineblock11.fabric.referencemod.block.PrismarineLampBlock;
import dev.mineblock11.fabric.referencemod.block.TestBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block CONDENSED_DIRT = register(
            new Block(
                    AbstractBlock.Settings.create().sounds(BlockSoundGroup.GRASS)
            ), "condensed_dirt", true);

    public static final Block TEST_BLOCK = register(
            new TestBlock(AbstractBlock.Settings.create()), "test_block", true
    );

    public static final PillarBlock CONDENSED_OAK_LOG = register(
            new PillarBlock(
                    AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD)
            ), "condensed_oak_log", true);

    public static final PrismarineLampBlock PRISMARINE_LAMP = register(
            new PrismarineLampBlock(), "prismarine_lamp", true
    );

    public static <T extends Block> T register(T block, String name, boolean registerItem) {
        Identifier id = new Identifier(MyMod.MOD_ID, name);

        if (registerItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {

    }
}
