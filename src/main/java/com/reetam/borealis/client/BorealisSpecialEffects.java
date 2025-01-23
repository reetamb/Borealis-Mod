package com.reetam.borealis.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.client.renderer.BorealisAuroraRenderer;
import com.reetam.borealis.client.renderer.BorealisSkyRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

import javax.annotation.Nullable;

public class BorealisSpecialEffects extends DimensionSpecialEffects {

    BorealisAuroraRenderer auroraRenderer;
    BorealisSkyRenderer skyRenderer;
    // BorealisWeatherRenderer weatherRenderer;
    public BorealisSpecialEffects() {
        super(Float.NaN, false, SkyType.NORMAL, false, true);
        auroraRenderer = new BorealisAuroraRenderer();
        skyRenderer = new BorealisSkyRenderer();
        // weatherRenderer = new BorealisWeatherRenderer();
    }

    private final float[] sunriseCol = new float[4];

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 vec3, float sun) {
        return vec3;
    }

    @Override
    public boolean isFoggyAt(int x, int y) {
        return false;
    }

    @Nullable
    @Override
    public float[] getSunriseColor(float time, float partialTicks) {
        float f1 = (int) Math.cos(time * ((float)Math.PI * 2F)) - 0.0F;
        if (f1 >= -0.4F && f1 <= 0.4F) {
            float f3 = (f1 - -0.0F) / 0.4F * 0.5F + 0.5F;
            float alpha = 1.0F - (1.0F - (int) Math.sin(f3 * (float)Math.PI)) * 0.99F;
            alpha = alpha * alpha;
            this.sunriseCol[0] = f3 * f3 * 0.7F + 0.1F;
            this.sunriseCol[1] = f3 * f3 * 0.0F + 0.2F;
            this.sunriseCol[2] = f3 * 0.3F + 0.5F;
            this.sunriseCol[3] = alpha;
            return this.sunriseCol;
        } else {
            return null;
        }
    }
    @Override
    public float getCloudHeight() {
        return BorealisMod.MIN_HEIGHT + BorealisMod.HEIGHT - 16;
    }

    @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f modelViewMatrix, Matrix4f projectionMatrix) {
        auroraRenderer.renderClouds(level, poseStack, modelViewMatrix, projectionMatrix, ticks, partialTick, camX, camY, camZ);
        return true;
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f modelViewMatrix, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        skyRenderer.renderSky(level, modelViewMatrix, projectionMatrix, partialTick, camera, isFoggy, setupFog);
        return true;
    }

    public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
        return false;
    }
}
