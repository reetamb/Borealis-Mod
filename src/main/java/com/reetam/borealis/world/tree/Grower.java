package com.reetam.borealis.world.tree;

import com.reetam.borealis.BorealisMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public class Grower {

    public static TreeGrower of(String nameIn) {
        return new TreeGrower(nameIn, Optional.empty(), Optional.of(getConfiguredFeature(nameIn)), Optional.empty());
    }

    protected static ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, name + "_tree"));
    }
}
