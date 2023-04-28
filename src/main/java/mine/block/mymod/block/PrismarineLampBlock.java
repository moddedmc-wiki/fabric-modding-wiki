package mine.block.mymod.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PrismarineLampBlock extends Block {
    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }

    public PrismarineLampBlock() {
        super(AbstractBlock.Settings
                .of(Material.REDSTONE_LAMP, MapColor.DARK_AQUA)
                .sounds(BlockSoundGroup.GLASS)
                .luminance((state) -> {
                    boolean activated = state.get(ACTIVATED);

                    return activated ? 15 : 0;
                })
        );

        setDefaultState(getDefaultState().with(ACTIVATED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Ignore the interaction if it was run on the client.
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        // Get the current value of the "activated" property
        boolean activated = state.get(ACTIVATED);

        // Flip the value of activated and save the new blockstate.
        world.setBlockState(pos, state.with(ACTIVATED, !activated));

        return ActionResult.SUCCESS;
    }


}
