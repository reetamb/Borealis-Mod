package com.reetam.borealis.registry;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import com.reetam.borealis.BorealisMod;

public class BorealisBiomes {

    public static final RegistryKey<Biome> boreal_tundra = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(BorealisMod.MODID, "boreal_tundra"));
    public static final RegistryKey<Biome> frostfir_woods = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(BorealisMod.MODID, "frostfir_woods"));

}
