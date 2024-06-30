package com.reetam.borealis.world.feature;

import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class HotSpringFeature extends Feature<NoneFeatureConfiguration> {

    public HotSpringFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();

        pos = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ()) - 4 + rand.nextInt(8) * rand.nextInt(1, 4), pos.getZ());

        if (!level.getBlockState(new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ()), pos.getZ())).isSolid()) return true;
        if (pos.getY() < 20) return true;

        // CREATE POND SHAPE
        boolean[] booleans = new boolean[2048];
        int n = rand.nextInt(4) + 4;

        for(int m = 0; m < n; ++m) { // make random amount of pond shapes
            // get random noise
            double d0 = rand.nextDouble() * 6.0D + 3.0D;
            double d1 = rand.nextDouble() * 4.0D + 2.0D;
            double d2 = rand.nextDouble() * 6.0D + 3.0D;
            double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
            double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
            double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

            for(int dx = 1; dx < 15; ++dx) {
                for(int dy = 1; dy < 15; ++dy) {
                    for(int dz = 1; dz < 7; ++dz) {
                        double d6 = ((double)dx - d3) / (d0 / 2.0D);
                        double d7 = ((double)dz - d4) / (d1 / 2.0D);
                        double d8 = ((double)dy - d5) / (d2 / 2.0D);
                        double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                        if (d9 < 1.0D) {
                            booleans[(dx * 16 + dy) * 8 + dz] = true; // populate boolean list to determine shape of pond
                        }
                    }
                }
            }

            for(int x1 = 0; x1 < 16; ++x1) {
                for(int z1 = 0; z1 < 16; ++z1) {
                    for(int y1 = 0; y1 < 8; ++y1) {
                        if (booleans[(x1 * 16 + z1) * 8 + y1]) {
                            if (y1 < 4) { // don't bother with creating air overhead since this doesn't generate in the ground
                                // place water according to shape dictated by booleans array
                                level.setBlock(pos.offset(x1, y1, z1), BorealisFluids.HOT_SPRING_WATER_BLOCK.get().defaultBlockState(), 2);
                                for (Direction direction : Direction.values()) {
                                    if (direction != Direction.UP) {
                                        if (level.getBlockState(pos.offset(x1, y1, z1).relative(direction, 1)) != BorealisFluids.HOT_SPRING_WATER_BLOCK.get().defaultBlockState()) {
                                            this.setBlock(level, pos.offset(x1, y1, z1).relative(direction, 1), BorealisBlocks.PUMICE.get().defaultBlockState());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
