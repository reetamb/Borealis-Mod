package com.reetam.borealis.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.reetam.borealis.data.provider.BorealisLootTableProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.*;
import net.minecraft.loot.functions.ExplosionDecay;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.functions.Smelt;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BorealisLootTables extends LootTableProvider {

    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.invert();
    private static final ILootCondition.IBuilder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.or(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.invert();
    private static final Set<Item> IMMUNE_TO_EXPLOSIONS = Stream.of(net.minecraft.block.Blocks.DRAGON_EGG, net.minecraft.block.Blocks.BEACON, net.minecraft.block.Blocks.CONDUIT, net.minecraft.block.Blocks.SKELETON_SKULL, net.minecraft.block.Blocks.WITHER_SKELETON_SKULL, net.minecraft.block.Blocks.PLAYER_HEAD, net.minecraft.block.Blocks.ZOMBIE_HEAD, net.minecraft.block.Blocks.CREEPER_HEAD, net.minecraft.block.Blocks.DRAGON_HEAD, net.minecraft.block.Blocks.SHULKER_BOX, net.minecraft.block.Blocks.BLACK_SHULKER_BOX, net.minecraft.block.Blocks.BLUE_SHULKER_BOX, net.minecraft.block.Blocks.BROWN_SHULKER_BOX, net.minecraft.block.Blocks.CYAN_SHULKER_BOX, net.minecraft.block.Blocks.GRAY_SHULKER_BOX, net.minecraft.block.Blocks.GREEN_SHULKER_BOX, net.minecraft.block.Blocks.LIGHT_BLUE_SHULKER_BOX, net.minecraft.block.Blocks.LIGHT_GRAY_SHULKER_BOX, net.minecraft.block.Blocks.LIME_SHULKER_BOX, net.minecraft.block.Blocks.MAGENTA_SHULKER_BOX, net.minecraft.block.Blocks.ORANGE_SHULKER_BOX, net.minecraft.block.Blocks.PINK_SHULKER_BOX, net.minecraft.block.Blocks.PURPLE_SHULKER_BOX, net.minecraft.block.Blocks.RED_SHULKER_BOX, net.minecraft.block.Blocks.WHITE_SHULKER_BOX, net.minecraft.block.Blocks.YELLOW_SHULKER_BOX).map(IItemProvider::asItem).collect(ImmutableSet.toImmutableSet());
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

    public BorealisLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    public String getName() {
        return "Borealis LootTables";
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK), Pair.of(Entities::new, LootParameterSets.ENTITY));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
    }

    public static class Blocks extends BorealisLootTableProvider {

        @Override
        protected void addTables() {
            dropSelf(BorealisBlocks.SOAPSTONE);
            dropSelf(BorealisBlocks.SOAPSTONE_TILES);
            dropWithFortune(BorealisBlocks.KYANITE_ORE, BorealisItems.KYANITE_CRYSTAL);
            dropWithFortune(BorealisBlocks.TANZANITE_ORE, BorealisItems.TANZANITE);
            dropSelf(BorealisBlocks.TANZANITE_BLOCK);
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
            dropSelf(BorealisBlocks.FROSTFIR_SIGN);
            dropOther(BorealisBlocks.FROSTFIR_WALL_SIGN, () -> BorealisBlocks.FROSTFIR_SIGN.get().asItem());
            dropSelf(BorealisBlocks.SACCHARINE_PLANKS);
            dropSelf(BorealisBlocks.SACCHARINE_LOG);
            dropSelf(BorealisBlocks.saccharine_wood);
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
            dropSelf(BorealisBlocks.SACCHARINE_SIGN);
            dropOther(BorealisBlocks.SACCHARINE_WALL_SIGN, () -> BorealisBlocks.SACCHARINE_SIGN.get().asItem());
            dropWithSilk(BorealisBlocks.LIVING_SNOW_BLOCK, () -> net.minecraft.block.Blocks.SNOW_BLOCK);
            dropSelf(BorealisBlocks.SUGAR_SNOW_BLOCK);
            dropSelf(BorealisBlocks.PERMAFROST);
            dropSelf(BorealisBlocks.TRAVERTINE);
            this.add(BorealisBlocks.PERMAFROST_RUBBLE.get(),
                    new LootTable.Builder()
                            .withPool(new LootPool.Builder()
                                    .add(ItemLootEntry.lootTableItem(Items.FLINT).when(RandomChance.randomChance((float) 0.2)))
                                    .add(ItemLootEntry.lootTableItem(Items.STICK).when(RandomChance.randomChance((float) 0.5)))
                                    .add(ItemLootEntry.lootTableItem(Items.IRON_NUGGET).when(RandomChance.randomChance((float) 0.05)))
                                    .add(ItemLootEntry.lootTableItem(Items.ARROW).when(RandomChance.randomChance((float) 0.2)))
                                    .add(ItemLootEntry.lootTableItem(Items.FEATHER).when(RandomChance.randomChance((float) 0.5)))
                                    .add(ItemLootEntry.lootTableItem(Items.STRING).when(RandomChance.randomChance((float) 0.1)))
                                    .add(ItemLootEntry.lootTableItem(Items.COAL).when(RandomChance.randomChance((float) 0.2)))
                                    .add(ItemLootEntry.lootTableItem(Items.CHARCOAL).when(RandomChance.randomChance((float) 0.2)))
                                    .add(ItemLootEntry.lootTableItem(Items.POISONOUS_POTATO).when(RandomChance.randomChance((float) 0.02)))));
            dropWithSilk(BorealisBlocks.CLOUD, BorealisBlocks.CLOUD);
            this.add(BorealisBlocks.SUGAR_SNOW.get(),
                    new LootTable.Builder()
                            .withPool(new LootPool.Builder()
                                .add(ItemLootEntry.lootTableItem(Items.SUGAR).when(RandomChance.randomChance(1.0F)))
                                .add(ItemLootEntry.lootTableItem(Items.SNOWBALL).when(RandomChance.randomChance(1.0F)))));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BorealisBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }

    protected static <T> T withExplosionDecay(IItemProvider item, ILootFunctionConsumer<T> function) {
        return !IMMUNE_TO_EXPLOSIONS.contains(item.asItem()) ? function.apply(ExplosionDecay.explosionDecay()) : function.unwrap();
    }

    public static class Entities extends EntityLootTables {

        @Override
        protected void addTables() {
            this.add(
                    BorealisEntities.HUMMINGBIRD.get(),
                    LootTable.lootTable().withPool
                            (LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                                    .add(ItemLootEntry.lootTableItem(Items.FEATHER)
                                            .apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
                                            .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                                            .when(KilledByPlayer.killedByPlayer()))));
            this.add(
                    BorealisEntities.TAKAHE.get(),
                    LootTable.lootTable().withPool
                            (LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                                    .add(ItemLootEntry.lootTableItem(Items.FEATHER)
                                            .apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
                                            .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                    .add(ItemLootEntry.lootTableItem(Items.CHICKEN)
                                        .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                            ));
            this.add(
                    BorealisEntities.MISMIC_MUSKOX.get(),
                    LootTable.lootTable().withPool
                            (LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                                    .add(ItemLootEntry.lootTableItem(net.minecraft.block.Blocks.WHITE_WOOL.asItem())
                                            .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                                    .add(ItemLootEntry.lootTableItem(Items.BEEF)
                                            .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
                                            .apply(Smelt.smelted().when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
                                            .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                            ));
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return BorealisEntities.ENTITIES.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }
}
