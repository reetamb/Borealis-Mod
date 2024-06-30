package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.world.foliageplacer.AspenFoliagePlacer;
import com.reetam.borealis.world.foliageplacer.HelixFoliagePlacer;
import com.reetam.borealis.world.foliageplacer.PalmFoliagePlacer;
import com.reetam.borealis.world.trunkplacer.RootedTrunkPlacer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class BorealisConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_GLACIAL_RIDGE = createKey("glacial_ridge");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_GLACIAL_SPIKE = createKey("glacial_spike");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_HOT_SPRING = createKey("hot_spring");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_SPIKE_TRAIL = createKey("spike_trail");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_SPIRAL_CLOUD = createKey("spiral_cloud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_CLOUD = createKey("cloud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_SUGAR_SNOW = createKey("sugar_snow");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_SLATE_BOULDER = createKey("slate_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRUMAL_TREE = createKey("brumal_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_BRUMAL_TREE = createKey("tall_brumal_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROSTFIR_TREE = createKey("frostfir_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANTWOOD_TREE = createKey("giantwood_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HELIX_TREE = createKey("helix_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COTTON_TREE = createKey("cotton_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLAZED_HELIX_TREE = createKey("glazed_helix_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLAZED_COTTON_TREE = createKey("glazed_cotton_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLLY_PATCH = createKey("holly_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICHEN_PATCH = createKey("lichen_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBBLE_PATCH = createKey("rubble_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KYANITE_CRYSTAL = createKey("kyanite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAT_ORE = createKey("peat_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_TANZANITE_METEOR = createKey("tanzanite_meteor");

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(BorealisMod.MODID, name));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, CONFIGURED_GLACIAL_RIDGE, BorealisFeatures.GLACIAL_RIDGE.get(), new ColumnFeatureConfiguration(ConstantInt.of(1), UniformInt.of(1, 4)));
        register(context, CONFIGURED_GLACIAL_SPIKE, BorealisFeatures.GLACIAL_SPIKE.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_HOT_SPRING, BorealisFeatures.HOT_SPRING.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_SPIKE_TRAIL, BorealisFeatures.SPIKE_TRAIL.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_SPIRAL_CLOUD, BorealisFeatures.SPIRAL_CLOUD.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_CLOUD, BorealisFeatures.CLOUD.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_SUGAR_SNOW, BorealisFeatures.SUGAR_SNOW.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_SLATE_BOULDER, Feature.FOREST_ROCK, new BlockStateConfiguration(BorealisBlocks.SLATE.get().defaultBlockState()));

        register(context, BRUMAL_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.BRUMAL_LOG.get().defaultBlockState()),
                new RootedTrunkPlacer(4, 2, 2),
                BlockStateProvider.simple(BorealisBlocks.BRUMAL_LEAVES.get().defaultBlockState()),
                new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, TALL_BRUMAL_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.BRUMAL_LOG.get().defaultBlockState()),
                new RootedTrunkPlacer(6, 2, 2),
                BlockStateProvider.simple(BorealisBlocks.BRUMAL_LEAVES.get().defaultBlockState()),
                new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, FROSTFIR_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(8, 2, 2),
                BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LEAVES.get().defaultBlockState()),
                new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, GIANTWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LOG.get().defaultBlockState()),
                new GiantTrunkPlacer(12, 2, 16),
                BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LEAVES.get().defaultBlockState()),
                new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(12, 16)),
                new TwoLayersFeatureSize(1, 1, 2))
                .ignoreVines().build());
        register(context, HELIX_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.SWEETWOOD_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(10, 2, 2),
                BlockStateProvider.simple(BorealisBlocks.SWEETWOOD_LEAVES.get().defaultBlockState()),
                new HelixFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, COTTON_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.SWEETWOOD_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(5, 2, 2),
                BlockStateProvider.simple(BorealisBlocks.SWEETWOOD_LEAVES.get().defaultBlockState()),
                new AspenFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, GLAZED_HELIX_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.SWEETWOOD_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(10, 2, 2),
                BlockStateProvider.simple(BorealisBlocks.GLAZED_LEAVES.get().defaultBlockState()),
                new HelixFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, GLAZED_COTTON_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BorealisBlocks.SWEETWOOD_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(6, 1, 1),
                BlockStateProvider.simple(BorealisBlocks.GLAZED_LEAVES.get().defaultBlockState()),
                new AspenFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());

        register(context, HOLLY_PATCH, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(64, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BorealisBlocks.HOLLY.get())))));
        register(context, LICHEN_PATCH, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(
                BorealisBlocks.LIVING_SNOW_BLOCK.get().defaultBlockState(),
                BorealisBlocks.LICHEN_BLOCK.get().defaultBlockState(),
                UniformInt.of(1, 2)
        ));
        register(context, RUBBLE_PATCH, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(
                BorealisBlocks.PERMAFROST.get().defaultBlockState(),
                BorealisBlocks.PERMAFROST_RUBBLE.get().defaultBlockState(),
                UniformInt.of(3, 4)));
        register(context, KYANITE_CRYSTAL, BorealisFeatures.BERYL.get(), new BlockStateConfiguration(BorealisBlocks.KYANITE_ORE.get().defaultBlockState()));
        register(context, PEAT_ORE, Feature.ORE, new OreConfiguration(
                new BlockMatchTest(BorealisBlocks.PERMAFROST.get()), BorealisBlocks.PEAT.get().defaultBlockState(), 16, 0.5F));
        register(context, CONFIGURED_TANZANITE_METEOR, BorealisFeatures.TANZANITE_METEOR.get(), FeatureConfiguration.NONE);
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
