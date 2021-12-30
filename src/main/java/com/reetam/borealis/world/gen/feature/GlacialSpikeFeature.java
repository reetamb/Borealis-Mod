package com.reetam.borealis.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class GlacialSpikeFeature extends Feature<NoneFeatureConfiguration> {
    public GlacialSpikeFeature(Codec<NoneFeatureConfiguration> p_i231962_1_) {
        super(p_i231962_1_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        Random rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        
        pos = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, pos.getX(), pos.getZ()), pos.getZ());

        pos = pos.above(rand.nextInt(4));
        int i = rand.nextInt(4) + 7;
        int j = i / 4 + rand.nextInt(2);
        if (j > 1 && rand.nextInt(60) == 0) {
            pos = pos.above(10 + rand.nextInt(30));
        }

        if (level.getBlockState(pos.below().below()).getBlock() != Blocks.PACKED_ICE) {
            return false;
        } else {
            for (int k = 0; k < i; ++k) {
                float f = (1.0F - (float) k / (float) i) * (float) j;
                int l = (int) Math.ceil(f);

                for (int i1 = -l; i1 <= l; ++i1) {
                    float f1 = (float) Math.abs(i1) - 0.25F;

                    for (int j1 = -l; j1 <= l; ++j1) {
                        float f2 = (float) Math.abs(j1) - 0.25F;
                        if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(rand.nextFloat() > 0.75F))) {
                            BlockState blockstate = level.getBlockState(pos.offset(i1, k, j1));
                            Block block = blockstate.getBlock();
                            if (blockstate.isAir() || isDirt(blockstate) || block == Blocks.SNOW_BLOCK || block == Blocks.ICE) {
                                this.setBlock(level, pos.offset(i1, k, j1), Blocks.PACKED_ICE.defaultBlockState());
                            }

                            if (k != 0 && l > 1) {
                                blockstate = level.getBlockState(pos.offset(i1, -k, j1));
                                block = blockstate.getBlock();
                                if (blockstate.isAir() || isDirt(blockstate) || block == Blocks.SNOW_BLOCK || block == Blocks.ICE) {
                                    this.setBlock(level, pos.offset(i1, -k, j1), Blocks.PACKED_ICE.defaultBlockState());
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
