package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.data.provider.BorealisRecipeProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SignBlock;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BorealisRecipes extends BorealisRecipeProvider {

    public BorealisRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        Block[] soapstone_stonecuts = new Block[]{
                BorealisBlocks.SOAPSTONE_BRICKS.get(),
                BorealisBlocks.SOAPSTONE_TILES.get(),
                BorealisBlocks.SOAPSTONE_STAIRS.get(),
                BorealisBlocks.SOAPSTONE_SLAB.get(),
                BorealisBlocks.SOAPSTONE_WALL.get(),
                BorealisBlocks.SOAPSTONE_BUTTON.get(),
                BorealisBlocks.SOAPSTONE_PRESSURE_PLATE.get()
        };
        Block[] brick_stonecuts = new Block[]{
                BorealisBlocks.SOAPSTONE_TILES.get(),
                BorealisBlocks.SOAPSTONE_BRICK_STAIRS.get(),
                BorealisBlocks.SOAPSTONE_BRICK_SLAB.get(),
                BorealisBlocks.SOAPSTONE_BRICK_WALL.get()
        };
        Block[] tile_stonecuts = new Block[]{
                BorealisBlocks.SOAPSTONE_TILE_STAIRS.get(),
                BorealisBlocks.SOAPSTONE_TILE_SLAB.get(),
                BorealisBlocks.SOAPSTONE_TILE_WALL.get()
        };
        Block[] slate_stonecuts = new Block[]{
                BorealisBlocks.SLATE_PILLAR.get(),
                BorealisBlocks.SLATE_TILES.get()
        };
        bulkWood(
                consumer,
                BorealisBlocks.BRUMAL_LOG,
                BorealisBlocks.BRUMAL_PLANKS,
                BorealisBlocks.BRUMAL_WOOD,
                BorealisBlocks.STRIPPED_BRUMAL_LOG,
                BorealisBlocks.STRIPPED_BRUMAL_WOOD,
                BorealisBlocks.BRUMAL_STAIRS,
                BorealisBlocks.BRUMAL_SLAB,
                BorealisBlocks.BRUMAL_FENCE,
                BorealisBlocks.BRUMAL_FENCE_GATE,
                BorealisBlocks.BRUMAL_BUTTON,
                BorealisBlocks.BRUMAL_PRESSURE_PLATE,
                BorealisBlocks.BRUMAL_DOOR,
                BorealisBlocks.BRUMAL_TRAPDOOR,
                BorealisItems.BRUMAL_BOAT,
                BorealisBlocks.BRUMAL_SIGN
        );
        bulkWood(
                consumer,
                BorealisBlocks.FROSTFIR_LOG,
                BorealisBlocks.FROSTFIR_PLANKS,
                BorealisBlocks.FROSTFIR_WOOD,
                BorealisBlocks.STRIPPED_FROSTFIR_LOG,
                BorealisBlocks.STRIPPED_FROSTFIR_WOOD,
                BorealisBlocks.FROSTFIR_STAIRS,
                BorealisBlocks.FROSTFIR_SLAB,
                BorealisBlocks.FROSTFIR_FENCE,
                BorealisBlocks.FROSTFIR_FENCE_GATE,
                BorealisBlocks.FROSTFIR_BUTTON,
                BorealisBlocks.FROSTFIR_PRESSURE_PLATE,
                BorealisBlocks.FROSTFIR_DOOR,
                BorealisBlocks.FROSTFIR_TRAPDOOR,
                BorealisItems.FROSTFIR_BOAT,
                BorealisBlocks.FROSTFIR_SIGN
        );
        bulkWood(
                consumer,
                BorealisBlocks.SACCHARINE_LOG,
                BorealisBlocks.SACCHARINE_PLANKS,
                BorealisBlocks.SACCHARINE_WOOD,
                BorealisBlocks.STRIPPED_SACCHARINE_LOG,
                BorealisBlocks.STRIPPED_SACCHARINE_WOOD,
                BorealisBlocks.SACCHARINE_STAIRS,
                BorealisBlocks.SACCHARINE_SLAB,
                BorealisBlocks.SACCHARINE_FENCE,
                BorealisBlocks.SACCHARINE_FENCE_GATE,
                BorealisBlocks.SACCHARINE_BUTTON,
                BorealisBlocks.SACCHARINE_PRESSURE_PLATE,
                BorealisBlocks.SACCHARINE_DOOR,
                BorealisBlocks.SACCHARINE_TRAPDOOR,
                BorealisItems.SACCHARINE_BOAT,
                BorealisBlocks.SACCHARINE_SIGN
        );
        doStone(consumer, BorealisBlocks.SOAPSTONE, BorealisBlocks.SOAPSTONE_STAIRS, BorealisBlocks.SOAPSTONE_SLAB, BorealisBlocks.SOAPSTONE_WALL);
        doStone(consumer, BorealisBlocks.SOAPSTONE_BRICKS, BorealisBlocks.SOAPSTONE_BRICK_STAIRS, BorealisBlocks.SOAPSTONE_BRICK_SLAB, BorealisBlocks.SOAPSTONE_BRICK_WALL);
        doStone(consumer, BorealisBlocks.SOAPSTONE_TILES, BorealisBlocks.SOAPSTONE_TILE_STAIRS, BorealisBlocks.SOAPSTONE_TILE_SLAB, BorealisBlocks.SOAPSTONE_TILE_WALL);
        stone(BorealisBlocks.SOAPSTONE, BorealisBlocks.SOAPSTONE_BRICKS).save(consumer);
        stone(BorealisBlocks.SOAPSTONE_BRICKS, BorealisBlocks.SOAPSTONE_TILES).save(consumer);
        bulkStonecutting(consumer, BorealisBlocks.SOAPSTONE, soapstone_stonecuts);
        bulkStonecutting(consumer, BorealisBlocks.SOAPSTONE_BRICKS, brick_stonecuts);
        bulkStonecutting(consumer, BorealisBlocks.SOAPSTONE_TILES, tile_stonecuts);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BorealisItems.HAT.get())
                .pattern(" # ")
                .pattern(" 0 ")
                .pattern("###")
                .define('#', Blocks.BLACK_WOOL)
                .define('0', Blocks.WHITE_WOOL)
                .unlockedBy("has_" + name(() -> Blocks.BLACK_WOOL), has(Blocks.BLACK_WOOL)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BorealisItems.MOON_PEARL.get())
                .pattern("000")
                .pattern("0#0")
                .pattern("000")
                .define('0', Items.LAPIS_LAZULI)
                .define('#', Items.SNOWBALL)
                .unlockedBy("has_" + Items.LAPIS_LAZULI, has(Items.LAPIS_LAZULI)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BorealisBlocks.SLATE_PILLAR.get())
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', BorealisBlocks.SLATE.get())
                .unlockedBy(has(BorealisBlocks.SLATE), has(BorealisBlocks.SLATE.get()));
        stone(BorealisBlocks.SLATE, BorealisBlocks.SLATE_TILES).save(consumer);
        smeltingResultFromBase(consumer, BorealisBlocks.STARRY_SLATE.get(), BorealisBlocks.SLATE.get());
        stone(BorealisBlocks.STARRY_SLATE, BorealisBlocks.STARRY_SLATE_TILES).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BorealisBlocks.PETRIFIED_WOOD_BRICKS.get(), 4)
                .requires(BorealisBlocks.PETRIFIED_WOOD.get())
                .unlockedBy(has(BorealisBlocks.PETRIFIED_WOOD), has(BorealisBlocks.PETRIFIED_WOOD.get()))
                .save(consumer, name(BorealisBlocks.PETRIFIED_WOOD));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.CRAFTING_TABLE, 1)
                .pattern("##")
                .pattern("##")
                .define('#', BorealisBlocks.PETRIFIED_WOOD_BRICKS.get())
                .unlockedBy(has(BorealisBlocks.PETRIFIED_WOOD_BRICKS), has(BorealisBlocks.PETRIFIED_WOOD_BRICKS.get()))
                .save(consumer, name("crafting_table_from_petrified_planks"));
        bulkStonecutting(consumer, BorealisBlocks.SLATE, slate_stonecuts);
    }

    public void bulkWood(Consumer<FinishedRecipe> consumer, Supplier<? extends Block> logIn, Supplier<? extends Block> plankIn, Supplier<? extends Block> woodIn, Supplier<? extends Block> stripLogIn, Supplier<? extends Block> stripWoodIn, Supplier<? extends Block> stairsIn, Supplier<? extends Block> slabIn, Supplier<? extends Block> fenceIn, Supplier<? extends Block> gateIn, Supplier<? extends Block> buttonIn, Supplier<? extends Block> plateIn, Supplier<? extends Block> doorIn, Supplier<? extends Block> trapdoorIn, Supplier<? extends Item> boatIn, Supplier<? extends SignBlock> signIn) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, plankIn.get(), 4)
                .requires(logIn.get())
                .unlockedBy(has(logIn), has(logIn.get()))
                .save(consumer, name(plankIn));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, plankIn.get(), 4)
                .requires(woodIn.get())
                .unlockedBy(has(woodIn), has(woodIn.get()))
                .save(consumer, name(plankIn) + "_from_" + name(woodIn));

        wood(logIn, woodIn).save(consumer, name(woodIn));
        wood(stripLogIn, stripWoodIn).save(consumer, name(stripWoodIn));
        stairs(plankIn, stairsIn).save(consumer, name(stairsIn));
        slab(plankIn, slabIn).save(consumer, name(slabIn));
        fence(plankIn, fenceIn).save(consumer, name(fenceIn));
        gate(plankIn, gateIn).save(consumer, name(gateIn));
        button(plankIn, buttonIn).save(consumer, name(buttonIn));
        plate(plankIn, plateIn).save(consumer, name(plateIn));
        door(plankIn, doorIn).save(consumer, name(doorIn));
        trapdoor(plankIn, trapdoorIn).save(consumer, name(trapdoorIn));
        boat(plankIn, boatIn).save(consumer, name(boatIn));
        sign(plankIn, signIn).save(consumer, name(signIn));
    }

    public void doStone(Consumer<FinishedRecipe> consumer, Supplier<? extends Block> stoneIn, Supplier<? extends Block> stairsIn, Supplier<? extends Block> slabIn, Supplier<? extends Block> wallIn) {
        stairs(stoneIn, stairsIn).save(consumer, name(stairsIn));
        slab(stoneIn, slabIn).save(consumer, name(slabIn));
        wall(stoneIn, wallIn).save(consumer, name(wallIn));
    }
}
