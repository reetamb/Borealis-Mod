package com.reetam.borealis.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StaticFieldBlock extends IronBarsBlock {
    public StaticFieldBlock(Properties p_54198_) {
        super(p_54198_);
    }

    //TODO: these should not be waterloggable; also needs a "static level" blockstate; also needs to check player for static level
    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pContext instanceof EntityCollisionContext entityContext) {
            Entity entity = entityContext.getEntity();
            if (entity instanceof Player player) {
                return Shapes.empty();
            }
        }
        return this.collisionShapeByIndex[this.getAABBIndex(pState)];
    }
}
