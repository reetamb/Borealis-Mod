package com.reetam.borealis.world.gen.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ClawFeature extends CoralShapeFeature {
    public ClawFeature(Codec<BlockStateFeatureConfig> config) {
        super(config);
    }

    protected boolean func_204623_a(IWorld world, Random rand, BlockPos pos, BlockState state) {
        if (!this.func_204624_b(world, rand, pos, state) || world.getBlockState(pos.down()).getBlock().isTransparent(world.getBlockState(pos.down()))) {
            return false;
        } else {
            Direction direction = Direction.Plane.HORIZONTAL.random(rand);
            int i = rand.nextInt(2) + 2;
            List<Direction> list = Lists.newArrayList(direction, direction.rotateY(), direction.rotateYCCW());
            Collections.shuffle(list, rand);

            for(Direction direction1 : list.subList(0, i)) {
                BlockPos.Mutable blockpos$mutable = pos.toMutable();
                int j = rand.nextInt(2) + 1;
                blockpos$mutable.move(direction1);
                int k;
                Direction direction2;
                if (direction1 == direction) {
                    direction2 = direction;
                    k = rand.nextInt(3) + 2;
                } else {
                    blockpos$mutable.move(Direction.UP);
                    Direction[] adirection = new Direction[]{direction1, Direction.UP};
                    direction2 = Util.getRandomObject(adirection, rand);
                    k = rand.nextInt(3) + 3;
                }

                for(int l = 0; l < j && this.func_204624_b(world, rand, blockpos$mutable, state); ++l) {
                    blockpos$mutable.move(direction2);
                }

                blockpos$mutable.move(direction2.getOpposite());
                blockpos$mutable.move(Direction.UP);

                for(int i1 = 0; i1 < k; ++i1) {
                    blockpos$mutable.move(direction);
                    if (!this.func_204624_b(world, rand, blockpos$mutable, state)) {
                        break;
                    }

                    if (rand.nextFloat() < 0.25F) {
                        blockpos$mutable.move(Direction.UP);
                    }
                }
            }

            return true;
        }
    }
}
