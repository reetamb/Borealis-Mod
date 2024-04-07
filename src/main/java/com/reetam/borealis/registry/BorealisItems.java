package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.BorealisBoatEntity;
import com.reetam.borealis.item.BorealisBoatItem;
import com.reetam.borealis.item.HatItem;
import com.reetam.borealis.item.MoonPearlItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.function.Supplier;

public class BorealisItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BorealisMod.MODID);

    public static final RegistryObject<Item> KYANITE_CRYSTAL = ITEMS.register("kyanite_crystal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOON_PEARL = ITEMS.register("moon_pearl", MoonPearlItem::new);
    public static final RegistryObject<Item> HOT_SPRING_WATER_BUCKET = ITEMS.register("hot_spring_water_bucket", () -> new BucketItem(
            BorealisFluids.HOT_SPRING_WATER_SOURCE, (new Item.Properties()).stacksTo(1)));

    public static final RegistryObject<Item> BRUMAL_BOAT = ITEMS.register("brumal_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.BRUMAL, (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> FROSTFIR_BOAT = ITEMS.register("frostfir_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.FROSTFIR, (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> SACCHARINE_BOAT = ITEMS.register("saccharine_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.SACCHARINE, (new Item.Properties()).stacksTo(1)));

    public static final RegistryObject<SignItem> BRUMAL_SIGN = ITEMS.register("brumal_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), BorealisBlocks.BRUMAL_SIGN.get(), BorealisBlocks.BRUMAL_WALL_SIGN.get()));
    public static final RegistryObject<SignItem> FROSTFIR_SIGN = ITEMS.register("frostfir_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), BorealisBlocks.FROSTFIR_SIGN.get(), BorealisBlocks.FROSTFIR_WALL_SIGN.get()));
    public static final RegistryObject<SignItem> SACCHARINE_SIGN = ITEMS.register("saccharine_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), BorealisBlocks.SACCHARINE_SIGN.get(), BorealisBlocks.SACCHARINE_WALL_SIGN.get()));

    public static final RegistryObject<SpawnEggItem> HUMMINGBIRD_SPAWN_EGG = ITEMS.register(
            "hummingbird_spawn_egg", () -> new ForgeSpawnEggItem(BorealisEntities.HUMMINGBIRD,
                    0x9CE542, 0xFF446D,
                    new Item.Properties()));
    public static final RegistryObject<SpawnEggItem> TAKAHE_SPAWN_EGG = ITEMS.register(
            "takahe_spawn_egg", () -> new ForgeSpawnEggItem(BorealisEntities.TAKAHE,
                    0x2F329F, 0x518A65,
                    new Item.Properties()));
    public static final RegistryObject<SpawnEggItem> THRUSHER_SPAWN_EGG = ITEMS.register(
            "thrusher_spawn_egg", () -> new ForgeSpawnEggItem(BorealisEntities.THRUSHER,
                    0xD8EBFF, 0x32749E, new Item.Properties()));

    public static final RegistryObject<Item> HAT = ITEMS.register("hat", () -> new HatItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> TANZANITE = ITEMS.register("tanzanite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HAILSTONE = ITEMS.register("hailstone", MoonPearlItem::new);

    public static final RegistryObject<Item> STARBURST = ITEMS.register("starburst", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder()
                    .nutrition(2)
                    .alwaysEat()
                    .saturationMod(2.0F)
                    .effect(() -> new MobEffectInstance(BorealisPotionEffects.MANIA.get(), 200), 1).build())));

    public static final RegistryObject<Item> BLUE_AMBER = ITEMS.register("blue_amber", () -> new Item(new Item.Properties()));
    public static class Tabs {

        public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BorealisMod.MODID);

        public static final RegistryObject<CreativeModeTab> BOREALIS_ITEMS = TABS.register("borealis", () -> CreativeModeTab.builder()
                .icon(() -> new ItemStack(KYANITE_CRYSTAL.get()))
                .title(Component.translatable("itemGroup." + BorealisMod.MODID))
                .displayItems((features, output) -> {
                    for (RegistryObject<Item> item : ITEMS.getEntries()) {
                        output.accept(item.get());
                    }
                }).displayItems((features, output) -> {
                    for (Supplier<Item> item : ITEMS.getEntries().stream().toList()) {
                        output.accept(item.get());
                    }
                }).build());
    }
}