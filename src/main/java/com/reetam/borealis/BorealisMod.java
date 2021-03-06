package com.reetam.borealis;

import com.reetam.borealis.client.ClientProxy;
import com.reetam.borealis.client.renderer.BorealisSkyRenderer;
import com.reetam.borealis.client.renderer.fluid.FluidRenderer;
import com.reetam.borealis.data.*;
import com.reetam.borealis.registry.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.ISkyRenderHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.system.CallbackI;

@Mod(BorealisMod.MODID)
public class BorealisMod {

    public static final String MODID = "borealis";

    public BorealisMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherData);
        forgeBus.addListener(FluidRenderer::fluidOverlay);

        BorealisEntities.ENTITIES.register(bus);
        BorealisBlocks.BLOCKS.register(bus);
        BorealisFluids.FLUIDS.register(bus);
        BorealisItems.ITEMS.register(bus);
        BorealisFeatures.FEATURES.register(bus);
        BorealisSounds.SOUND_EVENTS.register(bus);
        BorealisFeatures.TreePlacers.FOLIAGE_PLACERS.register(bus);
        BorealisFeatures.SurfaceBuilders.SURFACE_BUILDERS.register(bus);
    }

    public void clientSetup(FMLClientSetupEvent event) {
        ClientProxy.registerBlockRenderers();
        ClientProxy.registerEntityRenderers();

        DimensionRenderInfo.EFFECTS.put(BorealisDimensions.borealis.location(), new DimensionRenderInfo(Float.NaN, false, DimensionRenderInfo.FogType.NONE, false, true) {
            @Override
            public Vector3d getBrightnessDependentFogColor(Vector3d vector3d, float sun) {
                return vector3d;
            }

            @Override
            public boolean isFoggyAt(int x, int y) {
//                assert Minecraft.getInstance().world != null;
//                return Minecraft.getInstance().world.isNightTime();
                return true;
            }
        });
    }

    public void setup(FMLCommonSetupEvent event) {
        BorealisEntities.entityAttributes();
        BorealisEntities.spawnPlacements();
        BorealisFeatures.registerConfiguredFeatures();
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(new BorealisBlockStates(generator, helper));
            generator.addProvider(new BorealisItemModels(generator, helper));
        }
        if (event.includeServer()) {
            generator.addProvider(new BorealisLootTables(generator));
            BorealisBlockTags tags = new BorealisBlockTags(generator, helper);
            generator.addProvider(tags);
            generator.addProvider(new BorealisItemTags(generator, tags, helper));
            generator.addProvider(new BorealisRecipes(generator));
        }
    }
}