package com.reetam.borealis.entity.butterbird;

import com.reetam.borealis.block.plant.AlmsCrackedBlock;
import com.reetam.borealis.block.property.AlmsContents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class FindAlmsGoal extends MoveToBlockGoal {
    private final ButterbirdEntity bird;
    private boolean hasTarget;
    private boolean wantsToFind;
    private int refractoryTicks = 0;

    public FindAlmsGoal(ButterbirdEntity bird) {
        super(bird, 1.5, 8, 12);
        this.bird = bird;
    }

    @Override
    public boolean canUse() {
        if (this.nextStartTick <= 0) {
            hasTarget = false;
            wantsToFind = false;
            // canFind = has a located block
            // wantsToFind = has exactly one other partner within 12 blocks of the target
        }
        return super.canUse() && refractoryTicks == 0;
    }

    @Override
    public boolean canContinueToUse() {
        return this.hasTarget && super.canContinueToUse() && !this.bird.isPanicking() && refractoryTicks == 0;
    }

    @Override
    public void tick() {
        super.tick();
        this.bird.getLookControl()
                .setLookAt(
                        this.blockPos.getX() + 0.5, this.blockPos.getY() + 0.5, this.blockPos.getZ() + 0.5,
                        10F, this.bird.getMaxHeadXRot());

        if (this.isReachedTarget()) {

        }
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.getBlock() instanceof AlmsCrackedBlock) {
            if (blockstate.getValue(AlmsCrackedBlock.CONTENTS) == AlmsContents.NUT) {
                List<Entity> nearbyBirds = this.bird.level().getEntities(this.bird, new AABB(pos.getX()-3, pos.getY()-1, pos.getZ()-3, pos.getX()+3, pos.getY()+1, pos.getZ()+3));
                if (nearbyBirds.size() == 2) {
                    List<Entity> other = new ArrayList<>(List.copyOf(nearbyBirds));
                    other.remove(this.bird);
                }
            }
        }

        return false;
    }
}
