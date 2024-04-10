package com.reetam.borealis.entity;

import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;
import com.reetam.borealis.registry.BorealisSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.DirectionalPlaceContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

public class HailEntity extends FallingBlockEntity {
    public HailEntity(EntityType<? extends  HailEntity> entityType, Level level) {
        super(entityType, level);
        this.blockState = BorealisBlocks.HAILSTONE.get().defaultBlockState();
    }

    private HailEntity(Level pLevel, double pX, double pY, double pZ, BlockState pState) {
        this(BorealisEntities.HAIL.get(), pLevel);
        this.blockState = pState;
        this.blocksBuilding = true;
        this.setPos(pX, pY, pZ);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
        this.setStartPos(this.blockPosition());
    }

    public static HailEntity fall(Level pLevel, BlockPos pPos) {
        BlockState pBlockState = BorealisBlocks.HAILSTONE.get().defaultBlockState();
        HailEntity hail = new HailEntity(pLevel, (double)pPos.getX() + 0.5, (double)pPos.getY(), (double)pPos.getZ() + 0.5, pBlockState.hasProperty(BlockStateProperties.WATERLOGGED) ? (BlockState)pBlockState.setValue(BlockStateProperties.WATERLOGGED, false) : pBlockState);
        pLevel.setBlock(pPos, pBlockState.getFluidState().createLegacyBlock(), 3);
        pLevel.addFreshEntity(hail);
        return hail;
    }
    @Override
    public void tick() {
        ++this.time;
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.04, 0.0));
        }
        this.move(MoverType.SELF, this.getDeltaMovement());
        if (!this.level().isClientSide) {
            BlockPos blockpos = this.blockPosition();
            Block block = BorealisBlocks.HAILSTONE.get();

            if (!this.onGround()) {
                if (!this.level().isClientSide && (this.time > 100 && (blockpos.getY() <= this.level().getMinBuildHeight() || blockpos.getY() > this.level().getMaxBuildHeight()) || this.time > 600)) {
                    if (this.dropItem && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                        this.spawnAtLocation(block);
                    }
                    this.discard();
                }
            } else {
                BlockState blockstate = this.level().getBlockState(blockpos);
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.7, -0.5, 0.7));
                boolean flag2 = blockstate.canBeReplaced(new DirectionalPlaceContext(this.level(), blockpos, Direction.DOWN, ItemStack.EMPTY, Direction.UP));
                boolean flag3 = FallingBlock.isFree(this.level().getBlockState(blockpos.below()));
                boolean flag4 = block.defaultBlockState().canSurvive(this.level(), blockpos) && !flag3;
                if (flag2 && flag4) {

                    if (!this.level().setBlock(blockpos, block.defaultBlockState(), 3)) {
                        if (this.dropItem && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            this.discard();
                            this.callOnBrokenAfterFall(block, blockpos);
                            this.spawnAtLocation(block);
                        }
                    } else {
                        ((ServerLevel)this.level()).getChunkSource().chunkMap.broadcast(this, new ClientboundBlockUpdatePacket(blockpos, this.level().getBlockState(blockpos)));
                        this.level().playSound(this, blockpos, BorealisSounds.HAILSTONE_FALL.get(), SoundSource.WEATHER, 1000F, 1.0F);
                        this.level().explode(this, this.getX(), this.getY() - 1, this.getZ(), 3F, Level.ExplosionInteraction.NONE);
                        this.discard();
                    }
                } else {
                    this.discard();
                    if (this.dropItem && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                        this.callOnBrokenAfterFall(block, blockpos);
                        this.spawnAtLocation(block);
                    }
                }
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().scale(0.98));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.put("BlockState", NbtUtils.writeBlockState(BorealisBlocks.HAILSTONE.get().defaultBlockState()));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
    }

    @Override
    public BlockState getBlockState() {
        return BorealisBlocks.HAILSTONE.get().defaultBlockState();
    }
}
