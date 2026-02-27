package com.reetam.borealis.modify.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ThawSmeltingSerializer<T extends ThawSmeltingRecipe> implements RecipeSerializer<T> {
    private final ThawSmeltingRecipe.Factory<T> factory;
    private final MapCodec<T> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;

    public ThawSmeltingSerializer(ThawSmeltingRecipe.Factory<T> factory) {
        this.factory = factory;
        this.codec = RecordCodecBuilder.mapCodec((instance) -> {
            return instance.group(
                    DataComponentType.CODEC.fieldOf("component").orElse(BorealisItems.Components.FROZEN.get()).forGetter((recipe) -> {
                    return recipe.component;
                }), Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter((recipe) -> {
                    return recipe.getExperience();
                }), Codec.INT.fieldOf("cookingtime").orElse(200).forGetter((recipe) -> {
                    return recipe.getCookingTime();
                })
            ).apply(instance, factory::create);
        });
        this.streamCodec = StreamCodec.of(this::toNetwork, this::fromNetwork);
    }

    public MapCodec<T> codec() {
        return this.codec;
    }

    public StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
        return this.streamCodec;
    }

    private T fromNetwork(RegistryFriendlyByteBuf buf) {
        DataComponentType<?> dataComponentType = DataComponentType.STREAM_CODEC.decode(buf);
        float experience = buf.readFloat();
        int cookingTime = buf.readVarInt();
        return this.factory.create(dataComponentType, experience, cookingTime);
    }

    private void toNetwork(RegistryFriendlyByteBuf buf, T recipe) {
        DataComponentType.STREAM_CODEC.encode(buf, recipe.component);
        buf.writeFloat(recipe.getExperience());
        buf.writeVarInt(recipe.getCookingTime());
    }

    public ThawSmeltingRecipe create(DataComponentType<?> dataComponentType, float experience, int cookingTime) {
        return this.factory.create(dataComponentType, experience, cookingTime);
    }
}
