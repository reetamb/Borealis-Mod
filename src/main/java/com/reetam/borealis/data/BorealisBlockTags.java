package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class BorealisBlockTags extends BlockTagsProvider {


    public BorealisBlockTags(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, BorealisMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Borealis Block Tags";
    }

    @Override
    protected void addTags() {
        //mod
        tag(BorealisTags.Blocks.BASE_STONE_BOREALIS)
                .add(BorealisBlocks.soapstone.get())
                .add(BorealisBlocks.slate.get());
        tag(BorealisTags.Blocks.BRUMAL_LOGS)
                .add(BorealisBlocks.brumal_log.get())
                .add(BorealisBlocks.brumal_wood.get())
                .add(BorealisBlocks.stripped_brumal_log.get())
                .add(BorealisBlocks.stripped_brumal_wood.get());
        tag(BorealisTags.Blocks.FROSTFIR_LOGS)
                .add(BorealisBlocks.frostfir_log.get())
                .add(BorealisBlocks.frostfir_wood.get())
                .add(BorealisBlocks.stripped_frostfir_log.get())
                .add(BorealisBlocks.stripped_frostfir_wood.get());
        tag(BorealisTags.Blocks.SACCHARINE_LOGS)
                .add(BorealisBlocks.saccharine_log.get())
                .add(BorealisBlocks.saccharine_wood.get())
                .add(BorealisBlocks.stripped_saccharine_log.get())
                .add(BorealisBlocks.stripped_saccharine_wood.get());
        tag(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)
                .add(Blocks.ICE)
                .add(Blocks.PACKED_ICE)
                .add(Blocks.BLUE_ICE)
                .add(Blocks.SNOW_BLOCK);
        tag(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS)
                .add(Blocks.SNOW)
                .add(BorealisBlocks.sugar_snow.get());
        tag(BorealisTags.Blocks.SNOWY_BLOCKS)
                .add(Blocks.SNOW)
                .add(Blocks.SNOW_BLOCK)
                .add(BorealisBlocks.living_snow_block.get());
        tag(BorealisTags.Blocks.SUGARY_BLOCKS)
                .add(BorealisBlocks.sugar_snow.get())
                .add(BorealisBlocks.sugar_snow_block.get());
        //vanilla
        tag(BlockTags.SAPLINGS)
                .add(BorealisBlocks.brumal_sapling.get())
                .add(BorealisBlocks.frostfir_sapling.get())
                .add(BorealisBlocks.saccharine_sapling.get());
        tag(BlockTags.PLANKS)
                .add(BorealisBlocks.brumal_planks.get())
                .add(BorealisBlocks.frostfir_planks.get())
                .add(BorealisBlocks.saccharine_planks.get());
        tag(BlockTags.WOODEN_BUTTONS)
                .add(BorealisBlocks.brumal_button.get())
                .add(BorealisBlocks.frostfir_button.get())
                .add(BorealisBlocks.saccharine_button.get());
        tag(BlockTags.WOODEN_DOORS)
                .add(BorealisBlocks.brumal_door.get())
                .add(BorealisBlocks.frostfir_door.get())
                .add(BorealisBlocks.saccharine_door.get());
        tag(BlockTags.WOODEN_STAIRS)
                .add(BorealisBlocks.brumal_stairs.get())
                .add(BorealisBlocks.frostfir_stairs.get())
                .add(BorealisBlocks.saccharine_stairs.get());
        tag(BlockTags.WOODEN_SLABS)
                .add(BorealisBlocks.brumal_slab.get())
                .add(BorealisBlocks.frostfir_slab.get())
                .add(BorealisBlocks.saccharine_slab.get());
        tag(BlockTags.WOODEN_FENCES)
                .add(BorealisBlocks.brumal_fence.get())
                .add(BorealisBlocks.frostfir_fence.get())
                .add(BorealisBlocks.saccharine_fence.get());
        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(BorealisTags.Blocks.BRUMAL_LOGS)
                .addTag(BorealisTags.Blocks.FROSTFIR_LOGS)
                .addTag(BorealisTags.Blocks.SACCHARINE_LOGS);
        tag(BlockTags.LOGS)
                .addTag(BorealisTags.Blocks.BRUMAL_LOGS)
                .addTag(BorealisTags.Blocks.FROSTFIR_LOGS)
                .addTag(BorealisTags.Blocks.SACCHARINE_LOGS);
        tag(BlockTags.ENDERMAN_HOLDABLE)
                .add(BorealisBlocks.living_snow_block.get())
                .add(BorealisBlocks.sugar_snow_block.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(BorealisBlocks.brumal_pressure_plate.get())
                .add(BorealisBlocks.frostfir_pressure_plate.get())
                .add(BorealisBlocks.saccharine_pressure_plate.get());
        tag(BlockTags.STONE_PRESSURE_PLATES)
                .add(BorealisBlocks.soapstone_pressure_plate.get());
        tag(BlockTags.WALLS)
                .add(BorealisBlocks.soapstone_wall.get())
                .add(BorealisBlocks.soapstone_tile_wall.get())
                .add(BorealisBlocks.soapstone_brick_wall.get());
        tag(BlockTags.LEAVES)
                .add(BorealisBlocks.brumal_leaves.get())
                .add(BorealisBlocks.frostfir_leaves.get())
                .add(BorealisBlocks.saccharine_leaves.get());
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(BorealisBlocks.brumal_trapdoor.get())
                .add(BorealisBlocks.frostfir_trapdoor.get())
                .add(BorealisBlocks.saccharine_trapdoor.get());
        tag(BlockTags.FENCE_GATES)
                .add(BorealisBlocks.brumal_fence_gate.get())
                .add(BorealisBlocks.frostfir_fence_gate.get())
                .add(BorealisBlocks.saccharine_fence_gate.get());
        //forge
        tag(Tags.Blocks.COBBLESTONE)
                .add(BorealisBlocks.soapstone.get())
                .add(BorealisBlocks.slate.get());
        tag(Tags.Blocks.DIRT)
                .add(BorealisBlocks.living_snow_block.get())
                .add(BorealisBlocks.sugar_snow_block.get())
                .add(BorealisBlocks.permafrost.get())
                .add(BorealisBlocks.permafrost_rubble.get());
        tag(Tags.Blocks.FENCE_GATES_WOODEN)
                .add(BorealisBlocks.brumal_fence_gate.get())
                .add(BorealisBlocks.frostfir_fence_gate.get())
                .add(BorealisBlocks.saccharine_fence_gate.get());
        tag(Tags.Blocks.FENCES_WOODEN)
                .add(BorealisBlocks.brumal_fence.get())
                .add(BorealisBlocks.frostfir_fence.get())
                .add(BorealisBlocks.saccharine_fence.get());
        tag(Tags.Blocks.STONE)
                .add(BorealisBlocks.soapstone.get())
                .add(BorealisBlocks.slate.get());
    }

    protected TagsProvider.Builder<Block> tag(ITag.INamedTag<Block> tag) {
        return super.tag(tag);
    }
}
