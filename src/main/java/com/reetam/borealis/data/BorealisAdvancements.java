package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisDimensions;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.ChangeDimensionTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BorealisAdvancements extends AdvancementProvider {
    public BorealisAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new Advancements()));
    }

    protected static class Advancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer) {
            Advancement borealisRoot = Advancement.Builder.advancement()
                    .display(BorealisBlocks.HAILSTONE.get(),
                            name("borealis_root"),
                            desc("borealis_root"),
                            new ResourceLocation("textures/block/snow.png"),
                            FrameType.TASK, false, false, false)
                    .addCriterion("borealis_root", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(BorealisDimensions.BOREALIS))
                    .save(consumer, loc("borealis_root"));

            Advancement getHailstone = Advancement.Builder.advancement()
                    .display(BorealisItems.HAILSTONE.get(),
                            name("get_hailstone"),
                            desc("get_hailstone"),
                            null,
                            FrameType.TASK, true, true, false)
                    .addCriterion("get_hailstone", InventoryChangeTrigger.TriggerInstance.hasItems(BorealisItems.HAILSTONE.get()))
                    .save(consumer, loc("get_hailstone"));
        }

        private Component name(String name) {
            return Component.translatable("advancement." + BorealisMod.MODID + "." + name);
        }

        private Component desc(String name) {
            return Component.translatable("advancement." + BorealisMod.MODID + "." + name + ".desc");
        }

        private String loc(String name) {
            return new ResourceLocation(BorealisMod.MODID, name).toString();
        }
    }
}
