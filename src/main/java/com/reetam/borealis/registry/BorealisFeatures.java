package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.world.gen.feature.*;
import com.reetam.borealis.world.gen.foliageplacer.AspenFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.HelixFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.PalmFoliagePlacer;
import com.reetam.borealis.world.gen.trunkplacer.RootedTrunkPlacer;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
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
                        new StraightTrunkPlacer(10, 2, 2),
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

        register("geyser", borealis_tree.get().configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.pumice_geyser.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.pumice.get().defaultBlockState()),
                        new BushFoliagePlacer(FeatureSpread.fixed(1), FeatureSpread.fixed(1), 2),
                        new StraightTrunkPlacer(1, 0, 0),
                        new TwoLayerFeature(0, 0, 0)))
                        .heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(4))));

        register("brumal_tree", borealis_tree.get().configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_log.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_leaves.get().defaultBlockState()),
                        new PalmFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new RootedTrunkPlacer(5, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .ignoreVines().build()).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2))));

        register("tall_brumal_tree", borealis_tree.get().configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_log.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_leaves.get().defaultBlockState()),
                        new PalmFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new RootedTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .ignoreVines().build()).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1))));

        register("cotton_tree", borealis_tree.get().configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.saccharine_log.get().defaultBlockState()),
                        new SimpleBlockStateProvider(BorealisBlocks.saccharine_leaves.get().defaultBlockState()),
                        new AspenFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new StraightTrunkPlacer(8, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .ignoreVines().build()).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1))));

        register("permafrost_rubble_patch",
                        Feature.REPLACE_BLOBS.configured(
                                new BlobReplacementConfig(BorealisBlocks.permafrost.get().defaultBlockState(),
                                        BorealisBlocks.permafrost_rubble.get().defaultBlockState(),
                                        FeatureSpread.of(3, 4))).range(128)
                                .squared().count(25));

        register("cloud",
                Feature.ORE.configured(
                        new OreFeatureConfig(new BlockMatchRuleTest(Blocks.AIR),
                                BorealisBlocks.cloud.get().defaultBlockState(), 33))
                        .range(12).squared().count(10));

        register("glacial_ridge", glacial_ridge.get().configured(new ColumnConfig(FeatureSpread.fixed(2), FeatureSpread.fixed(2)))
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(6))));

        register("glacial_spike", glacial_spike.get().configured(IFeatureConfig.NONE)
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(3))));

        register("sugar_claw", claw.get().configured(new BlockStateFeatureConfig(BorealisBlocks.saccharine_wood.get().defaultBlockState()))
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1))));
        register("sugar_shrub", shrub.get().configured(new BlockStateFeatureConfig(BorealisBlocks.saccharine_wood.get().defaultBlockState()))
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1))));

        register("hot_spring", hot_spring.get().configured(IFeatureConfig.NONE)
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1)).chance(2)));

        register(
                "sprinkle_top_layer", sugar_snow.get().configured(
                        IFeatureConfig.NONE));

        register("spike_trail", spike_trail.get().configured(IFeatureConfig.NONE)
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(1))));
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BorealisMod.MODID, name), feature);
    }
}
