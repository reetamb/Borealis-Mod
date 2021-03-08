package com.reetam.borealis.world.gen.trunkplacer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.BorealisTreePlacers;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class RootedTrunkPlacer extends AbstractTrunkPlacer {
    int providedRootHeight;
    int providedRootSplay;

    public static final Codec<RootedTrunkPlacer> CODEC = RecordCodecBuilder.create((me) ->
            func_236915_a_(me).apply(me, RootedTrunkPlacer::new));

    public RootedTrunkPlacer(int baseHeight, int firstRandHeight, int secondRandHeight) {
        super(baseHeight, firstRandHeight, secondRandHeight);
    }

    @Override
    protected TrunkPlacerType<?> func_230381_a_() {
        return BorealisTreePlacers.ROOTED_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.Foliage> func_230382_a_(IWorldGenerationReader reader, Random rand, int y, BlockPos pos, Set<BlockPos> posSet, MutableBoundingBox boundingBox, BaseTreeFeatureConfig config) {
        int treeBaseHeight = config.trunkPlacer.func_236917_a_(rand);

        int rootHeight = rand.nextInt(2) + MathHelper.floor(treeBaseHeight / 5) + 1;
        int rootSplay = 2;
        BlockPos pos1 = pos;

        for (int i = 0; i < treeBaseHeight; i++) {
            if (i < rootHeight) {
                func_236911_a_(reader, rand, pos1.west(rootSplay), posSet, boundingBox, config);
                func_236911_a_(reader, rand, pos1.north(rootSplay), posSet, boundingBox, config);
                func_236911_a_(reader, rand, pos1.east(rootSplay), posSet, boundingBox, config);
                func_236911_a_(reader, rand, pos1.south(rootSplay), posSet, boundingBox, config);
            } else if (rootSplay > 0) {
                rootSplay--;
                func_236911_a_(reader, rand, pos1.west(rootSplay), posSet, boundingBox, config);
                func_236911_a_(reader, rand, pos1.north(rootSplay), posSet, boundingBox, config);
                func_236911_a_(reader, rand, pos1.east(rootSplay), posSet, boundingBox, config);
                func_236911_a_(reader, rand, pos1.south(rootSplay), posSet, boundingBox, config);
            } else {
                func_236911_a_(reader, rand, pos1, posSet, boundingBox, config);
            }
            pos1 = pos1.up(1);
        }

        return ImmutableList.of(new FoliagePlacer.Foliage(pos.up(treeBaseHeight), 0, false));
    }
}
