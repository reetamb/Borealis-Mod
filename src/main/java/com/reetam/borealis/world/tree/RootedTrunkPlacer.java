package com.reetam.borealis.world.tree;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.world.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class RootedTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<RootedTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            trunkPlacerParts(instance).apply(instance, RootedTrunkPlacer::new));

    private final BlockStateProvider decoration;
    private final float probability;

    public RootedTrunkPlacer(int baseHeight, int firstRandHeight, int secondRandHeight, BlockStateProvider deco, float probability) {
        super(baseHeight, firstRandHeight, secondRandHeight);
        this.decoration = deco;
        this.probability = probability;
    }
    public RootedTrunkPlacer(int baseHeight, int firstRandHeight, int secondRandHeight) {
        super(baseHeight, firstRandHeight, secondRandHeight);
        this.decoration = BlockStateProvider.simple(BorealisBlocks.SOAPSTONE.get());
        this.probability = 1;
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
                radialLog(level, blockSetter, rand, pos1, rootSplay, config, i == rootHeight - 1);
            } else if (rootSplay > 0) {
                rootSplay--;
                radialLog(level, blockSetter, rand, pos1, rootSplay, config, true);
            } else {
                placeLog(level, blockSetter, rand, pos1, config);
            }
            pos1 = pos.above(i);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(treeBaseHeight), 0, false));
    }

    private void radialLog(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource rand, BlockPos pos, int distance, TreeConfiguration config, boolean canPlaceDecoration) {
        Direction.Plane.HORIZONTAL.stream().forEach((direction -> placeLog(level, blockSetter, rand, pos.relative(direction, distance), config)));
    }
}
