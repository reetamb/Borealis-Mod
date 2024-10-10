package com.reetam.borealis.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Supplier;

public abstract class BorealisLootTableProvider extends BlockLootSubProvider {
    protected BorealisLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), new HashMap<>(), provider);
    }

    public Block dropSelf(Supplier<? extends Block> block) {
        super.dropSelf(block.get());
        return block.get();
    }

    public Block dropOther(Supplier<? extends Block> brokenBlock, ItemLike droppedBlock) {
        super.dropOther(brokenBlock.get(), droppedBlock);
        return brokenBlock.get();
    }

    public Block dropAsSilk(Supplier<? extends Block> block) {
        super.dropWhenSilkTouch(block.get());
        return block.get();
    }

    public Block dropWithSilk(Supplier<? extends Block> block, Supplier<? extends ItemLike> drop) {
        add(block.get(), (result) -> createSingleItemTableWithSilkTouch(result, drop.get()));
        return block.get();
    }

    public Block dropWithFortune(Supplier<? extends Block> block, Supplier<? extends Item> drop) {
        super.add(block.get(), (result) -> createOreDrop(result, drop.get()));
        return block.get();
    }

    public Block dropWithFortune(Supplier<? extends Block> block, ItemLike drop) {
        super.add(block.get(), (result) -> createOreDrop(result, drop.asItem()));
        return block.get();
    }

    public Block dropPottedPlant(Block flowerPot) {
        super.dropPottedContents(flowerPot);
        return flowerPot;
    }

    public Block drop(Block pBlock, LootTable.Builder pBuilder) {
        super.add(pBlock, pBuilder);
        return pBlock;
    }
}
