package com.reetam.borealis.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.world.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;
import java.util.function.Predicate;

public class UndergrowthDecorator extends TreeDecorator {

    public static final Codec<UndergrowthDecorator> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((decorator) -> decorator.probability),
            BlockStateProvider.CODEC.fieldOf("block_provider").forGetter((decorator) -> decorator.provider)
    ).apply(instance, UndergrowthDecorator::new));

    private final BlockStateProvider provider;
    private final float probability;
    public UndergrowthDecorator(float probability, BlockStateProvider provider) {
        this.provider = provider;
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return BorealisFeatures.TreePlacers.UNDERGROWTH_DECORATOR.get();
    }

    @Override
    public void place(Context context) {
        List<BlockPos> leaves = context.leaves();
        LevelSimulatedReader level = context.level();
        RandomSource random = context.random();
        for (BlockPos leafPos : leaves) {
            BlockState state = this.provider.getState(random, leafPos);
            if (random.nextFloat() <= this.probability) {
                for (int i = 1; i < 10; i++) {
                    if (level.isStateAtPosition(leafPos.below(i), (ground) -> ground.is(BlockTags.DIRT)) &&
                            context.isAir(leafPos.below(i - 1)) &&
                            state.canSurvive((LevelReader) level, leafPos.below(i))) {
                        if (state.getBlock() instanceof DoublePlantBlock) {
                            if (context.isAir(leafPos.below(i - 2))) {
                                context.setBlock(leafPos.below(i - 1), state);
                                context.setBlock(leafPos.below(i - 2), state.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
                            }
                        } else {
                            context.setBlock(leafPos.below(i - 1), state);
                        }
                    }
                }
            }
        }
    }
}
