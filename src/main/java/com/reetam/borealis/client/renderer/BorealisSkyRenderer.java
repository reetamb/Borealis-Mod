package com.reetam.borealis.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class BorealisSkyRenderer {

    private static final ResourceLocation MOON_LOCATION = ResourceLocation.withDefaultNamespace("textures/environment/moon_phases.png");
    private static final ResourceLocation SUN_LOCATION = ResourceLocation.withDefaultNamespace("textures/environment/sun.png");
    private VertexBuffer skyBuffer;
    private VertexBuffer darkBuffer;
    private VertexBuffer starBuffer;

    public void renderSky(ClientLevel level, Matrix4f pFrustumMatrix, Matrix4f pProjectionMatrix, float pPartialTick, Camera pCamera, boolean pIsFoggy, Runnable pSkyFogSetup) {
        pSkyFogSetup.run();
        if (!pIsFoggy) {
            FogType fogtype = pCamera.getFluidInCamera();
            if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !this.doesMobEffectBlockSky(pCamera)) {
                this.createStars();
                this.createLightSky();
                this.createDarkSky();

                PoseStack posestack = new PoseStack();
                posestack.mulPose(pFrustumMatrix);
                if (Minecraft.getInstance().level.effects().skyType() == DimensionSpecialEffects.SkyType.NORMAL) {
                    Vec3 vec3 = level.getSkyColor(Minecraft.getInstance().gameRenderer.getMainCamera().getPosition(), pPartialTick);
                    float f = (float)vec3.x;
                    float f1 = (float)vec3.y;
                    float f2 = (float)vec3.z;
                    FogRenderer.levelFogColor();
                    Tesselator tesselator = Tesselator.getInstance();
                    RenderSystem.depthMask(false);
                    RenderSystem.setShaderColor(f, f1, f2, 1.0F);
                    ShaderInstance shaderinstance = RenderSystem.getShader();
                    this.skyBuffer.bind();
                    this.skyBuffer.drawWithShader(posestack.last().pose(), pProjectionMatrix, shaderinstance);
                    VertexBuffer.unbind();
                    RenderSystem.enableBlend();
                    float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(pPartialTick), pPartialTick);
                    if (afloat != null) {
                        RenderSystem.setShader(GameRenderer::getPositionColorShader);
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                        posestack.pushPose();
                        posestack.mulPose(Axis.XP.rotationDegrees(90.0F));
                        float f3 = Mth.sin(level.getSunAngle(pPartialTick)) < 0.0F ? 180.0F : 0.0F;
                        posestack.mulPose(Axis.ZP.rotationDegrees(f3));
                        posestack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                        float f4 = afloat[0];
                        float f5 = afloat[1];
                        float f6 = afloat[2];
                        Matrix4f matrix4f = posestack.last().pose();
                        BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
                        bufferbuilder.addVertex(matrix4f, 0.0F, 100.0F, 0.0F).setColor(f4, f5, f6, afloat[3]);
                        int i = 16;

                        for (int j = 0; j <= 16; j++) {
                            float f7 = (float)j * (float) (Math.PI * 2) / 16.0F;
                            float f8 = Mth.sin(f7);
                            float f9 = Mth.cos(f7);
                            bufferbuilder.addVertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3])
                                    .setColor(afloat[0], afloat[1], afloat[2], 0.0F);
                        }

                        BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
                        posestack.popPose();
                    }

                    RenderSystem.blendFuncSeparate(
                            GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO
                    );
                    posestack.pushPose();
                    float f11 = 1.0F - level.getRainLevel(pPartialTick);
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f11);
                    posestack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                    posestack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(pPartialTick) * 360.0F));
                    Matrix4f matrix4f1 = posestack.last().pose();
                    float f12 = 30.0F;
                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    RenderSystem.setShaderTexture(0, SUN_LOCATION);
                    BufferBuilder bufferbuilder1 = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    bufferbuilder1.addVertex(matrix4f1, -f12, 100.0F, -f12).setUv(0.0F, 0.0F);
                    bufferbuilder1.addVertex(matrix4f1, f12, 100.0F, -f12).setUv(1.0F, 0.0F);
                    bufferbuilder1.addVertex(matrix4f1, f12, 100.0F, f12).setUv(1.0F, 1.0F);
                    bufferbuilder1.addVertex(matrix4f1, -f12, 100.0F, f12).setUv(0.0F, 1.0F);
                    BufferUploader.drawWithShader(bufferbuilder1.buildOrThrow());
                    f12 = 20.0F;
                    RenderSystem.setShaderTexture(0, MOON_LOCATION);
                    int k = level.getMoonPhase();
                    int l = k % 4;
                    int i1 = k / 4 % 2;
                    float f13 = (float)(l + 0) / 4.0F;
                    float f14 = (float)(i1 + 0) / 2.0F;
                    float f15 = (float)(l + 1) / 4.0F;
                    float f16 = (float)(i1 + 1) / 2.0F;
                    bufferbuilder1 = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    bufferbuilder1.addVertex(matrix4f1, -f12, -100.0F, f12).setUv(f15, f16);
                    bufferbuilder1.addVertex(matrix4f1, f12, -100.0F, f12).setUv(f13, f16);
                    bufferbuilder1.addVertex(matrix4f1, f12, -100.0F, -f12).setUv(f13, f14);
                    bufferbuilder1.addVertex(matrix4f1, -f12, -100.0F, -f12).setUv(f15, f14);
                    BufferUploader.drawWithShader(bufferbuilder1.buildOrThrow());
                    float f10 = level.getStarBrightness(pPartialTick) * f11;
                    if (f10 > 0.0F) {
                        RenderSystem.setShaderColor(f10, f10, f10, f10);
                        FogRenderer.setupNoFog();
                        starBuffer.bind();
                        starBuffer.drawWithShader(posestack.last().pose(), pProjectionMatrix, GameRenderer.getPositionShader());
                        VertexBuffer.unbind();
                        pSkyFogSetup.run();
                    }

                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    RenderSystem.disableBlend();
                    RenderSystem.defaultBlendFunc();
                    posestack.popPose();
                    RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
                    double d0 = Minecraft.getInstance().player.getEyePosition(pPartialTick).y - level.getLevelData().getHorizonHeight(level);
                    if (d0 < 0.0) {
                        posestack.pushPose();
                        posestack.translate(0.0F, 12.0F, 0.0F);
                        this.darkBuffer.bind();
                        this.darkBuffer.drawWithShader(posestack.last().pose(), pProjectionMatrix, shaderinstance);
                        VertexBuffer.unbind();
                        posestack.popPose();
                    }

                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    RenderSystem.depthMask(true);
                }
            }
        }
    }

    private void createStars() {
        if (this.starBuffer != null) {
            this.starBuffer.close();
        }

        this.starBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
        this.starBuffer.bind();
        this.starBuffer.upload(this.drawStars(Tesselator.getInstance()));
        VertexBuffer.unbind();
    }

    private MeshData drawStars(Tesselator pTesselator) {
        RandomSource randomsource = RandomSource.create(10842L);
        int count = 1500;
        float distance = 100.0F;
        BufferBuilder bufferbuilder = pTesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

        for (int j = 0; j < count; j++) {
            float f1 = randomsource.nextFloat() * 2.0F - 1.0F;
            float f2 = randomsource.nextFloat() * 2.0F - 1.0F;
            float f3 = randomsource.nextFloat() * 2.0F - 1.0F;
            float f4 = 0.15F + randomsource.nextFloat() * 0.1F;
            float f5 = Mth.lengthSquared(f1, f2, f3);
            if (!(f5 <= 0.010000001F) && !(f5 >= 1.0F)) {
                Vector3f vector3f = new Vector3f(f1, f2, f3).normalize(distance);
                float f6 = (float)(randomsource.nextDouble() * (float) Math.PI * 2.0);
                Quaternionf quaternionf = new Quaternionf().rotateTo(new Vector3f(0.0F, 0.0F, -1.0F), vector3f).rotateZ(f6);
                bufferbuilder.addVertex(vector3f.add(new Vector3f(f4, -f4, 0.0F).rotate(quaternionf)));
                bufferbuilder.addVertex(vector3f.add(new Vector3f(f4, f4, 0.0F).rotate(quaternionf)));
                bufferbuilder.addVertex(vector3f.add(new Vector3f(-f4, f4, 0.0F).rotate(quaternionf)));
                bufferbuilder.addVertex(vector3f.add(new Vector3f(-f4, -f4, 0.0F).rotate(quaternionf)));
            }
        }

        return bufferbuilder.buildOrThrow();
    }

    private void createDarkSky() {
        if (this.darkBuffer != null) {
            this.darkBuffer.close();
        }

        this.darkBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
        this.darkBuffer.bind();
        this.darkBuffer.upload(buildSkyDisc(Tesselator.getInstance(), -16.0F));
        VertexBuffer.unbind();
    }

    private void createLightSky() {
        if (this.skyBuffer != null) {
            this.skyBuffer.close();
        }

        this.skyBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
        this.skyBuffer.bind();
        this.skyBuffer.upload(buildSkyDisc(Tesselator.getInstance(), 16.0F));
        VertexBuffer.unbind();
    }

    private static MeshData buildSkyDisc(Tesselator pTesselator, float pY) {
        float f = Math.signum(pY) * 512.0F;
        float f1 = 512.0F;
        BufferBuilder bufferbuilder = pTesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
        bufferbuilder.addVertex(0.0F, pY, 0.0F);

        for (int i = -180; i <= 180; i += 45) {
            bufferbuilder.addVertex(f * Mth.cos((float)i * (float) (Math.PI / 180.0)), pY, 512.0F * Mth.sin((float)i * (float) (Math.PI / 180.0)));
        }

        return bufferbuilder.buildOrThrow();
    }

    private boolean doesMobEffectBlockSky(Camera pCamera) {
        return pCamera.getEntity() instanceof LivingEntity livingentity && (livingentity.hasEffect(MobEffects.BLINDNESS) || livingentity.hasEffect(MobEffects.DARKNESS));
    }
}
