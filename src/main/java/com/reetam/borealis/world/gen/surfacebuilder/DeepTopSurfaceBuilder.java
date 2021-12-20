package com.reetam.borealis.world.gen.surfacebuilder;

import com.mojang.serialization.Codec;
import com.reetam.borealis.block.PermafrostBlock;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

import java.util.Random;

public class DeepTopSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
    protected long seed;

    public DeepTopSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> codec) {
        super(codec);
    }

    public void apply(Random rand, ChunkAccess chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, int surfaceLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        int x1 = x & 15;
        int z1 = z & 15;
        int depth = -1;

        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
        int middleBlockExtraDepth = (int)(noise / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
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
                    } else if (previousBlock.is(BorealisTags.Blocks.ICY_BLOCKS)) {
                        activeBlock = config.getUnderMaterial().setValue(PermafrostBlock.ICY, true);
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
}
