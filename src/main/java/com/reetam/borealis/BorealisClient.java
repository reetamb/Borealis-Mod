package com.reetam.borealis;

import com.reetam.borealis.client.BorealisSpecialEffects;
import com.reetam.borealis.client.renderer.BorealisAuroraRenderer;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisFluids;
import com.reetam.borealis.registry.world.BorealisWorld;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;

import java.util.function.Supplier;

public class BorealisClient {

    private static void render(Supplier<? extends Block> block, RenderType render) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), render);
    }

    public static void registerBlockRenderers() {
        RenderType cutout = RenderType.cutout();
        RenderType translucent = RenderType.translucent();

        render(BorealisBlocks.BRUMAL_DOOR, cutout);
        render(BorealisBlocks.BRUMAL_TRAPDOOR, cutout);
        render(BorealisBlocks.SWEETWOOD_DOOR, cutout);
        render(BorealisBlocks.SWEETWOOD_TRAPDOOR, cutout);
        render(BorealisBlocks.CARAMELIZED_DOOR, cutout);
        render(BorealisBlocks.CARAMELIZED_TRAPDOOR, cutout);
        render(BorealisBlocks.EMBEDDED_KYANITE_ARROW, cutout);
        render(BorealisBlocks.HOLLY, cutout);
        render(BorealisBlocks.MISTERIA_HEAD, cutout);
        render(BorealisBlocks.MISTERIA_BODY, cutout);
        render(BorealisBlocks.BRUMELIAD, cutout);
        render(BorealisBlocks.WINTER_VIOLA, cutout);
        render(BorealisBlocks.WINTER_FIDDLE, cutout);
        render(BorealisBlocks.WALL_WINTER_FIDDLE, cutout);
        render(BorealisBlocks.WINTER_CELLO, cutout);
        render(BorealisBlocks.MARROW, cutout);
        render(BorealisBlocks.ARCTIC_WILLOW, cutout);
        render(BorealisBlocks.INSULATED_TANK, cutout);
        render(BorealisBlocks.TAPPER, cutout);

        render(BorealisFluids.HOT_SPRING_WATER_BLOCK, translucent);
        ItemBlockRenderTypes.setRenderLayer(BorealisFluids.HOT_SPRING_WATER_FLOWING.get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(BorealisFluids.HOT_SPRING_WATER_SOURCE.get(), translucent);
        render(BorealisBlocks.BOREALIS_PORTAL, translucent);
        render(BorealisBlocks.CLOUD, translucent);
        render(BorealisBlocks.CANDY_GLASS, translucent);

        render(BorealisBlocks.STATIC_FIELD, cutout);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerDimensionRenderers(RegisterDimensionSpecialEffectsEvent event) {
        new BorealisAuroraRenderer();
        event.register(BorealisWorld.BOREALIS_DIMENSION_TYPE.location(), new BorealisSpecialEffects());
    }
}