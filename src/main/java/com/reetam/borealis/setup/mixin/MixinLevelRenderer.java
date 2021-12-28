package com.reetam.borealis.setup.mixin;

import com.reetam.borealis.registry.BorealisDimensions;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LevelRenderer.class)
public class MixinLevelRenderer {
//    @Shadow
//    private ClientLevel level;
//
//    @ModifyVariable(at = @At(value = "STORE"), method = "renderSky(Lcom/mojang/blaze3d/vertex/PoseStack;F)V", ordinal = 0)
//    private double onRenderSky(double d0) {
//        if (this.level.dimension() == BorealisDimensions.BOREALIS) {
//            return 1.0D;
//        }
//        return d0;
//    }
}
