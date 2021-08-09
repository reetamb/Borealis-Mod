package com.reetam.borealis.world.gen.tree;

import com.reetam.borealis.registry.BorealisFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class BrumalTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random rand, boolean largeHive) {
        return BorealisFeatures.Configured.BRUMAL_TREE;
    }
}
