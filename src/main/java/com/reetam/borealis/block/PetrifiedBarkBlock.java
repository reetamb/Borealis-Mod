package com.reetam.borealis.block;

import com.reetam.borealis.data.trigger.BorealisTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class PetrifiedBarkBlock extends RotatedPillarBlock {
    private final RotatedPillarBlock core;
    public PetrifiedBarkBlock(RotatedPillarBlock coreBlock, Properties properties) {
        super(properties.noLootTable());
        core = coreBlock;
    }

    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        pLevel.setBlock(pPos, core.defaultBlockState().setValue(AXIS, pState.getValue(AXIS)), 3);
        BorealisTriggers.BREAK_BLOCK.get().trigger((ServerPlayer)pPlayer, pState);
    }
}
