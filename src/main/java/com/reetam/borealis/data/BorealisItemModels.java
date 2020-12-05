package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.data.provider.BorealisItemModelProvider;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BorealisItemModels extends BorealisItemModelProvider {

    public BorealisItemModels(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    protected void registerModels() {
        /*ITEMS*/
        normalItem(BorealisItems.kyanite_crystal);


        /*STONE*/
        itemBlock(BorealisBlocks.soapstone);
        itemBlock(BorealisBlocks.soapstone_stairs);
        itemBlock(BorealisBlocks.soapstone_slab);
        wallInventory("soapstone_wall", new ResourceLocation(BorealisMod.MODID, "soapstone"));

        itemBlock(BorealisBlocks.polished_soapstone);
        itemBlock(BorealisBlocks.polished_soapstone_stairs);
        itemBlock(BorealisBlocks.polished_soapstone_slab);
        wallInventory("polished_soapstone_wall", new ResourceLocation(BorealisMod.MODID, "polished_soapstone"));
        itemBlock(BorealisBlocks.soapstone_pressure_plate);
        itemBlock(BorealisBlocks.soapstone_button);

        itemBlock(BorealisBlocks.soapstone_bricks);
        itemBlock(BorealisBlocks.soapstone_brick_stairs);
        itemBlock(BorealisBlocks.soapstone_brick_slab);
        wallInventory("soapstone_brick_wall", new ResourceLocation(BorealisMod.MODID, "soapstone_bricks"));

        itemBlock(BorealisBlocks.slate);
        itemBlock(BorealisBlocks.slate_pillar);
        itemBlock(BorealisBlocks.chiseled_slate_pillar);

        /*WOOD*/
        itemBlock(BorealisBlocks.brumal_planks);
        itemBlock(BorealisBlocks.brumal_log);
        itemBlock(BorealisBlocks.brumal_wood);
        itemBlock(BorealisBlocks.stripped_brumal_log);
        itemBlock(BorealisBlocks.stripped_brumal_wood);
        itemFence(BorealisBlocks.brumal_fence, "brumal_planks");
        itemFenceGate(BorealisBlocks.brumal_fence_gate, "brumal_planks");
        itemBlock(BorealisBlocks.brumal_leaves);

        itemBlock(BorealisBlocks.frostfir_planks);
        itemBlock(BorealisBlocks.frostfir_log);
        itemBlock(BorealisBlocks.frostfir_wood);
        itemBlock(BorealisBlocks.stripped_frostfir_log);
        itemBlock(BorealisBlocks.stripped_frostfir_wood);
        itemFence(BorealisBlocks.frostfir_fence, "frostfir_planks");
        itemFenceGate(BorealisBlocks.frostfir_fence_gate, "frostfir_planks");
        itemBlock(BorealisBlocks.frostfir_leaves);

        /*TERRAIN*/
        itemBlock(BorealisBlocks.lichen_block);
        itemBlock(BorealisBlocks.permafrost);
        itemBlock(BorealisBlocks.kyanite_ore);
    }
}
