package com.reetam.borealis.registry.world;

import com.mojang.datafixers.util.Pair;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class BorealisBiomes {

    public static final ResourceKey<Biome> BOREAL_TUNDRA = createKey("boreal_tundra");
    public static final ResourceKey<Biome> BRUMAL_GROVE = createKey("brumal_grove");
    public static final ResourceKey<Biome> FROSTFIR_WOODS = createKey("frostfir_woods");
    public static final ResourceKey<Biome> HOT_SPRING_ISLANDS = createKey("hot_spring_islands");
    public static final ResourceKey<Biome> RAVAGED_GLACIER = createKey("ravaged_glacier");
    public static final ResourceKey<Biome> SACCHARINE_HILLS = createKey("saccharine_hills");

    private static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, name));
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> vanillaConfiguredCarvers = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(BOREAL_TUNDRA, borealTundra(placedFeatures, vanillaConfiguredCarvers));
        context.register(BRUMAL_GROVE, brumalGrove(placedFeatures, vanillaConfiguredCarvers));
        context.register(FROSTFIR_WOODS, frostfirWoods(placedFeatures, vanillaConfiguredCarvers));
        context.register(HOT_SPRING_ISLANDS, hotSprings(placedFeatures, vanillaConfiguredCarvers));
        context.register(RAVAGED_GLACIER, ravagedGlacier(placedFeatures, vanillaConfiguredCarvers));
        context.register(SACCHARINE_HILLS, saccharineHills(placedFeatures, vanillaConfiguredCarvers));
    }
    public static Biome borealTundra(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        return makeDefaultBiome(
                new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
                        .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, BorealisPlacedFeatures.PLACED_SLATE_BOULDER)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_LICHEN_PATCH)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_TALL_GRASS)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL),
                new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(BorealisEntities.THRUSHER.get(), 8, 1, 3)),
                10136810);
    }

    public static Biome brumalGrove(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        return makeDefaultBiome(
                new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_BRUMAL_TREE_1)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_BRUMAL_TREE_2)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_BRUMAL_TREE_3)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_BRUMAL_TREE_4)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_LICHEN_PATCH)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL),

                new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(BorealisEntities.TAKAHE.get(), 100, 2, 4)),
                10926829);
    }
    public static Biome frostfirWoods(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        return makeDefaultBiome(
                new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
                        .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, BorealisPlacedFeatures.PLACED_SLATE_BOULDER)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_FROSTFIR_TREE)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_HOLLY_PATCH)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_LICHEN_PATCH)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_LARGE_FERN)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_TAIGA),
                new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(BorealisEntities.TUBER.get(), 100, 2, 4)),
                6193806);
    }
    public static Biome ravagedGlacier(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        return makeDefaultBiome(
                new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
                        .addFeature(GenerationStep.Decoration.RAW_GENERATION, BorealisPlacedFeatures.PLACED_GLACIAL_RIDGE)
                        .addFeature(GenerationStep.Decoration.RAW_GENERATION, BorealisPlacedFeatures.PLACED_GLACIAL_SPIKE),
                new MobSpawnSettings.Builder(),
                10136810);
    }
    public static Biome hotSprings(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        return makeDefaultBiome(
                new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
                        .addFeature(GenerationStep.Decoration.RAW_GENERATION, BorealisPlacedFeatures.PLACED_HOT_SPRING)
                        .addFeature(GenerationStep.Decoration.RAW_GENERATION, BorealisPlacedFeatures.PLACED_DELTA_HOT_SPRINGS),
                new MobSpawnSettings.Builder(),
                10136810);
    }
    public static Biome saccharineHills(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        return makeDefaultBiome(
                new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_COTTON_TREE)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_HELIX_TREE)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_GLAZED_COTTON_TREE)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BorealisPlacedFeatures.PLACED_GLAZED_HELIX_TREE),
                new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(BorealisEntities.HUMMINGBIRD.get(), 100, 1, 4)),
                14134238);
    }

    private static ResourceKey<PlacedFeature> freeze(HolderGetter<PlacedFeature> placedFeatures) {
        return placedFeatures.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.withDefaultNamespace("freeze_top_layer"))).key();
    }
    public static Biome makeDefaultBiome(BiomeGenerationSettings.Builder generation, MobSpawnSettings.Builder spawning, int fogColor) {
        return fullDefinition(true,
                0.0F,
                0.0F,
                spawning.build(),
                new BiomeSpecialEffects.Builder()
                        .fogColor(fogColor)
                        .skyColor(10136810)
                        .waterColor(5683175)
                        .waterFogColor(5683175)
                        .grassColorOverride(14745599)
                        .foliageColorOverride(10338810)
                        .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.SNOWFLAKE, 0.1F))
                        .build(),
                generation
                        //.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, BorealisPlacedFeatures.PLACED_CLOUD)
                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BorealisPlacedFeatures.PLACED_KYANITE_CRYSTAL)
                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BorealisPlacedFeatures.PLACED_CLOUD_HAILSTONE)
                        .build(),
                Biome.TemperatureModifier.NONE
        );
    }

    public static Biome fullDefinition(boolean precipitation, float temperature, float downfall, MobSpawnSettings spawning, BiomeSpecialEffects effects, BiomeGenerationSettings generationSettings, Biome.TemperatureModifier temperatureModifier) {
        return new Biome.BiomeBuilder()
                .hasPrecipitation(precipitation)
                .temperature(temperature)
                .downfall(downfall)
                .mobSpawnSettings(spawning)
                .specialEffects(effects)
                .generationSettings(generationSettings)
                .temperatureAdjustment(temperatureModifier)
                .build();
    }

    public static BiomeSource biomeSource(HolderGetter<Biome> biomes) {
        Climate.Parameter zero = Climate.Parameter.point(0.0F);
        Climate.Parameter warm = Climate.Parameter.span(0.66F, 1.0F);
        Climate.Parameter cold = Climate.Parameter.span(-1.0F, 0.66F);
        Climate.Parameter wet = Climate.Parameter.span(0.5F, 1.0F);
        Climate.Parameter dry = Climate.Parameter.span(-0.75F, -0.25F);

        return MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(List.of(
                Pair.of(new Climate.ParameterPoint(zero, zero, zero, zero, zero, zero, 0),
                        biomes.getOrThrow(BOREAL_TUNDRA)),
                Pair.of(new Climate.ParameterPoint(zero, dry, zero, zero, zero, zero, 0),
                        biomes.getOrThrow(BOREAL_TUNDRA)),
                Pair.of(new Climate.ParameterPoint(zero, wet, zero, zero, zero, zero, 0),
                        biomes.getOrThrow(BOREAL_TUNDRA)),

                Pair.of(new Climate.ParameterPoint(cold, dry, zero, zero, zero, zero, 0),
                        biomes.getOrThrow(FROSTFIR_WOODS)),
                Pair.of(new Climate.ParameterPoint(cold, zero, zero, zero, zero, zero, 0),
                        biomes.getOrThrow(BOREAL_TUNDRA)),
                Pair.of(new Climate.ParameterPoint(cold, wet, zero, zero, zero, zero, 0),
                        biomes.getOrThrow(BRUMAL_GROVE)),

                Pair.of(new Climate.ParameterPoint(warm, dry, zero, zero, zero, zero, 0),
                        biomes.getOrThrow(SACCHARINE_HILLS)),
                Pair.of(new Climate.ParameterPoint(warm, wet, zero, zero, zero, zero, 0),
                        biomes.getOrThrow(HOT_SPRING_ISLANDS))
        )));
    }
}
