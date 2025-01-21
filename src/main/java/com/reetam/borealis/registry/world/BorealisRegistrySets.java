package com.reetam.borealis.registry.world;

import com.reetam.borealis.BorealisMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BorealisRegistrySets extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, BorealisConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, BorealisPlacedFeatures::bootstrap)
            .add(Registries.BIOME, BorealisBiomes::bootstrap)
            .add(Registries.NOISE_SETTINGS, BorealisWorld::bootstrapNoise)
            .add(Registries.DIMENSION_TYPE, BorealisWorld::bootstrapDimensionType)
            .add(Registries.LEVEL_STEM, BorealisWorld::bootstrapLevelStem);
    public BorealisRegistrySets(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of("minecraft", BorealisMod.MODID));
    }
}