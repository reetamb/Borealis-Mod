package com.reetam.borealis.world.gen.feature;

import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class SpikeTrailFeature extends Feature<NoneFeatureConfiguration> {
    public SpikeTrailFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        Random rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        
        int xdir = rand.nextInt(3) - 1;
        int zdir = rand.nextInt(3) - 1;
        for (int i = 0; i < rand.nextInt(5)+5; i++) {
            pos = toGround(level, pos);
            if (!level.getBlockState(pos).useShapeForLightOcclusion()) {
                spikeAt(level, pos);
            }
            pos = pos.west((rand.nextInt(3)+1)*xdir+1);
            pos = pos.north((rand.nextInt(3)+1)*zdir+1);
        }
        return true;
    }

    private void spikeAt(WorldGenLevel level, BlockPos pos) {
        level.setBlock(pos, BorealisBlocks.PUMICE.get().defaultBlockState(), 19);

        level.setBlock(toGround(level, pos.west()), BorealisBlocks.PUMICE.get().defaultBlockState(), 19);
        level.setBlock(toGround(level, pos.north()), BorealisBlocks.PUMICE.get().defaultBlockState(), 19);
        level.setBlock(toGround(level, pos.east()), BorealisBlocks.PUMICE.get().defaultBlockState(), 19);
        level.setBlock(toGround(level, pos.south()), BorealisBlocks.PUMICE.get().defaultBlockState(), 19);

        level.setBlock(pos.above(), BorealisBlocks.PUMICE.get().defaultBlockState(), 19);
        level.setBlock(pos.above(2), BorealisBlocks.PUMICE_GEYSER.get().defaultBlockState(), 19);
    }

    private BlockPos toGround(WorldGenLevel level, BlockPos pos) {
        return new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ()), pos.getZ());
    }
}