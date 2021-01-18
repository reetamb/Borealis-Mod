package com.reetam.borealis.world.gen.feature;

import com.mojang.serialization.Codec;
import java.util.Random;

import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.Tags;

public class HotSpringFeature extends Feature<NoFeatureConfig> {
    public HotSpringFeature(Codec<NoFeatureConfig> p_i231962_1_) {
        super(p_i231962_1_);
    }

    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        pos = new BlockPos(pos.getX(), reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX(), pos.getZ()), pos.getZ());
        BlockPos pos1;
        int width = rand.nextInt(2)+1;
        if (reader.getBlockState(pos.down()).isTransparent()) {
            return false;
        } else {
//            for (int x = pos.getX()-width; x < pos.getX()+width; x++) {
//                for (int z = pos.getZ()-width; z < pos.getZ()+width; z++) {
//                    for (int y = pos.getY(); y > pos.getY()-width; y--) {
//                        pos1 = new BlockPos(x, y, z);
//                        if (reader.getBlockState(pos1.down()).isIn(Tags.Blocks.DIRT) || reader.getBlockState(pos1.down()).getBlock() == Blocks.SNOW) {
//                            reader.setBlockState(pos1.down(), BorealisBlocks.hot_spring_water.get().getDefaultState(), 19);
//                            if (reader.getBlockState(pos1).getBlock() != BorealisBlocks.hot_spring_water.get()) {
//                                reader.setBlockState(pos1, Blocks.AIR.getDefaultState(), 19);
//                            }
//                        } else if (reader.getBlockState(pos1.down()).isIn(Tags.Blocks.COBBLESTONE)) {
//                            reader.setBlockState(pos1.down(), BorealisBlocks.porcelain.get().getDefaultState(), 19);
//                        }
//                    }
//                }
//            }
            for (int x = -width; x < width; x++) {
                for (int z = -width; z < width; z++) {
                    for (int y = -width; y < width; y++) {
                        pos1 = new BlockPos(pos.getX()+x, pos.down(2).getY()+y, pos.getZ()+z);
                        float formula = (float) (Math.pow(x, 2) + +Math.pow(y, 2) + Math.pow(z, 2));

                        if (formula <= Math.pow(width, 2)) {
                            if (reader.getBlockState(pos1).isIn(Tags.Blocks.DIRT) || reader.getBlockState(pos1).getBlock() == BorealisBlocks.porcelain.get()) {
                                reader.setBlockState(pos1, BorealisBlocks.hot_spring_water.get().getDefaultState(), 1);
                            } else if (reader.getBlockState(pos1).isIn(Tags.Blocks.COBBLESTONE)) {
                                reader.setBlockState(pos1.down(), BorealisBlocks.porcelain.get().getDefaultState(), 19);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
