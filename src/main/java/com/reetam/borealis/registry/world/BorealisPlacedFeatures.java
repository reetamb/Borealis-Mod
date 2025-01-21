package com.reetam.borealis.registry.world;

import com.reetam.borealis.BorealisMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;
public class BorealisPlacedFeatures {

    public static final ResourceKey<PlacedFeature> PLACED_GLACIAL_RIDGE = createKey("glacial_ridge");
    public static final ResourceKey<PlacedFeature> PLACED_GLACIAL_SPIKE = createKey("glacial_spike");
    public static final ResourceKey<PlacedFeature> PLACED_HOT_SPRING = createKey("hot_spring");
    public static final ResourceKey<PlacedFeature> PLACED_SPIKE_TRAIL = createKey("spike_trail");
    public static final ResourceKey<PlacedFeature> PLACED_SPIRAL_CLOUD = createKey("spiral_cloud");
    public static final ResourceKey<PlacedFeature> PLACED_CLOUD = createKey("cloud");
    public static final ResourceKey<PlacedFeature> PLACED_SUGAR_SNOW = createKey("sugar_snow");
    public static final ResourceKey<PlacedFeature> PLACED_SLATE_BOULDER = createKey("slate_boulder");

    public static final ResourceKey<PlacedFeature> PLACED_BRUMAL_TREE_1 = createKey("brumal_tree_1");
    public static final ResourceKey<PlacedFeature> PLACED_BRUMAL_TREE_2 = createKey("brumal_tree_2");
    public static final ResourceKey<PlacedFeature> PLACED_BRUMAL_TREE_3 = createKey("brumal_tree_3");
    public static final ResourceKey<PlacedFeature> PLACED_BRUMAL_TREE_4 = createKey("brumal_tree_4");

    public static final ResourceKey<PlacedFeature> PLACED_FROSTFIR_TREE = createKey("frostfir_tree");
    public static final ResourceKey<PlacedFeature> PLACED_GIANTWOOD_TREE = createKey("giantwood_tree");
    public static final ResourceKey<PlacedFeature> PLACED_HELIX_TREE = createKey("helix_tree");
    public static final ResourceKey<PlacedFeature> PLACED_COTTON_TREE = createKey("cotton_tree");
    public static final ResourceKey<PlacedFeature> PLACED_GLAZED_HELIX_TREE = createKey("glazed_helix_tree");
    public static final ResourceKey<PlacedFeature> PLACED_GLAZED_COTTON_TREE = createKey("glazed_cotton_tree");
    public static final ResourceKey<PlacedFeature> PLACED_RUBBLE_PATCH = createKey("rubble_patch");
    public static final ResourceKey<PlacedFeature> PLACED_KYANITE_CRYSTAL = createKey("kyanite_crystal");
    public static final ResourceKey<PlacedFeature> PLACED_PEAT_ORE = createKey("peat_ore");

    public static final ResourceKey<PlacedFeature> PLACED_HOLLY_PATCH = createKey("holly");
    public static final ResourceKey<PlacedFeature> PLACED_LICHEN_PATCH = createKey("lichen_patch");

    public static final ResourceKey<PlacedFeature> PLACED_NATURAL_HAILSTONE = createKey("natural_hailstone");
    public static final ResourceKey<PlacedFeature> PLACED_CLOUD_HAILSTONE = createKey("cloud_hailstone");


    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, name));
    }
    private static final BlockPredicateFilter PLANT = BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.SPRUCE_SAPLING.defaultBlockState(), BlockPos.ZERO));

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> please = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, PLACED_GLACIAL_RIDGE, please.getOrThrow(BorealisConfiguredFeatures.CONFIGURED_GLACIAL_RIDGE),
                CountOnEveryLayerPlacement.of(4), BiomeFilter.biome());
        register(context, PLACED_GLACIAL_SPIKE, please.getOrThrow(BorealisConfiguredFeatures.CONFIGURED_GLACIAL_SPIKE),
                CountOnEveryLayerPlacement.of(4), BiomeFilter.biome());
        register(context, PLACED_HOT_SPRING, please.getOrThrow(BorealisConfiguredFeatures.CONFIGURED_HOT_SPRING),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(32), VerticalAnchor.aboveBottom(128)),
                InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(3), BiomeFilter.biome());
        register(context, PLACED_SPIKE_TRAIL, please.getOrThrow(BorealisConfiguredFeatures.CONFIGURED_SPIKE_TRAIL),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(32), VerticalAnchor.aboveBottom(128)),
                RarityFilter.onAverageOnceEvery(2));
        register(context, PLACED_SPIRAL_CLOUD, please.getOrThrow(BorealisConfiguredFeatures.CONFIGURED_SPIRAL_CLOUD),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(32), VerticalAnchor.aboveBottom(128)),
                InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(4));
        register(context, PLACED_CLOUD, please.getOrThrow(BorealisConfiguredFeatures.CONFIGURED_CLOUD),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(32)),
                RarityFilter.onAverageOnceEvery(8));
        register(context, PLACED_SUGAR_SNOW, please.getOrThrow(BorealisConfiguredFeatures.CONFIGURED_SUGAR_SNOW),
                BiomeFilter.biome());
        register(context, PLACED_SLATE_BOULDER, please.getOrThrow(BorealisConfiguredFeatures.CONFIGURED_SLATE_BOULDER),
                CountOnEveryLayerPlacement.of(1), RarityFilter.onAverageOnceEvery(3), BiomeFilter.biome());

        register(context, PLACED_BRUMAL_TREE_1, please.getOrThrow(BorealisConfiguredFeatures.BRUMAL_TREE_1),
                CountOnEveryLayerPlacement.of(4), BiomeFilter.biome(), PLANT);
        register(context, PLACED_BRUMAL_TREE_2, please.getOrThrow(BorealisConfiguredFeatures.BRUMAL_TREE_2),
                CountOnEveryLayerPlacement.of(3), BiomeFilter.biome(), PLANT);
        register(context, PLACED_BRUMAL_TREE_3, please.getOrThrow(BorealisConfiguredFeatures.BRUMAL_TREE_3),
                CountOnEveryLayerPlacement.of(4), BiomeFilter.biome(), PLANT);
        register(context, PLACED_BRUMAL_TREE_4, please.getOrThrow(BorealisConfiguredFeatures.BRUMAL_TREE_4),
                CountOnEveryLayerPlacement.of(6), BiomeFilter.biome(), PLANT);

        register(context, PLACED_FROSTFIR_TREE, please.getOrThrow(BorealisConfiguredFeatures.FROSTFIR_TREE),
                CountOnEveryLayerPlacement.of(8), BiomeFilter.biome(), PLANT);
