package com.reetam.borealis.block.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.function.Supplier;

public class QuicksilverBlock extends LiquidBlock {

    public QuicksilverBlock(Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid, properties.noCollission().strength(100F).noLootTable());
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        if (rand.nextInt(10) == 0) level.addParticle(ParticleTypes.SCRAPE, pos.getX(), pos.getY(), pos.getZ(), 0.0, 0.0, 0.0);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livingEntityIn) {
            livingEntityIn.hurt(level.damageSources().magic(), 4.0F);
        }
    }
}
