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
        int iters = 0;

        float rad = 1.0F;
        for (int y = 0; y < treeBaseHeight; ++y) {
            int h = MathHelper.ceil(treeBaseHeight);
            iters++;

            for (int x = -h; x <= h; ++x) {
                float absx = (float) MathHelper.abs(x) - 0.25F;

                for (int z = -h; z <= h; ++z) {
                    float absz = (float) MathHelper.abs(z) - 0.25F;
                    if ((x == 0 && z == 0 || !(absx * absx + absz * absz > rad * rad)) && (x != -h && x != h && z != -h && z != h || !(rand.nextFloat() > 0.75F))) {
                        BlockPos placepos = offset(pos.add(x, y, z), iters, MathHelper.ceil(rad/2));
                        BlockPos negplacepos = offset(pos.add(x, -y, z), iters, MathHelper.ceil(rad/2));
                        BlockState blockstate = reader.getBlockState(placepos);
                        if (blockstate.isAir((IBlockReader) world, pos)) {
                            func_236911_a_(world, rand, placepos, posSet, boundingBox, config);
                        }

                        if (y != 0 && h > 1) {
                            blockstate = reader.getBlockState(negplacepos);
                            if (blockstate.isAir((IBlockReader) world, pos)) {
                                func_236911_a_(world, rand, placepos, posSet, boundingBox, config);
                            }
                        }
                    }
                }
            }
        }

        return ImmutableList.of(new FoliagePlacer.Foliage(pos.up(treeBaseHeight), 0, false));
    }

    private BlockPos offset(BlockPos pos, int i, int amt) {
        if (i % 4 == 0) {
            return pos.west(amt);
        } else if (i % 4 == 1) {
            return pos.south(amt);
        } else if (i % 4 == 2) {
            return pos.east(amt);
        } else {
            return pos.north(amt);
        }
    }
}
