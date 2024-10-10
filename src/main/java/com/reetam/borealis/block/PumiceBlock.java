package com.reetam.borealis.block;

import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class PumiceBlock extends Block {
    public PumiceBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        for (Direction direction : Direction.values()) {
            if (pLevel.getBlockState(pPos.relative(direction)).is(BorealisBlocks.PUMICE.get())) {
                if (pLevel.getRandom().nextInt(4) == 0 && !pLevel.isClientSide()) {
                    pLevel.destroyBlock(pPos.relative(direction), false);
                }
            }
        }
    }

    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        float breakChance = Math.min(1.0F, pFallDistance / 20.0F);
        if (pLevel.getRandom().nextFloat() <= breakChance && !pLevel.isClientSide()) {
            pLevel.destroyBlock(pPos, false);
            pEntity.causeFallDamage(pFallDistance, 0.15F, pLevel.damageSources().fall());
        } else {
            pEntity.causeFallDamage(pFallDistance, 0.4F, pLevel.damageSources().fall());
        }
    }
}
