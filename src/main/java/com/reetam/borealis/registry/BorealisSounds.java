package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class BorealisSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BorealisMod.MODID);

    public static final SoundEvent BOREALIS_PORTAL_CHIME = register("blocks.borealis_portal");

    private static SoundEvent register(String name) {
        SoundEvent sound = new SoundEvent(new ResourceLocation(BorealisMod.MODID, name));
        sound.setRegistryName(new ResourceLocation(BorealisMod.MODID, name));
        return sound;
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(
                BOREALIS_PORTAL_CHIME
        );
    }
}
