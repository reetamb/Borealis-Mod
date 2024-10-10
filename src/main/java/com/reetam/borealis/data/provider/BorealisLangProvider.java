package com.reetam.borealis.data.provider;

import com.reetam.borealis.BorealisMod;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Arrays;
import java.util.function.Supplier;

public abstract class BorealisLangProvider extends LanguageProvider {

    public BorealisLangProvider(PackOutput output) {
        super(output, BorealisMod.MODID, "en_us");
    }

    protected void creativeTab(Supplier<? extends CreativeModeTab> tab, String name) {
        this.add(tab.get().getDisplayName().getString(), name);
    }
    protected String assumeBlock(DeferredHolder<Block, ? extends Block> block) {
        BorealisMod.LOGGER.debug(block.getRegisteredName());

        String prename;
        String name;
        prename = block.get().getName().getString().substring(15);
        name = prename;
        String[] words = name.split("_");
        StringBuilder newName = new StringBuilder();
        for (String word : words) {
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            newName.append(word);
            newName.append(" ");
        }
        name = newName.toString().trim();
        addBlock(block, name);
        return prename;
    }

    protected String assumeItem(DeferredHolder<Item, ? extends Item> item) {
        String key = item.getRegisteredName().split(":")[1];
        StringBuilder name = new StringBuilder();
        Arrays.stream(key.split("_")).forEach((word) -> name.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" "));
        this.add("item." + BorealisMod.MODID + "." + key, name.toString().trim());
        return name.toString().trim();
    }

    protected void addAdvancement(String key, String name, String desc) {
        this.add("advancement." + BorealisMod.MODID + "." + key, name);
        this.add("advancement." + BorealisMod.MODID + "." + key + ".desc", desc);
    }
}