package com.reetam.borealis.world.tree;

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

public class PineyFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<PineyFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            foliagePlacerParts(instance).and(IntProvider.codec(0, 16)
                            .fieldOf("trunk_height")
                            .forGetter((placer) -> placer.trunkHeight))
                    .apply(instance, PineyFoliagePlacer::new));
    private final IntProvider trunkHeight;

    public PineyFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.trunkHeight = height;
    }

    protected FoliagePlacerType<?> type() {
        return BorealisFeatures.TreePlacers.PINEY_FOLIAGE_PLACER.get();
    }

    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource rand, TreeConfiguration config, int maxHeight, FoliagePlacer.FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos blockpos = attachment.pos();
        int i = 0;
        int width;

        for(int l = offset; l >= -foliageHeight; --l) {
            width = i % 3 + (i > 2 ? 1 : 0);
            this.placeLeavesRow(level, blockSetter, rand, config, blockpos, width, l, attachment.doubleTrunk());
            i++;
        }
    }

    public int foliageHeight(RandomSource rand, int height, TreeConfiguration config) {
        return Math.max(4, height - this.trunkHeight.sample(rand));
    }

    protected boolean shouldSkipLocation(RandomSource rand, int localX, int localY, int localZ, int range, boolean large) {
        return Math.abs(localX) + Math.abs(localZ) > range;
    }
}
