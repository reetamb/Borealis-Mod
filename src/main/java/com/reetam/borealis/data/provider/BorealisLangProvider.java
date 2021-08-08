package com.reetam.borealis.data.provider;

import com.reetam.borealis.BorealisMod;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.function.Supplier;

public abstract class BorealisLangProvider extends LanguageProvider {

    public BorealisLangProvider(DataGenerator gen) {
        super(gen, BorealisMod.MODID, "en_us");
    }

    protected void addItemGroup(ItemGroup group, String name) {
        add(group.getDisplayName().getString(), name);
    }

    protected void assumeBlockItem(Supplier<? extends ForgeRegistryEntry<?>> blockOrItem) {
        String name = blockOrItem.get().getRegistryName().toString().substring(BorealisMod.MODID.length() + 1).replace("_", " ");
        String[] words = name.split(" ");
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