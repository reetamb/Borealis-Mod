package com.reetam.borealis.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;
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

    public static void registerConfiguredFeatures() {
        
            register("frostfir_tree", borealis_tree.get().withConfiguration(
                    (new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BorealisBlocks.frostfir_log.get().getDefaultState()),
                            new SimpleBlockStateProvider(BorealisBlocks.frostfir_leaves.get().getDefaultState()),
                            new SpruceFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(2)),
                            new StraightTrunkPlacer(10, 2, 2),
                            new TwoLayerFeature(1, 0, 1)))
                            .setIgnoreVines().build()).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(8))));
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BorealisMod.MODID, name), feature);
    }
}
