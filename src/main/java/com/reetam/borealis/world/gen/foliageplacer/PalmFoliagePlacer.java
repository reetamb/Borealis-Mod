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

    protected void func_230372_a_(IWorldGenerationReader reader, Random random, BaseTreeFeatureConfig config, int p_230372_4_, FoliagePlacer.Foliage foliage, int foliageBottomHeight, int p_230372_7_, Set<BlockPos> p_230372_8_, int foliageTopHeight, MutableBoundingBox boundingBox) {
        BlockPos blockpos = foliage.func_236763_a_();

        this.func_236753_a_(reader, random, config, blockpos, 1, p_230372_8_, foliageTopHeight, foliage.func_236765_c_(), boundingBox);
        this.func_236753_a_(reader, random, config, blockpos, 2, p_230372_8_, foliageTopHeight-1, foliage.func_236765_c_(), boundingBox);

        this.func_236753_a_(reader, random, config, blockpos.west(2), 1, p_230372_8_, foliageTopHeight-1, foliage.func_236765_c_(), boundingBox);
        this.func_236753_a_(reader, random, config, blockpos.north(2), 1, p_230372_8_, foliageTopHeight-1, foliage.func_236765_c_(), boundingBox);
        this.func_236753_a_(reader, random, config, blockpos.east(2), 1, p_230372_8_, foliageTopHeight-1, foliage.func_236765_c_(), boundingBox);
        this.func_236753_a_(reader, random, config, blockpos.south(2), 1, p_230372_8_, foliageTopHeight-1, foliage.func_236765_c_(), boundingBox);

        this.func_236753_a_(reader, random, config, blockpos.west(3), 1, p_230372_8_, foliageTopHeight-2, foliage.func_236765_c_(), boundingBox);
        this.func_236753_a_(reader, random, config, blockpos.north(3), 1, p_230372_8_, foliageTopHeight-2, foliage.func_236765_c_(), boundingBox);
        this.func_236753_a_(reader, random, config, blockpos.east(3), 1, p_230372_8_, foliageTopHeight-2, foliage.func_236765_c_(), boundingBox);
        this.func_236753_a_(reader, random, config, blockpos.south(3), 1, p_230372_8_, foliageTopHeight-2, foliage.func_236765_c_(), boundingBox);
    }

    public int func_230374_a_(Random p_230374_1_, int p_230374_2_, BaseTreeFeatureConfig p_230374_3_) {
        return Math.max(4, p_230374_2_ - this.field_236791_b_.func_242259_a(p_230374_1_));
    }

    protected boolean func_230373_a_(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && p_230373_5_ > 0;
    }
}
