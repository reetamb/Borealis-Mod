package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BorealisTags {

    public static class Items {

        public static final Tag.Named<Item> BRUMAL_LOGS = tag("brumal_logs");
        public static final Tag.Named<Item> FROSTFIR_LOGS = tag("frostfir_logs");
        public static final Tag.Named<Item> SACCHARINE_LOGS = tag("saccharine_logs");

        private static Tag.Named<Item> tag(String name) {
            return ItemTags.createOptional(new ResourceLocation(BorealisMod.MODID, name));
        }
    }

    public static class Blocks {

        public static final Tag.Named<Block> BRUMAL_LOGS = tag("brumal_logs");
        public static final Tag.Named<Block> FROSTFIR_LOGS = tag("frostfir_logs");
        public static final Tag.Named<Block> SACCHARINE_LOGS = tag("saccharine_logs");
        public static final Tag.Named<Block> BASE_STONE_BOREALIS = tag("base_stone_borealis");
        public static final Tag.Named<Block> PORTAL_FRAME_BLOCKS = tag("portal_frame_blocks");
        public static final Tag.Named<Block> PORTAL_CENTER_BLOCKS = tag("portal_center_blocks");
        public static final Tag.Named<Block> SNOWY_BLOCKS = tag("snowy_blocks");
        public static final Tag.Named<Block> SUGARY_BLOCKS = tag("sugary_blocks");
        public static final Tag.Named<Block> ICY_BLOCKS = tag("icy_blocks");

        private static Tag.Named<Block> tag(String name) {
            return BlockTags.createOptional(new ResourceLocation(BorealisMod.MODID, name));
        }
    }
}
