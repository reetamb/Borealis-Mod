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

        public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
            pos = new BlockPos(pos.getX(), reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX(), pos.getZ()), pos.getZ()).down();

        BlockState state = BorealisBlocks.travertine.get().getDefaultState();

        int size = rand.nextInt(4) + 2;
        int yOffset = rand.nextInt(2);

        if (size >= 4) {
            pos = pos.add(Math.round(rand.nextFloat()-0.5F * 8), 0, Math.round(rand.nextFloat()-0.5F * 8));
            BlockPos pos1 = new BlockPos(pos.getX(), reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX(), pos.getZ()), pos.getZ()).down();
            diskAt(reader, pos1.down(), state, size-2);
            diskAt(reader, pos1, state, size-1);
            diskAt(reader, pos1.up(), state, size-2);

            diskAt(reader, pos1.up(), BorealisBlocks.hot_spring_water.get().getDefaultState(), size-3);
            diskAt(reader, pos1, BorealisBlocks.hot_spring_water.get().getDefaultState(), size-2);
        } else {
            diskAt(reader, pos.down().up(yOffset), state, size);
            diskAt(reader, pos.up(yOffset), state, size);
            diskAt(reader, pos.up(yOffset), BorealisBlocks.hot_spring_water.get().getDefaultState(), size-1);
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
                    reader.setBlockState(blockpos, state, 2);
                }
            }
        }
    }
}
