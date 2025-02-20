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

public class BreakBlockTrigger extends SimpleCriterionTrigger<BreakBlockTrigger.TriggerInstance> {
    @Override
    public Codec<BreakBlockTrigger.TriggerInstance> codec() {
        return BreakBlockTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer pPlayer, BlockState pState) {
        this.trigger(pPlayer, instance -> instance.matches(pState));
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<Holder<Block>> block) implements SimpleCriterionTrigger.SimpleInstance {
                    public static final Codec<BreakBlockTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                            instance -> instance.group(
                                    EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(BreakBlockTrigger.TriggerInstance::player),
                                    BuiltInRegistries.BLOCK.holderByNameCodec().optionalFieldOf("block").forGetter(BreakBlockTrigger.TriggerInstance::block))
                        .apply(instance, BreakBlockTrigger.TriggerInstance::new)
            );

        public static Criterion<BreakBlockTrigger.TriggerInstance> destroyedBlock(Block pBlock) {
            return BorealisTriggers.BREAK_BLOCK.get()
                    .createCriterion(
                            new BreakBlockTrigger.TriggerInstance(Optional.empty(), Optional.of(pBlock.builtInRegistryHolder())));
        }

        public boolean matches(BlockState pState) {
            if (pState.isEmpty() || this.block().isEmpty()) return false;
            return pState.is(this.block().get());
        }
    }
}
