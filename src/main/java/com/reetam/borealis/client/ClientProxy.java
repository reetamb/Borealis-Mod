package com.reetam.borealis.client;

import com.reetam.borealis.block.BorealisPortalBlock;
import com.reetam.borealis.client.renderer.entity.HummingbirdRenderer;
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
        RenderType cutout = RenderType.getCutout();
        RenderType mipped = RenderType.getCutoutMipped();
        RenderType translucent = RenderType.getTranslucent();


        render(BorealisBlocks.brumal_door, cutout);
        render(BorealisBlocks.brumal_trapdoor, cutout);
        render(BorealisBlocks.frostfir_door, cutout);
        render(BorealisBlocks.frostfir_trapdoor, cutout);
        render(BorealisBlocks.saccharine_door, cutout);
        render(BorealisBlocks.saccharine_trapdoor, cutout);


        render(BorealisBlocks.lichen_block, mipped);

        render(BorealisBlocks.hot_spring_water, translucent);
        RenderTypeLookup.setRenderLayer(BorealisFluids.hot_spring_water_flowing.get(), translucent);
        RenderTypeLookup.setRenderLayer(BorealisFluids.hot_spring_water_source.get(), translucent);
        render(BorealisBlocks.borealis_portal, translucent);
        render(BorealisBlocks.cloud, translucent);
    }

    public static void registerEntityRenderers() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.HUMMINGBIRD.get(), HummingbirdRenderer::new);
    }

    public static void registerBlockColors() {
        BlockColors colors = Minecraft.getInstance().getBlockColors();

        colors.register((state, world, pos, tint) ->
                        world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : new Color(181, 202, 250).getRGB(),
                BorealisBlocks.lichen_block.get()
        );
    }

    public static void registerItemColors() {
        BlockColors bColors = Minecraft.getInstance().getBlockColors();
        ItemColors iColors = Minecraft.getInstance().getItemColors();

        iColors.register((stack, tint) -> bColors.getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, 0),
                BorealisBlocks.lichen_block.get()
        );

        iColors.register((stack, tint) -> {
                    if(tint == 0) {
                        return new Color(181, 202, 250).getRGB();
                    }
                    return -1;
                }
        );
    }
}