package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.effect.ManiaEffect;
import com.reetam.borealis.effect.StaticEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BorealisEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, BorealisMod.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> MANIA = EFFECTS.register("mania", () -> new ManiaEffect(MobEffectCategory.NEUTRAL, 0xFF7A99));
    public static final DeferredHolder<MobEffect, MobEffect> STATIC = EFFECTS.register("static", () -> new StaticEffect(MobEffectCategory.NEUTRAL, 0xD4FF00));
}
