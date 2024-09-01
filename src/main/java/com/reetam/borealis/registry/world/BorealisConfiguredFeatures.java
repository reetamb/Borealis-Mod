package com.reetam.borealis.registry.world;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.world.tree.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;

import java.util.List;
import java.util.function.Supplier;

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
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRUMAL_TREE_1 = createKey("brumal_tree_1"); // root trunk, palm foliage, short
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRUMAL_TREE_2 = createKey("brumal_tree_2"); // root trunk, palm foliage, tall
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRUMAL_TREE_3 = createKey("brumal_tree_3"); // straight trunk, palm foliage, tall
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRUMAL_TREE_4 = createKey("brumal_tree_4"); // straight trunk, acacia foliage, tall
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
        register(context, CONFIGURED_GLACIAL_RIDGE, BorealisFeatures.GLACIAL_RIDGE.get(), new ColumnFeatureConfiguration(of(1), from(1, 4)));
        register(context, CONFIGURED_GLACIAL_SPIKE, BorealisFeatures.GLACIAL_SPIKE.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_HOT_SPRING, BorealisFeatures.HOT_SPRING.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_SPIKE_TRAIL, BorealisFeatures.SPIKE_TRAIL.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_SPIRAL_CLOUD, BorealisFeatures.SPIRAL_CLOUD.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_CLOUD, BorealisFeatures.CLOUD.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_SUGAR_SNOW, BorealisFeatures.SUGAR_SNOW.get(), FeatureConfiguration.NONE);
        register(context, CONFIGURED_SLATE_BOULDER, Feature.FOREST_ROCK, new BlockStateConfiguration(BorealisBlocks.SLATE.get().defaultBlockState()));

        register(context, BRUMAL_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                with(BorealisBlocks.BRUMAL_LOG), new RootedTrunkPlacer(6, 2, 2),
                with(BorealisBlocks.BRUMAL_LEAVES), new PalmFoliagePlacer(of(2), of(0), of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, BRUMAL_TREE_1, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                with(BorealisBlocks.BRUMAL_LOG),
                new RootedTrunkPlacer(5, 2, 2, with(BorealisBlocks.WINTER_VIOLA), 0.25F),
                with(BorealisBlocks.BRUMAL_LEAVES),
                new PalmFoliagePlacer(of(2), of(0), of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(List.of(
                        new EpiphyteDecorator(0.25F),
                        new RootDecorator(0.15F, with(BorealisBlocks.WINTER_VIOLA)),
                        new LogDecorator(0.3F, with(BorealisBlocks.WALL_WINTER_VIOLIN)),
                        new TreeNutDecorator(0.025F),
                        undergrowth()
                )).ignoreVines().build());
        register(context, BRUMAL_TREE_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                with(BorealisBlocks.BRUMAL_LOG),
                new RootedTrunkPlacer(7, 2, 2, with(BorealisBlocks.WINTER_VIOLA), 0.25F),
                with(BorealisBlocks.BRUMAL_LEAVES),
                new PalmFoliagePlacer(of(2), of(0), of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(List.of(
                        new EpiphyteDecorator(0.25F),
                        new RootDecorator(0.15F, with(BorealisBlocks.WINTER_VIOLA)),
                        new LogDecorator(0.3F, with(BorealisBlocks.WALL_WINTER_VIOLIN)),
                        new TreeNutDecorator(0.025F),
                        undergrowth()
                )).ignoreVines().build());
        register(context, BRUMAL_TREE_3, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                with(BorealisBlocks.BRUMAL_LOG),
                new StraightTrunkPlacer(6, 2, 2),
                with(BorealisBlocks.BRUMAL_LEAVES),
                new PalmFoliagePlacer(of(2), of(0), of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(List.of(
                        new EpiphyteDecorator(0.25F),
                        new LogDecorator(0.3F, with(BorealisBlocks.WALL_WINTER_VIOLIN)),
                        new TreeNutDecorator(0.025F),
                        undergrowth()
                )).ignoreVines().build());
        register(context, BRUMAL_TREE_4, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                with(BorealisBlocks.BRUMAL_LOG),
                new StraightTrunkPlacer(8, 2, 2),
                with(BorealisBlocks.BRUMAL_LEAVES),
                new AcaciaFoliagePlacer(of(3), of(0)),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(List.of(
                        new EpiphyteDecorator(0.25F),
                        new LogDecorator(0.3F, with(BorealisBlocks.WALL_WINTER_VIOLIN)),
                        undergrowth()
                )).ignoreVines().build());

        register(context, FROSTFIR_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                with(BorealisBlocks.FROSTFIR_LOG),
                new StraightTrunkPlacer(8, 2, 2),
                with(BorealisBlocks.FROSTFIR_LEAVES),
                new SpruceFoliagePlacer(of(2), of(0), of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, GIANTWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                with(BorealisBlocks.FROSTFIR_LOG),
                new GiantTrunkPlacer(12, 2, 16),
                with(BorealisBlocks.FROSTFIR_LEAVES),
                new MegaPineFoliagePlacer(of(0), of(0), from(12, 16)),
                new TwoLayersFeatureSize(1, 1, 2))
                .dirt(with(BorealisBlocks.LIVING_SNOW_BLOCK)).ignoreVines().build());
        register(context, HELIX_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                with(BorealisBlocks.SWEETWOOD_LOG),
                new StraightTrunkPlacer(10, 2, 2),
                with(BorealisBlocks.SWEETWOOD_LEAVES),
                new HelixFoliagePlacer(of(2), of(0), of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, COTTON_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                with(BorealisBlocks.SWEETWOOD_LOG),
                new StraightTrunkPlacer(5, 2, 2),
                with(BorealisBlocks.SWEETWOOD_LEAVES),
                new AspenFoliagePlacer(of(2), of(0), of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, GLAZED_HELIX_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                with(BorealisBlocks.SWEETWOOD_LOG),
                new StraightTrunkPlacer(10, 2, 2),
                with(BorealisBlocks.GLAZED_LEAVES),
                new HelixFoliagePlacer(of(2), of(0), of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());
        register(context, GLAZED_COTTON_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                with(BorealisBlocks.SWEETWOOD_LOG),
                new StraightTrunkPlacer(6, 1, 1),
                with(BorealisBlocks.GLAZED_LEAVES),
                new AspenFoliagePlacer(of(2), of(0), of(2)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());

        register(context, HOLLY_PATCH, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(64, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(with(BorealisBlocks.HOLLY.get())))));
        register(context, LICHEN_PATCH, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(
                BorealisBlocks.LIVING_SNOW_BLOCK.get().defaultBlockState(),
                BorealisBlocks.LICHEN_BLOCK.get().defaultBlockState(),
                from(1, 2)
        ));
        register(context, RUBBLE_PATCH, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(
                BorealisBlocks.PERMAFROST.get().defaultBlockState(),
                BorealisBlocks.PERMAFROST_RUBBLE.get().defaultBlockState(),
                from(3, 4)));
        register(context, KYANITE_CRYSTAL, BorealisFeatures.BERYL.get(), new BlockStateConfiguration(BorealisBlocks.KYANITE_ORE.get().defaultBlockState()));
        register(context, PEAT_ORE, Feature.ORE, new OreConfiguration(
                new BlockMatchTest(BorealisBlocks.PERMAFROST.get()), BorealisBlocks.PEAT.get().defaultBlockState(), 16, 0.5F));
        register(context, CONFIGURED_TANZANITE_METEOR, BorealisFeatures.TANZANITE_METEOR.get(), FeatureConfiguration.NONE);
    }

    private static ConstantInt of(int n) { return ConstantInt.of(n); }
    private static UniformInt from(int a, int b) { return UniformInt.of(a, b); }
    private static BlockStateProvider with(Supplier<? extends Block> block) { return with(block.get()); }
    private static BlockStateProvider with(Block block) { return BlockStateProvider.simple(block); }
    private static TreeDecorator undergrowth() {
        return new UndergrowthDecorator(0.375F, new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(BorealisBlocks.WINTER_CELLO.get().defaultBlockState(), 3)
                        .add(BorealisBlocks.BRUMELIAD.get().defaultBlockState(), 15)
                        .add(BorealisBlocks.WINTER_VIOLA.get().defaultBlockState(), 6)
                        .add(Blocks.LARGE_FERN.defaultBlockState(), 10)
                        .add(Blocks.TALL_GRASS.defaultBlockState(), 10)
                        .add(Blocks.FERN.defaultBlockState(), 15)
                        .build()));

    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
