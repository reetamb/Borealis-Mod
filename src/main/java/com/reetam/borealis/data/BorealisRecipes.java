package com.reetam.borealis.data;

import com.reetam.borealis.data.provider.BorealisRecipeProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.SignBlock;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class BorealisRecipes extends BorealisRecipeProvider {

    public BorealisRecipes(DataGenerator generatorIn, CompletableFuture<HolderLookup.Provider> provider) {
        super(generatorIn, provider);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
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

        doStone(consumer, BorealisBlocks.SOAPSTONE, BorealisBlocks.SOAPSTONE_STAIRS, BorealisBlocks.SOAPSTONE_SLAB, BorealisBlocks.SOAPSTONE_WALL);
        doStone(consumer, BorealisBlocks.SOAPSTONE_BRICKS, BorealisBlocks.SOAPSTONE_BRICK_STAIRS, BorealisBlocks.SOAPSTONE_BRICK_SLAB, BorealisBlocks.SOAPSTONE_BRICK_WALL);
        doStone(consumer, BorealisBlocks.SOAPSTONE_TILES, BorealisBlocks.SOAPSTONE_TILE_STAIRS, BorealisBlocks.SOAPSTONE_TILE_SLAB, BorealisBlocks.SOAPSTONE_TILE_WALL);
        doWood(consumer);

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
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, BorealisItems.KYANITE_ARROW.get(), 3)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', BorealisItems.KYANITE_CRYSTAL.get())
                .unlockedBy(hasItem(BorealisItems.KYANITE_CRYSTAL), has(BorealisItems.KYANITE_CRYSTAL.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BorealisBlocks.KYANITE_CABLE.get(), 1)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', BorealisItems.KYANITE_CRYSTAL.get())
                .unlockedBy(hasItem(BorealisItems.KYANITE_CRYSTAL), has(BorealisItems.KYANITE_CRYSTAL.get()));
        smeltingResultFromBase(consumer, BorealisBlocks.CRACKED_ALMS.get(), BorealisBlocks.ALMS.get());

        bulkStonecutting(consumer, BorealisBlocks.SLATE, slate_stonecuts);

        firing(Blocks.COBBLESTONE, Blocks.STONE).save(consumer, name("stone_from_firing"));
        firing(Blocks.STONE, Blocks.SMOOTH_STONE).save(consumer, name("smooth_stone_from_firing"));
        firing(Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE).save(consumer, name("deepslate_from_firing"));
        firing(Blocks.SANDSTONE, Blocks.SMOOTH_SANDSTONE).save(consumer, name("smooth_sandstone_from_firing"));
        firing(Blocks.RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE).save(consumer, name("smooth_red_sandstone_from_firing"));
        firing(Blocks.BASALT, Blocks.SMOOTH_BASALT).save(consumer, name("smooth_basalt_from_firing"));
        firing(Blocks.QUARTZ_BLOCK, Blocks.SMOOTH_QUARTZ).save(consumer, name("smooth_quartz_from_firing"));
        firing(Blocks.CLAY, Blocks.TERRACOTTA).save(consumer, name("terracotta_from_firing"));
        firing(Blocks.SAND, Blocks.GLASS).save(consumer, name("glass_from_firing"));
        firing(BorealisBlocks.SUGAR_SNOW.get(), Blocks.GLASS).save(consumer, name("sugar_snow_glass_from_firing"));
        firing(Blocks.NETHERRACK, Items.NETHER_BRICK).save(consumer, name("nether_brick_from_firing"));
        firing(ItemTags.LOGS_THAT_BURN, Items.CHARCOAL).save(consumer, name("charcoal_from_firing"));
        firing(Items.CLAY_BALL, Items.BRICK).save(consumer, name("brick_from_firing"));

        fireAllColors(consumer);
    }

    public void bulkWood(RecipeOutput consumer, Supplier<? extends Block> logIn, Supplier<? extends Block> plankIn, Supplier<? extends Block> woodIn, Supplier<? extends Block> stripLogIn, Supplier<? extends Block> stripWoodIn, Supplier<? extends Block> stairsIn, Supplier<? extends Block> slabIn, Supplier<? extends Block> fenceIn, Supplier<? extends Block> gateIn, Supplier<? extends Block> buttonIn, Supplier<? extends Block> plateIn, Supplier<? extends Block> doorIn, Supplier<? extends Block> trapdoorIn, Supplier<? extends Item> boatIn, Supplier<? extends SignBlock> signIn, Supplier<? extends CeilingHangingSignBlock> hangingSignIn) {
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
        boat(plankIn, boatIn);
        sign(plankIn, signIn).save(consumer, name(signIn));
        hangingSign(plankIn, hangingSignIn).save(consumer, name(hangingSignIn));
    }

    public void doStone(RecipeOutput consumer, Supplier<? extends Block> stoneIn, Supplier<? extends Block> stairsIn, Supplier<? extends Block> slabIn, Supplier<? extends Block> wallIn) {
        stairs(stoneIn, stairsIn).save(consumer, name(stairsIn));
        slab(stoneIn, slabIn).save(consumer, name(slabIn));
        wall(stoneIn, wallIn).save(consumer, name(wallIn));
    }

    public void doWood(RecipeOutput consumer) {
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
                BorealisBlocks.BRUMAL_SIGN,
                BorealisBlocks.BRUMAL_HANGING_SIGN
        );
        bulkWood(
                consumer,
                BorealisBlocks.SWEETWOOD_LOG,
                BorealisBlocks.SWEETWOOD_PLANKS,
                BorealisBlocks.SWEETWOOD,
                BorealisBlocks.STRIPPED_SWEETWOOD_LOG,
                BorealisBlocks.STRIPPED_SWEETWOOD,
                BorealisBlocks.SWEETWOOD_STAIRS,
                BorealisBlocks.SWEETWOOD_SLAB,
                BorealisBlocks.SWEETWOOD_FENCE,
                BorealisBlocks.SWEETWOOD_FENCE_GATE,
                BorealisBlocks.SWEETWOOD_BUTTON,
                BorealisBlocks.SWEETWOOD_PRESSURE_PLATE,
                BorealisBlocks.SWEETWOOD_DOOR,
                BorealisBlocks.SWEETWOOD_TRAPDOOR,
                BorealisItems.SWEETWOOD_BOAT,
                BorealisBlocks.SWEETWOOD_SIGN,
                BorealisBlocks.SWEETWOOD_HANGING_SIGN
        );
        bulkWood(
                consumer,
                BorealisBlocks.CARAMELIZED_LOG,
                BorealisBlocks.CARAMELIZED_PLANKS,
                BorealisBlocks.CARAMELIZED_WOOD,
                BorealisBlocks.STRIPPED_CARAMELIZED_LOG,
                BorealisBlocks.STRIPPED_CARAMELIZED_WOOD,
                BorealisBlocks.CARAMELIZED_STAIRS,
                BorealisBlocks.CARAMELIZED_SLAB,
                BorealisBlocks.CARAMELIZED_FENCE,
                BorealisBlocks.CARAMELIZED_FENCE_GATE,
                BorealisBlocks.CARAMELIZED_BUTTON,
                BorealisBlocks.CARAMELIZED_PRESSURE_PLATE,
                BorealisBlocks.CARAMELIZED_DOOR,
                BorealisBlocks.CARAMELIZED_TRAPDOOR,
                BorealisItems.CARAMELIZED_BOAT,
                BorealisBlocks.CARAMELIZED_SIGN,
                BorealisBlocks.CARAMELIZED_HANGING_SIGN
        );

        smeltingResultFromBase(consumer, BorealisBlocks.CARAMELIZED_LOG.get(), BorealisBlocks.SWEETWOOD_LOG.get());
        smeltingResultFromBase(consumer, BorealisBlocks.CARAMELIZED_PLANKS.get(), BorealisBlocks.SWEETWOOD_PLANKS.get());
        smeltingResultFromBase(consumer, BorealisBlocks.CARAMELIZED_WOOD.get(), BorealisBlocks.SWEETWOOD.get());
        smeltingResultFromBase(consumer, BorealisBlocks.STRIPPED_CARAMELIZED_LOG.get(), BorealisBlocks.STRIPPED_SWEETWOOD_LOG.get());
        smeltingResultFromBase(consumer, BorealisBlocks.STRIPPED_CARAMELIZED_WOOD.get(), BorealisBlocks.STRIPPED_SWEETWOOD.get());
        smeltingResultFromBase(consumer, BorealisBlocks.CARAMELIZED_STAIRS.get(), BorealisBlocks.SWEETWOOD_STAIRS.get());
        smeltingResultFromBase(consumer, BorealisBlocks.CARAMELIZED_SLAB.get(), BorealisBlocks.SWEETWOOD_SLAB.get());
        smeltingResultFromBase(consumer, BorealisBlocks.CARAMELIZED_FENCE.get(), BorealisBlocks.SWEETWOOD_FENCE.get());
        smeltingResultFromBase(consumer, BorealisBlocks.CARAMELIZED_FENCE_GATE.get(), BorealisBlocks.SWEETWOOD_FENCE_GATE.get());
        smeltingResultFromBase(consumer, BorealisBlocks.CARAMELIZED_BUTTON.get(), BorealisBlocks.SWEETWOOD_BUTTON.get());
        smeltingResultFromBase(consumer, BorealisBlocks.CARAMELIZED_PRESSURE_PLATE.get(), BorealisBlocks.SWEETWOOD_PRESSURE_PLATE.get());
        smeltingResultFromBase(consumer, BorealisBlocks.CARAMELIZED_DOOR.get(), BorealisBlocks.SWEETWOOD_DOOR.get());
        smeltingResultFromBase(consumer, BorealisBlocks.CARAMELIZED_TRAPDOOR.get(), BorealisBlocks.SWEETWOOD_TRAPDOOR.get());
        smeltingResultFromBase(consumer, BorealisItems.CARAMELIZED_BOAT.get(), BorealisItems.SWEETWOOD_BOAT.get());
        smeltingResultFromBase(consumer, BorealisBlocks.CARAMELIZED_SIGN.get(), BorealisBlocks.SWEETWOOD_SIGN.get());
    }
}
