package com.reetam.borealis.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.reetam.borealis.BorealisMod;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ICloudRenderHandler;

public class BorealisAuroraRenderer implements ICloudRenderHandler {

    private static final ResourceLocation TEXTURE_AURORA = new ResourceLocation(BorealisMod.MODID + ":textures/environment/aurora.png");
    private VertexBuffer cloudBuffer;

    private int prevCloudX = Integer.MIN_VALUE;
    private int prevCloudY = Integer.MIN_VALUE;
    private int prevCloudZ = Integer.MIN_VALUE;
    private Vec3 prevCloudColor = Vec3.ZERO;
    private boolean generateClouds = true;
    private CloudStatus prevCloudsType;

    @Override
    public void render(int ticks, float partialTicks, PoseStack poseStack, ClientLevel level, Minecraft mc, double x, double y, double z) {
        float cloudHeight = level.effects().getCloudHeight();
        double scale = 6.0;
        Matrix4f projectionMatrix = RenderSystem.getProjectionMatrix();

        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.depthMask(true);

        double movingX = ((float)ticks + partialTicks) * 0.03F;
        double xPos = (x + movingX) / scale;
        double yHeight = cloudHeight - (float)y + 0.33F;
        double zPos = z / scale + (double)0.33F;
        xPos -= Mth.floor(xPos / 2048.0D) * 2048;
        zPos -= Mth.floor(zPos / 2048.0D) * 2048;
        float dX = (float)(xPos - (double)Mth.floor(xPos));
        float dY = (float)(yHeight / 4.0D - (double)Mth.floor(yHeight / 4.0D)) * 4.0F;
        float dZ = (float)(zPos - (double)Mth.floor(zPos));
        Vec3 vec3 = level.getCloudColor(partialTicks);

        int cloudX = (int)Math.floor(xPos);
        int cloudY = (int)Math.floor(yHeight / 4.0D);
        int cloudZ = (int)Math.floor(zPos);
        if (cloudX != this.prevCloudX || cloudY != this.prevCloudY || cloudZ != this.prevCloudZ || Minecraft.getInstance().options.getCloudsType() != this.prevCloudsType || this.prevCloudColor.distanceToSqr(vec3) > 2.0E-4D) {
            this.prevCloudX = cloudX;
            this.prevCloudY = cloudY;
            this.prevCloudZ = cloudZ;
            this.prevCloudColor = vec3;
            this.prevCloudsType = Minecraft.getInstance().options.getCloudsType();
            this.generateClouds = true;
        }

        if (this.generateClouds) {
            this.generateClouds = false;
            BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
            if (this.cloudBuffer != null) {
                this.cloudBuffer.close();
            }

            this.cloudBuffer = new VertexBuffer();
            this.buildAurora(bufferbuilder, xPos, yHeight, zPos, vec3, level.getDayTime());
            bufferbuilder.end();
            this.cloudBuffer.upload(bufferbuilder);
        }

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        RenderSystem.setShaderTexture(0, TEXTURE_AURORA);
        FogRenderer.levelFogColor();
        poseStack.pushPose();
        poseStack.scale((float) scale, 1.0F, (float) scale);
        poseStack.translate(-dX, dY, -dZ);

        if (this.cloudBuffer != null) {
            int i1 = this.prevCloudsType == CloudStatus.FANCY ? 0 : 1;

            for(int l = i1; l < 2; ++l) {
                if (l == 0) {
                    RenderSystem.colorMask(false, false, false, false);
                } else {
                    RenderSystem.colorMask(true, true, true, true);
                }

                ShaderInstance shaderInstance = RenderSystem.getShader();
                this.cloudBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderInstance);
            }
        }

        poseStack.popPose();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

    private void buildAurora(BufferBuilder bufferBuilder, double x, double y, double z, Vec3 vector, float time) {
        final float fac0 = 0.00390625F;
        final float fac1 = 9.765625E-4F;
        
        float fx = (float)Math.floor(x) * fac0;
        float fz = (float)Math.floor(z) * fac0;

        float red = (float)vector.x;
        float green = (float)vector.y;
        float blue = (float)vector.z;

        double a = Math.abs(time - 6000) / 1200.0 * 0.1D;
        float alpha = (float) (a - 2*(Math.max(a,1)-1));

        float height = 80.0F;

        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL);
        float cloudHeight = (float)Math.floor(y / 4.0D) * 4.0F;
        if (this.prevCloudsType == CloudStatus.FANCY) {
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
