package com.reetam.borealis.registry.world;

import com.google.common.collect.ImmutableSet;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BorealisDimensions {

    public static final DeferredRegister<PoiType> POIS = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, BorealisMod.MODID);

    public static final ResourceKey<DimensionType> BOREALIS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, name("borealis"));
    public static final ResourceKey<Level> BOREALIS = ResourceKey.create(Registries.DIMENSION, name("borealis"));

    public static final DeferredHolder<PoiType, PoiType> BOREALIS_PORTAL_POI = POIS.register("borealis_portal",
            () -> new PoiType(ImmutableSet.copyOf(BorealisBlocks.BOREALIS_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));

    private static ResourceLocation name(String name) {
        return ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, name);
    }
}