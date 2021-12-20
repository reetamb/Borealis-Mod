package com.reetam.borealis.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ISkyRenderHandler;

import javax.annotation.Nullable;
import java.util.Random;


public class BorealisSkyRenderer implements ISkyRenderHandler {

    @Nullable
    private VertexBuffer skyBuffer;
    @Nullable
    private VertexBuffer starBuffer;
    @Nullable
    public VertexBuffer darkBuffer;
    private final VertexFormat skyFormat = DefaultVertexFormat.POSITION;

    private static final ResourceLocation MOON_LOCATION = new ResourceLocation("textures/environment/moon_phases.png");
    private static final ResourceLocation SUN_LOCATION = new ResourceLocation("textures/environment/sun.png");
    
    public BorealisSkyRenderer() {
        createStars();
        createLightSky();
        createDarkSky();
    }

    private void createStars() {
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        if (this.starBuffer != null) {
            this.starBuffer.close();
        }

        this.starBuffer = new VertexBuffer();
        this.drawStars(bufferbuilder);
        bufferbuilder.end();
        this.starBuffer.upload(bufferbuilder);
    }
    private void createLightSky() {
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        if (this.skyBuffer != null) {
            this.skyBuffer.close();
        }

        this.skyBuffer = new VertexBuffer();
        this.drawSkyHemisphere(bufferbuilder, 16.0F, false);
        bufferbuilder.end();
        this.skyBuffer.upload(bufferbuilder);
    }
    private void createDarkSky() {
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        if (this.darkBuffer != null) {
            this.darkBuffer.close();
        }

        this.darkBuffer = new VertexBuffer();
        this.drawSkyHemisphere(bufferbuilder, -16.0F, true);
        bufferbuilder.end();
        this.darkBuffer.upload(bufferbuilder);
    }
    
    
    @Override
    public void render(int ticks, float partialTicks, PoseStack poseStack, ClientLevel level, Minecraft mc) {
        // DAY SKY
        RenderSystem.disableTexture();
        Vec3 vector3d = level.getSkyColor(mc.gameRenderer.getMainCamera().getPosition(), partialTicks);
        float f = (float)vector3d.x;
        float f1 = (float)vector3d.y;
        float f2 = (float)vector3d.z;
        FogRenderer.levelFogColor();
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.depthMask(false);
        RenderSystem.setShaderColor(f, f1, f2, 1.0F);
        ShaderInstance shaderinstance = RenderSystem.getShader();
        this.skyBuffer.drawWithShader(poseStack.last().pose(), poseStack.last().pose(), shaderinstance);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        // MAKE SUNRISE
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(partialTicks), partialTicks);
        if (afloat != null) {
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.disableTexture();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.pushPose();
            poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
            float f3 = Math.sin(level.getSunAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F;
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(f3));
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
            float f4 = afloat[0];
            float f5 = afloat[1];
            float f6 = afloat[2];
            Matrix4f matrix4f = poseStack.last().pose();
            bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
            bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();

            for(int j = 0; j <= 16; ++j) {
                float f7 = (float)j * ((float)Math.PI * 2F) / 16.0F;
                float f8 = (float) Math.sin(f7);
                float f9 = (float) Math.cos(f7);
                bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
            }

            bufferbuilder.end();
            BufferUploader.end(bufferbuilder);
            poseStack.popPose();
        }

