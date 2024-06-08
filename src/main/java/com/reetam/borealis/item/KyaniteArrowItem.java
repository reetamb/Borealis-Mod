package com.reetam.borealis.item;

import com.reetam.borealis.entity.KyaniteArrowEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        if (!pLevel.isClientSide) {
            KyaniteArrowEntity $$4 = KyaniteArrowEntity.fromShooter(pLevel, pPlayer);
            $$4.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 2F, 0.5F);
            pLevel.addFreshEntity($$4);
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            pPlayer.getItemInHand(pHand).shrink(1);
        }

        return super.use(pLevel, pPlayer, pHand);
    }
}
