package com.reetam.borealis.registry;

import com.reetam.borealis.world.BorealisBiomeProvider;
import com.reetam.borealis.world.BorealisChunkGenerator;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import com.reetam.borealis.BorealisMod;

public class BorealisDimensions {

    public static final RegistryKey<DimensionType> borealis = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, name("borealis"));
    public static final RegistryKey<World> borealis_w = RegistryKey.create(Registry.DIMENSION_REGISTRY, name("borealis"));

    public static void registerDimensionGenerators() {
        Registry.register(Registry.CHUNK_GENERATOR, name("chunk_generator"), BorealisChunkGenerator.CODEC);
        Registry.register(Registry.BIOME_SOURCE, name("biome_provider"), BorealisBiomeProvider.CODEC);
    }

    private static ResourceLocation name(String name) {
        return new ResourceLocation(BorealisMod.MODID, name);
    }
}