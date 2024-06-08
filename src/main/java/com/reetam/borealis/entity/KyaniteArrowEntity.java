package com.reetam.borealis.entity;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.KyaniteArrowBlock;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class KyaniteArrowEntity extends AbstractArrow {
    private BlockPos inBlockPos;
    private Direction embedFace;

    public KyaniteArrowEntity(EntityType<? extends KyaniteArrowEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    protected KyaniteArrowEntity(Level pLevel, LivingEntity pShooter) {
        super(BorealisEntities.KYANITE_ARROW.get(), pShooter.getX(), pShooter.getEyeY() - 0.10000000149011612, pShooter.getZ(), pLevel);
        this.setOwner(pShooter);
        if (pShooter instanceof Player) {
            this.pickup = AbstractArrow.Pickup.ALLOWED;
        }
    }
    public static KyaniteArrowEntity fromShooter(Level pLevel, LivingEntity pShooter) {
        return new KyaniteArrowEntity(pLevel, pShooter);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(BorealisItems.KYANITE_CRYSTAL.get(), 1);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (this.inBlockPos != null) {
            pCompound.put("inBlockPos", NbtUtils.writeBlockPos(this.inBlockPos));
        }
        pCompound.putString("embedFace", this.embedFace.toString());
    }
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("inBlockPos", 10)) {
            this.inBlockPos = NbtUtils.readBlockPos(pCompound.getCompound("inBlockState"));
        }
        if (pCompound.contains("embedFace")) {
            this.embedFace = Direction.valueOf(pCompound.getString("embedFace"));
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        this.inBlockPos = pResult.getBlockPos();
        this.embedFace = pResult.getDirection();
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide && !this.inGround) {
            this.level().addParticle(ParticleTypes.GLOW_SQUID_INK, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
        else if (this.inGround) {
            BorealisMod.LOGGER.error("Arrow landed in " + this.level().getBlockState(this.inBlockPos) + " at " + this.inBlockPos + " on direction " + this.embedFace);
            BlockPos placePos = this.inBlockPos.relative(this.embedFace);
            if (this.level().getBlockState(placePos).isAir() && this.level().getBlockState(this.inBlockPos).isFaceSturdy(this.level(), this.inBlockPos, this.embedFace)) {
                this.level().setBlock(placePos, BorealisBlocks.EMBEDDED_KYANITE_ARROW.get().defaultBlockState().setValue(KyaniteArrowBlock.FACING, this.embedFace), 3);
            } else {
                this.level().addFreshEntity(new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), getPickupItem()));
            }
            this.kill();
        }
    }
}
