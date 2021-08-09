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
        itemBlock(BorealisBlocks.SOAPSTONE);
        itemBlock(BorealisBlocks.SOAPSTONE_TILES);
        itemBlock(BorealisBlocks.KYANITE_ORE);
        itemBlock(BorealisBlocks.SOAPSTONE);
        itemBlock(BorealisBlocks.SOAPSTONE_TILES);
        itemBlock(BorealisBlocks.SOAPSTONE_BRICKS);
        itemBlock(BorealisBlocks.SOAPSTONE_STAIRS);
        itemBlock(BorealisBlocks.SOAPSTONE_TILE_STAIRS);
        itemBlock(BorealisBlocks.SOAPSTONE_BRICK_STAIRS);
        itemBlock(BorealisBlocks.SOAPSTONE_SLAB);
        itemBlock(BorealisBlocks.SOAPSTONE_TILE_SLAB);
        itemBlock(BorealisBlocks.SOAPSTONE_BRICK_SLAB);
        wallInventory("soapstone_wall", modLoc("block/soapstone"));
        wallInventory("soapstone_tile_wall", modLoc("block/soapstone_tiles"));
        wallInventory("soapstone_brick_wall", modLoc("block/soapstone_bricks"));
        itemBlock(BorealisBlocks.SOAPSTONE_BUTTON, "soapstone_button_inventory");
        itemBlock(BorealisBlocks.SOAPSTONE_PRESSURE_PLATE);
        itemBlock(BorealisBlocks.SLATE);
        itemBlock(BorealisBlocks.SLATE_PILLAR);
        itemBlock(BorealisBlocks.SLATE_TILES);
        itemBlock(BorealisBlocks.PUMICE);
        itemBlock(BorealisBlocks.PUMICE_GEYSER);
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
        itemBlockFlat(BorealisBlocks.brumal_sapling);
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
        itemBlockFlat(BorealisBlocks.frostfir_sapling);
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
        itemBlockFlat(BorealisBlocks.saccharine_sapling);
        itemBlock(BorealisBlocks.LIVING_SNOW_BLOCK);
        itemBlock(BorealisBlocks.SUGAR_SNOW_BLOCK);
        itemBlock(BorealisBlocks.PERMAFROST);
        itemBlock(BorealisBlocks.PERMAFROST_RUBBLE);
        itemBlock(BorealisBlocks.CLOUD);
        itemBlock(BorealisBlocks.TANZANITE_BLOCK);
        itemBlock(BorealisBlocks.TANZANITE_ORE);
        itemBlock(BorealisBlocks.SUGAR_SNOW);

        normalItem(BorealisItems.KYANITE_CRYSTAL);
        normalItem(BorealisItems.MOON_PEARL);
        normalItem(BorealisItems.HOT_SPRING_WATER_BUCKET);
        egg(BorealisItems.HUMMINGBIRD_SPAWN_EGG);
        egg(BorealisItems.TAKAHE_SPAWN_EGG);
        egg(BorealisItems.MISMIC_MUSKOX_SPAWN_EGG);
        normalItem(BorealisItems.HAT);
        normalItem(BorealisItems.TANZANITE);
        normalItem(BorealisItems.BRUMAL_BOAT);
        normalItem(BorealisItems.FROSTFIR_BOAT);
        normalItem(BorealisItems.SACCHARINE_BOAT);
    }
}