        // MAKE SUN MOON AND STARS
        RenderSystem.enableTexture();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        poseStack.pushPose();
        float f11 = 1.0F - level.getRainLevel(partialTicks);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f11);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTicks) * 360.0F));
        Matrix4f matrix4f1 = poseStack.last().pose();
        float f12 = 30.0F;
        RenderSystem.setShaderTexture(0, SUN_LOCATION); // sun
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
        f12 = 20.0F;
        RenderSystem.setShaderTexture(0, MOON_LOCATION); // moon
        int k = level.getMoonPhase();
        int l = k % 4;
        int i1 = k / 4 % 2;
        float f13 = (float)(l) / 4.0F;
        float f14 = (float)(i1) / 2.0F;
        float f15 = (float)(l + 1) / 4.0F;
        float f16 = (float)(i1 + 1) / 2.0F;
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, -100.0F, f12).uv(f15, f16).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, -100.0F, f12).uv(f13, f16).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, -100.0F, -f12).uv(f13, f14).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, -100.0F, -f12).uv(f15, f14).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
        RenderSystem.disableTexture();
        float f10 = level.getStarBrightness(partialTicks) * f11; // stars
        if (f10 > 0.0F) {
            RenderSystem.setShaderColor(f10, f10, f10, f10);
            FogRenderer.setupNoFog();
            this.starBuffer.drawWithShader(poseStack.last().pose(), poseStack.last().pose(), GameRenderer.getPositionShader());
            FogRenderer.setupFog(mc.gameRenderer.getMainCamera(), FogRenderer.FogMode.FOG_SKY, mc.gameRenderer.getRenderDistance(), false);
        }

        // NIGHT SKY
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        poseStack.popPose();
        RenderSystem.disableTexture();
        RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
        double d0 = mc.player.getEyePosition(partialTicks).y - level.getLevelData().getHorizonHeight(level);
        if (d0 < 0.0D) {
            poseStack.pushPose();
            poseStack.translate(0.0D, 12.0D, 0.0D);
            this.darkBuffer.drawWithShader(poseStack.last().pose(), poseStack.last().pose(), shaderinstance);
            poseStack.popPose();
        }

        if (level.effects().hasGround()) {
            RenderSystem.setShaderColor(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F, 1.0F);
        } else {
            RenderSystem.setShaderColor(f, f1, f2, 1.0F);
        }
        RenderSystem.enableTexture();
        RenderSystem.depthMask(true);
    }

    private void drawStars(BufferBuilder bufferBuilder) {
        Random random = new Random(10842L);
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

        for(int i = 0; i < 1500; ++i) {
            double d0 = (random.nextFloat() * 2.0F - 1.0F);
            double d1 = (random.nextFloat() * 2.0F - 1.0F);
            double d2 = (random.nextFloat() * 2.0F - 1.0F);
            double d3 = (0.15F + random.nextFloat() * 0.1F);
            double d4 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d4 < 1.0D && d4 > 0.01D) {
                d4 = 1.0D / Math.sqrt(d4);
                d0 = d0 * d4;
                d1 = d1 * d4;
                d2 = d2 * d4;
                double d5 = d0 * 100.0D;
                double d6 = d1 * 100.0D;
                double d7 = d2 * 100.0D;
                double d8 = Math.atan2(d0, d2);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
                double d12 = Math.sin(d11);
                double d13 = Math.cos(d11);
                double d14 = random.nextDouble() * Math.PI * 2.0D;
                double d15 = Math.sin(d14);
                double d16 = Math.cos(d14);
                for(int j = 0; j < 4; ++j) {
                    double d18 = ((j & 2) - 1) * d3;
                    double d19 = ((j + 1 & 2) - 1) * d3;
                    double d21 = d18 * d16 - d19 * d15;
                    double d22 = d19 * d16 + d18 * d15;
                    double d23 = d21 * d12 + 0.0D * d13;
                    double d24 = 0.0D * d12 - d21 * d13;
                    double d25 = d24 * d9 - d22 * d10;
                    double d26 = d22 * d9 + d24 * d10;
                    bufferBuilder.vertex(d5 + d25, d6 + d23, d7 + d26).endVertex();
                }
            }
        }
    }
    private void drawSkyHemisphere(BufferBuilder bufferBuilder, float lightness, boolean isDark) {
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

        for(int k = -384; k <= 384; k += 64) {
            for(int l = -384; l <= 384; l += 64) {
                float f = (float)k;
                float f1 = (float)(k + 64);
                if (isDark) {
                    f1 = (float)k;
                    f = (float)(k + 64);
                }

                bufferBuilder.vertex(f, lightness, l).endVertex();
                bufferBuilder.vertex(f1, lightness, l).endVertex();
                bufferBuilder.vertex(f1, lightness, (l + 64)).endVertex();
                bufferBuilder.vertex(f, lightness, (l + 64)).endVertex();
            }
        }

    }
}
