package com.reetam.borealis.modify.recipe;

import com.reetam.borealis.registry.BorealisMenus;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class ThawSmeltingRecipe extends AbstractCookingRecipe {
    DataComponentType<?> component;

    public ThawSmeltingRecipe(DataComponentType<?> component, float experience, int cookingTime) {
        super(
                BorealisMenus.THAW_SMELTING_RECIPE.get(),
                "thawing",
                CookingBookCategory.FOOD,
                Ingredient.EMPTY,   // ingredient — we handle matching ourselves
                ItemStack.EMPTY,    // result — we handle output ourselves
                experience,               // experience
                cookingTime                // cook time (200 = default smelting time)
        );
        this.component = component;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return input.getItem(0).has(component);
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
        ItemStack result = input.getItem(0).copy();
        result.setCount(1);
        result.remove(component);
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return BorealisMenus.THAW_SMELTING_SERIALIZER.get();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(Blocks.ICE);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public interface Factory<T extends ThawSmeltingRecipe> {
        T create(DataComponentType<?> component, float experience, int cookingTime);
    }
}
