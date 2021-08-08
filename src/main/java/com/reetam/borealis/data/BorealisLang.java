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

        assumeBlockItem(BorealisBlocks.kyanite_ore);
        assumeBlockItem(BorealisBlocks.soapstone);
        assumeBlockItem(BorealisBlocks.soapstone_tiles);
        assumeBlockItem(BorealisBlocks.soapstone_bricks);
        assumeBlockItem(BorealisBlocks.soapstone_stairs);
        assumeBlockItem(BorealisBlocks.soapstone_tile_stairs);
        assumeBlockItem(BorealisBlocks.soapstone_brick_stairs);
        assumeBlockItem(BorealisBlocks.soapstone_slab);
        assumeBlockItem(BorealisBlocks.soapstone_tile_slab);
        assumeBlockItem(BorealisBlocks.soapstone_brick_slab);
        assumeBlockItem(BorealisBlocks.soapstone_wall);
        assumeBlockItem(BorealisBlocks.soapstone_tile_wall);
        assumeBlockItem(BorealisBlocks.soapstone_brick_wall);
        assumeBlockItem(BorealisBlocks.soapstone_button);
        assumeBlockItem(BorealisBlocks.soapstone_pressure_plate);
        assumeBlockItem(BorealisBlocks.slate);
        assumeBlockItem(BorealisBlocks.slate_pillar);
        assumeBlockItem(BorealisBlocks.slate_tiles);
        assumeBlockItem(BorealisBlocks.pumice);
        assumeBlockItem(BorealisBlocks.pumice_geyser);
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
        assumeBlockItem(BorealisBlocks.permafrost);
        assumeBlockItem(BorealisBlocks.permafrost_rubble);
        assumeBlockItem(BorealisBlocks.cloud);
        assumeBlockItem(BorealisBlocks.tanzanite_block);
        assumeBlockItem(BorealisBlocks.tanzanite_ore);
        assumeBlockItem(BorealisBlocks.sugar_snow);
        assumeBlockItem(BorealisBlocks.living_snow_block);
        assumeBlockItem(BorealisBlocks.sugar_snow_block);
        assumeBlockItem(BorealisItems.kyanite_crystal);
        assumeBlockItem(BorealisItems.moon_pearl);
        assumeBlockItem(BorealisItems.hot_spring_water_bucket);
        assumeBlockItem(BorealisItems.hummingbird_spawn_egg);
        assumeBlockItem(BorealisItems.takahe_spawn_egg);
        assumeBlockItem(BorealisItems.mismic_muskox_spawn_egg);
        assumeBlockItem(BorealisItems.tanzanite);
        addItem(BorealisItems.hat, "Tophat");
    }
}
