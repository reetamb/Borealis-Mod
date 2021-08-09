package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class BorealisItemTags extends ItemTagsProvider {

    public BorealisItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, BorealisMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Borealis Item Tags";
    }

    @Override
    protected void addTags() {
        //mod
        copy(BorealisTags.Blocks.BRUMAL_LOGS, BorealisTags.Items.BRUMAL_LOGS);
        copy(BorealisTags.Blocks.FROSTFIR_LOGS, BorealisTags.Items.FROSTFIR_LOGS);
        copy(BorealisTags.Blocks.SACCHARINE_LOGS, BorealisTags.Items.SACCHARINE_LOGS);

        //vanilla
        tag(ItemTags.SAPLINGS)
                .add(BorealisBlocks.brumal_sapling.get().asItem())
                .add(BorealisBlocks.frostfir_sapling.get().asItem())
                .add(BorealisBlocks.saccharine_sapling.get().asItem());
        tag(ItemTags.PLANKS)
                .add(BorealisBlocks.brumal_planks.get().asItem())
                .add(BorealisBlocks.frostfir_planks.get().asItem())
                .add(BorealisBlocks.saccharine_planks.get().asItem());
        tag(ItemTags.WOODEN_BUTTONS)
                .add(BorealisBlocks.brumal_button.get().asItem())
                .add(BorealisBlocks.frostfir_button.get().asItem())
                .add(BorealisBlocks.saccharine_button.get().asItem());
        tag(ItemTags.BUTTONS)
                .add(BorealisBlocks.soapstone_button.get().asItem());
        tag(ItemTags.WOODEN_DOORS)
                .add(BorealisBlocks.brumal_door.get().asItem())
                .add(BorealisBlocks.frostfir_door.get().asItem())
                .add(BorealisBlocks.saccharine_door.get().asItem());
        tag(ItemTags.WOODEN_STAIRS)
                .add(BorealisBlocks.brumal_stairs.get().asItem())
                .add(BorealisBlocks.frostfir_stairs.get().asItem())
                .add(BorealisBlocks.saccharine_stairs.get().asItem());
        tag(ItemTags.WOODEN_SLABS)
                .add(BorealisBlocks.brumal_slab.get().asItem())
                .add(BorealisBlocks.frostfir_slab.get().asItem())
                .add(BorealisBlocks.saccharine_slab.get().asItem());
        tag(ItemTags.WOODEN_FENCES)
                .add(BorealisBlocks.brumal_fence.get().asItem())
                .add(BorealisBlocks.frostfir_fence.get().asItem())
                .add(BorealisBlocks.saccharine_fence.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(BorealisTags.Items.BRUMAL_LOGS)
                .addTag(BorealisTags.Items.FROSTFIR_LOGS)
                .addTag(BorealisTags.Items.SACCHARINE_LOGS);
        tag(ItemTags.LOGS)
                .addTag(BorealisTags.Items.BRUMAL_LOGS)
                .addTag(BorealisTags.Items.FROSTFIR_LOGS)
                .addTag(BorealisTags.Items.SACCHARINE_LOGS);
        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(BorealisBlocks.brumal_pressure_plate.get().asItem())
                .add(BorealisBlocks.frostfir_pressure_plate.get().asItem())
                .add(BorealisBlocks.saccharine_pressure_plate.get().asItem());
        tag(ItemTags.WALLS)
                .add(BorealisBlocks.soapstone_wall.get().asItem())
                .add(BorealisBlocks.soapstone_brick_wall.get().asItem());
        tag(ItemTags.LEAVES)
                .add(BorealisBlocks.brumal_leaves.get().asItem())
                .add(BorealisBlocks.frostfir_leaves.get().asItem())
                .add(BorealisBlocks.saccharine_leaves.get().asItem());
        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(BorealisBlocks.brumal_trapdoor.get().asItem())
                .add(BorealisBlocks.frostfir_trapdoor.get().asItem())
                .add(BorealisBlocks.saccharine_trapdoor.get().asItem());
        tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(BorealisBlocks.soapstone.get().asItem())
                .add(BorealisBlocks.slate.get().asItem());
        tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(BorealisBlocks.soapstone.get().asItem())
                .add(BorealisBlocks.slate.get().asItem());

//forge
        tag(Tags.Items.COBBLESTONE)
                .add(BorealisBlocks.soapstone.get().asItem())
                .add(BorealisBlocks.slate.get().asItem());
        tag(Tags.Items.FENCE_GATES_WOODEN)
                .add(BorealisBlocks.brumal_fence_gate.get().asItem())
                .add(BorealisBlocks.frostfir_fence_gate.get().asItem())
                .add(BorealisBlocks.saccharine_fence_gate.get().asItem());
        tag(Tags.Items.FENCES_WOODEN)
                .add(BorealisBlocks.brumal_fence.get().asItem())
                .add(BorealisBlocks.frostfir_fence.get().asItem())
                .add(BorealisBlocks.saccharine_fence.get().asItem());
        tag(Tags.Items.STONE)
                .add(BorealisBlocks.soapstone.get().asItem())
                .add(BorealisBlocks.slate.get().asItem());
    }

    protected TagsProvider.Builder<Item> tag(ITag.INamedTag<Item> tag) {
        return super.tag(tag);
    }
}
