package com.reetam.borealis.client.renderer;

import com.reetam.borealis.BorealisMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IWeatherRenderHandler;

public class BorealisWeatherRenderer implements IWeatherRenderHandler {

    private static final ResourceLocation TEXTURE_HOT_SPRING_UNDERWATER = new ResourceLocation(BorealisMod.MODID + ":textures/fluid/hot_spring_water_overlay.png");

    @Override
    public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc, LightTexture lightmapIn, double xIn, double yIn, double zIn) {
        // do something
    }

    public static ResourceLocation getOverlayTexture() {
        return TEXTURE_HOT_SPRING_UNDERWATER;
    }
}