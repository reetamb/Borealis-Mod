package com.reetam.borealis.world.gen.feature;

import com.mojang.serialization.Codec;
import java.util.Random;

import com.reetam.borealis.block.BorealisGrassBlock;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowyDirtBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class SugarSnowFeature extends Feature<NoFeatureConfig> {
    public SugarSnowFeature(Codec<NoFeatureConfig> p_i231993_1_) {
        super(p_i231993_1_);
    }

    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        BlockPos.Mutable blockpos$mutable1 = new BlockPos.Mutable();

        for(int i = 0; i < 16; ++i) {
            for(int j = 0; j < 16; ++j) {
                int k = pos.getX() + i;
                int l = pos.getZ() + j;
                int i1 = reader.getHeight(Heightmap.Type.MOTION_BLOCKING, k, l);
                blockpos$mutable.setPos(k, i1, l);
                blockpos$mutable1.setPos(blockpos$mutable).move(Direction.DOWN, 1);
                Biome biome = reader.getBiome(blockpos$mutable);

                if (biome.doesSnowGenerate(reader, blockpos$mutable)) {
                    reader.setBlockState(blockpos$mutable, BorealisBlocks.sugar_snow.get().getDefaultState(), 2);
                    BlockState blockstate = reader.getBlockState(blockpos$mutable1);
                    if (blockstate.hasProperty(BorealisGrassBlock.SUGARY)) {
                        reader.setBlockState(blockpos$mutable1, blockstate.with(BorealisGrassBlock.SUGARY, Boolean.valueOf(true)), 2);
                    }
                }
            }
        }

        return true;
    }
}
