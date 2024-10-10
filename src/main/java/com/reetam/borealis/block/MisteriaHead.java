package com.reetam.borealis.block;

import com.mojang.serialization.MapCodec;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MisteriaHead extends GrowingPlantHeadBlock {
    protected static final VoxelShape SHAPE = Block.box(4.0, 9.0, 4.0, 12.0, 16.0, 12.0);
    public MisteriaHead(Properties pProperties) {
        super(pProperties, Direction.DOWN, SHAPE, false, 0.1F);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState aboveBlock = pLevel.getBlockState(pPos.relative(this.growthDirection.getOpposite()));
        return aboveBlock.is(BorealisBlocks.MISTERIA_BODY.get()) || aboveBlock.is(BorealisBlocks.MISTERIA_HEAD.get()) || aboveBlock.is(BlockTags.LEAVES);
    }

    @Override
    protected MapCodec<? extends GrowingPlantHeadBlock> codec() {
        return simpleCodec(MisteriaHead::new);
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
        return randomSource.nextInt(2) + 1;
    }

    @Override
    protected boolean canGrowInto(BlockState blockState) {
        return blockState.isAir();
    }

    @Override
    protected Block getBodyBlock() {
        return BorealisBlocks.MISTERIA_BODY.get();
    }
}
