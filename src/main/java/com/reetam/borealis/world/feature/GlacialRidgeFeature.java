package com.reetam.borealis.world.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;

import javax.annotation.Nullable;

public class GlacialRidgeFeature extends Feature<ColumnFeatureConfiguration> {
    private static final ImmutableList<Block> CANNOT_PLACE_ON = ImmutableList.of(Blocks.WATER, BorealisBlocks.CLOUD.get());

    public GlacialRidgeFeature(Codec<ColumnFeatureConfiguration> codec) {
        super(codec);
    }

    /** Copy of Vanilla with block modification **/

    public boolean place(FeaturePlaceContext<ColumnFeatureConfiguration> context) {
        int i = context.chunkGenerator().getSeaLevel();
        BlockPos blockpos = context.origin();
        WorldGenLevel worldgenlevel = context.level();
        RandomSource random = context.random();
        ColumnFeatureConfiguration columnfeatureconfiguration = context.config();
        if (!canPlaceAt(worldgenlevel, i, blockpos.mutable())) {
            return false;
        } else {
            int j = columnfeatureconfiguration.height().sample(random);
            boolean flag = random.nextFloat() < 0.9F;
            int k = Math.min(j, flag ? 5 : 8);
            int l = flag ? 50 : 15;
            boolean flag1 = false;

            for(BlockPos blockpos1 : BlockPos.randomBetweenClosed(random, l, blockpos.getX() - k, blockpos.getY(), blockpos.getZ() - k, blockpos.getX() + k, blockpos.getY(), blockpos.getZ() + k)) {
                int i1 = j - blockpos1.distManhattan(blockpos);
                if (i1 >= 0) {
                    flag1 |= this.placeColumn(worldgenlevel, i, blockpos1, i1, columnfeatureconfiguration.reach().sample(random));
                }
            }

            return flag1;
        }
    }

    private boolean placeColumn(LevelAccessor level, int sealevel, BlockPos pos, int distance, int reach) {
        boolean flag = false;

        for(BlockPos blockpos : BlockPos.betweenClosed(pos.getX() - reach, pos.getY(), pos.getZ() - reach, pos.getX() + reach, pos.getY(), pos.getZ() + reach)) {
            int i = blockpos.distManhattan(pos);
            BlockPos blockpos1 = isAirOrLake(level, sealevel, blockpos) ? findSurface(level, sealevel, blockpos.mutable(), i) : findAir(level, blockpos.mutable(), i);
            if (blockpos1 != null) {
                int j = distance - i / 2;

                for(BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos1.mutable(); j >= 0; --j) {
                    if (isAirOrLake(level, sealevel, blockpos$mutableblockpos)) {
                        this.setBlock(level, blockpos$mutableblockpos, Blocks.PACKED_ICE.defaultBlockState());
                        blockpos$mutableblockpos.move(Direction.UP);
                        flag = true;
                    } else {
                        if (!level.getBlockState(blockpos$mutableblockpos).is(Blocks.PACKED_ICE)) {
                            break;
                        }

                        blockpos$mutableblockpos.move(Direction.UP);
                    }
                }
            }
        }

        return flag;
    }

    @Nullable
    private static BlockPos findSurface(LevelAccessor level, int seaLevel, BlockPos.MutableBlockPos pos, int distance) {
        while(pos.getY() > level.getMinBuildHeight() + 1 && distance > 0) {
            --distance;
            if (canPlaceAt(level, seaLevel, pos)) {
                return pos;
            }

            pos.move(Direction.DOWN);
        }

        return null;
    }

    private static boolean canPlaceAt(LevelAccessor level, int seaLevel, BlockPos.MutableBlockPos pos) {
        if (!isAirOrLake(level, seaLevel, pos)) {
            return false;
        } else {
            BlockState blockstate = level.getBlockState(pos.move(Direction.DOWN));
            pos.move(Direction.UP);
            return !blockstate.isAir() && !CANNOT_PLACE_ON.contains(blockstate.getBlock());
        }
    }

    @Nullable
    private static BlockPos findAir(LevelAccessor level, BlockPos.MutableBlockPos pos, int distance) {
        while(pos.getY() < level.getMaxBuildHeight() && distance > 0) {
            --distance;
            BlockState blockstate = level.getBlockState(pos);
            if (CANNOT_PLACE_ON.contains(blockstate.getBlock())) {
                return null;
            }

            if (blockstate.isAir()) {
                return pos;
            }

            pos.move(Direction.UP);
        }

        return null;
    }

    private static boolean isAirOrLake(LevelAccessor level, int seaLevel, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        return blockstate.isAir() || blockstate.is(Blocks.WATER) && pos.getY() <= seaLevel;
    }
}
