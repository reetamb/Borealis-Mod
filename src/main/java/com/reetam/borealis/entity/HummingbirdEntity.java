package com.reetam.borealis.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;

public class HummingbirdEntity extends AnimalEntity implements IFlyingAnimal {

    public HummingbirdEntity(EntityType<? extends HummingbirdEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveControl = new HummingbirdEntity.MoveHelperController(this);
        this.setNoGravity(true);

    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.5D));
//        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
//        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(Items.HONEY_BOTTLE.getItem()), false));
//        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new HummingbirdEntity.RandomFlyGoal(this));
        this.goalSelector.addGoal(2, new HummingbirdEntity.LookAroundGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.FLYING_SPEED, 0.6F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.FOLLOW_RANGE, 48.0D);

    }

    public static boolean canHummingbirdSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getBlockState(pos.below()).getBlock() == Blocks.AIR && (pos.getY() >= 100 && pos.getY() < 120);
    }

    protected PathNavigator createNavigation(World worldIn) {
        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn);
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanFloat(true);
        flyingpathnavigator.setCanPassDoors(true);
        return flyingpathnavigator;
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
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
            MovementController movementcontroller = this.parentEntity.getMoveControl();
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
            Random random = this.parentEntity.getRandom();
            double dx = this.parentEntity.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 2.0F);
            double dy = this.parentEntity.getY() + (double)((random.nextFloat() * 2.0F - 1.0F));
            double dz = this.parentEntity.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 2.0F);
            this.parentEntity.getMoveControl().setWantedPosition(dx, dy, dz, 3.0D);
        }
    }

    static class MoveHelperController extends MovementController {
        private final HummingbirdEntity parentEntity;
        private int courseChangeCooldown;

        public MoveHelperController(HummingbirdEntity hummingbird) {
            super(hummingbird);
            this.parentEntity = hummingbird;
        }

        public void tick() {
            if (this.operation == MovementController.Action.MOVE_TO) {
                if (this.courseChangeCooldown-- <= 0) {
                    this.courseChangeCooldown += 3 * (this.parentEntity.getRandom().nextInt(5) + 2);
                    Vector3d movePath = new Vector3d(this.wantedX - this.parentEntity.getX(), this.wantedY - this.parentEntity.getY(), this.wantedZ - this.parentEntity.getZ());
                    double pathLength = movePath.length();
                    movePath = movePath.normalize();
                    if (this.canReach(movePath, MathHelper.ceil(pathLength))) {
                        this.parentEntity.setDeltaMovement(this.parentEntity.getDeltaMovement().add(movePath.scale(0.22D)));
                    } else {
                        this.operation = MovementController.Action.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vector3d p_220673_1_, int p_220673_2_) {
            AxisAlignedBB axisalignedbb = this.parentEntity.getBoundingBox();

            for(int i = 1; i < p_220673_2_; ++i) {
                axisalignedbb = axisalignedbb.move(p_220673_1_);
                if (!this.parentEntity.level.noCollision(this.parentEntity, axisalignedbb)) {
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
                Vector3d vector3d = this.parentEntity.getDeltaMovement();
                this.parentEntity.yRot = -((float)MathHelper.atan2(vector3d.x, vector3d.z)) * (180F / (float)Math.PI);
                this.parentEntity.yBodyRot = this.parentEntity.yRot;
            } else {
                LivingEntity livingentity = this.parentEntity.getTarget();
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.parentEntity) < 4096.0D) {
                    double d1 = livingentity.getX() - this.parentEntity.getX();
                    double d2 = livingentity.getZ() - this.parentEntity.getZ();
                    this.parentEntity.yRot = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                    this.parentEntity.yBodyRot = this.parentEntity.yRot;
                }
            }

        }
    }
}
