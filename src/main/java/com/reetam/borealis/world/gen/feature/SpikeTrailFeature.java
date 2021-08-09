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

public class SpikeTrailFeature extends Feature<NoFeatureConfig> {
    public SpikeTrailFeature(Codec<NoFeatureConfig> p_i231962_1_) {
        super(p_i231962_1_);
    }

    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int xdir = rand.nextInt(3) - 1;
        int zdir = rand.nextInt(3) - 1;
        for (int i = 0; i < rand.nextInt(5)+5; i++) {
            pos = toGround(reader, pos);
            if (!reader.getBlockState(pos).useShapeForLightOcclusion()) {
                spikeAt(reader, pos);
            }
            pos = pos.west((rand.nextInt(3)+1)*xdir+1);
            pos = pos.north((rand.nextInt(3)+1)*zdir+1);
        }
        return true;
    }

    private void spikeAt(ISeedReader reader, BlockPos pos) {
        reader.setBlock(pos, BorealisBlocks.PUMICE.get().defaultBlockState(), 19);

        reader.setBlock(toGround(reader, pos.west()), BorealisBlocks.PUMICE.get().defaultBlockState(), 19);
        reader.setBlock(toGround(reader, pos.north()), BorealisBlocks.PUMICE.get().defaultBlockState(), 19);
        reader.setBlock(toGround(reader, pos.east()), BorealisBlocks.PUMICE.get().defaultBlockState(), 19);
        reader.setBlock(toGround(reader, pos.south()), BorealisBlocks.PUMICE.get().defaultBlockState(), 19);

        reader.setBlock(pos.above(), BorealisBlocks.PUMICE.get().defaultBlockState(), 19);
        reader.setBlock(pos.above(2), BorealisBlocks.PUMICE_GEYSER.get().defaultBlockState(), 19);
    }

    private BlockPos toGround(ISeedReader reader, BlockPos pos) {
        return new BlockPos(pos.getX(), reader.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX(), pos.getZ()), pos.getZ());
    }
}