package com.reetam.borealis.data;

import com.reetam.borealis.data.provider.BorealisLangProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.data.DataGenerator;

public class BorealisLang extends BorealisLangProvider {

    public BorealisLang(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void addTranslations() {
        addItemGroup(BorealisItems.Groups.BOREALIS_ITEMS, "Borealis");

        assumeBlockItem(BorealisBlocks.KYANITE_ORE);
        assumeBlockItem(BorealisBlocks.SOAPSTONE);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_TILES);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_BRICKS);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_STAIRS);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_TILE_STAIRS);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_BRICK_STAIRS);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_SLAB);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_TILE_SLAB);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_BRICK_SLAB);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_WALL);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_TILE_WALL);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_BRICK_WALL);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_BUTTON);
        assumeBlockItem(BorealisBlocks.SOAPSTONE_PRESSURE_PLATE);
        assumeBlockItem(BorealisBlocks.SLATE);
        assumeBlockItem(BorealisBlocks.SLATE_PILLAR);
        assumeBlockItem(BorealisBlocks.SLATE_TILES);
        assumeBlockItem(BorealisBlocks.PUMICE);
        assumeBlockItem(BorealisBlocks.PUMICE_GEYSER);
        assumeBlockItem(BorealisBlocks.brumal_planks);
        assumeBlockItem(BorealisBlocks.brumal_log);
        assumeBlockItem(BorealisBlocks.brumal_wood);
        assumeBlockItem(BorealisBlocks.stripped_brumal_log);
        assumeBlockItem(BorealisBlocks.stripped_brumal_wood);
        assumeBlockItem(BorealisBlocks.brumal_leaves);
        assumeBlockItem(BorealisBlocks.brumal_stairs);
        assumeBlockItem(BorealisBlocks.brumal_slab);
        assumeBlockItem(BorealisBlocks.brumal_fence);
        assumeBlockItem(BorealisBlocks.brumal_fence_gate);
        assumeBlockItem(BorealisBlocks.brumal_door);
        assumeBlockItem(BorealisBlocks.brumal_trapdoor);
        assumeBlockItem(BorealisBlocks.brumal_button);
        assumeBlockItem(BorealisBlocks.brumal_pressure_plate);
        assumeBlockItem(BorealisBlocks.brumal_sapling);
        assumeBlockItem(BorealisBlocks.frostfir_planks);
        assumeBlockItem(BorealisBlocks.frostfir_log);
        assumeBlockItem(BorealisBlocks.frostfir_wood);
        assumeBlockItem(BorealisBlocks.stripped_frostfir_log);
        assumeBlockItem(BorealisBlocks.stripped_frostfir_wood);
        assumeBlockItem(BorealisBlocks.frostfir_leaves);
        assumeBlockItem(BorealisBlocks.frostfir_stairs);
        assumeBlockItem(BorealisBlocks.frostfir_slab);
        assumeBlockItem(BorealisBlocks.frostfir_fence);
        assumeBlockItem(BorealisBlocks.frostfir_fence_gate);
        assumeBlockItem(BorealisBlocks.frostfir_door);
        assumeBlockItem(BorealisBlocks.frostfir_trapdoor);
        assumeBlockItem(BorealisBlocks.frostfir_button);
        assumeBlockItem(BorealisBlocks.frostfir_pressure_plate);
        assumeBlockItem(BorealisBlocks.frostfir_sapling);
        assumeBlockItem(BorealisBlocks.saccharine_planks);
        assumeBlockItem(BorealisBlocks.saccharine_log);
        assumeBlockItem(BorealisBlocks.saccharine_wood);
        assumeBlockItem(BorealisBlocks.stripped_saccharine_log);
        assumeBlockItem(BorealisBlocks.saccharine_leaves);
        assumeBlockItem(BorealisBlocks.saccharine_stairs);
        assumeBlockItem(BorealisBlocks.saccharine_slab);
        assumeBlockItem(BorealisBlocks.stripped_saccharine_wood);
        assumeBlockItem(BorealisBlocks.saccharine_fence);
        assumeBlockItem(BorealisBlocks.saccharine_fence_gate);
        assumeBlockItem(BorealisBlocks.saccharine_door);
        assumeBlockItem(BorealisBlocks.saccharine_trapdoor);
        assumeBlockItem(BorealisBlocks.saccharine_button);
        assumeBlockItem(BorealisBlocks.saccharine_pressure_plate);
        assumeBlockItem(BorealisBlocks.saccharine_sapling);
        assumeBlockItem(BorealisBlocks.PERMAFROST);
        assumeBlockItem(BorealisBlocks.PERMAFROST_RUBBLE);
        assumeBlockItem(BorealisBlocks.CLOUD);
        assumeBlockItem(BorealisBlocks.TANZANITE_BLOCK);
        assumeBlockItem(BorealisBlocks.TANZANITE_ORE);
        assumeBlockItem(BorealisBlocks.SUGAR_SNOW);
        assumeBlockItem(BorealisBlocks.LIVING_SNOW_BLOCK);
        assumeBlockItem(BorealisBlocks.SUGAR_SNOW_BLOCK);

        assumeBlockItem(BorealisItems.KYANITE_CRYSTAL);
        assumeBlockItem(BorealisItems.MOON_PEARL);
        assumeBlockItem(BorealisItems.HOT_SPRING_WATER_BUCKET);
        assumeBlockItem(BorealisItems.HUMMINGBIRD_SPAWN_EGG);
        assumeBlockItem(BorealisItems.TAKAHE_SPAWN_EGG);
        assumeBlockItem(BorealisItems.MISMIC_MUSKOX_SPAWN_EGG);
        assumeBlockItem(BorealisItems.TANZANITE);
        assumeBlockItem(BorealisItems.BRUMAL_BOAT);
        assumeBlockItem(BorealisItems.FROSTFIR_BOAT);
        assumeBlockItem(BorealisItems.SACCHARINE_BOAT);
        addItem(BorealisItems.HAT, "Tophat");
    }
}
