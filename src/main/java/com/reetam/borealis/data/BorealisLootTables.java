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
import net.minecraft.item.ItemStack;
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
            dropSelf(BorealisBlocks.soapstone);
            dropSelf(BorealisBlocks.soapstone_tiles);
            dropWithFortune(BorealisBlocks.kyanite_ore, BorealisItems.kyanite_crystal);
            dropWithFortune(BorealisBlocks.tanzanite_ore, BorealisItems.tanzanite);
            dropSelf(BorealisBlocks.tanzanite_block);
            dropSelf(BorealisBlocks.soapstone);
            dropSelf(BorealisBlocks.soapstone_tiles);
            dropSelf(BorealisBlocks.soapstone_bricks);
            dropSelf(BorealisBlocks.soapstone_stairs);
            dropSelf(BorealisBlocks.soapstone_tile_stairs);
            dropSelf(BorealisBlocks.soapstone_brick_stairs);
            dropSelf(BorealisBlocks.soapstone_slab);
            dropSelf(BorealisBlocks.soapstone_tile_slab);
            dropSelf(BorealisBlocks.soapstone_brick_slab);
            dropSelf(BorealisBlocks.soapstone_wall);
            dropSelf(BorealisBlocks.soapstone_tile_wall);
            dropSelf(BorealisBlocks.soapstone_brick_wall);
            dropSelf(BorealisBlocks.soapstone_button);
            dropSelf(BorealisBlocks.soapstone_pressure_plate);
            dropSelf(BorealisBlocks.slate);
            dropSelf(BorealisBlocks.slate_pillar);
            dropSelf(BorealisBlocks.slate_tiles);
            dropSelf(BorealisBlocks.pumice);
            dropSelf(BorealisBlocks.pumice_geyser);
            dropSelf(BorealisBlocks.brumal_planks);
            dropSelf(BorealisBlocks.brumal_log);
            dropSelf(BorealisBlocks.brumal_wood);
            dropSelf(BorealisBlocks.stripped_brumal_log);
            dropSelf(BorealisBlocks.stripped_brumal_wood);
            dropSelf(BorealisBlocks.brumal_leaves);
            dropSelf(BorealisBlocks.brumal_stairs);
            dropSelf(BorealisBlocks.brumal_slab);
            dropSelf(BorealisBlocks.brumal_fence);
            dropSelf(BorealisBlocks.brumal_fence_gate);
            dropSelf(BorealisBlocks.brumal_door);
            dropSelf(BorealisBlocks.brumal_trapdoor);
            dropSelf(BorealisBlocks.brumal_button);
            dropSelf(BorealisBlocks.brumal_pressure_plate);
            dropSelf(BorealisBlocks.frostfir_planks);
            dropSelf(BorealisBlocks.frostfir_log);
            dropSelf(BorealisBlocks.frostfir_wood);
            dropSelf(BorealisBlocks.stripped_frostfir_log);
            dropSelf(BorealisBlocks.stripped_frostfir_wood);
            dropSelf(BorealisBlocks.frostfir_leaves);
            dropSelf(BorealisBlocks.frostfir_stairs);
            dropSelf(BorealisBlocks.frostfir_slab);
            dropSelf(BorealisBlocks.frostfir_fence);
            dropSelf(BorealisBlocks.frostfir_fence_gate);
            dropSelf(BorealisBlocks.frostfir_door);
            dropSelf(BorealisBlocks.frostfir_trapdoor);
            dropSelf(BorealisBlocks.frostfir_button);
            dropSelf(BorealisBlocks.frostfir_pressure_plate);
            dropSelf(BorealisBlocks.saccharine_planks);
            dropSelf(BorealisBlocks.saccharine_log);
            dropSelf(BorealisBlocks.saccharine_wood);
            dropSelf(BorealisBlocks.stripped_saccharine_log);
            dropSelf(BorealisBlocks.saccharine_leaves);
            dropSelf(BorealisBlocks.saccharine_stairs);
            dropSelf(BorealisBlocks.saccharine_slab);
            dropSelf(BorealisBlocks.stripped_saccharine_wood);
            dropSelf(BorealisBlocks.saccharine_fence);
            dropSelf(BorealisBlocks.saccharine_fence_gate);
            dropSelf(BorealisBlocks.saccharine_door);
            dropSelf(BorealisBlocks.saccharine_trapdoor);
            dropSelf(BorealisBlocks.saccharine_button);
            dropSelf(BorealisBlocks.saccharine_pressure_plate);
            dropWithSilk(BorealisBlocks.living_snow_block, () -> net.minecraft.block.Blocks.SNOW_BLOCK);
            dropSelf(BorealisBlocks.sugar_snow_block);
            dropSelf(BorealisBlocks.permafrost);
            dropSelf(BorealisBlocks.travertine);
            this.add(BorealisBlocks.permafrost_rubble.get(),
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
            dropWithSilk(BorealisBlocks.cloud, BorealisBlocks.cloud);
            this.add(BorealisBlocks.sugar_snow.get(),
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
