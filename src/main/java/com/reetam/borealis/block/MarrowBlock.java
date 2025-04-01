package com.reetam.borealis.block;

import com.reetam.borealis.block.property.MarrowShape;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MarrowBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<MarrowShape> SHAPE = EnumProperty.create("shape", MarrowShape.class);
    public static final BooleanProperty TOP = BooleanProperty.create("top");
    public static final VoxelShape UP_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
    public static final VoxelShape WEST_AABB = Block.box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0);
    public static final VoxelShape EAST_AABB = Block.box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    public static final VoxelShape NORTH_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0);
    public static final VoxelShape SOUTH_AABB = Block.box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0);
    public MarrowBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(SHAPE, MarrowShape.SOLO)
                .setValue(TOP, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57882_) {
        p_57882_.add(FACING, SHAPE, TOP);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockPos thisPos = pos.relative(context.getClickedFace());


        return calculateState(level, pos, super.getStateForPlacement(context), context.getClickedFace());
    }

    public BlockState calculateState(Level level, BlockPos thisPos, BlockState state, Direction direction) {
        MarrowShape shape;

        if (level.getBlockState(thisPos.above()).is(this)) {
            if (level.getBlockState(thisPos.below()).is(this)) {
                shape = MarrowShape.MIDDLE;
            } else {
                shape = MarrowShape.END;
            }
        } else {
            if (level.getBlockState(thisPos.below()).is(this)) {
                shape = MarrowShape.SOURCE;
            } else {
                shape = MarrowShape.SOLO;
            }
        }

        return state
                .setValue(FACING, direction)
                .setValue(SHAPE, shape)
                .setValue(TOP, canSupportCenter(level, thisPos.below(), Direction.UP));
    }

    @Override
    protected BlockState updateShape(BlockState thisState, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos thisPos, BlockPos neighborPos) {
        MarrowShape shape;

        if (level.getBlockState(thisPos.above()).is(this)) {
            if (level.getBlockState(thisPos.below()).is(this)) {
                shape = MarrowShape.MIDDLE;
            } else {
                shape = MarrowShape.END;
            }
        } else {
            if (level.getBlockState(thisPos.below()).is(this)) {
                shape = MarrowShape.SOURCE;
            } else {
                shape = MarrowShape.SOLO;
            }
        }

        return !this.canSurvive(thisState, level, thisPos) ? Blocks.AIR.defaultBlockState() :
                thisState.setValue(SHAPE, shape)
                        .setValue(TOP, canSupportCenter(level, thisPos.below(), Direction.UP));
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos supportPos = pos.relative(state.getValue(FACING).getOpposite());
        return (level.getBlockState(supportPos).is(BlockTags.LOGS) || level.getBlockState(supportPos).is(BorealisBlocks.BONE_DRY_WOOD))
                && level.getBlockState(supportPos).isFaceSturdy(level, supportPos, state.getValue(FACING));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        VoxelShape shape;

        switch (state.getValue(FACING)) {
            default -> shape = SOUTH_AABB;
            case Direction.EAST -> shape = WEST_AABB;
            case Direction.WEST -> shape = EAST_AABB;
            case Direction.SOUTH -> shape = NORTH_AABB;
        }
        if (state.getValue(TOP)) {
            return Shapes.or(shape, UP_AABB);
        }
        return shape;
    }
}
