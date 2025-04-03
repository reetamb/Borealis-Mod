package com.reetam.borealis.block.entity;

import com.reetam.borealis.registry.BorealisBlockEntities;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Blocks;

public class KilnRecipe extends AbstractCookingRecipe {
    public KilnRecipe(String pGroup, CookingBookCategory pCategory, Ingredient pIngredient, ItemStack pResult, float pExperience, int pCookingTime) {
        super(BorealisBlockEntities.KILN_RECIPE.get(), pGroup, pCategory, pIngredient, pResult, pExperience, pCookingTime);
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(BorealisBlocks.KILN.get());
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }
}
