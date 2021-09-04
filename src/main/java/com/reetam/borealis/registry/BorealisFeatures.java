package com.reetam.borealis.registry;

import com.mojang.serialization.Codec;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.world.gen.feature.*;
import com.reetam.borealis.world.gen.foliageplacer.AspenFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.HelixFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.PalmFoliagePlacer;
import com.reetam.borealis.world.gen.surfacebuilder.DeepTopSurfaceBuilder;
import com.reetam.borealis.world.gen.trunkplacer.RootedTrunkPlacer;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class BorealisFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BorealisMod.MODID);

    public static final RegistryObject<Feature<ColumnConfig>> GLACIAL_RIDGE = FEATURES.register(
            "glacial_ridge", () -> new GlacialRidgeFeature(ColumnConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> GLACIAL_SPIKE = FEATURES.register(
            "glacial_spike", () -> new GlacialSpikeFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> HOT_SPRING = FEATURES.register(
            "hot_spring", () -> new HotSpringFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> SUGAR_SNOW = FEATURES.register(
            "sprinkle_top_layer", () -> new SugarSnowFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> SPIKE_TRAIL = FEATURES.register(
            "spike_trail", () -> new SpikeTrailFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> SPIRAL_CLOUD = FEATURES.register(
            "spiral_cloud", () -> new SpiralCloudFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> CLOUD = FEATURES.register(
            "cloud", () -> new CloudFeature(NoFeatureConfig.CODEC)
    );

    public static class TreePlacers {
        public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, BorealisMod.MODID);

        public static final RegistryObject<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("palm_foliage_placer", () -> new FoliagePlacerType<PalmFoliagePlacer>(PalmFoliagePlacer.CODEC));
        public static final RegistryObject<FoliagePlacerType<AspenFoliagePlacer>> ASPEN_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("aspen_foliage_placer", () -> new FoliagePlacerType<AspenFoliagePlacer>(AspenFoliagePlacer.CODEC));
        public static final RegistryObject<FoliagePlacerType<HelixFoliagePlacer>> HELIX_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("helix_foliage_placer", () -> new FoliagePlacerType<HelixFoliagePlacer>(HelixFoliagePlacer.CODEC));

        public static final TrunkPlacerType<RootedTrunkPlacer> ROOTED_TRUNK_PLACER = register("rooted_trunk_placer", RootedTrunkPlacer.CODEC);

        private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {
            return Registry.register(Registry.TRUNK_PLACER_TYPES, new ResourceLocation(BorealisMod.MODID, name), new TrunkPlacerType<>(codec));
        }
    }

    public static class SurfaceBuilders {
        public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, BorealisMod.MODID);

        public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> DEEP_TOP = SURFACE_BUILDERS.register("deep_top", () -> new DeepTopSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    }

    public static class Configured {

        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BRUMAL_TREE = Feature.TREE.configured(
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.BRUMAL_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.BRUMAL_LEAVES.get().defaultBlockState()),
                        new PalmFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new RootedTrunkPlacer(4, 2, 2),
                        new TwoLayerFeature(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> TALL_BRUMAL_TREE = Feature.TREE.configured(
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.BRUMAL_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.BRUMAL_LEAVES.get().defaultBlockState()),
                        new PalmFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new RootedTrunkPlacer(6, 2, 2),
                        new TwoLayerFeature(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FROSTFIR_TREE = Feature.TREE.configured(
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.FROSTFIR_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.FROSTFIR_LEAVES.get().defaultBlockState()),
                        new SpruceFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new StraightTrunkPlacer(8, 2, 2),
                        new TwoLayerFeature(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> HELIX_TREE = Feature.TREE.configured(
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.SACCHARINE_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.SACCHARINE_LEAVES.get().defaultBlockState()),
                        new HelixFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new StraightTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> COTTON_TREE = Feature.TREE.configured(
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.SACCHARINE_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.SACCHARINE_LEAVES.get().defaultBlockState()),
                        new AspenFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new StraightTrunkPlacer(5, 2, 2),
                        new TwoLayerFeature(1, 0, 1))
                        .ignoreVines().build());

        public static final ConfiguredFeature<?, ?> CONFIGURED_RUBBLE_PATCH = Feature.REPLACE_BLOBS.configured(
                new BlobReplacementConfig(
                        BorealisBlocks.PERMAFROST.get().defaultBlockState(),
                        BorealisBlocks.PERMAFROST_RUBBLE.get().defaultBlockState(),
                        FeatureSpread.of(3, 4))).range(128)
                .squared().count(10);

        public static final ConfiguredFeature<?, ?> CONFIGURED_FROSTFIR_TREE = FROSTFIR_TREE.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(8)));
        public static final ConfiguredFeature<?, ?> CONFIGURED_HELIX_TREE = HELIX_TREE.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1)));
        public static final ConfiguredFeature<?, ?> CONFIGURED_BRUMAL_TREE = BRUMAL_TREE.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2)));
        public static final ConfiguredFeature<?, ?> CONFIGURED_TALL_BRUMAL_TREE = TALL_BRUMAL_TREE.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1)));
        public static final ConfiguredFeature<?, ?> CONFIGURED_COTTON_TREE = COTTON_TREE.decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1)));

        public static final ConfiguredFeature<?, ?> CONFIGURED_GLACIAL_RIDGE = GLACIAL_RIDGE.get().configured(new ColumnConfig(FeatureSpread.fixed(2), FeatureSpread.fixed(2)))
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(6)));

        public static final ConfiguredFeature<?, ?> CONFIGURED_GLACIAL_SPIKE = GLACIAL_SPIKE.get().configured(IFeatureConfig.NONE)
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(3)));

        public static final ConfiguredFeature<?, ?> CONFIGURED_HOT_SPRING = HOT_SPRING.get().configured(IFeatureConfig.NONE)
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1)));

        public static final ConfiguredFeature<?, ?> CONFIGURED_SPIKE_TRAIL = SPIKE_TRAIL.get().configured(IFeatureConfig.NONE)
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1)));

        public static final ConfiguredFeature<?, ?> CONFIGURED_SPIRAL_CLOUD = SPIRAL_CLOUD.get().configured(IFeatureConfig.NONE)
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1)).chance(8));

        public static final ConfiguredFeature<?, ?> CONFIGURED_CLOUD = CLOUD.get().configured(IFeatureConfig.NONE)
                .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(50, 0, 12))
                .count(4));
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
        register("sprinkle_top_layer", SUGAR_SNOW.get().configured(IFeatureConfig.NONE));
        register("spike_trail", Configured.CONFIGURED_SPIKE_TRAIL);
        register("spiral_cloud", Configured.CONFIGURED_SPIRAL_CLOUD);
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BorealisMod.MODID, name), feature);
    }
}
