package com.reetam.borealis.registry.world;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.world.configuration.CoverTopLayerConfiguration;
import com.reetam.borealis.world.feature.*;
import com.reetam.borealis.world.tree.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BorealisFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, BorealisMod.MODID);

    public static final DeferredHolder<Feature<?>, Feature<ColumnFeatureConfiguration>> GLACIAL_RIDGE = FEATURES.register(
            "glacial_ridge", () -> new GlacialRidgeFeature(ColumnFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GLACIAL_SPIKE = FEATURES.register(
            "glacial_spike", () -> new GlacialSpikeFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> HOT_SPRING = FEATURES.register(
            "hot_spring", () -> new HotSpringFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<CoverTopLayerConfiguration>> ARCTIC_WILLOW = FEATURES.register(
            "arctic_willow_top_layer", () -> new CoverTopLayerFeature(CoverTopLayerConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SPIKE_TRAIL = FEATURES.register(
            "spike_trail", () -> new SpikeTrailFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SPIRAL_CLOUD = FEATURES.register(
            "spiral_cloud", () -> new SpiralCloudFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> CLOUD = FEATURES.register(
            "cloud", () -> new CloudFeature(NoneFeatureConfiguration.CODEC)
    );
    public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> BERYL = FEATURES.register(
            "beryl", () -> new BerylFeature(BlockStateConfiguration.CODEC)
    );
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> TANZANITE_METEOR = FEATURES.register(
            "tanzanite_meteor", () -> new TanzaniteMeteorFeature(NoneFeatureConfiguration.CODEC)
    );

    public static class TreePlacers {

        public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, BorealisMod.MODID);
        public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("palm_foliage_placer", () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));
        public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<AspenFoliagePlacer>> ASPEN_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("aspen_foliage_placer", () -> new FoliagePlacerType<>(AspenFoliagePlacer.CODEC));
        public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<HelixFoliagePlacer>> HELIX_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("helix_foliage_placer", () -> new FoliagePlacerType<>(HelixFoliagePlacer.CODEC));
        public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<PineyFoliagePlacer>> PINEY_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("piney_foliage_placer", () -> new FoliagePlacerType<>(PineyFoliagePlacer.CODEC));

        public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, BorealisMod.MODID);
        public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<RootedTrunkPlacer>> ROOTED_TRUNK_PLACER = TRUNK_PLACERS.register("rooted_trunk_placer", () -> new TrunkPlacerType<>(RootedTrunkPlacer.CODEC));

        public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, BorealisMod.MODID);
        public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<EpiphyteDecorator>> EPIPHYTE_DECORATOR = TREE_DECORATORS.register("epiphyte_decorator", () -> new TreeDecoratorType<>(EpiphyteDecorator.CODEC));
        public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<UndergrowthDecorator>> UNDERGROWTH_DECORATOR = TREE_DECORATORS.register("undergrowth_decorator", () -> new TreeDecoratorType<>(UndergrowthDecorator.CODEC));
        public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<LogDecorator>> LOG_DECORATOR = TREE_DECORATORS.register("log_decorator", () -> new TreeDecoratorType<>(LogDecorator.CODEC));
        public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<RootDecorator>> ROOT_DECORATOR = TREE_DECORATORS.register("root_decorator", () -> new TreeDecoratorType<>(RootDecorator.CODEC));
        public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<TreeNutDecorator>> TREE_NUT_DECORATOR = TREE_DECORATORS.register("tree_nut_decorator", () -> new TreeDecoratorType<>(TreeNutDecorator.CODEC));
    }
}
