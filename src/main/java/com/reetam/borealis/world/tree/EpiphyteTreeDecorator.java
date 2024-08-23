package com.reetam.borealis.world.tree;

import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.world.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;
import java.util.function.Predicate;

public class EpiphyteTreeDecorator extends TreeDecorator {

    public static final Codec<EpiphyteTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(EpiphyteTreeDecorator::new, (decorator) -> decorator.probability).codec();
    private final float probability;

    public EpiphyteTreeDecorator(float probability) {
        this.probability = probability;
    }
    @Override
    protected TreeDecoratorType<?> type() {
        return BorealisFeatures.TreePlacers.EPIPHYTE_TREE_DECORATOR.get();
    }
    @Override
    public void place(Context context) {
        List<BlockPos> leaves = context.leaves();
        LevelSimulatedReader level = context.level();
        RandomSource random = context.random();
        for (BlockPos leafPos : leaves) {
            if (context.isAir(leafPos.below()) && random.nextDouble() < this.probability) {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        if (level.isStateAtPosition(leafPos.below().east(x).south(z), Predicate.isEqual(BorealisBlocks.MISTERIA_BODY.get().defaultBlockState()))) {
                            return;
                        }
                    }
                }
                int height = random.nextInt(1, 4);
                for (int i = 1; i < height; i++) {
                    if (context.isAir(leafPos.below(i)) && context.isAir(leafPos.below(i + 1))) {
                        context.setBlock(leafPos.below(i), BorealisBlocks.MISTERIA_BODY.get().defaultBlockState());
                    }
                }
                context.setBlock(leafPos.below(height), BorealisBlocks.MISTERIA_HEAD.get().defaultBlockState());
            }
        }
    }
}
