package com.reetam.borealis.effect;

import com.reetam.borealis.BorealisMod;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class ManiaEffect extends MobEffect {
    private int effectDuration;
    private int nextTwitch = 0;

    public ManiaEffect(MobEffectCategory category, int color) {
        super(category, color);
        nextTwitch = 20;
    }
    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        RandomSource random = livingEntity.getRandom();
        if (nextTwitch == 0) {
            nextTwitch = random.nextInt(0, 40 / amplifier);
        } else {
            if (this.effectDuration % nextTwitch == 0) {
                livingEntity.moveTo(
                        livingEntity.getX() + (random.nextFloat() - 0.5F) / 2.5,
                        livingEntity.getY(),
                        livingEntity.getZ() + (random.nextFloat() - 0.5F) / 2.5,
                        livingEntity.getYHeadRot() + (random.nextFloat() - 0.5F) * 2F,
                        livingEntity.getXRot()
                );
            }
        }
        if (this.effectDuration % 30 == 0) BorealisMod.LOGGER.error("new mania is running");
        super.applyEffectTick(livingEntity, amplifier);
        return true;
    }

    @Override
    public void removeAttributeModifiers(AttributeMap pAttributeMap) {
        super.removeAttributeModifiers(pAttributeMap);
    }

    @Override
    public void addAttributeModifiers(AttributeMap pAttributeMap, int pAmplifier) {
        super.addAttributeModifiers(pAttributeMap, pAmplifier);
    }
}
