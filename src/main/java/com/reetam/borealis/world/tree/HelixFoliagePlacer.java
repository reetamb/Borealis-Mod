package com.reetam.borealis.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.world.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class HelixFoliagePlacer extends FoliagePlacer {

    public static final Codec<HelixFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return foliagePlacerParts(instance).and(IntProvider.codec(0, 16).fieldOf("trunk_height").forGetter((placer) -> {
            return placer.trunkHeight;
        })).apply(instance, HelixFoliagePlacer::new);
    });
    private final IntProvider trunkHeight;

    public HelixFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.trunkHeight = height;
    }

    public int foliageHeight(RandomSource rand, int height, TreeConfiguration config) {
        return Math.max(4, height - this.trunkHeight.sample(rand));
    }

    protected FoliagePlacerType<?> type() {
        return BorealisFeatures.TreePlacers.HELIX_FOLIAGE_PLACER.get();
    }

    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource rand, TreeConfiguration config, int maxHeight, FoliagePlacer.FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos blockpos = attachment.pos();
        int i = 0;

        for(int l = offset; l >= -foliageHeight; --l) {
            this.placeLeavesRow(level, blockSetter, rand, config, nextLeaf(blockpos, i), 1, l, attachment.doubleTrunk());
            this.placeLeavesRow(level, blockSetter, rand, config, nextLeaf(blockpos, i).below(), 0, l, attachment.doubleTrunk());
            i++;
        }
        this.placeLeavesRow(level, blockSetter, rand, config, blockpos, 0, offset+1, attachment.doubleTrunk());
    }

    private BlockPos nextLeaf(BlockPos pos, int i) {
        return switch (i % 4) {
            case 0 -> pos.north();
            case 1 -> pos.east();
            case 2 -> pos.south();
            case 3 -> pos.west();
            default -> pos;
        };
    }

    protected boolean shouldSkipLocation(RandomSource rand, int localX, int localY, int localZ, int range, boolean large) {
        return localX == range && localZ == range && range > 0;
    }
}
