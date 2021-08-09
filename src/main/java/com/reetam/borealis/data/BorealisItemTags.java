package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
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
                .add(BorealisBlocks.BRUMAL_SAPLING.get().asItem())
                .add(BorealisBlocks.FROSTFIR_SAPLING.get().asItem())
                .add(BorealisBlocks.SACCHARINE_SAPLING.get().asItem());
        tag(ItemTags.PLANKS)
                .add(BorealisBlocks.BRUMAL_PLANKS.get().asItem())
                .add(BorealisBlocks.FROSTFIR_PLANKS.get().asItem())
                .add(BorealisBlocks.SACCHARINE_PLANKS.get().asItem());
        tag(ItemTags.WOODEN_BUTTONS)
                .add(BorealisBlocks.BRUMAL_BUTTON.get().asItem())
                .add(BorealisBlocks.FROSTFIR_BUTTON.get().asItem())
                .add(BorealisBlocks.SACCHARINE_BUTTON.get().asItem());
        tag(ItemTags.BUTTONS)
                .add(BorealisBlocks.SOAPSTONE_BUTTON.get().asItem());
        tag(ItemTags.WOODEN_DOORS)
                .add(BorealisBlocks.BRUMAL_DOOR.get().asItem())
                .add(BorealisBlocks.FROSTFIR_DOOR.get().asItem())
                .add(BorealisBlocks.SACCHARINE_DOOR.get().asItem());
        tag(ItemTags.WOODEN_STAIRS)
                .add(BorealisBlocks.BRUMAL_STAIRS.get().asItem())
                .add(BorealisBlocks.FROSTFIR_STAIRS.get().asItem())
                .add(BorealisBlocks.SACCHARINE_STAIRS.get().asItem());
        tag(ItemTags.WOODEN_SLABS)
                .add(BorealisBlocks.BRUMAL_SLAB.get().asItem())
                .add(BorealisBlocks.FROSTFIR_SLAB.get().asItem())
                .add(BorealisBlocks.SACCHARINE_SLAB.get().asItem());
        tag(ItemTags.WOODEN_FENCES)
                .add(BorealisBlocks.BRUMAL_FENCE.get().asItem())
                .add(BorealisBlocks.FROSTFIR_FENCE.get().asItem())
                .add(BorealisBlocks.SACCHARINE_FENCE.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(BorealisTags.Items.BRUMAL_LOGS)
                .addTag(BorealisTags.Items.FROSTFIR_LOGS)
                .addTag(BorealisTags.Items.SACCHARINE_LOGS);
        tag(ItemTags.LOGS)
                .addTag(BorealisTags.Items.BRUMAL_LOGS)
                .addTag(BorealisTags.Items.FROSTFIR_LOGS)
                .addTag(BorealisTags.Items.SACCHARINE_LOGS);
        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(BorealisBlocks.BRUMAL_PRESSURE_PLATE.get().asItem())
                .add(BorealisBlocks.FROSTFIR_PRESSURE_PLATE.get().asItem())
                .add(BorealisBlocks.SACCHARINE_PRESSURE_PLATE.get().asItem());
        tag(ItemTags.WALLS)
                .add(BorealisBlocks.SOAPSTONE_WALL.get().asItem())
                .add(BorealisBlocks.SOAPSTONE_BRICK_WALL.get().asItem());
        tag(ItemTags.LEAVES)
                .add(BorealisBlocks.BRUMAL_LEAVES.get().asItem())
                .add(BorealisBlocks.FROSTFIR_LEAVES.get().asItem())
                .add(BorealisBlocks.SACCHARINE_LEAVES.get().asItem());
        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(BorealisBlocks.BRUMAL_TRAPDOOR.get().asItem())
                .add(BorealisBlocks.FROSTFIR_TRAPDOOR.get().asItem())
                .add(BorealisBlocks.SACCHARINE_TRAPDOOR.get().asItem());
        tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(BorealisBlocks.SOAPSTONE.get().asItem())
                .add(BorealisBlocks.SLATE.get().asItem());
        tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(BorealisBlocks.SOAPSTONE.get().asItem())
                .add(BorealisBlocks.SLATE.get().asItem());
        tag(ItemTags.BOATS)
                .add(BorealisItems.BRUMAL_BOAT.get())
                .add(BorealisItems.FROSTFIR_BOAT.get())
                .add(BorealisItems.SACCHARINE_BOAT.get());
        tag(ItemTags.SIGNS)
                .add(BorealisItems.BRUMAL_SIGN.get())
                .add(BorealisItems.FROSTFIR_SIGN.get())
                .add(BorealisItems.SACCHARINE_SIGN.get());

        //forge
        tag(Tags.Items.COBBLESTONE)
                .add(BorealisBlocks.SOAPSTONE.get().asItem())
                .add(BorealisBlocks.SLATE.get().asItem());
        tag(Tags.Items.FENCE_GATES_WOODEN)
                .add(BorealisBlocks.BRUMAL_FENCE_GATE.get().asItem())
                .add(BorealisBlocks.FROSTFIR_FENCE_GATE.get().asItem())
                .add(BorealisBlocks.SACCHARINE_FENCE_GATE.get().asItem());
        tag(Tags.Items.FENCES_WOODEN)
                .add(BorealisBlocks.BRUMAL_FENCE.get().asItem())
                .add(BorealisBlocks.FROSTFIR_FENCE.get().asItem())
                .add(BorealisBlocks.SACCHARINE_FENCE.get().asItem());
        tag(Tags.Items.STONE)
                .add(BorealisBlocks.SOAPSTONE.get().asItem())
                .add(BorealisBlocks.SLATE.get().asItem());
    }

    protected TagsProvider.Builder<Item> tag(ITag.INamedTag<Item> tag) {
        return super.tag(tag);
    }
}
