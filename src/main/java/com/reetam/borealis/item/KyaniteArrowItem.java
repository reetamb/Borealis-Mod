package com.reetam.borealis.item;

import com.reetam.borealis.entity.KyaniteArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class KyaniteArrowItem extends ArrowItem {
    public KyaniteArrowItem(Properties pProperties) {
        super(pProperties);
    }

    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        return KyaniteArrowEntity.fromShooter(pLevel, pShooter);
    }
}
