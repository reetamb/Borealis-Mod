package com.reetam.borealis.block.plant;

import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class AlmsCrackedBlock extends Block {
    public static final BooleanProperty EMPTY = BooleanProperty.create("empty");
    public AlmsCrackedBlock(Properties pProperties) {
        super(pProperties.noLootTable());
        this.registerDefaultState(this.defaultBlockState().setValue(EMPTY, true));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (!pState.getValue(EMPTY)) {
            popResource(pLevel, pPos.above(), new ItemStack(BorealisItems.ALMS_NUT.get(), 1));
            pLevel.setBlockAndUpdate(pPos, pState.setValue(EMPTY, true));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(EMPTY, true);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(EMPTY);
    }
}
