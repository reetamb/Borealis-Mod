package com.reetam.borealis.client;

import com.reetam.borealis.client.renderer.fluid.FluidRenderer;
import com.reetam.borealis.entity.renderer.BorealisBoatRenderer;
import com.reetam.borealis.entity.renderer.HummingbirdRenderer;
import com.reetam.borealis.entity.renderer.MismicMuskoxRenderer;
import com.reetam.borealis.entity.renderer.TakaheRenderer;
import com.reetam.borealis.registry.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class ClientProxy {

    private static void render(Supplier<? extends Block> block, RenderType render) {
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }

    public static void registerBlockRenderers() {
        RenderType cutout = RenderType.cutout();
        RenderType translucent = RenderType.translucent();


        render(BorealisBlocks.BRUMAL_DOOR, cutout);
        render(BorealisBlocks.BRUMAL_TRAPDOOR, cutout);
        render(BorealisBlocks.FROSTFIR_DOOR, cutout);
        render(BorealisBlocks.FROSTFIR_TRAPDOOR, cutout);
        render(BorealisBlocks.SACCHARINE_DOOR, cutout);
        render(BorealisBlocks.SACCHARINE_TRAPDOOR, cutout);

        render(BorealisBlocks.HOT_SPRING_WATER, translucent);
        RenderTypeLookup.setRenderLayer(BorealisFluids.hot_spring_water_flowing.get(), translucent);
        RenderTypeLookup.setRenderLayer(BorealisFluids.hot_spring_water_source.get(), translucent);
        render(BorealisBlocks.BOREALIS_PORTAL, translucent);
        render(BorealisBlocks.CLOUD, translucent);
        render(BorealisBlocks.TANZANITE_BLOCK, translucent);
        render(BorealisBlocks.BRUMAL_SAPLING, translucent);
        render(BorealisBlocks.FROSTFIR_SAPLING, translucent);
        render(BorealisBlocks.SACCHARINE_SAPLING, translucent);
        render(BorealisBlocks.POTTED_BRUMAL_SAPLING, translucent);
        render(BorealisBlocks.POTTED_FROSTFIR_SAPLING, translucent);
        render(BorealisBlocks.POTTED_SACCHARINE_SAPLING, translucent);
    }

    public static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.HUMMINGBIRD.get(), HummingbirdRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.TAKAHE.get(), TakaheRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.MISMIC_MUSKOX.get(), MismicMuskoxRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.BOAT.get(), BorealisBoatRenderer::new);
    }

    public static void registerDimensionRenderers() {
        DimensionRenderInfo.EFFECTS.put(BorealisDimensions.BOREALIS_TYPE.location(), new DimensionRenderInfo(Float.NaN, false, DimensionRenderInfo.FogType.NONE, false, true) {
            @Override
            public Vector3d getBrightnessDependentFogColor(Vector3d vector3d, float sun) {
                return vector3d;
            }

            @Override
            public boolean isFoggyAt(int x, int y) {
                return Minecraft.getInstance().level.isNight();
            }
        });
    }

    public static void registerTileEntityRenderers() {
        ClientRegistry.bindTileEntityRenderer(BorealisTileEntities.BOREALIS_SIGN.get(), SignTileEntityRenderer::new);
    }

    public static void registerWoodTypes() {
        Atlases.addWoodType(BorealisBlocks.BRUMAL_WOODTYPE);
        Atlases.addWoodType(BorealisBlocks.FROSTFIR_WOODTYPE);
        Atlases.addWoodType(BorealisBlocks.SACCHARINE_WOODTYPE);
    }
}