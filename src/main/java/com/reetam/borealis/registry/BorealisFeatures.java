package com.reetam.borealis.registry;

import com.mojang.serialization.Codec;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.world.gen.feature.*;
import com.reetam.borealis.world.gen.foliageplacer.AspenFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.HelixFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.PalmFoliagePlacer;
import com.reetam.borealis.world.gen.trunkplacer.RootedTrunkPlacer;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
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
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

    public static class Configured {

        public static final ConfiguredFeature<TreeConfiguration, ?> CONFIGURED_BRUMAL_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BorealisBlocks.BRUMAL_LOG.get().defaultBlockState()),
                        new RootedTrunkPlacer(4, 2, 2),
                        BlockStateProvider.simple(BorealisBlocks.BRUMAL_LEAVES.get().defaultBlockState()),
                        new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<TreeConfiguration, ?> CONFIGURED_TALL_BRUMAL_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BorealisBlocks.BRUMAL_LOG.get().defaultBlockState()),
                        new RootedTrunkPlacer(6, 2, 2),
                        BlockStateProvider.simple(BorealisBlocks.BRUMAL_LEAVES.get().defaultBlockState()),
                        new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<TreeConfiguration, ?> CONFIGURED_FROSTFIR_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LOG.get().defaultBlockState()),
                        new StraightTrunkPlacer(8, 2, 2),
                        BlockStateProvider.simple(BorealisBlocks.FROSTFIR_LEAVES.get().defaultBlockState()),
                        new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<TreeConfiguration, ?> CONFIGURED_HELIX_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BorealisBlocks.SACCHARINE_LOG.get().defaultBlockState()),
                        new StraightTrunkPlacer(10, 2, 2),
                        BlockStateProvider.simple(BorealisBlocks.SACCHARINE_LEAVES.get().defaultBlockState()),
                        new HelixFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<TreeConfiguration, ?> CONFIGURED_COTTON_TREE = Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BorealisBlocks.SACCHARINE_LOG.get().defaultBlockState()),
                        new StraightTrunkPlacer(5, 2, 2),
                        BlockStateProvider.simple(BorealisBlocks.SACCHARINE_LEAVES.get().defaultBlockState()),
                        new AspenFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<?, ?> CONFIGURED_RUBBLE_PATCH = Feature.REPLACE_BLOBS.configured(
                new ReplaceSphereConfiguration(
                        BorealisBlocks.PERMAFROST.get().defaultBlockState(),
                        BorealisBlocks.PERMAFROST_RUBBLE.get().defaultBlockState(),
                        UniformInt.of(3, 4)));
    }

    public static class Placed {
        public static final PlacedFeature PLACED_FROSTFIR_TREE = Configured.CONFIGURED_FROSTFIR_TREE.placed(VegetationPlacements.treePlacement(PlacementUtils.countExtra(8, 0.1F, 1)));
        public static final PlacedFeature PLACED_HELIX_TREE = Configured.CONFIGURED_HELIX_TREE.placed(VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));
        public static final PlacedFeature PLACED_BRUMAL_TREE = Configured.CONFIGURED_BRUMAL_TREE.placed(VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.1F, 1)));
        public static final PlacedFeature PLACED_TALL_BRUMAL_TREE = Configured.CONFIGURED_TALL_BRUMAL_TREE.placed(VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));
        public static final PlacedFeature PLACED_COTTON_TREE = Configured.CONFIGURED_COTTON_TREE.placed(VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));

        public static final PlacedFeature PLACED_GLACIAL_RIDGE = GLACIAL_RIDGE.get().configured(new ColumnFeatureConfiguration(ConstantInt.of(2), ConstantInt.of(2)))
                .placed(HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)),
                        InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(6));
        public static final PlacedFeature PLACED_GLACIAL_SPIKE = GLACIAL_SPIKE.get().configured(FeatureConfiguration.NONE)
                .placed(HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)),
                        InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(6));
        public static final PlacedFeature PLACED_RUBBLE_PATCH = Configured.CONFIGURED_RUBBLE_PATCH
                .placed(HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)),
                        InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(4));

        public static final PlacedFeature PLACED_HOT_SPRING = HOT_SPRING.get().configured(FeatureConfiguration.NONE)
                .placed(HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(128)),
                        InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(1));
        public static final PlacedFeature PLACED_SPIKE_TRAIL = SPIKE_TRAIL.get().configured(FeatureConfiguration.NONE)
                .placed(HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(128)),
                        InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(1));

        public static final PlacedFeature PLACED_SPIRAL_CLOUD = SPIRAL_CLOUD.get().configured(FeatureConfiguration.NONE)
                .placed(HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(128)),
                        InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(1));
        public static final PlacedFeature PLACED_CLOUD = CLOUD.get().configured(FeatureConfiguration.NONE)
                .placed(HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(32)),
                        RarityFilter.onAverageOnceEvery(4));

        public static final PlacedFeature PLACED_SUGAR_SNOW = SUGAR_SNOW.get().configured(FeatureConfiguration.NONE)
                .placed(HeightRangePlacement.uniform(VerticalAnchor.absolute(16), VerticalAnchor.absolute(80)));
    }

    public static void registerPlacedFeatures() {
        register("brumal_tree", Placed.PLACED_BRUMAL_TREE);
        register("frostfir_tree", Placed.PLACED_FROSTFIR_TREE);
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

    private static PlacedFeature register(String name, PlacedFeature feature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BorealisMod.MODID, name), feature);
    }
}
