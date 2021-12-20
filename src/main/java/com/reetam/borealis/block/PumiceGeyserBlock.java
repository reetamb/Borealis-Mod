package com.reetam.borealis.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class PumiceGeyserBlock extends Block {

    Random random = new Random();

    public PumiceGeyserBlock() {
        super(Properties.of(Material.STONE)
                .strength(0.5F)
        );
    }

    @Override
    public void animateTick(BlockState stateIn, Level level, BlockPos pos, Random rand) {
        if (level.getBlockState(pos.above()).isAir()) {
            for (int i = 0; i < 10; i++) {
                level.addParticle(ParticleTypes.CLOUD, pos.getX()+0.5, pos.above().getY()+rand.nextFloat()-0.5, pos.getZ()+0.5, 0, rand.nextFloat()/3+0.15, 0);
            }
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity) {
            entity.setDeltaMovement(new Vec3(entity.getDeltaMovement().x(), entity.getDeltaMovement().y() + random.nextInt(5), entity.getDeltaMovement().z()));
        }

        super.stepOn(level, pos, state, entity);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        if (level.getBlockState(pos.above()).getBlock() == Blocks.SNOW) {
            level.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), 19);
        }
    }
}