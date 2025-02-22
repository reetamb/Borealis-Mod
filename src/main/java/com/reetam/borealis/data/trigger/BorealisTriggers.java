package com.reetam.borealis.data.trigger;

import com.reetam.borealis.BorealisMod;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BorealisTriggers {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, BorealisMod.MODID);

    public static final DeferredHolder<CriterionTrigger<?>, BreakBlockTrigger> BREAK_BLOCK = TRIGGERS.register("break_block", BreakBlockTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, HailstoneTrigger> USE_HAILSTONE = TRIGGERS.register("use_hailstone", HailstoneTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, HotSpringStepTrigger> HOT_SPRING_STEP = TRIGGERS.register("kaolin_step", HotSpringStepTrigger::new);
}
