package com.reetam.borealis.client;

import com.reetam.borealis.entity.renderer.BorealisBoatRenderer;
import com.reetam.borealis.entity.renderer.HummingbirdRenderer;
import com.reetam.borealis.entity.renderer.MismicMuskoxRenderer;
import com.reetam.borealis.entity.renderer.TakaheRenderer;
import com.reetam.borealis.registry.BorealisEntities;
import com.reetam.borealis.registry.BorealisFluids;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import com.reetam.borealis.registry.BorealisBlocks;

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
    }

    public static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.HUMMINGBIRD.get(), HummingbirdRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.TAKAHE.get(), TakaheRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.MISMIC_MUSKOX.get(), MismicMuskoxRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.BOAT.get(), BorealisBoatRenderer::new);
    }
}