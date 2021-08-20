package com.reetam.borealis.world.gen.feature;

import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.LightType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class CloudFeature extends Feature<NoFeatureConfig> {

    public CloudFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int size = rand.nextInt(6) + 2;
        BlockState state = BorealisBlocks.CLOUD.get().defaultBlockState();
        pos = pos.offset(rand.nextInt(16) - 8, 0, rand.nextInt(16) - 8);

        if (reader.dimensionType().hasSkyLight()) {
            if (reader.getBrightness(LightType.SKY, pos) != 15 || rand.nextInt(10) == 0) {
                for (int i = 0; i < rand.nextInt(6) + 1; i++) {
                    makeDisk(reader, state, rand, pos.below(), size - 1);
                    makeDisk(reader, state, rand, pos, size);
                    makeDisk(reader, state, rand, pos.above(), size);
                    makeDisk(reader, state, rand, pos.above(2), size - 2);
                    if (size - 4 > 1) {
                        makeDisk(reader, state, rand, pos.above(3), size - 4);
                    }

                    pos = pos.offset(rand.nextInt(8) - 4, rand.nextInt(4) - 2, rand.nextInt(8) - 4);
                    size -= rand.nextInt(2) + 2;
                }
            }
        }
        return true;
    }

    public void makeDisk(ISeedReader reader, BlockState state, Random rand, BlockPos pos, int radius) {
        for (int i = -radius; i < radius; i++) {
            for (int j = -radius; j < radius; j++) {
                float line = Math.abs(i*i + j*j);
                if (line <= (radius + rand.nextFloat() - 0.5F)*(radius + rand.nextFloat() - 0.5F) + rand.nextFloat() - 0.5F) {
                    reader.setBlock(pos.offset(i, 0, j), state, 19);
                }
            }
        }
    }
}
