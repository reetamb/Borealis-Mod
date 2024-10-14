package com.reetam.borealis.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.world.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("trunk_height").forGetter((placer) -> placer.trunkHeight)).apply(instance, PalmFoliagePlacer::new));
    private final int trunkHeight;

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.trunkHeight = height;
    }

    protected FoliagePlacerType<?> type() {
        return BorealisFeatures.TreePlacers.PALM_FOLIAGE_PLACER.get();
    }

    public void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource rand, TreeConfiguration config, int maxHeight, FoliagePlacer.FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos blockpos = attachment.pos();

        this.placeLeavesRow(level, blockSetter, rand, config, blockpos, 1, offset, attachment.doubleTrunk());
        this.placeLeavesRow(level, blockSetter, rand, config, blockpos, 3, offset-1, attachment.doubleTrunk());
        this.placeLeavesRowWithHangingLeavesBelow(level, blockSetter, rand, config, blockpos, 4, offset-2, attachment.doubleTrunk(), 0.5F, 0.5F);
    }

    public int foliageHeight(RandomSource rand, int height, TreeConfiguration config) {
        return this.trunkHeight;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource rand, int localX, int localY, int localZ, int range, boolean large) {
        if (range <= 3) return localX + localZ > range && range > 0;
        else return range * (Math.abs(localX - range + 1) + localZ) > range && range * (Math.abs(localZ - range + 1) + localX) > range;
    }
}
