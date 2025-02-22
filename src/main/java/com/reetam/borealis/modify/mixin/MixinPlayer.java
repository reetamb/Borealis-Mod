package com.reetam.borealis.modify.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Player.class)
public abstract class MixinPlayer {
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
