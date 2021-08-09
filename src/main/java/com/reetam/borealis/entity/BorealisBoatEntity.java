package com.reetam.borealis.entity;


import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BorealisBoatEntity extends BoatEntity {

    private static final DataParameter<Integer> BOAT_TYPE = EntityDataManager.defineId(BorealisBoatEntity.class, DataSerializers.INT);

    public BorealisBoatEntity(EntityType<? extends BoatEntity> type, World world) {
        super(type, world);
        this.blocksBuilding = true;
    }

    public BorealisBoatEntity(World worldIn, double x, double y, double z) {
        this(BorealisEntities.BOAT.get(), worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vector3d.ZERO);
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
    protected void addAdditionalSaveData(CompoundNBT compound) {
        compound.putString("Type", this.getBorealisBoatType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT compound) {
        if (compound.contains("Type", 8)) {
            this.setBoatType(BorealisBoatEntity.Type.getTypeFromString(compound.getString("Type")));
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public enum Type {
        BRUMAL(BorealisBlocks.brumal_planks.get(), "brumal"),
        FROSTFIR(BorealisBlocks.frostfir_planks.get(), "frostfir"),
        SACCHARINE(BorealisBlocks.saccharine_planks.get(), "saccharine")
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

        /**
         * Get a boat type by it's enum ordinal
         */
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
