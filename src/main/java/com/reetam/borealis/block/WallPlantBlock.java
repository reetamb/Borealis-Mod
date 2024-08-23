package com.reetam.borealis.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;

public class WallPlantBlock extends Block {
    public static final DirectionProperty FACING;
    private static final Map<Direction, VoxelShape> AABBS;
    private final TagKey<Block> substrate;

    public WallPlantBlock(TagKey<Block> substrate, BlockBehaviour.Properties pProperties) {
        super(pProperties.randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        this.substrate = substrate;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.canSeeSky(pPos)) {
            pLevel.removeBlock(pPos, false);
        }
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState);
    }

    public static VoxelShape getShape(BlockState pState) {
        return AABBS.get(pState.getValue(FACING));
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        Direction $$3 = pState.getValue(FACING);
        BlockPos $$4 = pPos.relative($$3.getOpposite());
        BlockState $$5 = pLevel.getBlockState($$4);
        return $$5.isFaceSturdy(pLevel, $$4, $$3) && $$5.is(this.substrate) && !pLevel.canSeeSky(pPos);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState $$1 = this.defaultBlockState();
        LevelReader $$2 = pContext.getLevel();
        BlockPos $$3 = pContext.getClickedPos();
        Direction[] $$4 = pContext.getNearestLookingDirections();
        Direction[] var6 = $$4;
        int var7 = $$4.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            Direction $$5 = var6[var8];
            if ($$5.getAxis().isHorizontal()) {
                Direction $$6 = $$5.getOpposite();
                $$1 = $$1.setValue(FACING, $$6);
                if ($$1.canSurvive($$2, $$3)) {
                    return $$1;
                }
            }
        }
        return null;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing.getOpposite() == pState.getValue(FACING) && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : pState;
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

    static {
        FACING = HorizontalDirectionalBlock.FACING;
        AABBS = Maps.newEnumMap(ImmutableMap.of(
                Direction.NORTH, Block.box(5.5, 3.0, 11.0, 10.5, 13.0, 16.0),
                Direction.SOUTH, Block.box(5.5, 3.0, 0.0, 10.5, 13.0, 5.0),
                Direction.WEST, Block.box(11.0, 3.0, 5.5, 16.0, 13.0, 10.5),
                Direction.EAST, Block.box(0.0, 3.0, 5.5, 5.0, 13.0, 10.5)
        ));
    }
}
