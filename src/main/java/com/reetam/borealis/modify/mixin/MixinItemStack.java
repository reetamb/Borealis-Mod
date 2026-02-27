package com.reetam.borealis.modify.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {

    @Shadow public abstract <T extends TooltipProvider> void addToTooltip(DataComponentType<T> p_331344_, Item.TooltipContext p_341231_, Consumer<Component> p_331885_, TooltipFlag p_331177_);

    @Inject(
            method = "getHoverName",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetHoverName(CallbackInfoReturnable<Component> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        if (stack.has(BorealisItems.Components.FROZEN)) {
            cir.setReturnValue(
                    Component
                            .translatable("item." + BorealisMod.MODID + ".frozen_food_prefix")
                            .append(stack.getItem().getName(stack))
                            .append(Component.translatable("item." + BorealisMod.MODID + ".frozen_food_suffix"))
            );
        }
    }

    @Inject(
            method = "getTooltipLines",
            at = @At(
                    value = "TAIL",
                    ordinal = 0,
                    target = "Lnet/minecraft/world/item/ItemStack;addToTooltip(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)Lnet/minecraft/world/item/component/TooltipProvider;"
            )
    )
    private void modifyTooltipLines(Item.TooltipContext context, @Nullable Player player, TooltipFlag tooltipFlag, CallbackInfoReturnable<List<Component>> cir, @Local Consumer<Component> consumer) {
        this.addToTooltip(BorealisItems.Components.FROZEN.get(), context, consumer, tooltipFlag);
    }
}
