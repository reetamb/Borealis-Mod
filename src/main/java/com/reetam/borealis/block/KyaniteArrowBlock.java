package com.reetam.borealis.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class KyaniteArrowBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    protected static final VoxelShape TOP_AABB;
    protected static final VoxelShape BOTTOM_AABB;
    protected static final VoxelShape WEST_AABB;
    protected static final VoxelShape EAST_AABB;
    protected static final VoxelShape NORTH_AABB;
    protected static final VoxelShape SOUTH_AABB;
    public KyaniteArrowBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.DOWN));
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos).isFaceSturdy(pLevel, pPos, pState.getValue(FACING));
    }

    // TODO: hey btw you need to make it so that the arrowheads break when the block they're on is broken
    // also make it so multishot crossbow ones don't drop anything
    // this should probably involve a bool "multishot" blockstate
    // also you need to add a recipe for crafting the arrows, probably 2 or 3 kyanite > 1 arrow

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getClickedFace().getOpposite());
    }

    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        return switch (direction) {
            case DOWN -> BOTTOM_AABB;
            case UP -> TOP_AABB;
            case EAST -> EAST_AABB;
            case WEST -> WEST_AABB;
            case NORTH -> NORTH_AABB;
            case SOUTH -> SOUTH_AABB;
        };
    }

    static {
        TOP_AABB = Block.box(5, 0, 5, 5 + 6, 5, 5 + 6);
        BOTTOM_AABB = Block.box(5, 16 - 5, 5, 5 + 6, 16, 5 + 6);
        EAST_AABB = Block.box(0, 5, 5, 5, 11, 11);
        WEST_AABB = Block.box(16 - 5, 5, 5, 16, 11, 11);
        SOUTH_AABB = Block.box(5, 5, 0, 11, 11, 5);
        NORTH_AABB = Block.box(5, 5, 16 - 5, 11, 11, 16);
    }
}
