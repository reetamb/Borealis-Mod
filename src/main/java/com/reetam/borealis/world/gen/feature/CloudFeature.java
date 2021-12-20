package com.reetam.borealis.world.gen.feature;

import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class CloudFeature extends Feature<NoneFeatureConfiguration> {

    public CloudFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        Random rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        
        int size = rand.nextInt(6) + 2;
        BlockState state = BorealisBlocks.CLOUD.get().defaultBlockState();
        pos = pos.offset(rand.nextInt(16) - 8, 0, rand.nextInt(16) - 8);

        if (level.dimensionType().hasSkyLight()) {
            if (level.getBrightness(LightLayer.SKY, pos) != 15 || rand.nextInt(10) == 0) {
                for (int i = 0; i < rand.nextInt(6) + 1; i++) {
                    makeDisk(level, state, rand, pos.below(), size - 1);
                    makeDisk(level, state, rand, pos, size);
                    makeDisk(level, state, rand, pos.above(), size);
                    makeDisk(level, state, rand, pos.above(2), size - 2);
                    if (size - 4 > 1) {
                        makeDisk(level, state, rand, pos.above(3), size - 4);
                    }

                    pos = pos.offset(rand.nextInt(8) - 4, rand.nextInt(4) - 2, rand.nextInt(8) - 4);
                    size -= rand.nextInt(2) + 2;
                }
            }
        }
        return true;
    }

    public void makeDisk(WorldGenLevel level, BlockState state, Random rand, BlockPos pos, int radius) {
        for (int i = -radius; i < radius; i++) {
            for (int j = -radius; j < radius; j++) {
                float line = Math.abs(i*i + j*j);
                if (line <= (radius + rand.nextFloat() - 0.5F)*(radius + rand.nextFloat() - 0.5F) + rand.nextFloat() - 0.5F) {
                    level.setBlock(pos.offset(i, 0, j), state, 19);
                }
            }
        }
    }
}
