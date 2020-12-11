package com.reetam.borealis.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.Placement;
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

    public static void registerConfiguredFeatures() {
            final RuleTest permafrost_tester = new BlockMatchRuleTest(BorealisBlocks.permafrost.get());

            register("frostfir_tree", borealis_tree.get().withConfiguration(
                    (new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BorealisBlocks.frostfir_log.get().getDefaultState()),
                            new SimpleBlockStateProvider(BorealisBlocks.frostfir_leaves.get().getDefaultState()),
                            new SpruceFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(2)),
                            new StraightTrunkPlacer(10, 2, 2),
                            new TwoLayerFeature(1, 0, 1)))
                            .setIgnoreVines().build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(8))));

            register("brumal_tree", brumal_tree.get().withConfiguration(
                    IFeatureConfig.NO_FEATURE_CONFIG).chance(6));

            register("brumal_tree_common", brumal_tree.get().withConfiguration(
                        IFeatureConfig.NO_FEATURE_CONFIG).chance(1));

            register("permafrost_rubble_patch", Feature.NETHERRACK_REPLACE_BLOBS.withConfiguration(new BlobReplacementConfig(BorealisBlocks.permafrost.get().getDefaultState(), BorealisBlocks.permafrost_rubble.get().getDefaultState(), FeatureSpread.func_242253_a(3, 4))).range(128).square().func_242731_b(25));
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BorealisMod.MODID, name), feature);
    }
}
