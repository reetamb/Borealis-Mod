package com.reetam.borealis.world.gen.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class BrumalTree extends Tree {

    @Nullable
    @Override
    public ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return BorealisFeatures.borealis_tree.get().withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_log.get().getDefaultState()),
                        new SimpleBlockStateProvider(BorealisBlocks.brumal_leaves.get().getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 2),
                        new ForkyTrunkPlacer(10, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                        .setIgnoreVines().build());
    }
}