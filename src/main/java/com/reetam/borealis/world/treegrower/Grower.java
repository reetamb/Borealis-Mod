package com.reetam.borealis.world.treegrower;

import com.reetam.borealis.BorealisMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class Grower extends AbstractTreeGrower {
    private final String name;

    private Grower(String nameIn) {
        name = nameIn;
    }

    public static AbstractTreeGrower of(String nameIn) {
        return new Grower(nameIn);
    }

    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(BorealisMod.MODID, name + "_tree"));
    }
}
