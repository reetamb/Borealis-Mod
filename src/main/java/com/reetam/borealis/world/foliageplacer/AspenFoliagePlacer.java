package com.reetam.borealis.world.foliageplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class AspenFoliagePlacer extends FoliagePlacer {
    public static final Codec<AspenFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return foliagePlacerParts(instance).and(IntProvider.codec(0, 16).fieldOf("trunk_height").forGetter((placer) -> {
            return placer.trunkHeight;
        })).apply(instance, AspenFoliagePlacer::new);
    });
    private final IntProvider trunkHeight;

    public AspenFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.trunkHeight = height;
    }

    protected FoliagePlacerType<?> type() {
        return BorealisFeatures.TreePlacers.ASPEN_FOLIAGE_PLACER.get();
    }

    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource rand, TreeConfiguration config, int maxHeight, FoliagePlacer.FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos blockpos = attachment.pos();
        int i = 0;
        int width;

        for(int l = offset; l >= -foliageHeight; --l) {
            width = (int) Math.ceil(-Math.abs(0.5*(i-4)))+2;
            this.placeLeavesRow(level, blockSetter, rand, config, blockpos, width, l, attachment.doubleTrunk());
            i++;
        }
    }

    public int foliageHeight(RandomSource rand, int height, TreeConfiguration config) {
        return Math.max(4, height - this.trunkHeight.sample(rand));
    }

    protected boolean shouldSkipLocation(RandomSource rand, int localX, int localY, int localZ, int range, boolean large) {
        return localX == range - rand.nextInt(2) && localZ == range - rand.nextInt(2) && range > 0;
        // TODO: make this more circular and less like a square
    }
}
