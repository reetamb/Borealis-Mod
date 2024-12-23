package com.reetam.borealis.registry;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.fluid.*;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class BorealisFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, BorealisMod.MODID);
    public static final DeferredRegister<FluidType> TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, BorealisMod.MODID);

    public static final DeferredHolder<FluidType, FluidType> HOT_SPRING_WATER_TYPE = TYPES.register("hot_spring_water", () -> new FluidType(
            properties("hot_spring_water", true, 1000, 1000, 1000).motionScale(0.014)));
    public static final DeferredHolder<Fluid, HotSpringWaterFluid.Source> HOT_SPRING_WATER_SOURCE = FLUIDS.register("hot_spring_water_source", () -> new HotSpringWaterFluid.Source(BorealisFluids.HOT_SPRING_WATER_PROPERTIES));
    public static final DeferredHolder<Fluid, HotSpringWaterFluid.Flowing> HOT_SPRING_WATER_FLOWING = FLUIDS.register("hot_spring_water_flowing", () -> new HotSpringWaterFluid.Flowing(BorealisFluids.HOT_SPRING_WATER_PROPERTIES));
    public static final DeferredHolder<Item, Item> HOT_SPRING_WATER_BUCKET = BorealisItems.ITEMS.register("hot_spring_water_bucket", () -> new BucketItem(
            BorealisFluids.HOT_SPRING_WATER_SOURCE.get(), (new Item.Properties()).stacksTo(1)));
    public static final DeferredHolder<Block, LiquidBlock> HOT_SPRING_WATER_BLOCK = BorealisBlocks.BLOCKS.register("hot_spring_water", () -> new HotSpringWaterBlock(
            BorealisFluids.HOT_SPRING_WATER_SOURCE, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).liquid()));
    public static final BaseFlowingFluid.Properties HOT_SPRING_WATER_PROPERTIES = new BaseFlowingFluid
            .Properties(HOT_SPRING_WATER_TYPE, HOT_SPRING_WATER_SOURCE, HOT_SPRING_WATER_FLOWING)
            .bucket(HOT_SPRING_WATER_BUCKET)
            .block(BorealisFluids.HOT_SPRING_WATER_BLOCK);

    public static final DeferredHolder<FluidType, FluidType> QUICKSILVER_TYPE = TYPES.register("quicksilver", () -> new FluidType(
            properties("quicksilver", false, 10000, 10000, 500).motionScale(0.014)));
    public static final DeferredHolder<Fluid, QuicksilverFluid.Source> QUICKSILVER_SOURCE = FLUIDS.register("quicksilver_source", () -> new QuicksilverFluid.Source(BorealisFluids.QUICKSILVER_PROPERTIES));
    public static final DeferredHolder<Fluid, QuicksilverFluid.Flowing> QUICKSILVER_FLOWING = FLUIDS.register("quicksilver_flowing", () -> new QuicksilverFluid.Flowing(BorealisFluids.QUICKSILVER_PROPERTIES));
    public static final DeferredHolder<Item, Item> QUICKSILVER_BUCKET = BorealisItems.ITEMS.register("quicksilver_bucket", () -> new BucketItem(
            BorealisFluids.QUICKSILVER_SOURCE.get(), (new Item.Properties()).stacksTo(1)));
    public static final DeferredHolder<Block, LiquidBlock> QUICKSILVER_BLOCK = BorealisBlocks.BLOCKS.register("quicksilver", () -> new QuicksilverBlock(
            BorealisFluids.QUICKSILVER_SOURCE, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).liquid()));
    public static final BaseFlowingFluid.Properties QUICKSILVER_PROPERTIES = new BaseFlowingFluid
            .Properties(QUICKSILVER_TYPE, QUICKSILVER_SOURCE, QUICKSILVER_FLOWING)
            .bucket(QUICKSILVER_BUCKET)
            .block(BorealisFluids.QUICKSILVER_BLOCK);

    public static final DeferredHolder<FluidType, FluidType> SLUSH_TYPE = TYPES.register("slush", () -> new FluidType(
            properties("slush", false, 5000, 5000, 10).motionScale(0.014)));
    public static final DeferredHolder<Fluid, SlushFluid.Source> SLUSH_SOURCE = FLUIDS.register("slush_source", () -> new SlushFluid.Source(BorealisFluids.SLUSH_PROPERTIES));
    public static final DeferredHolder<Fluid, SlushFluid.Flowing> SLUSH_FLOWING = FLUIDS.register("slush_flowing", () -> new SlushFluid.Flowing(BorealisFluids.SLUSH_PROPERTIES));
    public static final DeferredHolder<Item, Item> SLUSH_BUCKET = BorealisItems.ITEMS.register("slush_bucket", () -> new BucketItem(
            BorealisFluids.SLUSH_SOURCE.get(), (new Item.Properties()).stacksTo(1)));
    public static final DeferredHolder<Block, LiquidBlock> SLUSH_BLOCK = BorealisBlocks.BLOCKS.register("slush", () -> new SlushBlock(
            BorealisFluids.SLUSH_SOURCE, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).liquid()));
    public static final BaseFlowingFluid.Properties SLUSH_PROPERTIES = new BaseFlowingFluid
            .Properties(SLUSH_TYPE, SLUSH_SOURCE, SLUSH_FLOWING)
            .bucket(SLUSH_BUCKET)
            .block(BorealisFluids.SLUSH_BLOCK);

    public static final DeferredHolder<FluidType, FluidType> PORTAL_FLUID_TYPE = TYPES.register("portal_fluid", () -> new FluidType(
            properties("portal_fluid", false, 5000, 5000, 10).motionScale(0.014)));
    public static final DeferredHolder<Fluid, PortalFluid.Source> PORTAL_FLUID_SOURCE = FLUIDS.register("portal_fluid_source", () -> new PortalFluid.Source(BorealisFluids.PORTAL_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, PortalFluid.Flowing> PORTAL_FLUID_FLOWING = FLUIDS.register("portal_fluid_flowing", () -> new PortalFluid.Flowing(BorealisFluids.PORTAL_FLUID_PROPERTIES));
    public static final DeferredHolder<Item, Item> PORTAL_FLUID_BUCKET = BorealisItems.ITEMS.register("portal_fluid_bucket", () -> new BucketItem(
            BorealisFluids.PORTAL_FLUID_SOURCE.get(), (new Item.Properties()).stacksTo(1)));
    public static final DeferredHolder<Block, LiquidBlock> PORTAL_FLUID_BLOCK = BorealisBlocks.BLOCKS.register("portal_fluid", () -> new PortalBlock(
            BorealisFluids.PORTAL_FLUID_SOURCE, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).liquid()));
    public static final BaseFlowingFluid.Properties PORTAL_FLUID_PROPERTIES = new BaseFlowingFluid
            .Properties(PORTAL_FLUID_TYPE, PORTAL_FLUID_SOURCE, PORTAL_FLUID_FLOWING)
            .bucket(PORTAL_FLUID_BUCKET)
            .block(BorealisFluids.PORTAL_FLUID_BLOCK);
    private static FluidType.Properties properties(String name, boolean wet, int density, int viscosity, int temperature) {
        return FluidType.Properties.create()
                .descriptionId("block." + BorealisMod.MODID + "." + name)
                .canExtinguish(wet).canHydrate(wet).canDrown(wet).supportsBoating(wet).canSwim(wet)
                .fallDistanceModifier(wet ? 0F : 0.5F)
                .density(density).viscosity(viscosity).temperature(temperature)
                .canPushEntity(true)
                .canConvertToSource(false);
    }

    public static void registerFluidClient(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new BorealisFluids.BorealisFluidClient("hot_spring_water", 112, 220, 255, 2F, 5F), HOT_SPRING_WATER_TYPE.get());
        event.registerFluidType(new BorealisFluids.BorealisFluidClient("quicksilver", 196, 216, 229, 0F, 1F), QUICKSILVER_TYPE.get());
        event.registerFluidType(new BorealisFluids.BorealisFluidClient("portal_fluid", 51, 51, 204, 0.5F, 1.5F), PORTAL_FLUID_TYPE.get());
        event.registerFluidType(new BorealisFluids.BorealisFluidClient("slush", 133, 146, 191, 1F, 3F), SLUSH_TYPE.get());
    }

    public record BorealisFluidClient(String name, int red, int green, int blue, float fogBegin, float fogEnd) implements IClientFluidTypeExtensions {
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
            return ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "fluid/" + name + "_" + type);
        }
    }
}
