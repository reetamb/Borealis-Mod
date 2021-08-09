package com.reetam.borealis.world.gen.trunkplacer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisFeatures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class RootedTrunkPlacer extends AbstractTrunkPlacer {

    public static final Codec<RootedTrunkPlacer> CODEC = RecordCodecBuilder.create((me) ->
            trunkPlacerParts(me).apply(me, RootedTrunkPlacer::new));

    public RootedTrunkPlacer(int baseHeight, int firstRandHeight, int secondRandHeight) {
        super(baseHeight, firstRandHeight, secondRandHeight);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return BorealisFeatures.TreePlacers.ROOTED_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.Foliage> placeTrunk(IWorldGenerationReader reader, Random rand, int y, BlockPos pos, Set<BlockPos> posSet, MutableBoundingBox boundingBox, BaseTreeFeatureConfig config) {
        int treeBaseHeight = config.trunkPlacer.getTreeHeight(rand);

        int rootHeight = rand.nextInt(2) + MathHelper.floor(treeBaseHeight / 5) + 1;
        int rootSplay = 2;
        BlockPos pos1 = pos;

        for (int i = 1; i <= rootSplay; i++) {
            reader.setBlock(pos1.below().north(i), BorealisBlocks.LIVING_SNOW_BLOCK.get().defaultBlockState(), 19);
            reader.setBlock(pos1.below().east(i), BorealisBlocks.LIVING_SNOW_BLOCK.get().defaultBlockState(), 19);
            reader.setBlock(pos1.below().south(i), BorealisBlocks.LIVING_SNOW_BLOCK.get().defaultBlockState(), 19);
            reader.setBlock(pos1.below().west(i), BorealisBlocks.LIVING_SNOW_BLOCK.get().defaultBlockState(), 19);

            reader.setBlock(pos1.below(2).north(i), BorealisBlocks.PERMAFROST.get().defaultBlockState(), 19);
            reader.setBlock(pos1.below(2).east(i), BorealisBlocks.PERMAFROST.get().defaultBlockState(), 19);
            reader.setBlock(pos1.below(2).south(i), BorealisBlocks.PERMAFROST.get().defaultBlockState(), 19);
            reader.setBlock(pos1.below(2).west(i), BorealisBlocks.PERMAFROST.get().defaultBlockState(), 19);
        }
        for (int i = 0; i < treeBaseHeight; i++) {
            if (i < rootHeight) {
                placeLog(reader, rand, pos1.west(rootSplay), posSet, boundingBox, config);
                placeLog(reader, rand, pos1.north(rootSplay), posSet, boundingBox, config);
                placeLog(reader, rand, pos1.east(rootSplay), posSet, boundingBox, config);
                placeLog(reader, rand, pos1.south(rootSplay), posSet, boundingBox, config);
            } else if (rootSplay > 0) {
                rootSplay--;
                placeLog(reader, rand, pos1.west(rootSplay), posSet, boundingBox, config);
                placeLog(reader, rand, pos1.north(rootSplay), posSet, boundingBox, config);
                placeLog(reader, rand, pos1.east(rootSplay), posSet, boundingBox, config);
                placeLog(reader, rand, pos1.south(rootSplay), posSet, boundingBox, config);
            } else {
                placeLog(reader, rand, pos1, posSet, boundingBox, config);
            }
            pos1 = pos1.above(1);
        }

        return ImmutableList.of(new FoliagePlacer.Foliage(pos.above(treeBaseHeight), 0, false));
    }
}
