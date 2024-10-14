package com.reetam.borealis.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.world.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;
import java.util.function.Predicate;

public class EpiphyteDecorator extends TreeDecorator {

    public static final MapCodec<EpiphyteDecorator> CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((decorator) -> decorator.probability)
            ).apply(instance, EpiphyteDecorator::new));
    private final float probability;

    public EpiphyteDecorator(float probability) {
        this.probability = probability;
    }
    @Override
    protected TreeDecoratorType<?> type() {
        return BorealisFeatures.TreePlacers.EPIPHYTE_DECORATOR.get();
    }
    @Override
    public void place(Context context) {
        List<BlockPos> leaves = context.leaves();
        RandomSource random = context.random();

        leaves.forEach((leafPos) -> {
            if (random.nextDouble() < this.probability) {
                int height = random.nextInt(1, 4);
                for (int i = 1; i < height; i++) {
                    if (!leaves.contains(leafPos.below(i)) && !leaves.contains(leafPos.below(i + 1)) && !context.logs().contains(leafPos.below(i))) {
                        context.setBlock(leafPos.below(i), BorealisBlocks.MISTERIA_BODY.get().defaultBlockState());
                    }
                }
            }
        });
    }
}
