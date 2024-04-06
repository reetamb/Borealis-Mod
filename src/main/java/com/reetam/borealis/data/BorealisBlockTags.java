package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BorealisBlockTags extends BlockTagsProvider {

    public BorealisBlockTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, registries, BorealisMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Borealis Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //mod
        tag(BorealisTags.Blocks.BASE_STONE_BOREALIS)
                .add(BorealisBlocks.SOAPSTONE.get())
                .add(BorealisBlocks.SLATE.get());
        tag(BorealisTags.Blocks.BRUMAL_LOGS)
                .add(BorealisBlocks.BRUMAL_LOG.get())
                .add(BorealisBlocks.BRUMAL_WOOD.get())
                .add(BorealisBlocks.STRIPPED_BRUMAL_LOG.get())
                .add(BorealisBlocks.STRIPPED_BRUMAL_WOOD.get());
        tag(BorealisTags.Blocks.FROSTFIR_LOGS)
                .add(BorealisBlocks.FROSTFIR_LOG.get())
                .add(BorealisBlocks.FROSTFIR_WOOD.get())
                .add(BorealisBlocks.STRIPPED_FROSTFIR_LOG.get())
                .add(BorealisBlocks.STRIPPED_FROSTFIR_WOOD.get());
        tag(BorealisTags.Blocks.SACCHARINE_LOGS)
                .add(BorealisBlocks.SACCHARINE_LOG.get())
                .add(BorealisBlocks.SACCHARINE_WOOD.get())
                .add(BorealisBlocks.STRIPPED_SACCHARINE_LOG.get())
                .add(BorealisBlocks.STRIPPED_SACCHARINE_WOOD.get());
        tag(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)
                .add(Blocks.ICE)
                .add(Blocks.PACKED_ICE)
                .add(Blocks.BLUE_ICE)
                .add(Blocks.SNOW_BLOCK);
        tag(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS)
                .add(Blocks.SNOW)
                .add(BorealisBlocks.SUGAR_SNOW.get());
        tag(BorealisTags.Blocks.SNOWY_BLOCKS)
                .add(Blocks.SNOW)
                .add(Blocks.SNOW_BLOCK)
                .add(BorealisBlocks.LIVING_SNOW_BLOCK.get());
        tag(BorealisTags.Blocks.SUGARY_BLOCKS)
                .add(BorealisBlocks.SUGAR_SNOW.get())
                .add(BorealisBlocks.SUGAR_SNOW_BLOCK.get());
        tag(BorealisTags.Blocks.ICY_BLOCKS)
                .add(Blocks.ICE)
                .add(Blocks.PACKED_ICE)
                .add(Blocks.BLUE_ICE);
        tag(BorealisTags.Blocks.SOAPSTONE_ORE_REPLACEABLES)
                .add(BorealisBlocks.SOAPSTONE.get());
        //vanilla
        tag(BlockTags.SAPLINGS)
                .add(BorealisBlocks.BRUMAL_SAPLING.get())
                .add(BorealisBlocks.FROSTFIR_SAPLING.get())
                .add(BorealisBlocks.SACCHARINE_SAPLING.get());
        tag(BlockTags.PLANKS)
                .add(BorealisBlocks.BRUMAL_PLANKS.get())
                .add(BorealisBlocks.FROSTFIR_PLANKS.get())
                .add(BorealisBlocks.SACCHARINE_PLANKS.get());
        tag(BlockTags.WOODEN_BUTTONS)
                .add(BorealisBlocks.BRUMAL_BUTTON.get())
                .add(BorealisBlocks.FROSTFIR_BUTTON.get())
                .add(BorealisBlocks.SACCHARINE_BUTTON.get());
        tag(BlockTags.WOODEN_DOORS)
                .add(BorealisBlocks.BRUMAL_DOOR.get())
                .add(BorealisBlocks.FROSTFIR_DOOR.get())
                .add(BorealisBlocks.SACCHARINE_DOOR.get());
        tag(BlockTags.WOODEN_STAIRS)
                .add(BorealisBlocks.BRUMAL_STAIRS.get())
                .add(BorealisBlocks.FROSTFIR_STAIRS.get())
                .add(BorealisBlocks.SACCHARINE_STAIRS.get());
        tag(BlockTags.WOODEN_SLABS)
                .add(BorealisBlocks.BRUMAL_SLAB.get())
                .add(BorealisBlocks.FROSTFIR_SLAB.get())
                .add(BorealisBlocks.SACCHARINE_SLAB.get());
        tag(BlockTags.WOODEN_FENCES)
                .add(BorealisBlocks.BRUMAL_FENCE.get())
                .add(BorealisBlocks.FROSTFIR_FENCE.get())
                .add(BorealisBlocks.SACCHARINE_FENCE.get());
        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(BorealisTags.Blocks.BRUMAL_LOGS)
                .addTag(BorealisTags.Blocks.FROSTFIR_LOGS)
                .addTag(BorealisTags.Blocks.SACCHARINE_LOGS);
        tag(BlockTags.LOGS)
                .addTag(BorealisTags.Blocks.BRUMAL_LOGS)
                .addTag(BorealisTags.Blocks.FROSTFIR_LOGS)
                .addTag(BorealisTags.Blocks.SACCHARINE_LOGS);
        tag(BlockTags.ENDERMAN_HOLDABLE)
                .add(BorealisBlocks.LIVING_SNOW_BLOCK.get())
                .add(BorealisBlocks.SUGAR_SNOW_BLOCK.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(BorealisBlocks.BRUMAL_PRESSURE_PLATE.get())
                .add(BorealisBlocks.FROSTFIR_PRESSURE_PLATE.get())
                .add(BorealisBlocks.SACCHARINE_PRESSURE_PLATE.get());
        tag(BlockTags.STONE_PRESSURE_PLATES)
                .add(BorealisBlocks.SOAPSTONE_PRESSURE_PLATE.get());
        tag(BlockTags.WALLS)
                .add(BorealisBlocks.SOAPSTONE_WALL.get())
                .add(BorealisBlocks.SOAPSTONE_TILE_WALL.get())
                .add(BorealisBlocks.SOAPSTONE_BRICK_WALL.get());
        tag(BlockTags.LEAVES)
                .add(BorealisBlocks.BRUMAL_LEAVES.get())
                .add(BorealisBlocks.FROSTFIR_LEAVES.get())
                .add(BorealisBlocks.SACCHARINE_LEAVES.get());
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(BorealisBlocks.BRUMAL_TRAPDOOR.get())
                .add(BorealisBlocks.FROSTFIR_TRAPDOOR.get())
                .add(BorealisBlocks.SACCHARINE_TRAPDOOR.get());
        tag(BlockTags.FENCE_GATES)
                .add(BorealisBlocks.BRUMAL_FENCE_GATE.get())
                .add(BorealisBlocks.FROSTFIR_FENCE_GATE.get())
                .add(BorealisBlocks.SACCHARINE_FENCE_GATE.get());
        tag(BlockTags.STANDING_SIGNS)
                .add(BorealisBlocks.BRUMAL_SIGN.get())
                .add(BorealisBlocks.FROSTFIR_SIGN.get())
                .add(BorealisBlocks.SACCHARINE_SIGN.get());
        tag(BlockTags.WALL_SIGNS)
                .add(BorealisBlocks.BRUMAL_WALL_SIGN.get())
                .add(BorealisBlocks.FROSTFIR_WALL_SIGN.get())
                .add(BorealisBlocks.SACCHARINE_WALL_SIGN.get());
        tag(BlockTags.SIGNS)
                .add(BorealisBlocks.BRUMAL_SIGN.get())
                .add(BorealisBlocks.FROSTFIR_SIGN.get())
                .add(BorealisBlocks.SACCHARINE_SIGN.get())
                .add(BorealisBlocks.BRUMAL_WALL_SIGN.get())
                .add(BorealisBlocks.FROSTFIR_WALL_SIGN.get())
                .add(BorealisBlocks.SACCHARINE_WALL_SIGN.get());
        tag(BlockTags.DIRT)
                .add(BorealisBlocks.LIVING_SNOW_BLOCK.get())
                .add(BorealisBlocks.SUGAR_SNOW_BLOCK.get())
                .add(BorealisBlocks.PERMAFROST.get())
                .add(BorealisBlocks.PERMAFROST_RUBBLE.get());
        //forge
        tag(Tags.Blocks.COBBLESTONE)
                .add(BorealisBlocks.SOAPSTONE.get())
                .add(BorealisBlocks.SLATE.get());
        tag(Tags.Blocks.FENCE_GATES_WOODEN)
                .add(BorealisBlocks.BRUMAL_FENCE_GATE.get())
                .add(BorealisBlocks.FROSTFIR_FENCE_GATE.get())
                .add(BorealisBlocks.SACCHARINE_FENCE_GATE.get());
        tag(Tags.Blocks.FENCES_WOODEN)
                .add(BorealisBlocks.BRUMAL_FENCE.get())
                .add(BorealisBlocks.FROSTFIR_FENCE.get())
                .add(BorealisBlocks.SACCHARINE_FENCE.get());
        tag(Tags.Blocks.STONE)
                .add(BorealisBlocks.SOAPSTONE.get())
                .add(BorealisBlocks.SLATE.get());
    }
}
