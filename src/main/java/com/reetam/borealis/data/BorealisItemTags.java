package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BorealisItemTags extends ItemTagsProvider {

    public BorealisItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blocks, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, registries, blocks, BorealisMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Borealis Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //mod
        copy(BorealisTags.Blocks.BRUMAL_LOGS, BorealisTags.Items.BRUMAL_LOGS);
        copy(BorealisTags.Blocks.CARAMELIZED_LOGS, BorealisTags.Items.CARAMELIZED_LOGS);
        copy(BorealisTags.Blocks.SWEETWOOD_LOGS, BorealisTags.Items.SWEETWOOD_LOGS);

        tag(BorealisTags.Items.PREHISTORIC_PLANT)
                .add(BorealisItems.WINTER_FIDDLE.get())
                .add(BorealisBlocks.WINTER_CELLO.get().asItem())
                .add(BorealisBlocks.WINTER_VIOLA.get().asItem())
                .add(BorealisBlocks.BRUMELIAD.get().asItem());

        //vanilla
        tag(ItemTags.PLANKS)
                .add(BorealisBlocks.BRUMAL_PLANKS.get().asItem())
                .add(BorealisBlocks.CARAMELIZED_PLANKS.get().asItem())
                .add(BorealisBlocks.SWEETWOOD_PLANKS.get().asItem());
        tag(ItemTags.WOODEN_BUTTONS)
                .add(BorealisBlocks.BRUMAL_BUTTON.get().asItem())
                .add(BorealisBlocks.CARAMELIZED_BUTTON.get().asItem())
                .add(BorealisBlocks.SWEETWOOD_BUTTON.get().asItem());
        tag(ItemTags.BUTTONS)
                .add(BorealisBlocks.SOAPSTONE_BUTTON.get().asItem());
        tag(ItemTags.WOODEN_DOORS)
                .add(BorealisBlocks.BRUMAL_DOOR.get().asItem())
                .add(BorealisBlocks.CARAMELIZED_DOOR.get().asItem())
                .add(BorealisBlocks.SWEETWOOD_DOOR.get().asItem());
        tag(ItemTags.WOODEN_STAIRS)
                .add(BorealisBlocks.BRUMAL_STAIRS.get().asItem())
                .add(BorealisBlocks.CARAMELIZED_STAIRS.get().asItem())
                .add(BorealisBlocks.SWEETWOOD_STAIRS.get().asItem());
        tag(ItemTags.WOODEN_SLABS)
                .add(BorealisBlocks.BRUMAL_SLAB.get().asItem())
                .add(BorealisBlocks.CARAMELIZED_SLAB.get().asItem())
                .add(BorealisBlocks.SWEETWOOD_SLAB.get().asItem());
        tag(ItemTags.WOODEN_FENCES)
                .add(BorealisBlocks.BRUMAL_FENCE.get().asItem())
                .add(BorealisBlocks.CARAMELIZED_FENCE.get().asItem())
                .add(BorealisBlocks.SWEETWOOD_FENCE.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(BorealisTags.Items.BRUMAL_LOGS)
                .addTag(BorealisTags.Items.CARAMELIZED_LOGS)
                .addTag(BorealisTags.Items.SWEETWOOD_LOGS)
                .add(BorealisBlocks.PETRIFIED_WOOD.get().asItem());
        tag(ItemTags.LOGS)
                .addTag(BorealisTags.Items.BRUMAL_LOGS)
                .addTag(BorealisTags.Items.CARAMELIZED_LOGS)
                .addTag(BorealisTags.Items.SWEETWOOD_LOGS)
                .add(BorealisBlocks.PETRIFIED_WOOD.get().asItem());
        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(BorealisBlocks.BRUMAL_PRESSURE_PLATE.get().asItem())
                .add(BorealisBlocks.CARAMELIZED_PRESSURE_PLATE.get().asItem())
                .add(BorealisBlocks.SWEETWOOD_PRESSURE_PLATE.get().asItem());
        tag(ItemTags.WALLS)
                .add(BorealisBlocks.SOAPSTONE_WALL.get().asItem())
                .add(BorealisBlocks.SOAPSTONE_BRICK_WALL.get().asItem());
        tag(ItemTags.LEAVES)
                .add(BorealisBlocks.BRUMAL_LEAVES.get().asItem())
                .add(BorealisBlocks.FROSTFIR_LEAVES.get().asItem())
                .add(BorealisBlocks.GLAZED_LEAVES.get().asItem())
                .add(BorealisBlocks.SWEETWOOD_LEAVES.get().asItem());
        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(BorealisBlocks.BRUMAL_TRAPDOOR.get().asItem())
                .add(BorealisBlocks.CARAMELIZED_TRAPDOOR.get().asItem())
                .add(BorealisBlocks.SWEETWOOD_TRAPDOOR.get().asItem());
        tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(BorealisBlocks.SOAPSTONE.get().asItem())
                .add(BorealisBlocks.SLATE.get().asItem());
        tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(BorealisBlocks.SOAPSTONE.get().asItem())
                .add(BorealisBlocks.SLATE.get().asItem());
        tag(ItemTags.BOATS)
                .add(BorealisItems.BRUMAL_BOAT.get())
                .add(BorealisItems.CARAMELIZED_BOAT.get())
                .add(BorealisItems.SWEETWOOD_BOAT.get());
        tag(ItemTags.SIGNS)
                .add(BorealisItems.BRUMAL_SIGN.get())
                .add(BorealisItems.CARAMELIZED_SIGN.get())
                .add(BorealisItems.SWEETWOOD_SIGN.get());

        //forge
        tag(Tags.Items.COBBLESTONES)
                .add(BorealisBlocks.SOAPSTONE.get().asItem())
                .add(BorealisBlocks.SLATE.get().asItem());
        tag(Tags.Items.FENCE_GATES_WOODEN)
                .add(BorealisBlocks.BRUMAL_FENCE_GATE.get().asItem())
                .add(BorealisBlocks.CARAMELIZED_FENCE_GATE.get().asItem())
                .add(BorealisBlocks.SWEETWOOD_FENCE_GATE.get().asItem());
        tag(Tags.Items.FENCES_WOODEN)
                .add(BorealisBlocks.BRUMAL_FENCE.get().asItem())
                .add(BorealisBlocks.CARAMELIZED_FENCE.get().asItem())
                .add(BorealisBlocks.SWEETWOOD_FENCE.get().asItem());
        tag(Tags.Items.STONES)
                .add(BorealisBlocks.SOAPSTONE.get().asItem())
                .add(BorealisBlocks.SLATE.get().asItem());
    }
}
