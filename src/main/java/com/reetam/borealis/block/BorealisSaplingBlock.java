package com.reetam.borealis.block;

import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class BorealisSaplingBlock extends SaplingBlock {
    public BorealisSaplingBlock(AbstractTreeGrower tree) {
        super(tree, Properties.of(Material.PLANT)
                .strength(0F)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion()
                .noCollission()
        );
    }

    @Override
    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, Random rand) {
        if (state.getValue(STAGE) == 0) {
            level.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(level, rand, pos)) return;
            super.advanceTree(level, pos, state, rand);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random rand) {
        super.randomTick(state, level, pos, rand);
        if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        this.advanceTree(level, pos, state, rand);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return level.getBlockState(blockpos).canSustainPlant(level, blockpos, Direction.UP, this);
        return super.canSurvive(state, level, blockpos) || level.getBlockState(blockpos).is(BorealisBlocks.LIVING_SNOW_BLOCK.get()) || level.getBlockState(blockpos).is(BorealisBlocks.SUGAR_SNOW_BLOCK.get());
    }
}
