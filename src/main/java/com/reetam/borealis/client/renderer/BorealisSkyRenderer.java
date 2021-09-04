package com.reetam.borealis.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
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
    private final VertexFormat skyFormat = DefaultVertexFormats.POSITION;

    private static final ResourceLocation MOON_LOCATION = new ResourceLocation("textures/environment/moon_phases.png");
    private static final ResourceLocation SUN_LOCATION = new ResourceLocation("textures/environment/sun.png");
    
    public BorealisSkyRenderer() {
        createStars();
        createLightSky();
        createDarkSky();
    }

    private void createStars() {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        if (this.starBuffer != null) {
            this.starBuffer.close();
        }

        this.starBuffer = new VertexBuffer(this.skyFormat);
        this.drawStars(bufferbuilder);
        bufferbuilder.end();
        this.starBuffer.upload(bufferbuilder);
    }
    private void createLightSky() {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        if (this.skyBuffer != null) {
            this.skyBuffer.close();
        }

        this.skyBuffer = new VertexBuffer(this.skyFormat);
        this.drawSkyHemisphere(bufferbuilder, 16.0F, false);
        bufferbuilder.end();
        this.skyBuffer.upload(bufferbuilder);
    }
    private void createDarkSky() {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        if (this.darkBuffer != null) {
            this.darkBuffer.close();
        }

        this.darkBuffer = new VertexBuffer(this.skyFormat);
        this.drawSkyHemisphere(bufferbuilder, -16.0F, true);
        bufferbuilder.end();
        this.darkBuffer.upload(bufferbuilder);
    }
    
    
    @Override
    public void render(int ticks, float partialTicks, MatrixStack matrixStack, ClientWorld world, Minecraft mc) {
        // DAY SKY
        RenderSystem.disableTexture();
        Vector3d vector3d = world.getSkyColor(mc.gameRenderer.getMainCamera().getBlockPosition(), partialTicks);
        float f = (float)vector3d.x;
        float f1 = (float)vector3d.y;
        float f2 = (float)vector3d.z;
        FogRenderer.levelFogColor();
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        RenderSystem.depthMask(false);
        RenderSystem.enableFog();
        RenderSystem.color3f(f, f1, f2);
        this.skyBuffer.bind(); // this line causes an error
        this.skyFormat.setupBufferState(0L);
        this.skyBuffer.draw(matrixStack.last().pose(), 7);
        VertexBuffer.unbind();
        this.skyFormat.clearBufferState();

        // MAKE SUNRISE
        RenderSystem.disableFog();
        RenderSystem.disableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        float[] afloat = world.effects().getSunriseColor(world.getTimeOfDay(partialTicks), partialTicks);
        if (afloat != null) {
            RenderSystem.disableTexture();
            RenderSystem.shadeModel(7425);
            matrixStack.pushPose();
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
            float f3 = MathHelper.sin(world.getSunAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F;
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(f3));
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
            float f4 = afloat[0];
            float f5 = afloat[1];
            float f6 = afloat[2];
            Matrix4f matrix4f = matrixStack.last().pose();
            bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();

            for(int j = 0; j <= 16; ++j) {
                float f7 = (float)j * ((float)Math.PI * 2F) / 16.0F;
                float f8 = MathHelper.sin(f7);
                float f9 = MathHelper.cos(f7);
                bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
            }

            bufferbuilder.end();
            WorldVertexBufferUploader.end(bufferbuilder);
            matrixStack.popPose();
            RenderSystem.shadeModel(7424);
        }

        // MAKE SUN MOON AND STARS
        RenderSystem.enableTexture();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        matrixStack.pushPose();
        float f11 = 1.0F - world.getRainLevel(partialTicks);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(world.getTimeOfDay(partialTicks) * 360.0F));
        Matrix4f matrix4f1 = matrixStack.last().pose();
        float f12 = 30.0F;
        mc.getTextureManager().bind(SUN_LOCATION); // sun
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.end();
        WorldVertexBufferUploader.end(bufferbuilder);
        f12 = 20.0F;
        mc.getTextureManager().bind(MOON_LOCATION); // moon
        int k = world.getMoonPhase();
        int l = k % 4;
        int i1 = k / 4 % 2;
        float f13 = (float)(l) / 4.0F;
        float f14 = (float)(i1) / 2.0F;
        float f15 = (float)(l + 1) / 4.0F;
        float f16 = (float)(i1 + 1) / 2.0F;
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, -100.0F, f12).uv(f15, f16).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, -100.0F, f12).uv(f13, f16).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, -100.0F, -f12).uv(f13, f14).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, -100.0F, -f12).uv(f15, f14).endVertex();
        bufferbuilder.end();
        WorldVertexBufferUploader.end(bufferbuilder);
        RenderSystem.disableTexture();
        float f10 = world.getStarBrightness(partialTicks) * f11; // stars
        if (f10 > 0.0F) {
            RenderSystem.color4f(f10, f10, f10, f10);
            this.starBuffer.bind();
            this.skyFormat.setupBufferState(0L);
            this.starBuffer.draw(matrixStack.last().pose(), 7);
            VertexBuffer.unbind();
            this.skyFormat.clearBufferState();
        }

        // NIGHT SKY
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableFog();
        matrixStack.popPose();
        RenderSystem.disableTexture();
        RenderSystem.color3f(0.0F, 0.0F, 0.0F);
        double d0 = mc.player.getEyePosition(partialTicks).y - world.getLevelData().getHorizonHeight();
        if (d0 < 0.0D) {
            matrixStack.pushPose();
            matrixStack.translate(0.0D, 12.0D, 0.0D);
            this.darkBuffer.bind();
            this.skyFormat.setupBufferState(0L);
            this.darkBuffer.draw(matrixStack.last().pose(), 7);
            VertexBuffer.unbind();
            this.skyFormat.clearBufferState();
            matrixStack.popPose();
        }

        if (world.effects().hasGround()) {
            RenderSystem.color3f(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
        } else {
            RenderSystem.color3f(f, f1, f2);
        }
        RenderSystem.enableTexture();
        RenderSystem.depthMask(true);
        RenderSystem.disableFog();
    }

    private void drawStars(BufferBuilder bufferBuilder) {
        Random random = new Random(10842L);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);

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
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);

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
