package com.reetam.borealis.block.sign;

import com.reetam.borealis.registry.BorealisBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class BorealisStandingSignBlock extends StandingSignBlock {

    public BorealisStandingSignBlock(Properties properties, WoodType woodType) {
        super(woodType, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BorealisBlockEntities.BOREALIS_SIGN.get().create(pos, state);
    }
}
