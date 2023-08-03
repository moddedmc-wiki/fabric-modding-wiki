package dev.mineblock11.fabric.referencemod.block.custom;

import dev.mineblock11.fabric.referencemod.block.entity.TestBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TestBlock extends BlockWithEntity {
    public TestBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        // Check for the custom BlockEntity
        if (world.getBlockEntity(pos) instanceof TestBlockEntity testBlockEntity && !world.isClient()) {
            // Using the Accessor of the custom BlockEntity
            int count = testBlockEntity.getInteractionCount();
            // Using the Mutator of the custom BlockEntity
            testBlockEntity.setInteractionCount(count + 1);

            // Value output for the player in the chat
            if (entity instanceof PlayerEntity) {
                entity.sendMessage(Text.literal("You've stepped on this block " + testBlockEntity.getInteractionCount() + " times!"));
            }
        }

        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TestBlockEntity(pos, state);
    }

    // When extending from BlockWithEntity, it would be invisible by default.
    // Make sure to pass in the correct BlockRenderType.
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
