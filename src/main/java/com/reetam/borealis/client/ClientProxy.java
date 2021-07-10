package com.reetam.borealis.client;

import com.reetam.borealis.block.BorealisPortalBlock;
import com.reetam.borealis.client.renderer.entity.HummingbirdRenderer;
import com.reetam.borealis.client.renderer.entity.MismicMuskoxRenderer;
import com.reetam.borealis.client.renderer.entity.TakaheRenderer;
import com.reetam.borealis.registry.BorealisEntities;
import com.reetam.borealis.registry.BorealisFluids;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import com.reetam.borealis.registry.BorealisBlocks;

import java.awt.*;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = "borealis", value = Dist.CLIENT)
public class ClientProxy {

    private static void render(Supplier<? extends Block> block, RenderType render) {
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }

    public static void registerBlockRenderers() {
        RenderType cutout = RenderType.cutout();
        RenderType translucent = RenderType.translucent();


        render(BorealisBlocks.brumal_door, cutout);
        render(BorealisBlocks.brumal_trapdoor, cutout);
        render(BorealisBlocks.frostfir_door, cutout);
        render(BorealisBlocks.frostfir_trapdoor, cutout);
        render(BorealisBlocks.saccharine_door, cutout);
        render(BorealisBlocks.saccharine_trapdoor, cutout);

        render(BorealisBlocks.hot_spring_water, translucent);
        RenderTypeLookup.setRenderLayer(BorealisFluids.hot_spring_water_flowing.get(), translucent);
        RenderTypeLookup.setRenderLayer(BorealisFluids.hot_spring_water_source.get(), translucent);
        render(BorealisBlocks.borealis_portal, translucent);
        render(BorealisBlocks.cloud, translucent);
    }

    public static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.HUMMINGBIRD.get(), HummingbirdRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.TAKAHE.get(), TakaheRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.MISMIC_MUSKOX.get(), MismicMuskoxRenderer::new);
    }
}