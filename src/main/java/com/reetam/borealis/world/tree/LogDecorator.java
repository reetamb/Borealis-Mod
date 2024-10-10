package com.reetam.borealis.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.world.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.ArrayList;
import java.util.List;

public class LogDecorator extends TreeDecorator {

    public static final MapCodec<LogDecorator> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((decorator) -> decorator.probability),
            BlockStateProvider.CODEC.fieldOf("block_provider").forGetter((decorator) -> decorator.provider)
            ).apply(instance, LogDecorator::new));

    private final float probability;
    private final BlockStateProvider provider;

    public LogDecorator(float probability, BlockStateProvider provider) {
        this.probability = probability;
        this.provider = provider;
    }
    @Override
    protected TreeDecoratorType<?> type() {
        return BorealisFeatures.TreePlacers.LOG_DECORATOR.get();
    }

    @Override
    public void place(Context context) {
        List<BlockPos> logs = context.logs();
        RandomSource random = context.random();

        for (BlockPos logPos : logs) {

            if (random.nextFloat() <= this.probability) {
                Direction choice = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                BlockState state = this.provider.getState(random, logPos);
                if (state.hasProperty(HorizontalDirectionalBlock.FACING)) {
                    state = state.setValue(HorizontalDirectionalBlock.FACING, choice);
                }
                if (!logs.contains(logPos.relative(choice)) && context.isAir(logPos.relative(choice))) {
                    context.setBlock(logPos.relative(choice), state);
                }
            }
        }
    }
}
