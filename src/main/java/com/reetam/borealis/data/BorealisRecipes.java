package com.reetam.borealis.data;

import com.reetam.borealis.block.BorealisPortalBlock;
import com.reetam.borealis.data.provider.BorealisRecipeProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BorealisRecipes extends BorealisRecipeProvider {

    public BorealisRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
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
                BorealisBlocks.brumal_trapdoor
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
                BorealisBlocks.frostfir_trapdoor
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
                BorealisBlocks.saccharine_trapdoor
        );
    }

    public void bulkWood(Consumer<IFinishedRecipe> consumer, Supplier<? extends Block> logIn, Supplier<? extends Block> plankIn, Supplier<? extends Block> woodIn, Supplier<? extends Block> stripLogIn, Supplier<? extends Block> stripWoodIn, Supplier<? extends Block> stairsIn, Supplier<? extends Block> slabIn, Supplier<? extends Block> fenceIn, Supplier<? extends Block> gateIn, Supplier<? extends Block> buttonIn, Supplier<? extends Block> plateIn, Supplier<? extends Block> doorIn, Supplier<? extends Block> trapdoorIn) {
        ShapelessRecipeBuilder.shapelessRecipe(plankIn.get(), 4)
                .addIngredient(logIn.get())
                .addCriterion(has(logIn), hasItem(logIn.get()))
                .build(consumer, name(plankIn));

        wood(logIn, woodIn).build(consumer, name(woodIn));
        wood(stripLogIn, stripWoodIn).build(consumer, name(stripWoodIn));
        stairs(plankIn, stairsIn).build(consumer, name(stairsIn));
        slab(plankIn, slabIn).build(consumer, name(slabIn));
        fence(plankIn, fenceIn).build(consumer, name(fenceIn));
        gate(plankIn, gateIn).build(consumer, name(gateIn));
        button(plankIn, buttonIn).build(consumer, name(buttonIn));
        plate(plankIn, plateIn).build(consumer, name(plateIn));
        door(plankIn, doorIn).build(consumer, name(doorIn));
        trapdoor(plankIn, trapdoorIn).build(consumer, name(trapdoorIn));
    }
}
