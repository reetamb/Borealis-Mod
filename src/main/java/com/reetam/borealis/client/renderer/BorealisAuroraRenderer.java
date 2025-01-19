package com.reetam.borealis.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.reetam.borealis.BorealisMod;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CubicSampler;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class BorealisAuroraRenderer {

    private static final ResourceLocation TEXTURE_AURORA = ResourceLocation.read(BorealisMod.MODID + ":textures/environment/aurora.png").getOrThrow();
    private VertexBuffer cloudBuffer;

    private int prevCloudX = Integer.MIN_VALUE;
    private int prevCloudY = Integer.MIN_VALUE;
    private int prevCloudZ = Integer.MIN_VALUE;
    private Vec3 prevCloudColor = Vec3.ZERO;
    private boolean generateClouds = true;
    private CloudStatus prevCloudsType;

    private static final Vec3 auroraGreenColor = new Vec3(0, 255, 153);
    private static final Vec3 auroraPinkColor = new Vec3(241, 89, 206);
    private static final float scale = 2.0F;

    public void renderClouds(ClientLevel level, PoseStack pPoseStack, Matrix4f pFrustumMatrix, Matrix4f pProjectionMatrix, int ticks, float pPartialTick, double pCamX, double pCamY, double pCamZ) {
        float f = level.effects().getCloudHeight();
        if (!Float.isNaN(f)) {
            double d1 = (((float)ticks + pPartialTick) * 0.03F);
            double d2 = (pCamX + d1) / 12.0;
            double d3 = (f - (float)pCamY + 0.33F);
            double d4 = pCamZ / 12.0 + 0.33F;
            d2 -= (Mth.floor(d2 / 2048.0) * 2048);
            d4 -= Mth.floor(d4 / 2048.0) * 2048;
            float f3 = (float)(d2 - (double)Mth.floor(d2));
            float f4 = (float)(d3 / 4.0 - (double)Mth.floor(d3 / 4.0)) * 4.0F;
            float f5 = (float)(d4 - (double)Mth.floor(d4));
            Vec3 vec3 = level.getCloudColor(pPartialTick);
            int i = (int)Math.floor(d2);
            int j = (int)Math.floor(d3 / 4.0);
            int k = (int)Math.floor(d4);
            if (i != this.prevCloudX
                    || j != this.prevCloudY
                    || k != this.prevCloudZ
                    || Minecraft.getInstance().options.getCloudsType() != this.prevCloudsType
                    || this.prevCloudColor.distanceToSqr(vec3) > 2.0E-4) {
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
                this.cloudBuffer.bind();
                this.cloudBuffer.upload(this.buildClouds(Tesselator.getInstance(), d2, d3, d4, vec3));
                VertexBuffer.unbind();
            }

            BiomeManager biomemanager = level.getBiomeManager();
            Vec3 cameraPosition = new Vec3(pCamX, pCamY, pCamZ).subtract(2.0, 2.0, 2.0).scale(0.25);
            float brightness = Mth.clamp(Mth.cos(level.getTimeOfDay(pPartialTick) * (float) (Math.PI * 2)) * 2.0F + 0.5F, 0.0F, 1.0F);
            Vector3f fogColor = CubicSampler.gaussianSampleVec3(
                    cameraPosition,
                    (p_109033_, p_109034_, p_109035_) -> level.effects()
                            .getBrightnessDependentFogColor(
                                    Vec3.fromRGB24(biomemanager.getNoiseBiomeAtQuart(p_109033_, p_109034_, p_109035_).value().getFogColor()), brightness
                            )
            ).toVector3f();
            Vector3f skyColor = level.getSkyColor(new Vec3(pCamX, pCamY, pCamZ), pPartialTick).toVector3f();
            Vector3f blendColor = skyColor.mul(0.75F).add(fogColor.mul(0.25F));

            RenderSystem.setShaderFogColor(blendColor.x, blendColor.y, blendColor.z);
            // I need to obtain a ratio of sky color to fog color, such that
            // 192/255 opacity of sky color + 255 opacity of fog color = new color
            pPoseStack.pushPose();
            pPoseStack.mulPose(pFrustumMatrix);
            pPoseStack.scale(scale, 1.0F, scale);
            pPoseStack.translate(-f3, f4, -f5);
            if (this.cloudBuffer != null) {
                this.cloudBuffer.bind();
                int l = this.prevCloudsType == CloudStatus.FANCY ? 0 : 1;

                for (int i1 = l; i1 < 2; i1++) {
                    RenderType rendertype = i1 == 0 ? createClouds(true) : createClouds(false);
                    rendertype.setupRenderState();
                    ShaderInstance shaderinstance = RenderSystem.getShader();
                    this.cloudBuffer.drawWithShader(pPoseStack.last().pose(), pProjectionMatrix, shaderinstance);
                    rendertype.clearRenderState();
                }

                VertexBuffer.unbind();
            }

            pPoseStack.popPose();
        }
    }

    private MeshData buildClouds(Tesselator pTesselator, double pX, double pY, double pZ, Vec3 pCloudColor) {
        float f1 = 0.00390625F;
        int height = 80;
        float f2 = 9.765625E-4F;
        float f3 = (float)Mth.floor(pX) * 0.00390625F;
        float f4 = (float)Mth.floor(pZ) * 0.00390625F;

//        PerlinSimplexNoise colorNoise = new PerlinSimplexNoise(RandomSource.create(11), List.of(0));
        float factor = 8;
        float alpha = 0.6F;

        float f5 = 1.0F;
        float f6 = 1.0F;
        float f7 = 1.0F;

        BufferBuilder bufferbuilder = pTesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL);
        float yLevel = (float)Math.floor(pY / 4.0) * 4.0F;

        if (this.prevCloudsType == CloudStatus.FANCY) {
            for (int x = (int) (-24 / scale) + 1; x <= 24 / scale; x++) {
                for (int z = (int) (-24 / scale) + 1; z <= 24 / scale ; z++) {
                    float scaledX = x * factor;
                    float scaledZ = z * factor;

//                    double grayscaleColor = colorNoise.getValue(x / 24.0F, z / 24.0F, true);
//                    Vector3f color = doubleAlongGradient(grayscaleColor).toVector3f();
//                    float f5 = color.x;
//                    float f6 = color.y;
//                    float f7 = color.z;

                    if (yLevel > -5.0F) {
                        bufferbuilder.addVertex(scaledX, yLevel, scaledZ + factor)
                                .setUv((scaledX) * f1 + f3, (scaledZ + factor) * f1 + f4)
                                .setColor(f5, f6, f7, alpha)
                                .setNormal(0.0F, -1.0F, 0.0F);
                        bufferbuilder.addVertex(scaledX + factor, yLevel, scaledZ + factor)
                                .setUv((scaledX + factor) * f1 + f3, (scaledZ + factor) * f1 + f4)
                                .setColor(f5, f6, f7, alpha)
                                .setNormal(0.0F, -1.0F, 0.0F);
                        bufferbuilder.addVertex(scaledX + factor, yLevel, scaledZ)
                                .setUv((scaledX + factor) * f1 + f3, (scaledZ) * f1 + f4)
                                .setColor(f5, f6, f7, alpha)
                                .setNormal(0.0F, -1.0F, 0.0F);
                        bufferbuilder.addVertex(scaledX, yLevel, scaledZ)
                                .setUv((scaledX) * f1 + f3, (scaledZ) * f1 + f4)
                                .setColor(f5, f6, f7, alpha)
                                .setNormal(0.0F, -1.0F, 0.0F);
                    }

                    if (yLevel <= 5.0F) {
                        bufferbuilder.addVertex(scaledX, yLevel + height-f2, scaledZ + factor)
                                .setUv((scaledX) * f1 + f3, (scaledZ + factor) * f1 + f4)
                                .setColor(f5, f6, f7, alpha)
                                .setNormal(0.0F, 1.0F, 0.0F);
                        bufferbuilder.addVertex(scaledX + factor, yLevel + height-f2, scaledZ + factor)
                                .setUv((scaledX + factor) * f1 + f3, (scaledZ + factor) * f1 + f4)
                                .setColor(f5, f6, f7, alpha)
                                .setNormal(0.0F, 1.0F, 0.0F);
                        bufferbuilder.addVertex(scaledX + factor, yLevel + height-f2, scaledZ)
                                .setUv((scaledX + factor) * f1 + f3, (scaledZ) * f1 + f4)
                                .setColor(f5, f6, f7, alpha)
                                .setNormal(0.0F, 1.0F, 0.0F);
                        bufferbuilder.addVertex(scaledX, yLevel + height-f2, scaledZ)
                                .setUv((scaledX) * f1 + f3, (scaledZ) * f1 + f4)
                                .setColor(f5, f6, f7, alpha)
                                .setNormal(0.0F, 1.0F, 0.0F);
                    }

                    if (x > -1) {
                        for (int i1 = 0; i1 < (64/factor); i1++) {
                            bufferbuilder.addVertex(scaledX + (float)i1, yLevel, scaledZ + factor)
                                    .setUv((scaledX + (float)i1 + 0.5F) * f1 + f3, (scaledZ + factor) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(-1.0F, 0.0F, 0.0F);
                            bufferbuilder.addVertex(scaledX + (float)i1, yLevel + height, scaledZ + factor)
                                    .setUv((scaledX + (float)i1 + 0.5F) * f1 + f3, (scaledZ + factor) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(-1.0F, 0.0F, 0.0F);
                            bufferbuilder.addVertex(scaledX + (float)i1, yLevel + height, scaledZ)
                                    .setUv((scaledX + (float)i1 + 0.5F) * f1 + f3, (scaledZ) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(-1.0F, 0.0F, 0.0F);
                            bufferbuilder.addVertex(scaledX + (float)i1, yLevel, scaledZ)
                                    .setUv((scaledX + (float)i1 + 0.5F) * f1 + f3, (scaledZ) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(-1.0F, 0.0F, 0.0F);
                        }
                    }

                    if (x <= 1) {
                        for (int j2 = 0; j2 < (64/factor); j2++) {
                            bufferbuilder.addVertex(scaledX + (float)j2 + 1.0F - f2, yLevel, scaledZ + factor)
                                    .setUv((scaledX + (float)j2 + 0.5F) * f1 + f3, (scaledZ + factor) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(1.0F, 0.0F, 0.0F);
                            bufferbuilder.addVertex(scaledX + (float)j2 + 1.0F - f2, yLevel + height, scaledZ + factor)
                                    .setUv((scaledX + (float)j2 + 0.5F) * f1 + f3, (scaledZ + factor) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(1.0F, 0.0F, 0.0F);
                            bufferbuilder.addVertex(scaledX + (float)j2 + 1.0F - f2, yLevel + height, scaledZ)
                                    .setUv((scaledX + (float)j2 + 0.5F) * f1 + f3, (scaledZ) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(1.0F, 0.0F, 0.0F);
                            bufferbuilder.addVertex(scaledX + (float)j2 + 1.0F - f2, yLevel, scaledZ)
                                    .setUv((scaledX + (float)j2 + 0.5F) * f1 + f3, (scaledZ) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(1.0F, 0.0F, 0.0F);
                        }
                    }

                    if (z > -1) {
                        for (int k2 = 0; k2 < (64/factor); k2++) {
                            bufferbuilder.addVertex(scaledX, yLevel + height, scaledZ + (float)k2)
                                    .setUv((scaledX) * f1 + f3, (scaledZ + (float)k2 + 0.5F) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(0.0F, 0.0F, -1.0F);
                            bufferbuilder.addVertex(scaledX + factor, yLevel + height, scaledZ + (float)k2)
                                    .setUv((scaledX + factor) * f1 + f3, (scaledZ + (float)k2 + 0.5F) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(0.0F, 0.0F, -1.0F);
                            bufferbuilder.addVertex(scaledX + factor, yLevel, scaledZ + (float)k2)
                                    .setUv((scaledX + factor) * f1 + f3, (scaledZ + (float)k2 + 0.5F) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(0.0F, 0.0F, -1.0F);
                            bufferbuilder.addVertex(scaledX, yLevel, scaledZ + (float)k2)
                                    .setUv((scaledX) * f1 + f3, (scaledZ + (float)k2 + 0.5F) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(0.0F, 0.0F, -1.0F);
                        }
                    }

                    if (z <= 1) {
                        for (int l2 = 0; l2 < (64/factor); l2++) {
                            bufferbuilder.addVertex(scaledX, yLevel + height, scaledZ + (float)l2 + 1.0F - f2)
                                    .setUv((scaledX) * f1 + f3, (scaledZ + (float)l2 + 0.5F) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(0.0F, 0.0F, 1.0F);
                            bufferbuilder.addVertex(scaledX + factor, yLevel + height, scaledZ + (float)l2 + 1.0F - f2)
                                    .setUv((scaledX + factor) * f1 + f3, (scaledZ + (float)l2 + 0.5F) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(0.0F, 0.0F, 1.0F);
                            bufferbuilder.addVertex(scaledX + factor, yLevel, scaledZ + (float)l2 + 1.0F - f2)
                                    .setUv((scaledX + factor) * f1 + f3, (scaledZ + (float)l2 + 0.5F) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(0.0F, 0.0F, 1.0F);
                            bufferbuilder.addVertex(scaledX, yLevel, scaledZ + (float)l2 + 1.0F - f2)
                                    .setUv((scaledX) * f1 + f3, (scaledZ + (float)l2 + 0.5F) * f1 + f4)
                                    .setColor(f5, f6, f7, alpha)
                                    .setNormal(0.0F, 0.0F, 1.0F);
                        }
                    }
                }
            }
        }  else {
            int k1 = 96;

            for (int l1 = -k1; l1 < k1; l1 += 32) {
                for (int i2 = -k1; i2 < k1; i2 += 32) {
                    bufferbuilder.addVertex((float)(l1), yLevel, (float)(i2 + 32))
                            .setUv((float)(l1) * 0.00390625F + f3, (float)(i2 + 32) * 0.00390625F + f4)
                            .setColor(f5, f6, f7, alpha)
                            .setNormal(0.0F, -1.0F, 0.0F);
                    bufferbuilder.addVertex((float)(l1 + 32), yLevel, (float)(i2 + 32))
                            .setUv((float)(l1 + 32) * 0.00390625F + f3, (float)(i2 + 32) * 0.00390625F + f4)
                            .setColor(f5, f6, f7, alpha)
                            .setNormal(0.0F, -1.0F, 0.0F);
                    bufferbuilder.addVertex((float)(l1 + 32), yLevel, (float)(i2))
                            .setUv((float)(l1 + 32) * 0.00390625F + f3, (float)(i2) * 0.00390625F + f4)
                            .setColor(f5, f6, f7, alpha)
                            .setNormal(0.0F, -1.0F, 0.0F);
                    bufferbuilder.addVertex((float)(l1), yLevel, (float)(i2))
                            .setUv((float)(l1) * 0.00390625F + f3, (float)(i2) * 0.00390625F + f4)
                            .setColor(f5, f6, f7, alpha)
                            .setNormal(0.0F, -1.0F, 0.0F);
                }
            }
        }

        return bufferbuilder.buildOrThrow();
    }

    private static RenderType.CompositeRenderType createClouds(boolean pColor) {
        return RenderType.create(
                "clouds",
                DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL,
                VertexFormat.Mode.QUADS,
                786432,
                false,
                false,
                RenderType.CompositeState.builder()
                        .setShaderState(RenderType.RENDERTYPE_CLOUDS_SHADER)
                        .setTextureState(new RenderStateShard.TextureStateShard(TEXTURE_AURORA, false, false))
                        .setTransparencyState(RenderType.TRANSLUCENT_TRANSPARENCY)
                        .setCullState(RenderType.NO_CULL)
                        .setWriteMaskState(pColor ? RenderType.DEPTH_WRITE : RenderType.COLOR_DEPTH_WRITE)
                        .setOutputState(RenderType.CLOUDS_TARGET)
                        .createCompositeState(true)
        );
    }

    private static Vec3 doubleAlongGradient(double proportion) {
        proportion = Math.clamp(proportion, 0.0, 1);

        double red = auroraGreenColor.x + (proportion * (auroraPinkColor.x - auroraGreenColor.x));
        double green = auroraGreenColor.y + (proportion * (auroraPinkColor.y - auroraGreenColor.y));
        double blue = auroraGreenColor.z + (proportion * (auroraPinkColor.z - auroraGreenColor.z));

        return new Vec3(red / 255, green / 255, blue / 255);
    }
}
