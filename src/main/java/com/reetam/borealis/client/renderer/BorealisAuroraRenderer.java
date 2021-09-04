package com.reetam.borealis.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.reetam.borealis.BorealisMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.settings.CloudOption;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.ICloudRenderHandler;

public class BorealisAuroraRenderer implements ICloudRenderHandler {

    private static final ResourceLocation TEXTURE_AURORA = new ResourceLocation(BorealisMod.MODID + ":textures/environment/aurora.png");
    private VertexBuffer cloudBuffer;
    
    private int prevCloured = Integer.MIN_VALUE;
    private int prevCloudY = Integer.MIN_VALUE;
    private int prevCloudZ = Integer.MIN_VALUE;
    private Vector3d prevCloudColor = Vector3d.ZERO;
    private boolean generateClouds = true;
    private CloudOption prevCloudsType;

    @Override
    public void render(int ticks, float partialTicks, MatrixStack matrixStack, ClientWorld world, Minecraft mc, double xIn, double yIn, double zIn) {
        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableDepthTest();
        RenderSystem.defaultAlphaFunc();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableFog();
        RenderSystem.depthMask(true);

        double x = xIn / 4.0D + 0.33F;
        double y = world.effects().getCloudHeight() - (float)yIn + 0.33F;
        double z = zIn / 4.0D + 0.33F;
        x = x - (MathHelper.floor(x / 2048.0D) * 2048);
        z = z - (MathHelper.floor(z / 2048.0D) * 2048);
        float f3 = (float)(x - MathHelper.floor(x));
        float f4 = (float)(y / 4.0D - MathHelper.floor(y / 4.0D)) * 4.0F;
        float f5 = (float)(z - MathHelper.floor(z));
        Vector3d vector3d = new Vector3d(1.0F, 1.0F, 1.0F);
        int i = (int)Math.floor(x);
        int j = (int)Math.floor(y / 4.0D);
        int k = (int)Math.floor(z);
        if (i != this.prevCloured || j != this.prevCloudY || k != this.prevCloudZ || mc.options.getCloudsType() != this.prevCloudsType || this.prevCloudColor.distanceToSqr(vector3d) > 2.0E-4D) {
            this.prevCloured = i;
            this.prevCloudY = j;
            this.prevCloudZ = k;
            this.prevCloudColor = vector3d;
            this.prevCloudsType = mc.options.getCloudsType();
            this.generateClouds = true;
        }

        if (this.generateClouds) {
            this.generateClouds = false;
            BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
            if (this.cloudBuffer != null) {
                this.cloudBuffer.close();
            }

            this.cloudBuffer = new VertexBuffer(DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
            buildAurora(bufferbuilder, x, y, z, vector3d, world.getTimeOfDay(partialTicks));
            bufferbuilder.end();
            this.cloudBuffer.upload(bufferbuilder);
        }

        mc.getTextureManager().bind(TEXTURE_AURORA);
        matrixStack.pushPose();
        matrixStack.scale(2.0F, 1.0F, 2.0F);
        matrixStack.translate((-f3), f4, (-f5));
        if (this.cloudBuffer != null) {
            this.cloudBuffer.bind();
            DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL.setupBufferState(0L);
            int isFancyCloud = this.prevCloudsType == CloudOption.FANCY ? 0 : 1;

            for(int l = isFancyCloud; l < 2; ++l) {
                if (l == 0) {
                    RenderSystem.colorMask(false, false, false, false);
                } else {
                    RenderSystem.colorMask(true, true, true, true);
                }

                this.cloudBuffer.draw(matrixStack.last().pose(), 7);
            }

            VertexBuffer.unbind();
            DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL.clearBufferState();
        }

        matrixStack.popPose();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableAlphaTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

    private void buildAurora(BufferBuilder bufferBuilder, double xIn, double yIn, double zIn, Vector3d vector, float time) {
        final float fac0 = 0.00390625F;
        final float fac1 = 9.765625E-4F;
        
        float fx = (float)MathHelper.floor(xIn) * fac0;
        float fz = (float)MathHelper.floor(zIn) * fac0;

        float red = (float)vector.x;
        float green = (float)vector.y;
        float blue = (float)vector.z;

        double a = Math.abs(time - 6000) / 1200.0 * 0.1D;
        float alpha = (float) (a - 2*(Math.max(a,1)-1));

        float height = 80.0F;
        
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
        float cloudHeight = (float)Math.floor(yIn / 4.0D) * 4.0F;
        if (this.prevCloudsType == CloudOption.FANCY) {
            for(int k0 = -3; k0 <= 4; ++k0) {
                for(int l0 = -3; l0 <= 4; ++l0) {
                    float k1 = (float)(k0 * 8);
                    float l1 = (float)(l0 * 8);
                    if (cloudHeight > -5.0F) {
                        bufferBuilder.vertex((k1 + 0.0F), (cloudHeight + 0.0F), (l1 + 8.0F)).uv((k1 + 0.0F) * fac0 + fx, (l1 + 8.0F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, -1.0F, 0.0F).endVertex();
                        bufferBuilder.vertex((k1 + 8.0F), (cloudHeight + 0.0F), (l1 + 8.0F)).uv((k1 + 8.0F) * fac0 + fx, (l1 + 8.0F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, -1.0F, 0.0F).endVertex();
                        bufferBuilder.vertex((k1 + 8.0F), (cloudHeight + 0.0F), (l1 + 0.0F)).uv((k1 + 8.0F) * fac0 + fx, (l1 + 0.0F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, -1.0F, 0.0F).endVertex();
                        bufferBuilder.vertex((k1 + 0.0F), (cloudHeight + 0.0F), (l1 + 0.0F)).uv((k1 + 0.0F) * fac0 + fx, (l1 + 0.0F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, -1.0F, 0.0F).endVertex();
                    }

                    if (cloudHeight <= 5.0F) {
                        bufferBuilder.vertex((k1 + 0.0F), (cloudHeight + height - fac1), (l1 + 8.0F)).uv((k1 + 0.0F) * fac0 + fx, (l1 + 8.0F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, 1.0F, 0.0F).endVertex();
                        bufferBuilder.vertex((k1 + 8.0F), (cloudHeight + height - fac1), (l1 + 8.0F)).uv((k1 + 8.0F) * fac0 + fx, (l1 + 8.0F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, 1.0F, 0.0F).endVertex();
                        bufferBuilder.vertex((k1 + 8.0F), (cloudHeight + height - fac1), (l1 + 0.0F)).uv((k1 + 8.0F) * fac0 + fx, (l1 + 0.0F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, 1.0F, 0.0F).endVertex();
                        bufferBuilder.vertex((k1 + 0.0F), (cloudHeight + height - fac1), (l1 + 0.0F)).uv((k1 + 0.0F) * fac0 + fx, (l1 + 0.0F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, 1.0F, 0.0F).endVertex();
                    }

                    if (k0 > -1) {
                        for(int i1 = 0; i1 < 8; ++i1) {
                            bufferBuilder.vertex((k1 + (float)i1 + 0.0F), (cloudHeight + 0.0F), (l1 + 8.0F)).uv((k1 + (float)i1 + 0.5F) * fac0 + fx, (l1 + 8.0F) * fac0 + fz).color(red, green, blue, alpha).normal(-1.0F, 0.0F, 0.0F).endVertex();
                            bufferBuilder.vertex((k1 + (float)i1 + 0.0F), (cloudHeight + height), (l1 + 8.0F)).uv((k1 + (float)i1 + 0.5F) * fac0 + fx, (l1 + 8.0F) * fac0 + fz).color(red, green, blue, alpha).normal(-1.0F, 0.0F, 0.0F).endVertex();
                            bufferBuilder.vertex((k1 + (float)i1 + 0.0F), (cloudHeight + height), (l1 + 0.0F)).uv((k1 + (float)i1 + 0.5F) * fac0 + fx, (l1 + 0.0F) * fac0 + fz).color(red, green, blue, alpha).normal(-1.0F, 0.0F, 0.0F).endVertex();
                            bufferBuilder.vertex((k1 + (float)i1 + 0.0F), (cloudHeight + 0.0F), (l1 + 0.0F)).uv((k1 + (float)i1 + 0.5F) * fac0 + fx, (l1 + 0.0F) * fac0 + fz).color(red, green, blue, alpha).normal(-1.0F, 0.0F, 0.0F).endVertex();
                        }
                    }

                    if (k0 <= 1) {
                        for(int j2 = 0; j2 < 8; ++j2) {
                            bufferBuilder.vertex((k1 + (float)j2 + 1.0F - fac1), (cloudHeight + 0.0F), (l1 + 8.0F)).uv((k1 + (float)j2 + 0.5F) * fac0 + fx, (l1 + 8.0F) * fac0 + fz).color(red, green, blue, alpha).normal(1.0F, 0.0F, 0.0F).endVertex();
                            bufferBuilder.vertex((k1 + (float)j2 + 1.0F - fac1), (cloudHeight + height), (l1 + 8.0F)).uv((k1 + (float)j2 + 0.5F) * fac0 + fx, (l1 + 8.0F) * fac0 + fz).color(red, green, blue, alpha).normal(1.0F, 0.0F, 0.0F).endVertex();
                            bufferBuilder.vertex((k1 + (float)j2 + 1.0F - fac1), (cloudHeight + height), (l1 + 0.0F)).uv((k1 + (float)j2 + 0.5F) * fac0 + fx, (l1 + 0.0F) * fac0 + fz).color(red, green, blue, alpha).normal(1.0F, 0.0F, 0.0F).endVertex();
                            bufferBuilder.vertex((k1 + (float)j2 + 1.0F - fac1), (cloudHeight + 0.0F), (l1 + 0.0F)).uv((k1 + (float)j2 + 0.5F) * fac0 + fx, (l1 + 0.0F) * fac0 + fz).color(red, green, blue, alpha).normal(1.0F, 0.0F, 0.0F).endVertex();
                        }
                    }

                    if (l0 > -1) {
                        for(int k2 = 0; k2 < 8; ++k2) {
                            bufferBuilder.vertex((k1 + 0.0F), (cloudHeight + height), (l1 + (float)k2 + 0.0F)).uv((k1 + 0.0F) * fac0 + fx, (l1 + (float)k2 + 0.5F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, 0.0F, -1.0F).endVertex();
                            bufferBuilder.vertex((k1 + 8.0F), (cloudHeight + height), (l1 + (float)k2 + 0.0F)).uv((k1 + 8.0F) * fac0 + fx, (l1 + (float)k2 + 0.5F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, 0.0F, -1.0F).endVertex();
                            bufferBuilder.vertex((k1 + 8.0F), (cloudHeight + 0.0F), (l1 + (float)k2 + 0.0F)).uv((k1 + 8.0F) * fac0 + fx, (l1 + (float)k2 + 0.5F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, 0.0F, -1.0F).endVertex();
                            bufferBuilder.vertex((k1 + 0.0F), (cloudHeight + 0.0F), (l1 + (float)k2 + 0.0F)).uv((k1 + 0.0F) * fac0 + fx, (l1 + (float)k2 + 0.5F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, 0.0F, -1.0F).endVertex();
                        }
                    }

                    if (l0 <= 1) {
                        for(int l2 = 0; l2 < 8; ++l2) {
                            bufferBuilder.vertex((k1 + 0.0F), (cloudHeight + height), (l1 + (float)l2 + 1.0F - fac1)).uv((k1 + 0.0F) * fac0 + fx, (l1 + (float)l2 + 0.5F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, 0.0F, 1.0F).endVertex();
                            bufferBuilder.vertex((k1 + 8.0F), (cloudHeight + height), (l1 + (float)l2 + 1.0F - fac1)).uv((k1 + 8.0F) * fac0 + fx, (l1 + (float)l2 + 0.5F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, 0.0F, 1.0F).endVertex();
                            bufferBuilder.vertex((k1 + 8.0F), (cloudHeight + 0.0F), (l1 + (float)l2 + 1.0F - fac1)).uv((k1 + 8.0F) * fac0 + fx, (l1 + (float)l2 + 0.5F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, 0.0F, 1.0F).endVertex();
                            bufferBuilder.vertex((k1 + 0.0F), (cloudHeight + 0.0F), (l1 + (float)l2 + 1.0F - fac1)).uv((k1 + 0.0F) * fac0 + fx, (l1 + (float)l2 + 0.5F) * fac0 + fz).color(red, green, blue, alpha).normal(0.0F, 0.0F, 1.0F).endVertex();
                        }
                    }
                }
            }
        }
    }
}
