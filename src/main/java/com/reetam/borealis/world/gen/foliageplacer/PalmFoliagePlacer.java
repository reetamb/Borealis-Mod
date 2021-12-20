package com.reetam.borealis.world.gen.foliageplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class PalmFoliagePlacer extends FoliagePlacer {

    public static final Codec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.create((p_242836_0_) -> {
        return foliagePlacerParts(p_242836_0_).and(IntProvider.codec(0, 16).fieldOf("trunk_height").forGetter((p_242835_0_) -> {
            return p_242835_0_.trunkHeight;
        })).apply(p_242836_0_, PalmFoliagePlacer::new);
    });
    private final IntProvider trunkHeight;

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.trunkHeight = height;
    }

    protected FoliagePlacerType<?> type() {
        return BorealisFeatures.TreePlacers.PALM_FOLIAGE_PLACER.get();
    }

    public void createFoliage(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, Random rand, TreeConfiguration config, int maxHeight, FoliagePlacer.FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
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

    public int foliageHeight(Random rand, int height, TreeConfiguration config) {
        return Math.max(4, height - this.trunkHeight.sample(rand));
    }

    protected boolean shouldSkipLocation(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && p_230373_5_ > 0;
    }
}
