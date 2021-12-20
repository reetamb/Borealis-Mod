package com.reetam.borealis.registry;

import com.mojang.serialization.Codec;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.world.gen.feature.*;
import com.reetam.borealis.world.gen.foliageplacer.AspenFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.HelixFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.PalmFoliagePlacer;
import com.reetam.borealis.world.gen.surfacebuilder.DeepTopSurfaceBuilder;
import com.reetam.borealis.world.gen.trunkplacer.RootedTrunkPlacer;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderConfiguration;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class BorealisFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BorealisMod.MODID);

    public static final RegistryObject<Feature<ColumnFeatureConfiguration>> GLACIAL_RIDGE = FEATURES.register(
            "glacial_ridge", () -> new GlacialRidgeFeature(ColumnFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> GLACIAL_SPIKE = FEATURES.register(
            "glacial_spike", () -> new GlacialSpikeFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> HOT_SPRING = FEATURES.register(
            "hot_spring", () -> new HotSpringFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SUGAR_SNOW = FEATURES.register(
            "sprinkle_top_layer", () -> new SugarSnowFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SPIKE_TRAIL = FEATURES.register(
            "spike_trail", () -> new SpikeTrailFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SPIRAL_CLOUD = FEATURES.register(
            "spiral_cloud", () -> new SpiralCloudFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CLOUD = FEATURES.register(
            "cloud", () -> new CloudFeature(NoneFeatureConfiguration.CODEC)
    );

    public static class TreePlacers {
        public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, BorealisMod.MODID);

        public static final RegistryObject<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("palm_foliage_placer", () -> new FoliagePlacerType<PalmFoliagePlacer>(PalmFoliagePlacer.CODEC));
        public static final RegistryObject<FoliagePlacerType<AspenFoliagePlacer>> ASPEN_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("aspen_foliage_placer", () -> new FoliagePlacerType<AspenFoliagePlacer>(AspenFoliagePlacer.CODEC));
        public static final RegistryObject<FoliagePlacerType<HelixFoliagePlacer>> HELIX_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("helix_foliage_placer", () -> new FoliagePlacerType<HelixFoliagePlacer>(HelixFoliagePlacer.CODEC));

        public static final TrunkPlacerType<RootedTrunkPlacer> ROOTED_TRUNK_PLACER = register("rooted_trunk_placer", RootedTrunkPlacer.CODEC);

        private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {
            return Registry.register(Registry.TRUNK_PLACER_TYPES, new ResourceLocation(BorealisMod.MODID, name), new TrunkPlacerType<>(codec));
        }
    }

    public static class SurfaceBuilders {
        public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, BorealisMod.MODID);

        public static final RegistryObject<DeepTopSurfaceBuilder> DEEP_TOP = SURFACE_BUILDERS.register("deep_top", () -> new DeepTopSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC));
    }

    public static class Configured {

        public static final ConfiguredFeature<TreeConfiguration, ?> BRUMAL_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        new SimpleStateProvider(BorealisBlocks.BRUMAL_LOG.get().defaultBlockState()),
                        new RootedTrunkPlacer(4, 2, 2),
                        new SimpleStateProvider(BorealisBlocks.BRUMAL_LEAVES.get().defaultBlockState()),
                        new SimpleStateProvider(BorealisBlocks.BRUMAL_SAPLING.get().defaultBlockState()),
                        new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<TreeConfiguration, ?> TALL_BRUMAL_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        new SimpleStateProvider(BorealisBlocks.BRUMAL_LOG.get().defaultBlockState()),
                        new RootedTrunkPlacer(6, 2, 2),
                        new SimpleStateProvider(BorealisBlocks.BRUMAL_LEAVES.get().defaultBlockState()),
                        new SimpleStateProvider(BorealisBlocks.BRUMAL_SAPLING.get().defaultBlockState()),
                        new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<TreeConfiguration, ?> FROSTFIR_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        new SimpleStateProvider(BorealisBlocks.FROSTFIR_LOG.get().defaultBlockState()),
                        new StraightTrunkPlacer(8, 2, 2),
                        new SimpleStateProvider(BorealisBlocks.FROSTFIR_LEAVES.get().defaultBlockState()),
                        new SimpleStateProvider(BorealisBlocks.FROSTFIR_SAPLING.get().defaultBlockState()),
                        new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<TreeConfiguration, ?> HELIX_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        new SimpleStateProvider(BorealisBlocks.SACCHARINE_LOG.get().defaultBlockState()),
                        new StraightTrunkPlacer(10, 2, 2),
                        new SimpleStateProvider(BorealisBlocks.SACCHARINE_LEAVES.get().defaultBlockState()),
                        new SimpleStateProvider(BorealisBlocks.SACCHARINE_SAPLING.get().defaultBlockState()),
                        new HelixFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<TreeConfiguration, ?> COTTON_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        new SimpleStateProvider(BorealisBlocks.SACCHARINE_LOG.get().defaultBlockState()),
                        new StraightTrunkPlacer(5, 2, 2),
                        new SimpleStateProvider(BorealisBlocks.SACCHARINE_LEAVES.get().defaultBlockState()),
                        new SimpleStateProvider(BorealisBlocks.SACCHARINE_SAPLING.get().defaultBlockState()),
                        new AspenFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<?, ?> CONFIGURED_RUBBLE_PATCH = Feature.REPLACE_BLOBS.configured(
                new ReplaceSphereConfiguration(
                        BorealisBlocks.PERMAFROST.get().defaultBlockState(),
                        BorealisBlocks.PERMAFROST_RUBBLE.get().defaultBlockState(),
                        UniformInt.of(3, 4))).rangeUniform(VerticalAnchor.aboveBottom(64), VerticalAnchor.belowTop(64))
                .squared().count(10);

        public static final ConfiguredFeature<?, ?> CONFIGURED_FROSTFIR_TREE = FROSTFIR_TREE.decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(8);
        public static final ConfiguredFeature<?, ?> CONFIGURED_HELIX_TREE = HELIX_TREE.decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(1);
        public static final ConfiguredFeature<?, ?> CONFIGURED_BRUMAL_TREE = BRUMAL_TREE.decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(2);
        public static final ConfiguredFeature<?, ?> CONFIGURED_TALL_BRUMAL_TREE = TALL_BRUMAL_TREE.decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(1);
        public static final ConfiguredFeature<?, ?> CONFIGURED_COTTON_TREE = COTTON_TREE.decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(1);

        public static final ConfiguredFeature<?, ?> CONFIGURED_GLACIAL_RIDGE = GLACIAL_RIDGE.get().configured(new ColumnFeatureConfiguration(ConstantInt.of(2), ConstantInt.of(2)))
                .decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(6);

        public static final ConfiguredFeature<?, ?> CONFIGURED_GLACIAL_SPIKE = GLACIAL_SPIKE.get().configured(FeatureConfiguration.NONE)
                .decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(3);

        public static final ConfiguredFeature<?, ?> CONFIGURED_HOT_SPRING = HOT_SPRING.get().configured(FeatureConfiguration.NONE)
                .count(1);

        public static final ConfiguredFeature<?, ?> CONFIGURED_SPIKE_TRAIL = SPIKE_TRAIL.get().configured(FeatureConfiguration.NONE)
                .decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(1);

        public static final ConfiguredFeature<?, ?> CONFIGURED_SPIRAL_CLOUD = SPIRAL_CLOUD.get().configured(FeatureConfiguration.NONE)
                .count(2);

        public static final ConfiguredFeature<?, ?> CONFIGURED_CLOUD = CLOUD.get().configured(FeatureConfiguration.NONE)
                .rangeUniform(VerticalAnchor.aboveBottom(24), VerticalAnchor.aboveBottom(60)).count(4);
    }

    public static void registerConfiguredFeatures() {
        register("frostfir_tree", Configured.CONFIGURED_FROSTFIR_TREE);
        register("helix_tree", Configured.CONFIGURED_HELIX_TREE);
        register("brumal_tree", Configured.CONFIGURED_BRUMAL_TREE);
        register("tall_brumal_tree", Configured.CONFIGURED_TALL_BRUMAL_TREE);
        register("cotton_tree", Configured.CONFIGURED_COTTON_TREE);
        register("permafrost_rubble_patch", Configured.CONFIGURED_RUBBLE_PATCH);
        register("cloud", Configured.CONFIGURED_CLOUD);
        register("glacial_ridge", Configured.CONFIGURED_GLACIAL_RIDGE);
        register("glacial_spike", Configured.CONFIGURED_GLACIAL_SPIKE);
        register("hot_spring", Configured.CONFIGURED_HOT_SPRING);
        register("sprinkle_top_layer", SUGAR_SNOW.get().configured(FeatureConfiguration.NONE));
        register("spike_trail", Configured.CONFIGURED_SPIKE_TRAIL);
        register("spiral_cloud", Configured.CONFIGURED_SPIRAL_CLOUD);
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BorealisMod.MODID, name), feature);
    }
}
