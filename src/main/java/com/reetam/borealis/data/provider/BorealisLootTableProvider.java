package com.reetam.borealis.data.provider;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;
import java.util.function.Supplier;

public abstract class BorealisLootTableProvider extends BlockLootSubProvider {

    private static final LootItemCondition.Builder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder NO_SILK_TOUCH = SILK_TOUCH.invert();
    private static final LootItemCondition.Builder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder SILK_TOUCH_OR_SHEARS = SHEARS.or(SILK_TOUCH);

    protected BorealisLootTableProvider(Set<Item> explosionResistant, FeatureFlagSet enabledFeatures) {
        super(explosionResistant, enabledFeatures);
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

    public Block dropChance(Supplier<? extends Block> block, Supplier<? extends Block> drop, float... chances) {
        add(block.get(), (result) -> withChance(block.get(), drop.get(), chances));
        return block.get();
    }

    public Block dropChanceAdditional(Supplier<? extends Block> block, Supplier<? extends Block> drop, Supplier<? extends Item> item, float... chances) {
        add(block.get(), (result) -> withChanceAdditional(block.get(), drop.get(), item.get(), chances));
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

    protected static LootTable.Builder withChance(Block block, Block drop, float... chances) {
        return createSilkTouchOrShearsDispatchTable(block, LootItem.lootTableItem(drop))
                .withPool(new LootPool.Builder()
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances)));
    }

    protected static LootTable.Builder withChanceAdditional(Block block, Block sapling, Item item, float... chances) {
        return createSilkTouchOrShearsDispatchTable(block, LootItem.lootTableItem(sapling)).withPool(new LootPool.Builder()
                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(SILK_TOUCH_OR_SHEARS)
                        .add((LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
    }
}
