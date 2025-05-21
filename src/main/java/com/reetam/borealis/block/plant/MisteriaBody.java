package com.reetam.borealis.block.plant;

import com.mojang.serialization.MapCodec;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MisteriaBody extends GrowingPlantBodyBlock {
    public static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public MisteriaBody(BlockBehaviour.Properties p_154975_) {
        super(p_154975_.randomTicks(), Direction.DOWN, SHAPE, false);
    }

    protected GrowingPlantHeadBlock getHeadBlock() {
        return BorealisBlocks.MISTERIA_HEAD.get();
    }

    @Override
    protected MapCodec<? extends GrowingPlantBodyBlock> codec() {
        return simpleCodec(MisteriaBody::new);
    }

    @Override
    protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (this.canSurvive(pState, pLevel, pPos)) {
            if (!pLevel.getBlockState(pPos.below()).is(this) && !pLevel.getBlockState(pPos.below()).is(BorealisBlocks.MISTERIA_HEAD)) {
                if (pLevel.getBlockState(pPos.above()).is(BorealisBlocks.MISTERIA_HEAD) || pLevel.getBlockState(pPos.above()).is(BlockTags.LEAVES)) {
                    pLevel.setBlock(pPos, BorealisBlocks.MISTERIA_HEAD.get().defaultBlockState(), 3);
                } else {
                    this.destroy(pLevel, pPos, pState);
                }
            }
        } else {
            this.destroy(pLevel, pPos, pState);
        }
    }
}
