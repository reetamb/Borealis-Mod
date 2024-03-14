//package com.reetam.borealis.client.renderer;
//
//import com.mojang.blaze3d.systems.RenderSystem;
//import com.mojang.blaze3d.vertex.*;
//import com.reetam.borealis.BorealisMod;
//import com.reetam.borealis.block.HotSpringWaterBlock;
//import com.reetam.borealis.registry.BorealisBlocks;
//import net.minecraft.client.renderer.GameRenderer;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.block.LiquidBlock;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
//
//
//@OnlyIn(Dist.CLIENT)
//public class BorealisFluidRenderer {
//
//    private static final ResourceLocation TEXTURE_FALLBACK_UNDERWATER = new ResourceLocation("minecraft:textures/misc/underwater.png");
//    private static final ResourceLocation TEXTURE_HOT_SPRING_UNDERWATER = new ResourceLocation(BorealisMod.MODID + ":textures/fluid/hot_spring_water_overlay.png");
//
//    public static void fluidOverlay(CustomizeGuiOverlayEvent event) {
//        if (event..level.getBlockState(event.getBlockPos()).getBlock() == BorealisBlocks.HOT_SPRING_WATER_BLOCK.get()) {
//            RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
//            RenderSystem.setShaderColor(113 / 255F, 186 / 255F, 206 / 255F, 0.5F);
//            RenderSystem.setShaderTexture(7, getOverlayTexture(BorealisBlocks.HOT_SPRING_WATER_BLOCK.get()));
//            BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
//            float f = event.getPlayer().getBrightness();
//            RenderSystem.enableBlend();
//            RenderSystem.defaultBlendFunc();
//            float f7 = -event.getPlayer().yRot / 64.0F;
//            float f8 = event.getPlayer().xRot / 64.0F;
//            Matrix4f matrix4f = event.getPoseStack().last().pose();
//
//            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
//            bufferbuilder.vertex(matrix4f, -1.0F, -1.0F, -0.5F).color(f, f, f, 0.42F).uv(4.0F + f7, 4.0F + f8).endVertex();
//            bufferbuilder.vertex(matrix4f, 1.0F, -1.0F, -0.5F).color(f, f, f, 0.42F).uv(0.0F + f7, 4.0F + f8).endVertex();
//            bufferbuilder.vertex(matrix4f, 1.0F, 1.0F, -0.5F).color(f, f, f, 0.42F).uv(0.0F + f7, 0.0F + f8).endVertex();
//            bufferbuilder.vertex(matrix4f, -1.0F, 1.0F, -0.5F).color(f, f, f, 0.42F).uv(4.0F + f7, 0.0F + f8).endVertex();
//            bufferbuilder.end();
//
//            BufferUploader.end(bufferbuilder);
//            RenderSystem.disableBlend();
//            event.setCanceled(true);
//        }
//    }
//
//    public static ResourceLocation getOverlayTexture(LiquidBlock liquid) {
//        if (liquid instanceof HotSpringWaterBlock) {
//            return TEXTURE_HOT_SPRING_UNDERWATER;
//        } else {
//            return TEXTURE_FALLBACK_UNDERWATER;
//        }
//    }
//}
