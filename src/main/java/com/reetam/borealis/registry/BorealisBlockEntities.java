package com.reetam.borealis.registry;

import com.mojang.datafixers.types.Type;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.entity.BorealisHangingSignBlockEntity;
import com.reetam.borealis.block.entity.BorealisSignBlockEntity;
import com.reetam.borealis.block.entity.KilnBlockEntity;
import com.reetam.borealis.block.entity.KilnRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class BorealisBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BorealisMod.MODID);

    public static final RegistryObject<BlockEntityType<BorealisSignBlockEntity>> BOREALIS_SIGN = BLOCK_ENTITIES.register("borealis_sign", () ->
            BlockEntityType.Builder.of(BorealisSignBlockEntity::new,
                    BorealisBlocks.BRUMAL_SIGN.get(),
                    BorealisBlocks.BRUMAL_WALL_SIGN.get(),
                    BorealisBlocks.FROSTFIR_SIGN.get(),
                    BorealisBlocks.FROSTFIR_WALL_SIGN.get(),
                    BorealisBlocks.SWEETWOOD_SIGN.get(),
                    BorealisBlocks.SWEETWOOD_WALL_SIGN.get(),
                    BorealisBlocks.CARAMELIZED_SIGN.get(),
                    BorealisBlocks.CARAMELIZED_WALL_SIGN.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<BorealisHangingSignBlockEntity>> BOREALIS_HANGING_SIGN = BLOCK_ENTITIES.register("borealis_hanging_sign", () ->
            BlockEntityType.Builder.of(BorealisHangingSignBlockEntity::new,
                    BorealisBlocks.BRUMAL_HANGING_SIGN.get(),
                    BorealisBlocks.BRUMAL_WALL_HANGING_SIGN.get(),
                    BorealisBlocks.FROSTFIR_HANGING_SIGN.get(),
                    BorealisBlocks.FROSTFIR_WALL_HANGING_SIGN.get(),
                    BorealisBlocks.SWEETWOOD_HANGING_SIGN.get(),
                    BorealisBlocks.SWEETWOOD_WALL_HANGING_SIGN.get(),
                    BorealisBlocks.CARAMELIZED_HANGING_SIGN.get(),
                    BorealisBlocks.CARAMELIZED_WALL_HANGING_SIGN.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<KilnBlockEntity>> KILN = BLOCK_ENTITIES.register("kiln", () ->
            BlockEntityType.Builder.of(KilnBlockEntity::new,
                    BorealisBlocks.KILN.get()).build(null));

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, BorealisMod.MODID);
    public static final RegistryObject<RecipeType<KilnRecipe>> KILN_RECIPE = RECIPE_TYPES.register("kiln", () -> RecipeType.register("kiln"));

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BorealisMod.MODID);
    // public static final RegistryObject<RecipeSerializer<KilnRecipe>> KILN_SERIALIZER = RECIPE_SERIALIZERS.register("kiln", () -> RecipeSerializer.register("kiln", new SimpleCookingSerializer<>(KilnRecipe::new, 100)));
}