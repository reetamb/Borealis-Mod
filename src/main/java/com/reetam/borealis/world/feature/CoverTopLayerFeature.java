package com.reetam.borealis.world.feature;

import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.world.configuration.CoverTopLayerConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CoverTopLayerFeature extends Feature<CoverTopLayerConfiguration> {
    public CoverTopLayerFeature(Codec<CoverTopLayerConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<CoverTopLayerConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos blockpos$mutable1 = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 16; ++i) {
            for(int j = 0; j < 16; ++j) {
                int k = pos.getX() + i;
                int l = pos.getZ() + j;
                int i1 = level.getHeight(Heightmap.Types.MOTION_BLOCKING, k, l);
                blockpos$mutable.set(k, i1, l);
                blockpos$mutable1.set(blockpos$mutable).move(Direction.DOWN, 1);
                Holder<Biome> biome = level.getBiome(blockpos$mutable);

                BlockState state = context.config().state.getState(context.random(), blockpos$mutable);
                if (biome == context.config().biome && state.canSurvive(level, blockpos$mutable)) {
                    level.setBlock(blockpos$mutable, state, 2);
                }
            }
        }

        return true;
    }
}
