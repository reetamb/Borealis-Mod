package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.world.gen.foliageplacer.AspenFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.HelixFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.PalmFoliagePlacer;
import com.reetam.borealis.world.gen.trunkplacer.RootedTrunkPlacer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class BorealisWorldgen extends BorealisFeatures {

    public static class Configured {

        public static final Holder<ConfiguredFeature<ColumnFeatureConfiguration, ?>> CONFIGURED_GLACIAL_RIDGE = configure("configured_glacial_ridge", GLACIAL_RIDGE.get(), new ColumnFeatureConfiguration(ConstantInt.of(1), UniformInt.of(1, 4)));
        public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_GLACIAL_SPIKE = configure("configured_glacial_spike", GLACIAL_SPIKE.get(), FeatureConfiguration.NONE);
        public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_HOT_SPRING = configure("configured_hot_spring", HOT_SPRING.get(), FeatureConfiguration.NONE);
        public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_SPIKE_TRAIL = configure("configured_spike_trail", SPIKE_TRAIL.get(), FeatureConfiguration.NONE);
        public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_SPIRAL_CLOUD = configure("configured_spiral_cloud", SPIRAL_CLOUD.get(), FeatureConfiguration.NONE);
        public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_CLOUD = configure("configured_cloud", CLOUD.get(), FeatureConfiguration.NONE);
        public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_SUGAR_SNOW = configure("configured_sugar_snow", SUGAR_SNOW.get(), FeatureConfiguration.NONE);

        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CONFIGURED_BRUMAL_TREE = configure("configured_brumal_tree", Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BorealisBlocks.BRUMAL_LOG.get().defaultBlockState()),
                        new RootedTrunkPlacer(4, 2, 2),
                        BlockStateProvider.simple(BorealisBlocks.BRUMAL_LEAVES.get().defaultBlockState()),
                        new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CONFIGURED_TALL_BRUMAL_TREE = configure("configured_tall_brumal_tree", Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BorealisBlocks.BRUMAL_LOG.get().defaultBlockState()),
                        new RootedTrunkPlacer(6, 2, 2),
                        BlockStateProvider.simple(BorealisBlocks.BRUMAL_LEAVES.get().defaultBlockState()),
                        new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CONFIGURED_FROSTFIR_TREE = configure("configured_frostfir_tree", Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LOG.get().defaultBlockState()),
                        new StraightTrunkPlacer(8, 2, 2),
                        BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LEAVES.get().defaultBlockState()),
                        new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CONFIGURED_GIANTWOOD_TREE = configure("configured_giantwood_tree", Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LOG.get().defaultBlockState()),
                        new GiantTrunkPlacer(12, 2, 16),
                        BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LEAVES.get().defaultBlockState()),
                        new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(12, 16)),
                        new TwoLayersFeatureSize(1, 1, 2))
                        .ignoreVines().build());

        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CONFIGURED_HELIX_TREE = configure("configured_helix_tree", Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BorealisBlocks.SACCHARINE_LOG.get().defaultBlockState()),
                        new StraightTrunkPlacer(10, 2, 2),
                        BlockStateProvider.simple(BorealisBlocks.SACCHARINE_LEAVES.get().defaultBlockState()),
                        new HelixFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CONFIGURED_COTTON_TREE = configure("configured_cotton", Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BorealisBlocks.SACCHARINE_LOG.get().defaultBlockState()),
                        new StraightTrunkPlacer(5, 2, 2),
                        BlockStateProvider.simple(BorealisBlocks.SACCHARINE_LEAVES.get().defaultBlockState()),
                        new AspenFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final Holder<ConfiguredFeature<ReplaceSphereConfiguration, ?>> CONFIGURED_RUBBLE_PATCH = configure("configured_rubble_patch", Feature.REPLACE_BLOBS,
                new ReplaceSphereConfiguration(
                        BorealisBlocks.PERMAFROST.get().defaultBlockState(),
                        BorealisBlocks.PERMAFROST_RUBBLE.get().defaultBlockState(),
                        UniformInt.of(3, 4)));
    }

    public static class Placed {

        private static final BlockPredicateFilter PLANT = BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO));
        public static final Holder<PlacedFeature> PLACED_GLACIAL_RIDGE = place("placed_glacial_ridge", Configured.CONFIGURED_GLACIAL_RIDGE,
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)),
                CountOnEveryLayerPlacement.of(4), BiomeFilter.biome());
        public static final Holder<PlacedFeature> PLACED_GLACIAL_SPIKE = place("placed_glacial_spike", Configured.CONFIGURED_GLACIAL_SPIKE,
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)),
                InSquarePlacement.spread(), CountPlacement.of(4), BiomeFilter.biome());
        public static final Holder<PlacedFeature> PLACED_HOT_SPRING = place("placed_hot_spring", Configured.CONFIGURED_HOT_SPRING,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(128)),
                InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(3), BiomeFilter.biome());
        public static final Holder<PlacedFeature> PLACED_SPIKE_TRAIL = place("placed_spike_trail", Configured.CONFIGURED_HOT_SPRING,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(128)),
                RarityFilter.onAverageOnceEvery(2));
        public static final Holder<PlacedFeature> PLACED_SPIRAL_CLOUD = place("placed_spiral_cloud", Configured.CONFIGURED_SPIRAL_CLOUD,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(128)),
                InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(4));
        public static final Holder<PlacedFeature> PLACED_CLOUD = place("placed_cloud", Configured.CONFIGURED_CLOUD,
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(32)),
                RarityFilter.onAverageOnceEvery(4));
        public static final Holder<PlacedFeature> PLACED_SUGAR_SNOW = place("placed_sugar_snow", Configured.CONFIGURED_SUGAR_SNOW,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(16), VerticalAnchor.absolute(80)));

        public static final Holder<PlacedFeature> PLACED_BRUMAL_TREE = place("placed_brumal_tree", Configured.CONFIGURED_BRUMAL_TREE,
                List.of(CountOnEveryLayerPlacement.of(2), BiomeFilter.biome(), PLANT));
        public static final Holder<PlacedFeature> PLACED_TALL_BRUMAL_TREE = place("placed_tall_brumal_tree", Configured.CONFIGURED_TALL_BRUMAL_TREE,
                List.of(CountOnEveryLayerPlacement.of(1), BiomeFilter.biome(), PLANT));
        public static final Holder<PlacedFeature> PLACED_FROSTFIR_TREE = place("placed_frostfir_tree", Configured.CONFIGURED_FROSTFIR_TREE,
                List.of(CountOnEveryLayerPlacement.of(8), BiomeFilter.biome(), PLANT));
        public static final Holder<PlacedFeature> PLACED_GREAT_FROSTFIR_TREE = place("placed_great_frostfir_tree", Configured.CONFIGURED_FROSTFIR_TREE,
                List.of(CountOnEveryLayerPlacement.of(4), BiomeFilter.biome(), PLANT));
        public static final Holder<PlacedFeature> PLACED_GIANTWOOD_TREE = place("placed_giantwood_tree", Configured.CONFIGURED_GIANTWOOD_TREE,
                List.of(CountOnEveryLayerPlacement.of(4), BiomeFilter.biome(), PLANT));
        public static final Holder<PlacedFeature> PLACED_HELIX_TREE = place("placed_helix_tree", Configured.CONFIGURED_HELIX_TREE,
                List.of(CountOnEveryLayerPlacement.of(1), BiomeFilter.biome(), PLANT));
        public static final Holder<PlacedFeature> PLACED_COTTON_TREE = place("placed_cotton_tree", Configured.CONFIGURED_COTTON_TREE,
                List.of(CountOnEveryLayerPlacement.of(1), BiomeFilter.biome(), PLANT));

        public static final Holder<PlacedFeature> PLACED_RUBBLE_PATCH = place("placed_rubble_patch", Configured.CONFIGURED_RUBBLE_PATCH,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)),
                InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(2));
    }

    public static void registerPlacedFeatures() {
        register("brumal_tree", Placed.PLACED_BRUMAL_TREE);
        register("frostfir_tree", Placed.PLACED_FROSTFIR_TREE);
        register("great_frostfir_tree", Placed.PLACED_GREAT_FROSTFIR_TREE);
        register("giantwood_tree", Placed.PLACED_GIANTWOOD_TREE);
        register("helix_tree", Placed.PLACED_HELIX_TREE);
        register("tall_brumal_tree", Placed.PLACED_TALL_BRUMAL_TREE);
        register("cotton_tree", Placed.PLACED_COTTON_TREE);
        register("permafrost_rubble_patch", Placed.PLACED_RUBBLE_PATCH);
        register("cloud", Placed.PLACED_CLOUD);
        register("glacial_ridge", Placed.PLACED_GLACIAL_RIDGE);
        register("glacial_spike", Placed.PLACED_GLACIAL_SPIKE);
        register("hot_spring", Placed.PLACED_HOT_SPRING);
        register("sprinkle_top_layer", Placed.PLACED_SUGAR_SNOW);
        register("spike_trail", Placed.PLACED_SPIKE_TRAIL);
        register("spiral_cloud", Placed.PLACED_SPIRAL_CLOUD);
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> configure(String name, F feature, FC configuration) {
        return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BorealisMod.MODID, name).toString(), new ConfiguredFeature<>(feature, configuration));
    }

    public static Holder<PlacedFeature> place(String name, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(new ResourceLocation(BorealisMod.MODID, name).toString(), configuredFeature, List.of(placementModifiers));
    }

    public static Holder<PlacedFeature> place(String name, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> placementModifiers) {
        return PlacementUtils.register(new ResourceLocation(BorealisMod.MODID, name).toString(), configuredFeature, placementModifiers);
    }

    private static PlacedFeature register(String name, Holder<PlacedFeature> feature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BorealisMod.MODID, name), feature.value());
    }
}
