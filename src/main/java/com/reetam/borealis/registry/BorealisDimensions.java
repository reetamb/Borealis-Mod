package com.reetam.borealis.registry;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import com.reetam.borealis.BorealisMod;

public class BorealisDimensions {

    public static final RegistryKey<DimensionType> borealis = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, name("borealis"));
    public static final RegistryKey<World> borealis_w = RegistryKey.create(Registry.DIMENSION_REGISTRY, name("borealis"));

    private static ResourceLocation name(String name) {
        return new ResourceLocation(BorealisMod.MODID, name);
    }
}