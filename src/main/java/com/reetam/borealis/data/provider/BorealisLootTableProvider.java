package com.reetam.borealis.data.provider;

import net.minecraft.block.*;
import net.minecraft.data.loot.BlockLootTables;

import java.util.function.Supplier;

public abstract class BorealisLootTableProvider extends BlockLootTables {

    protected String name(Supplier<? extends Block> block) {
        return block.get().getRegistryName().getPath();
    }

    public void dropSelf(Supplier<? extends Block> block) {
        super.registerDropSelfLootTable(block.get());
    }
}
