package com.reetam.borealis.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.world.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return foliagePlacerParts(instance).and(IntProvider.codec(0, 16).fieldOf("trunk_height").forGetter((placer) -> {
            return placer.trunkHeight;
        })).apply(instance, PalmFoliagePlacer::new);
    });
    private final IntProvider trunkHeight;

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.trunkHeight = height;
    }

    protected FoliagePlacerType<?> type() {
        return BorealisFeatures.TreePlacers.PALM_FOLIAGE_PLACER.get();
    }

    public void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource rand, TreeConfiguration config, int maxHeight, FoliagePlacer.FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos blockpos = attachment.pos();

        this.placeLeavesRow(level, blockSetter, rand, config, blockpos, 1, offset, attachment.doubleTrunk());
        this.placeLeavesRow(level, blockSetter, rand, config, blockpos, 2, offset-1, attachment.doubleTrunk());

        this.placeLeavesRow(level, blockSetter, rand, config, blockpos.west(2), 1, offset-1, attachment.doubleTrunk());
        this.placeLeavesRow(level, blockSetter, rand, config, blockpos.north(2), 1, offset-1, attachment.doubleTrunk());
        this.placeLeavesRow(level, blockSetter, rand, config, blockpos.east(2), 1, offset-1, attachment.doubleTrunk());
        this.placeLeavesRow(level, blockSetter, rand, config, blockpos.south(2), 1, offset-1, attachment.doubleTrunk());

        this.placeLeavesRow(level, blockSetter, rand, config, blockpos.west(3), 1, offset-2, attachment.doubleTrunk());
        this.placeLeavesRow(level, blockSetter, rand, config, blockpos.north(3), 1, offset-2, attachment.doubleTrunk());
        this.placeLeavesRow(level, blockSetter, rand, config, blockpos.east(3), 1, offset-2, attachment.doubleTrunk());
        this.placeLeavesRow(level, blockSetter, rand, config, blockpos.south(3), 1, offset-2, attachment.doubleTrunk());
    }

    public int foliageHeight(RandomSource rand, int height, TreeConfiguration config) {
        return Math.max(4, height - this.trunkHeight.sample(rand));
    }

    protected boolean shouldSkipLocation(RandomSource rand, int localX, int localY, int localZ, int range, boolean large) {
        return localX == range && localZ == range && range > 0;
    }
}
