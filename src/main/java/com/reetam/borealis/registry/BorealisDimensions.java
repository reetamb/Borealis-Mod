package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.world.seed.BorealisBiomeProvider;
import com.reetam.borealis.world.seed.BorealisChunkGenerator;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class BorealisDimensions {

    public static final ResourceKey<DimensionType> BOREALIS_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, name("borealis"));
    public static final ResourceKey<Level> BOREALIS = ResourceKey.create(Registry.DIMENSION_REGISTRY, name("borealis"));

    public static void registerDimensionGenerators() {
        Registry.register(Registry.CHUNK_GENERATOR, name("chunk_generator"), BorealisChunkGenerator.CODEC);
        Registry.register(Registry.BIOME_SOURCE, name("biome_provider"), BorealisBiomeProvider.CODEC);
    }

    private static ResourceLocation name(String name) {
        return new ResourceLocation(BorealisMod.MODID, name);
    }
}