package com.reetam.borealis.modify.mixin;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.world.BorealisDimensions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Player.class)
public abstract class MixinPlayer extends LivingEntity {
    protected MixinPlayer(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @ModifyArg(method = "causeFoodExhaustion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;addExhaustion(F)V"))
    public float lessExhaustion(float pExhaustion) {
        if (this.level().dimension() == BorealisDimensions.BOREALIS) {
            return pExhaustion * BorealisMod.HUNGER_FACTOR;
        }
        return pExhaustion;
    }
}


/*
Mixin Player:
    public void causeFoodExhaustion(float pExhaustion) {
        if (!this.abilities.invulnerable) {
            if (!this.level().isClientSide) {
                this.foodData.addExhaustion(pExhaustion);
            }
        }
    }
so that:
    public void causeFoodExhaustion(float pExhaustion) {
        if (!this.abilities.invulnerable) {
            if (!this.level().isClientSide) {
                if (this.level().dimension() == BorealisDimensions.BOREALIS {
                    this.foodData.addExhaustion(pExhaustion / 2);
                    return;
                }
                this.foodData.addExhaustion(pExhaustion);
            }
        }
    }
 */
