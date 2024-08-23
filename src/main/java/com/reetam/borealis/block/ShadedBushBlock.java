package com.reetam.borealis.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ShadedBushBlock extends BushBlock {
    private final TagKey<Block> substrate;
    public ShadedBushBlock(TagKey<Block> substrate, Properties pProperties) {
        super(pProperties.randomTicks());
        this.substrate = substrate;
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return (super.canSurvive(pState, pLevel, pPos) || pLevel.getBlockState(pPos.below()).is(this.substrate)) && !pLevel.canSeeSky(pPos);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.canSeeSky(pPos)) {
            pLevel.removeBlock(pPos, false);
        }
    }
}
