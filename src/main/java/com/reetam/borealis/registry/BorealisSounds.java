package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BorealisSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, BorealisMod.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> HAILSTONE_FALL = SOUND_EVENTS.register("weather.hailstone_fall", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "weather.hailstone_fall")));

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, name)));
    }
}
