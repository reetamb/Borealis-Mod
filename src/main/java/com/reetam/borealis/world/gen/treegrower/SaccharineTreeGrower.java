package com.reetam.borealis.world.gen.treegrower;

import com.reetam.borealis.registry.BorealisWorldgen;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class SaccharineTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<ConfiguredFeature<TreeConfiguration, ?>> getConfiguredFeature(Random rand, boolean largeHive) {
        return BorealisWorldgen.Configured.CONFIGURED_HELIX_TREE;
    }
}
