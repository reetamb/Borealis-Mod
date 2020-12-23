package com.reetam.borealis.world.gen.foliageplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Random;
import java.util.Set;

import com.reetam.borealis.registry.BorealisTreePlacers;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {
    public static final Codec<PalmFoliagePlacer> field_236790_a_ = RecordCodecBuilder.create((p_242836_0_) -> {
        return func_242830_b(p_242836_0_).and(FeatureSpread.func_242254_a(0, 16, 8).fieldOf("trunk_height").forGetter((p_242835_0_) -> {
            return p_242835_0_.field_236791_b_;
        })).apply(p_242836_0_, PalmFoliagePlacer::new);
    });
    private final FeatureSpread field_236791_b_;

    public PalmFoliagePlacer(FeatureSpread p_i242003_1_, FeatureSpread p_i242003_2_, FeatureSpread p_i242003_3_) {
        super(p_i242003_1_, p_i242003_2_);
        this.field_236791_b_ = p_i242003_3_;
    }

    protected FoliagePlacerType<?> func_230371_a_() {
        return BorealisTreePlacers.PALM_FOLIAGE_PLACER.get();
    }

    protected void func_230372_a_(IWorldGenerationReader reader, Random random, BaseTreeFeatureConfig config, int p_230372_4_, FoliagePlacer.Foliage foliage, int p_230372_6_, int p_230372_7_, Set<BlockPos> p_230372_8_, int p_230372_9_, MutableBoundingBox p_230372_10_) {
        BlockPos blockpos = foliage.func_236763_a_();
        int stacks = config.trunkPlacer.func_236917_a_(random) / 2 - 1;

        reader.setBlockState(blockpos, config.leavesProvider.getBlockState(random, blockpos), 19);

        // this can be done more efficiently but for some reason ITS NOT WORKING so im doing it the dumb way for now
        reader.setBlockState(blockpos.west(), config.leavesProvider.getBlockState(random, blockpos), 19);
        reader.setBlockState(blockpos.north(), config.leavesProvider.getBlockState(random, blockpos), 19);
        reader.setBlockState(blockpos.east(), config.leavesProvider.getBlockState(random, blockpos), 19);
        reader.setBlockState(blockpos.south(), config.leavesProvider.getBlockState(random, blockpos), 19);
        for (int i = 0; i < stacks; i++) {
            reader.setBlockState(blockpos.west(i+1).down(i), config.leavesProvider.getBlockState(random, blockpos), 19);
            reader.setBlockState(blockpos.west(i+1).down(i+1), config.leavesProvider.getBlockState(random, blockpos), 19);

            reader.setBlockState(blockpos.north(i+1).down(i), config.leavesProvider.getBlockState(random, blockpos), 19);
            reader.setBlockState(blockpos.north(i+1).down(i+1), config.leavesProvider.getBlockState(random, blockpos), 19);

            reader.setBlockState(blockpos.east(i+1).down(i), config.leavesProvider.getBlockState(random, blockpos), 19);
            reader.setBlockState(blockpos.east(i+1).down(i+1), config.leavesProvider.getBlockState(random, blockpos), 19);

            reader.setBlockState(blockpos.south(i+1).down(i), config.leavesProvider.getBlockState(random, blockpos), 19);
            reader.setBlockState(blockpos.south(i+1).down(i+1), config.leavesProvider.getBlockState(random, blockpos), 19);
        }

    }
//    private static BlockPos direct(BlockPos blockpos, int ind, int amount) {
//        if (ind == 0) {
//            blockpos = blockpos.west(ind);
//        } else if (ind == 1) {
//            blockpos = blockpos.north(ind);
//        } else if (ind == 2) {
//            blockpos = blockpos.east(ind);
//        } else if (ind == 3) {
//            blockpos = blockpos.south(ind);
//        }
//        return blockpos;
//    }
    public int func_230374_a_(Random p_230374_1_, int p_230374_2_, BaseTreeFeatureConfig p_230374_3_) {
        return Math.max(4, p_230374_2_ - this.field_236791_b_.func_242259_a(p_230374_1_));
    }

    protected boolean func_230373_a_(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && p_230373_5_ > 0;
    }
}
