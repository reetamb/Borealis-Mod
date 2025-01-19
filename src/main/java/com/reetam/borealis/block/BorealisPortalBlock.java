package com.reetam.borealis.block;

import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisSounds;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BorealisPortalBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);

    public BorealisPortalBlock() {
        super(Properties.ofFullCopy(Blocks.NETHER_PORTAL)
                .strength(-1F)
                .noCollission()
                .lightLevel((state) -> 10)
                .noLootTable()
        );
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        double d0 = (double)pos.getX() + rand.nextDouble();
        double d1 = (double)pos.getY() + rand.nextDouble();
        double d2 = (double)pos.getZ() + rand.nextDouble();
        double d3 = (rand.nextFloat()-0.5)/(rand.nextInt(8)+3);
        double d4 = 0.1;
        double d5 = (rand.nextFloat()-0.5)/(rand.nextInt(8)+3);
        int j = rand.nextInt(2) * 2 - 1;
        if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
            d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
            d3 = (rand.nextFloat() / 6) * j;
        } else {
            d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
            d5 = (rand.nextFloat() / 6) * j;
        }

        level.addParticle(ParticleTypes.CLOUD, d0, d1, d2, d3, d4, d5);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public void makePortal(Level level, BlockPos pos) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                int range = Math.abs(x) + Math.abs(z);
                if (range == 2) {
                    level.setBlock(pos.west(x).north(z), Blocks.PACKED_ICE.defaultBlockState(), 18);
                } else if (range < 2) {
                    level.setBlock(pos.west(x).north(z), BorealisBlocks.BOREALIS_PORTAL.get().defaultBlockState(), 18);
                    level.setBlock(pos.below().west(x).north(z), Blocks.PACKED_ICE.defaultBlockState(), 18);
                }
            }
        }
        if (level.getBlockState(pos.below(2)).isAir()) level.setBlock(pos.below(2), Blocks.PACKED_ICE.defaultBlockState(), 18);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {

    }

    @Override
    public ItemStack getCloneItemStack(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return ItemStack.EMPTY;
    }
}