package com.reetam.borealis.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class BorealisItemGroups {

    public static final ItemGroup BLOCKS_GROUP = new ItemGroup("borealis_blocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BorealisBlocks.soapstone.get());
        }
    };
}