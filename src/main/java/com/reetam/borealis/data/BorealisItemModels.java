package com.reetam.borealis.data;

import com.reetam.borealis.data.provider.BorealisItemModelProvider;
import com.reetam.borealis.item.SilverTools;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;
import com.reetam.borealis.registry.BorealisFluids;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.advancements.critereon.ItemCustomDataPredicate;
import net.minecraft.advancements.critereon.ItemSubPredicate;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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
        itemBlock(BorealisBlocks.GYPSUM);
        itemBlock(BorealisBlocks.KAOLIN);
        itemBlock(BorealisBlocks.PETRIFIED_WOOD);
        itemBlock(BorealisBlocks.PETRIFIED_WOOD_BRICKS);
        itemBlock(BorealisBlocks.FROSTFIR_LEAVES);

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
        normalItem(BorealisItems.BRUMAL_SIGN);
        normalItem(BorealisItems.BRUMAL_HANGING_SIGN);

        itemBlockFlat(BorealisBlocks.MISTERIA_HEAD, "block/misteria");
        itemBlockFlat(BorealisBlocks.BRUMELIAD, "block/brumeliad");
        itemBlockFlat(BorealisBlocks.WINTER_VIOLA, "block/winter_viola");
        normalItem(BorealisItems.WINTER_FIDDLE);
        itemBlockFlat(BorealisBlocks.WINTER_CELLO, "block/winter_cello_bottom");
        normalItem(BorealisItems.ALMS_NUT);
        itemBlock(BorealisBlocks.ALMS);
        itemBlock(BorealisBlocks.CRACKED_ALMS);
        itemBlockFlat(BorealisBlocks.MARROW, "item/marrow");

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
        normalItem(BorealisItems.SWEETWOOD_SIGN);
        normalItem(BorealisItems.SWEETWOOD_HANGING_SIGN);

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
        normalItem(BorealisItems.CARAMELIZED_HANGING_SIGN);

        itemBlock(BorealisBlocks.FIRN);
        itemBlock(BorealisBlocks.WILLOWY_FIRN);
        itemBlock(BorealisBlocks.SUGAR_SNOW_BLOCK);
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

        itemBlock(BorealisBlocks.CINNABAR);
        itemBlock(BorealisBlocks.KYANITE_FLAGSTONE);

        normalItem(BorealisItems.KYANITE_CRYSTAL);
        normalItem(BorealisFluids.HOT_SPRING_WATER_BUCKET);
        normalItem(BorealisFluids.QUICKSILVER_BUCKET);
        normalItem(BorealisFluids.SLUSH_BUCKET);
        normalItem(BorealisFluids.PORTAL_FLUID_BUCKET);
        egg(BorealisEntities.HUMMINGBIRD_SPAWN_EGG);
        egg(BorealisEntities.TAKAHE_SPAWN_EGG);
        egg(BorealisEntities.THRUSHER_SPAWN_EGG);
        egg(BorealisEntities.TUBER_SPAWN_EGG);
        normalItem(BorealisItems.HAT);
        normalItem(BorealisItems.TANZANITE);
        normalItem(BorealisItems.BRUMAL_BOAT);
        normalItem(BorealisItems.SWEETWOOD_BOAT);
        normalItem(BorealisItems.CARAMELIZED_BOAT);

        normalItem(BorealisItems.STARBURST);
        itemBlockFlat(BorealisBlocks.HOLLY);
        itemBlockFlat(BorealisBlocks.ARCTIC_WILLOW);
        itemBlock(BorealisBlocks.LICHEN_BLOCK);
        normalItem(BorealisItems.BLUE_AMBER);
        normalItem(BorealisItems.HAILSTONE);
        normalItem(BorealisItems.KYANITE_ARROW);
        itemBlock(BorealisBlocks.KILN);

        itemBlock(BorealisBlocks.MALACHITE);
        itemBlock(BorealisBlocks.CANDY_GLASS);
        itemBlock(BorealisBlocks.MODERN_DEBRIS);
        itemBlock(BorealisBlocks.GIRDLED_LOG);

        itemBlock(BorealisBlocks.TAPPER);
        itemBlock(BorealisBlocks.INSULATED_TANK);

        ItemModelBuilder SILVER_BLADE = withExistingParent(name(BorealisItems.SILVER_BLADE), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/silver/silver_blade"))
        ;
        SilverTools.initialize();
        for (int i = 0; i < 32; i++) {
            String toolName = SilverTools.IDS.get(i).name();
            SILVER_BLADE.override()
                    .model(withExistingParent(modLoc("silver_" + toolName).toString(), mcLoc("item/handheld")).texture("layer0", modLoc("item/silver/silver_" + toolName)))
                    .predicate(mcLoc("custom_model_data"), i)
                    .end();
        }
    }
}
