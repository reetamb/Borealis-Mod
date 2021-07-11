package com.reetam.borealis.registry;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.reetam.borealis.item.*;
import com.reetam.borealis.BorealisMod;

public class BorealisItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BorealisMod.MODID);

    public static final RegistryObject<Item> kyanite_crystal = ITEMS.register("kyanite_crystal", BaseItem::new);
    public static final RegistryObject<Item> moon_pearl = ITEMS.register("moon_pearl", MoonPearlItem::new);
    public static final RegistryObject<Item> hot_spring_water_bucket = ITEMS.register("hot_spring_water_bucket", () -> new BucketItem(
            BorealisFluids.hot_spring_water_source, (new Item.Properties()).tab(Groups.BOREALIS_ITEMS).stacksTo(1)));
    public static final RegistryObject<SpawnEggItem> hummingbird_spawn_egg = ITEMS.register(
            "hummingbird_spawn_egg", () -> new SpawnEggItem(BorealisEntities.hummingbird,
                    0x9CE542, 0xFF446D,
                    new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<SpawnEggItem> takahe_spawn_egg = ITEMS.register(
            "takahe_spawn_egg", () -> new SpawnEggItem(BorealisEntities.takahe,
                    0x2F329F, 0x518A65,
                    new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<SpawnEggItem> mismic_muskox_spawn_egg = ITEMS.register(
            "mismic_muskox_spawn_egg", () -> new SpawnEggItem(BorealisEntities.mismic_muskox,
                    0x294F8D, 0x88D2C6,
                    new Item.Properties().tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> hat = ITEMS.register("hat", () -> new HatItem(new Item.Properties().tab(Groups.BOREALIS_ITEMS).stacksTo(1)));

    public static final RegistryObject<Item> tanzanite = ITEMS.register("tanzanite", BaseItem::new);
    
    public static class Groups {
        public static final ItemGroup BOREALIS_ITEMS = new ItemGroup("borealis") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(BorealisItems.kyanite_crystal.get());
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