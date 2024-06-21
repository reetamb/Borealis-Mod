package com.reetam.borealis;

import com.mojang.logging.LogUtils;
import com.reetam.borealis.client.renderer.BorealisFluidRenderer;
import com.reetam.borealis.data.*;
import com.reetam.borealis.modify.events.BlockEvents;
import com.reetam.borealis.modify.events.PlayerEvents;
import com.reetam.borealis.registry.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

@Mod(BorealisMod.MODID)
public class BorealisMod {

    public static final String MODID = "borealis";
    public static final Logger LOGGER = LogUtils.getLogger();
    // 1.18 Port Todo:
    // FINISHED Fix noise sliders so that island tops are less flat
    // FINISHED Fix biome distribution
    // FINISHED Fix tree placers to fix tree features
    // Fix dimension render effects

    public BorealisMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherData);

        forgeBus.register(PlayerEvents.class);
        forgeBus.register(BlockEvents.class);
        forgeBus.register(BorealisFluidRenderer.class);

        DeferredRegister<?>[] registers = {
                BorealisBlocks.BLOCKS,
                BorealisBlockEntities.BLOCK_ENTITIES,
                BorealisFluids.FLUIDS,
                BorealisFluids.FLUID_TYPES,
                BorealisItems.ITEMS,
                BorealisItems.Tabs.TABS,
                BorealisEntities.ENTITIES,
                BorealisFeatures.FEATURES,
                BorealisFeatures.TreePlacers.FOLIAGE_PLACERS,
                BorealisFeatures.TreePlacers.TRUNK_PLACERS,
                BorealisSounds.SOUND_EVENTS,
                BorealisPotionEffects.EFFECTS,
                BorealisDimensions.POIS
        };

        for (DeferredRegister<?> register : registers) {
            register.register(bus);
        }
    }

    public void clientSetup(FMLClientSetupEvent event) {
        BorealisClient.registerBlockRenderers();
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        BorealisEntities.registerSpawnPlacements();
        BorealisCommon.registerFlowerPots();
        BorealisCommon.registerWoodTypes();
        BorealisCommon.registerAxeStrips();
        BorealisCommon.registerHoeTills();
        BorealisCommon.registerComposts();
        BorealisCommon.registerDispenserBehaviors();
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new BorealisBlockStates(generator, helper));
        generator.addProvider(event.includeClient(), new BorealisItemModels(generator, helper));
        generator.addProvider(event.includeClient(), new BorealisLang(packOutput));
        generator.addProvider(event.includeClient(), new BorealisSoundDefinitions(packOutput, helper));

        generator.addProvider(event.includeServer(), new BorealisRegistrySets(packOutput, lookupProvider));
        BlockTagsProvider tags = new BorealisBlockTags(generator.getPackOutput(), lookupProvider, helper);
        generator.addProvider(event.includeServer(), new BorealisLootTables(packOutput));
        generator.addProvider(event.includeServer(), tags);
        generator.addProvider(event.includeServer(), new BorealisItemTags(packOutput, lookupProvider, tags.contentsGetter(), helper));
        generator.addProvider(event.includeServer(), new BorealisFluidTags(generator, lookupProvider, helper));
        generator.addProvider(event.includeServer(), new BorealisAdvancements(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new BorealisRecipes(generator));
    }
}