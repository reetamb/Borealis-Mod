package com.reetam.borealis.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class TallFrostfirTreeFeature extends Feature<BaseTreeFeatureConfig> {

    public TallFrostfirTreeFeature(Codec<BaseTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BaseTreeFeatureConfig config) {
        return false;
    }
}
