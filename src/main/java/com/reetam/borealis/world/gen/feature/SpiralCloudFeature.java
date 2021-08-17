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

        int totalWidth = rand.nextInt(6) + 6;
        int startWidth = totalWidth - 5;

        int baseYOffset = 0;
        int yOffset;

        for (int i = 0; i < rand.nextInt(5) + 5; i++) {
            for (int j = -totalWidth; j < totalWidth; j++) {
                for (int k = -totalWidth; k < totalWidth; k++) {
                    int yOffsetStep = 0;
                    if (j > 0 && k > 0) {
                        yOffsetStep = 3;
                    }
                    if (j > 0 && k <= 0) {
                        yOffsetStep = 2;
                    }
                    if (j <= 0 && k <= 0) {
                        yOffsetStep = 1;
                    }
                    yOffset = baseYOffset + yOffsetStep;

                    int line = Math.abs(j*j + k*k);
                    if (line > (0.75*startWidth) + rand.nextFloat() + rand.nextInt(2) && line < (2.5*startWidth) + rand.nextFloat() + rand.nextInt(3) && rand.nextInt(10) != 0) {
                        reader.setBlock(pos.offset(j, yOffset, k), BorealisBlocks.CLOUD.get().defaultBlockState(), 19);
                    }
                    if (line > startWidth && line < (1.75*startWidth)) {
                        reader.setBlock(pos.offset(j, yOffset + 1, k), BorealisBlocks.CLOUD.get().defaultBlockState(), 19);
                        reader.setBlock(pos.offset(j, yOffset - 1, k), BorealisBlocks.CLOUD.get().defaultBlockState(), 19);
                    }
                }
            }
            baseYOffset += 4;
            startWidth += 3;
        }
        return true;
    }
}
