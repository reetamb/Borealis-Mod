package com.reetam.borealis.world.gen.feature;

import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class SpiralCloudFeature extends Feature<NoFeatureConfig> {
    public SpiralCloudFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
        pos = new BlockPos(pos.getX(), reader.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ()) + rand.nextInt(20) + 10, pos.getZ());

        int r0 = rand.nextInt(6) + 6;
        int r1 = r0 - 3;

        int y0 = 0;
        int y1 = y0;
        for (int i = 0; i < rand.nextInt(5) + 5; i++) {
            for (int j = -r0; j < r0; j++) {
                for (int k = -r0; k < r0; k++) {
                    if (j > 0 && k > 0) {
                        y1 = y0 + 3;
                    } else if (j > 0) {
                        y1 = y0 + 2;
                    } else if (k > 0) {
                        y1 = y0;
                    }
                    if (j <= 0 && k <= 0) {
                        y1 = y0 + 1;
                    }
                    int line = Math.abs(j*(j) + k*(k));
                    if (line > (0.75*r1) + rand.nextFloat() + rand.nextInt(2) && line < (2.5*r1) + rand.nextFloat() + rand.nextInt(3)) {
                        reader.setBlock(pos.offset(j, y1, k), BorealisBlocks.CLOUD.get().defaultBlockState(), 19);
                    }
                }
            }
            y0 += 2;
            r1 += 2;
        }
        return true;
    }
}
