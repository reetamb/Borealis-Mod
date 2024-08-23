package com.reetam.borealis;

import com.reetam.borealis.client.BorealisSpecialEffects;
import com.reetam.borealis.client.renderer.BorealisAuroraRenderer;
import com.reetam.borealis.entity.BorealisBoatEntity;
import com.reetam.borealis.entity.model.HummingbirdModel;
import com.reetam.borealis.entity.model.TakaheModel;
import com.reetam.borealis.entity.model.ThrusherModel;
import com.reetam.borealis.entity.renderer.*;
import com.reetam.borealis.registry.*;
import com.reetam.borealis.registry.world.BorealisWorld;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = "borealis", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BorealisClient {

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
        render(BorealisBlocks.SWEETWOOD_DOOR, cutout);
        render(BorealisBlocks.SWEETWOOD_TRAPDOOR, cutout);
        render(BorealisBlocks.SWEETWOOD_SAPLING, cutout);
        render(BorealisBlocks.POTTED_SWEETWOOD_SAPLING, cutout);
        render(BorealisBlocks.CARAMELIZED_DOOR, cutout);
        render(BorealisBlocks.CARAMELIZED_TRAPDOOR, cutout);
        render(BorealisBlocks.EMBEDDED_KYANITE_ARROW, cutout);
        render(BorealisBlocks.HOLLY, cutout);
        render(BorealisBlocks.MISTERIA_HEAD, cutout);
        render(BorealisBlocks.MISTERIA_BODY, cutout);
        render(BorealisBlocks.BRUMELIAD, cutout);
        render(BorealisBlocks.WINTER_VIOLA, cutout);
        render(BorealisBlocks.WINTER_VIOLIN, cutout);
        render(BorealisBlocks.WALL_WINTER_VIOLIN, cutout);
        render(BorealisBlocks.WINTER_CELLO, cutout);

        render(BorealisFluids.HOT_SPRING_WATER_BLOCK, translucent);
        ItemBlockRenderTypes.setRenderLayer(BorealisFluids.HOT_SPRING_WATER_FLOWING.get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(BorealisFluids.HOT_SPRING_WATER_SOURCE.get(), translucent);
        render(BorealisBlocks.BOREALIS_PORTAL, translucent);
        render(BorealisBlocks.CLOUD, translucent);

        render(BorealisBlocks.STATIC_FIELD, cutout);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BorealisEntities.HUMMINGBIRD.get(), HummingbirdRenderer::new);
        event.registerEntityRenderer(BorealisEntities.TAKAHE.get(), TakaheRenderer::new);
        event.registerEntityRenderer(BorealisEntities.THRUSHER.get(), ThrusherRenderer::new);
        event.registerEntityRenderer(BorealisEntities.BOAT.get(), BorealisBoatRenderer::new);
        event.registerEntityRenderer(BorealisEntities.HAIL.get(), HailRenderer::new);
        event.registerEntityRenderer(BorealisEntities.KYANITE_ARROW.get(), KyaniteArrowRenderer::new);

        event.registerBlockEntityRenderer(BorealisBlockEntities.BOREALIS_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(BorealisBlockEntities.BOREALIS_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

//    @SubscribeEvent
//    public static void registerDimensionEffects(RegisterDimensionSpecialEffectsEvent event) {
//        event.register(new ResourceLocation(BorealisMod.MODID, "borealis"), new BorealisSpecialEffects());
//    }

    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HummingbirdModel.LAYER_LOCATION, HummingbirdModel::createBodyLayer);
        event.registerLayerDefinition(TakaheModel.LAYER_LOCATION, TakaheModel::createBodyLayer);
        event.registerLayerDefinition(ThrusherModel.LAYER_LOCATION, ThrusherModel::createBodyLayer);

        for (BorealisBoatEntity.Type boatType : BorealisBoatEntity.Type.values()) {
            event.registerLayerDefinition(BorealisBoatRenderer.boatLayer(boatType), BoatModel::createBodyModel);
        }
    }

    @SubscribeEvent
    public static void registerDimensionRenderers(RegisterDimensionSpecialEffectsEvent event) {
        new BorealisAuroraRenderer();
        event.register(BorealisWorld.BOREALIS_DIMENSION_TYPE.location(), new BorealisSpecialEffects());

    }
}