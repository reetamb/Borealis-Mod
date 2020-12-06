package com.reetam.borealis.registry;

import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.reetam.borealis.item.*;
import com.reetam.borealis.BorealisMod;

public class BorealisItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BorealisMod.MODID);

    public static final RegistryObject<Item> kyanite_crystal = ITEMS.register("kyanite_crystal", BorealisItem::new);
    public static final RegistryObject<Item> dry_ice = ITEMS.register("dry_ice", DryIceItem::new);
}