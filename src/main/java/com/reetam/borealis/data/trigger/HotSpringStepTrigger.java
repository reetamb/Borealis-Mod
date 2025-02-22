package com.reetam.borealis.data.trigger;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class HotSpringStepTrigger extends SimpleCriterionTrigger<HotSpringStepTrigger.TriggerInstance> {
    @Override
    public Codec<HotSpringStepTrigger.TriggerInstance> codec() {
        return HotSpringStepTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer pPlayer, BlockState pState) {
        this.trigger(pPlayer, instance -> instance.matches(pState));
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<Holder<Block>> block) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<HotSpringStepTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(HotSpringStepTrigger.TriggerInstance::player),
                                BuiltInRegistries.BLOCK.holderByNameCodec().optionalFieldOf("block").forGetter(HotSpringStepTrigger.TriggerInstance::block))
                        .apply(instance, HotSpringStepTrigger.TriggerInstance::new)
        );

        public static Criterion<HotSpringStepTrigger.TriggerInstance> step(Block pBlock) {
            return BorealisTriggers.HOT_SPRING_STEP.get()
                    .createCriterion(
                            new HotSpringStepTrigger.TriggerInstance(Optional.empty(), Optional.of(pBlock.builtInRegistryHolder())));
        }

        public boolean matches(BlockState pState) {
            if (pState.isEmpty() || this.block().isEmpty()) return false;
            return pState.is(this.block().get());
        }
    }
}
