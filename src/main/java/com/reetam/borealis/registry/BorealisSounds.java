package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BorealisSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BorealisMod.MODID);

    public static final RegistryObject<SoundEvent> BOREALIS_PORTAL_CHIME = register("block.borealis_portal");
    public static final RegistryObject<SoundEvent> HAILSTONE_FALL = SOUND_EVENTS.register("weather.hailstone_fall", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BorealisMod.MODID, "weather.hailstone_fall")));

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BorealisMod.MODID, name)));
    }
}
