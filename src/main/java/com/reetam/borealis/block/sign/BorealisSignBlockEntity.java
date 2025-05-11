package com.reetam.borealis.block.sign;

import com.reetam.borealis.registry.BorealisBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BorealisSignBlockEntity extends SignBlockEntity {

    public BorealisSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return BorealisBlockEntities.BOREALIS_SIGN.get();
    }
}
