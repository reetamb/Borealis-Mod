package com.reetam.borealis.data;

import com.reetam.borealis.data.provider.BorealisLangProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import com.reetam.borealis.registry.BorealisPotionEffects;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class BorealisLang extends BorealisLangProvider {

    public BorealisLang(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        creativeTab(BorealisItems.Tabs.BOREALIS_ITEMS, "Borealis Items");

        ArrayList<String> blockNames = new ArrayList<>();
        for (RegistryObject<Block> block : BorealisBlocks.BLOCKS.getEntries()) {
            if (!(block.get() instanceof WallSignBlock)) {
                blockNames.add(assumeBlockItem(block));
            }
        }
        for (RegistryObject<Item> item : BorealisItems.ITEMS.getEntries()) {
            if (!blockNames.contains(item.get().toString())) {
                assumeBlockItem(item);
            }
        }
        add(BorealisPotionEffects.MANIA.get(), "Mania");
    }
}
