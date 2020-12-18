package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BorealisFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, BorealisMod.MODID);

    public static final RegistryObject<FlowingFluid> hot_spring_water_source = FLUIDS.register("hot_spring_water_source", () -> new ForgeFlowingFluid.Source(
            BorealisFluids.hot_spring_water_properties));
    public static final RegistryObject<FlowingFluid> hot_spring_water_flowing = FLUIDS.register("hot_spring_water_flowing", () -> new ForgeFlowingFluid.Flowing(
            BorealisFluids.hot_spring_water_properties));

    public static final ForgeFlowingFluid.Properties hot_spring_water_properties = new ForgeFlowingFluid.Properties(
            hot_spring_water_source, hot_spring_water_flowing,
            FluidAttributes.builder(
                    new ResourceLocation(BorealisMod.MODID, "fluid/hot_spring_water_still"),
                    new ResourceLocation(BorealisMod.MODID, "fluid/hot_spring_water_flow")).overlay(
                    new ResourceLocation(BorealisMod.MODID, "fluid/hot_spring_water_overlay")))
            .bucket(BorealisItems.hot_spring_water_bucket)
            .block(BorealisBlocks.hot_spring_water);
}
