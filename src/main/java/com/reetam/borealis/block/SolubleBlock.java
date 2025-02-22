package com.reetam.borealis.block;

import com.reetam.borealis.registry.BorealisFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;

public class SolubleBlock extends Block {
    @Nullable private final BlockState dissolveState;
    public SolubleBlock(Properties properties, @Nullable BlockState dissolveState) {
        super(properties.randomTicks());
        this.dissolveState = dissolveState;
    }

    @Override
    protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(4) == 0) {
            FluidState state = pLevel.getFluidState(pPos.above());
            if (state.is(BorealisFluids.HOT_SPRING_WATER_FLOWING.get())) {
                if (!state.getValue(FlowingFluid.FALLING)) {
                    pLevel.removeBlock(pPos, false);
                } else {
                    if (this.dissolveState != null) {
                        pLevel.setBlock(pPos, dissolveState, 3);
                    }
                }
            }
        }
    }
}
