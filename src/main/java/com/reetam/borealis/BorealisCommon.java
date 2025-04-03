package com.reetam.borealis;

import com.reetam.borealis.entity.HummingbirdEntity;
import com.reetam.borealis.entity.TakaheEntity;
import com.reetam.borealis.entity.ThrusherEntity;
import com.reetam.borealis.entity.TuberEntity;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;
import com.reetam.borealis.registry.BorealisFluids;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BorealisCommon {
    public static void registerDispenserBehaviors() {
        final DefaultDispenseItemBehavior eggBehavior = new DefaultDispenseItemBehavior() {
            public ItemStack execute(BlockSource source, ItemStack stack) {
                Direction direction = source.state().getValue(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem)stack.getItem()).getType(stack);
                type.spawn(source.level(), stack, null, source.pos().relative(direction), MobSpawnType.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);
                return stack;
            }
        };

        DispenseItemBehavior bucketBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(BlockSource source, ItemStack stack) {
                BucketItem bucketitem = (BucketItem)stack.getItem();
                BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                Level level = source.level();
                if (bucketitem.emptyContents(null, level, blockpos, null)) {
                    bucketitem.checkExtraContent(null, level, stack, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultBehavior.dispense(source, stack);
                }
            }
        };

        DispenserBlock.registerBehavior(BorealisEntities.HUMMINGBIRD_SPAWN_EGG.get(), eggBehavior);
        DispenserBlock.registerBehavior(BorealisEntities.TAKAHE_SPAWN_EGG.get(), eggBehavior);
        DispenserBlock.registerBehavior(BorealisEntities.THRUSHER_SPAWN_EGG.get(), eggBehavior);
        DispenserBlock.registerBehavior(BorealisEntities.TUBER_SPAWN_EGG.get(), eggBehavior);

        DispenserBlock.registerBehavior(BorealisFluids.HOT_SPRING_WATER_BUCKET.get(), bucketBehavior);
        DispenserBlock.registerBehavior(BorealisFluids.QUICKSILVER_BUCKET.get(), bucketBehavior);
        DispenserBlock.registerBehavior(BorealisFluids.SLUSH_BUCKET.get(), bucketBehavior);
    }

    public static void registerWoodTypes() {
        WoodType.register(BorealisBlocks.WOODSET_BRUMAL);
        WoodType.register(BorealisBlocks.WOODSET_CARAMELIZED);
        WoodType.register(BorealisBlocks.WOODSET_SWEETWOOD);
    }

    public static void registerFluidInteractions() {
        // quicksilver turns into cinnabar
        FluidInteractionRegistry.addInteraction(
                BorealisFluids.QUICKSILVER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                        NeoForgeMod.LAVA_TYPE.value(), BorealisBlocks.CINNABAR.get().defaultBlockState()));
        FluidInteractionRegistry.addInteraction(
                BorealisFluids.QUICKSILVER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                        BorealisFluids.SLUSH_TYPE.get(), BorealisBlocks.CINNABAR.get().defaultBlockState()));
        FluidInteractionRegistry.addInteraction(
                BorealisFluids.QUICKSILVER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                        BorealisFluids.HOT_SPRING_WATER_TYPE.get(), BorealisBlocks.CINNABAR.get().defaultBlockState()));
        // lava and hot spring water makes pumice
        FluidInteractionRegistry.addInteraction(
                NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
                        BorealisFluids.HOT_SPRING_WATER_TYPE.get(), BorealisBlocks.PUMICE.get().defaultBlockState()));
        // slush makes ice
        FluidInteractionRegistry.addInteraction(
                BorealisFluids.SLUSH_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                        BorealisFluids.HOT_SPRING_WATER_TYPE.get(), Blocks.PACKED_ICE.defaultBlockState()));
        FluidInteractionRegistry.addInteraction(
                BorealisFluids.SLUSH_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                        NeoForgeMod.WATER_TYPE.value(), Blocks.BLUE_ICE.defaultBlockState()));
        // slush over lava is smooth basalt
        FluidInteractionRegistry.addInteraction(
                NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
                        BorealisFluids.SLUSH_TYPE.get(), (fluidState) -> fluidState.isSource() ?
                        Blocks.SMOOTH_BASALT.defaultBlockState() :
                        Blocks.BASALT.defaultBlockState()));
    }
    public static void toolInteractions(BlockEvent.BlockToolModificationEvent event) {
        ItemAbility action = event.getItemAbility();
        BlockState state = event.getState();

        Map<Block, Block> STRIPPABLES = new HashMap<>();
        STRIPPABLES.put(BorealisBlocks.BRUMAL_LOG.get(), BorealisBlocks.STRIPPED_BRUMAL_LOG.get());
        STRIPPABLES.put(BorealisBlocks.BRUMAL_WOOD.get(), BorealisBlocks.STRIPPED_BRUMAL_WOOD.get());
        STRIPPABLES.put(BorealisBlocks.SWEETWOOD_LOG.get(), BorealisBlocks.STRIPPED_SWEETWOOD_LOG.get());
        STRIPPABLES.put(BorealisBlocks.SWEETWOOD.get(), BorealisBlocks.STRIPPED_SWEETWOOD.get());
        STRIPPABLES.put(BorealisBlocks.CARAMELIZED_LOG.get(), BorealisBlocks.STRIPPED_CARAMELIZED_LOG.get());
        STRIPPABLES.put(BorealisBlocks.CARAMELIZED_WOOD.get(), BorealisBlocks.STRIPPED_CARAMELIZED_WOOD.get());

        if (!event.isSimulated()) {
            if (action == ItemAbilities.AXE_STRIP) {
                if (STRIPPABLES.containsKey(state.getBlock())) {
                    event.setFinalState(STRIPPABLES.get(event.getState().getBlock()).defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)));
                }
            }
        }
    }

    public static void spawnRestrictions(RegisterSpawnPlacementsEvent event) {
        event.register(BorealisEntities.HUMMINGBIRD.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HummingbirdEntity::spawningRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(BorealisEntities.TUBER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TuberEntity::spawningRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(BorealisEntities.THRUSHER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ThrusherEntity::spawningRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(BorealisEntities.TAKAHE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TakaheEntity::spawningRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

//        BorealisEntities.ENTITIES.getEntries().forEach((entityType) -> {
//            if (entityType.get().getCategory() != MobCategory.MISC) {
//                event.register(entityType.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
//                        (entity, level, reason, pos, random) -> {
//                            try {
//                                return (boolean) entityType.get().getBaseClass()
//                                        .getMethod("spawningRules", EntityType.class, ServerLevelAccessor.class, MobSpawnType.class, BlockPos.class, RandomSource.class)
//                                        .invoke(null, entity, level, reason, pos, random);
//                            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }, RegisterSpawnPlacementsEvent.Operation.REPLACE);
//            }
//        });
    }
}
