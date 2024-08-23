package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.effect.ManiaEffect;
import com.reetam.borealis.effect.StaticEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BorealisEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BorealisMod.MODID);

    public static final RegistryObject<MobEffect> MANIA = EFFECTS.register("mania", () -> new ManiaEffect(MobEffectCategory.NEUTRAL, 0xFF7A99));
    public static final RegistryObject<MobEffect> STATIC = EFFECTS.register("static", () -> new StaticEffect(MobEffectCategory.NEUTRAL, 0xD4FF00));
}
