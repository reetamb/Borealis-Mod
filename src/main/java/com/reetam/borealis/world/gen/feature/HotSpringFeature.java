package com.reetam.borealis.world.gen.feature;

import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class HotSpringFeature extends Feature<NoFeatureConfig> {
    public HotSpringFeature(Codec<NoFeatureConfig> p_i231962_1_) {
            super(p_i231962_1_);
        }

        public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
            pos = new BlockPos(pos.getX(), reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX(), pos.getZ()), pos.getZ()).below();

        BlockState state = BorealisBlocks.TRAVERTINE.get().defaultBlockState();

        int size = rand.nextInt(4) + 2;
        int yOffset = rand.nextInt(2);

        if (size >= 4) {
            pos = pos.offset(Math.round(rand.nextFloat()-0.5F * 8), 0, Math.round(rand.nextFloat()-0.5F * 8));
            BlockPos pos1 = new BlockPos(pos.getX(), reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX(), pos.getZ()), pos.getZ()).below();
            diskAt(reader, pos1.below(), state, size-2);
            diskAt(reader, pos1, state, size-1);
            diskAt(reader, pos1.above(), state, size-2);

            diskAt(reader, pos1.above(), BorealisBlocks.HOT_SPRING_WATER.get().defaultBlockState(), size-3);
            diskAt(reader, pos1, BorealisBlocks.HOT_SPRING_WATER.get().defaultBlockState(), size-2);
        } else {
            diskAt(reader, pos.below().above(yOffset), state, size);
            diskAt(reader, pos.above(yOffset), state, size);
            diskAt(reader, pos.above(yOffset), BorealisBlocks.HOT_SPRING_WATER.get().defaultBlockState(), size-1);
        }

        return true;
    }

    public void diskAt(ISeedReader reader, BlockPos pos, BlockState state, int radius) {
        for(int x = pos.getX() - radius; x <= pos.getX() + radius; ++x) {
            for(int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z) {
                int x1 = x - pos.getX();
                int z1 = z - pos.getZ();
                if (x1 * x1 + z1 * z1 <= (radius * (radius-1))) {
                    BlockPos blockpos = new BlockPos(x, pos.getY(), z);
                    reader.setBlock(blockpos, state, 2);
                }
            }
        }
    }
}
