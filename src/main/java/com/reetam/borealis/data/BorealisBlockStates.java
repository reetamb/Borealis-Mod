package com.reetam.borealis.data;

import com.reetam.borealis.data.provider.BorealisBlockStateProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.block.Blocks;
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
        block(BorealisBlocks.soapstone);
        block(BorealisBlocks.soapstone_tiles);
        block(BorealisBlocks.soapstone_bricks);
        block(BorealisBlocks.kyanite_ore);
        block(BorealisBlocks.tanzanite_ore);
        stairs(BorealisBlocks.soapstone_stairs, BorealisBlocks.soapstone);
        stairs(BorealisBlocks.soapstone_tile_stairs, BorealisBlocks.soapstone_tiles);
        stairs(BorealisBlocks.soapstone_brick_stairs, BorealisBlocks.soapstone_bricks);
        slab(BorealisBlocks.soapstone_slab, BorealisBlocks.soapstone);
        slab(BorealisBlocks.soapstone_tile_slab, BorealisBlocks.soapstone_tiles);
        slab(BorealisBlocks.soapstone_brick_slab, BorealisBlocks.soapstone_bricks);
        wallBlock(BorealisBlocks.soapstone_wall.get(), modLoc("block/soapstone"));
        wallBlock(BorealisBlocks.soapstone_tile_wall.get(), modLoc("block/soapstone_tiles"));
        wallBlock(BorealisBlocks.soapstone_brick_wall.get(), modLoc("block/soapstone_bricks"));
        //block(BorealisBlocks.soapstone_button);
        //block(BorealisBlocks.soapstone_pressure_plate);
        block(BorealisBlocks.slate);
        log(BorealisBlocks.slate_pillar, "slate_pillar");
        block(BorealisBlocks.slate_tiles);
        block(BorealisBlocks.pumice);
        block(BorealisBlocks.travertine);
        block(BorealisBlocks.living_snow_block);
        block(BorealisBlocks.sugar_snow_block);
        block(BorealisBlocks.permafrost_rubble);
        block(BorealisBlocks.cloud);
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
//        block(BorealisBlocks.brumal_button);
//        block(BorealisBlocks.brumal_pressure_plate);
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
//        block(BorealisBlocks.frostfir_button);
//        block(BorealisBlocks.frostfir_pressure_plate);
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
//        block(BorealisBlocks.saccharine_button);
//        block(BorealisBlocks.saccharine_pressure_plate);
    }
}
