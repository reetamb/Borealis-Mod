package com.reetam.borealis.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.world.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;

public class RootDecorator extends TreeDecorator {

    public static final Codec<RootDecorator> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((decorator) -> decorator.probability),
            BlockStateProvider.CODEC.fieldOf("block_provider").forGetter((decorator) -> decorator.provider)
    ).apply(instance, RootDecorator::new));

    private final float probability;
    private final BlockStateProvider provider;

    public RootDecorator(float probability, BlockStateProvider provider) {
        this.probability = probability;
        this.provider = provider;
    }
    @Override
    protected TreeDecoratorType<?> type() {
        return BorealisFeatures.TreePlacers.ROOT_DECORATOR.get();
    }

    @Override
    public void place(Context context) {
        List<BlockPos> logs = context.logs();

        for (BlockPos logPos : logs) {
            if (context.random().nextFloat() <= this.probability) {
                while (logs.contains(logPos)) logPos = logPos.offset(0, 1, 0);
                if (!context.leaves().contains(logPos)) {
                    context.setBlock(logPos, this.provider.getState(context.random(), logPos));
                }
            }
        }
    }
}
