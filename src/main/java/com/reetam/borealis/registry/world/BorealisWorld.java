package com.reetam.borealis.registry.world;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.OptionalLong;

public class BorealisWorld {
    private final static ResourceLocation BOREALIS_LEVEL_ID = ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "borealis");

    public static final ResourceKey<DimensionType> BOREALIS_DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, BOREALIS_LEVEL_ID);
    public static final ResourceKey<Level> BOREALIS_LEVEL = ResourceKey.create(Registries.DIMENSION, BOREALIS_LEVEL_ID);
    public static final ResourceKey<LevelStem> BOREALIS_LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM, BOREALIS_LEVEL_ID);
    public static void bootstrapDimensionType(BootstrapContext<DimensionType> context) {
        context.register(BOREALIS_DIMENSION_TYPE, new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                false,
                4.0,
                true,
                false,
                BorealisMod.MIN_HEIGHT,
                BorealisMod.HEIGHT,
                BorealisMod.HEIGHT,
                BlockTags.INFINIBURN_OVERWORLD,
                ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "borealis"),
                0.0F,
                new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 7), 0)));
    }

    public static void bootstrapLevelStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<NoiseGeneratorSettings> noiseSettings = context.lookup(Registries.NOISE_SETTINGS);
        HolderGetter<DimensionType> dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
        BiomeSource source = BorealisBiomes.biomeSource(biomes);
        NoiseBasedChunkGenerator borealisChunkGen = new NoiseBasedChunkGenerator(source, noiseSettings.getOrThrow(ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "floating_islands"))));
        context.register(BOREALIS_LEVEL_STEM, new LevelStem(dimensionTypes.getOrThrow(BorealisWorld.BOREALIS_DIMENSION_TYPE), borealisChunkGen));
    }

    public static final ResourceKey<NoiseGeneratorSettings> FLOATING_ISLANDS = createKey("floating_islands");

    private static ResourceKey<NoiseGeneratorSettings> createKey(String name) {
        return ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, name));
    }

    public static void bootstrapNoise(BootstrapContext<NoiseGeneratorSettings> context) {
        HolderGetter<DensityFunction> densityFunctions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise.NoiseParameters> noise = context.lookup(Registries.NOISE);
        context.register(FLOATING_ISLANDS, floatingIslandNoiseSettings(densityFunctions, noise));
    }
    public static NoiseGeneratorSettings floatingIslandNoiseSettings(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noise) {
        BlockState soapstone = BorealisBlocks.SOAPSTONE.get().defaultBlockState();
        return new NoiseGeneratorSettings(
                new NoiseSettings(BorealisMod.MIN_HEIGHT, BorealisMod.HEIGHT, 1, 1), // noiseSettings
                soapstone,
                Blocks.WATER.defaultBlockState(),
                noiseRouter(densityFunctions, noise),
                surfaceRule(noise),
                List.of(),
                0,
                false,
                false,
                false,
                false
        );
    }

    private static NoiseRouter noiseRouter(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noise) {
        return new NoiseRouter(
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.shiftedNoise2d(
                        getFunction(densityFunctions, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.withDefaultNamespace("shift_x"))),
                        getFunction(densityFunctions, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.withDefaultNamespace("shift_z"))),
                        1, noise.getOrThrow(ResourceKey.create(Registries.NOISE, ResourceLocation.withDefaultNamespace("temperature")))),
                DensityFunctions.shiftedNoise2d(
                        getFunction(densityFunctions, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.withDefaultNamespace("shift_x"))),
                        getFunction(densityFunctions, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.withDefaultNamespace("shift_z"))),
                        1, noise.getOrThrow(ResourceKey.create(Registries.NOISE, ResourceLocation.withDefaultNamespace("vegetation")))),
                DensityFunctions.zero(),
                DensityFunctions.shiftedNoise2d(
                        getFunction(densityFunctions, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.withDefaultNamespace("shift_x"))),
                        getFunction(densityFunctions, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.withDefaultNamespace("shift_z"))),
                        1, noise.getOrThrow(ResourceKey.create(Registries.NOISE, ResourceLocation.withDefaultNamespace("erosion")))),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.add(
                        // MOUNTAINS
                        // blend height to prevent hitting 256|512 (gradient goes 384 to 512)
                        DensityFunctions.lerp(DensityFunctions.yClampedGradient(BorealisMod.MIN_HEIGHT+BorealisMod.HEIGHT/2, BorealisMod.MIN_HEIGHT+BorealisMod.HEIGHT, 1, 0),
                                -0.5,
                                // blend height to prevent going below -42
                                DensityFunctions.lerp(DensityFunctions.yClampedGradient(BorealisMod.MIN_HEIGHT-32, (BorealisMod.MIN_HEIGHT+BorealisMod.HEIGHT/2)-12, -1, 1),
                                        -0.15,
                                        // take continentalness_large and set the min value to 0
                                        DensityFunctions.max(
                                                DensityFunctions.constant(0),
                                                DensityFunctions.noise(get(noise, "continentalness_large"), 4, 1)))),
                        // BASE GENERATION
                        // prevent bottom height from going below 16|272 (gradient goes 240 to 272)
                        DensityFunctions.lerp(DensityFunctions.yClampedGradient(BorealisMod.MIN_HEIGHT-16, BorealisMod.MIN_HEIGHT+16, 0, 1),
                                -0.5,
                                // prevent top height from exceeding 128|384 (gradient goes 352 to 384)
                                DensityFunctions.lerp(DensityFunctions.yClampedGradient((BorealisMod.MIN_HEIGHT+BorealisMod.HEIGHT/2)-32, (BorealisMod.MIN_HEIGHT+BorealisMod.HEIGHT/2), 1, 0),
                                        -0.15,
                                        // overall blend for... something IDK LOL (gradient goes -32|224 to 116|372)
                                        DensityFunctions.lerp(DensityFunctions.yClampedGradient(BorealisMod.MIN_HEIGHT-32, (BorealisMod.MIN_HEIGHT+BorealisMod.HEIGHT/2)-12, -1, 1),
                                                -0.15,
                                                DensityFunctions.add(
                                                        DensityFunctions.noise(get(noise, "gravel"), 4, 4),
                                                        DensityFunctions.noise(get(noise, "cave_entrance"), 1, 8)))))),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero());
    }

    // increase max generation height
    // need to clamp a maximum height of the gravel+cave_entrance to compensate

    private static DensityFunction getFunction(HolderGetter<DensityFunction> densityFunctions, ResourceKey<DensityFunction> key) {
        return new DensityFunctions.HolderHolder(densityFunctions.getOrThrow(key));
    }

    @SafeVarargs
    private static SurfaceRules.RuleSource biomeBlock(BlockState block, ResourceKey<Biome>... biomes) {
        return SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes), SurfaceRules.state(block));
    }
    private static SurfaceRules.RuleSource surfaceRule(HolderGetter<NormalNoise.NoiseParameters> noise) {
        return SurfaceRules.sequence(
                // if higher than H/2
                SurfaceRules.ifTrue(SurfaceRules.yStartCheck(VerticalAnchor.belowTop(BorealisMod.HEIGHT/2), 5),
                        SurfaceRules.sequence(
                            SurfaceRules.ifTrue(SurfaceRules.yStartCheck(VerticalAnchor.belowTop(BorealisMod.HEIGHT/3), 10),
                                    SurfaceRules.sequence(
                                            SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                                    SurfaceRules.state(BorealisBlocks.FIRN.get().defaultBlockState())),
                                            SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(1, false, 0, CaveSurface.FLOOR),
                                                    SurfaceRules.state(BorealisBlocks.FIRN.get().defaultBlockState())),
                                            SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(2, false, 0, CaveSurface.FLOOR),
                                                    SurfaceRules.state(BorealisBlocks.FIRN.get().defaultBlockState())),
                                            SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(3, true, 0, CaveSurface.FLOOR),
                                                    SurfaceRules.state(BorealisBlocks.FIRN.get().defaultBlockState()))
                                    )),
                            SurfaceRules.ifTrue(SurfaceRules.noiseCondition(get(noise, "aquifer_barrier").key(), 0),
                                    SurfaceRules.state(BorealisBlocks.FIRN.get().defaultBlockState())))),
                // else
                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.belowTop(BorealisMod.HEIGHT/2), 1)), SurfaceRules.sequence(
                    SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                            SurfaceRules.sequence(
                                    biomeBlock(BorealisBlocks.FIRN.get().defaultBlockState(), BorealisBiomes.BOREAL_TUNDRA, BorealisBiomes.FROSTFIR_WOODS, BorealisBiomes.BRUMAL_GROVE, BorealisBiomes.GIANTWOOD),
                                    biomeBlock(BorealisBlocks.SUGAR_SNOW_BLOCK.get().defaultBlockState(), BorealisBiomes.SACCHARINE_HILLS),
                                    biomeBlock(Blocks.PACKED_ICE.defaultBlockState(), BorealisBiomes.RAVAGED_GLACIER),
                                    biomeBlock(BorealisBlocks.GYPSUM.get().defaultBlockState(), BorealisBiomes.HOT_SPRING_ISLANDS)
                            )),
                    SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(1, false, 0, CaveSurface.FLOOR),
                            SurfaceRules.sequence(
                                    biomeBlock(BorealisBlocks.FIRN.get().defaultBlockState(), BorealisBiomes.BOREAL_TUNDRA, BorealisBiomes.FROSTFIR_WOODS, BorealisBiomes.BRUMAL_GROVE, BorealisBiomes.GIANTWOOD),
                                    biomeBlock(BorealisBlocks.SUGAR_SNOW_BLOCK.get().defaultBlockState(), BorealisBiomes.SACCHARINE_HILLS),
                                    biomeBlock(Blocks.PACKED_ICE.defaultBlockState(), BorealisBiomes.RAVAGED_GLACIER),
                                    biomeBlock(BorealisBlocks.GYPSUM.get().defaultBlockState(), BorealisBiomes.HOT_SPRING_ISLANDS)
                            )),
                    SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(2, false, 0, CaveSurface.FLOOR),
                            SurfaceRules.sequence(
                                    biomeBlock(BorealisBlocks.FIRN.get().defaultBlockState(), BorealisBiomes.BOREAL_TUNDRA, BorealisBiomes.FROSTFIR_WOODS, BorealisBiomes.BRUMAL_GROVE, BorealisBiomes.GIANTWOOD),
                                    biomeBlock(BorealisBlocks.SUGAR_SNOW_BLOCK.get().defaultBlockState(), BorealisBiomes.SACCHARINE_HILLS),
                                    biomeBlock(Blocks.PACKED_ICE.defaultBlockState(), BorealisBiomes.RAVAGED_GLACIER),
                                    biomeBlock(BorealisBlocks.GYPSUM.get().defaultBlockState(), BorealisBiomes.HOT_SPRING_ISLANDS)
                            )),
                    SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(3, true, 0, CaveSurface.FLOOR),
                            SurfaceRules.sequence(
                                    biomeBlock(BorealisBlocks.FIRN.get().defaultBlockState(), BorealisBiomes.BOREAL_TUNDRA, BorealisBiomes.FROSTFIR_WOODS, BorealisBiomes.BRUMAL_GROVE, BorealisBiomes.GIANTWOOD),
                                    biomeBlock(BorealisBlocks.GYPSUM.get().defaultBlockState(), BorealisBiomes.HOT_SPRING_ISLANDS)
                            )))));
    }

    private static Holder.Reference<NormalNoise.NoiseParameters> get(HolderGetter<NormalNoise.NoiseParameters> noise, String name) {
        return noise.getOrThrow(ResourceKey.create(Registries.NOISE, ResourceLocation.withDefaultNamespace(name)));
    }
}
