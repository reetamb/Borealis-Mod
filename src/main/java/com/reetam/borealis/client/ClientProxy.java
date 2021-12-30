package com.reetam.borealis.client;

import com.reetam.borealis.entity.BorealisBoatEntity;
import com.reetam.borealis.entity.model.HummingbirdModel;
import com.reetam.borealis.entity.model.TakaheModel;
import com.reetam.borealis.entity.renderer.BorealisBoatRenderer;
import com.reetam.borealis.entity.renderer.HummingbirdRenderer;
import com.reetam.borealis.entity.renderer.TakaheRenderer;
import com.reetam.borealis.registry.*;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = "borealis", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientProxy {

    private static void render(Supplier<? extends Block> block, RenderType render) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), render);
    }

    public static void registerBlockRenderers() {
        RenderType cutout = RenderType.cutout();
        RenderType translucent = RenderType.translucent();


        render(BorealisBlocks.BRUMAL_DOOR, cutout);
        render(BorealisBlocks.BRUMAL_TRAPDOOR, cutout);
        render(BorealisBlocks.BRUMAL_SAPLING, cutout);
        render(BorealisBlocks.POTTED_BRUMAL_SAPLING, cutout);
        render(BorealisBlocks.FROSTFIR_DOOR, cutout);
        render(BorealisBlocks.FROSTFIR_TRAPDOOR, cutout);
        render(BorealisBlocks.FROSTFIR_SAPLING, cutout);
        render(BorealisBlocks.POTTED_FROSTFIR_SAPLING, cutout);
        render(BorealisBlocks.SACCHARINE_DOOR, cutout);
        render(BorealisBlocks.SACCHARINE_TRAPDOOR, cutout);
        render(BorealisBlocks.SACCHARINE_SAPLING, cutout);
        render(BorealisBlocks.POTTED_SACCHARINE_SAPLING, cutout);

        render(BorealisBlocks.HOT_SPRING_WATER_BLOCK, translucent);
        ItemBlockRenderTypes.setRenderLayer(BorealisFluids.HOT_SPRING_WATER_FLOWING.get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(BorealisFluids.HOT_SPRING_WATER_SOURCE.get(), translucent);
        render(BorealisBlocks.BOREALIS_PORTAL, translucent);
        render(BorealisBlocks.CLOUD, translucent);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BorealisEntities.HUMMINGBIRD_TYPE, HummingbirdRenderer::new);
        event.registerEntityRenderer(BorealisEntities.TAKAHE_TYPE, TakaheRenderer::new);
        event.registerEntityRenderer(BorealisEntities.BOAT_TYPE, BorealisBoatRenderer::new);
        event.registerBlockEntityRenderer(BorealisBlockEntities.BOREALIS_SIGN.get(), SignRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HummingbirdModel.LAYER_LOCATION, HummingbirdModel::createBodyLayer);
        event.registerLayerDefinition(TakaheModel.LAYER_LOCATION, TakaheModel::createBodyLayer);

        for (BorealisBoatEntity.Type boatType : BorealisBoatEntity.Type.values()) {
            event.registerLayerDefinition(BorealisBoatRenderer.boatLayer(boatType), BoatModel::createBodyModel);
        }
    }

    public static void registerDimensionRenderers() {
        DimensionSpecialEffects borealisspecialEffects = new DimensionSpecialEffects(Float.NaN, false, DimensionSpecialEffects.SkyType.NORMAL, false, true) {
            private final float[] sunriseCol = new float[4];

            @Override
            public Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float sun) {
                return vector3d;
            }

            @Override
            public boolean isFoggyAt(int x, int y) {
                return false;
            }

            @Nullable
            @Override
            public float[] getSunriseColor(float time, float partialTicks) {
                float f1 = (int) Math.cos(time * ((float)Math.PI * 2F)) - 0.0F;
                if (f1 >= -0.4F && f1 <= 0.4F) {
                    float f3 = (f1 - -0.0F) / 0.4F * 0.5F + 0.5F;
                    float alpha = 1.0F - (1.0F - (int) Math.sin(f3 * (float)Math.PI)) * 0.99F;
                    alpha = alpha * alpha;
                    this.sunriseCol[0] = f3 * f3 * 0.7F + 0.1F;
                    this.sunriseCol[1] = f3 * f3 * 0.0F + 0.2F;
                    this.sunriseCol[2] = f3 * 0.3F + 0.5F;
                    this.sunriseCol[3] = alpha;
                    return this.sunriseCol;
                } else {
                    return null;
                }
            }

//            @Override
//            public ICloudRenderHandler getCloudRenderHandler() {
//                return new BorealisAuroraRenderer();
//            }
//
//            @Nullable
//            @Override
//            public ISkyRenderHandler getSkyRenderHandler() {
//                return new BorealisSkyRenderer();
//            }
//
//            @Override
//            public float getCloudHeight() {
//                return 160.0F;
//            }
        };
//        borealisspecialEffects.setCloudRenderHandler(new BorealisAuroraRenderer());
//        borealisspecialEffects.setSkyRenderHandler(new BorealisSkyRenderer());
        DimensionSpecialEffects.EFFECTS.put(BorealisDimensions.BOREALIS_TYPE.location(), borealisspecialEffects);

    }
}