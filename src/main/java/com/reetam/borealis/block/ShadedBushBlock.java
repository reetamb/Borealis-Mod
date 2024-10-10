package com.reetam.borealis.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShadedBushBlock extends BushBlock {
    private final List<TagKey<Block>> substrate;
    public ShadedBushBlock(Properties pProperties, TagKey<Block> substrate) {
        super(pProperties.randomTicks());
        this.substrate = List.of(substrate);
    }

    public ShadedBushBlock(Properties properties, List<TagKey<Block>> substrates) {
        super(properties.randomTicks());
        this.substrate = substrates;
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return simpleCodec((properties) -> new ShadedBushBlock(properties, substrate));
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        AtomicBoolean flag = new AtomicBoolean(false);
        this.substrate.stream().forEach((type) -> {
            if (pLevel.getBlockState(pPos.below()).is(type)) flag.set(true);
        });
        return flag.get() && !pLevel.canSeeSky(pPos);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.canSeeSky(pPos)) {
            pLevel.removeBlock(pPos, false);
        }
    }
}
