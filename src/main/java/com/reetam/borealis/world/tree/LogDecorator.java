package com.reetam.borealis.world.tree;

import com.mojang.serialization.Codec;
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

    public static final Codec<LogDecorator> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
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

        ArrayList<Direction> open = new ArrayList<>();
        for (BlockPos logPos : logs) {
            Direction.Plane.HORIZONTAL.stream().forEach((direction) -> {
                if (context.isAir(logPos.relative(direction)) && context.isAir(logPos.above()) && !logs.contains(logPos.relative(direction))) {
                    open.add(direction);
                }
            });

            if (random.nextFloat() <= this.probability && !open.isEmpty()) {
                Direction choice = open.get(random.nextInt(open.size()));
                BlockState state = this.provider.getState(random, logPos);
                if (state.hasProperty(HorizontalDirectionalBlock.FACING)) {
                    state = state.setValue(HorizontalDirectionalBlock.FACING, choice);
                }
                if (!logs.contains(logPos.relative(choice))) {
                    context.setBlock(logPos.relative(choice), state);
                }
            }
        }
    }
}
