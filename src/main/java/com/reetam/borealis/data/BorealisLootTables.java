package com.reetam.borealis.data;

import com.reetam.borealis.data.provider.BorealisLootTableProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
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
        super(output, Set.of(), List.of());
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
            dropSelf(BorealisBlocks.SOAPSTONE);
            dropSelf(BorealisBlocks.SOAPSTONE_TILES);
            dropWithFortune(BorealisBlocks.KYANITE_ORE, BorealisItems.KYANITE_CRYSTAL);
            dropSelf(BorealisBlocks.SOAPSTONE);
            dropSelf(BorealisBlocks.SOAPSTONE_TILES);
            dropSelf(BorealisBlocks.SOAPSTONE_BRICKS);
            dropSelf(BorealisBlocks.SOAPSTONE_STAIRS);
            dropSelf(BorealisBlocks.SOAPSTONE_TILE_STAIRS);
            dropSelf(BorealisBlocks.SOAPSTONE_BRICK_STAIRS);
            dropSelf(BorealisBlocks.SOAPSTONE_SLAB);
            dropSelf(BorealisBlocks.SOAPSTONE_TILE_SLAB);
            dropSelf(BorealisBlocks.SOAPSTONE_BRICK_SLAB);
            dropSelf(BorealisBlocks.SOAPSTONE_WALL);
            dropSelf(BorealisBlocks.SOAPSTONE_TILE_WALL);
            dropSelf(BorealisBlocks.SOAPSTONE_BRICK_WALL);
            dropSelf(BorealisBlocks.SOAPSTONE_BUTTON);
            dropSelf(BorealisBlocks.SOAPSTONE_PRESSURE_PLATE);
            dropSelf(BorealisBlocks.SLATE);
            dropSelf(BorealisBlocks.SLATE_PILLAR);
            dropSelf(BorealisBlocks.SLATE_TILES);
            dropSelf(BorealisBlocks.PUMICE);
            dropSelf(BorealisBlocks.PUMICE_GEYSER);
            dropSelf(BorealisBlocks.PETRIFIED_WOOD);
            dropSelf(BorealisBlocks.PETRIFIED_WOOD_BRICKS);
            dropSelf(BorealisBlocks.BRUMAL_PLANKS);
            dropSelf(BorealisBlocks.BRUMAL_LOG);
            dropSelf(BorealisBlocks.BRUMAL_WOOD);
            dropSelf(BorealisBlocks.STRIPPED_BRUMAL_LOG);
            dropSelf(BorealisBlocks.STRIPPED_BRUMAL_WOOD);
            this.add(BorealisBlocks.BRUMAL_LEAVES.get(), (leaves) -> createLeavesDrops(leaves, BorealisBlocks.BRUMAL_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
            dropSelf(BorealisBlocks.BRUMAL_STAIRS);
            dropSelf(BorealisBlocks.BRUMAL_SLAB);
            dropSelf(BorealisBlocks.BRUMAL_FENCE);
            dropSelf(BorealisBlocks.BRUMAL_FENCE_GATE);
            dropSelf(BorealisBlocks.BRUMAL_DOOR);
            dropSelf(BorealisBlocks.BRUMAL_TRAPDOOR);
            dropSelf(BorealisBlocks.BRUMAL_BUTTON);
            dropSelf(BorealisBlocks.BRUMAL_PRESSURE_PLATE);
            dropSelf(BorealisBlocks.BRUMAL_SAPLING);
            dropPottedContents(BorealisBlocks.POTTED_BRUMAL_SAPLING.get());
            dropSelf(BorealisBlocks.BRUMAL_SIGN);
            dropOther(BorealisBlocks.BRUMAL_WALL_SIGN, () -> BorealisBlocks.BRUMAL_SIGN.get().asItem());
            dropSelf(BorealisBlocks.FROSTFIR_PLANKS);
            dropSelf(BorealisBlocks.FROSTFIR_LOG);
            dropSelf(BorealisBlocks.FROSTFIR_WOOD);
            dropSelf(BorealisBlocks.STRIPPED_FROSTFIR_LOG);
            dropSelf(BorealisBlocks.STRIPPED_FROSTFIR_WOOD);
            this.add(BorealisBlocks.FROSTFIR_LEAVES.get(), (leaves) -> createLeavesDrops(leaves, BorealisBlocks.FROSTFIR_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
            dropSelf(BorealisBlocks.FROSTFIR_STAIRS);
            dropSelf(BorealisBlocks.FROSTFIR_SLAB);
            dropSelf(BorealisBlocks.FROSTFIR_FENCE);
            dropSelf(BorealisBlocks.FROSTFIR_FENCE_GATE);
            dropSelf(BorealisBlocks.FROSTFIR_DOOR);
            dropSelf(BorealisBlocks.FROSTFIR_TRAPDOOR);
            dropSelf(BorealisBlocks.FROSTFIR_BUTTON);
            dropSelf(BorealisBlocks.FROSTFIR_PRESSURE_PLATE);
            dropSelf(BorealisBlocks.FROSTFIR_SAPLING);
            dropPottedContents(BorealisBlocks.POTTED_FROSTFIR_SAPLING.get());
            dropSelf(BorealisBlocks.FROSTFIR_SIGN);
            dropOther(BorealisBlocks.FROSTFIR_WALL_SIGN, () -> BorealisBlocks.FROSTFIR_SIGN.get().asItem());
            dropSelf(BorealisBlocks.SACCHARINE_PLANKS);
            dropSelf(BorealisBlocks.SACCHARINE_LOG);
            dropSelf(BorealisBlocks.SACCHARINE_WOOD);
            dropSelf(BorealisBlocks.STRIPPED_SACCHARINE_LOG);
            this.add(BorealisBlocks.SACCHARINE_LEAVES.get(), (leaves) -> createLeavesDrops(leaves, BorealisBlocks.SACCHARINE_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
            dropSelf(BorealisBlocks.SACCHARINE_STAIRS);
            dropSelf(BorealisBlocks.SACCHARINE_SLAB);
            dropSelf(BorealisBlocks.STRIPPED_SACCHARINE_WOOD);
            dropSelf(BorealisBlocks.SACCHARINE_FENCE);
            dropSelf(BorealisBlocks.SACCHARINE_FENCE_GATE);
            dropSelf(BorealisBlocks.SACCHARINE_DOOR);
            dropSelf(BorealisBlocks.SACCHARINE_TRAPDOOR);
            dropSelf(BorealisBlocks.SACCHARINE_BUTTON);
            dropSelf(BorealisBlocks.SACCHARINE_PRESSURE_PLATE);
            dropSelf(BorealisBlocks.SACCHARINE_SAPLING);
            dropPottedContents(BorealisBlocks.POTTED_SACCHARINE_SAPLING.get());
            dropSelf(BorealisBlocks.SACCHARINE_SIGN);
            dropOther(BorealisBlocks.SACCHARINE_WALL_SIGN, () -> BorealisBlocks.SACCHARINE_SIGN.get().asItem());
            dropWithSilk(BorealisBlocks.LIVING_SNOW_BLOCK, () -> net.minecraft.world.level.block.Blocks.SNOW_BLOCK);
            dropSelf(BorealisBlocks.SUGAR_SNOW_BLOCK);
            dropSelf(BorealisBlocks.PERMAFROST);
            dropSelf(BorealisBlocks.TRAVERTINE);
            this.add(BorealisBlocks.PERMAFROST_RUBBLE.get(),
                    new LootTable.Builder()
                            .withPool(new LootPool.Builder()
                                    .add(LootItem.lootTableItem(Items.FLINT).when(LootItemRandomChanceCondition.randomChance(0.2F)))
                                    .add(LootItem.lootTableItem(Items.STICK).when(LootItemRandomChanceCondition.randomChance( 0.5F)))
                                    .add(LootItem.lootTableItem(Items.IRON_NUGGET).when(LootItemRandomChanceCondition.randomChance(0.3F)))
                                    .add(LootItem.lootTableItem(Items.IRON_INGOT).when(LootItemRandomChanceCondition.randomChance(0.03F)))
                                    .add(LootItem.lootTableItem(BorealisBlocks.PETRIFIED_WOOD.get().asItem()).when(LootItemRandomChanceCondition.randomChance(0.25F)))
                                    .add(LootItem.lootTableItem(Items.ARROW).when(LootItemRandomChanceCondition.randomChance(0.2F)))
                                    .add(LootItem.lootTableItem(Items.FEATHER).when(LootItemRandomChanceCondition.randomChance(0.5F)))
                                    .add(LootItem.lootTableItem(Items.STRING).when(LootItemRandomChanceCondition.randomChance(0.1F)))
                                    .add(LootItem.lootTableItem(Items.COAL).when(LootItemRandomChanceCondition.randomChance(0.2F)))
                                    .add(LootItem.lootTableItem(Items.CHARCOAL).when(LootItemRandomChanceCondition.randomChance(0.2F)))
                                    .add(LootItem.lootTableItem(Items.POISONOUS_POTATO).when(LootItemRandomChanceCondition.randomChance(0.01F)))));
            dropWithSilk(BorealisBlocks.CLOUD, BorealisBlocks.CLOUD);
            this.add(BorealisBlocks.SUGAR_SNOW.get(),
                    new LootTable.Builder()
                            .withPool(new LootPool.Builder()
                                .add(LootItem.lootTableItem(Items.SUGAR).when(LootItemRandomChanceCondition.randomChance(1.0F)))
                                .add(LootItem.lootTableItem(Items.SNOWBALL).when(LootItemRandomChanceCondition.randomChance(1.0F)))));
            dropOther(BorealisBlocks.TANZANITE_ORE, BorealisItems.TANZANITE.get());
            dropSelf(BorealisBlocks.TANZANITE_BLOCK);
            dropSelf(BorealisBlocks.STARRY_SLATE);
            dropSelf(BorealisBlocks.STARRY_SLATE_TILES);
            dropOther(BorealisBlocks.HAILSTONE, BorealisItems.HAILSTONE.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BorealisBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
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
        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes() {
            return BorealisEntities.ENTITIES.getEntries().stream().map(Supplier::get);
        }
    }
}
