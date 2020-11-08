package com.reetam.borealis.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class BorealisItemGroups {

    public static final ItemGroup BLOCKS_GROUP = new ItemGroup("borealis_blocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BorealisBlocks.slate.get());
        }
    };
    public static final ItemGroup ITEMS_GROUP = new ItemGroup("borealis_items") {
        @Override
        public ItemStack createIcon() { return new ItemStack(BorealisItems.kyanite_crystal.get()); }
    };
}