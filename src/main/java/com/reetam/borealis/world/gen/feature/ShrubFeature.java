package com.reetam.borealis.world.gen.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.CoralFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShrubFeature extends CoralShapeFeature {
    public ShrubFeature(Codec<BlockStateFeatureConfig> p_i231942_1_) {
        super(p_i231942_1_);
    }

    protected boolean placeFeature(IWorld world, Random rand, BlockPos pos, BlockState state) {
        BlockPos.Mutable blockpos$mutable = pos.mutable();
        int i = rand.nextInt(3) + 1;

        for(int j = 0; j < i; ++j) {
            if (!this.placeCoralBlock(world, rand, blockpos$mutable, state) || world.getBlockState(pos.below()).getBlock().useShapeForLightOcclusion(world.getBlockState(pos.below()))) {
                return true;
            }

            blockpos$mutable.move(Direction.UP);
        }

        BlockPos blockpos = blockpos$mutable.immutable();
        int k = rand.nextInt(3) + 2;
        List<Direction> list = Lists.newArrayList(Direction.Plane.HORIZONTAL);
        Collections.shuffle(list, rand);

        for(Direction direction : list.subList(0, k)) {
            blockpos$mutable.set(blockpos);
            blockpos$mutable.move(direction);
            int l = rand.nextInt(5) + 2;
            int i1 = 0;

            for(int j1 = 0; j1 < l && this.placeCoralBlock(world, rand, blockpos$mutable, state); ++j1) {
                ++i1;
                blockpos$mutable.move(Direction.UP);
                if (j1 == 0 || i1 >= 2 && rand.nextFloat() < 0.25F) {
                    blockpos$mutable.move(direction);
                    i1 = 0;
                }
            }
        }

        return true;
    }
}
