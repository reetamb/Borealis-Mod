package com.reetam.borealis.block;

import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;


public class PumiceGeyserBlock extends Block {

    Random random = new Random();

    public PumiceGeyserBlock() {
        super(Properties.create(Material.ROCK)
                .hardnessAndResistance(0.5F)
        );
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        for (int i = 0; i < 10; i++) {
            worldIn.addParticle(ParticleTypes.CLOUD, pos.getX()+0.5, pos.up().getY()+rand.nextFloat()-0.5, pos.getZ()+0.5, 0, rand.nextFloat()/3+0.15, 0);
        }
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity) {
            entityIn.setMotion(new Vector3d(entityIn.getMotion().getX(), entityIn.getMotion().getY() + random.nextInt(5), entityIn.getMotion().getZ()));
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.SNOW) {
            worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 19);
        }
    }
}