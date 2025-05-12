package com.reetam.borealis.data.provider;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.kiln.KilnRecipe;
import com.reetam.borealis.block.kiln.KilnRecipeBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.Tags;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class BorealisRecipeProvider extends RecipeProvider {
    public BorealisRecipeProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> provider) {
        super(generator.getPackOutput(), provider);
    }

    public ArrayList<SingleItemRecipeBuilder> bulkStonecutting(RecipeOutput consumer, Supplier<? extends Block> stoneIn, Block[] outputs) {
        ArrayList<SingleItemRecipeBuilder> recipes = new ArrayList<>();
        for (Block output : outputs) {
            SingleItemRecipeBuilder recipe = SingleItemRecipeBuilder.stonecutting(
                    Ingredient.of(stoneIn.get()),
                    RecipeCategory.BUILDING_BLOCKS,
                    output,
                    output instanceof SlabBlock ? 2 : 1
            ).unlockedBy(has(stoneIn), has(stoneIn.get()));
            recipe.save(consumer, name(output.toString().substring(15, output.toString().length() - 1) + "_from_" + stoneIn.get().toString().substring(15, stoneIn.get().toString().length() - 1) + "_stonecutting"));
            recipes.add(recipe);
        }
        return recipes;
    }

    public ShapedRecipeBuilder wood(Supplier<? extends Block> logIn, Supplier<? extends Block> woodOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, woodOut.get(), 3)
                .pattern("##")
                .pattern("##")
                .define('#', logIn.get())
                .unlockedBy(has(logIn), has(logIn.get()));
    }
    public ShapedRecipeBuilder stairs(Supplier<? extends Block> blockIn, Supplier<? extends Block> stairsOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairsOut.get(), 4)
                .pattern("  #")
                .pattern(" ##")
                .pattern("###")
                .define('#', blockIn.get())
                .unlockedBy(has(blockIn), has(blockIn.get()));
    }
    public ShapedRecipeBuilder slab(Supplier<? extends Block> blockIn, Supplier<? extends Block> slabOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slabOut.get(), 6)
                .pattern("###")
                .define('#', blockIn.get())
                .unlockedBy(has(blockIn), has(blockIn.get()));
    }
    public ShapedRecipeBuilder fence(Supplier<? extends Block> blockIn, Supplier<? extends Block> fenceOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fenceOut.get(), 3)
                .pattern("#/#")
                .pattern("#/#")
                .define('#', blockIn.get())
                .define('/', Items.STICK)
                .unlockedBy(has(blockIn), has(blockIn.get()));
    }
    public ShapedRecipeBuilder gate(Supplier<? extends Block> blockIn, Supplier<? extends Block> gateOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, gateOut.get(), 3)
                .pattern("/#/")
                .pattern("/#/")
                .define('/', Items.STICK)
                .define('#', blockIn.get())
                .unlockedBy(has(blockIn), has(blockIn.get()));
    }
    public ShapelessRecipeBuilder button(Supplier<? extends Block> blockIn, Supplier<? extends Block> buttonOut) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, buttonOut.get())
                .requires(blockIn.get())
                .unlockedBy(has(blockIn), has(blockIn.get()));
    }
    public ShapedRecipeBuilder plate(Supplier<? extends Block> blockIn, Supplier<? extends Block> pressurePlateOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, pressurePlateOut.get())
                .pattern("##")
                .define('#', blockIn.get())
                .unlockedBy(has(blockIn), has(blockIn.get()));
    }
    public ShapedRecipeBuilder door(Supplier<? extends Block> plankIn, Supplier<? extends Block> doorOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, doorOut.get(), 3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', plankIn.get())
                .unlockedBy(has(plankIn), has(plankIn.get()));
    }
    public ShapedRecipeBuilder trapdoor(Supplier<? extends Block> plankIn, Supplier<? extends Block> trapdoorOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, trapdoorOut.get(), 2)
                .pattern("###")
                .pattern("###")
                .define('#', plankIn.get())
                .unlockedBy(has(plankIn), has(plankIn.get()));
    }
    public ShapedRecipeBuilder stone(Supplier<? extends Block> stoneIn, Supplier<? extends Block> stoneOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stoneOut.get(), 4)
                .pattern("##")
                .pattern("##")
                .define('#', stoneIn.get())
                .unlockedBy(has(stoneIn), has(stoneIn.get()));
    }
    public ShapedRecipeBuilder wall(Supplier<? extends Block> stoneIn, Supplier<? extends Block> wallOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, wallOut.get(), 6)
                .pattern("###")
                .pattern("###")
                .define('#', stoneIn.get())
                .unlockedBy(has(stoneIn), has(stoneIn.get()));
    }

    public ShapedRecipeBuilder boat(Supplier<? extends Block> plankIn, Supplier<? extends Item> boatOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, boatOut.get(), 1)
                .pattern("# #")
                .pattern("###")
                .define('#', plankIn.get())
                .unlockedBy("in_water", insideOf(Blocks.WATER));
    }

    public ShapedRecipeBuilder sign(Supplier<? extends Block> planksIn, Supplier<? extends SignBlock> signOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, signOut.get(), 3)
                .pattern("###")
                .pattern("###")
                .pattern(" / ")
                .define('#', planksIn.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy(has(planksIn), has(planksIn.get()));
    }

    public ShapedRecipeBuilder hangingSign(Supplier<? extends Block> planksIn, Supplier<? extends CeilingHangingSignBlock> signOut) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, signOut.get(), 3)
                .pattern("/ /")
                .pattern("###")
                .pattern("###")
                .define('#', planksIn.get())
                .define('/', Items.CHAIN)
                .unlockedBy(has(planksIn), has(planksIn.get()));
    }

    public KilnRecipeBuilder firing(ItemLike input, ItemLike output) {
        return KilnRecipeBuilder.generic(Ingredient.of(input), output, RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, 0.1F, 100, KilnRecipe::new)
                .unlockedBy("has_" + name(() -> input), has(input))
                .group(getSmeltingRecipeName(input));
    }

    public KilnRecipeBuilder firing(TagKey<Item> input, ItemLike output) {
        return KilnRecipeBuilder.generic(Ingredient.of(input), output, RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, 0.1F, 100, KilnRecipe::new)
                .unlockedBy("has_" + input, has(input));
    }

    public void fireAllColors(RecipeOutput consumer) {
        firing(Blocks.BLACK_TERRACOTTA, Blocks.BLACK_GLAZED_TERRACOTTA).save(consumer, name("black_glazed_terracotta_from_firing"));
        firing(Blocks.GRAY_TERRACOTTA, Blocks.GRAY_GLAZED_TERRACOTTA).save(consumer, name("gray_glazed_terracotta_from_firing"));
        firing(Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA).save(consumer, name("light_gray_glazed_terracotta_from_firing"));
        firing(Blocks.WHITE_TERRACOTTA, Blocks.WHITE_GLAZED_TERRACOTTA).save(consumer, name("white_glazed_terracotta_from_firing"));
        firing(Blocks.PINK_TERRACOTTA, Blocks.PINK_GLAZED_TERRACOTTA).save(consumer, name("pink_glazed_terracotta_from_firing"));
        firing(Blocks.MAGENTA_TERRACOTTA, Blocks.MAGENTA_GLAZED_TERRACOTTA).save(consumer, name("magenta_glazed_terracotta_from_firing"));
        firing(Blocks.PURPLE_TERRACOTTA, Blocks.PURPLE_GLAZED_TERRACOTTA).save(consumer, name("purple_glazed_terracotta_from_firing"));
        firing(Blocks.BLUE_TERRACOTTA, Blocks.BLUE_GLAZED_TERRACOTTA).save(consumer, name("blue_glazed_terracotta_from_firing"));
        firing(Blocks.CYAN_TERRACOTTA, Blocks.CYAN_GLAZED_TERRACOTTA).save(consumer, name("cyan_glazed_terracotta_from_firing"));
        firing(Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA).save(consumer, name("light_blue_glazed_terracotta_from_firing"));
        firing(Blocks.GREEN_TERRACOTTA, Blocks.GREEN_GLAZED_TERRACOTTA).save(consumer, name("green_glazed_terracotta_from_firing"));
        firing(Blocks.LIME_TERRACOTTA, Blocks.LIME_GLAZED_TERRACOTTA).save(consumer, name("lime_glazed_terracotta_from_firing"));
        firing(Blocks.YELLOW_TERRACOTTA, Blocks.YELLOW_GLAZED_TERRACOTTA).save(consumer, name("yellow_glazed_terracotta_from_firing"));
        firing(Blocks.ORANGE_TERRACOTTA, Blocks.ORANGE_GLAZED_TERRACOTTA).save(consumer, name("orange_glazed_terracotta_from_firing"));
        firing(Blocks.RED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA).save(consumer, name("red_glazed_terracotta_from_firing"));
        firing(Blocks.BROWN_TERRACOTTA, Blocks.BROWN_GLAZED_TERRACOTTA).save(consumer, name("brown_glazed_terracotta_from_firing"));
    }

    protected ResourceLocation name(String name) {
        return ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, name);
    }

    protected String name(Supplier<? extends ItemLike> block) {
        String n;
        if (block.get() instanceof Block) {
            n = block.get().toString().substring(15, block.get().toString().length() - 1);
        } else {
            n = block.get().toString();
        }
        return n;
    }

    protected String has(Supplier<? extends Block> block) {
        return "has_" + block.get().getName();
    }
    protected String hasItem(Supplier<? extends Item> item) {
        return "has_" + item.get().getName(new ItemStack(item.get()));
    }
}
