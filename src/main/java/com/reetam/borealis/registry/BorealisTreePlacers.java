package com.reetam.borealis.registry;

import com.mojang.serialization.Codec;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.world.gen.foliageplacer.AspenFoliagePlacer;
import com.reetam.borealis.world.gen.foliageplacer.PalmFoliagePlacer;
import com.reetam.borealis.world.gen.trunkplacer.RootedTrunkPlacer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BorealisTreePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, BorealisMod.MODID);

    public static final RegistryObject<FoliagePlacerType<FoliagePlacer>> PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("palm_foliage_placer", () -> new FoliagePlacerType(PalmFoliagePlacer.field_236790_a_));
    public static final RegistryObject<FoliagePlacerType<FoliagePlacer>> ASPEN_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("aspen_foliage_placer", () -> new FoliagePlacerType(AspenFoliagePlacer.field_236790_a_));

    public static final TrunkPlacerType<RootedTrunkPlacer> ROOTED_TRUNK_PLACER = register("rooted_trunk_placer", RootedTrunkPlacer.CODEC);


    private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {
        return Registry.register(Registry.TRUNK_REPLACER, new ResourceLocation(BorealisMod.MODID, name), new TrunkPlacerType<>(codec));
    }
}

