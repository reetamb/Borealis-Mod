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
        block(BorealisBlocks.travertine);
        block(BorealisBlocks.LIVING_SNOW_BLOCK);
        block(BorealisBlocks.SUGAR_SNOW_BLOCK);
        block(BorealisBlocks.PERMAFROST_RUBBLE);
        block(BorealisBlocks.CLOUD);
        wood();
    }

    private void wood() {
        block(BorealisBlocks.brumal_planks);
        log(BorealisBlocks.brumal_log, "brumal_log");
        wood(BorealisBlocks.brumal_wood, BorealisBlocks.brumal_log);
        log(BorealisBlocks.stripped_brumal_log, "stripped_brumal_log");
        wood(BorealisBlocks.stripped_brumal_wood, BorealisBlocks.stripped_brumal_log);
        block(BorealisBlocks.brumal_leaves);
        stairs(BorealisBlocks.brumal_stairs, BorealisBlocks.brumal_planks);
        slab(BorealisBlocks.brumal_slab, BorealisBlocks.brumal_planks);
        fenceBlock(BorealisBlocks.brumal_fence.get(), modLoc("block/brumal_planks"));
        fenceGateBlock(BorealisBlocks.brumal_fence_gate.get(), modLoc("block/brumal_planks"));
        door(BorealisBlocks.brumal_door, "brumal");
        trapdoor(BorealisBlocks.brumal_trapdoor, "brumal");
        crossBlock(BorealisBlocks.brumal_sapling);
        block(BorealisBlocks.frostfir_planks);
        log(BorealisBlocks.frostfir_log, "frostfir_log");
        wood(BorealisBlocks.frostfir_wood, BorealisBlocks.frostfir_log);
        log(BorealisBlocks.stripped_frostfir_log, "stripped_frostfir_log");
        wood(BorealisBlocks.stripped_frostfir_wood, BorealisBlocks.stripped_frostfir_log);
        block(BorealisBlocks.frostfir_leaves);
        stairs(BorealisBlocks.frostfir_stairs, BorealisBlocks.frostfir_planks);
        slab(BorealisBlocks.frostfir_slab, BorealisBlocks.frostfir_planks);
        fenceBlock(BorealisBlocks.frostfir_fence.get(), modLoc("block/frostfir_planks"));
        fenceGateBlock(BorealisBlocks.frostfir_fence_gate.get(), modLoc("block/frostfir_planks"));
        door(BorealisBlocks.frostfir_door, "frostfir");
        trapdoor(BorealisBlocks.frostfir_trapdoor, "frostfir");
        crossBlock(BorealisBlocks.frostfir_sapling);
        block(BorealisBlocks.saccharine_planks);
        log(BorealisBlocks.saccharine_log, "saccharine_log");
        wood(BorealisBlocks.saccharine_wood, BorealisBlocks.saccharine_log);
        log(BorealisBlocks.stripped_saccharine_log, "stripped_saccharine_log");
        wood(BorealisBlocks.stripped_saccharine_wood, BorealisBlocks.stripped_saccharine_log);
        block(BorealisBlocks.saccharine_leaves);
        stairs(BorealisBlocks.saccharine_stairs, BorealisBlocks.saccharine_planks);
        slab(BorealisBlocks.saccharine_slab, BorealisBlocks.saccharine_planks);
        fenceBlock(BorealisBlocks.saccharine_fence.get(), modLoc("block/saccharine_planks"));
        fenceGateBlock(BorealisBlocks.saccharine_fence_gate.get(), modLoc("block/saccharine_planks"));
        door(BorealisBlocks.saccharine_door, "saccharine");
        trapdoor(BorealisBlocks.saccharine_trapdoor, "saccharine");
        crossBlock(BorealisBlocks.saccharine_sapling);
    }
}
