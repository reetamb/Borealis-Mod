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
            if (!(block.get() instanceof StandingSignBlock ||
                    block.get() instanceof CeilingHangingSignBlock ||
                    block.get() instanceof WallSignBlock ||
                    block.get() instanceof WallHangingSignBlock ||
                    block.get() == BorealisBlocks.WINTER_FIDDLE.get() ||
                    block.get() instanceof WallPlantBlock)) {
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

        addAdvancement("borealis_root", "Borealis", "Enter Borealis");
        addAdvancement("get_hailstone", "Discover a Hailstone", "Find a hailstone in a snowy thunderstorm");
        addAdvancement("shear_plant", "Distant Cousins", "Shear a flower that resembles ancient Overworld crops");
    }
}
