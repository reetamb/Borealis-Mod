package com.reetam.borealis.block.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShadedDoublePlantBlock extends DoublePlantBlock {
    private final List<TagKey<Block>> substrate;
    public ShadedDoublePlantBlock(Properties pProperties, TagKey<Block> substrates) {
        super(pProperties.randomTicks());
        this.substrate = List.of(substrates);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        AtomicBoolean flag = new AtomicBoolean(false);
        this.substrate.stream().forEach((type) -> {
            if (pLevel.getBlockState(pPos.below()).is(type) || pLevel.getBlockState(pPos.below()).is(this)) flag.set(true);
        });
        return flag.get() && !pLevel.canSeeSky(pPos) && super.canSurvive(pState, pLevel, pPos);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.canSeeSky(pPos)) {
            pLevel.removeBlock(pPos, false);
        }
    }
}
