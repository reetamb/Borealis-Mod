package com.reetam.borealis.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.world.BorealisFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class TreeNutDecorator extends TreeDecorator {

    public static final MapCodec<TreeNutDecorator> CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                    Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((decorator) -> decorator.probability)
            ).apply(instance, TreeNutDecorator::new));
    private final float probability;

    public TreeNutDecorator(float probability) {
        this.probability = probability;
    }
    @Override
    protected TreeDecoratorType<?> type() {
        return BorealisFeatures.TreePlacers.TREE_NUT_DECORATOR.get();
    }
    @Override
    public void place(Context context) {
        if (! context.logs().isEmpty()) {
            BlockPos topLog = context.logs().top();

            Direction.Plane.HORIZONTAL.stream().forEach((direction) -> {
                if (context.random().nextFloat() <= this.probability) {
                    context.setBlock(topLog.relative(direction), BorealisBlocks.ALMS.get().defaultBlockState());
                }
            });
        }
    }
}
