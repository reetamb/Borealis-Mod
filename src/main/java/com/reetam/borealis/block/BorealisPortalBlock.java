package com.reetam.borealis.block;

import com.reetam.borealis.registry.BorealisSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.*;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisDimensions;
import com.reetam.borealis.world.BorealisTeleporter;

import java.util.Random;

public class BorealisPortalBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);

    public BorealisPortalBlock() {
        super(Properties.of(Material.PORTAL)
                .strength(-1F)
                .noCollission()
                .lightLevel((state) -> 10)
                .noDrops()
        );
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, BorealisSounds.BOREALIS_PORTAL_CHIME.get(), SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        double d0 = (double)pos.getX() + rand.nextDouble();
        double d1 = (double)pos.getY() + rand.nextDouble();
        double d2 = (double)pos.getZ() + rand.nextDouble();
        double d3 = (rand.nextFloat()-0.5)/(rand.nextInt(8)+3);
        double d4 = 0.1;
        double d5 = (rand.nextFloat()-0.5)/(rand.nextInt(8)+3);
        int j = rand.nextInt(2) * 2 - 1;
        if (!worldIn.getBlockState(pos.west()).is(this) && !worldIn.getBlockState(pos.east()).is(this)) {
            d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
            d3 = (rand.nextFloat() / 6) * j;
        } else {
            d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
            d5 = (rand.nextFloat() / 6) * j;
        }

        worldIn.addParticle(ParticleTypes.CLOUD, d0, d1, d2, d3, d4, d5);

    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public boolean makePortal(IWorld worldIn, BlockPos pos) {
        worldIn.setBlock(pos, BorealisBlocks.BOREALIS_PORTAL.get().defaultBlockState(), 18);

        worldIn.setBlock(pos.west(), BorealisBlocks.BOREALIS_PORTAL.get().defaultBlockState(), 18);
        worldIn.setBlock(pos.east(), BorealisBlocks.BOREALIS_PORTAL.get().defaultBlockState(), 18);
        worldIn.setBlock(pos.south(), BorealisBlocks.BOREALIS_PORTAL.get().defaultBlockState(), 18);
        worldIn.setBlock(pos.north(), BorealisBlocks.BOREALIS_PORTAL.get().defaultBlockState(), 18);

        if (worldIn.getBlockState(pos.west().below()).isAir()) {
            worldIn.setBlock(pos.west().below(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        }
        if (worldIn.getBlockState(pos.east().below()).isAir()) {
            worldIn.setBlock(pos.east().below(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        }
        if (worldIn.getBlockState(pos.south().below()).isAir()) {
            worldIn.setBlock(pos.south().below(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        }
        if (worldIn.getBlockState(pos.north().below()).isAir()) {
            worldIn.setBlock(pos.north().below(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        }
        if (worldIn.getBlockState(pos.below()).isAir()) {
            worldIn.setBlock(pos.below(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        }
        if (worldIn.getBlockState(pos.below(2)).isAir()) {
            worldIn.setBlock(pos.below(2), Blocks.PACKED_ICE.defaultBlockState(), 18);
        }

        worldIn.setBlock(pos.west(2), Blocks.PACKED_ICE.defaultBlockState(), 18);
        worldIn.setBlock(pos.north(2), Blocks.PACKED_ICE.defaultBlockState(), 18);
        worldIn.setBlock(pos.east(2), Blocks.PACKED_ICE.defaultBlockState(), 18);
        worldIn.setBlock(pos.south(2), Blocks.PACKED_ICE.defaultBlockState(), 18);

        worldIn.setBlock(pos.west().north(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        worldIn.setBlock(pos.north().east(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        worldIn.setBlock(pos.east().south(), Blocks.PACKED_ICE.defaultBlockState(), 18);
        worldIn.setBlock(pos.south().west(), Blocks.PACKED_ICE.defaultBlockState(), 18);

        return true;
    }

    @Override
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        if(!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            if(entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            }
            else {
                if(!entity.level.isClientSide && !pos.equals(entity.portalEntrancePos)) {
                    entity.portalEntrancePos = pos.immutable();
                }
                World entityWorld = entity.level;
                if(entityWorld != null) {
                    MinecraftServer minecraftserver = entityWorld.getServer();
                    RegistryKey<World> destination = entity.level.dimension() == BorealisDimensions.BOREALIS ? World.OVERWORLD : BorealisDimensions.BOREALIS;
                    if(minecraftserver != null) {
                        ServerWorld destinationWorld = minecraftserver.getLevel(destination);
                        if(destinationWorld != null && minecraftserver.isNetherEnabled() && !entity.isPassenger()) {
                            entity.level.getProfiler().push("borealis_portal");
                            entity.setPortalCooldown();
                            entity.changeDimension(destinationWorld, new BorealisTeleporter());
                            entity.level.getProfiler().pop();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void neighborChanged(BlockState thisState, World world, BlockPos thisPos, Block otherBlock, BlockPos otherPos, boolean p_220069_6_) {
        if (otherBlock.is(BorealisBlocks.BOREALIS_PORTAL.get())) {
            world.setBlock(thisPos, Blocks.AIR.defaultBlockState(), 1);
        }
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }
}