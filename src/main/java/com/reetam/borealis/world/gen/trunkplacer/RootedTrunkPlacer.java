package com.reetam.borealis.world.gen.trunkplacer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class RootedTrunkPlacer extends TrunkPlacer {

    public static final Codec<RootedTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
            trunkPlacerParts(instance).apply(instance, RootedTrunkPlacer::new));

    public RootedTrunkPlacer(int baseHeight, int firstRandHeight, int secondRandHeight) {
        super(baseHeight, firstRandHeight, secondRandHeight);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return BorealisFeatures.TreePlacers.ROOTED_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource rand, int height, BlockPos pos, TreeConfiguration config) {
        int treeBaseHeight = config.trunkPlacer.getTreeHeight(rand);

        int rootHeight = rand.nextInt(2) + (int) Math.floor(treeBaseHeight / 5.0) + 1;
        int rootSplay = 2;
        BlockPos pos1 = pos;

        for (int i = -3; i < treeBaseHeight; i++) {
            if (i < rootHeight) {
                placeLog(level, blockSetter, rand, pos1.west(rootSplay), config);
                placeLog(level, blockSetter, rand, pos1.north(rootSplay), config);
                placeLog(level, blockSetter, rand, pos1.east(rootSplay), config);
                placeLog(level, blockSetter, rand, pos1.south(rootSplay), config);
            } else if (rootSplay > 0) {
                rootSplay--;
                placeLog(level, blockSetter, rand, pos1.west(rootSplay), config);
                placeLog(level, blockSetter, rand, pos1.north(rootSplay), config);
                placeLog(level, blockSetter, rand, pos1.east(rootSplay), config);
                placeLog(level, blockSetter, rand, pos1.south(rootSplay), config);
            } else {
                placeLog(level, blockSetter, rand, pos1, config);
            }
            pos1 = pos.above(i);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(treeBaseHeight), 0, false));
    }
}
