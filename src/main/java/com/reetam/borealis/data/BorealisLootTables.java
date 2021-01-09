package com.reetam.borealis.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.reetam.borealis.block.*;
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
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.KilledByPlayer;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.functions.ExplosionDecay;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class BorealisLootTables extends LootTableProvider {

    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.inverted();
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();
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
        return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK)/*, Pair.of(Entities::new, LootParameterSets.ENTITY)*/);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
    }

    public static class Blocks extends BorealisLootTableProvider {

        @Override
        protected void addTables() {
            dropSelf(BorealisBlocks.soapstone);
            dropSelf(BorealisBlocks.polished_soapstone);
            dropSelf(BorealisBlocks.porcelain);
            dropSelf(BorealisBlocks.porcelain_tile);
            dropSelf(BorealisBlocks.chiseled_porcelain_tile);
            dropWithFortune(BorealisBlocks.kyanite_ore, BorealisItems.kyanite_crystal);
            dropSelf(BorealisBlocks.soapstone);
            dropSelf(BorealisBlocks.polished_soapstone);
            dropSelf(BorealisBlocks.soapstone_bricks);
            dropSelf(BorealisBlocks.soapstone_stairs);
            dropSelf(BorealisBlocks.polished_soapstone_stairs);
            dropSelf(BorealisBlocks.soapstone_brick_stairs);
            dropSelf(BorealisBlocks.soapstone_slab);
            dropSelf(BorealisBlocks.polished_soapstone_slab);
            dropSelf(BorealisBlocks.soapstone_brick_slab);
            dropSelf(BorealisBlocks.soapstone_wall);
            dropSelf(BorealisBlocks.polished_soapstone_wall);
            dropSelf(BorealisBlocks.soapstone_brick_wall);
            dropSelf(BorealisBlocks.soapstone_button);
            dropSelf(BorealisBlocks.soapstone_pressure_plate);
            dropSelf(BorealisBlocks.slate);
            dropSelf(BorealisBlocks.slate_pillar);
            dropSelf(BorealisBlocks.chiseled_slate_pillar);
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
            dropWithSilk(BorealisBlocks.lichen_block, BorealisBlocks.permafrost);
            dropSelf(BorealisBlocks.permafrost);
            this.registerLootTable(BorealisBlocks.permafrost_rubble.get(),
                    new LootTable.Builder()
                            .addLootPool(new LootPool.Builder()
                                    .addEntry(ItemLootEntry.builder(Items.FLINT).acceptCondition(RandomChance.builder((float) 0.2)))
                                    .addEntry(ItemLootEntry.builder(Items.STICK).acceptCondition(RandomChance.builder((float) 0.5)))
                                    .addEntry(ItemLootEntry.builder(Items.IRON_NUGGET).acceptCondition(RandomChance.builder((float) 0.05)))
                                    .addEntry(ItemLootEntry.builder(Items.ARROW).acceptCondition(RandomChance.builder((float) 0.2)))
                                    .addEntry(ItemLootEntry.builder(Items.FEATHER).acceptCondition(RandomChance.builder((float) 0.5)))
                                    .addEntry(ItemLootEntry.builder(Items.STRING).acceptCondition(RandomChance.builder((float) 0.1)))
                                    .addEntry(ItemLootEntry.builder(Items.COAL).acceptCondition(RandomChance.builder((float) 0.2)))
                                    .addEntry(ItemLootEntry.builder(Items.CHARCOAL).acceptCondition(RandomChance.builder((float) 0.2)))
                                    .addEntry(ItemLootEntry.builder(Items.POISONOUS_POTATO).acceptCondition(RandomChance.builder((float) 0.02)))));
            dropWithSilk(BorealisBlocks.cloud, BorealisBlocks.cloud);

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BorealisBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }

    protected static <T> T withExplosionDecay(IItemProvider item, ILootFunctionConsumer<T> function) {
        return !IMMUNE_TO_EXPLOSIONS.contains(item.asItem()) ? function.acceptFunction(ExplosionDecay.builder()) : function.cast();
    }

    public static class Entities extends EntityLootTables {

        @Override
        protected void addTables() {
            this.registerLootTable(
                    BorealisEntities.HUMMINGBIRD.get(),
                    LootTable.builder().addLootPool
                            (LootPool.builder().rolls(ConstantRange.of(1))
                                    .addEntry(ItemLootEntry.builder(Items.FEATHER)
                                            .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F)))
                                            .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))
                                            .acceptCondition(KilledByPlayer.builder()))));
            this.registerLootTable(
                    BorealisEntities.TAKAHE.get(),
                    LootTable.builder().addLootPool
                            (LootPool.builder().rolls(ConstantRange.of(1))
                                    .addEntry(ItemLootEntry.builder(Items.FEATHER)
                                            .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F)))
                                            .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))
                                    .addEntry(ItemLootEntry.builder(Items.CHICKEN)
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))
                            ));
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return BorealisEntities.ENTITIES.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }
}
