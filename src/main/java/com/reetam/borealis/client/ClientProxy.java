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

//    public static void registerBlockRenderers() {
//        RenderType cutout = RenderType.getCutout();
//        RenderType mipped = RenderType.getCutoutMipped();
//        RenderType translucent = RenderType.getTranslucent();
//
//        render(UGBlocks.deepturf_block, mipped);
//        render(UGBlocks.deepturf, cutout);
//        render(UGBlocks.shimmerweed, cutout);
//        render(UGBlocks.smogstem_sapling, cutout);
//        render(UGBlocks.wigglewood_sapling, cutout);
//        render(UGBlocks.indigo_mushroom, cutout);
//        render(UGBlocks.veil_mushroom, cutout);
//        render(UGBlocks.ink_mushroom, cutout);
//        render(UGBlocks.blood_mushroom, cutout);
//        render(UGBlocks.underbean_bush, cutout);
//        render(UGBlocks.ditchbulb_plant, cutout);
//        render(UGBlocks.tall_deepturf, cutout);
//        render(UGBlocks.tall_shimmerweed, cutout);
//        render(UGBlocks.cloggrum_bars, cutout);
//        render(UGBlocks.glowing_kelp, cutout);
//        render(UGBlocks.glowing_kelp_plant, cutout);
//        render(UGBlocks.undergarden_portal, translucent);
//        render(UGBlocks.goo, translucent);
//        render(UGBlocks.smogstem_door, cutout);
//        render(UGBlocks.wigglewood_door, cutout);
//        render(UGBlocks.smogstem_trapdoor, cutout);
//        render(UGBlocks.wigglewood_trapdoor, cutout);
//        render(UGBlocks.ashen_deepturf, cutout);
//        render(UGBlocks.blisterberry_bush, cutout);
//        render(UGBlocks.gloomgourd_stem, cutout);
//        render(UGBlocks.gloomgourd_stem_attached, cutout);
//        render(UGBlocks.shard_torch, cutout);
//        render(UGBlocks.shard_wall_torch, cutout);
//        render(UGBlocks.droopvine_top, cutout);
//        render(UGBlocks.droopvine, cutout);
//        render(UGBlocks.gronglet, cutout);
//        render(UGBlocks.grongle_door, cutout);
//        render(UGBlocks.grongle_trapdoor, cutout);
//        render(UGBlocks.virulent_mix, translucent);
//        render(UGBlocks.seeping_ink, cutout);
//        render(UGBlocks.mushroom_veil, cutout);
//        render(UGBlocks.mushroom_veil_top, cutout);
//
//        RenderTypeLookup.setRenderLayer(UGFluids.virulent_mix_source.get(), translucent);
//        RenderTypeLookup.setRenderLayer(UGFluids.virulent_mix_flowing.get(), translucent);
//    }

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