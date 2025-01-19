package com.reetam.borealis.item;

import com.reetam.borealis.entity.KyaniteArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class KyaniteArrowItem extends ArrowItem {
    public KyaniteArrowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pAmmo, LivingEntity pShooter, @Nullable ItemStack pWeapon) {
        return KyaniteArrowEntity.fromShooter(pLevel, pShooter);
    }
}
