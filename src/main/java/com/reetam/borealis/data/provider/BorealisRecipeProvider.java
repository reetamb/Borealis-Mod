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
            SingleItemRecipeBuilder recipe = SingleItemRecipeBuilder.stonecutting(
                    Ingredient.of(stoneIn.get()),
                    output,
                    output instanceof SlabBlock ? 2 : 1
            ).unlocks(has(stoneIn), has(stoneIn.get()));
            recipe.save(consumer, name(output.getRegistryName().toString().substring(BorealisMod.MODID.length()+1) + "_from_" + stoneIn.get().getRegistryName().toString().substring(BorealisMod.MODID.length()+1) + "_stonecutting"));
            recipes.add(recipe);
        }
        return recipes;
    }

    public ShapedRecipeBuilder wood(Supplier<? extends Block> logIn, Supplier<? extends Block> woodOut) {
        return ShapedRecipeBuilder.shaped(woodOut.get(), 3)
                .pattern("##")
                .pattern("##")
                .define('#', logIn.get())
                .unlockedBy(has(logIn), has(logIn.get()));
    }
    public ShapedRecipeBuilder stairs(Supplier<? extends Block> blockIn, Supplier<? extends Block> stairsOut) {
        return ShapedRecipeBuilder.shaped(stairsOut.get(), 4)
                .pattern("  #")
                .pattern(" ##")
                .pattern("###")
                .define('#', blockIn.get())
                .unlockedBy(has(blockIn), has(blockIn.get()));
    }
    public ShapedRecipeBuilder slab(Supplier<? extends Block> blockIn, Supplier<? extends Block> slabOut) {
        return ShapedRecipeBuilder.shaped(slabOut.get(), 6)
                .pattern("###")
                .define('#', blockIn.get())
                .unlockedBy(has(blockIn), has(blockIn.get()));
    }
    public ShapedRecipeBuilder fence(Supplier<? extends Block> blockIn, Supplier<? extends Block> fenceOut) {
        return ShapedRecipeBuilder.shaped(fenceOut.get(), 3)
                .pattern("#/#")
                .pattern("#/#")
                .define('#', blockIn.get())
                .define('/', Items.STICK)
                .unlockedBy(has(blockIn), has(blockIn.get()));
    }
    public ShapedRecipeBuilder gate(Supplier<? extends Block> blockIn, Supplier<? extends Block> gateOut) {
        return ShapedRecipeBuilder.shaped(gateOut.get(), 3)
                .pattern("/#/")
                .pattern("/#/")
                .define('/', Items.STICK)
                .define('#', blockIn.get())
                .unlockedBy(has(blockIn), has(blockIn.get()));
    }
    public ShapelessRecipeBuilder button(Supplier<? extends Block> blockIn, Supplier<? extends Block> buttonOut) {
        return ShapelessRecipeBuilder.shapeless(buttonOut.get())
                .requires(blockIn.get())
                .unlockedBy(has(blockIn), has(blockIn.get()));
    }
    public ShapedRecipeBuilder plate(Supplier<? extends Block> blockIn, Supplier<? extends Block> pressurePlateOut) {
        return ShapedRecipeBuilder.shaped(pressurePlateOut.get())
                .pattern("##")
                .define('#', blockIn.get())
                .unlockedBy(has(blockIn), has(blockIn.get()));
    }
    public ShapedRecipeBuilder door(Supplier<? extends Block> plankIn, Supplier<? extends Block> doorOut) {
        return ShapedRecipeBuilder.shaped(doorOut.get(), 3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', plankIn.get())
                .unlockedBy(has(plankIn), has(plankIn.get()));
    }
    public ShapedRecipeBuilder trapdoor(Supplier<? extends Block> plankIn, Supplier<? extends Block> trapdoorOut) {
        return ShapedRecipeBuilder.shaped(trapdoorOut.get(), 2)
                .pattern("###")
                .pattern("###")
                .define('#', plankIn.get())
                .unlockedBy(has(plankIn), has(plankIn.get()));
    }
    public ShapedRecipeBuilder stone(Supplier<? extends Block> stoneIn, Supplier<? extends Block> stoneOut) {
        return ShapedRecipeBuilder.shaped(stoneOut.get(), 4)
                .pattern("##")
                .pattern("##")
                .define('#', stoneIn.get())
                .unlockedBy(has(stoneIn), has(stoneIn.get()));
    }
    public ShapedRecipeBuilder wall(Supplier<? extends Block> stoneIn, Supplier<? extends Block> wallOut) {
        return ShapedRecipeBuilder.shaped(wallOut.get(), 6)
                .pattern("###")
                .pattern("###")
                .define('#', stoneIn.get())
                .unlockedBy(has(stoneIn), has(stoneIn.get()));
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
