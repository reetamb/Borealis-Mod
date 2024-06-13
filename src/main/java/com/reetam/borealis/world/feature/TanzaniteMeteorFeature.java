package com.reetam.borealis.world.feature;

import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class TanzaniteMeteorFeature extends Feature<NoneFeatureConfiguration> {
    public TanzaniteMeteorFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();

        BlockPos meteorCenter;
        BlockPos meteorPos;
        BlockPos craterPos;

        int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, origin.getX(), origin.getZ());
        if (y < 64) return false;

        meteorCenter = origin.atY(y);
        for (int i = -10; i < 10; i++) {
            for (int j = -10; j < 10; j++) {
                craterPos = meteorCenter.offset(i, 0, j);
                craterPos = craterPos.atY(level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, craterPos.getX(), craterPos.getZ()) - 1);
                double sqrt = Math.sqrt(i * i + j * j);
                if (sqrt*sqrt + random.nextInt(10) - 5 < 100 && random.nextFloat() > sqrt / 30) {
                    if (random.nextFloat() > sqrt / 15) {
                        this.setBlock(level, craterPos, BorealisBlocks.STARRY_SLATE.get().defaultBlockState());
                    } else {
                        this.setBlock(level, craterPos, BorealisBlocks.SLATE.get().defaultBlockState());
                    }
                }

                if (i >= -3 && i < 3 && j >= -3 && j < 3) {
                    for (int k = -3; k < 3; k++) {
                        meteorPos = meteorCenter.offset(i, k, j);
                        if (i*i+j*j+k*k + random.nextInt(2) < 9) {
                            this.setBlock(level, meteorPos, BorealisBlocks.TANZANITE_ORE.get().defaultBlockState());
                        }
                    }
                }
            }
        }
        return true;

    }
}
