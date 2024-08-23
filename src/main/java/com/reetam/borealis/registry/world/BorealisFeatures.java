package com.reetam.borealis.registry.world;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.world.feature.*;
import com.reetam.borealis.world.tree.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
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
    public static final RegistryObject<Feature<BlockStateConfiguration>> BERYL = FEATURES.register(
            "beryl", () -> new BerylFeature(BlockStateConfiguration.CODEC)
    );
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> TANZANITE_METEOR = FEATURES.register(
            "tanzanite_meteor", () -> new TanzaniteMeteorFeature(NoneFeatureConfiguration.CODEC)
    );

    public static class TreePlacers {

        public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, BorealisMod.MODID);
        public static final RegistryObject<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("palm_foliage_placer", () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));
        public static final RegistryObject<FoliagePlacerType<AspenFoliagePlacer>> ASPEN_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("aspen_foliage_placer", () -> new FoliagePlacerType<>(AspenFoliagePlacer.CODEC));
        public static final RegistryObject<FoliagePlacerType<HelixFoliagePlacer>> HELIX_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("helix_foliage_placer", () -> new FoliagePlacerType<>(HelixFoliagePlacer.CODEC));

        public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, BorealisMod.MODID);
        public static final RegistryObject<TrunkPlacerType<RootedTrunkPlacer>> ROOTED_TRUNK_PLACER = TRUNK_PLACERS.register("rooted_trunk_placer", () -> new TrunkPlacerType<>(RootedTrunkPlacer.CODEC));

        public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, BorealisMod.MODID);
        public static final RegistryObject<TreeDecoratorType<EpiphyteTreeDecorator>> EPIPHYTE_TREE_DECORATOR = TREE_DECORATORS.register("epiphyte_tree_decorator", () -> new TreeDecoratorType<>(EpiphyteTreeDecorator.CODEC));
    }
}
