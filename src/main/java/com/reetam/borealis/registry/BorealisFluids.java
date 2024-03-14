package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BorealisFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, BorealisMod.MODID);

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, BorealisMod.MODID);
    public static final RegistryObject<FlowingFluid> HOT_SPRING_WATER_SOURCE = FLUIDS.register("hot_spring_water_source", () -> new ForgeFlowingFluid.Source(
            BorealisFluids.HOT_SPRING_WATER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> HOT_SPRING_WATER_FLOWING = FLUIDS.register("hot_spring_water_flowing", () -> new ForgeFlowingFluid.Flowing(
            BorealisFluids.HOT_SPRING_WATER_PROPERTIES));

    public static final RegistryObject<FluidType> HOT_SPRING_WATER_TYPE = FLUID_TYPES.register("hot_spring_water", () -> new FluidType(FluidType.Properties.create()
            .canConvertToSource(true).canExtinguish(true).canHydrate(true).canDrown(true).canSwim(true).canPushEntity(true)
    ));
    public static final ForgeFlowingFluid.Properties HOT_SPRING_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(
            HOT_SPRING_WATER_TYPE, HOT_SPRING_WATER_SOURCE, HOT_SPRING_WATER_FLOWING)
            .bucket(BorealisItems.HOT_SPRING_WATER_BUCKET)
            .block(BorealisBlocks.HOT_SPRING_WATER_BLOCK);
}
