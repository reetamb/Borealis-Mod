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

public class SpikeTrailFeature extends Feature<NoneFeatureConfiguration> {
    public SpikeTrailFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        
//        int xdir = rand.nextInt(3) - 1;
//        int zdir = rand.nextInt(3) - 1;
//        int count = rand.nextInt(5) + 5;
//
//        for (int i = 0; i < count; i++) {
//            pos = toGround(level, pos);
//            if (!level.getBlockState(pos).useShapeForLightOcclusion()) {
//                spikeAt(level, pos);
//            }
//            pos = pos.west((rand.nextInt(3)+1)*xdir+1);
//            pos = pos.north((rand.nextInt(3)+1)*zdir+1);
//        }

        spikeAt(level, pos);

        return true;
    }

    private void spikeAt(WorldGenLevel level, BlockPos pos) {
        if (toGround(level, pos).getY() < 20) return;
        if (toGround(level, pos.west()).getY() < 20) return;
        if (toGround(level, pos.north()).getY() < 20) return;
        if (toGround(level, pos.east()).getY() < 20) return;
        if (toGround(level, pos.south()).getY() < 20) return;

        level.setBlock(pos, BorealisBlocks.PUMICE.get().defaultBlockState(), 19);

        this.setBlock(level, toGround(level, pos.west()), BorealisBlocks.PUMICE.get().defaultBlockState());
        this.setBlock(level, toGround(level, pos.north()), BorealisBlocks.PUMICE.get().defaultBlockState());
        this.setBlock(level, toGround(level, pos.east()), BorealisBlocks.PUMICE.get().defaultBlockState());
        this.setBlock(level, toGround(level, pos.south()), BorealisBlocks.PUMICE.get().defaultBlockState());

        this.setBlock(level, pos.above(), BorealisBlocks.PUMICE.get().defaultBlockState());
        this.setBlock(level, pos.above(2), BorealisBlocks.PUMICE_GEYSER.get().defaultBlockState());
    }

    private BlockPos toGround(WorldGenLevel level, BlockPos pos) {
        return new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ()), pos.getZ());
    }
}