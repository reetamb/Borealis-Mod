package com.reetam.borealis.block.fluid;

import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public abstract class PortalFluid extends BaseFlowingFluid {

    public PortalFluid(Properties properties) {
        super(properties);
        properties.levelDecreasePerBlock(4);
    }

    @Override
    protected boolean isRandomlyTicking() {
        return true;
    }

//    @Override
//    protected void randomTick(Level pLevel, BlockPos pPos, FluidState pState, RandomSource pRandom) {
//        Direction.Plane.HORIZONTAL.stream().forEach((direction -> {
//            BlockState block = pLevel.getBlockState(pPos.relative(direction));
//            if (!block.is(BorealisBlocks.KYANITE_FLAGSTONE) && !block.is(BorealisFluids.PORTAL_FLUID_BLOCK)) {
//                pLevel.addParticle(ParticleTypes.ANGRY_VILLAGER, true, pPos.getX(), pPos.getY() + 1, pPos.getZ(), 0, 0, 0);
//                pLevel.setBlock(pPos.above(), Blocks.STONE.defaultBlockState(), 3);
//            }
//        }));
//    }


    @Override
    protected boolean canConvertToSource(Level level) {
        return false;
    }

    public static class Flowing extends PortalFluid {
        public Flowing(Properties properties) {
            super(properties);
            this.registerDefaultState(this.getStateDefinition().any().setValue(LEVEL, 7));
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends PortalFluid {
        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}