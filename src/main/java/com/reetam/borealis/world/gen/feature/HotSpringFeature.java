package com.reetam.borealis.world.gen.feature;

import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class HotSpringFeature extends Feature<NoFeatureConfig> {

    public HotSpringFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
        pos = new BlockPos(pos.getX(), reader.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ()) - 4 + random.nextInt(8), pos.getZ());

        // CREATE POND SHAPE
        boolean[] booleans = new boolean[2048];
        int n = random.nextInt(4) + 4;

        for(int m = 0; m < n; ++m) {
            // get random noise
            double d0 = random.nextDouble() * 6.0D + 3.0D;
            double d1 = random.nextDouble() * 4.0D + 2.0D;
            double d2 = random.nextDouble() * 6.0D + 3.0D;
            double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
            double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
            double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

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
                                // place water according to shape dictate by booleans array
                                reader.setBlock(pos.offset(x1, y1, z1), BorealisBlocks.HOT_SPRING_WATER.get().defaultBlockState(), 2);
                                for (Direction direction : Direction.values()) {
                                    if (direction != Direction.UP) {
                                        if (reader.getBlockState(pos.offset(x1, y1, z1).relative(direction, 1)) != BorealisBlocks.HOT_SPRING_WATER.get().defaultBlockState()) {
                                            reader.setBlock(pos.offset(x1, y1, z1).relative(direction, 1), BorealisBlocks.PUMICE.get().defaultBlockState(), 19);
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
