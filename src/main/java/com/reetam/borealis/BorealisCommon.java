package com.reetam.borealis;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisFluids;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidInteractionRegistry;

public class BorealisCommon {
    public static void registerDispenserBehaviors() {
        final DefaultDispenseItemBehavior eggBehavior = new DefaultDispenseItemBehavior() {
            public ItemStack execute(BlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem)stack.getItem()).getType(stack.getTag());
                type.spawn(source.getLevel(), stack, null, source.getPos().relative(direction), MobSpawnType.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);
                return stack;
            }
        };

        DispenseItemBehavior bucketBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(BlockSource source, ItemStack stack) {
                BucketItem bucketitem = (BucketItem)stack.getItem();
                BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                Level level = source.getLevel();
                if (bucketitem.emptyContents(null, level, blockpos, null)) {
                    bucketitem.checkExtraContent(null, level, stack, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultBehavior.dispense(source, stack);
                }
            }
        };

        DispenserBlock.registerBehavior(BorealisItems.HUMMINGBIRD_SPAWN_EGG.get(), eggBehavior);
        DispenserBlock.registerBehavior(BorealisItems.TAKAHE_SPAWN_EGG.get(), eggBehavior);

        DispenserBlock.registerBehavior(BorealisFluids.HOT_SPRING_WATER_BUCKET.get(), bucketBehavior);
        DispenserBlock.registerBehavior(BorealisFluids.QUICKSILVER_BUCKET.get(), bucketBehavior);
        DispenserBlock.registerBehavior(BorealisFluids.SLUSH_BUCKET.get(), bucketBehavior);
    }

    public static void registerWoodTypes() {
        WoodType.register(BorealisBlocks.WOODSET_BRUMAL);
        WoodType.register(BorealisBlocks.WOODSET_FROSTFIR);
        WoodType.register(BorealisBlocks.WOODSET_SWEETWOOD);
    }

    public static void registerFlowerPots() {
        FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;

        pot.addPlant(BorealisBlocks.BRUMAL_SAPLING.getId(), BorealisBlocks.POTTED_BRUMAL_SAPLING);
        pot.addPlant(BorealisBlocks.FROSTFIR_SAPLING.getId(), BorealisBlocks.POTTED_FROSTFIR_SAPLING);
        pot.addPlant(BorealisBlocks.SWEETWOOD_SAPLING.getId(), BorealisBlocks.POTTED_SWEETWOOD_SAPLING);
    }

    public static void registerFluidInteractions() {
        // quicksilver turns into cinnabar
        FluidInteractionRegistry.addInteraction(
                BorealisFluids.QUICKSILVER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                        ForgeMod.LAVA_TYPE.get(), BorealisBlocks.CINNABAR.get().defaultBlockState()));
        FluidInteractionRegistry.addInteraction(
                BorealisFluids.QUICKSILVER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                        BorealisFluids.SLUSH_TYPE.get(), BorealisBlocks.CINNABAR.get().defaultBlockState()));
        FluidInteractionRegistry.addInteraction(
                BorealisFluids.QUICKSILVER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                        BorealisFluids.HOT_SPRING_WATER_TYPE.get(), BorealisBlocks.CINNABAR.get().defaultBlockState()));
        // lava and hot spring water makes pumice
        FluidInteractionRegistry.addInteraction(
                ForgeMod.LAVA_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                        BorealisFluids.HOT_SPRING_WATER_TYPE.get(), BorealisBlocks.PUMICE.get().defaultBlockState()));
        // slush makes ice
        FluidInteractionRegistry.addInteraction(
                BorealisFluids.SLUSH_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                        BorealisFluids.HOT_SPRING_WATER_TYPE.get(), Blocks.PACKED_ICE.defaultBlockState()));
        FluidInteractionRegistry.addInteraction(
                BorealisFluids.SLUSH_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                        ForgeMod.WATER_TYPE.get(), Blocks.BLUE_ICE.defaultBlockState()));
        // slush over lava is smooth basalt
        FluidInteractionRegistry.addInteraction(
                ForgeMod.LAVA_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                        BorealisFluids.SLUSH_TYPE.get(), (fluidState) -> fluidState.isSource() ?
                        Blocks.SMOOTH_BASALT.defaultBlockState() :
                        Blocks.BASALT.defaultBlockState()));
    }

    public static void registerAxeStrips() {
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);

        AxeItem.STRIPPABLES.put(BorealisBlocks.BRUMAL_LOG.get(), BorealisBlocks.STRIPPED_BRUMAL_LOG.get());
        AxeItem.STRIPPABLES.put(BorealisBlocks.BRUMAL_WOOD.get(), BorealisBlocks.STRIPPED_BRUMAL_WOOD.get());
        AxeItem.STRIPPABLES.put(BorealisBlocks.FROSTFIR_LOG.get(), BorealisBlocks.STRIPPED_FROSTFIR_LOG.get());
        AxeItem.STRIPPABLES.put(BorealisBlocks.FROSTFIR_WOOD.get(), BorealisBlocks.STRIPPED_FROSTFIR_WOOD.get());
        AxeItem.STRIPPABLES.put(BorealisBlocks.SWEETWOOD_LOG.get(), BorealisBlocks.STRIPPED_SWEETWOOD_LOG.get());
        AxeItem.STRIPPABLES.put(BorealisBlocks.SWEETWOOD.get(), BorealisBlocks.STRIPPED_SWEETWOOD.get());
    }

    public static void registerHoeTills() {
        HoeItem.TILLABLES = Maps.newHashMap(HoeItem.TILLABLES);

        HoeItem.TILLABLES.put(BorealisBlocks.PERMAFROST_RUBBLE.get(), Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(BorealisBlocks.PERMAFROST.get().defaultBlockState())));
    }

    public static void registerComposts() {
        ComposterBlock.add(0.3F, BorealisBlocks.BRUMAL_LEAVES.get());
        ComposterBlock.add(0.3F, BorealisBlocks.FROSTFIR_LEAVES.get());
        ComposterBlock.add(0.3F, BorealisBlocks.SWEETWOOD_LEAVES.get());
        ComposterBlock.add(0.3F, BorealisBlocks.BRUMAL_SAPLING.get());
        ComposterBlock.add(0.3F, BorealisBlocks.FROSTFIR_SAPLING.get());
        ComposterBlock.add(0.3F, BorealisBlocks.SWEETWOOD_SAPLING.get());
    }
}
