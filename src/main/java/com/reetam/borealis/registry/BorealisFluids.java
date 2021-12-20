package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BorealisFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, BorealisMod.MODID);

    public static final RegistryObject<FlowingFluid> HOT_SPRING_WATER_SOURCE = FLUIDS.register("hot_spring_water_source", () -> new ForgeFlowingFluid.Source(
            BorealisFluids.HOT_SPRING_WATER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> HOT_SPRING_WATER_FLOWING = FLUIDS.register("hot_spring_water_flowing", () -> new ForgeFlowingFluid.Flowing(
            BorealisFluids.HOT_SPRING_WATER_PROPERTIES));

    public static final ForgeFlowingFluid.Properties HOT_SPRING_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(
            HOT_SPRING_WATER_SOURCE, HOT_SPRING_WATER_FLOWING,
            FluidAttributes.builder(
                    new ResourceLocation(BorealisMod.MODID, "fluid/hot_spring_water_still"),
                    new ResourceLocation(BorealisMod.MODID, "fluid/hot_spring_water_flow")).overlay(
                    new ResourceLocation(BorealisMod.MODID, "fluid/hot_spring_water_overlay")))
            .bucket(BorealisItems.HOT_SPRING_WATER_BUCKET)
            .block(BorealisBlocks.HOT_SPRING_WATER_BLOCK);
}
