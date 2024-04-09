package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BorealisTags {

    public static class Items {

        public static final TagKey<Item> BRUMAL_LOGS = tag("brumal_logs");
        public static final TagKey<Item> FROSTFIR_LOGS = tag("frostfir_logs");
        public static final TagKey<Item> SACCHARINE_LOGS = tag("saccharine_logs");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(BorealisMod.MODID, name));
        }
    }

    public static class Blocks {

        public static final TagKey<Block> BRUMAL_LOGS = tag("brumal_logs");
        public static final TagKey<Block> FROSTFIR_LOGS = tag("frostfir_logs");
        public static final TagKey<Block> SACCHARINE_LOGS = tag("saccharine_logs");
        public static final TagKey<Block> BASE_STONE_BOREALIS = tag("base_stone_borealis");
        public static final TagKey<Block> PORTAL_FRAME_BLOCKS = tag("portal_frame_blocks");
        public static final TagKey<Block> PORTAL_CENTER_BLOCKS = tag("portal_center_blocks");
        public static final TagKey<Block> SNOWY_BLOCKS = tag("snowy_blocks");
        public static final TagKey<Block> SUGARY_BLOCKS = tag("sugary_blocks");
        public static final TagKey<Block> ICY_BLOCKS = tag("icy_blocks");

        public static final TagKey<Block> SOAPSTONE_ORE_REPLACEABLES = tag("soapstone_ore_replaceables");

        public static final TagKey<Block> BLOCKSET_BRUMAL = tag("blockset_brumal");
        public static final TagKey<Block> BLOCKSET_FROSTFIR = tag("blockset_frostfir");
        public static final TagKey<Block> BLOCKSET_SACCHARINE = tag("blockset_saccharine");
        public static final TagKey<Block> BLOCKSET_SOAPSTONE = tag("blockset_soapstone");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(BorealisMod.MODID, name));
        }
    }
}
