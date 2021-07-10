package com.reetam.borealis.world.gen.foliageplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Random;
import java.util.Set;

import com.reetam.borealis.registry.BorealisFeatures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {
    public static final Codec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.create((p_242836_0_) -> {
        return foliagePlacerParts(p_242836_0_).and(FeatureSpread.codec(0, 16, 8).fieldOf("trunk_height").forGetter((p_242835_0_) -> {
            return p_242835_0_.trunkHeight;
        })).apply(p_242836_0_, PalmFoliagePlacer::new);
    });
    private final FeatureSpread trunkHeight;

    public PalmFoliagePlacer(FeatureSpread p_i242003_1_, FeatureSpread p_i242003_2_, FeatureSpread p_i242003_3_) {
        super(p_i242003_1_, p_i242003_2_);
        this.trunkHeight = p_i242003_3_;
    }

    protected FoliagePlacerType<?> type() {
        return BorealisFeatures.TreePlacers.PALM_FOLIAGE_PLACER.get();
    }

    protected void createFoliage(IWorldGenerationReader reader, Random random, BaseTreeFeatureConfig config, int p_230372_4_, FoliagePlacer.Foliage foliage, int foliageBottomHeight, int p_230372_7_, Set<BlockPos> p_230372_8_, int foliageTopHeight, MutableBoundingBox boundingBox) {
        BlockPos blockpos = foliage.foliagePos();

        this.placeLeavesRow(reader, random, config, blockpos, 1, p_230372_8_, foliageTopHeight, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos, 2, p_230372_8_, foliageTopHeight-1, foliage.doubleTrunk(), boundingBox);

        this.placeLeavesRow(reader, random, config, blockpos.west(2), 1, p_230372_8_, foliageTopHeight-1, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos.north(2), 1, p_230372_8_, foliageTopHeight-1, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos.east(2), 1, p_230372_8_, foliageTopHeight-1, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos.south(2), 1, p_230372_8_, foliageTopHeight-1, foliage.doubleTrunk(), boundingBox);

        this.placeLeavesRow(reader, random, config, blockpos.west(3), 1, p_230372_8_, foliageTopHeight-2, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos.north(3), 1, p_230372_8_, foliageTopHeight-2, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos.east(3), 1, p_230372_8_, foliageTopHeight-2, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos.south(3), 1, p_230372_8_, foliageTopHeight-2, foliage.doubleTrunk(), boundingBox);
    }

    public int foliageHeight(Random p_230374_1_, int p_230374_2_, BaseTreeFeatureConfig p_230374_3_) {
        return Math.max(4, p_230374_2_ - this.trunkHeight.sample(p_230374_1_));
    }

    protected boolean shouldSkipLocation(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && p_230373_5_ > 0;
    }
}
