package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.data.provider.BorealisRecipeProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BorealisRecipes extends BorealisRecipeProvider {

    public BorealisRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
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
                BorealisBlocks.brumal_log,
                BorealisBlocks.brumal_planks,
                BorealisBlocks.brumal_wood,
                BorealisBlocks.stripped_brumal_log,
                BorealisBlocks.stripped_brumal_wood,
                BorealisBlocks.brumal_stairs,
                BorealisBlocks.brumal_slab,
                BorealisBlocks.brumal_fence,
                BorealisBlocks.brumal_fence_gate,
                BorealisBlocks.brumal_button,
                BorealisBlocks.brumal_pressure_plate,
                BorealisBlocks.brumal_door,
                BorealisBlocks.brumal_trapdoor,
                BorealisItems.BRUMAL_BOAT
        );
        bulkWood(
                consumer,
                BorealisBlocks.frostfir_log,
                BorealisBlocks.frostfir_planks,
                BorealisBlocks.frostfir_wood,
                BorealisBlocks.stripped_frostfir_log,
                BorealisBlocks.stripped_frostfir_wood,
                BorealisBlocks.frostfir_stairs,
                BorealisBlocks.frostfir_slab,
                BorealisBlocks.frostfir_fence,
                BorealisBlocks.frostfir_fence_gate,
                BorealisBlocks.frostfir_button,
                BorealisBlocks.frostfir_pressure_plate,
                BorealisBlocks.frostfir_door,
                BorealisBlocks.frostfir_trapdoor,
                BorealisItems.FROSTFIR_BOAT
        );
        bulkWood(
                consumer,
                BorealisBlocks.saccharine_log,
                BorealisBlocks.saccharine_planks,
                BorealisBlocks.saccharine_wood,
                BorealisBlocks.stripped_saccharine_log,
                BorealisBlocks.stripped_saccharine_wood,
                BorealisBlocks.saccharine_stairs,
                BorealisBlocks.saccharine_slab,
                BorealisBlocks.saccharine_fence,
                BorealisBlocks.saccharine_fence_gate,
                BorealisBlocks.saccharine_button,
                BorealisBlocks.saccharine_pressure_plate,
                BorealisBlocks.saccharine_door,
                BorealisBlocks.saccharine_trapdoor,
                BorealisItems.SACCHARINE_BOAT
        );
        doStone(consumer, BorealisBlocks.SOAPSTONE, BorealisBlocks.SOAPSTONE_STAIRS, BorealisBlocks.SOAPSTONE_SLAB, BorealisBlocks.SOAPSTONE_WALL);
        doStone(consumer, BorealisBlocks.SOAPSTONE_BRICKS, BorealisBlocks.SOAPSTONE_BRICK_STAIRS, BorealisBlocks.SOAPSTONE_BRICK_SLAB, BorealisBlocks.SOAPSTONE_BRICK_WALL);
        doStone(consumer, BorealisBlocks.SOAPSTONE_TILES, BorealisBlocks.SOAPSTONE_TILE_STAIRS, BorealisBlocks.SOAPSTONE_TILE_SLAB, BorealisBlocks.SOAPSTONE_TILE_WALL);
        stone(BorealisBlocks.SOAPSTONE, BorealisBlocks.SOAPSTONE_BRICKS).save(consumer);
        stone(BorealisBlocks.SOAPSTONE_BRICKS, BorealisBlocks.SOAPSTONE_TILES).save(consumer);
        bulkStonecutting(consumer, BorealisBlocks.SOAPSTONE, soapstone_stonecuts);
        bulkStonecutting(consumer, BorealisBlocks.SOAPSTONE_BRICKS, brick_stonecuts);
        bulkStonecutting(consumer, BorealisBlocks.SOAPSTONE_TILES, tile_stonecuts);
        ShapedRecipeBuilder.shaped(BorealisItems.HAT.get())
                .pattern(" # ")
                .pattern(" 0 ")
                .pattern("###")
                .define('#', Blocks.BLACK_WOOL)
                .define('0', Blocks.WHITE_WOOL)
                .unlockedBy("has_" + Blocks.BLACK_WOOL.getRegistryName().getPath(), has(Blocks.BLACK_WOOL)).save(consumer);
        ShapedRecipeBuilder.shaped(BorealisItems.MOON_PEARL.get())
                .pattern("000")
                .pattern("0#0")
                .pattern("000")
                .define('0', Items.LAPIS_LAZULI)
                .define('#', Items.SNOWBALL)
                .unlockedBy("has_" + Items.LAPIS_LAZULI, has(Items.LAPIS_LAZULI)).save(consumer);
        ShapedRecipeBuilder.shaped(BorealisBlocks.SLATE_PILLAR.get())
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', BorealisBlocks.SLATE.get())
                .unlockedBy(has(BorealisBlocks.SLATE), has(BorealisBlocks.SLATE.get()));
        stone(BorealisBlocks.SLATE, BorealisBlocks.SLATE_TILES).save(consumer);
        bulkStonecutting(consumer, BorealisBlocks.SLATE, slate_stonecuts);
    }

    public void bulkWood(Consumer<IFinishedRecipe> consumer, Supplier<? extends Block> logIn, Supplier<? extends Block> plankIn, Supplier<? extends Block> woodIn, Supplier<? extends Block> stripLogIn, Supplier<? extends Block> stripWoodIn, Supplier<? extends Block> stairsIn, Supplier<? extends Block> slabIn, Supplier<? extends Block> fenceIn, Supplier<? extends Block> gateIn, Supplier<? extends Block> buttonIn, Supplier<? extends Block> plateIn, Supplier<? extends Block> doorIn, Supplier<? extends Block> trapdoorIn, Supplier<? extends Item> boatIn) {
        ShapelessRecipeBuilder.shapeless(plankIn.get(), 4)
                .requires(logIn.get())
                .unlockedBy(has(logIn), has(logIn.get()))
                .save(consumer, name(plankIn));
        ShapelessRecipeBuilder.shapeless(plankIn.get(), 4)
                .requires(woodIn.get())
                .unlockedBy(has(woodIn), has(woodIn.get()))
                .save(consumer, name(plankIn) + "_from_" + name(woodIn).toString().substring(BorealisMod.MODID.length()+1));

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
    }

    public void doStone(Consumer<IFinishedRecipe> consumer, Supplier<? extends Block> stoneIn, Supplier<? extends Block> stairsIn, Supplier<? extends Block> slabIn, Supplier<? extends Block> wallIn) {
        stairs(stoneIn, stairsIn).save(consumer, name(stairsIn));
        slab(stoneIn, slabIn).save(consumer, name(slabIn));
        wall(stoneIn, wallIn).save(consumer, name(wallIn));
    }
}
