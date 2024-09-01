package com.reetam.borealis.block.entity;

import com.reetam.borealis.registry.BorealisBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class BorealisWallSignBlock extends WallSignBlock {

    public BorealisWallSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BorealisBlockEntities.BOREALIS_SIGN.get().create(pos, state);
    }
}
