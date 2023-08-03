package dev.mineblock11.fabric.referencemod.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightningStick extends Item {
    public LightningStick(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // Ensure we don't spawn the lightning only on the client.
        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 10);

        // Spawn the lightning bolt.
        LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightningBolt.setPosition(frontOfPlayer.toCenterPos());
        world.spawnEntity(lightningBolt);

        // Nothing has changed to the item stack,
        // so we just return it how it was.
        ItemStack heldStack = user.getStackInHand(hand);
        heldStack.decrement(1);
        return TypedActionResult.success(heldStack);
    }
}
