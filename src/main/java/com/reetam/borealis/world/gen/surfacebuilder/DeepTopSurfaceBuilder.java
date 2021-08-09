package com.reetam.borealis.world.gen.surfacebuilder;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.reetam.borealis.block.PermafrostBlock;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class DeepTopSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    private static final BlockState AIR = Blocks.CAVE_AIR.defaultBlockState();
    protected long seed;
    private OctavesNoiseGenerator decorationNoise;

    public DeepTopSurfaceBuilder(Codec<SurfaceBuilderConfig> p_i232131_1_) {
        super(p_i232131_1_);
    }

    public void apply(Random rand, IChunk chunk, Biome biome, int x, int z, int terrainHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        int x1 = x & 15;
        int z1 = z & 15;
        int depth = -1;

        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        int middleBlockExtraDepth = (int)(noise / 3.0D + 3.0D + rand.nextDouble() * 0.25D); // this is random
        BlockState activeBlock = config.getTopMaterial();
        BlockState previousBlock = activeBlock;

        boolean placeBlock;

        for(int y = 127; y >= 0; --y) {
            blockpos$mutable.set(x1, y, z1); // move block down
            BlockState detectedBlock = chunk.getBlockState(blockpos$mutable);

            if (detectedBlock.isAir()) {
                depth = -1;
            } else if (detectedBlock == Blocks.WATER.defaultBlockState()) {
                depth = -5;
            } else {
                depth++;
            }

            placeBlock = true;
            if (depth == 0 || depth == 1) {
                previousBlock = config.getTopMaterial();
                activeBlock = config.getTopMaterial();
            } else if (depth >= 2 && depth <= middleBlockExtraDepth + 1) {

                if (config.getUnderMaterial().is(BorealisBlocks.PERMAFROST.get()) && depth == 2) {
                    if (previousBlock.is(BorealisTags.Blocks.SNOWY_BLOCKS)) {
                        activeBlock = config.getUnderMaterial().setValue(PermafrostBlock.SNOWY, true);
                    } else if (previousBlock.is(BorealisTags.Blocks.SUGARY_BLOCKS)) {
                        activeBlock = config.getUnderMaterial().setValue(PermafrostBlock.SUGARY, true);
                    }
                } else {
                    activeBlock = config.getUnderMaterial();
                }
                previousBlock = Blocks.AIR.defaultBlockState();
            } else if (depth == -4) {
                activeBlock = config.getUnderwaterMaterial();
                depth = middleBlockExtraDepth + 2;
                previousBlock = Blocks.AIR.defaultBlockState();
            } else {
                placeBlock = false;
            }

            if (placeBlock) {
                chunk.setBlockState(blockpos$mutable, activeBlock, false);
            }
        }

    }

    public void initNoise(long seed) {
        if (this.seed != seed || this.decorationNoise == null) {
            this.decorationNoise = new OctavesNoiseGenerator(new SharedSeedRandom(seed), ImmutableList.of(0));
        }

        this.seed = seed;
    }
}
