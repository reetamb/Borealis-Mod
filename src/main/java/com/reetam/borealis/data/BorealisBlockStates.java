package com.reetam.borealis.data;

import com.reetam.borealis.block.PermafrostBlock;
import com.reetam.borealis.block.property.PermafrostCover;
import com.reetam.borealis.data.provider.BorealisBlockStateProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BorealisBlockStates extends BorealisBlockStateProvider {

    public BorealisBlockStates(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    public String getName() {
        return "Borealis Block States";
    }

    @Override
    protected void registerStatesAndModels() {
        block(BorealisBlocks.SOAPSTONE);
        block(BorealisBlocks.SOAPSTONE_TILES);
        block(BorealisBlocks.SOAPSTONE_BRICKS);
        logBlock(BorealisBlocks.KYANITE_ORE.get());
        stairs(BorealisBlocks.SOAPSTONE_STAIRS, BorealisBlocks.SOAPSTONE);
        stairs(BorealisBlocks.SOAPSTONE_TILE_STAIRS, BorealisBlocks.SOAPSTONE_TILES);
        stairs(BorealisBlocks.SOAPSTONE_BRICK_STAIRS, BorealisBlocks.SOAPSTONE_BRICKS);
        slab(BorealisBlocks.SOAPSTONE_SLAB, BorealisBlocks.SOAPSTONE);
        slab(BorealisBlocks.SOAPSTONE_TILE_SLAB, BorealisBlocks.SOAPSTONE_TILES);
        slab(BorealisBlocks.SOAPSTONE_BRICK_SLAB, BorealisBlocks.SOAPSTONE_BRICKS);
        wallBlock(BorealisBlocks.SOAPSTONE_WALL.get(), texture("soapstone"));
        wallBlock(BorealisBlocks.SOAPSTONE_TILE_WALL.get(), texture("soapstone_tiles"));
        wallBlock(BorealisBlocks.SOAPSTONE_BRICK_WALL.get(), texture("soapstone_bricks"));
        block(BorealisBlocks.SLATE);
        log(BorealisBlocks.SLATE_PILLAR, "slate_pillar");
        block(BorealisBlocks.SLATE_TILES);
        block(BorealisBlocks.PUMICE);
        block(BorealisBlocks.TRAVERTINE);
        block(BorealisBlocks.PETRIFIED_WOOD);
        block(BorealisBlocks.PETRIFIED_WOOD_BRICKS);
        block(BorealisBlocks.LIVING_SNOW_BLOCK);
        block(BorealisBlocks.SUGAR_SNOW_BLOCK);
        block(BorealisBlocks.PERMAFROST_RUBBLE);
        buttonBlock(BorealisBlocks.SOAPSTONE_BUTTON.get(), texture("soapstone"));
        pressurePlateBlock(BorealisBlocks.SOAPSTONE_PRESSURE_PLATE.get(), texture("soapstone"));
        block(BorealisBlocks.TANZANITE_ORE);
        block(BorealisBlocks.TANZANITE_BLOCK);
        block(BorealisBlocks.STARRY_SLATE);
        block(BorealisBlocks.STARRY_SLATE_TILES);
        block(BorealisBlocks.CLOUD);
        block(BorealisBlocks.HAILSTONE);
        block(BorealisBlocks.BONE_DRY_WOOD);
        block(BorealisBlocks.BONE_DRY_WOOD_BRICKS);
        getVariantBuilder(BorealisBlocks.PERMAFROST.get())
                .partialState().with(PermafrostBlock.COVER, PermafrostCover.CLEAR).modelForState()
                    .modelFile(this.models().cubeAll("permafrost", texture("permafrost"))).addModel()
                .partialState().with(PermafrostBlock.COVER, PermafrostCover.ICY).modelForState()
                        .modelFile(this.models().cubeBottomTop("icy_permafrost", texture("icy_permafrost_side"), texture("permafrost"), mcLoc("block/packed_ice"))).addModel()
                .partialState().with(PermafrostBlock.COVER, PermafrostCover.SNOWY).modelForState()
                    .modelFile(this.models().cubeBottomTop("snowy_permafrost", texture("snowy_permafrost_side"), texture("permafrost"), mcLoc("block/snow"))).addModel()
                .partialState().with(PermafrostBlock.COVER, PermafrostCover.SUGARY).modelForState()
                    .modelFile(this.models().cubeBottomTop("sugary_permafrost", texture("sugary_permafrost_side"), texture("permafrost"), texture("sugar_snow_block"))).addModel();
        simpleBlock(BorealisBlocks.PUMICE_GEYSER.get(), this.models().cubeTop("pumice_geyser", texture("pumice"), texture("pumice_geyser")));
        wood();
    }

    private void wood() {
        block(BorealisBlocks.BRUMAL_PLANKS);
        log(BorealisBlocks.BRUMAL_LOG, "brumal_log");
        wood(BorealisBlocks.BRUMAL_WOOD, BorealisBlocks.BRUMAL_LOG);
        log(BorealisBlocks.STRIPPED_BRUMAL_LOG, "stripped_brumal_log");
        wood(BorealisBlocks.STRIPPED_BRUMAL_WOOD, BorealisBlocks.STRIPPED_BRUMAL_LOG);
        block(BorealisBlocks.BRUMAL_LEAVES);
        stairs(BorealisBlocks.BRUMAL_STAIRS, BorealisBlocks.BRUMAL_PLANKS);
        slab(BorealisBlocks.BRUMAL_SLAB, BorealisBlocks.BRUMAL_PLANKS);
        fenceBlock(BorealisBlocks.BRUMAL_FENCE.get(), texture("brumal_planks"));
        fenceGateBlock(BorealisBlocks.BRUMAL_FENCE_GATE.get(), texture("brumal_planks"));
        door(BorealisBlocks.BRUMAL_DOOR, "brumal");
        trapdoor(BorealisBlocks.BRUMAL_TRAPDOOR, "brumal");
        crossBlock(BorealisBlocks.BRUMAL_SAPLING);
        buttonBlock(BorealisBlocks.BRUMAL_BUTTON.get(), texture("brumal_planks"));
        pressurePlateBlock(BorealisBlocks.BRUMAL_PRESSURE_PLATE.get(), texture("brumal_planks"));
        signBlock(BorealisBlocks.BRUMAL_SIGN.get(), BorealisBlocks.BRUMAL_WALL_SIGN.get(), texture("brumal_planks"));
        block(BorealisBlocks.FROSTFIR_PLANKS);
        log(BorealisBlocks.FROSTFIR_LOG, "frostfir_log");
        wood(BorealisBlocks.FROSTFIR_WOOD, BorealisBlocks.FROSTFIR_LOG);
        log(BorealisBlocks.STRIPPED_FROSTFIR_LOG, "stripped_frostfir_log");
        wood(BorealisBlocks.STRIPPED_FROSTFIR_WOOD, BorealisBlocks.STRIPPED_FROSTFIR_LOG);
        block(BorealisBlocks.FROSTFIR_LEAVES);
        stairs(BorealisBlocks.FROSTFIR_STAIRS, BorealisBlocks.FROSTFIR_PLANKS);
        slab(BorealisBlocks.FROSTFIR_SLAB, BorealisBlocks.FROSTFIR_PLANKS);
        fenceBlock(BorealisBlocks.FROSTFIR_FENCE.get(), texture("frostfir_planks"));
        fenceGateBlock(BorealisBlocks.FROSTFIR_FENCE_GATE.get(), texture("frostfir_planks"));
        door(BorealisBlocks.FROSTFIR_DOOR, "frostfir");
        trapdoor(BorealisBlocks.FROSTFIR_TRAPDOOR, "frostfir");
        crossBlock(BorealisBlocks.FROSTFIR_SAPLING);
        buttonBlock(BorealisBlocks.FROSTFIR_BUTTON.get(), texture("frostfir_planks"));
        pressurePlateBlock(BorealisBlocks.FROSTFIR_PRESSURE_PLATE.get(), texture("frostfir_planks"));
        signBlock(BorealisBlocks.FROSTFIR_SIGN.get(), BorealisBlocks.FROSTFIR_WALL_SIGN.get(), texture("frostfir_planks"));
        block(BorealisBlocks.SACCHARINE_PLANKS);
        log(BorealisBlocks.SACCHARINE_LOG, "saccharine_log");
        wood(BorealisBlocks.SACCHARINE_WOOD, BorealisBlocks.SACCHARINE_LOG);
        log(BorealisBlocks.STRIPPED_SACCHARINE_LOG, "stripped_saccharine_log");
        wood(BorealisBlocks.STRIPPED_SACCHARINE_WOOD, BorealisBlocks.STRIPPED_SACCHARINE_LOG);
        block(BorealisBlocks.SACCHARINE_LEAVES);
        stairs(BorealisBlocks.SACCHARINE_STAIRS, BorealisBlocks.SACCHARINE_PLANKS);
        slab(BorealisBlocks.SACCHARINE_SLAB, BorealisBlocks.SACCHARINE_PLANKS);
        fenceBlock(BorealisBlocks.SACCHARINE_FENCE.get(), texture("saccharine_planks"));
        fenceGateBlock(BorealisBlocks.SACCHARINE_FENCE_GATE.get(), texture("saccharine_planks"));
        door(BorealisBlocks.SACCHARINE_DOOR, "saccharine");
        trapdoor(BorealisBlocks.SACCHARINE_TRAPDOOR, "saccharine");
        crossBlock(BorealisBlocks.SACCHARINE_SAPLING);
        buttonBlock(BorealisBlocks.SACCHARINE_BUTTON.get(), texture("saccharine_planks"));
        pressurePlateBlock(BorealisBlocks.SACCHARINE_PRESSURE_PLATE.get(), texture("saccharine_planks"));
        signBlock(BorealisBlocks.SACCHARINE_SIGN.get(), BorealisBlocks.SACCHARINE_WALL_SIGN.get(), texture("saccharine_planks"));
    }
}
