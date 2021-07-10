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
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.Placement;
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

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> borealis_tree = FEATURES.register(
            "borealis_tree", () -> new TreeFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<ColumnConfig>> glacial_ridge = FEATURES.register(
            "glacial_ridge", () -> new GlacialRidgeFeature(ColumnConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> glacial_spike = FEATURES.register(
            "glacial_spike", () -> new GlacialSpikeFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> hot_spring = FEATURES.register(
            "hot_spring", () -> new HotSpringFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BlockStateFeatureConfig>> claw = FEATURES.register(
            "claw", () -> new ClawFeature(BlockStateFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BlockStateFeatureConfig>> shrub = FEATURES.register(
            "shrub", () -> new ShrubFeature(BlockStateFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> sugar_snow = FEATURES.register(
            "sprinkle_top_layer", () -> new SugarSnowFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> spike_trail = FEATURES.register(
            "spike_trail", () -> new SpikeTrailFeature(NoFeatureConfig.CODEC));


    public static void registerConfiguredFeatures() {

        register("frostfir_tree", borealis_tree.get().configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.frostfir_log.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.frostfir_leaves.get().defaultBlockState()),
                        new SpruceFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new StraightTrunkPlacer(8, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .ignoreVines().build()).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(8))));

        register("helix_tree", borealis_tree.get().configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.saccharine_log.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.saccharine_leaves.get().defaultBlockState()),
                        new HelixFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new StraightTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                .ignoreVines().build()).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1))));

        register("brumal_tree", borealis_tree.get().configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_log.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_leaves.get().defaultBlockState()),
                        new PalmFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new RootedTrunkPlacer(4, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .ignoreVines().build()).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2))));

        register("tall_brumal_tree", borealis_tree.get().configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_log.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_leaves.get().defaultBlockState()),
                        new PalmFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new RootedTrunkPlacer(6, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .ignoreVines().build()).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1))));

        register("cotton_tree", borealis_tree.get().configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.saccharine_log.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.saccharine_leaves.get().defaultBlockState()),
                        new AspenFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new StraightTrunkPlacer(5, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .ignoreVines().build()).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1))));

        register("permafrost_rubble_patch",
                        Feature.REPLACE_BLOBS.configured(
                                new BlobReplacementConfig(BorealisBlocks.permafrost.get().defaultBlockState(),
                                        BorealisBlocks.permafrost_rubble.get().defaultBlockState(),
                                        FeatureSpread.of(3, 4))).range(128)
                                .squared().count(10));

        register("cloud",
                Feature.ORE.configured(
                        new OreFeatureConfig(new BlockMatchRuleTest(Blocks.AIR),
                                BorealisBlocks.cloud.get().defaultBlockState(), 33))
                        .range(12).squared().count(10));

        register("glacial_ridge", glacial_ridge.get().configured(new ColumnConfig(FeatureSpread.fixed(2), FeatureSpread.fixed(2)))
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(6))));

        register("glacial_spike", glacial_spike.get().configured(IFeatureConfig.NONE)
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(3))));

        register("hot_spring", hot_spring.get().configured(IFeatureConfig.NONE)
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2)).chance(2)));

        register("sprinkle_top_layer", sugar_snow.get().configured(IFeatureConfig.NONE));

        register("spike_trail", spike_trail.get().configured(IFeatureConfig.NONE)
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1))));

        // add cyanocoral features to hot springs to act as a shrub-like space filler
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BorealisMod.MODID, name), feature);
    }

    public static class TreePlacers {
        public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, BorealisMod.MODID);

        public static final RegistryObject<FoliagePlacerType<FoliagePlacer>> PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("palm_foliage_placer", () -> new FoliagePlacerType(PalmFoliagePlacer.CODEC));
        public static final RegistryObject<FoliagePlacerType<FoliagePlacer>> ASPEN_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("aspen_foliage_placer", () -> new FoliagePlacerType(AspenFoliagePlacer.CODEC));
        public static final RegistryObject<FoliagePlacerType<FoliagePlacer>> HELIX_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("helix_foliage_placer", () -> new FoliagePlacerType(HelixFoliagePlacer.CODEC));

        public static final TrunkPlacerType<RootedTrunkPlacer> ROOTED_TRUNK_PLACER = register("rooted_trunk_placer", RootedTrunkPlacer.CODEC);

        private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {
            return Registry.register(Registry.TRUNK_PLACER_TYPES, new ResourceLocation(BorealisMod.MODID, name), new TrunkPlacerType<>(codec));
        }
    }

    public static class SurfaceBuilders {
        public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, BorealisMod.MODID);

        public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> DEEP_TOP = SURFACE_BUILDERS.register("deep_top", () -> new DeepTopSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    }
}
