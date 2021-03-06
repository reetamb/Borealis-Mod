package com.reetam.borealis.item;

import com.reetam.borealis.entity.TakaheEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public class HatItem extends Item {
    public HatItem(Item.Properties builder) {
        super(builder);
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        if (target instanceof TakaheEntity && target.isAlive()) {
            TakaheEntity takahe = (TakaheEntity) target;
            if (!takahe.getHat()) {
                if (!playerIn.level.isClientSide) {
                    takahe.setHat(true);
                    stack.shrink(1);
                }
                return ActionResultType.sidedSuccess(playerIn.level.isClientSide);
            }
        }
        return ActionResultType.PASS;
    }
}
