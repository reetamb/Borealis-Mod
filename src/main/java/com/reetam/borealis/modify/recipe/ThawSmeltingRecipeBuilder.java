package com.reetam.borealis.modify.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ThawSmeltingRecipeBuilder implements RecipeBuilder {
    private final DataComponentType<?> component;
    private final float experience;
    private final int cookingTime;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private final ThawSmeltingRecipe.Factory<?> factory;

    public ThawSmeltingRecipeBuilder(DataComponentType<?> component, float experience, int cookingTime, ThawSmeltingRecipe.Factory<?> factory) {
        this.component = component;
        this.experience = experience;
        this.cookingTime = cookingTime;
        this.factory = factory;
    }

    public static ThawSmeltingRecipeBuilder generic(DataComponentType<?> component, float experience, int cookingTime, ThawSmeltingRecipe.Factory<?> factory) {
        return new ThawSmeltingRecipeBuilder(component, experience, cookingTime, factory);
    }

    @Override
    public ThawSmeltingRecipeBuilder unlockedBy(String criterionName, Criterion<?> criterionTrigger) {
        this.criteria.put(criterionName, criterionTrigger);
        return this;
    }

    @Override
    public RecipeBuilder group(@org.jetbrains.annotations.Nullable String s) {
        return null;
    }

    @Override
    public Item getResult() {
        return null;
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        Advancement.Builder advancement$builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(advancement$builder);
        this.criteria.forEach(advancement$builder::addCriterion);
        ThawSmeltingRecipe recipe = this.factory.create(this.component, this.experience, this.cookingTime);
        recipeOutput.accept(id, recipe, advancement$builder.build(id.withPrefix("recipes/thawing/")));
    }
}
