package com.reetam.borealis.block.entity;

import com.reetam.borealis.registry.BorealisBlockEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Blocks;

public class KilnRecipe extends AbstractCookingRecipe {
    public KilnRecipe(ResourceLocation pId, String pGroup, CookingBookCategory pCategory, Ingredient pIngredient, ItemStack pResult, float pExperience, int pCookingTime) {
        super(BorealisBlockEntities.KILN_RECIPE.get(), pId, pGroup, pCategory, pIngredient, pResult, pExperience, pCookingTime);
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(Blocks.SMOKER);
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }
}
