package com.reetam.borealis.item;

import com.reetam.borealis.entity.TakaheEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HatItem extends Item {
    public HatItem(Properties builder) {
        super(builder);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof TakaheEntity && target.isAlive()) {
            TakaheEntity takahe = (TakaheEntity) target;
            if (!takahe.getHat()) {
                if (!player.level().isClientSide) {
                    takahe.setHat(true);
                    stack.shrink(1);
                }
                return InteractionResult.sidedSuccess(player.level().isClientSide);
            }
        }
        return InteractionResult.PASS;
    }
}
