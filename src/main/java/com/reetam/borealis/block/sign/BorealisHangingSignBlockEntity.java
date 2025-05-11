package com.reetam.borealis.block.sign;

import com.reetam.borealis.registry.BorealisBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BorealisHangingSignBlockEntity extends HangingSignBlockEntity {

    public BorealisHangingSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return BorealisBlockEntities.BOREALIS_HANGING_SIGN.get();
    }
}
