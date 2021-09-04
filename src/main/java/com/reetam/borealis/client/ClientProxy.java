package com.reetam.borealis.client;

import com.reetam.borealis.client.renderer.BorealisAuroraRenderer;
import com.reetam.borealis.client.renderer.BorealisSkyRenderer;
import com.reetam.borealis.client.renderer.BorealisWeatherRenderer;
import com.reetam.borealis.entity.renderer.BorealisBoatRenderer;
import com.reetam.borealis.entity.renderer.HummingbirdRenderer;
import com.reetam.borealis.entity.renderer.MismicMuskoxRenderer;
import com.reetam.borealis.entity.renderer.TakaheRenderer;
import com.reetam.borealis.registry.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ICloudRenderHandler;
import net.minecraftforge.client.ISkyRenderHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import javax.annotation.Nullable;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class ClientProxy {

    private static void render(Supplier<? extends Block> block, RenderType render) {
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }

    public static void registerBlockRenderers() {
        RenderType cutout = RenderType.cutout();
        RenderType translucent = RenderType.translucent();


        render(BorealisBlocks.BRUMAL_DOOR, cutout);
        render(BorealisBlocks.BRUMAL_TRAPDOOR, cutout);
        render(BorealisBlocks.BRUMAL_SAPLING, cutout);
        render(BorealisBlocks.POTTED_BRUMAL_SAPLING, cutout);
        render(BorealisBlocks.FROSTFIR_DOOR, cutout);
        render(BorealisBlocks.FROSTFIR_TRAPDOOR, cutout);
        render(BorealisBlocks.FROSTFIR_SAPLING, cutout);
        render(BorealisBlocks.POTTED_FROSTFIR_SAPLING, cutout);
        render(BorealisBlocks.SACCHARINE_DOOR, cutout);
        render(BorealisBlocks.SACCHARINE_TRAPDOOR, cutout);
        render(BorealisBlocks.SACCHARINE_SAPLING, cutout);
        render(BorealisBlocks.POTTED_SACCHARINE_SAPLING, cutout);

        render(BorealisBlocks.HOT_SPRING_WATER, translucent);
        RenderTypeLookup.setRenderLayer(BorealisFluids.hot_spring_water_flowing.get(), translucent);
        RenderTypeLookup.setRenderLayer(BorealisFluids.hot_spring_water_source.get(), translucent);
        render(BorealisBlocks.BOREALIS_PORTAL, translucent);
        render(BorealisBlocks.CLOUD, translucent);
        render(BorealisBlocks.TANZANITE_BLOCK, translucent);
    }

    public static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.HUMMINGBIRD.get(), HummingbirdRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.TAKAHE.get(), TakaheRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.MISMIC_MUSKOX.get(), MismicMuskoxRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BorealisEntities.BOAT.get(), BorealisBoatRenderer::new);
    }

    public static void registerDimensionRenderers() {
        DimensionRenderInfo borealisRenderInfo = new DimensionRenderInfo(Float.NaN, false, DimensionRenderInfo.FogType.NORMAL, false, true) {
            private final float[] sunriseCol = new float[4];

            @Override
            public Vector3d getBrightnessDependentFogColor(Vector3d vector3d, float sun) {
                return vector3d;
            }

            @Override
            public boolean isFoggyAt(int x, int y) {
                return false;
            }

            @Nullable
            @Override
            public float[] getSunriseColor(float time, float partialTicks) {
                float f1 = MathHelper.cos(time * ((float)Math.PI * 2F)) - 0.0F;
                if (f1 >= -0.4F && f1 <= 0.4F) {
                    float f3 = (f1 - -0.0F) / 0.4F * 0.5F + 0.5F;
                    float alpha = 1.0F - (1.0F - MathHelper.sin(f3 * (float)Math.PI)) * 0.99F;
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
            public ICloudRenderHandler getCloudRenderHandler() {
                return new BorealisAuroraRenderer();
            }

            @Nullable
            @Override
            public ISkyRenderHandler getSkyRenderHandler() {
                return new BorealisSkyRenderer();
            }

            @Override
            public float getCloudHeight() {
                return 160.0F;
            }
        };
        borealisRenderInfo.setCloudRenderHandler(new BorealisAuroraRenderer());
        borealisRenderInfo.setSkyRenderHandler(new BorealisSkyRenderer());
        DimensionRenderInfo.EFFECTS.put(BorealisDimensions.BOREALIS_TYPE.location(), borealisRenderInfo);

    }

    public static void registerTileEntityRenderers() {
        ClientRegistry.bindTileEntityRenderer(BorealisTileEntities.BOREALIS_SIGN.get(), SignTileEntityRenderer::new);
    }

    public static void registerWoodTypes() {
        Atlases.addWoodType(BorealisBlocks.BRUMAL_WOODTYPE);
        Atlases.addWoodType(BorealisBlocks.FROSTFIR_WOODTYPE);
        Atlases.addWoodType(BorealisBlocks.SACCHARINE_WOODTYPE);
    }
}