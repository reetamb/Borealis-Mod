package com.reetam.borealis.client.renderer.fluid;

import com.mojang.blaze3d.systems.RenderSystem;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.HotSpringWaterBlock;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;


@OnlyIn(Dist.CLIENT)
public class FluidRenderer {

    private static final ResourceLocation TEXTURE_FALLBACK_UNDERWATER = new ResourceLocation("minecraft:textures/misc/underwater.png");
    private static final ResourceLocation TEXTURE_HOT_SPRING_UNDERWATER = new ResourceLocation(BorealisMod.MODID + ":textures/fluid/hot_spring_water_overlay.png");

    public static void fluidOverlay(RenderBlockOverlayEvent event) {
        if (event.getPlayer().world.getBlockState(event.getBlockPos()).getBlock() == BorealisBlocks.hot_spring_water.get()) {
            Minecraft minecraftIn = Minecraft.getInstance();
            minecraftIn.getTextureManager().bindTexture(getOverlayTexture(BorealisBlocks.hot_spring_water.get()));
            BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
            float f = event.getPlayer().getBrightness();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            float f7 = -event.getPlayer().rotationYaw / 64.0F;
            float f8 = event.getPlayer().rotationPitch / 64.0F;
            Matrix4f matrix4f = event.getMatrixStack().getLast().getMatrix();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
            bufferbuilder.pos(matrix4f, -1.0F, -1.0F, -0.5F).color(f, f, f, 0.42F).tex(4.0F + f7, 4.0F + f8).endVertex();
            bufferbuilder.pos(matrix4f, 1.0F, -1.0F, -0.5F).color(f, f, f, 0.42F).tex(0.0F + f7, 4.0F + f8).endVertex();
            bufferbuilder.pos(matrix4f, 1.0F, 1.0F, -0.5F).color(f, f, f, 0.42F).tex(0.0F + f7, 0.0F + f8).endVertex();
            bufferbuilder.pos(matrix4f, -1.0F, 1.0F, -0.5F).color(f, f, f, 0.42F).tex(4.0F + f7, 0.0F + f8).endVertex();
            bufferbuilder.finishDrawing();
            WorldVertexBufferUploader.draw(bufferbuilder);
            RenderSystem.disableBlend();
            event.setCanceled(true);
        }
    }

    public static ResourceLocation getOverlayTexture(FlowingFluidBlock fluid) {
        if (fluid instanceof HotSpringWaterBlock) {
            return TEXTURE_HOT_SPRING_UNDERWATER;
        } else {
            return TEXTURE_FALLBACK_UNDERWATER;
        }
    }
}
