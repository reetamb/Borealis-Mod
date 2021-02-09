package com.reetam.borealis.world.gen.trunkplacer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.BorealisTreePlacers;
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

public class HelixTrunkPlacer extends AbstractTrunkPlacer {

    public static final Codec<HelixTrunkPlacer> CODEC = RecordCodecBuilder.create((me) ->
            func_236915_a_(me).apply(me, HelixTrunkPlacer::new));

    public HelixTrunkPlacer(int baseHeight, int firstRandHeight, int secondRandHeight) {
        super(baseHeight, firstRandHeight, secondRandHeight);
    }

    @Override
    protected TrunkPlacerType<?> func_230381_a_() {
        return BorealisTreePlacers.HELIX_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.Foliage> func_230382_a_(IWorldGenerationReader world, Random rand, int height, BlockPos pos, Set<BlockPos> posSet, MutableBoundingBox boundingBox, BaseTreeFeatureConfig config) {
        IBlockReader reader = (IBlockReader) world;
        int treeBaseHeight = config.trunkPlacer.func_236917_a_(rand);
        int width = treeBaseHeight / 2;

        for(int y = 0; y < treeBaseHeight; y++) {

            func_236911_a_(world, rand, pos.up(y), posSet, boundingBox, config);
            int l = MathHelper.ceil(treeBaseHeight);


            for (int x = -l; x < l; x++) {
                float absx = Math.abs(x) - 0.25F;
                if ((x == 0 || !(absx * absx * 2 > width * width)) && (x != -l && x != l || !(rand.nextFloat() > 0.75F))) {
                    if (reader.getBlockState(pos.add(x, y, x)).isAir()) {
                        func_236911_a_(world, rand, pos.add(x, y, x), posSet, boundingBox, config);
                    }
                }
            }
        }
        return ImmutableList.of(new FoliagePlacer.Foliage(pos.up(treeBaseHeight), 0, false));
    }
}
