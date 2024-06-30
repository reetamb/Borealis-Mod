package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.data.provider.BorealisItemModelProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisFluids;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
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
        wallInventory("soapstone_wall", texture("soapstone"));
        wallInventory("soapstone_tile_wall", texture("soapstone_tiles"));
        wallInventory("soapstone_brick_wall", texture("soapstone_bricks"));
        buttonInventory("soapstone_button", texture("soapstone_bricks"));
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
        buttonInventory("brumal_button", texture("brumal_planks"));
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
        buttonInventory("frostfir_button", texture("frostfir_planks"));
        itemBlock(BorealisBlocks.FROSTFIR_PRESSURE_PLATE);
        itemBlockFlat(BorealisBlocks.FROSTFIR_SAPLING);
        normalItem(BorealisItems.FROSTFIR_SIGN);

        itemBlock(BorealisBlocks.SWEETWOOD_PLANKS);
        itemBlock(BorealisBlocks.SWEETWOOD_LOG);
        itemBlock(BorealisBlocks.SWEETWOOD);
        itemBlock(BorealisBlocks.STRIPPED_SWEETWOOD_LOG);
        itemBlock(BorealisBlocks.SWEETWOOD_LEAVES);
        itemBlock(BorealisBlocks.SWEETWOOD_STAIRS);
        itemBlock(BorealisBlocks.SWEETWOOD_SLAB);
        itemBlock(BorealisBlocks.STRIPPED_SWEETWOOD);
        itemFence(BorealisBlocks.SWEETWOOD_FENCE, "sweetwood_planks");
        itemBlock(BorealisBlocks.SWEETWOOD_FENCE_GATE);
        itemBlockFlat(BorealisBlocks.SWEETWOOD_DOOR, "item/sweetwood_door");
        itemBlock(BorealisBlocks.SWEETWOOD_TRAPDOOR, "sweetwood_trapdoor_bottom");
        buttonInventory("sweetwood_button", texture("sweetwood_planks"));
        itemBlock(BorealisBlocks.SWEETWOOD_PRESSURE_PLATE);
        itemBlockFlat(BorealisBlocks.SWEETWOOD_SAPLING);
        normalItem(BorealisItems.SWEETWOOD_SIGN);

        itemBlock(BorealisBlocks.CARAMELIZED_PLANKS);
        itemBlock(BorealisBlocks.CARAMELIZED_LOG);
        itemBlock(BorealisBlocks.CARAMELIZED_WOOD);
        itemBlock(BorealisBlocks.STRIPPED_CARAMELIZED_LOG);
        itemBlock(BorealisBlocks.GLAZED_LEAVES);
        itemBlock(BorealisBlocks.CARAMELIZED_STAIRS);
        itemBlock(BorealisBlocks.CARAMELIZED_SLAB);
        itemBlock(BorealisBlocks.STRIPPED_CARAMELIZED_WOOD);
        itemFence(BorealisBlocks.CARAMELIZED_FENCE, "caramelized_planks");
        itemBlock(BorealisBlocks.CARAMELIZED_FENCE_GATE);
        itemBlockFlat(BorealisBlocks.CARAMELIZED_DOOR, "item/caramelized_door");
        itemBlock(BorealisBlocks.CARAMELIZED_TRAPDOOR, "caramelized_trapdoor_bottom");
        buttonInventory("caramelized_button", texture("caramelized_planks"));
        itemBlock(BorealisBlocks.CARAMELIZED_PRESSURE_PLATE);
        normalItem(BorealisItems.CARAMELIZED_SIGN);

        itemBlock(BorealisBlocks.LIVING_SNOW_BLOCK);
        itemBlock(BorealisBlocks.SUGAR_SNOW_BLOCK);
        itemBlock(BorealisBlocks.PERMAFROST);
        itemBlock(BorealisBlocks.PERMAFROST_RUBBLE);
        itemBlock(BorealisBlocks.CLOUD);
        itemBlock(BorealisBlocks.SUGAR_SNOW);
        itemBlock(BorealisBlocks.HAILSTONE);
        itemBlock(BorealisBlocks.BONE_DRY_WOOD);
        itemBlock(BorealisBlocks.BONE_DRY_WOOD_BRICKS);
        itemBlock(BorealisBlocks.PEAT);

        itemBlock(BorealisBlocks.KYANITE_CABLE);
        itemBlock(BorealisBlocks.KYANITE_BULB);

        itemBlock(BorealisBlocks.FLUORITE, "neon_fluorite");
        itemBlockFlat(BorealisBlocks.STATIC_FIELD);
        normalItem(BorealisItems.FLEECE);

        normalItem(BorealisItems.KYANITE_CRYSTAL);
        normalItem(BorealisFluids.HOT_SPRING_WATER_BUCKET);
        egg(BorealisItems.HUMMINGBIRD_SPAWN_EGG);
        egg(BorealisItems.TAKAHE_SPAWN_EGG);
        egg(BorealisItems.THRUSHER_SPAWN_EGG);
        normalItem(BorealisItems.HAT);
        normalItem(BorealisItems.TANZANITE);
        normalItem(BorealisItems.BRUMAL_BOAT);
        normalItem(BorealisItems.FROSTFIR_BOAT);
        normalItem(BorealisItems.SWEETWOOD_BOAT);
        normalItem(BorealisItems.CARAMELIZED_BOAT);

        normalItem(BorealisItems.STARBURST);
        itemBlockFlat(BorealisBlocks.HOLLY);
        itemBlock(BorealisBlocks.LICHEN_BLOCK);
        normalItem(BorealisItems.BLUE_AMBER);
        normalItem(BorealisItems.HAILSTONE);
        normalItem(BorealisItems.KYANITE_ARROW);
    }
}
