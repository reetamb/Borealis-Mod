package com.reetam.borealis;

import com.reetam.borealis.client.ClientProxy;
import com.reetam.borealis.data.BorealisBlockstates;
import com.reetam.borealis.data.BorealisItemModels;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.reetam.borealis.registry.*;

@Mod(BorealisMod.MODID)
public class BorealisMod {

    public static final String MODID = "borealis";

    public BorealisMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
//        bus.addListener(this::gatherData);

        BorealisEntities.ENTITIES.register(bus);
        BorealisBlocks.BLOCKS.register(bus);
        BorealisItems.ITEMS.register(bus);
        BorealisFeatures.FEATURES.register(bus);
    }

    public void clientSetup(FMLClientSetupEvent event) {
        ClientProxy.registerBlockRenderers();
        ClientProxy.registerEntityRenderers();
        ClientProxy.registerBlockColors();
        ClientProxy.registerItemColors();
    }

    public void setup(FMLCommonSetupEvent event) {
        BorealisEntities.entityAttributes();
        BorealisFeatures.registerConfiguredFeatures();

    }

//    public void gatherData(GatherDataEvent event) {
//        DataGenerator generator = event.getGenerator();
//
//        if (event.includeClient()) {
//            generator.addProvider(new BorealisBlockstates(generator, event.getExistingFileHelper()));
//            generator.addProvider(new BorealisItemModels(generator, event.getExistingFileHelper()));
//        }
//    }
}