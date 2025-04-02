package com.reetam.borealis.entity;

import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class HummingbirdEntity extends Animal implements FlyingAnimal {

    public HummingbirdEntity(EntityType<? extends HummingbirdEntity> type, Level level) {
        super(type, level);
        this.moveControl = new HummingbirdEntity.MoveHelperController(this);
        this.setNoGravity(true);
    }
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @org.jetbrains.annotations.Nullable SpawnGroupData pSpawnGroupData) {
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.5D));
        this.goalSelector.addGoal(5, new HummingbirdEntity.RandomFlyGoal(this));
        this.goalSelector.addGoal(2, new HummingbirdEntity.LookAroundGoal(this));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.FLYING_SPEED, 0.6F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }


    public static boolean spawningRules(EntityType<? extends Entity> entity, ServerLevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return true;
    }

    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        return null;
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return false;
    }

    static class RandomFlyGoal extends Goal {
        private final HummingbirdEntity parentEntity;

        public RandomFlyGoal(HummingbirdEntity hummingbird) {
            this.parentEntity = hummingbird;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            MoveControl movementcontroller = this.parentEntity.getMoveControl();
            if (!movementcontroller.hasWanted()) {
                return true;
            } else {
                double dx = movementcontroller.getWantedX() - this.parentEntity.getX();
                double dy = movementcontroller.getWantedY() - this.parentEntity.getY();
                double dz = movementcontroller.getWantedZ() - this.parentEntity.getZ();
                double dist = dx * dx + dy * dy + dz * dz;
                return dist < 1.0D || dist > 100.0D;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            RandomSource random = this.parentEntity.getRandom();
            double dx = this.parentEntity.getX() + (double)((random.nextFloat() * 4.0F - 1.0F) * 2.0F);
            double dy = this.parentEntity.getY() + (double)((random.nextFloat() * 4.0F - 1.0F));
            double dz = this.parentEntity.getZ() + (double)((random.nextFloat() * 4.0F - 1.0F) * 2.0F);
            this.parentEntity.getMoveControl().setWantedPosition(dx, dy, dz, 6.0D);
        }
    }

    static class MoveHelperController extends MoveControl {
        private final HummingbirdEntity parentEntity;
        private int courseChangeCooldown;

        public MoveHelperController(HummingbirdEntity hummingbird) {
            super(hummingbird);
            this.parentEntity = hummingbird;
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                if (this.courseChangeCooldown-- <= 0) {
                    this.courseChangeCooldown += 3 * (this.parentEntity.getRandom().nextInt(5) + 2);
                    Vec3 movePath = new Vec3(this.wantedX - this.parentEntity.getX(), this.wantedY - this.parentEntity.getY(), this.wantedZ - this.parentEntity.getZ());
                    double pathLength = movePath.length();
                    movePath = movePath.normalize();
                    if (this.canReach(movePath, (int) Math.ceil(pathLength))) {
                        this.parentEntity.setDeltaMovement(this.parentEntity.getDeltaMovement().add(movePath.scale(0.22D)));
                    } else {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }
            }
        }

        private boolean canReach(Vec3 motion, int tries) {
            AABB axisalignedbb = this.parentEntity.getBoundingBox();

            for(int i = 1; i < tries; i++) {
                axisalignedbb = axisalignedbb.move(motion);
                if (!this.parentEntity.level().noCollision(this.parentEntity, axisalignedbb)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class LookAroundGoal extends Goal {
        private final HummingbirdEntity parentEntity;

        public LookAroundGoal(HummingbirdEntity hummingbird) {
            this.parentEntity = hummingbird;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.parentEntity.getTarget() == null) {
                Vec3 vector3d = this.parentEntity.getDeltaMovement();
                this.parentEntity.setYRot(-((float)Math.atan2(vector3d.x, vector3d.z)) * (180F / (float)Math.PI));
                this.parentEntity.yBodyRot = this.parentEntity.getYRot();
            } else {
                LivingEntity livingentity = this.parentEntity.getTarget();
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.parentEntity) < 4096.0D) {
                    double d1 = livingentity.getX() - this.parentEntity.getX();
                    double d2 = livingentity.getZ() - this.parentEntity.getZ();
                    this.parentEntity.setYRot(-((float)Math.atan2(d1, d2)) * (180F / (float)Math.PI));
                    this.parentEntity.yBodyRot = this.parentEntity.getYRot();
                }
            }

        }
    }
}
