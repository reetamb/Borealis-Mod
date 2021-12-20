package com.reetam.borealis.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class GlacialRidgeFeature extends Feature<ColumnFeatureConfiguration> {
    private static final ImmutableList<Block> CANNOT_PLACE_ON = ImmutableList.of(Blocks.WATER, BorealisBlocks.CLOUD.get());

    public GlacialRidgeFeature(Codec<ColumnFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<ColumnFeatureConfiguration> context) {
        Random rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        ColumnFeatureConfiguration config = context.config();
        ChunkGenerator generator = context.chunkGenerator();
        
        int i = generator.getSeaLevel();
        if (!canPlaceAt(level, i, pos.mutable())) {
            return false;
        } else {
            int j = config.height().sample(rand);
            boolean flag = rand.nextFloat() < 0.9F;
            int k = Math.min(j, flag ? 5 : 8);
            int l = flag ? 50 : 15;
            boolean flag1 = false;

            for(BlockPos blockpos : BlockPos.randomBetweenClosed(rand, l, pos.getX() - k, pos.getY(), pos.getZ() - k, pos.getX() + k, pos.getY(), pos.getZ() + k)) {
                int i1 = j - blockpos.distManhattan(pos);
                if (i1 >= 0) {
                    flag1 |= this.placeColumn(level, i, blockpos, i1, config.reach().sample(rand));
                }
            }

            return flag1;
        }
    }

    private boolean placeColumn(LevelAccessor level, int seaLevel, BlockPos pos, int distance, int reach) {
        boolean flag = false;

        for(BlockPos blockpos : BlockPos.betweenClosed(pos.getX() - reach, pos.getY(), pos.getZ() - reach, pos.getX() + reach, pos.getY(), pos.getZ() + reach)) {
            int i = blockpos.distManhattan(pos);
            BlockPos blockpos1 = isAirOrLavaOcean(level, seaLevel, blockpos) ? findSurface(level, seaLevel, blockpos.mutable(), i) : findAir(level, blockpos.mutable(), i);
            if (blockpos1 != null) {
                int j = distance - i / 2;

                for(BlockPos.MutableBlockPos blockpos$mutable = blockpos1.mutable(); j >= 0; --j) {
                    if (isAirOrLavaOcean(level, seaLevel, blockpos$mutable)) {
                        this.setBlock(level, blockpos$mutable, Blocks.PACKED_ICE.defaultBlockState());
                        blockpos$mutable.move(Direction.UP);
                        flag = true;
                    } else {
                        if (!level.getBlockState(blockpos$mutable).is(Blocks.PACKED_ICE)) {
                            break;
                        }

                        blockpos$mutable.move(Direction.UP);
                    }
                }
            }
        }

        return flag;
    }

    @Nullable
    private static BlockPos findSurface(LevelAccessor level, int seaLevel, BlockPos.MutableBlockPos pos$mutable, int distance) {
        while(pos$mutable.getY() > 1 && distance > 0) {
            --distance;
            if (canPlaceAt(level, seaLevel, pos$mutable)) {
                return pos$mutable;
            }

            pos$mutable.move(Direction.DOWN);
        }

        return null;
    }

    private static boolean canPlaceAt(LevelAccessor level, int seaLevel, BlockPos.MutableBlockPos pos$mutable) {
        if (!isAirOrLavaOcean(level, seaLevel, pos$mutable)) {
            return false;
        } else {
            BlockState blockstate = level.getBlockState(pos$mutable.move(Direction.DOWN));
            pos$mutable.move(Direction.UP);
            return !blockstate.isAir() && !CANNOT_PLACE_ON.contains(blockstate.getBlock());
        }
    }

    @Nullable
    private static BlockPos findAir(LevelAccessor level, BlockPos.MutableBlockPos pos$mutable, int distance) {
        while(pos$mutable.getY() < level.getMaxBuildHeight() && distance > 0) {
            --distance;
            BlockState blockstate = level.getBlockState(pos$mutable);
            if (CANNOT_PLACE_ON.contains(blockstate.getBlock())) {
                return null;
            }

            if (blockstate.isAir()) {
                return pos$mutable;
            }

            pos$mutable.move(Direction.UP);
        }

        return null;
    }

    private static boolean isAirOrLavaOcean(LevelAccessor level, int seaLevel, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        return blockstate.isAir() || blockstate.is(Blocks.WATER) && pos.getY() <= seaLevel;
    }
}
