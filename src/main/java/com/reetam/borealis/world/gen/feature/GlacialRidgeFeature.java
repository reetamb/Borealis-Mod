package com.reetam.borealis.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.Random;
import javax.annotation.Nullable;

import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ColumnConfig;
import net.minecraft.world.gen.feature.Feature;

public class GlacialRidgeFeature extends Feature<ColumnConfig> {
    private static final ImmutableList<Block> CANNOT_PLACE_ON = ImmutableList.of(Blocks.WATER, BorealisBlocks.CLOUD.get());

    public GlacialRidgeFeature(Codec<ColumnConfig> codec) {
        super(codec);
    }

    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, ColumnConfig config) {
        int i = generator.getSeaLevel();
        if (!canPlaceAt(reader, i, pos.mutable())) {
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
                    flag1 |= this.placeColumn(reader, i, blockpos, i1, config.reach().sample(rand));
                }
            }

            return flag1;
        }
    }

    private boolean placeColumn(IWorld p_236248_1_, int p_236248_2_, BlockPos p_236248_3_, int p_236248_4_, int p_236248_5_) {
        boolean flag = false;

        for(BlockPos blockpos : BlockPos.betweenClosed(p_236248_3_.getX() - p_236248_5_, p_236248_3_.getY(), p_236248_3_.getZ() - p_236248_5_, p_236248_3_.getX() + p_236248_5_, p_236248_3_.getY(), p_236248_3_.getZ() + p_236248_5_)) {
            int i = blockpos.distManhattan(p_236248_3_);
            BlockPos blockpos1 = isAirOrLavaOcean(p_236248_1_, p_236248_2_, blockpos) ? findSurface(p_236248_1_, p_236248_2_, blockpos.mutable(), i) : findAir(p_236248_1_, blockpos.mutable(), i);
            if (blockpos1 != null) {
                int j = p_236248_4_ - i / 2;

                for(BlockPos.Mutable blockpos$mutable = blockpos1.mutable(); j >= 0; --j) {
                    if (isAirOrLavaOcean(p_236248_1_, p_236248_2_, blockpos$mutable)) {
                        this.setBlock(p_236248_1_, blockpos$mutable, Blocks.PACKED_ICE.defaultBlockState());
                        blockpos$mutable.move(Direction.UP);
                        flag = true;
                    } else {
                        if (!p_236248_1_.getBlockState(blockpos$mutable).is(Blocks.PACKED_ICE)) {
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
    private static BlockPos findSurface(IWorld p_236246_0_, int p_236246_1_, BlockPos.Mutable p_236246_2_, int p_236246_3_) {
        while(p_236246_2_.getY() > 1 && p_236246_3_ > 0) {
            --p_236246_3_;
            if (canPlaceAt(p_236246_0_, p_236246_1_, p_236246_2_)) {
                return p_236246_2_;
            }

            p_236246_2_.move(Direction.DOWN);
        }

        return null;
    }

    private static boolean canPlaceAt(IWorld p_242762_0_, int p_242762_1_, BlockPos.Mutable p_242762_2_) {
        if (!isAirOrLavaOcean(p_242762_0_, p_242762_1_, p_242762_2_)) {
            return false;
        } else {
            BlockState blockstate = p_242762_0_.getBlockState(p_242762_2_.move(Direction.DOWN));
            p_242762_2_.move(Direction.UP);
            return !blockstate.isAir() && !CANNOT_PLACE_ON.contains(blockstate.getBlock());
        }
    }

    @Nullable
    private static BlockPos findAir(IWorld p_236249_0_, BlockPos.Mutable p_236249_1_, int p_236249_2_) {
        while(p_236249_1_.getY() < p_236249_0_.getMaxBuildHeight() && p_236249_2_ > 0) {
            --p_236249_2_;
            BlockState blockstate = p_236249_0_.getBlockState(p_236249_1_);
            if (CANNOT_PLACE_ON.contains(blockstate.getBlock())) {
                return null;
            }

            if (blockstate.isAir()) {
                return p_236249_1_;
            }

            p_236249_1_.move(Direction.UP);
        }

        return null;
    }

    private static boolean isAirOrLavaOcean(IWorld p_236247_0_, int p_236247_1_, BlockPos p_236247_2_) {
        BlockState blockstate = p_236247_0_.getBlockState(p_236247_2_);
        return blockstate.isAir() || blockstate.is(Blocks.WATER) && p_236247_2_.getY() <= p_236247_1_;
    }
}
