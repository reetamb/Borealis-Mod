package com.reetam.borealis.block;

import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;

public class AlmsBlock extends Block {
    public AlmsBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
        if (pNeighborBlock == BorealisFluids.HOT_SPRING_WATER_BLOCK.get()) {
            pLevel.setBlock(pPos, BorealisBlocks.CRACKED_ALMS.get().defaultBlockState().setValue(AlmsCrackedBlock.EMPTY, false), 3);
        } else if (pNeighborBlock.defaultBlockState().is(BlockTags.LEAVES) && pNeighborPos == pPos.above()) {
            FallingBlockEntity falling = FallingBlockEntity.fall(pLevel, pPos, pState);
            falling.setHurtsEntities(1.5F, 6);
            falling.disableDrop();
        }
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        Arrays.stream(Direction.values()).forEach((direction) -> {
            if (pLevel.getBlockState(pPos.relative(direction)).is(BorealisFluids.HOT_SPRING_WATER_BLOCK.get())) {
                pLevel.setBlock(pPos, BorealisBlocks.CRACKED_ALMS.get().defaultBlockState(), 3);
            }
        });
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return super.canSurvive(pState, pLevel, pPos);
    }
}
