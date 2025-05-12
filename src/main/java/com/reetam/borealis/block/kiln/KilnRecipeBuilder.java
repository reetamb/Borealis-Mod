package com.reetam.borealis.block.kiln;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class KilnRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final CookingBookCategory bookCategory;
    private final ItemStack result;
    private final Ingredient ingredient;
    private final float experience;
    private final int cookingTime;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;
    private final AbstractCookingRecipe.Factory<?> factory;

    public KilnRecipeBuilder(RecipeCategory category, CookingBookCategory bookCategory, ItemStack result, Ingredient ingredient, float experience, int cookingTime, AbstractCookingRecipe.Factory<?> factory) {
        this.category = category;
        this.bookCategory = bookCategory;
        this.result = result;
        this.ingredient = ingredient;
        this.experience = experience;
        this.cookingTime = cookingTime;
        this.factory = factory;
    }

    public static KilnRecipeBuilder generic(Ingredient input, ItemLike result, RecipeCategory category, CookingBookCategory book, float experience, int cookingTime, AbstractCookingRecipe.Factory<?> factory) {
        return new KilnRecipeBuilder(category, book, new ItemStack(result), input, experience, cookingTime, factory);
    }

    @Override
    public KilnRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result.getItem();
    }

    @Override
    public KilnRecipeBuilder unlockedBy(String criterionName, Criterion<?> criterionTrigger) {
        this.criteria.put(criterionName, criterionTrigger);
        return this;
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        this.ensureValid(id);
        Advancement.Builder advancement$builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(advancement$builder);
        this.criteria.forEach(advancement$builder::addCriterion);
        AbstractCookingRecipe recipe = this.factory.create(Objects.requireNonNullElse(this.group, ""), this.bookCategory, this.ingredient, this.result, this.experience, this.cookingTime);
        recipeOutput.accept(id, recipe, advancement$builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }
}