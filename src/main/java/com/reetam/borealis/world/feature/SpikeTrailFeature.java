package com.reetam.borealis.world.feature;

import com.mojang.serialization.Codec;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SpikeTrailFeature extends Feature<NoneFeatureConfiguration> {
    public SpikeTrailFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();

        pos = pos.atY(level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ()));
        if (pos.getY() < BorealisMod.MIN_HEIGHT + 16) return false;

        final int radius = 1 + context.random().nextInt(3);
        final int height = 7 - radius + context.random().nextInt(2);

        BlockState placeState;

        for (int x = -radius * 2; x <= radius * 2; x++) {
            for (int z = -radius * 2; z <= radius * 2; z++) {
                placeState = BorealisBlocks.PUMICE.get().defaultBlockState();
                for (int y = -2; y < height; y++) {
                    if (y == height - 1) {
                        placeState = BorealisBlocks.PUMICE_GEYSER.get().defaultBlockState();
                    }

                    double threshold = (((double) -height / radius) * Math.sqrt(x * x + z * z) - y);

                    if (-height < threshold - 2 && radius > 1 && y > -2) {
                        level.setBlock(pos.offset(x, y, z), BorealisFluids.HOT_SPRING_WATER_BLOCK.get().defaultBlockState(), 3);
                    } else if (-height <= threshold) {
                        level.setBlock(pos.offset(x, y, z), placeState, 3);
                    }
                }
            }
        }

        return true;
    }
}