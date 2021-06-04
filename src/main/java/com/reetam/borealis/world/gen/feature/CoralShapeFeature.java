package com.reetam.borealis.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public abstract class CoralShapeFeature extends Feature<BlockStateFeatureConfig> {
    public CoralShapeFeature(Codec<BlockStateFeatureConfig> p_i231940_1_) {
        super(p_i231940_1_);
    }

    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
        BlockState blockstate = config.state;
        return this.placeFeature(reader, rand, pos, blockstate);
    }

    protected abstract boolean placeFeature(IWorld world, Random rand, BlockPos pos, BlockState state);

    protected boolean placeCoralBlock(IWorld world, Random rand, BlockPos pos, BlockState state) {
        world.setBlock(pos, state, 3);

//        for(Direction direction : Direction.Plane.HORIZONTAL) {
//            if (rand.nextFloat() < 0.2F) {
//                BlockPos blockpos1 = pos.offset(direction);
//                if (world.getBlockState(blockpos1).isIn(Blocks.WATER)) {
//                    BlockState blockstate1 = BlockTags.WALL_CORALS.getRandomElement(rand).getDefaultState().with(DeadCoralWallFanBlock.FACING, direction);
//                    world.setBlockState(blockpos1, blockstate1, 2);
//                }
//            }
//        }

        return true;
    }
}
