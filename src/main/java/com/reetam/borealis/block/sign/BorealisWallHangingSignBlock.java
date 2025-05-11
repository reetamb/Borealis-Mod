package com.reetam.borealis.block.sign;

import com.reetam.borealis.registry.BorealisBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class BorealisWallHangingSignBlock extends WallHangingSignBlock {
    public BorealisWallHangingSignBlock(Properties pProperties, WoodType pType) {
        super(pType, pProperties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BorealisBlockEntities.BOREALIS_HANGING_SIGN.get().create(pos, state);
    }
}
