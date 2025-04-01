package com.reetam.borealis.block;

import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BoneDryWood extends RotatedPillarBlock {
    public BoneDryWood(Properties properties) {
        super(properties.randomTicks());
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(40) != 0) return;
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        if (!level.getBlockState(pos.relative(direction)).canBeReplaced()) return;
        if (level.getBlockState(pos.relative(direction)).is(BorealisBlocks.MARROW.get())) return;
        level.setBlock(pos.relative(direction), BorealisBlocks.MARROW.get().calculateState(level, pos.relative(direction), BorealisBlocks.MARROW.get().defaultBlockState(), direction), 3);
    }
}
