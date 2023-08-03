package dev.mineblock11.fabric.referencemod.datagen;

import dev.mineblock11.fabric.referencemod.block.ModBlocks;
import dev.mineblock11.fabric.referencemod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;

public class MyBlockLootTableProvider extends FabricBlockLootTableProvider {
    MyBlockLootTableProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {
//        addDrop(ModBlocks.CONDENSED_DIRT, drops(ModBlocks.CONDENSED_DIRT.asItem()));
        addDrop(ModBlocks.CONDENSED_OAK_LOG, drops(ModItems.POOP));

        // Gives a positive result if the item has silk touch on it.
        LootCondition.Builder HAS_SILK_TOUCH = MatchToolLootCondition.builder(ItemPredicate.Builder.create()
                .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.ANY)));

        LootTable.Builder builder = LootTable.builder().pool(
                LootPool.builder()
                        // If silk touch is used, drop a diamond.
                        .with(ItemEntry.builder(Items.DIAMOND)
                                .conditionally(HAS_SILK_TOUCH)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))

                        // If silk touch is not used (invert of with silk touch), drop the normal block.
                        .with(ItemEntry.builder(ModBlocks.CONDENSED_DIRT.asItem()).conditionally(HAS_SILK_TOUCH.invert()))
        );

        addDrop(ModBlocks.CONDENSED_DIRT, builder);
    }
}