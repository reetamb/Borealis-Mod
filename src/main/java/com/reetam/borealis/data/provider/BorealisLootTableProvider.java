package com.reetam.borealis.data.provider;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.*;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.IItemProvider;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class BorealisLootTableProvider extends BlockLootTables {

    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.invert();
    private static final ILootCondition.IBuilder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.or(SILK_TOUCH);

    public void registerTable(Supplier<? extends Block> block, Function<Block, LootTable.Builder> factory) {
        super.add(block.get(), factory);
    }

    public void dropSelf(Supplier<? extends Block> block) {
        super.dropSelf(block.get());
    }

    public void dropOther(Supplier<? extends Block> brokenBlock, IItemProvider droppedBlock) {
        super.dropOther(brokenBlock.get(), droppedBlock);
    }

    public void dropAsSilk(Supplier<? extends Block> block) {
        super.dropWhenSilkTouch(block.get());
    }

    public void dropWithSilk(Supplier<? extends Block> block, Supplier<? extends IItemProvider> drop) {
        add(block.get(), (result) -> createSingleItemTableWithSilkTouch(result, drop.get()));
    }

    public void dropWithFortune(Supplier<? extends Block> block, Supplier<? extends Item> drop) {
        super.add(block.get(), (result) -> createOreDrop(result, drop.get()));
    }

    public void dropWithFortune(Supplier<? extends Block> block, IItemProvider drop) {
        super.add(block.get(), (result) -> createOreDrop(result, drop.asItem()));
    }

    public void dropChance(Supplier<? extends Block> block, Supplier<? extends Block> drop, float... chances) {
        add(block.get(), (result) -> withChance(block.get(), drop.get(), chances));
    }

    public void dropChanceAdditional(Supplier<? extends Block> block, Supplier<? extends Block> drop, Supplier<? extends Item> item, float... chances) {
        add(block.get(), (result) -> withChanceAdditional(block.get(), drop.get(), item.get(), chances));
    }

    protected static LootTable.Builder withChance(Block block, Block drop, float... chances) {
        return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, ItemLootEntry.lootTableItem(drop))
                .when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances)));
    }

    protected static LootTable.Builder withChanceAdditional(Block block, Block sapling, Item item, float... chances) {
        return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, ItemLootEntry.lootTableItem(sapling))
                .when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances)))
                .withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                        .when(SILK_TOUCH_OR_SHEARS)
                        .add(applyExplosionDecay(block, ItemLootEntry.lootTableItem(item)
                                .apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))))
                                .when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
    }
}
