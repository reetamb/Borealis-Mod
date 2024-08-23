package com.reetam.borealis.modify.mixin;

import com.reetam.borealis.registry.world.BorealisWorld;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LevelRenderer.class)
public class MixinLevelRenderer {
    @Shadow
    private ClientLevel level;

    @ModifyVariable(at = @At(value = "HEAD"), method = "renderSky", ordinal = 0)
    private LevelRenderer borealisRenderSky(LevelRenderer value) {
        if (this.level.dimension() == BorealisWorld.BOREALIS_LEVEL) {
            return value;
        }
        return value;
    }
}
