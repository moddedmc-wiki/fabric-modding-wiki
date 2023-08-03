package dev.mineblock11.fabric.referencemod.datagen;

import dev.mineblock11.fabric.referencemod.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class MyBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public static final TagKey<Block> AWESOME_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier("referencemod:awesome_blocks"));

    public MyBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(AWESOME_BLOCKS)
                .add(Blocks.DIAMOND_BLOCK, ModBlocks.CONDENSED_OAK_LOG)
                .addOptionalTag(BlockTags.DIRT)
                // An optional tag value does not cause the tag to fail when loading if the
                // ID in question doesn't exist in the registry.
                .addOptional(new Identifier("create:cogwheel"))
                // Add an existing tag to the tags, whether it's valid or not.
                .forceAddTag(BlockTags.BUTTONS);
    }


}
