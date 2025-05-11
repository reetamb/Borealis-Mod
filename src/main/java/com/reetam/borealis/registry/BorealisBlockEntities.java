package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.sign.BorealisHangingSignBlockEntity;
import com.reetam.borealis.block.sign.BorealisSignBlockEntity;
import com.reetam.borealis.block.kiln.KilnBlockEntity;
import com.reetam.borealis.block.kiln.KilnRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class BorealisBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, BorealisMod.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BorealisSignBlockEntity>> BOREALIS_SIGN = BLOCK_ENTITIES.register("borealis_sign", () ->
            BlockEntityType.Builder.of(BorealisSignBlockEntity::new,
                    BorealisBlocks.BRUMAL_SIGN.get(),
                    BorealisBlocks.BRUMAL_WALL_SIGN.get(),
                    BorealisBlocks.SWEETWOOD_SIGN.get(),
                    BorealisBlocks.SWEETWOOD_WALL_SIGN.get(),
                    BorealisBlocks.CARAMELIZED_SIGN.get(),
                    BorealisBlocks.CARAMELIZED_WALL_SIGN.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BorealisHangingSignBlockEntity>> BOREALIS_HANGING_SIGN = BLOCK_ENTITIES.register("borealis_hanging_sign", () ->
            BlockEntityType.Builder.of(BorealisHangingSignBlockEntity::new,
                    BorealisBlocks.BRUMAL_HANGING_SIGN.get(),
                    BorealisBlocks.BRUMAL_WALL_HANGING_SIGN.get(),
                    BorealisBlocks.SWEETWOOD_HANGING_SIGN.get(),
                    BorealisBlocks.SWEETWOOD_WALL_HANGING_SIGN.get(),
                    BorealisBlocks.CARAMELIZED_HANGING_SIGN.get(),
                    BorealisBlocks.CARAMELIZED_WALL_HANGING_SIGN.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<KilnBlockEntity>> KILN = BLOCK_ENTITIES.register("kiln", () ->
            BlockEntityType.Builder.of(KilnBlockEntity::new,
                    BorealisBlocks.KILN.get()).build(null));

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, BorealisMod.MODID);
    public static final DeferredHolder<RecipeType<?>, RecipeType<KilnRecipe>> KILN_RECIPE = RECIPE_TYPES.register("kiln", () -> RecipeType.register("kiln"));

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, BorealisMod.MODID);
    // public static final RegistryObject<RecipeSerializer<KilnRecipe>> KILN_SERIALIZER = RECIPE_SERIALIZERS.register("kiln", () -> RecipeSerializer.register("kiln", new SimpleCookingSerializer<>(KilnRecipe::new, 100)));
}