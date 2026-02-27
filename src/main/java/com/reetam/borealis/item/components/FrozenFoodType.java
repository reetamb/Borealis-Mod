package com.reetam.borealis.item.components;

import com.reetam.borealis.BorealisMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;
import java.util.function.IntFunction;

public enum FrozenFoodType implements StringRepresentable, TooltipProvider {
    MEAT("meat", 0),
    PRODUCE("produce", 1),
    STEW("stew", 2),
    MUSH("mush", 3)
    ;


    public static final StringRepresentable.EnumCodec<FrozenFoodType> CODEC = StringRepresentable.fromEnum(FrozenFoodType::values);
    static final IntFunction<FrozenFoodType> BY_ID = ByIdMap.continuous(FrozenFoodType::id, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StreamCodec<ByteBuf, FrozenFoodType> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, FrozenFoodType::id);

    private final String name;
    private final int id;
    private final ResourceLocation texture;

    FrozenFoodType(String name, int id) {
        this.name = name;
        this.id = id;
        this.texture = ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "frozen_" + name);
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public ResourceLocation texture() {
        return texture;
    }

    public int id() {
        return id;
    }

    @Override
    public void addToTooltip(Item.TooltipContext tooltipContext, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
        consumer.accept(Component.translatable("item.borealis.frozen_food_tooltip"));
    }
}
