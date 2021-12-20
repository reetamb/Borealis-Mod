package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.BorealisBoatEntity;
import com.reetam.borealis.item.BorealisBoatItem;
import com.reetam.borealis.item.HatItem;
import com.reetam.borealis.item.MoonPearlItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BorealisItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BorealisMod.MODID);

    public static final RegistryObject<Item> KYANITE_CRYSTAL = ITEMS.register("kyanite_crystal", BaseItem::new);
    public static final RegistryObject<Item> MOON_PEARL = ITEMS.register("moon_pearl", MoonPearlItem::new);
    public static final RegistryObject<Item> HOT_SPRING_WATER_BUCKET = ITEMS.register("hot_spring_water_bucket", () -> new BucketItem(
            BorealisFluids.HOT_SPRING_WATER_SOURCE, (new Item.Properties()).tab(Groups.BOREALIS_ITEMS).stacksTo(1)));

    public static final RegistryObject<Item> BRUMAL_BOAT = ITEMS.register("brumal_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.BRUMAL, (new Item.Properties()).tab(Groups.BOREALIS_ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> FROSTFIR_BOAT = ITEMS.register("frostfir_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.FROSTFIR, (new Item.Properties()).tab(Groups.BOREALIS_ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> SACCHARINE_BOAT = ITEMS.register("saccharine_boat", () -> new BorealisBoatItem(BorealisBoatEntity.Type.SACCHARINE, (new Item.Properties()).tab(Groups.BOREALIS_ITEMS).stacksTo(1)));

    public static final RegistryObject<SignItem> BRUMAL_SIGN = ITEMS.register("brumal_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTab.TAB_DECORATIONS), BorealisBlocks.BRUMAL_SIGN.get(), BorealisBlocks.BRUMAL_WALL_SIGN.get()));
    public static final RegistryObject<SignItem> FROSTFIR_SIGN = ITEMS.register("frostfir_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTab.TAB_DECORATIONS), BorealisBlocks.FROSTFIR_SIGN.get(), BorealisBlocks.FROSTFIR_WALL_SIGN.get()));
    public static final RegistryObject<SignItem> SACCHARINE_SIGN = ITEMS.register("saccharine_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTab.TAB_DECORATIONS), BorealisBlocks.SACCHARINE_SIGN.get(), BorealisBlocks.SACCHARINE_WALL_SIGN.get()));

    public static final RegistryObject<SpawnEggItem> HUMMINGBIRD_SPAWN_EGG = ITEMS.register(
            "hummingbird_spawn_egg", () -> new SpawnEggItem(BorealisEntities.HUMMINGBIRD_TYPE,
                    0x9CE542, 0xFF446D,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<SpawnEggItem> TAKAHE_SPAWN_EGG = ITEMS.register(
            "takahe_spawn_egg", () -> new SpawnEggItem(BorealisEntities.TAKAHE_TYPE,
                    0x2F329F, 0x518A65,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> HAT = ITEMS.register("hat", () -> new HatItem(new Item.Properties().tab(Groups.BOREALIS_ITEMS).stacksTo(1)));

    public static final RegistryObject<Item> TANZANITE = ITEMS.register("tanzanite", BaseItem::new);
    
    public static class Groups {
        public static final CreativeModeTab BOREALIS_ITEMS = new CreativeModeTab("borealis") {
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

    public static void registerDispenserBehaviors() {
        final DefaultDispenseItemBehavior eggBehavior = new DefaultDispenseItemBehavior() {
            public ItemStack execute(BlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem)stack.getItem()).getType(stack.getTag());
                type.spawn(source.getLevel(), stack, null, source.getPos().relative(direction), MobSpawnType.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);
                return stack;
            }
        };

        DispenseItemBehavior bucketBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(BlockSource source, ItemStack stack) {
                BucketItem bucketitem = (BucketItem)stack.getItem();
                BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                Level level = source.getLevel();
                if (bucketitem.emptyContents(null, level, blockpos, null)) {
                    bucketitem.checkExtraContent(null, level, stack, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultBehavior.dispense(source, stack);
                }
            }
        };

        DispenserBlock.registerBehavior(HUMMINGBIRD_SPAWN_EGG.get(), eggBehavior);
        DispenserBlock.registerBehavior(TAKAHE_SPAWN_EGG.get(), eggBehavior);

        DispenserBlock.registerBehavior(HOT_SPRING_WATER_BUCKET.get(), bucketBehavior);
    }
}