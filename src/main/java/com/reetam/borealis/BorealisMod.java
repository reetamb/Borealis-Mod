package com.reetam.borealis;

import com.reetam.borealis.client.ClientProxy;
import com.reetam.borealis.data.*;
import com.reetam.borealis.registry.*;
import com.reetam.borealis.setup.events.EventHandler;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BorealisMod.MODID)
public class BorealisMod {

    public static final String MODID = "borealis";

    public BorealisMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherData);
        EventHandler.addEvents(forgeBus);

        BorealisEntities.ENTITIES.register(bus);
        BorealisBlocks.BLOCKS.register(bus);
        BorealisTileEntities.TILE_ENTITIES.register(bus);
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
        ClientProxy.registerDimensionRenderers();
        ClientProxy.registerTileEntityRenderers();

        event.enqueueWork(ClientProxy::registerWoodTypes);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        BorealisEntities.registerEntityAttributes();
        BorealisEntities.registerSpawnPlacements();
        BorealisFeatures.registerConfiguredFeatures();
        BorealisDimensions.registerDimensionGenerators();
        BorealisBlocks.registerFlowerPots();
        BorealisBlocks.registerWoodTypes();
        BorealisBlocks.registerAxeStrips();
        BorealisBlocks.registerHoeTills();
        BorealisBlocks.registerComposts();
        BorealisItems.registerDispenserBehaviors();
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(new BorealisBlockStates(generator, helper));
            generator.addProvider(new BorealisItemModels(generator, helper));
            generator.addProvider(new BorealisLang(generator));
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