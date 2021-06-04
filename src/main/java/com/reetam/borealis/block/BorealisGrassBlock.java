package com.reetam.borealis.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import com.reetam.borealis.registry.BorealisBlocks;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class BorealisGrassBlock extends SpreadableSnowyDirtBlock {

    public static final BooleanProperty SUGARY = BooleanProperty.create("sugary");

    public BorealisGrassBlock(Properties builder) {
        super(builder);
        this.registerDefaultState(this.stateDefinition.any().setValue(SNOWY, Boolean.FALSE).setValue(SUGARY, Boolean.FALSE));
    }

    private static boolean isSnowyConditions(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = world.getBlockState(blockpos);
        if ((blockstate.getBlock() == Blocks.SNOW && blockstate.getValue(SnowBlock.LAYERS) == 1 ) || blockstate.getBlock() == BorealisBlocks.sugar_snow.get()) {
            return true;
        } else {
            int i = LightEngine.getLightBlockInto(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(world, blockpos));
            return i < world.getMaxLightLevel();
        }
    }

    private static boolean isSnowyAndNotUnderwater(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return isSnowyConditions(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!isSnowyConditions(state, worldIn, pos)) {
            if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            worldIn.setBlockAndUpdate(pos, BorealisBlocks.permafrost.get().defaultBlockState());
        }
        else {
            BlockState blockstate = this.defaultBlockState();

            for (int i = 0; i < 4; ++i) {
                BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (worldIn.getBlockState(blockpos).is(BorealisBlocks.permafrost.get()) && isSnowyAndNotUnderwater(blockstate, worldIn, blockpos)) {
                    worldIn.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, worldIn.getBlockState(blockpos.above()).is(Blocks.SNOW)));
                }
            }
        }
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        //boolean flag = world
        return facing != Direction.UP ?
                super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos) :
                stateIn.setValue(SNOWY, facingState.is(Blocks.SNOW_BLOCK) || facingState.is(Blocks.SNOW)).setValue(SUGARY, facingState.is(BorealisBlocks.sugar_snow.get()));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().above());
        return this.defaultBlockState().setValue(SNOWY, blockstate.is(Blocks.SNOW_BLOCK) || blockstate.is(Blocks.SNOW)).setValue(SUGARY, blockstate.is(BorealisBlocks.sugar_snow.get()));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(SNOWY, SUGARY);
    }
}
