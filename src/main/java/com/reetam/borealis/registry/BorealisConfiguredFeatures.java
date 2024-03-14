package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.world.gen.foliageplacer.AspenFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.HelixFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.PalmFoliagePlacer;
import com.reetam.borealis.world.gen.trunkplacer.RootedTrunkPlacer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class BorealisConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_GLACIAL_RIDGE = createKey("glacial_ridge");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_GLACIAL_SPIKE = createKey("glacial_spike");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_HOT_SPRING = createKey("hot_spring");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_SPIKE_TRAIL = createKey("spike_trail");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_SPIRAL_CLOUD = createKey("spiral_cloud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_CLOUD = createKey("cloud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_SUGAR_SNOW = createKey("sugar_snow");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_SLATE_BOULDER = createKey("slate_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_BRUMAL_TREE = createKey("brumal_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_TALL_BRUMAL_TREE = createKey("tall_brumal_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_FROSTFIR_TREE = createKey("frostfir_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_GIANTWOOD_TREE = createKey("giantwood_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_HELIX_TREE = createKey("helix_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_COTTON_TREE = createKey("cotton_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_RUBBLE_PATCH = createKey("rubble_patch");

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(BorealisMod.MODID, name));
    }

    @SuppressWarnings("deprecation")
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, CONFIGURED_GLACIAL_RIDGE, BorealisFeatures.GLACIAL_RIDGE.get(), new ColumnFeatureConfiguration(ConstantInt.of(1), UniformInt.of(1, 4)));
        register(context, CONFIGURED_GLACIAL_SPIKE, BorealisFeatures.GLACIAL_SPIKE.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_HOT_SPRING, BorealisFeatures.HOT_SPRING.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_SPIKE_TRAIL, BorealisFeatures.SPIKE_TRAIL.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_SPIRAL_CLOUD, BorealisFeatures.SPIRAL_CLOUD.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_CLOUD, BorealisFeatures.CLOUD.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_SUGAR_SNOW, BorealisFeatures.SUGAR_SNOW.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_SLATE_BOULDER, Feature.FOREST_ROCK, new BlockStateConfiguration(BorealisBlocks.SLATE.get().defaultBlockState()));

        register(context, CONFIGURED_BRUMAL_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.BRUMAL_LOG.get().defaultBlockState()),
                new RootedTrunkPlacer(4, 2, 2),
                BlockStateProvider.simple(BorealisBlocks.BRUMAL_LEAVES.get().defaultBlockState()),
                new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, CONFIGURED_TALL_BRUMAL_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.BRUMAL_LOG.get().defaultBlockState()),
                new RootedTrunkPlacer(6, 2, 2),
                BlockStateProvider.simple(BorealisBlocks.BRUMAL_LEAVES.get().defaultBlockState()),
                new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, CONFIGURED_FROSTFIR_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(8, 2, 2),
                BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LEAVES.get().defaultBlockState()),
                new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, CONFIGURED_GIANTWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LOG.get().defaultBlockState()),
                new GiantTrunkPlacer(12, 2, 16),
                BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LEAVES.get().defaultBlockState()),
                new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(12, 16)),
                new TwoLayersFeatureSize(1, 1, 2))
                .ignoreVines().build());
        register(context, CONFIGURED_HELIX_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.SACCHARINE_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(10, 2, 2),
                BlockStateProvider.simple(BorealisBlocks.SACCHARINE_LEAVES.get().defaultBlockState()),
                new HelixFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, CONFIGURED_COTTON_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.SACCHARINE_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(5, 2, 2),
                BlockStateProvider.simple(BorealisBlocks.SACCHARINE_LEAVES.get().defaultBlockState()),
                new AspenFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());

        register(context, CONFIGURED_RUBBLE_PATCH, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(
                BorealisBlocks.PERMAFROST.get().defaultBlockState(),
                BorealisBlocks.PERMAFROST_RUBBLE.get().defaultBlockState(),
                UniformInt.of(3, 4)));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
