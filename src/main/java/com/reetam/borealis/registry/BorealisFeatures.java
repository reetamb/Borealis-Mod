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
import net.minecraft.world.gen.placement.ChanceConfig;
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
            "glacial_spike", () -> new GlacialSpikeFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<NoFeatureConfig>> hot_spring = FEATURES.register(
            "hot_spring", () -> new HotSpringFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<BlockStateFeatureConfig>> claw = FEATURES.register(
            "claw", () -> new ClawFeature(BlockStateFeatureConfig.field_236455_a_));
    public static final RegistryObject<Feature<BlockStateFeatureConfig>> shrub = FEATURES.register(
            "shrub", () -> new ShrubFeature(BlockStateFeatureConfig.field_236455_a_));
    public static final RegistryObject<Feature<NoFeatureConfig>> sugar_snow = FEATURES.register(
            "sprinkle_top_layer", () -> new SugarSnowFeature(NoFeatureConfig.field_236558_a_));


    public static void registerConfiguredFeatures() {

        register("frostfir_tree", borealis_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.frostfir_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(BorealisBlocks.frostfir_leaves.get().getDefaultState()),
                        new SpruceFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(2)),
                        new StraightTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .setIgnoreVines().build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(8))));

        register("helix_tree", borealis_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.saccharine_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(BorealisBlocks.saccharine_leaves.get().getDefaultState()),
                        new HelixFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(2)),
                        new StraightTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                .setIgnoreVines().build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(1))));

        register("geyser", borealis_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.pumice_geyser.get().getDefaultState()),
                        new SimpleBlockStateProvider(BorealisBlocks.pumice.get().getDefaultState()),
                        new BushFoliagePlacer(FeatureSpread.func_242252_a(1), FeatureSpread.func_242252_a(1), 2),
                        new StraightTrunkPlacer(1, 0, 0),
                        new TwoLayerFeature(0, 0, 0)))
                        .func_236702_a_(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(2))));

        register("brumal_tree", borealis_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_leaves.get().getDefaultState()),
                        new PalmFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(2)),
                        new RootedTrunkPlacer(5, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .setIgnoreVines().build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(2))));

        register("tall_brumal_tree", borealis_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_leaves.get().getDefaultState()),
                        new PalmFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(2)),
                        new RootedTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .setIgnoreVines().build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(1))));

        register("cotton_tree", borealis_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.saccharine_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(BorealisBlocks.saccharine_leaves.get().getDefaultState()),
                        new AspenFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(2)),
                        new StraightTrunkPlacer(8, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .setIgnoreVines().build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(1))));

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

        register("sugar_claw", claw.get().withConfiguration(new BlockStateFeatureConfig(BorealisBlocks.saccharine_wood.get().getDefaultState()))
                .withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(1))));
        register("sugar_shrub", shrub.get().withConfiguration(new BlockStateFeatureConfig(BorealisBlocks.saccharine_wood.get().getDefaultState()))
                .withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(1))));

        register("hot_spring", hot_spring.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
                .withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(1))));

        register("hot_spring_pond", Feature.LAKE.withConfiguration(
                new BlockStateFeatureConfig(BorealisBlocks.hot_spring_water.get().getDefaultState()))
                .withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(1))));

        register(
                "sprinkle_top_layer", sugar_snow.get().withConfiguration(
                        IFeatureConfig.NO_FEATURE_CONFIG));

    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BorealisMod.MODID, name), feature);
    }
}
