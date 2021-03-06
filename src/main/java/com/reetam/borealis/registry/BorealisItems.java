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

    public static final RegistryObject<ArmorItem> tanzanite_helmet = ITEMS.register("tanzanite_helmet",
            () -> new ArmorItem(BorealisTiers.Armor.TANZANITE, EquipmentSlotType.HEAD,
                    new Item.Properties().tab(Groups.BOREALIS_ITEMS)));
    public static final RegistryObject<ArmorItem> tanzanite_chestplate = ITEMS.register("tanzanite_chestplate",
            () -> new ArmorItem(BorealisTiers.Armor.TANZANITE, EquipmentSlotType.CHEST,
                    new Item.Properties().tab(Groups.BOREALIS_ITEMS)));
    public static final RegistryObject<ArmorItem> tanzanite_leggings = ITEMS.register("tanzanite_leggings",
            () -> new ArmorItem(BorealisTiers.Armor.TANZANITE, EquipmentSlotType.LEGS,
                    new Item.Properties().tab(Groups.BOREALIS_ITEMS)));
    public static final RegistryObject<ArmorItem> tanzanite_boots = ITEMS.register("tanzanite_boots",
            () -> new ArmorItem(BorealisTiers.Armor.TANZANITE, EquipmentSlotType.FEET,
                    new Item.Properties().tab(Groups.BOREALIS_ITEMS)));

    public static final RegistryObject<SwordItem> tanzanite_sword = ITEMS.register("tanzanite_sword",
            () -> new SwordItem(BorealisTiers.Tools.TANZANITE, 2, -2.4F,
                    new Item.Properties().tab(Groups.BOREALIS_ITEMS)));
    public static final RegistryObject<AxeItem> tanzanite_axe = ITEMS.register("tanzanite_axe",
            () -> new AxeItem(BorealisTiers.Tools.TANZANITE, 5.5F, -3.0F,
                    new Item.Properties().tab(Groups.BOREALIS_ITEMS)));
    public static final RegistryObject<PickaxeItem> tanzanite_pickaxe = ITEMS.register("tanzanite_pickaxe",
            () -> new PickaxeItem(BorealisTiers.Tools.TANZANITE, 1, -2.9F,
                    new Item.Properties().tab(Groups.BOREALIS_ITEMS)));
    public static final RegistryObject<ShovelItem> tanzanite_shovel = ITEMS.register("tanzanite_shovel",
            () -> new ShovelItem(BorealisTiers.Tools.TANZANITE, 1.5F, -3.0F,
                    new Item.Properties().tab(Groups.BOREALIS_ITEMS)));
    public static final RegistryObject<HoeItem> tanzanite_hoe = ITEMS.register("tanzanite_hoe",
            () -> new HoeItem(BorealisTiers.Tools.TANZANITE, -3, 0.0F,
                    new Item.Properties().tab(Groups.BOREALIS_ITEMS)));
    
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