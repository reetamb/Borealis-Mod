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

public class BorealisGrassBlock extends SpreadableSnowyDirtBlock {

    public static final BooleanProperty SUGARY = BooleanProperty.create("sugary");

    public BorealisGrassBlock(Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(SNOWY, Boolean.FALSE).with(SUGARY, Boolean.FALSE));
    }

    private static boolean isSnowyConditions(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = world.getBlockState(blockpos);
        if ((blockstate.getBlock() == Blocks.SNOW && blockstate.get(SnowBlock.LAYERS) == 1 ) || blockstate.getBlock() == BorealisBlocks.sugar_snow.get()) {
            return true;
        } else {
            int i = LightEngine.func_215613_a(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(world, blockpos));
            return i < world.getMaxLightLevel();
        }
    }

    private static boolean isSnowyAndNotUnderwater(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return isSnowyConditions(state, world, pos) && !world.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!isSnowyConditions(state, worldIn, pos)) {
            if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            worldIn.setBlockState(pos, BorealisBlocks.permafrost.get().getDefaultState());
        }
        else {
            BlockState blockstate = this.getDefaultState();

            for (int i = 0; i < 4; ++i) {
                BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (worldIn.getBlockState(blockpos).isIn(BorealisBlocks.permafrost.get()) && isSnowyAndNotUnderwater(blockstate, worldIn, blockpos)) {
                    worldIn.setBlockState(blockpos, blockstate.with(SNOWY, worldIn.getBlockState(blockpos.up()).isIn(Blocks.SNOW)));
                }
            }
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        //boolean flag = world
        return facing != Direction.UP ?
                super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos) :
                stateIn.with(SNOWY, facingState.isIn(Blocks.SNOW_BLOCK) || facingState.isIn(Blocks.SNOW)).with(SUGARY, facingState.isIn(BorealisBlocks.sugar_snow.get()));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = context.getWorld().getBlockState(context.getPos().up());
        return this.getDefaultState().with(SNOWY, blockstate.isIn(Blocks.SNOW_BLOCK) || blockstate.isIn(Blocks.SNOW)).with(SUGARY, blockstate.isIn(BorealisBlocks.sugar_snow.get()));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(SNOWY, SUGARY);
    }
}
