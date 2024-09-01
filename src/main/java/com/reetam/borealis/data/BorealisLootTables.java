package com.reetam.borealis.data;

import com.reetam.borealis.block.AlmsCrackedBlock;
import com.reetam.borealis.block.KyaniteArrowBlock;
import com.reetam.borealis.data.provider.BorealisLootTableProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BorealisLootTables extends LootTableProvider {

    private static final LootItemCondition.Builder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder SILK_TOUCH_OR_SHEARS = SHEARS.or(SILK_TOUCH);
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    public BorealisLootTables(PackOutput output) {
        super(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(() -> new Blocks(Set.of(BorealisItems.HAILSTONE.get()), FeatureFlags.REGISTRY.allFlags()), LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(() -> new Entities(FeatureFlags.REGISTRY.allFlags()), LootContextParamSets.ENTITY)
        ));
    }


    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {

    }


    public static class Blocks extends BorealisLootTableProvider {
        protected Blocks(Set<Item> explosionResistant, FeatureFlagSet enabledFeatures) {
            super(explosionResistant, enabledFeatures);
        }

        @Override
        protected void generate() {
            List<Block> entries = List.of(
                    dropWithFortune(BorealisBlocks.KYANITE_ORE, BorealisItems.KYANITE_CRYSTAL),
                    drop(BorealisBlocks.BRUMAL_LEAVES.get(), createLeavesDrops(BorealisBlocks.BRUMAL_LEAVES.get(), BorealisBlocks.BRUMAL_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES)),
                    dropPottedPlant(BorealisBlocks.POTTED_BRUMAL_SAPLING.get()),
                    dropOther(BorealisBlocks.BRUMAL_WALL_SIGN, () -> BorealisBlocks.BRUMAL_SIGN.get().asItem()),
                    drop(BorealisBlocks.BRUMAL_DOOR.get(), createDoorTable(BorealisBlocks.BRUMAL_DOOR.get())),
                    drop(BorealisBlocks.FROSTFIR_LEAVES.get(), createLeavesDrops(BorealisBlocks.FROSTFIR_LEAVES.get(), BorealisBlocks.FROSTFIR_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES)),
                    dropPottedPlant(BorealisBlocks.POTTED_FROSTFIR_SAPLING.get()),
                    dropOther(BorealisBlocks.FROSTFIR_WALL_SIGN, () -> BorealisBlocks.FROSTFIR_SIGN.get().asItem()),
                    drop(BorealisBlocks.FROSTFIR_DOOR.get(), createDoorTable(BorealisBlocks.FROSTFIR_DOOR.get())),
                    drop(BorealisBlocks.SWEETWOOD_LEAVES.get(), createLeavesDrops(BorealisBlocks.SWEETWOOD_LEAVES.get(), BorealisBlocks.SWEETWOOD_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES)),
                    dropPottedPlant(BorealisBlocks.POTTED_SWEETWOOD_SAPLING.get()),
                    dropOther(BorealisBlocks.SWEETWOOD_WALL_SIGN, () -> BorealisBlocks.SWEETWOOD_SIGN.get().asItem()),
                    drop(BorealisBlocks.SWEETWOOD_DOOR.get(), createDoorTable(BorealisBlocks.SWEETWOOD_DOOR.get())),
                    dropOther(BorealisBlocks.CARAMELIZED_WALL_SIGN, () -> BorealisBlocks.CARAMELIZED_SIGN.get().asItem()),
                    drop(BorealisBlocks.CARAMELIZED_DOOR.get(), createDoorTable(BorealisBlocks.CARAMELIZED_DOOR.get())),
                    dropWithSilk(BorealisBlocks.LIVING_SNOW_BLOCK, () -> net.minecraft.world.level.block.Blocks.SNOW_BLOCK),
                    drop(BorealisBlocks.PERMAFROST_RUBBLE.get(), new LootTable.Builder().withPool(new LootPool.Builder()
                                            .add(chance(Items.FLINT, 0.2F))
                                            .add(chance(Items.STICK, 0.5F))
                                            .add(chance(Items.IRON_NUGGET, 0.3F))
                                            .add(chance(Items.IRON_INGOT, 0.03F))
                                            .add(chance(BorealisBlocks.PETRIFIED_WOOD.get(), 0.4F))
                                            .add(chance(Items.FEATHER, 0.2F))
                                            .add(chance(Items.STRING, 0.2F))
                                            .add(chance(Items.CHARCOAL, 0.6F)))),
                    dropWithSilk(BorealisBlocks.CLOUD, BorealisBlocks.CLOUD),
                    drop(BorealisBlocks.SUGAR_SNOW.get(), new LootTable.Builder().withPool(new LootPool.Builder()
                                            .add(chance(Items.SUGAR, 1.0F))
                                            .add(chance(Items.SNOWBALL, 1.0F)))),
                    dropWithFortune(BorealisBlocks.TANZANITE_ORE, BorealisItems.TANZANITE.get()),
                    dropOther(BorealisBlocks.HAILSTONE, BorealisItems.HAILSTONE.get()),
                    dropWithFortune(BorealisBlocks.PEAT, Items.COAL),
                    drop(BorealisBlocks.MISTERIA_BODY.get(), noDrop()),
                    drop(BorealisBlocks.MISTERIA_HEAD.get(), createShearsOnlyDrop(BorealisBlocks.MISTERIA_HEAD.get())),
                    drop(BorealisBlocks.BRUMELIAD.get(), createShearsOnlyDrop(BorealisBlocks.BRUMELIAD.get())),
                    drop(BorealisBlocks.WINTER_VIOLA.get(), createShearsOnlyDrop(BorealisBlocks.WINTER_VIOLA.get())),
                    drop(BorealisBlocks.WINTER_VIOLIN.get(), createShearsOnlyDrop(BorealisBlocks.WINTER_VIOLIN.get())),
                    drop(BorealisBlocks.WALL_WINTER_VIOLIN.get(), createShearsOnlyDrop(BorealisBlocks.WALL_WINTER_VIOLIN.get())),
                    drop(BorealisBlocks.WINTER_CELLO.get(), createShearsOnlyDrop(BorealisBlocks.WINTER_CELLO.get())),
//                    drop(BorealisBlocks.CRACKED_ALMS.get(), dropWithBooleanState(
//                            BorealisItems.ALMS_NUT.get(),
//                            BorealisBlocks.CRACKED_ALMS.get(),
//                            AlmsCrackedBlock.EMPTY, true)),

                    drop(BorealisBlocks.EMBEDDED_KYANITE_ARROW.get(), dropWithBooleanState(
                            BorealisItems.KYANITE_CRYSTAL.get(),
                            BorealisBlocks.EMBEDDED_KYANITE_ARROW.get(),
                            KyaniteArrowBlock.DROPS, false))
            );

            
            getKnownBlocks().iterator().forEachRemaining((knownBlock) -> {
                if (knownBlock instanceof Block && !(knownBlock.getLootTable() == BuiltInLootTables.EMPTY) && !entries.contains(knownBlock)) {
                    dropSelf(knownBlock);
                }
            });
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BorealisBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }

        private LootTable.Builder dropWithBooleanState(Item dropItem, Block minedBlock, Property<Boolean> property, boolean value) {
            return new LootTable.Builder()
                    .withPool(new LootPool.Builder()
                            .add(LootItem.lootTableItem(dropItem))
                            .when(new LootItemBlockStatePropertyCondition.Builder(minedBlock)
                                    .setProperties(StatePropertiesPredicate.Builder.properties()
                                            .hasProperty(property, value)
                            )));
        }

        private LootPoolSingletonContainer.Builder<?> chance(ItemLike item, float probability) {
            return LootItem.lootTableItem(item).when(LootItemRandomChanceCondition.randomChance(probability));
        }
    }

    public static class Entities extends EntityLootSubProvider {

        protected Entities(FeatureFlagSet enabledFeatures) {
            super(enabledFeatures);
        }
        @Override
        public void generate() {
            this.add(
                    BorealisEntities.HUMMINGBIRD.get(),
                    LootTable.lootTable().withPool
                            (LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                                    .add(LootItem.lootTableItem(Items.FEATHER)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                            .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
            this.add(
                    BorealisEntities.TAKAHE.get(),
                    LootTable.lootTable().withPool
                            (LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                                    .add(LootItem.lootTableItem(Items.FEATHER)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                    .add(LootItem.lootTableItem(Items.CHICKEN)
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                            ));
            this.add(
                    BorealisEntities.THRUSHER.get(),
                    LootTable.lootTable().withPool
                            (LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                                    .add(LootItem.lootTableItem(Items.FEATHER)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                                    .add(LootItem.lootTableItem(Items.CHICKEN)
                                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))
            ));
        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes() {
            return BorealisEntities.ENTITIES.getEntries().stream().map(Supplier::get);
        }
    }
}
