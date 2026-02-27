package com.reetam.borealis.modify.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.modify.recipe.ThawSmeltingRecipe;
import com.reetam.borealis.registry.BorealisItems;
import com.reetam.borealis.registry.BorealisMenus;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AbstractFurnaceBlockEntity.class)
public class MixinAbstractFurnaceBlockEntity {

    // Cache the fake recipe holder statically
    private static final RecipeHolder<ThawSmeltingRecipe> FAKE_HOLDER = new RecipeHolder<>(
            ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "thaw_smelting"),
            new ThawSmeltingRecipe(BorealisItems.Components.FROZEN.get(), 0.1F, 200));

    @Redirect(
            method = "serverTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/crafting/RecipeManager$CachedCheck;getRecipeFor(Lnet/minecraft/world/item/crafting/RecipeInput;Lnet/minecraft/world/level/Level;)Ljava/util/Optional;"
            )
    )
    private static <I extends RecipeInput, T extends Recipe<I>> Optional<RecipeHolder<T>> redirectGetRecipeFor(
            RecipeManager.CachedCheck<I, T> instance, I input, Level level, @Local(argsOnly = true) AbstractFurnaceBlockEntity furnace) {

        // Fall back to our fake recipe if the input has our component
        ItemStack stack = furnace.getItem(0);
        if (stack.has(BorealisItems.Components.FROZEN)) {
            return Optional.of((RecipeHolder<T>) FAKE_HOLDER);
        }
        return instance.getRecipeFor(input, level);
    }

    @Inject(
            method = "burn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/core/NonNullList;set(ILjava/lang/Object;)Ljava/lang/Object;",
                    ordinal = 0  // first setItem in burn() = writing to output slot
            ),
            cancellable = true
    )
    private static void onBurnOutput(
            RegistryAccess access,
            RecipeHolder<?> recipeHolder,
            NonNullList<ItemStack> items,
            int p_267157_,
            AbstractFurnaceBlockEntity furnace,
            CallbackInfoReturnable<Boolean> cir
    ) {
        ItemStack input = items.getFirst();

        // TODO: generalize to any component of thawing, not just frozen
        if (!input.has(BorealisItems.Components.FROZEN)) return;

        // Build the result: copy the input, remove the component
        ItemStack result = input.copy();
        result.setCount(1);
        result.remove(BorealisItems.Components.FROZEN);

        // Write to output slot
        ItemStack currentOutput = items.get(2);
        if (currentOutput.isEmpty()) {
            items.set(2, result);
        } else if (ItemStack.isSameItemSameComponents(currentOutput, result)) {
            currentOutput.grow(1);
        } else {
            // Output slot is occupied with something incompatible; do nothing
            return;
        }

        // Shrink the input
        input.shrink(1);

        // Reset cooking progress for this slot
        furnace.cookingProgress = 0;

        AbstractFurnaceBlockEntity.invalidateCache();

        // Cancel vanilla's setItem call
        cir.cancel();
    }

    @Inject(
            method = "canPlaceItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onIsItemValid(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (slot == 0 && stack.has(BorealisItems.Components.FROZEN)) {
            cir.setReturnValue(true);
        }
    }
}