package com.reetam.borealis.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
}
