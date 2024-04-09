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

public class SpiralCloudFeature extends Feature<NoneFeatureConfiguration> {
    public SpiralCloudFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();

        pos = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ()) + rand.nextInt(20) + 10, pos.getZ());

        if (pos.getY() < 32 || rand.nextBoolean()) return true;

        int totalWidth = rand.nextInt(6) + 4;
        int startWidth = totalWidth - 3;

        int baseYOffset = 0;
        int yOffset;

        for (int i = 0; i < rand.nextInt(5) + 3; i++) {
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
                        this.setBlock(level, pos.offset(j, yOffset, k), BorealisBlocks.CLOUD.get().defaultBlockState());
                    }
                    if (line > startWidth && line < (1.75*startWidth)) {
                        this.setBlock(level, pos.offset(j, yOffset + 1, k), BorealisBlocks.CLOUD.get().defaultBlockState());
                        this.setBlock(level, pos.offset(j, yOffset - 1, k), BorealisBlocks.CLOUD.get().defaultBlockState());
                    }
                }
            }
            baseYOffset += 4;
            startWidth += 3;
        }
        return true;
    }
}
