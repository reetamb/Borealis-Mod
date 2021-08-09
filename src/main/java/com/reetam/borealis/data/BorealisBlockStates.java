package com.reetam.borealis.data;

import com.reetam.borealis.data.provider.BorealisBlockStateProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.data.DataGenerator;
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
        block(BorealisBlocks.KYANITE_ORE);
        block(BorealisBlocks.TANZANITE_ORE);
        stairs(BorealisBlocks.SOAPSTONE_STAIRS, BorealisBlocks.SOAPSTONE);
        stairs(BorealisBlocks.SOAPSTONE_TILE_STAIRS, BorealisBlocks.SOAPSTONE_TILES);
        stairs(BorealisBlocks.SOAPSTONE_BRICK_STAIRS, BorealisBlocks.SOAPSTONE_BRICKS);
        slab(BorealisBlocks.SOAPSTONE_SLAB, BorealisBlocks.SOAPSTONE);
        slab(BorealisBlocks.SOAPSTONE_TILE_SLAB, BorealisBlocks.SOAPSTONE_TILES);
        slab(BorealisBlocks.SOAPSTONE_BRICK_SLAB, BorealisBlocks.SOAPSTONE_BRICKS);
        wallBlock(BorealisBlocks.SOAPSTONE_WALL.get(), modLoc("block/soapstone"));
        wallBlock(BorealisBlocks.SOAPSTONE_TILE_WALL.get(), modLoc("block/soapstone_tiles"));
        wallBlock(BorealisBlocks.SOAPSTONE_BRICK_WALL.get(), modLoc("block/soapstone_bricks"));
        block(BorealisBlocks.SLATE);
        log(BorealisBlocks.SLATE_PILLAR, "slate_pillar");
        block(BorealisBlocks.SLATE_TILES);
        block(BorealisBlocks.PUMICE);
        block(BorealisBlocks.TRAVERTINE);
        block(BorealisBlocks.LIVING_SNOW_BLOCK);
        block(BorealisBlocks.SUGAR_SNOW_BLOCK);
        block(BorealisBlocks.PERMAFROST_RUBBLE);
        block(BorealisBlocks.CLOUD);
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
        fenceBlock(BorealisBlocks.BRUMAL_FENCE.get(), modLoc("block/brumal_planks"));
        fenceGateBlock(BorealisBlocks.BRUMAL_FENCE_GATE.get(), modLoc("block/brumal_planks"));
        door(BorealisBlocks.BRUMAL_DOOR, "brumal");
        trapdoor(BorealisBlocks.BRUMAL_TRAPDOOR, "brumal");
        crossBlock(BorealisBlocks.BRUMAL_SAPLING);
        block(BorealisBlocks.FROSTFIR_PLANKS);
        log(BorealisBlocks.FROSTFIR_LOG, "frostfir_log");
        wood(BorealisBlocks.FROSTFIR_WOOD, BorealisBlocks.FROSTFIR_LOG);
        log(BorealisBlocks.STRIPPED_FROSTFIR_LOG, "stripped_frostfir_log");
        wood(BorealisBlocks.STRIPPED_FROSTFIR_WOOD, BorealisBlocks.STRIPPED_FROSTFIR_LOG);
        block(BorealisBlocks.FROSTFIR_LEAVES);
        stairs(BorealisBlocks.FROSTFIR_STAIRS, BorealisBlocks.FROSTFIR_PLANKS);
        slab(BorealisBlocks.FROSTFIR_SLAB, BorealisBlocks.FROSTFIR_PLANKS);
        fenceBlock(BorealisBlocks.FROSTFIR_FENCE.get(), modLoc("block/frostfir_planks"));
        fenceGateBlock(BorealisBlocks.FROSTFIR_FENCE_GATE.get(), modLoc("block/frostfir_planks"));
        door(BorealisBlocks.FROSTFIR_DOOR, "frostfir");
        trapdoor(BorealisBlocks.FROSTFIR_TRAPDOOR, "frostfir");
        crossBlock(BorealisBlocks.FROSTFIR_SAPLING);
        block(BorealisBlocks.SACCHARINE_PLANKS);
        log(BorealisBlocks.SACCHARINE_LOG, "saccharine_log");
        wood(BorealisBlocks.saccharine_wood, BorealisBlocks.SACCHARINE_LOG);
        log(BorealisBlocks.STRIPPED_SACCHARINE_LOG, "stripped_saccharine_log");
        wood(BorealisBlocks.STRIPPED_SACCHARINE_WOOD, BorealisBlocks.STRIPPED_SACCHARINE_LOG);
        block(BorealisBlocks.SACCHARINE_LEAVES);
        stairs(BorealisBlocks.SACCHARINE_STAIRS, BorealisBlocks.SACCHARINE_PLANKS);
        slab(BorealisBlocks.SACCHARINE_SLAB, BorealisBlocks.SACCHARINE_PLANKS);
        fenceBlock(BorealisBlocks.SACCHARINE_FENCE.get(), modLoc("block/saccharine_planks"));
        fenceGateBlock(BorealisBlocks.SACCHARINE_FENCE_GATE.get(), modLoc("block/saccharine_planks"));
        door(BorealisBlocks.SACCHARINE_DOOR, "saccharine");
        trapdoor(BorealisBlocks.SACCHARINE_TRAPDOOR, "saccharine");
        crossBlock(BorealisBlocks.SACCHARINE_SAPLING);
    }
}
