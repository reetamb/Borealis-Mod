package com.reetam.borealis.data.provider;

import com.reetam.borealis.BorealisMod;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BorealisRecipeProvider extends RecipeProvider {
    public BorealisRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    public ArrayList<SingleItemRecipeBuilder> bulkStonecutting(Consumer<IFinishedRecipe> consumer, Supplier<? extends Block> stoneIn, Block[] outputs) {
        ArrayList<SingleItemRecipeBuilder> recipes = new ArrayList<>();
        for (Block output : outputs) {
            SingleItemRecipeBuilder recipe = SingleItemRecipeBuilder.stonecuttingRecipe(
                    Ingredient.fromItems(stoneIn.get()),
                    output,
                    output instanceof SlabBlock ? 2 : 1
            ).addCriterion(has(stoneIn), hasItem(stoneIn.get()));
            recipe.build(consumer, name(output.getRegistryName().toString().substring(BorealisMod.MODID.length()+1) + "_from_" + stoneIn.get().getRegistryName().toString().substring(BorealisMod.MODID.length()+1) + "_stonecutting"));
            recipes.add(recipe);
        }
        return recipes;
    }

    public ShapedRecipeBuilder wood(Supplier<? extends Block> logIn, Supplier<? extends Block> woodOut) {
        return ShapedRecipeBuilder.shapedRecipe(woodOut.get(), 3)
                .patternLine("##")
                .patternLine("##")
                .key('#', logIn.get())
                .addCriterion(has(logIn), hasItem(logIn.get()));
    }
    public ShapedRecipeBuilder stairs(Supplier<? extends Block> blockIn, Supplier<? extends Block> stairsOut) {
        return ShapedRecipeBuilder.shapedRecipe(stairsOut.get(), 4)
                .patternLine("  #")
                .patternLine(" ##")
                .patternLine("###")
                .key('#', blockIn.get())
                .addCriterion(has(blockIn), hasItem(blockIn.get()));
    }
    public ShapedRecipeBuilder slab(Supplier<? extends Block> blockIn, Supplier<? extends Block> slabOut) {
        return ShapedRecipeBuilder.shapedRecipe(slabOut.get(), 6)
                .patternLine("###")
                .key('#', blockIn.get())
                .addCriterion(has(blockIn), hasItem(blockIn.get()));
    }
    public ShapedRecipeBuilder fence(Supplier<? extends Block> blockIn, Supplier<? extends Block> fenceOut) {
        return ShapedRecipeBuilder.shapedRecipe(fenceOut.get(), 3)
                .patternLine("#/#")
                .patternLine("#/#")
                .key('#', blockIn.get())
                .key('/', Items.STICK)
                .addCriterion(has(blockIn), hasItem(blockIn.get()));
    }
    public ShapedRecipeBuilder gate(Supplier<? extends Block> blockIn, Supplier<? extends Block> gateOut) {
        return ShapedRecipeBuilder.shapedRecipe(gateOut.get(), 3)
                .patternLine("/#/")
                .patternLine("/#/")
                .key('/', Items.STICK)
                .key('#', blockIn.get())
                .addCriterion(has(blockIn), hasItem(blockIn.get()));
    }
    public ShapelessRecipeBuilder button(Supplier<? extends Block> blockIn, Supplier<? extends Block> buttonOut) {
        return ShapelessRecipeBuilder.shapelessRecipe(buttonOut.get())
                .addIngredient(blockIn.get())
                .addCriterion(has(blockIn), hasItem(blockIn.get()));
    }
    public ShapedRecipeBuilder plate(Supplier<? extends Block> blockIn, Supplier<? extends Block> pressurePlateOut) {
        return ShapedRecipeBuilder.shapedRecipe(pressurePlateOut.get())
                .patternLine("##")
                .key('#', blockIn.get())
                .addCriterion(has(blockIn), hasItem(blockIn.get()));
    }
    public ShapedRecipeBuilder door(Supplier<? extends Block> plankIn, Supplier<? extends Block> doorOut) {
        return ShapedRecipeBuilder.shapedRecipe(doorOut.get(), 3)
                .patternLine("##")
                .patternLine("##")
                .patternLine("##")
                .key('#', plankIn.get())
                .addCriterion(has(plankIn), hasItem(plankIn.get()));
    }
    public ShapedRecipeBuilder trapdoor(Supplier<? extends Block> plankIn, Supplier<? extends Block> trapdoorOut) {
        return ShapedRecipeBuilder.shapedRecipe(trapdoorOut.get(), 2)
                .patternLine("###")
                .patternLine("###")
                .key('#', plankIn.get())
                .addCriterion(has(plankIn), hasItem(plankIn.get()));
    }
    public ShapedRecipeBuilder stone(Supplier<? extends Block> stoneIn, Supplier<? extends Block> stoneOut) {
        return ShapedRecipeBuilder.shapedRecipe(stoneOut.get(), 4)
                .patternLine("##")
                .patternLine("##")
                .key('#', stoneIn.get())
                .addCriterion(has(stoneIn), hasItem(stoneIn.get()));
    }
    public ShapedRecipeBuilder wall(Supplier<? extends Block> stoneIn, Supplier<? extends Block> wallOut) {
        return ShapedRecipeBuilder.shapedRecipe(wallOut.get(), 6)
                .patternLine("###")
                .patternLine("###")
                .key('#', stoneIn.get())
                .addCriterion(has(stoneIn), hasItem(stoneIn.get()));
    }

    protected ResourceLocation name(String name) {
        return new ResourceLocation(BorealisMod.MODID, name);
    }

    protected ResourceLocation name(Supplier<? extends Block> block) {
        return block.get().getRegistryName();
    }
    protected String has(Supplier<? extends Block> block) {
        return "has_" + block.get().getRegistryName().getPath();
    }
}
