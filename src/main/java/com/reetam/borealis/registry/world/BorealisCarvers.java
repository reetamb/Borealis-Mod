package com.reetam.borealis.registry.world;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public class BorealisCarvers {
    public static final ResourceKey<ConfiguredWorldCarver<?>> GENERAL_CAVES = createKey("general_caves");

    private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, name));
    }
    public static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> context) {
        HolderGetter<Block> lookup = context.lookup(Registries.BLOCK);
        context.register(GENERAL_CAVES, WorldCarver.CAVE.configured(new CaveCarverConfiguration(
                0.15F, // probability
                UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.TOP), // height
                ConstantFloat.of(1.25F), // y scale
                new VerticalAnchor.AboveBottom(0), // lava level
                CarverDebugSettings.DEFAULT,
                lookup.getOrThrow(BorealisTags.Blocks.CAVE_REPLACEABLES), // replaces
                ConstantFloat.of(1.5F), // horizontal radius multiplier
                ConstantFloat.of(2), // vertical radius multiplier
                UniformFloat.of(0, 1) // floor level
        )));
    }
}
