package com.reetam.borealis.registry;

import com.reetam.borealis.entity.BorealisBoatEntity;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.reetam.borealis.item.*;
import com.reetam.borealis.BorealisMod;

public class BorealisItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BorealisMod.MODID);

    public static final RegistryObject<Item> KYANITE_CRYSTAL = ITEMS.register("kyanite_crystal", BaseItem::new);
    public static final RegistryObject<Item> MOON_PEARL = ITEMS.register("moon_pearl", MoonPearlItem::new);
    public static final RegistryObject<Item> HOT_SPRING_WATER_BUCKET = ITEMS.register("hot_spring_water_bucket", () -> new BucketItem(
            BorealisFluids.hot_spring_water_source, (new Item.Properties()).tab(Groups.BOREALIS_ITEMS).stacksTo(1)));

    public static final RegistryObject<Item> BRUMAL_BOAT = ITEMS.register("brumal_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.BRUMAL, (new Item.Properties()).tab(Groups.BOREALIS_ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> FROSTFIR_BOAT = ITEMS.register("frostfir_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.FROSTFIR, (new Item.Properties()).tab(Groups.BOREALIS_ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> SACCHARINE_BOAT = ITEMS.register("saccharine_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.SACCHARINE, (new Item.Properties()).tab(Groups.BOREALIS_ITEMS).stacksTo(1)));

    public static final RegistryObject<SignItem> BRUMAL_SIGN = ITEMS.register("brumal_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(ItemGroup.TAB_DECORATIONS), BorealisBlocks.BRUMAL_SIGN.get(), BorealisBlocks.BRUMAL_WALL_SIGN.get()));
    public static final RegistryObject<SignItem> FROSTFIR_SIGN = ITEMS.register("frostfir_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(ItemGroup.TAB_DECORATIONS), BorealisBlocks.FROSTFIR_SIGN.get(), BorealisBlocks.FROSTFIR_WALL_SIGN.get()));
    public static final RegistryObject<SignItem> SACCHARINE_SIGN = ITEMS.register("saccharine_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(ItemGroup.TAB_DECORATIONS), BorealisBlocks.SACCHARINE_SIGN.get(), BorealisBlocks.SACCHARINE_WALL_SIGN.get()));

    public static final RegistryObject<SpawnEggItem> HUMMINGBIRD_SPAWN_EGG = ITEMS.register(
            "hummingbird_spawn_egg", () -> new SpawnEggItem(BorealisEntities.HUMMINGBIRD_TYPE,
                    0x9CE542, 0xFF446D,
                    new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<SpawnEggItem> TAKAHE_SPAWN_EGG = ITEMS.register(
            "takahe_spawn_egg", () -> new SpawnEggItem(BorealisEntities.TAKAHE_TYPE,
                    0x2F329F, 0x518A65,
                    new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<SpawnEggItem> MISMIC_MUSKOX_SPAWN_EGG = ITEMS.register(
            "mismic_muskox_spawn_egg", () -> new SpawnEggItem(BorealisEntities.MISMIC_MUSKOX_TYPE,
                    0x294F8D, 0x88D2C6,
                    new Item.Properties().tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> HAT = ITEMS.register("hat", () -> new HatItem(new Item.Properties().tab(Groups.BOREALIS_ITEMS).stacksTo(1)));

    public static final RegistryObject<Item> TANZANITE = ITEMS.register("tanzanite", BaseItem::new);
    
    public static class Groups {
        public static final ItemGroup BOREALIS_ITEMS = new ItemGroup("borealis") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(BorealisItems.KYANITE_CRYSTAL.get());
            }
        };
    }

    public static class BaseItem extends Item {

        public BaseItem() {
            super(new Properties()
                    .tab(BorealisItems.Groups.BOREALIS_ITEMS));
        }
    }
}