package com.reetam.borealis;

import com.mojang.logging.LogUtils;
import com.reetam.borealis.data.*;
import com.reetam.borealis.data.trigger.BorealisTriggers;
import com.reetam.borealis.item.SilverTools;
import com.reetam.borealis.modify.events.PlayerEvents;
import com.reetam.borealis.registry.*;
import com.reetam.borealis.registry.world.BorealisDimensions;
import com.reetam.borealis.registry.world.BorealisFeatures;
import com.reetam.borealis.registry.world.BorealisRegistrySets;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

@Mod(BorealisMod.MODID)
public class BorealisMod {

    public static final String MODID = "borealis";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final int MIN_HEIGHT = 256;
    public static final int HEIGHT = 256;
    public static final float HUNGER_FACTOR = 1/2.0F;
    public static final float FALL_FACTOR = 2/3.0F;

    public BorealisMod(IEventBus bus) {

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherData);
        bus.addListener(BorealisEntities::registerEntityRenderers);
        bus.addListener(BorealisEntities::registerEntityLayerDefinitions);
        bus.addListener(BorealisFluids::registerFluidClient);
        bus.addListener(BorealisClient::registerDimensionRenderers);
        bus.addListener(BorealisEntities::registerEntityAttributes);
        bus.addListener(BorealisCommon::spawnRestrictions);
        NeoForge.EVENT_BUS.addListener(PlayerEvents::loadKyaniteArrowEvent);
        NeoForge.EVENT_BUS.addListener(PlayerEvents::burnInAtmosphereEvent);
        NeoForge.EVENT_BUS.addListener(PlayerEvents::reducedFallDamageEvent);
        NeoForge.EVENT_BUS.addListener(BorealisCommon::toolInteractions);

        DeferredRegister<?>[] registers = {
                BorealisBlocks.BLOCKS,
                BorealisBlockEntities.BLOCK_ENTITIES,
                BorealisFluids.FLUIDS,
                BorealisFluids.TYPES,
                BorealisItems.ITEMS,
                BorealisItems.Tabs.TABS,
                BorealisEntities.ENTITIES,
                BorealisFeatures.FEATURES,
                BorealisFeatures.TreePlacers.FOLIAGE_PLACERS,
                BorealisFeatures.TreePlacers.TRUNK_PLACERS,
                BorealisFeatures.TreePlacers.TREE_DECORATORS,
                BorealisSounds.SOUND_EVENTS,
                BorealisEffects.EFFECTS,
                BorealisDimensions.POIS,
                BorealisTriggers.TRIGGERS
        };

        for (DeferredRegister<?> register : registers) {
            register.register(bus);
        }
    }

    public void clientSetup(FMLClientSetupEvent event) {
        BorealisClient.registerBlockRenderers();
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        BorealisCommon.registerWoodTypes();
        BorealisCommon.registerDispenserBehaviors();
        BorealisCommon.registerFluidInteractions();
        SilverTools.initialize();
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
        generator.addProvider(event.includeServer(), new BorealisLootTables(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), tags);
        generator.addProvider(event.includeServer(), new BorealisItemTags(packOutput, lookupProvider, tags.contentsGetter(), helper));
        generator.addProvider(event.includeServer(), new BorealisFluidTags(generator, lookupProvider, helper));
        generator.addProvider(event.includeServer(), new BorealisAdvancements(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new BorealisRecipes(generator, lookupProvider));
    }
}