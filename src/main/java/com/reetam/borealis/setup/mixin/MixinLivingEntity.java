package com.reetam.borealis.setup.mixin;

import com.reetam.borealis.registry.BorealisPotionEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Inject(at = @At(value = "HEAD"), method= "getFrictionInfluencedSpeed(F)F", cancellable = true)
    private void getFrictionInfluencedSpeed(CallbackInfoReturnable<Float> friction) {
//        LivingEntity entity = (LivingEntity) (Object) this;
//        if (entity.hasEffect(BorealisPotionEffects.MANIA.get()) && entity.onGround()) {
//            friction.setReturnValue(entity.getSpeed() * 1.1F);
//        }
    }
}
