/**
    Credit: Quek04 https://github.com/quek04/The-Undergarden/blob/1.16/src/main/java/quek/undergarden/world/UGTeleporter.java
    If his stuff wasn't open source, surely I would Die.
 **/
package com.reetam.borealis.world;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PortalInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ColumnPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;
import net.minecraftforge.common.util.ITeleporter;
import com.reetam.borealis.block.BorealisPortalBlock;
import com.reetam.borealis.registry.BorealisBlocks;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class BorealisTeleporter implements ITeleporter {

    protected final Map<ColumnPos, PortalPosition> destinationCoordinateCache = Maps.newHashMapWithExpectedSize(4096);
    private final Object2LongMap<ColumnPos> columnMap = new Object2LongOpenHashMap<>();

    public boolean placeInPortal(ServerWorld world, Entity entity, float yaw) {
        PortalInfo pattern = this.placeInExistingPortal(world, entity, new BlockPos(entity.getPosition()));
        return pattern != null;
    }

    @Nullable
    public PortalInfo placeInExistingPortal(ServerWorld world, Entity entity, BlockPos pos) {
        boolean isFrame = true;
        BlockPos blockpos = null;
        ColumnPos columnpos = new ColumnPos(pos);
        if (!(entity instanceof PlayerEntity) && this.columnMap.containsKey(columnpos)) {
            return null;
        } else {
            BorealisTeleporter.PortalPosition position = this.destinationCoordinateCache.get(columnpos);
            if (position != null) {
                blockpos = position.pos;
                position.lastUpdateTime = world.getGameTime();
                isFrame = false;
            } else {
                double d0 = Double.MAX_VALUE;

                for(int eX = -128; eX <= 128; ++eX) {
                    BlockPos blockpos2;
                    for(int eZ = -128; eZ <= 128; ++eZ) {
                        for(BlockPos blockpos1 = pos.add(eX, world.getHeight() - 1 - pos.getY(), eZ); blockpos1.getY() >= 0; blockpos1 = blockpos2) {
                            blockpos2 = blockpos1.down();
                            if (world.getBlockState(blockpos1).getBlock() == BorealisBlocks.borealis_portal.get()) {
                                for(blockpos2 = blockpos1.down(); world.getBlockState(blockpos2).getBlock() == BorealisBlocks.borealis_portal.get(); blockpos2 = blockpos2.down()) {
                                    blockpos1 = blockpos2;
                                }

                                double distance = blockpos1.distanceSq(pos);
                                if (d0 < 0.0D || distance < d0) {
                                    d0 = distance;
                                    blockpos = blockpos1;
                                }
                            }
                        }
                    }
                }
            }

            if (blockpos == null) {
                long factor = world.getGameTime();
                this.columnMap.put(columnpos, factor);
                return null;
            }
            else {
                if (isFrame) {
                    this.destinationCoordinateCache.put(columnpos, new BorealisTeleporter.PortalPosition(blockpos, world.getGameTime()));
                    world.getChunkProvider().registerTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, new BlockPos(columnpos.x, blockpos.getY(), columnpos.z));
                }

                return new PortalInfo(new Vector3d(blockpos.getX(), blockpos.getY(), blockpos.getZ()), entity.getMotion(), entity.rotationYaw, entity.rotationPitch);
            }
        }
    }

    /**
     * Create a portal at the teleport location.
     */
    public void makePortal(ServerWorld world, Entity entity) {
        Random random = new Random(world.getSeed());
        double d0 = -1.0D;
        int entityX = MathHelper.floor(entity.getPosX());
        int entityY = MathHelper.floor(entity.getPosY());
        int entityZ = MathHelper.floor(entity.getPosZ());
        int xPos = entityX;
        int yPos = entityY;
        int zPos = entityZ;
        int baseAxis = 0;
        int randAxis = random.nextInt(4);
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int startX = entityX - 16; startX <= entityX + 16; ++startX) {
            double ePosX = (double) startX + 0.5D - entity.getPosX();

            for (int startZ = entityZ - 16; startZ <= entityZ + 16; ++startZ) {
                double ePosZ = (double) startZ + 0.5D - entity.getPosZ();

                searchpos:
                for (int startY = world.getHeight() - 1; startY >= 0; --startY) {
                    if (world.isAirBlock(mutable.setPos(startX, startY, startZ))) {
                        while (startY > 0 && world.isAirBlock(mutable.setPos(startX, startY - 1, startZ))) {
                            --startY;
                        }

                        for (int k3 = randAxis; k3 < randAxis + 4; ++k3) {
                            int l3 = k3 % 2;
                            int i4 = 1 - l3;
                            if (k3 % 4 >= 2) {
                                l3 = -l3;
                                i4 = -i4;
                            }

                            for (int j4 = 0; j4 < 3; ++j4) {
                                for (int k4 = 0; k4 < 4; ++k4) {
                                    for (int portalHeight = -1; portalHeight < 4; ++portalHeight) {
                                        int sPosX = startX + (k4 - 1) * l3 + j4 * i4;
                                        int sPosY = startY + portalHeight;
                                        int sPosZ = startZ + (k4 - 1) * i4 - j4 * l3;
                                        mutable.setPos(sPosX, sPosY, sPosZ);
                                        if (portalHeight < 0 && !world.getBlockState(mutable).getMaterial().isSolid() || portalHeight >= 0 && !world.isAirBlock(mutable)) {
                                            continue searchpos;
                                        }
                                    }
                                }
                            }

                            double ePosY = (double) startY + 0.5D - entity.getPosY();
                            double eArea = ePosX * ePosX + ePosY * ePosY + ePosZ * ePosZ;
                            if (d0 < 0.0D || eArea < d0) {
                                d0 = eArea;
                                xPos = startX;
                                yPos = startY;
                                zPos = startZ;
                                baseAxis = k3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D) {
            for (int startX2 = entityX - 16; startX2 <= entityX + 16; ++startX2) {
                double ePosX2 = (double) startX2 + 0.5D - entity.getPosX();

                for (int startZ2 = entityZ - 16; startZ2 <= entityZ + 16; ++startZ2) {
                    double ePosZ2 = (double) startZ2 + 0.5D - entity.getPosZ();

                    label214:
                    for (int startY2 = world.getHeight() - 1; startY2 >= 0; --startY2) {
                        if (world.isAirBlock(mutable.setPos(startX2, startY2, startZ2))) {
                            while (startY2 > 0 && world.isAirBlock(mutable.setPos(startX2, startY2 - 1, startZ2))) {
                                --startY2;
                            }

                            for (int l7 = randAxis; l7 < randAxis + 2; ++l7) {
                                int l8 = l7 % 2;
                                int k9 = 1 - l8;

                                for (int i10 = 0; i10 < 4; ++i10) {
                                    for (int portalHeight2 = -1; portalHeight2 < 4; ++portalHeight2) {
                                        int sPosX2 = startX2 + (i10 - 1) * l8;
                                        int sPosY2 = startY2 + portalHeight2;
                                        int sPosZ2 = startZ2 + (i10 - 1) * k9;
                                        mutable.setPos(sPosX2, sPosY2, sPosZ2);
                                        if (portalHeight2 < 0 && !world.getBlockState(mutable).getMaterial().isSolid() || portalHeight2 >= 0 && !world.isAirBlock(mutable)) {
                                            continue label214;
                                        }
                                    }
                                }

                                double ePosY2 = (double) startY2 + 0.5D - entity.getPosY();
                                double eArea2 = ePosX2 * ePosX2 + ePosY2 * ePosY2 + ePosZ2 * ePosZ2;
                                if (d0 < 0.0D || eArea2 < d0) {
                                    d0 = eArea2;
                                    xPos = startX2;
                                    yPos = startY2;
                                    zPos = startZ2;
                                    baseAxis = l7 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int baseX = xPos;
        int baseY = yPos;
        int baseZ = zPos;
        int xAxis = baseAxis % 2;
        int zAxis = 1 - xAxis;
        if (baseAxis % 4 >= 2) {
            xAxis = -xAxis;
            zAxis = -zAxis;
        }

        if (d0 < 0.0D) {
            yPos = MathHelper.clamp(yPos, 70, world.getHeight() - 10);
            baseY = yPos;

            for (int j7 = -1; j7 <= 1; ++j7) {
                for (int i8 = 1; i8 < 3; ++i8) {
                    for (int i9 = -1; i9 < 3; ++i9) {
                        int frameX = baseX + (i8 - 1) * xAxis + j7 * zAxis;
                        int frameY = baseY + i9;
                        int frameZ = baseZ + (i8 - 1) * zAxis - j7 * xAxis;
                        boolean flag = i9 < 0;
                        mutable.setPos(frameX, frameY, frameZ);
                        world.setBlockState(mutable, flag ? Blocks.PACKED_ICE.getDefaultState() : Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        for (int fWidth = -1; fWidth < 3; ++fWidth) {
            for (int fHeight = -1; fHeight < 4; ++fHeight) {
                if (fWidth == -1 || fWidth == 2 || fHeight == -1 || fHeight == 3) {
                    mutable.setPos(baseX + fWidth * xAxis, baseY + fHeight, baseZ + fWidth * zAxis);
                    world.setBlockState(mutable, Blocks.PACKED_ICE.getDefaultState(), 3);
                }
            }
        }

        BlockState portal = BorealisBlocks.borealis_portal.get().getDefaultState().with(BorealisPortalBlock.AXIS, xAxis == 0 ? Direction.Axis.Z : Direction.Axis.X);

        for (int pWidth = 0; pWidth < 2; ++pWidth) {
            for (int pHeight = 0; pHeight < 3; ++pHeight) {
                mutable.setPos(baseX + pWidth * xAxis, baseY + pHeight, baseZ + pWidth * zAxis);
                world.setBlockState(mutable, portal, 18);
            }
        }

    }

    @Nullable
    @Override
    public PortalInfo getPortalInfo(Entity entity, ServerWorld destWorld, Function<ServerWorld, PortalInfo> defaultPortalInfo) {
        WorldBorder worldborder = destWorld.getWorldBorder();
        double d0 = Math.max(-2.9999872E7D, worldborder.minX() + 16.0D);
        double d1 = Math.max(-2.9999872E7D, worldborder.minZ() + 16.0D);
        double d2 = Math.min(2.9999872E7D, worldborder.maxX() - 16.0D);
        double d3 = Math.min(2.9999872E7D, worldborder.maxZ() - 16.0D);
        double d4 = DimensionType.getCoordinateDifference(entity.world.getDimensionType(), destWorld.getDimensionType());
        BlockPos blockpos1 = new BlockPos(MathHelper.clamp(entity.getPosX() * d4, d0, d2), entity.getPosY(), MathHelper.clamp(entity.getPosZ() * d4, d1, d3));
        if (!placeInPortal(destWorld, entity, entity.rotationYaw)) {
            makePortal(destWorld, entity);
            placeInPortal(destWorld, entity, entity.rotationYaw);
        }
        return this.placeInExistingPortal(destWorld, entity, blockpos1);
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        Entity newEntity = repositionEntity.apply(false);

        if (!placeInPortal(destWorld, newEntity, newEntity.rotationYaw)) {
            makePortal(destWorld, newEntity);
            placeInPortal(destWorld, newEntity, newEntity.rotationYaw);
        }

        return newEntity;
    }

    @Override
    public boolean isVanilla() {
        return false;
    }

    static class PortalPosition {
        public final BlockPos pos;
        public long lastUpdateTime;

        public PortalPosition(BlockPos pos, long time) {
            this.pos = pos;
            this.lastUpdateTime = time;
        }
    }
}