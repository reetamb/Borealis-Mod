package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.BorealisBoatEntity;
import com.reetam.borealis.item.*;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BorealisItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, BorealisMod.MODID);

    public static final DeferredHolder<Item, Item> KYANITE_CRYSTAL = ITEMS.register("kyanite_crystal", () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> BRUMAL_BOAT = ITEMS.register("brumal_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.BRUMAL, (new Item.Properties()).stacksTo(1)));
    public static final DeferredHolder<Item, Item> FROSTFIR_BOAT = ITEMS.register("frostfir_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.FROSTFIR, (new Item.Properties()).stacksTo(1)));
    public static final DeferredHolder<Item, Item> SWEETWOOD_BOAT = ITEMS.register("sweetwood_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.SWEETWOOD, (new Item.Properties()).stacksTo(1)));
    public static final DeferredHolder<Item, Item> CARAMELIZED_BOAT = ITEMS.register("caramelized_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.CARAMELIZED, (new Item.Properties()).stacksTo(1)));

    public static final DeferredHolder<Item, SignItem> BRUMAL_SIGN = ITEMS.register("brumal_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), BorealisBlocks.BRUMAL_SIGN.get(), BorealisBlocks.BRUMAL_WALL_SIGN.get()));
    public static final DeferredHolder<Item, SignItem> FROSTFIR_SIGN = ITEMS.register("frostfir_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), BorealisBlocks.FROSTFIR_SIGN.get(), BorealisBlocks.FROSTFIR_WALL_SIGN.get()));
    public static final DeferredHolder<Item, SignItem> SWEETWOOD_SIGN = ITEMS.register("sweetwood_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), BorealisBlocks.SWEETWOOD_SIGN.get(), BorealisBlocks.SWEETWOOD_WALL_SIGN.get()));
    public static final DeferredHolder<Item, SignItem> CARAMELIZED_SIGN = ITEMS.register("caramelized_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), BorealisBlocks.CARAMELIZED_SIGN.get(), BorealisBlocks.CARAMELIZED_WALL_SIGN.get()));

    public static final DeferredHolder<Item, HangingSignItem> BRUMAL_HANGING_SIGN = ITEMS.register("brumal_hanging_sign", () -> new HangingSignItem(BorealisBlocks.BRUMAL_HANGING_SIGN.get(), BorealisBlocks.BRUMAL_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, HangingSignItem> FROSTFIR_HANGING_SIGN = ITEMS.register("frostfir_hanging_sign", () -> new HangingSignItem(BorealisBlocks.FROSTFIR_HANGING_SIGN.get(), BorealisBlocks.FROSTFIR_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, HangingSignItem> SWEETWOOD_HANGING_SIGN = ITEMS.register("sweetwood_hanging_sign", () -> new HangingSignItem(BorealisBlocks.SWEETWOOD_HANGING_SIGN.get(), BorealisBlocks.SWEETWOOD_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, HangingSignItem> CARAMELIZED_HANGING_SIGN = ITEMS.register("caramelized_hanging_sign", () -> new HangingSignItem(BorealisBlocks.CARAMELIZED_HANGING_SIGN.get(), BorealisBlocks.CARAMELIZED_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final DeferredHolder<Item, SpawnEggItem> HUMMINGBIRD_SPAWN_EGG = ITEMS.register(
            "hummingbird_spawn_egg", () -> new SpawnEggItem(BorealisEntities.HUMMINGBIRD.get(),
                    0x9CE542, 0xFF446D,
                    new Item.Properties()));
    public static final DeferredHolder<Item, SpawnEggItem> TAKAHE_SPAWN_EGG = ITEMS.register(
            "takahe_spawn_egg", () -> new SpawnEggItem(BorealisEntities.TAKAHE.get(),
                    0x2F329F, 0x518A65,
                    new Item.Properties()));
    public static final DeferredHolder<Item, SpawnEggItem> THRUSHER_SPAWN_EGG = ITEMS.register(
            "thrusher_spawn_egg", () -> new SpawnEggItem(BorealisEntities.THRUSHER.get(),
                    0xD8EBFF, 0x32749E, new Item.Properties()));

    public static final DeferredHolder<Item, Item> HAT = ITEMS.register("hat", () -> new HatItem(new Item.Properties().stacksTo(1)));

    public static final DeferredHolder<Item, Item> TANZANITE = ITEMS.register("tanzanite", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> HAILSTONE = ITEMS.register("hailstone", HailstoneItem::new);

    public static final DeferredHolder<Item, Item> STARBURST = ITEMS.register("starburst", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder()
                    .nutrition(2)
                    .alwaysEdible()
                    .saturationModifier(2.0F)
                    .effect(() -> new MobEffectInstance(BorealisEffects.MANIA, 200), 1).build())));

    public static final DeferredHolder<Item, Item> BLUE_AMBER = ITEMS.register("blue_amber", () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> KYANITE_ARROW = ITEMS.register("kyanite_arrow", () -> new KyaniteArrowItem(new Item.Properties()));

    public static final DeferredHolder<Item, FleeceItem> FLEECE = ITEMS.register("fleece", () -> new FleeceItem(new Item.Properties()));

    public static final DeferredHolder<Item, StandingAndWallBlockItem> WINTER_FIDDLE = ITEMS.register("winter_fiddle", () -> new StandingAndWallBlockItem(BorealisBlocks.WINTER_FIDDLE.get(), BorealisBlocks.WALL_WINTER_FIDDLE.get(), new Item.Properties(), Direction.DOWN));
    public static final DeferredHolder<Item, Item> ALMS_NUT = ITEMS.register("alms_nut", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationModifier(1.25F).build())));
    public static class Tabs {

        public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BorealisMod.MODID);

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BOREALIS_ITEMS = TABS.register("borealis", () -> CreativeModeTab.builder()
                .icon(() -> new ItemStack(KYANITE_CRYSTAL.get()))
                .title(Component.translatable("itemGroup." + BorealisMod.MODID))
                .displayItems((features, output) -> {
                    for (DeferredHolder<Item, ? extends Item> item : ITEMS.getEntries()) {
                        output.accept(item.get());
                    }
                }).displayItems((features, output) -> {
                    for (Supplier<? extends Item> item : ITEMS.getEntries().stream().toList()) {
                        output.accept(item.get());
                    }
                }).build());
    }
}