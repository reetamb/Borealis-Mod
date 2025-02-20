package com.reetam.borealis.data.trigger;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class HailstoneTrigger extends SimpleCriterionTrigger<HailstoneTrigger.TriggerInstance> {

    @Override
    public Codec<HailstoneTrigger.TriggerInstance> codec() {
        return HailstoneTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer pPlayer, ItemStack pHeldItem, ResourceKey<Level> pDimensionIn) {
        this.trigger(pPlayer, instance -> instance.matches(pHeldItem, pDimensionIn, pPlayer.getEyePosition()));
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> item, MinMaxBounds.Doubles height, Optional<ResourceKey<Level>> from) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<HailstoneTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(HailstoneTrigger.TriggerInstance::player),
                        ItemPredicate.CODEC.optionalFieldOf("item").forGetter(HailstoneTrigger.TriggerInstance::item),
                        MinMaxBounds.Doubles.CODEC
                                .optionalFieldOf("height", MinMaxBounds.Doubles.ANY)
                                .forGetter(HailstoneTrigger.TriggerInstance::height),
                        ResourceKey.codec(Registries.DIMENSION).optionalFieldOf("from").forGetter(HailstoneTrigger.TriggerInstance::from))
                        .apply(instance, HailstoneTrigger.TriggerInstance::new)
        );

        public static Criterion<HailstoneTrigger.TriggerInstance> usedHailstone(ItemPredicate.Builder pItem, MinMaxBounds.Doubles pHeight, ResourceKey<Level> pFrom) {
            return BorealisTriggers.USE_HAILSTONE.get()
                    .createCriterion(
                            new HailstoneTrigger.TriggerInstance(Optional.empty(), Optional.of(pItem.build()), pHeight, Optional.of(pFrom)));
        }

        public boolean matches(ItemStack pHeldItem, ResourceKey<Level> pDimensionIn, Vec3 pPlayerPos) {
            if (this.item().isEmpty() || this.from().isEmpty()) return false;
            return this.height().matches(pPlayerPos.y()) && this.item().get().test(pHeldItem) && this.from().get().equals(pDimensionIn);
        }
    }

}
