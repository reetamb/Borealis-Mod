package com.reetam.borealis.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class HummingbirdEntity extends AnimalEntity implements IFlyingAnimal {

    //public float wingstate;

    public HummingbirdEntity(EntityType<? extends HummingbirdEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveController = new FlyingMovementController(this, 20, true);
        this.setNoGravity(true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.5D));
//        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
//        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(Items.HONEY_BOTTLE.getItem()), false));
//        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new HummingbirdEntity.WanderGoal());
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AnimalEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D)
                .createMutableAttribute(Attributes.FLYING_SPEED, 0.6F)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3F)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D);

    }

    protected PathNavigator createNavigator(World worldIn) {
        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn);
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanSwim(true);
        flyingpathnavigator.setCanEnterDoors(true);
        return flyingpathnavigator;
    }

    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return Ingredient.fromItems(Items.HONEY_BOTTLE.getItem()).test(stack);
    }

    @Override
    public void livingTick() {
        super.livingTick();
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    /**COPIED FROM BEE CLASS**/
    class WanderGoal extends Goal {
        WanderGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean shouldExecute() {
            return HummingbirdEntity.this.navigator.noPath() && HummingbirdEntity.this.rand.nextInt(10) == 0;
        }

        public boolean shouldContinueExecuting() {
            return HummingbirdEntity.this.navigator.hasPath();
        }

        public void startExecuting() {
            Vector3d vector3d = this.getRandomLocation();
            if (vector3d != null) {
                HummingbirdEntity.this.navigator.setPath(HummingbirdEntity.this.navigator.getPathToPos(new BlockPos(vector3d), 1), 1.0D);
            }

        }

        @Nullable
        private Vector3d getRandomLocation() {
            Vector3d vector3d = HummingbirdEntity.this.getLook(0.0F);

            Vector3d vector3d2 = RandomPositionGenerator.findAirTarget(HummingbirdEntity.this, 8, 7, vector3d, ((float)Math.PI / 2F), 2, 1);
            return vector3d2 != null ? vector3d2 : RandomPositionGenerator.findGroundTarget(HummingbirdEntity.this, 8, 4, -2, vector3d, ((float)Math.PI / 2F));
        }
    }
}
