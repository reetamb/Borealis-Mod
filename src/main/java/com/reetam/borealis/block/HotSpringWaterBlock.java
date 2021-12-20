package com.reetam.borealis.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;

public class HotSpringWaterBlock extends LiquidBlock {

    public HotSpringWaterBlock(Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid, properties.noCollission().strength(100F).noDrops());
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {
        level.addParticle(ParticleTypes.CLOUD, pos.getX(), pos.getY()+rand.nextFloat(), pos.getZ(), 0.0, 0.1, 0.0);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntityIn = (LivingEntity) entity;
            Collection<MobEffectInstance> activeEffects = livingEntityIn.getActiveEffects();
            ArrayList<MobEffectInstance> activeEffectsArray = new ArrayList<>(activeEffects);
            for (MobEffectInstance effectInstance : activeEffectsArray) {
                if (effectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL) {
                    livingEntityIn.removeEffect(effectInstance.getEffect());
                }
            }
        }
    }
}
