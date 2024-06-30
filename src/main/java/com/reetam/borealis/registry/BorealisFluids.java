package com.reetam.borealis.registry;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.HotSpringWaterBlock;
import com.reetam.borealis.block.HotSpringWaterFluid;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class BorealisFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, BorealisMod.MODID);
    public static final DeferredRegister<FluidType> TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, BorealisMod.MODID);

    public static final RegistryObject<FluidType> HOT_SPRING_WATER_TYPE = TYPES.register("hot_spring_water", () -> new FluidType(
            properties("hot_spring_water", true, 1000, 1000, 1000).motionScale(0.014)) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new BorealisFluidClient("hot_spring_water", 112, 220, 255, 2F, 5F));
    }});
    public static final RegistryObject<HotSpringWaterFluid.Source> HOT_SPRING_WATER_SOURCE = FLUIDS.register("hot_spring_water_source", () -> new HotSpringWaterFluid.Source(BorealisFluids.HOT_SPRING_WATER_PROPERTIES));
    public static final RegistryObject<HotSpringWaterFluid.Flowing> HOT_SPRING_WATER_FLOWING = FLUIDS.register("hot_spring_water_flowing", () -> new HotSpringWaterFluid.Flowing(BorealisFluids.HOT_SPRING_WATER_PROPERTIES));
    public static final RegistryObject<Item> HOT_SPRING_WATER_BUCKET = BorealisItems.ITEMS.register("hot_spring_water_bucket", () -> new BucketItem(
            BorealisFluids.HOT_SPRING_WATER_SOURCE, (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<LiquidBlock> HOT_SPRING_WATER_BLOCK = BorealisBlocks.BLOCKS.register("hot_spring_water", () -> new HotSpringWaterBlock(
            BorealisFluids.HOT_SPRING_WATER_SOURCE, BlockBehaviour.Properties.copy(Blocks.WATER).liquid()));
    public static final ForgeFlowingFluid.Properties HOT_SPRING_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(HOT_SPRING_WATER_TYPE, HOT_SPRING_WATER_SOURCE, HOT_SPRING_WATER_FLOWING).bucket(HOT_SPRING_WATER_BUCKET).block(BorealisFluids.HOT_SPRING_WATER_BLOCK);

    private static FluidType.Properties properties(String name, boolean wet, int density, int viscosity, int temperature) {
        return FluidType.Properties.create()
                .descriptionId("block." + BorealisMod.MODID + "." + name)
                .canExtinguish(wet).canHydrate(wet).canDrown(wet).supportsBoating(wet).canSwim(wet)
                .fallDistanceModifier(wet ? 0F : 0.5F)
                .density(density).viscosity(viscosity).temperature(temperature)
                .canPushEntity(true)
                .canConvertToSource(false);
    }
    private record BorealisFluidClient(String name, int red, int green, int blue, float fogBegin, float fogEnd) implements IClientFluidTypeExtensions {
        @Override
        public ResourceLocation getStillTexture() { return texture("still"); }
        @Override
        public ResourceLocation getFlowingTexture() { return texture("flow"); }
        @Override
        public ResourceLocation getOverlayTexture() { return texture("overlay"); }
        @Override
        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTicks, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) { return new Vector3f(red / 255F, green / 255F, blue / 255F); }

        @Override
        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTicks, float nearDistance, float farDistance, FogShape shape) {
            RenderSystem.setShaderFogStart(fogBegin);
            RenderSystem.setShaderFogEnd(fogEnd);
        }

        private ResourceLocation texture(String type) {
            return new ResourceLocation(BorealisMod.MODID, "fluid/" + name + "_" + type);
        }
    }
}
