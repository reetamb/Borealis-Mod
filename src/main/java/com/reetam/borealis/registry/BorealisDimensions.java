package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class BorealisDimensions {

    public static final ResourceKey<DimensionType> BOREALIS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, name("borealis"));
    public static final ResourceKey<Level> BOREALIS = ResourceKey.create(Registries.DIMENSION, name("borealis"));

    private static ResourceLocation name(String name) {
        return new ResourceLocation(BorealisMod.MODID, name);
    }
}