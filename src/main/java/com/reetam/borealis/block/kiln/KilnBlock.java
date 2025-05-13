package com.reetam.borealis.block.kiln;

import com.mojang.serialization.MapCodec;
import com.reetam.borealis.registry.BorealisBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class KilnBlock extends AbstractFurnaceBlock {
    public KilnBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends AbstractFurnaceBlock> codec() {
        return simpleCodec(KilnBlock::new);
    }

    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new KilnBlockEntity(pPos, pState);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createFurnaceTicker(pLevel, pBlockEntityType, BorealisBlockEntities.KILN.get());
    }

    protected void openContainer(Level pLevel, BlockPos pPos, Player pPlayer) {
        if (!pLevel.isClientSide()) {
            BlockEntity $$3 = pLevel.getBlockEntity(pPos);
            if ($$3 instanceof KilnBlockEntity) {
                pPlayer.openMenu((MenuProvider)$$3);
            }
        }
    }

    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(LIT)) {
            double x = (double)pPos.getX() + 0.5;
            double Y = pPos.getY();
            double Z = (double)pPos.getZ() + 0.5;
            pLevel.addParticle(ParticleTypes.FLAME, x, Y + 1.1, Z, 0.0, 0.0, 0.0);
        }
    }
}
