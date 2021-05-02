package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class BorealisTags {

    public static class Items {

        public static final ITag.INamedTag<Item> BRUMAL_LOGS = tag("brumal_logs");
        public static final ITag.INamedTag<Item> FROSTFIR_LOGS = tag("frostfir_logs");
        public static final ITag.INamedTag<Item> SACCHARINE_LOGS = tag("saccharine_logs");

        private static ITag.INamedTag<Item> tag(String name) {
            return ItemTags.createOptional(new ResourceLocation(BorealisMod.MODID, name));
        }
    }

    public static class Blocks {

        public static final ITag.INamedTag<Block> BRUMAL_LOGS = tag("brumal_logs");
        public static final ITag.INamedTag<Block> FROSTFIR_LOGS = tag("frostfir_logs");
        public static final ITag.INamedTag<Block> SACCHARINE_LOGS = tag("saccharine_logs");
        public static final ITag.INamedTag<Block> BASE_STONE_BOREALIS = tag("base_stone_borealis");
        public static final ITag.INamedTag<Block> PORTAL_FRAME_BLOCKS = tag("portal_frame_blocks");
        public static final ITag.INamedTag<Block> PORTAL_CENTER_BLOCKS = tag("portal_center_blocks");

        private static ITag.INamedTag<Block> tag(String name) {
            return BlockTags.createOptional(new ResourceLocation(BorealisMod.MODID, name));
        }
    }
}
