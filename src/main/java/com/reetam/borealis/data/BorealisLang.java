package com.reetam.borealis.data;

import com.reetam.borealis.block.WallPlantBlock;
import com.reetam.borealis.data.provider.BorealisLangProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;
import com.reetam.borealis.registry.BorealisItems;
import com.reetam.borealis.registry.BorealisEffects;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;

public class BorealisLang extends BorealisLangProvider {

    public BorealisLang(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        creativeTab(BorealisItems.Tabs.BOREALIS_ITEMS, "Borealis Items");

        ArrayList<String> blockNames = new ArrayList<>();
        for (DeferredHolder<Block, ? extends Block> block : BorealisBlocks.BLOCKS.getEntries()) {
            if (!(blockNames.contains(block.get().getName().getString().substring(15)))) {
                blockNames.add(assumeBlock(block));
            }
        }
        for (DeferredHolder<Item, ? extends Item> item : BorealisItems.ITEMS.getEntries()) {
            if (!blockNames.contains(item.get().toString())) {
                assumeItem(item);
            }
        }

        add(BorealisEffects.MANIA.get(), "Mania");
        add(BorealisEffects.STATIC.get(), "Static");

        add(BorealisEntities.HAIL.get(), "Hailstone");
        add(BorealisEntities.BOAT.get(), "Borealis Boat");
        add(BorealisEntities.HUMMINGBIRD.get(), "Hummingbird");
        add(BorealisEntities.THRUSHER.get(), "Thrusher");
        add(BorealisEntities.TAKAHE.get(), "Takahe");

        addAdvancement("borealis_root", "Borealis", "Discover an untouched world");
        addAdvancement("enter_borealis", "Enter Borealis", "Use a hailstone above build height");
        addAdvancement("get_hailstone", "Hail-o There", "Find a hailstone in a snowy thunderstorm");
        addAdvancement("icarian", "Icarian", "Save yourself from burning up in the stratosphere");
        addAdvancement("make_portal", "Warm Welcome Home", "Build a portal back home with Kyanite Cable");
        addAdvancement("get_kyanite", "Bottom of the Beryl", "Mine a fibrous crystal");
        addAdvancement("shear_plant", "Distant Relatives", "Gather a prehistoric plant");
        addAdvancement("strip_frostfir", "Woodn't You Like To Know", "Try chopping a Frostfir tree to no avail");
        addAdvancement("break_pumice", "Splat", "Break your fall on some Pumice");
        addAdvancement("walk_geyser", "Up, Up, and Away!", "Get launched up by a Geyser");
        addAdvancement("walk_kaolin", "Soothing", "Let Hot Spring Water dissolve Gypsum into a softer form");

    }
}
