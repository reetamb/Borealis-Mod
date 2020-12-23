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
    boolean provided = false;
    int providedRootHeight;
    int providedRootSplay;

    public static final Codec<RootedTrunkPlacer> CODEC = RecordCodecBuilder.create((me) ->
            func_236915_a_(me).apply(me, RootedTrunkPlacer::new));

    public RootedTrunkPlacer(int baseHeight, int firstRandHeight, int secondRandHeight) {
        super(baseHeight, firstRandHeight, secondRandHeight);
    }

    public RootedTrunkPlacer(int baseHeight, int firstRandHeight, int secondRandHeight, int rootHeightIn, int rootSplayIn) {
        super(baseHeight, firstRandHeight, secondRandHeight);
        provided = true;
        providedRootHeight = rootHeightIn;
        providedRootSplay = rootSplayIn;
    }

    @Override
    protected TrunkPlacerType<?> func_230381_a_() {
        return BorealisTreePlacers.ROOTED_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.Foliage> func_230382_a_(IWorldGenerationReader reader, Random rand, int y, BlockPos pos, Set<BlockPos> posSet, MutableBoundingBox boundingBox, BaseTreeFeatureConfig config) {
        int treeBaseHeight = config.trunkPlacer.func_236917_a_(rand);

        int rootHeight = rand.nextInt(2) + 2;
        int rootSplay = rootHeight - 1;
        BlockPos pos1 = pos;

        if (provided) {
            rootHeight = providedRootHeight;
            rootSplay = providedRootSplay;
        }


        for (int i = 0; i < treeBaseHeight; i++) {
            if (i < rootHeight) {
                reader.setBlockState(pos1.west(rootSplay), config.trunkProvider.getBlockState(rand, pos1), 19);
                reader.setBlockState(pos1.north(rootSplay), config.trunkProvider.getBlockState(rand, pos1), 19);
                reader.setBlockState(pos1.east(rootSplay), config.trunkProvider.getBlockState(rand, pos1), 19);
                reader.setBlockState(pos1.south(rootSplay), config.trunkProvider.getBlockState(rand, pos1), 19);
            } else if (i >= rootHeight && rootSplay > 0) {
                rootSplay--;
                reader.setBlockState(pos1.west(rootSplay), config.trunkProvider.getBlockState(rand, pos1), 19);
                reader.setBlockState(pos1.north(rootSplay), config.trunkProvider.getBlockState(rand, pos1), 19);
                reader.setBlockState(pos1.east(rootSplay), config.trunkProvider.getBlockState(rand, pos1), 19);
                reader.setBlockState(pos1.south(rootSplay), config.trunkProvider.getBlockState(rand, pos1), 19);
            } else {
                reader.setBlockState(pos1, config.trunkProvider.getBlockState(rand, pos1), 19);
            }
            pos1 = pos1.up(1);
        }

        return ImmutableList.of(new FoliagePlacer.Foliage(pos.up(treeBaseHeight), 0, false));
    }
}
