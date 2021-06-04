package com.reetam.borealis.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock.Properties;

public class HotSpringWaterBlock extends FlowingFluidBlock {

    public HotSpringWaterBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties.noCollission().strength(100).noDrops());
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        worldIn.addParticle(ParticleTypes.CLOUD, pos.getX(), pos.getY()+rand.nextFloat(), pos.getZ(), 0.0, 0.1, 0.0);
    }

    @Override
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity) {
            LivingEntity livingEntityIn = (LivingEntity) entityIn;
            Collection<EffectInstance> activeEffects = livingEntityIn.getActiveEffects();
            ArrayList<EffectInstance> activeEffectsArray = new ArrayList<>(activeEffects);
            for (EffectInstance effectInstance : activeEffectsArray) {
                if (effectInstance.getEffect().getCategory() == EffectType.HARMFUL) {
                    livingEntityIn.removeEffect(effectInstance.getEffect());
                }
            }
        }
    }
}
