package com.reetam.borealis.world.feature;

import com.mojang.serialization.Codec;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public class BerylFeature extends Feature<BlockStateConfiguration> {

    public BerylFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
        RandomSource rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();

        if (!level.getBlockState(pos).is(BorealisTags.Blocks.SOAPSTONE_ORE_REPLACEABLES)) {
            return false;
        }
        Direction direction = new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST}[rand.nextInt(4)];
        BlockState state = context.config().state.getBlock().defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis());
        Direction perpendicular = direction.getClockWise();

        int length = rand.nextInt(3) + 3;
        BlockPos pos$0 = pos.relative(direction, rand.nextInt(length) / 2);
        for (int i = 0; i <= length; i++) {
            setBlock(level, pos$0.relative(direction, i), state);
        }
        length = rand.nextInt(3) + 3;
        BlockPos pos$1 = pos.below().relative(direction, rand.nextInt(length) / 2);
        for (int i = 0; i <= length; i++) {
            setBlock(level, pos$1.relative(direction, i), state);
        }
        length = rand.nextInt(3) + 3;
        BlockPos pos$2 = pos.relative(perpendicular, 1).relative(direction, rand.nextInt(length) / 2);
        for (int i = 0; i <= length; i++) {
            setBlock(level, pos$2.relative(direction, i), state);
        }
        length = rand.nextInt(3) + 3;
        BlockPos pos$3 = pos.relative(perpendicular, 1).below().relative(direction, rand.nextInt(length) / 2);
        for (int i = 0; i <= length; i++) {
            setBlock(level, pos$3.relative(direction, i), state);
        }

        return true;
    }
}
