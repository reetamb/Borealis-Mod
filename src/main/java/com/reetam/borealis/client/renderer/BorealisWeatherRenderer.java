package com.reetam.borealis.client.renderer;

import com.reetam.borealis.BorealisMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;

public class BorealisWeatherRenderer {

    private static final ResourceLocation TEXTURE_HOT_SPRING_UNDERWATER = new ResourceLocation(BorealisMod.MODID + ":textures/fluid/hot_spring_water_overlay.png");

    public void render(int ticks, float partialTicks, ClientLevel world, Minecraft mc, LightTexture lightmapIn, double xIn, double yIn, double zIn) {
        // do something
    }
}
