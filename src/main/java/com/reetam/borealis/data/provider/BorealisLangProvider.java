package com.reetam.borealis.data.provider;

import com.reetam.borealis.BorealisMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public abstract class BorealisLangProvider extends LanguageProvider {

    public BorealisLangProvider(PackOutput output) {
        super(output, BorealisMod.MODID, "en_us");
    }

    protected void creativeTab(Supplier<? extends CreativeModeTab> tab, String name) {
        this.add(tab.get().getDisplayName().getString(), name);
    }
    protected void assumeBlockItem(Supplier<? extends ItemLike> blockOrItem) {
        String name;
        if (blockOrItem.get() instanceof Block block) {
            name = block.getName().getString();
            name = name.substring(15);
        } else {
            name = blockOrItem.get().toString();
        }
        String[] words = name.split("_");
        StringBuilder newName = new StringBuilder();
        for (String word : words) {
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            newName.append(word);
            newName.append(" ");
        }
        name = newName.toString().trim();

        if (blockOrItem.get() instanceof Block) {
            addBlock(() -> ((Block) blockOrItem.get()), name);
        } else if (blockOrItem.get() instanceof Item) {
            addItem(() -> ((Item) blockOrItem.get()), name);
        }
    }
}