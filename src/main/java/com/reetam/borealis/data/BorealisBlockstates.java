package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import com.reetam.borealis.data.provider.BorealisBlockstateProvider;

public class BorealisBlockstates extends BorealisBlockstateProvider {

    public BorealisBlockstates(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        /*STONE*/
        block(BorealisBlocks.soapstone);
        block(BorealisBlocks.soapstone_stairs);
        block(BorealisBlocks.soapstone_slab);
        block(BorealisBlocks.soapstone_wall);

        block(BorealisBlocks.polished_soapstone);
        block(BorealisBlocks.polished_soapstone_stairs);
        block(BorealisBlocks.polished_soapstone_slab);
        block(BorealisBlocks.polished_soapstone_wall);
        block(BorealisBlocks.soapstone_pressure_plate);
        block(BorealisBlocks.soapstone_button);

        block(BorealisBlocks.soapstone_bricks);
        block(BorealisBlocks.soapstone_brick_stairs);
        block(BorealisBlocks.soapstone_brick_wall);
        block(BorealisBlocks.soapstone_brick_wall);

        block(BorealisBlocks.slate);
        block(BorealisBlocks.slate_pillar);
        block(BorealisBlocks.chiseled_slate_pillar);

        /*WOOD*/
        block(BorealisBlocks.brumal_planks);
        block(BorealisBlocks.brumal_log);
        block(BorealisBlocks.brumal_wood);
        block(BorealisBlocks.stripped_brumal_log);
        block(BorealisBlocks.stripped_brumal_wood);
        fence(BorealisBlocks.brumal_fence, "brumal_planks");
        fenceGateBlock(BorealisBlocks.brumal_fence_gate.get(), new ResourceLocation(BorealisMod.MODID, "brumal_fence_gate"));
        block(BorealisBlocks.brumal_leaves);

        block(BorealisBlocks.frostfir_planks);
        block(BorealisBlocks.frostfir_log);
        block(BorealisBlocks.frostfir_wood);
        block(BorealisBlocks.stripped_frostfir_log);
        block(BorealisBlocks.stripped_frostfir_wood);
        fence(BorealisBlocks.frostfir_fence, "frostfir_planks");
        fenceGateBlock(BorealisBlocks.frostfir_fence_gate.get(), new ResourceLocation(BorealisMod.MODID, "frostfir_fence_gate"));
        block(BorealisBlocks.frostfir_leaves);

        /*TERRAIN*/
        block(BorealisBlocks.lichen_block);
        block(BorealisBlocks.permafrost);
        block(BorealisBlocks.kyanite_ore);
    }
}
