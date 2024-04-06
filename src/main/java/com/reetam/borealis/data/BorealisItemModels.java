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
        itemBlock(BorealisBlocks.TANZANITE_ORE);
        itemBlock(BorealisBlocks.TANZANITE_BLOCK);
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
        itemBlock(BorealisBlocks.STARRY_SLATE);
        itemBlock(BorealisBlocks.STARRY_SLATE_TILES);
        itemBlock(BorealisBlocks.PUMICE);
        itemBlock(BorealisBlocks.PUMICE_GEYSER);
        itemBlock(BorealisBlocks.TRAVERTINE);
        itemBlock(BorealisBlocks.PETRIFIED_WOOD);
        itemBlock(BorealisBlocks.PETRIFIED_WOOD_BRICKS);

        itemBlock(BorealisBlocks.BRUMAL_PLANKS);
        itemBlock(BorealisBlocks.BRUMAL_LOG);
        itemBlock(BorealisBlocks.BRUMAL_WOOD);
        itemBlock(BorealisBlocks.STRIPPED_BRUMAL_LOG);
        itemBlock(BorealisBlocks.STRIPPED_BRUMAL_WOOD);
        itemBlock(BorealisBlocks.BRUMAL_LEAVES);
        itemBlock(BorealisBlocks.BRUMAL_STAIRS);
        itemBlock(BorealisBlocks.BRUMAL_SLAB);
        itemFence(BorealisBlocks.BRUMAL_FENCE, "brumal_planks");
        itemBlock(BorealisBlocks.BRUMAL_FENCE_GATE);
        itemBlockFlat(BorealisBlocks.BRUMAL_DOOR, "item/brumal_door");
        itemBlock(BorealisBlocks.BRUMAL_TRAPDOOR, "brumal_trapdoor_bottom");
        itemBlock(BorealisBlocks.BRUMAL_BUTTON, "brumal_button_inventory");
        itemBlock(BorealisBlocks.BRUMAL_PRESSURE_PLATE);
        itemBlockFlat(BorealisBlocks.BRUMAL_SAPLING);
        normalItem(BorealisItems.BRUMAL_SIGN);

        itemBlock(BorealisBlocks.FROSTFIR_PLANKS);
        itemBlock(BorealisBlocks.FROSTFIR_LOG);
        itemBlock(BorealisBlocks.FROSTFIR_WOOD);
        itemBlock(BorealisBlocks.STRIPPED_FROSTFIR_LOG);
        itemBlock(BorealisBlocks.STRIPPED_FROSTFIR_WOOD);
        itemBlock(BorealisBlocks.FROSTFIR_LEAVES);
        itemBlock(BorealisBlocks.FROSTFIR_STAIRS);
        itemBlock(BorealisBlocks.FROSTFIR_SLAB);
        itemFence(BorealisBlocks.FROSTFIR_FENCE, "frostfir_planks");
        itemBlock(BorealisBlocks.FROSTFIR_FENCE_GATE);
        itemBlockFlat(BorealisBlocks.FROSTFIR_DOOR, "item/frostfir_door");
        itemBlock(BorealisBlocks.FROSTFIR_TRAPDOOR, "frostfir_trapdoor_bottom");
        itemBlock(BorealisBlocks.FROSTFIR_BUTTON, "frostfir_button_inventory");
        itemBlock(BorealisBlocks.FROSTFIR_PRESSURE_PLATE);
        itemBlockFlat(BorealisBlocks.FROSTFIR_SAPLING);
        normalItem(BorealisItems.FROSTFIR_SIGN);

        itemBlock(BorealisBlocks.SACCHARINE_PLANKS);
        itemBlock(BorealisBlocks.SACCHARINE_LOG);
        itemBlock(BorealisBlocks.SACCHARINE_WOOD);
        itemBlock(BorealisBlocks.STRIPPED_SACCHARINE_LOG);
        itemBlock(BorealisBlocks.SACCHARINE_LEAVES);
        itemBlock(BorealisBlocks.SACCHARINE_STAIRS);
        itemBlock(BorealisBlocks.SACCHARINE_SLAB);
        itemBlock(BorealisBlocks.STRIPPED_SACCHARINE_WOOD);
        itemFence(BorealisBlocks.SACCHARINE_FENCE, "saccharine_planks");
        itemBlock(BorealisBlocks.SACCHARINE_FENCE_GATE);
        itemBlockFlat(BorealisBlocks.SACCHARINE_DOOR, "item/saccharine_door");
        itemBlock(BorealisBlocks.SACCHARINE_TRAPDOOR, "saccharine_trapdoor_bottom");
        itemBlock(BorealisBlocks.SACCHARINE_BUTTON, "saccharine_button_inventory");
        itemBlock(BorealisBlocks.SACCHARINE_PRESSURE_PLATE);
        itemBlockFlat(BorealisBlocks.SACCHARINE_SAPLING);
        normalItem(BorealisItems.SACCHARINE_SIGN);

        itemBlock(BorealisBlocks.LIVING_SNOW_BLOCK);
        itemBlock(BorealisBlocks.SUGAR_SNOW_BLOCK);
        itemBlock(BorealisBlocks.PERMAFROST);
        itemBlock(BorealisBlocks.PERMAFROST_RUBBLE);
        itemBlock(BorealisBlocks.CLOUD);
        itemBlock(BorealisBlocks.SUGAR_SNOW);

        normalItem(BorealisItems.KYANITE_CRYSTAL);
        normalItem(BorealisItems.MOON_PEARL);
        normalItem(BorealisItems.HOT_SPRING_WATER_BUCKET);
        egg(BorealisItems.HUMMINGBIRD_SPAWN_EGG);
        egg(BorealisItems.TAKAHE_SPAWN_EGG);
        egg(BorealisItems.THRUSHER_SPAWN_EGG);
        normalItem(BorealisItems.HAT);
        normalItem(BorealisItems.TANZANITE);
        normalItem(BorealisItems.BRUMAL_BOAT);
        normalItem(BorealisItems.FROSTFIR_BOAT);
        normalItem(BorealisItems.SACCHARINE_BOAT);
    }
}
