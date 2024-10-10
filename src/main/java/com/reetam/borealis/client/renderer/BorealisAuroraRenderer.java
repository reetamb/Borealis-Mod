package com.reetam.borealis.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
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
import org.joml.Matrix4f;

public class BorealisAuroraRenderer {

    private static final ResourceLocation TEXTURE_AURORA = ResourceLocation.read(BorealisMod.MODID + ":textures/environment/aurora.png").getOrThrow();
    private VertexBuffer cloudBuffer;

    private int prevCloudX = Integer.MIN_VALUE;
    private int prevCloudY = Integer.MIN_VALUE;
    private int prevCloudZ = Integer.MIN_VALUE;
    private Vec3 prevCloudColor = Vec3.ZERO;
    private boolean generateClouds = true;
    private CloudStatus prevCloudsType;

    public void render(ClientLevel level, int ticks, float partialTicks, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
        float cloudHeight = level.effects().getCloudHeight();
        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.depthMask(true);
        double d1 = ((float)ticks + partialTicks) * 0.03F;
        double d2 = (camX + d1) / 12.0;
        double d3 = cloudHeight - (float)camY + 0.33F;
        double d4 = camZ / 12.0 + 0.33000001311302185;
        d2 -= (Mth.floor(d2 / 2048.0) * 2048);
        d4 -= (Mth.floor(d4 / 2048.0) * 2048);
        float fx = (float)(d2 - Mth.floor(d2));
        float fz = (float)(d3 / 4.0 - Mth.floor(d3 / 4.0)) * 4.0F;
        float f5 = (float)(d4 - Mth.floor(d4));
        Vec3 vec3 = new Vec3(1, 1, 1);
        int i = (int)Math.floor(d2);
        int j = (int)Math.floor(d3 / 4.0);
        int k = (int)Math.floor(d4);
        if (i != this.prevCloudX || j != this.prevCloudY || k != this.prevCloudZ || Minecraft.getInstance().options.getCloudsType() != this.prevCloudsType || this.prevCloudColor.distanceToSqr(vec3) > 2.0E-4) {
            this.prevCloudX = i;
            this.prevCloudY = j;
            this.prevCloudZ = k;
            this.prevCloudColor = vec3;
            this.prevCloudsType = Minecraft.getInstance().options.getCloudsType();
            this.generateClouds = true;
        }

        if (this.generateClouds) {
            this.generateClouds = false;
            if (this.cloudBuffer != null) {
                this.cloudBuffer.close();
            }

            this.cloudBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
            BufferBuilder bufferbuilder = buildAurora(Tesselator.getInstance(), d2, d3, d4, vec3, level.getDayTime());
            this.cloudBuffer.bind();
            this.cloudBuffer.upload(bufferbuilder.build());
            VertexBuffer.unbind();
        }

        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, TEXTURE_AURORA);
        // for some reason getSkyColor is returning (0, 0, 0)
        Vec3 skyColor = new Vec3(154, 172, 234);
                // level.getSkyColor(Minecraft.getInstance().gameRenderer.getMainCamera().getPosition(), partialTicks);
        RenderSystem.setShaderFogColor((float) (skyColor.x / 255F), (float) skyColor.y / 255F, (float) skyColor.z / 255F);
        RenderSystem.setShaderFogStart(Minecraft.getInstance().gameRenderer.getRenderDistance());
        RenderSystem.setShaderFogEnd(1.5F * Minecraft.getInstance().gameRenderer.getRenderDistance());
        FogRenderer.levelFogColor();
        RenderSystem.setShaderFogColor((float) (skyColor.x / 255F), (float) skyColor.y / 255F, (float) skyColor.z / 255F);
        poseStack.pushPose();
        poseStack.scale(4.0F, 1.0F, 4.0F);
        poseStack.translate(-fx, fz, -f5);
        if (this.cloudBuffer != null) {
            this.cloudBuffer.bind();
            int l = this.prevCloudsType == CloudStatus.FANCY ? 0 : 1;

            for(int i1 = l; i1 < 2; ++i1) {
                if (i1 == 0) {
                    RenderSystem.colorMask(false, false, false, false);
                } else {
                    RenderSystem.colorMask(true, true, true, true);
                }

                ShaderInstance shaderinstance = RenderSystem.getShader();
                this.cloudBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
            }

            VertexBuffer.unbind();

            poseStack.popPose();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
        }
    }

    private BufferBuilder buildAurora(Tesselator tesselator, double x, double y, double z, Vec3 vector, float time) {
        final float f0 = 0.00390625F;
        final float f1 = 9.765625E-4F;

        float fx = (float)Math.floor(x) * f0;
        float fz = (float)Math.floor(z) * f0;

        float red = (float)vector.x;
        float green = (float)vector.y;
        float blue = (float)vector.z;

        // double a = Math.abs(time - 6000) / 1200.0 * 0.1D;
        float alpha = 1;

        float height = 80.0F;

        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        BufferBuilder bufferBuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL);
        float vertY = (float)Math.floor(y / 4.0D) * 4.0F;
        if (this.prevCloudsType == CloudStatus.FANCY) {
            for(int chunkX = -3; chunkX <= 4; ++chunkX) {
                for(int chunkZ = -3; chunkZ <= 4; ++chunkZ) {
                    float vertX = (float)(chunkX * 8);
                    float vertZ = (float)(chunkZ * 8);
                    alpha = 1 - ((chunkX * chunkX + chunkZ * chunkZ) / 32.0F);
                    if (vertY > -5.0F) {
                        bufferBuilder.addVertex((vertX + 0.0F), (vertY + 0.0F), (vertZ + 8.0F))
                                .setUv((vertX + 0.0F) * f0 + fx, (vertZ + 8.0F) * f0 + fz)
                                .setColor(red, green, blue, alpha).setNormal(0.0F, -1.0F, 0.0F);
                        bufferBuilder.addVertex((vertX + 8.0F), (vertY + 0.0F), (vertZ + 8.0F))
                                .setUv((vertX + 8.0F) * f0 + fx, (vertZ + 8.0F) * f0 + fz)
                                .setColor(red, green, blue, alpha).setNormal(0.0F, -1.0F, 0.0F);
                        bufferBuilder.addVertex((vertX + 8.0F), (vertY + 0.0F), (vertZ + 0.0F))
                                .setUv((vertX + 8.0F) * f0 + fx, (vertZ + 0.0F) * f0 + fz)
                                .setColor(red, green, blue, alpha).setNormal(0.0F, -1.0F, 0.0F);
                        bufferBuilder.addVertex((vertX + 0.0F), (vertY + 0.0F), (vertZ + 0.0F))
                                .setUv((vertX + 0.0F) * f0 + fx, (vertZ + 0.0F) * f0 + fz)
                                .setColor(red, green, blue, alpha).setNormal(0.0F, -1.0F, 0.0F);
                    }

                    if (vertY <= 5.0F) {
                        bufferBuilder.addVertex((vertX + 0.0F), (vertY + height - f1), (vertZ + 8.0F))
                                .setUv((vertX + 0.0F) * f0 + fx, (vertZ + 8.0F) * f0 + fz)
                                .setColor(red, green, blue, alpha).setNormal(0.0F, 1.0F, 0.0F);
                        bufferBuilder.addVertex((vertX + 8.0F), (vertY + height - f1), (vertZ + 8.0F))
                                .setUv((vertX + 8.0F) * f0 + fx, (vertZ + 8.0F) * f0 + fz)
                                .setColor(red, green, blue, alpha).setNormal(0.0F, 1.0F, 0.0F);
                        bufferBuilder.addVertex((vertX + 8.0F), (vertY + height - f1), (vertZ + 0.0F))
                                .setUv((vertX + 8.0F) * f0 + fx, (vertZ + 0.0F) * f0 + fz)
                                .setColor(red, green, blue, alpha).setNormal(0.0F, 1.0F, 0.0F);
                        bufferBuilder.addVertex((vertX + 0.0F), (vertY + height - f1), (vertZ + 0.0F))
                                .setUv((vertX + 0.0F) * f0 + fx, (vertZ + 0.0F) * f0 + fz)
                                .setColor(red, green, blue, alpha).setNormal(0.0F, 1.0F, 0.0F);
                    }

                    float l2;
                    if (chunkX > -1) { // the east half (incl. center)
                        for(l2 = 0; l2 < 8; ++l2) {
                            bufferBuilder.addVertex((vertX + l2 + 0.0F), (vertY + 0.0F), (vertZ + 8.0F))
                                    .setUv((vertX + l2 + 0.5F) * f0 + fx, (vertZ + 8.0F) * f0 + fz)
                                    .setColor(red, green, blue, alpha).setNormal(-1.0F, 0.0F, 0.0F);
                            bufferBuilder.addVertex((vertX + l2 + 0.0F), (vertY + height), (vertZ + 8.0F))
                                    .setUv((vertX + l2 + 0.5F) * f0 + fx, (vertZ + 8.0F) * f0 + fz)
                                    .setColor(red, green, blue, alpha).setNormal(-1.0F, 0.0F, 0.0F);
                            bufferBuilder.addVertex((vertX + l2 + 0.0F), (vertY + height), (vertZ + 0.0F))
                                    .setUv((vertX + l2 + 0.5F) * f0 + fx, (vertZ + 0.0F) * f0 + fz)
                                    .setColor(red, green, blue, alpha).setNormal(-1.0F, 0.0F, 0.0F);
                            bufferBuilder.addVertex((vertX + l2 + 0.0F), (vertY + 0.0F), (vertZ + 0.0F))
                                    .setUv((vertX + l2 + 0.5F) * f0 + fx, (vertZ + 0.0F) * f0 + fz)
                                    .setColor(red, green, blue, alpha).setNormal(-1.0F, 0.0F, 0.0F);
                        }
                    }

                    if (chunkX <= 1) { // the west half (incl. center)
                        for(l2 = 0; l2 < 8; ++l2) {
                            bufferBuilder.addVertex((vertX + l2 + 1.0F - f1), (vertY + 0.0F), (vertZ + 8.0F)).setUv((vertX + l2 + 0.5F) * f0 + fx, (vertZ + 8.0F) * f0 + fz).setColor(red, green, blue, alpha).setNormal(1.0F, 0.0F, 0.0F);
                            bufferBuilder.addVertex((vertX + l2 + 1.0F - f1), (vertY + height), (vertZ + 8.0F)).setUv((vertX + l2 + 0.5F) * f0 + fx, (vertZ + 8.0F) * f0 + fz).setColor(red, green, blue, alpha).setNormal(1.0F, 0.0F, 0.0F);
                            bufferBuilder.addVertex((vertX + l2 + 1.0F - f1), (vertY + height), (vertZ + 0.0F)).setUv((vertX + l2 + 0.5F) * f0 + fx, (vertZ + 0.0F) * f0 + fz).setColor(red, green, blue, alpha).setNormal(1.0F, 0.0F, 0.0F);
                            bufferBuilder.addVertex((vertX + l2 + 1.0F - f1), (vertY + 0.0F), (vertZ + 0.0F)).setUv((vertX + l2 + 0.5F) * f0 + fx, (vertZ + 0.0F) * f0 + fz).setColor(red, green, blue, alpha).setNormal(1.0F, 0.0F, 0.0F);
                        }
                    }

                    if (chunkZ > -1) { // the south half (incl. center)
                        for(l2 = 0; l2 < 8; ++l2) {
                            bufferBuilder.addVertex((vertX + 0.0F), (vertY + height), (vertZ + l2 + 0.0F)).setUv((vertX + 0.0F) * f0 + fx, (vertZ + l2 + 0.5F) * f0 + fz).setColor(red, green, blue, alpha).setNormal(0.0F, 0.0F, -1.0F);
                            bufferBuilder.addVertex((vertX + 8.0F), (vertY + height), (vertZ + l2 + 0.0F)).setUv((vertX + 8.0F) * f0 + fx, (vertZ + l2 + 0.5F) * f0 + fz).setColor(red, green, blue, alpha).setNormal(0.0F, 0.0F, -1.0F);
                            bufferBuilder.addVertex((vertX + 8.0F), (vertY + 0.0F), (vertZ + l2 + 0.0F)).setUv((vertX + 8.0F) * f0 + fx, (vertZ + l2 + 0.5F) * f0 + fz).setColor(red, green, blue, alpha).setNormal(0.0F, 0.0F, -1.0F);
                            bufferBuilder.addVertex((vertX + 0.0F), (vertY + 0.0F), (vertZ + l2 + 0.0F)).setUv((vertX + 0.0F) * f0 + fx, (vertZ + l2 + 0.5F) * f0 + fz).setColor(red, green, blue, alpha).setNormal(0.0F, 0.0F, -1.0F);
                        }
                    }

                    if (chunkZ <= 1) { // the north half (incl. center)
                        for(l2 = 0; l2 < 8; ++l2) {
                            bufferBuilder.addVertex((vertX + 0.0F), (vertY + height), (vertZ + l2 + 1.0F - f1)).setUv((vertX + 0.0F) * f0 + fx, (vertZ + l2 + 0.5F) * f0 + fz).setColor(red, green, blue, alpha).setNormal(0.0F, 0.0F, 1.0F);
                            bufferBuilder.addVertex((vertX + 8.0F), (vertY + height), (vertZ + l2 + 1.0F - f1)).setUv((vertX + 8.0F) * f0 + fx, (vertZ + l2 + 0.5F) * f0 + fz).setColor(red, green, blue, alpha).setNormal(0.0F, 0.0F, 1.0F);
                            bufferBuilder.addVertex((vertX + 8.0F), (vertY + 0.0F), (vertZ + l2 + 1.0F - f1)).setUv((vertX + 8.0F) * f0 + fx, (vertZ + l2 + 0.5F) * f0 + fz).setColor(red, green, blue, alpha).setNormal(0.0F, 0.0F, 1.0F);
                            bufferBuilder.addVertex((vertX + 0.0F), (vertY + 0.0F), (vertZ + l2 + 1.0F - f1)).setUv((vertX + 0.0F) * f0 + fx, (vertZ + l2 + 0.5F) * f0 + fz).setColor(red, green, blue, alpha).setNormal(0.0F, 0.0F, 1.0F);
                        }
                    }
                }
            }
        }
        return bufferBuilder;
    }
}
