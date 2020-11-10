package com.reetam.borealis.client;

import com.reetam.borealis.client.renderer.entity.HummingbirdRenderer;
import com.reetam.borealis.registry.BorealisEntities;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import com.reetam.borealis.client.renderer.entity.*;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;

import java.awt.*;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = "undergarden", value = Dist.CLIENT)
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


//        render(BorealisBlocks.deepturf_block, mipped);
//        RenderTypeLookup.setRenderLayer(UGFluids.virulent_mix_source.get(), translucent);
//        RenderTypeLookup.setRenderLayer(UGFluids.virulent_mix_flowing.get(), translucent);
    }

    public static void registerEntityRenderers() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.HUMMINGBIRD.get(), HummingbirdRenderer::new);
    }

//    public static void registerBlockColors() {
//        BlockColors colors = Minecraft.getInstance().getBlockColors();
//
//        colors.register((state, world, pos, tint) ->
//                        world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : new Color(91, 117, 91).getRGB(),
//                UGBlocks.deepturf_block.get(),
//                UGBlocks.deepturf.get(),
//                UGBlocks.shimmerweed.get(),
//                UGBlocks.tall_deepturf.get(),
//                UGBlocks.tall_shimmerweed.get(),
//                UGBlocks.gloomgourd_stem.get(),
//                UGBlocks.gloomgourd_stem_attached.get()
//        );
//
//        colors.register((state, world, pos, tint) ->
//                        new Color(54, 45, 66).getRGB(),
//                UGBlocks.gloomgourd_stem.get(),
//                UGBlocks.gloomgourd_stem_attached.get()
//        );
//    }

//    public static void registerItemColors() {
//        BlockColors bColors = Minecraft.getInstance().getBlockColors();
//        ItemColors iColors = Minecraft.getInstance().getItemColors();
//
//        iColors.register((stack, tint) -> bColors.getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, 0),
//                UGBlocks.deepturf_block.get(),
//                UGBlocks.deepturf.get(),
//                UGBlocks.shimmerweed.get(),
//                UGBlocks.tall_shimmerweed.get(),
//                UGBlocks.tall_deepturf.get()
//        );
//
//        iColors.register((stack, tint) -> {
//                    if(tint == 0) {
//                        return new Color(91, 117, 91).getRGB();
//                    }
//                    return -1;
//                },
//
//                UGBlocks.shimmerweed.get(),
//                UGBlocks.tall_shimmerweed.get()
//        );
//    }
}