package com.reetam.borealis.item;

import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class SilverToolItem extends TieredItem {
    public SilverToolItem(Properties properties) {
        super(BorealisItems.Tiers.SILVER, properties.setNoRepair().attributes(createAttributes(0F, 1.6F)));
    }

    public static ItemAttributeModifiers createAttributes(float damage, float speed) {
        return SwordItem.createAttributes(BorealisItems.Tiers.SILVER, damage, speed);
    }
}
