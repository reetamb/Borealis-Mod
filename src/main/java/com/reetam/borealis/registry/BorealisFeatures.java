package com.reetam.borealis.registry;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.util.TextTable;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.world.gen.feature.*;


public class BorealisFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BorealisMod.MODID);

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> borealis_tree = FEATURES.register(
            "borealis_tree", () -> new BorealisTreeFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> brumal_tree = FEATURES.register(
            "brumal_tree", () -> new BrumalTreeFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<ColumnConfig>> glacial_ridge = FEATURES.register(
            "glacial_ridge", () -> new GlacialRidgeFeature(ColumnConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> glacial_spike = FEATURES.register(
            "glacial_spike", () -> new GlacialSpikeFeature(NoFeatureConfig.field_236558_a_));

    public static void registerConfiguredFeatures() {

            register("frostfir_tree", borealis_tree.get().withConfiguration(
                    (new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BorealisBlocks.frostfir_log.get().getDefaultState()),
                            new SimpleBlockStateProvider(BorealisBlocks.frostfir_leaves.get().getDefaultState()),
                            new SpruceFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(2)),
                            new StraightTrunkPlacer(10, 2, 2),
                            new TwoLayerFeature(1, 0, 1)))
                            .setIgnoreVines().build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(8))));

            register("geyser", borealis_tree.get().withConfiguration(
                    (new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BorealisBlocks.pumice_geyser.get().getDefaultState()),
                            new SimpleBlockStateProvider(BorealisBlocks.pumice.get().getDefaultState()),
                            new BushFoliagePlacer(FeatureSpread.func_242252_a(1), FeatureSpread.func_242252_a(1), 2),
                            new StraightTrunkPlacer(1, 0, 0),
                            new TwoLayerFeature(0, 0, 0)))
                            .func_236702_a_(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(1))));

            register("brumal_tree", brumal_tree.get().withConfiguration(
                    IFeatureConfig.NO_FEATURE_CONFIG).chance(6));

            register("brumal_tree_common", brumal_tree.get().withConfiguration(
                        IFeatureConfig.NO_FEATURE_CONFIG).chance(1));

            register("permafrost_rubble_patch",
                    Feature.NETHERRACK_REPLACE_BLOBS.withConfiguration(
                            new BlobReplacementConfig(BorealisBlocks.permafrost.get().getDefaultState(),
                                    BorealisBlocks.permafrost_rubble.get().getDefaultState(),
                                    FeatureSpread.func_242253_a(3, 4))).range(128)
                            .square().func_242731_b(25));

            register("cloud",
                    Feature.ORE.withConfiguration(
                            new OreFeatureConfig(new BlockMatchRuleTest(Blocks.AIR),
                                    BorealisBlocks.cloud.get().getDefaultState(), 33))
                            .range(12).square().func_242731_b(10));

            register("glacial_ridge", glacial_ridge.get().withConfiguration(new ColumnConfig(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(2)))
                    .withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(6))));

            register("glacial_spike", glacial_spike.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
                    .withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(3))));

            register("hot_spring", Feature.DELTA_FEATURE.withConfiguration(
                    new BasaltDeltasFeature(
                            BorealisBlocks.hot_spring_water.get().getDefaultState(),
                            BorealisBlocks.pumice.get().getDefaultState(),
                            FeatureSpread.func_242253_a(2, 3),
                            FeatureSpread.func_242253_a(0, 4)))
                    .withPlacement(
                            Placement.RANGE.configure(new TopSolidRangeConfig(64, 0, 128))
                    .square().func_242731_b(16)));

    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BorealisMod.MODID, name), feature);
    }
}