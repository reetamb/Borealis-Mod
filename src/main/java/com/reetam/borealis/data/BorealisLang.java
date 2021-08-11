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
        assumeBlockItem(BorealisBlocks.BRUMAL_PLANKS);
        assumeBlockItem(BorealisBlocks.BRUMAL_LOG);
        assumeBlockItem(BorealisBlocks.BRUMAL_WOOD);
        assumeBlockItem(BorealisBlocks.STRIPPED_BRUMAL_LOG);
        assumeBlockItem(BorealisBlocks.STRIPPED_BRUMAL_WOOD);
        assumeBlockItem(BorealisBlocks.BRUMAL_LEAVES);
        assumeBlockItem(BorealisBlocks.BRUMAL_STAIRS);
        assumeBlockItem(BorealisBlocks.BRUMAL_SLAB);
        assumeBlockItem(BorealisBlocks.BRUMAL_FENCE);
        assumeBlockItem(BorealisBlocks.BRUMAL_FENCE_GATE);
        assumeBlockItem(BorealisBlocks.BRUMAL_DOOR);
        assumeBlockItem(BorealisBlocks.BRUMAL_TRAPDOOR);
        assumeBlockItem(BorealisBlocks.BRUMAL_BUTTON);
        assumeBlockItem(BorealisBlocks.BRUMAL_PRESSURE_PLATE);
        assumeBlockItem(BorealisBlocks.BRUMAL_SAPLING);
        assumeBlockItem(BorealisBlocks.BRUMAL_SIGN);
        assumeBlockItem(BorealisBlocks.FROSTFIR_PLANKS);
        assumeBlockItem(BorealisBlocks.FROSTFIR_LOG);
        assumeBlockItem(BorealisBlocks.FROSTFIR_WOOD);
        assumeBlockItem(BorealisBlocks.STRIPPED_FROSTFIR_LOG);
        assumeBlockItem(BorealisBlocks.STRIPPED_FROSTFIR_WOOD);
        assumeBlockItem(BorealisBlocks.FROSTFIR_LEAVES);
        assumeBlockItem(BorealisBlocks.FROSTFIR_STAIRS);
        assumeBlockItem(BorealisBlocks.FROSTFIR_SLAB);
        assumeBlockItem(BorealisBlocks.FROSTFIR_FENCE);
        assumeBlockItem(BorealisBlocks.FROSTFIR_FENCE_GATE);
        assumeBlockItem(BorealisBlocks.FROSTFIR_DOOR);
        assumeBlockItem(BorealisBlocks.FROSTFIR_TRAPDOOR);
        assumeBlockItem(BorealisBlocks.FROSTFIR_BUTTON);
        assumeBlockItem(BorealisBlocks.FROSTFIR_PRESSURE_PLATE);
        assumeBlockItem(BorealisBlocks.FROSTFIR_SAPLING);
        assumeBlockItem(BorealisBlocks.FROSTFIR_SIGN);
        assumeBlockItem(BorealisBlocks.SACCHARINE_PLANKS);
        assumeBlockItem(BorealisBlocks.SACCHARINE_LOG);
        assumeBlockItem(BorealisBlocks.SACCHARINE_WOOD);
        assumeBlockItem(BorealisBlocks.STRIPPED_SACCHARINE_LOG);
        assumeBlockItem(BorealisBlocks.SACCHARINE_LEAVES);
        assumeBlockItem(BorealisBlocks.SACCHARINE_STAIRS);
        assumeBlockItem(BorealisBlocks.SACCHARINE_SLAB);
        assumeBlockItem(BorealisBlocks.STRIPPED_SACCHARINE_WOOD);
        assumeBlockItem(BorealisBlocks.SACCHARINE_FENCE);
        assumeBlockItem(BorealisBlocks.SACCHARINE_FENCE_GATE);
        assumeBlockItem(BorealisBlocks.SACCHARINE_DOOR);
        assumeBlockItem(BorealisBlocks.SACCHARINE_TRAPDOOR);
        assumeBlockItem(BorealisBlocks.SACCHARINE_BUTTON);
        assumeBlockItem(BorealisBlocks.SACCHARINE_PRESSURE_PLATE);
        assumeBlockItem(BorealisBlocks.SACCHARINE_SAPLING);
        assumeBlockItem(BorealisBlocks.SACCHARINE_SIGN);
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