//        register(context, PLACED_GREAT_FROSTFIR_TREE, please.getOrThrow(BorealisConfiguredFeatures.CONFIGURED_FROSTFIR_TREE),
//                CountPlacement.of(4), BiomeFilter.biome());
        register(context, PLACED_GIANTWOOD_TREE, please.getOrThrow(BorealisConfiguredFeatures.GIANTWOOD_TREE),
                CountOnEveryLayerPlacement.of(4), BiomeFilter.biome(), PLANT);
        register(context, PLACED_HELIX_TREE, please.getOrThrow(BorealisConfiguredFeatures.HELIX_TREE),
                CountOnEveryLayerPlacement.of(3), BiomeFilter.biome(), PLANT);
        register(context, PLACED_COTTON_TREE, please.getOrThrow(BorealisConfiguredFeatures.COTTON_TREE),
                CountOnEveryLayerPlacement.of(5), BiomeFilter.biome(), PLANT);
        register(context, PLACED_GLAZED_HELIX_TREE, please.getOrThrow(BorealisConfiguredFeatures.GLAZED_HELIX_TREE),
                CountOnEveryLayerPlacement.of(2), BiomeFilter.biome(), PLANT);
        register(context, PLACED_GLAZED_COTTON_TREE, please.getOrThrow(BorealisConfiguredFeatures.GLAZED_COTTON_TREE),
                CountOnEveryLayerPlacement.of(3), BiomeFilter.biome(), PLANT);

        register(context, PLACED_LICHEN_PATCH, please.getOrThrow(BorealisConfiguredFeatures.LICHEN_PATCH),
                CountOnEveryLayerPlacement.of(2), BiomeFilter.biome());
        register(context, PLACED_RUBBLE_PATCH, please.getOrThrow(BorealisConfiguredFeatures.RUBBLE_PATCH),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(64)),
                InSquarePlacement.spread(), CountOnEveryLayerPlacement.of(2), RarityFilter.onAverageOnceEvery(2), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(60), VerticalAnchor.aboveBottom(120)));
        register(context, PLACED_KYANITE_CRYSTAL, please.getOrThrow(BorealisConfiguredFeatures.KYANITE_CRYSTAL),
                InSquarePlacement.spread(), CountPlacement.of(1), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(60), VerticalAnchor.aboveBottom(120)));
        register(context, PLACED_PEAT_ORE, please.getOrThrow(BorealisConfiguredFeatures.PEAT_ORE),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()),
                CountPlacement.of(16), InSquarePlacement.spread());
        register(context, PLACED_HOLLY_PATCH, please.getOrThrow(BorealisConfiguredFeatures.HOLLY_PATCH),
                CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());

        register(context, PLACED_NATURAL_HAILSTONE, please.getOrThrow(BorealisConfiguredFeatures.NATURAL_HAILSTONE),
                RarityFilter.onAverageOnceEvery(128), InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING));
        register(context, PLACED_CLOUD_HAILSTONE, please.getOrThrow(BorealisConfiguredFeatures.CLOUD_HAILSTONE),
                CountPlacement.of(5), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(32)));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
