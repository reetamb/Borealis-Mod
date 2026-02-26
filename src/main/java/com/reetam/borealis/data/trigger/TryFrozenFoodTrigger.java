package com.reetam.borealis.data.trigger;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class TryFrozenFoodTrigger extends SimpleCriterionTrigger<TryFrozenFoodTrigger.TriggerInstance> {

    @Override
    public Codec<TryFrozenFoodTrigger.TriggerInstance> codec() {
        return TryFrozenFoodTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer pPlayer, ItemStack pHeldItem) {
        this.trigger(pPlayer, instance -> instance.matches(pHeldItem));
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> item) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<TryFrozenFoodTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TryFrozenFoodTrigger.TriggerInstance::player),
                                ItemPredicate.CODEC.optionalFieldOf("item").forGetter(TryFrozenFoodTrigger.TriggerInstance::item))
                        .apply(instance, TryFrozenFoodTrigger.TriggerInstance::new)
        );

        public static Criterion<TryFrozenFoodTrigger.TriggerInstance> triedFrozenFood(ItemPredicate.Builder pItem) {
            return BorealisTriggers.TRY_FROZEN_FOOD.get()
                    .createCriterion(
                            new TryFrozenFoodTrigger.TriggerInstance(Optional.empty(), Optional.of(pItem.build())));
        }

        public boolean matches(ItemStack pHeldItem) {
            return pHeldItem.has(BorealisItems.Components.FROZEN) && pHeldItem.has(DataComponents.FOOD);
        }
    }
}
