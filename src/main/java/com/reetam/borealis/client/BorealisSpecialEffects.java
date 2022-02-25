package com.reetam.borealis.client;

import com.reetam.borealis.client.renderer.BorealisAuroraRenderer;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ICloudRenderHandler;

import javax.annotation.Nullable;

public class BorealisSpecialEffects extends DimensionSpecialEffects {

    public BorealisSpecialEffects() {
        super(Float.NaN, false, DimensionSpecialEffects.SkyType.NORMAL, false, true);
        this.setCloudRenderHandler(new BorealisAuroraRenderer());
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

    @Nullable
    @Override
    public ICloudRenderHandler getCloudRenderHandler() {
        return new BorealisAuroraRenderer();
    }

    @Override
    public float getCloudHeight() {
        return 192.0F;
    }
}
