package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.data.trigger.BreakBlockTrigger;
import com.reetam.borealis.data.trigger.HailstoneTrigger;
import com.reetam.borealis.data.trigger.HotSpringStepTrigger;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import com.reetam.borealis.registry.world.BorealisDimensions;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biomes;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BorealisAdvancements extends AdvancementProvider {
    public BorealisAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new Advancements()));
    }

    protected static class Advancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
            AdvancementHolder borealisRoot = Advancement.Builder.advancement()
                    .display(BorealisBlocks.HAILSTONE.get(),
                            name("borealis_root"),
                            desc("borealis_root"),
                            ResourceLocation.withDefaultNamespace("textures/block/snow.png"),
                            AdvancementType.TASK, false, false, false)
                    .requirements(AdvancementRequirements.anyOf(List.of("in_borealis", "wind_charge", "snowy_plains")))
                    .addCriterion("in_borealis", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(BorealisDimensions.BOREALIS))
                    .addCriterion("wind_charge", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WIND_CHARGE))
                    .addCriterion("snowy_plains", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(provider.holderOrThrow(Biomes.SNOWY_PLAINS))))
                    .save(consumer, loc("borealis_root"));

            AdvancementHolder getHailstone = Advancement.Builder.advancement()
                    .display(BorealisItems.HAILSTONE.get(),
                            name("get_hailstone"),
                            desc("get_hailstone"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("get_hailstone", InventoryChangeTrigger.TriggerInstance.hasItems(BorealisItems.HAILSTONE.get()))
                    .parent(borealisRoot)
                    .save(consumer, loc("get_hailstone"));

            AdvancementHolder enterBorealis = Advancement.Builder.advancement()
                    .display(BorealisBlocks.FIRN.get(),
                            name("enter_borealis"),
                            desc("enter_borealis"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("enter_borealis", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(BorealisDimensions.BOREALIS))
                    .parent(getHailstone)
                    .save(consumer, loc("enter_borealis"));

            AdvancementHolder kyanite = Advancement.Builder.advancement()
                    .display(BorealisItems.KYANITE_CRYSTAL.get(),
                            name("get_kyanite"),
                            desc("get_kyanite"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("get_kyanite", InventoryChangeTrigger.TriggerInstance.hasItems(BorealisItems.KYANITE_CRYSTAL.get()))
                    .parent(enterBorealis)
                    .save(consumer, loc("get_kyanite"));

            AdvancementHolder portal = Advancement.Builder.advancement()
                    .display(BorealisBlocks.KYANITE_FLAGSTONE.get(),
                            name("make_portal"),
                            desc("make_portal"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("make_portal", EnterBlockTrigger.TriggerInstance.entersBlock(BorealisBlocks.BOREALIS_PORTAL.get()))
                    .parent(kyanite)
                    .save(consumer, loc("make_portal"));

            AdvancementHolder icarian = Advancement.Builder.advancement()
                    .display(new ItemStack(provider.holderOrThrow(ResourceKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.withDefaultNamespace("elytra"))), 1, DataComponentPatch.builder().set(DataComponents.DAMAGE, 432).build()),
                            name("icarian"),
                            desc("icarian"),
                            null,
                            AdvancementType.CHALLENGE, true, true, true)
                    .addCriterion("used_hailstone", HailstoneTrigger.TriggerInstance.usedHailstone(ItemPredicate.Builder.item().of(BorealisItems.HAILSTONE.get()), MinMaxBounds.Doubles.between(-1 * BorealisMod.MIN_HEIGHT, BorealisMod.MIN_HEIGHT), BorealisDimensions.BOREALIS))
                    .parent(getHailstone)
                    .save(consumer, loc("icarian"));

            AdvancementHolder shearPlant = Advancement.Builder.advancement()
                    .display(BorealisBlocks.BRUMELIAD.get(),
                            name("shear_plant"),
                            desc("shear_plant"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .requirements(AdvancementRequirements.anyOf(List.of("shear_brumeliad", "shear_fiddle", "shear_viola", "shear_cello")))
                    .addCriterion("shear_brumeliad", InventoryChangeTrigger.TriggerInstance.hasItems(BorealisBlocks.BRUMELIAD.get().asItem(), Items.TORCHFLOWER))
                    .addCriterion("shear_fiddle", InventoryChangeTrigger.TriggerInstance.hasItems(BorealisBlocks.WINTER_FIDDLE.get().asItem(), Items.PITCHER_PLANT))
                    .addCriterion("shear_viola", InventoryChangeTrigger.TriggerInstance.hasItems(BorealisBlocks.WINTER_VIOLA.get().asItem(), Items.PITCHER_PLANT))
                    .addCriterion("shear_cello", InventoryChangeTrigger.TriggerInstance.hasItems(BorealisBlocks.WINTER_CELLO.get().asItem(), Items.PITCHER_PLANT))
                    .parent(enterBorealis)
                    .save(consumer, loc("shear_plant"));

            AdvancementHolder stripFrostfir = Advancement.Builder.advancement()
                    .display(BorealisBlocks.BONE_DRY_WOOD.get(),
                            name("strip_frostfir"),
                            desc("strip_frostfir"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("strip_frostfir", BreakBlockTrigger.TriggerInstance.destroyedBlock(BorealisBlocks.PETRIFIED_WOOD.get()))
                    .parent(enterBorealis)
                    .save(consumer, loc("strip_frostfir"));

            AdvancementHolder breakPumice = Advancement.Builder.advancement()
                    .display(BorealisBlocks.PUMICE.get(),
                            name("break_pumice"),
                            desc("break_pumice"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("break_pumice", HotSpringStepTrigger.TriggerInstance.step(BorealisBlocks.PUMICE.get()))
                    .parent(enterBorealis)
                    .save(consumer, loc("break_pumice"));

            AdvancementHolder walkGeyser = Advancement.Builder.advancement()
                    .display(BorealisBlocks.PUMICE_GEYSER.get(),
                            name("walk_geyser"),
                            desc("walk_geyser"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("walk_geyser", HotSpringStepTrigger.TriggerInstance.step(BorealisBlocks.PUMICE_GEYSER.get()))
                    .parent(breakPumice)
                    .save(consumer, loc("walk_geyser"));

            AdvancementHolder walkKaolin = Advancement.Builder.advancement()
                    .display(BorealisBlocks.KAOLIN.get(),
                            name("walk_kaolin"),
                            desc("walk_kaolin"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("walk_kaolin", HotSpringStepTrigger.TriggerInstance.step(BorealisBlocks.KAOLIN.get()))
                    .parent(breakPumice)
                    .save(consumer, loc("walk_kaolin"));
        }

        private Component name(String name) {
            return Component.translatable("advancement." + BorealisMod.MODID + "." + name);
        }

        private Component desc(String name) {
            return Component.translatable("advancement." + BorealisMod.MODID + "." + name + ".desc");
        }

        private String loc(String name) {
            return ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, name).toString();
        }
    }
}
