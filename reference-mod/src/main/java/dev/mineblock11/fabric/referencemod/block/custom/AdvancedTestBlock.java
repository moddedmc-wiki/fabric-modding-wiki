package dev.mineblock11.fabric.referencemod.block.custom;

import dev.mineblock11.fabric.referencemod.util.MyModProperties;
import dev.mineblock11.fabric.referencemod.util.helper.LoggerUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("deprecation")
public class AdvancedTestBlock extends Block {
    public static final int MAX_ARROWS_COUNT = 3;

    // Blockstate properties
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty ARROWS = MyModProperties.ARROWS; // custom Block-state Property
    public static final EnumProperty<HitBoxVariant> BOXES = MyModProperties.HITBOX_SIZE;  // custom Block-state Property


    public AdvancedTestBlock(Settings settings) {
        super(settings);
        this.getDefaultState().with(FACING, Direction.NORTH).with(ARROWS, 0).with(BOXES, HitBoxVariant.FULL);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, ARROWS, BOXES);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        return switch (state.get(BOXES)) {
            case SMALL -> HitBoxVariant.SMALL.getShape(direction);
            case BIG -> HitBoxVariant.BIG.getShape(direction);
            case FULL -> HitBoxVariant.FULL.getShape(direction);
        };
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(ARROWS, 1).with(BOXES, HitBoxVariant.SMALL);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            world.setBlockState(pos, player.isSneaking() ? state.cycle(BOXES) : state.cycle(ARROWS), Block.NOTIFY_ALL);
            world.updateNeighborsAlways(pos, this);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable(this.getTranslationKey().concat(".subtitle.shift_0")));
            tooltip.add(Text.translatable(this.getTranslationKey().concat(".subtitle.shift_1")));
        } else {
            tooltip.add(Text.translatable(this.getTranslationKey().concat(".subtitle")));
        }
    }

    // Example enum for blockstate and voxelshape management
    public enum HitBoxVariant implements StringIdentifiable {
        SMALL(0, "small"),
        BIG(1, "big"),
        FULL(2, "full");

        private final int id;
        private final String shapeName;

        HitBoxVariant(int id, String shapeName) {
            this.id = id;
            this.shapeName = shapeName;
        }

        public int getId() {
            return this.id;
        }

        public static HitBoxVariant fromId(int id) {
            for (HitBoxVariant entry : HitBoxVariant.values()) {
                if (entry.getId() == id) return entry;
            }
            LoggerUtil.devLogger("Couldn't find VoxelShape entry. Using fallback value!", true, null);
            return HitBoxVariant.FULL;
        }

        @Override
        public String asString() {
            return this.shapeName;
        }

        public VoxelShape getShape(Direction direction) {
            if (this.equals(FULL)) return VoxelShapes.fullCube();

            CornerPoints cornerPoints = CornerPoints.facing(this, direction);
            Vec3d start = cornerPoints.startPoint;
            Vec3d end = cornerPoints.endPoint;

            return VoxelShapes.cuboid(start.x, start.y, start.z, end.x, end.y, end.z);
        }

        private record CornerPoints(Vec3d startPoint, Vec3d endPoint) {
            private static final double MIN = 0;
            private static final double MAX = 1;

            private static CornerPoints full() {
                return new CornerPoints(new Vec3d(MIN, MIN, MIN), new Vec3d(MAX, MAX, MAX));
            }

            private static CornerPoints facing(HitBoxVariant variant, Direction direction) {
                if (variant.equals(BIG)) {
                    return switch (direction) {
                        case EAST -> new CornerPoints(new Vec3d(0, 0, 0.5), new Vec3d(1, 1, 1));
                        case SOUTH -> new CornerPoints(new Vec3d(0, 0, 0), new Vec3d(0.5, 1, 1));
                        case WEST -> new CornerPoints(new Vec3d(0, 0, 0), new Vec3d(1, 1, 0.5));
                        default -> new CornerPoints(new Vec3d(0.5, 0, 0), new Vec3d(1, 1, 1));
                    };
                } else if (variant.equals(SMALL)) {
                    return switch (direction) {
                        case EAST -> new CornerPoints(new Vec3d(0.5625, 0, 0), new Vec3d(1, 0.25, 0.5));
                        case SOUTH -> new CornerPoints(new Vec3d(0.5, 0, 0.5625), new Vec3d(1, 0.25, 1));
                        case WEST -> new CornerPoints(new Vec3d(0, 0, 0.5), new Vec3d(0.4375, 0.25, 1));
                        default -> new CornerPoints(new Vec3d(0, 0, 0), new Vec3d(0.5, 0.25, 0.4375));
                    };
                } else return CornerPoints.full();
            }
        }
    }
}
