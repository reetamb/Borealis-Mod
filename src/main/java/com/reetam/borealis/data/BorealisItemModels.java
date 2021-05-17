package com.reetam.borealis.data;

import com.reetam.borealis.data.provider.BorealisItemModelProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BorealisItemModels extends BorealisItemModelProvider {

    public BorealisItemModels(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    public String getName() {
        return "Borealis Item Models";
    }

    @Override
    protected void registerModels() {
        itemBlock(BorealisBlocks.soapstone);
        itemBlock(BorealisBlocks.soapstone_tiles);
        itemBlock(BorealisBlocks.kyanite_ore);
        itemBlock(BorealisBlocks.soapstone);
        itemBlock(BorealisBlocks.soapstone_tiles);
        itemBlock(BorealisBlocks.soapstone_bricks);
        itemBlock(BorealisBlocks.soapstone_stairs);
        itemBlock(BorealisBlocks.soapstone_tile_stairs);
        itemBlock(BorealisBlocks.soapstone_brick_stairs);
        itemBlock(BorealisBlocks.soapstone_slab);
        itemBlock(BorealisBlocks.soapstone_tile_slab);
        itemBlock(BorealisBlocks.soapstone_brick_slab);
        wallInventory("soapstone_wall", modLoc("block/soapstone"));
        wallInventory("polished_soapstone_wall", modLoc("block/polished_soapstone"));
        wallInventory("soapstone_brick_wall", modLoc("block/soapstone_bricks"));
        itemBlock(BorealisBlocks.soapstone_button, "soapstone_button_inventory");
        itemBlock(BorealisBlocks.soapstone_pressure_plate);
        itemBlock(BorealisBlocks.slate);
        itemBlock(BorealisBlocks.slate_pillar);
        itemBlock(BorealisBlocks.slate_tiles);
        itemBlock(BorealisBlocks.pumice);
        itemBlock(BorealisBlocks.pumice_geyser);
        itemBlock(BorealisBlocks.travertine);
        itemBlock(BorealisBlocks.brumal_planks);
        itemBlock(BorealisBlocks.brumal_log);
        itemBlock(BorealisBlocks.brumal_wood);
        itemBlock(BorealisBlocks.stripped_brumal_log);
        itemBlock(BorealisBlocks.stripped_brumal_wood);
        itemBlock(BorealisBlocks.brumal_leaves);
        itemBlock(BorealisBlocks.brumal_stairs);
        itemBlock(BorealisBlocks.brumal_slab);
        itemFence(BorealisBlocks.brumal_fence, "brumal_planks");
        itemBlock(BorealisBlocks.brumal_fence_gate);
        itemBlockFlat(BorealisBlocks.brumal_door, "item/brumal_door");
        itemBlock(BorealisBlocks.brumal_trapdoor, "brumal_trapdoor_bottom");
        itemBlock(BorealisBlocks.brumal_button, "brumal_button_inventory");
        itemBlock(BorealisBlocks.brumal_pressure_plate);
        itemBlock(BorealisBlocks.frostfir_planks);
        itemBlock(BorealisBlocks.frostfir_log);
        itemBlock(BorealisBlocks.frostfir_wood);
        itemBlock(BorealisBlocks.stripped_frostfir_log);
        itemBlock(BorealisBlocks.stripped_frostfir_wood);
        itemBlock(BorealisBlocks.frostfir_leaves);
        itemBlock(BorealisBlocks.frostfir_stairs);
        itemBlock(BorealisBlocks.frostfir_slab);
        itemFence(BorealisBlocks.frostfir_fence, "frostfir_planks");
        itemBlock(BorealisBlocks.frostfir_fence_gate);
        itemBlockFlat(BorealisBlocks.frostfir_door, "item/frostfir_door");
        itemBlock(BorealisBlocks.frostfir_trapdoor, "frostfir_trapdoor_bottom");
        itemBlock(BorealisBlocks.frostfir_button, "frostfir_button_inventory");
        itemBlock(BorealisBlocks.frostfir_pressure_plate);
        itemBlock(BorealisBlocks.saccharine_planks);
        itemBlock(BorealisBlocks.saccharine_log);
        itemBlock(BorealisBlocks.saccharine_wood);
        itemBlock(BorealisBlocks.stripped_saccharine_log);
        itemBlock(BorealisBlocks.saccharine_leaves);
        itemBlock(BorealisBlocks.saccharine_stairs);
        itemBlock(BorealisBlocks.saccharine_slab);
        itemBlock(BorealisBlocks.stripped_saccharine_wood);
        itemFence(BorealisBlocks.saccharine_fence, "saccharine_planks");
        itemBlock(BorealisBlocks.saccharine_fence_gate);
        itemBlockFlat(BorealisBlocks.saccharine_door, "item/saccharine_door");
        itemBlock(BorealisBlocks.saccharine_trapdoor, "saccharine_trapdoor_bottom");
        itemBlock(BorealisBlocks.saccharine_button, "saccharine_button_inventory");
        itemBlock(BorealisBlocks.saccharine_pressure_plate);
        itemBlock(BorealisBlocks.lichen_block);
        itemBlock(BorealisBlocks.permafrost);
        itemBlock(BorealisBlocks.permafrost_rubble);
        itemBlock(BorealisBlocks.cloud);
        itemBlock(BorealisBlocks.tanzanite_block);
        itemBlock(BorealisBlocks.tanzanite_ore);
        itemBlock(BorealisBlocks.sugar_snow);

        normalItem(BorealisItems.kyanite_crystal);
        normalItem(BorealisItems.moon_pearl);
        normalItem(BorealisItems.hot_spring_water_bucket);
        egg(BorealisItems.hummingbird_spawn_egg);
        egg(BorealisItems.takahe_spawn_egg);
        egg(BorealisItems.mismic_muskox_spawn_egg);
        normalItem(BorealisItems.hat);
        normalItem(BorealisItems.tanzanite);
        normalItem(BorealisItems.tanzanite_helmet);
        normalItem(BorealisItems.tanzanite_chestplate);
        normalItem(BorealisItems.tanzanite_leggings);
        normalItem(BorealisItems.tanzanite_boots);
        toolItem(BorealisItems.tanzanite_sword);
        toolItem(BorealisItems.tanzanite_axe);
        toolItem(BorealisItems.tanzanite_pickaxe);
        toolItem(BorealisItems.tanzanite_shovel);
        toolItem(BorealisItems.tanzanite_hoe);
    }
}
