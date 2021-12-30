package com.reetam.borealis.entity;

import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class BorealisBoatEntity extends Boat {

    private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(BorealisBoatEntity.class, EntityDataSerializers.INT);

    public BorealisBoatEntity(EntityType<? extends Boat> type, Level world) {
        super(type, world);
        this.blocksBuilding = true;
    }

    public BorealisBoatEntity(Level worldIn, double x, double y, double z) {
        this(BorealisEntities.BOAT.get(), worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public BorealisBoatEntity.Type getBorealisBoatType() {
        return BorealisBoatEntity.Type.byId(this.entityData.get(BOAT_TYPE));
    }

    @Override
    public Item getDropItem() {
        switch(this.getBorealisBoatType()) {
            case BRUMAL:
            default:
                return BorealisItems.BRUMAL_BOAT.get();
            case FROSTFIR:
                return BorealisItems.FROSTFIR_BOAT.get();
            case SACCHARINE:
                return BorealisItems.SACCHARINE_BOAT.get();
        }
    }

    public void setBoatType(BorealisBoatEntity.Type boatType) {
        this.entityData.set(BOAT_TYPE, boatType.ordinal());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BOAT_TYPE, Type.BRUMAL.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putString("Type", this.getBorealisBoatType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("Type", 8)) {
            this.setBoatType(BorealisBoatEntity.Type.getTypeFromString(compound.getString("Type")));
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public enum Type {
        BRUMAL(BorealisBlocks.BRUMAL_PLANKS.get(), "brumal"),
        FROSTFIR(BorealisBlocks.FROSTFIR_PLANKS.get(), "frostfir"),
        SACCHARINE(BorealisBlocks.SACCHARINE_PLANKS.get(), "saccharine")
        ;

        private final String name;
        private final Block block;

        Type(Block block, String name) {
            this.name = name;
            this.block = block;
        }

        public String getName() {
            return this.name;
        }

        public Block asPlank() {
            return this.block;
        }

        public String toString() {
            return this.name;
        }

        public static BorealisBoatEntity.Type byId(int id) {
            BorealisBoatEntity.Type[] aBorealisBoatEntity$type = values();
            if (id < 0 || id >= aBorealisBoatEntity$type.length) {
                id = 0;
            }

            return aBorealisBoatEntity$type[id];
        }

        public static BorealisBoatEntity.Type getTypeFromString(String nameIn) {
            BorealisBoatEntity.Type[] boatTypeArray = values();

            for (Type type : boatTypeArray) {
                if (type.getName().equals(nameIn)) {
                    return type;
                }
            }

            return boatTypeArray[0];
        }
    }
}