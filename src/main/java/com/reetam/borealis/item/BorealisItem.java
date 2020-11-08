package com.reetam.borealis.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import com.reetam.borealis.registry.BorealisItemGroups;

import net.minecraft.item.Item.Properties;

public class BorealisItem extends Item {

    public BorealisItem() {
        super(new Properties()
                .group(BorealisItemGroups.ITEMS_GROUP));
    }

    public BorealisItem(Rarity rarity) {
        super(new Properties()
                .group(BorealisItemGroups.BLOCKS_GROUP)
                .rarity(rarity)
        );
    }

    public BorealisItem(Food food) {
        super(new Properties()
                .food(food)
                .group(BorealisItemGroups.BLOCKS_GROUP));
    }
}