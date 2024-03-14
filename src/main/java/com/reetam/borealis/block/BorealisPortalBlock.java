package com.reetam.borealis.block;

import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisWorld;
import com.reetam.borealis.registry.BorealisSounds;
import com.reetam.borealis.world.BorealisTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class BorealisPortalBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);

    public BorealisPortalBlock() {
        super(Properties.copy(Blocks.NETHER_PORTAL)
                .strength(-1F)
                .noCollission()
                .lightLevel((state) -> 10)
                .noLootTable()
        );
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {
        if (rand.nextInt(100) == 0) {
            level.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, BorealisSounds.BOREALIS_PORTAL_CHIME.get(), SoundSource.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

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

    public boolean makePortal(Level level, BlockPos pos) {
        level.setBlock(pos, BorealisBlocks.BOREALIS_PORTAL.get().defaultBlockState(), 18);

        level.setBlock(pos.west(), BorealisBlocks.BOREALIS_PORTAL.get().defaultBlockState(), 18);
        level.setBlock(pos.east(), BorealisBlocks.BOREALIS_PORTAL.get().defaultBlockState(), 18);
        level.setBlock(pos.south(), BorealisBlocks.BOREALIS_PORTAL.get().defaultBlockState(), 18);
        level.setBlock(pos.north(), BorealisBlocks.BOREALIS_PORTAL.get().defaultBlockState(), 18);

        if (level.getBlockState(pos.west().below()).isAir()) {
            level.setBlock(pos.west().below(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        }
        if (level.getBlockState(pos.east().below()).isAir()) {
            level.setBlock(pos.east().below(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        }
        if (level.getBlockState(pos.south().below()).isAir()) {
            level.setBlock(pos.south().below(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        }
        if (level.getBlockState(pos.north().below()).isAir()) {
            level.setBlock(pos.north().below(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        }
        if (level.getBlockState(pos.below()).isAir()) {
            level.setBlock(pos.below(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        }
        if (level.getBlockState(pos.below(2)).isAir()) {
            level.setBlock(pos.below(2), Blocks.PACKED_ICE.defaultBlockState(), 18);
        }

        level.setBlock(pos.west(2), Blocks.PACKED_ICE.defaultBlockState(), 18);
        level.setBlock(pos.north(2), Blocks.PACKED_ICE.defaultBlockState(), 18);
        level.setBlock(pos.east(2), Blocks.PACKED_ICE.defaultBlockState(), 18);
        level.setBlock(pos.south(2), Blocks.PACKED_ICE.defaultBlockState(), 18);

        level.setBlock(pos.west().north(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        level.setBlock(pos.north().east(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        level.setBlock(pos.east().south(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        level.setBlock(pos.south().west(), Blocks.PACKED_ICE.defaultBlockState(), 18);

        return true;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if(!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            if(entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            }
            else {
                if(!entity.level().isClientSide && !pos.equals(entity.portalEntrancePos)) {
                    entity.portalEntrancePos = pos.immutable();
                }
                Level entityLevel = entity.level();
                MinecraftServer minecraftserver = entityLevel.getServer();
                ResourceKey<Level> destination = entity.level().dimension() == BorealisWorld.BOREALIS_LEVEL ? Level.OVERWORLD : BorealisWorld.BOREALIS_LEVEL;
                if(minecraftserver != null) {
                    ServerLevel destinationLevel = minecraftserver.getLevel(destination);
                    if(destinationLevel != null && minecraftserver.isNetherEnabled() && !entity.isPassenger()) {
                        entity.level().getProfiler().push("borealis_portal");
                        entity.setPortalCooldown();
                        entity.changeDimension(destinationLevel, new BorealisTeleporter());
                        entity.level().getProfiler().pop();
                    }
                }
            }
        }
    }

    @Override
    public void neighborChanged(BlockState thisState, Level level, BlockPos thisPos, Block otherBlock, BlockPos otherPos, boolean p_220069_6_) {
        if (otherBlock == BorealisBlocks.BOREALIS_PORTAL.get()) {
            level.setBlock(thisPos, Blocks.AIR.defaultBlockState(), 1);
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }
}