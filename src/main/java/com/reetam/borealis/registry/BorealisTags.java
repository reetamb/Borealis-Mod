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
        public static final TagKey<Item> SWEETWOOD_LOGS = tag("sweetwood_logs");
        public static final TagKey<Item> CARAMELIZED_LOGS = tag("caramelized_logs");

        public static final TagKey<Item> PREHISTORIC_PLANT = tag("prehistoric_plant");
        public static final TagKey<Item> MEAT = tag("meat"); // any food is considered meat the moment it contains any meat at all
        public static final TagKey<Item> PRODUCE = tag("produce"); // all plant-based food, even if processed (incl. cake, cookies, bread)
        public static final TagKey<Item> STEW = tag("stew"); // food that goes in a bowl

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, name));
        }
    }

    public static class Blocks {

        public static final TagKey<Block> BRUMAL_LOGS = tag("brumal_logs");
        public static final TagKey<Block> SWEETWOOD_LOGS = tag("sweetwood_logs");
        public static final TagKey<Block> CARAMELIZED_LOGS = tag("caramelized_logs");
        public static final TagKey<Block> BASE_STONE_BOREALIS = tag("base_stone_borealis");
        public static final TagKey<Block> PORTAL_FRAME_BLOCKS = tag("portal_frame_blocks");
        public static final TagKey<Block> PORTAL_CENTER_BLOCKS = tag("portal_center_blocks");
        public static final TagKey<Block> SNOWY_BLOCKS = tag("snowy_blocks");
        public static final TagKey<Block> SUGARY_BLOCKS = tag("sugary_blocks");
        public static final TagKey<Block> ICY_BLOCKS = tag("icy_blocks");
        public static final TagKey<Block> MINEABLE_WITH_SWORD = tag("mineable_with_sword");

        public static final TagKey<Block> SOAPSTONE_ORE_REPLACEABLES = tag("soapstone_ore_replaceables");

        public static final TagKey<Block> BLOCKSET_BRUMAL = tag("blockset_brumal");
        public static final TagKey<Block> BLOCKSET_SWEETWOOD = tag("blockset_sweetwood");
        public static final TagKey<Block> BLOCKSET_SOAPSTONE = tag("blockset_soapstone");
        public static final TagKey<Block> BLOCKSET_CARAMELIZED = tag("blockset_caramelized");

        public static final TagKey<Block> TAPPABLE = tag("tappable");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, name));
        }
    }
}
