package dev.mineblock11.fabric.referencemod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestBlockEntity extends BlockEntity {
    // A static String variable to avoid typos when refering to the NBT data
    private static final String INTERACT_NBT_KEY = "test_block.interaction";
    // Creating a field for the object's custom data
    private int interactionCount = 0;
    private int tickCounter = 0;

    public TestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TEST_BLOCK_ENTITY_TYPE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState blockState, BlockEntity blockEntity) {
        if (blockEntity instanceof TestBlockEntity testBlockEntity) {
            if (testBlockEntity.tickCounter % 200 == 0) {
                world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1f, 1f);
            }
            testBlockEntity.tickCounter++;
        }
    }

    // "Accessor" (getter) method
    // To access this value from outside of this class
    public int getInteractionCount() {
        return this.interactionCount;
    }

    // "Mutator" (setter) method
    // To change this value from outside of this class
    public void setInteractionCount(int newCount) {
        this.interactionCount = newCount;
        // When changing values for the BlockEntity, make sure to mark it as dirty.
        // Otherwise, the new values won't be saved properly in the NBT data
        this.markDirty();
    }

    // Store data in the NBT of the BlockEntity
    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt(INTERACT_NBT_KEY, this.interactionCount);
        super.writeNbt(nbt);
    }

    // Read data from the NBT of the BlockEntity
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.interactionCount = nbt.getInt(INTERACT_NBT_KEY);
    }
}