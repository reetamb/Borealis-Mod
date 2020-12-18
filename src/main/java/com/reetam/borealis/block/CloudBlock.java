package com.reetam.borealis.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class CloudBlock extends Block {

    public CloudBlock(AbstractBlock.Properties properties) {
        super(properties.doesNotBlockMovement());
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.setMotionMultiplier(state, new Vector3d(1.25, 0.01, 1.25));
    }

    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }
}
