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
        DimensionSpecialEffects borealisSpecialEffects = new BorealisSpecialEffects();
        DimensionSpecialEffects.EFFECTS.put(BorealisDimensions.BOREALIS_TYPE.location(), borealisSpecialEffects);

    }
}