package com.reetam.borealis.world;

import com.google.common.collect.Maps;
import com.reetam.borealis.block.BorealisPortalBlock;
import com.reetam.borealis.registry.BorealisBlocks;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ColumnPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

// Credit https://github.com/TeamTwilight/twilightforest/blob/1.16.x/src/main/java/twilightforest/world/TFTeleporter.java
// License GNU LGPL

public class BorealisTeleporter implements ITeleporter {

    private static final Map<ResourceLocation, Map<ColumnPos, PortalPosition>> destinationCoordinateCache = new HashMap<>();
    private static final Object2LongMap<ColumnPos> columnMap = new Object2LongOpenHashMap<>();

    @Nullable
    @Override
    public PortalInfo getPortalInfo(Entity entity, ServerLevel dest, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
        PortalInfo pos;
        if ((pos = placeInExistingPortal(dest, entity, entity.blockPosition(), entity instanceof Player)) == null) {
            pos = moveToSafeCoords(dest, entity);
            makePortal(entity, dest, pos.pos);
            pos = placeInExistingPortal(dest, entity, new BlockPos(pos.pos), entity instanceof Player);
        }
        return pos;
    }

    @Nullable
    private static PortalInfo placeInExistingPortal(ServerLevel level, Entity entity, BlockPos pos, boolean isPlayer) {
        int i = 200;
        boolean flag = true;
        BlockPos blockpos = BlockPos.ZERO;
        ColumnPos columnPos = new ColumnPos(pos);

        if (!isPlayer && columnMap.containsKey(columnPos)) {
            return null;
        } else {
            PortalPosition portalPosition = destinationCoordinateCache.containsKey(level.dimension().location()) ? destinationCoordinateCache.get(level.dimension().location()).get(columnPos) : null;
            if (portalPosition != null) {
                blockpos = portalPosition.pos;
                portalPosition.lastUpdateTime = level.getGameTime();
                flag = false;
            } else {
                double d0 = Double.MAX_VALUE;

                for (int i1 = -i; i1 <= i; ++i1) {
                    BlockPos blockpos2;

                    for (int j1 = -i; j1 <= i; ++j1) {

                        if (!level.getWorldBorder().isWithinBounds(pos.offset(i1, 0, j1))) {
                            continue;
                        }

                        ChunkPos chunkPos = new ChunkPos(pos.offset(i1, 0, j1));
                        if (!level.getChunkSource().chunkMap.isExistingChunkFull(chunkPos)) {
                            continue;
                        }

                        LevelChunk chunk = level.getChunk(chunkPos.x, chunkPos.z);

                        for (BlockPos blockpos1 = pos.offset(i1, getScanHeight(level, pos) - pos.getY(), j1); blockpos1.getY() >= 0; blockpos1 = blockpos2) {
                            blockpos2 = blockpos1.below();

                            if (d0 >= 0.0D && blockpos1.distSqr(pos) >= d0) {
                                continue;
                            }

                            if (isPortal(chunk.getBlockState(blockpos1))) {
                                for (blockpos2 = blockpos1.below(); isPortal(chunk.getBlockState(blockpos2)); blockpos2 = blockpos2.below()) {
                                    blockpos1 = blockpos2;
                                }

                                double d1 = blockpos1.distSqr(pos);
                                if (d0 < 0.0D || d1 < d0) {
                                    d0 = d1;
                                    blockpos = blockpos1;
                                    i = (int) Math.ceil(Math.sqrt(d1));
                                }
                            }
                        }
                    }
                }
            }
        }

        if (blockpos.equals(BlockPos.ZERO)) {
            long factor = level.getGameTime() + 300L;
            columnMap.put(columnPos, factor);
            return null;
        } else {
            if (flag) {
                destinationCoordinateCache.putIfAbsent(level.dimension().location(), Maps.newHashMapWithExpectedSize(4096));
                destinationCoordinateCache.get(level.dimension().location()).put(columnPos, new PortalPosition(blockpos, level.getGameTime()));
                level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, new BlockPos(columnPos.x, blockpos.getY(), columnPos.z));
            }

            BlockPos[] portalBorder = getBoundaryPositions(level, blockpos).toArray(new BlockPos[0]);
            BlockPos borderPos = portalBorder[0];

            double portalX = borderPos.getX() + 0.5;
            double portalY = borderPos.getY() + 1.0;
            double portalZ = borderPos.getZ() + 0.5;

            return makePortalInfo(entity, portalX, portalY, portalZ);
        }
    }

    private static int getScanHeight(ServerLevel level, BlockPos pos) {
        return getScanHeight(level, pos.getX(), pos.getZ());
    }

    private static int getScanHeight(ServerLevel level, int x, int z) {
        int worldHeight = level.getMaxBuildHeight() - 1;
        int chunkHeight = level.getChunk(x >> 4, z >> 4).getHighestSectionPosition() + 15;
        return Math.min(worldHeight, chunkHeight);
    }

    private static boolean isPortal(BlockState state) {
        return state.getBlock() == BorealisBlocks.BOREALIS_PORTAL.get();
    }

    private static Set<BlockPos> getBoundaryPositions(ServerLevel level, BlockPos start) {
        Set<BlockPos> result = new HashSet<>(), checked = new HashSet<>();
        checked.add(start);
        checkAdjacent(level, start, checked, result);
        return result;
    }

    private static void checkAdjacent(ServerLevel level, BlockPos pos, Set<BlockPos> checked, Set<BlockPos> result) {
        for (Direction facing : Direction.Plane.HORIZONTAL) {
            BlockPos offset = pos.relative(facing);
            if (!checked.add(offset))
                continue;
            if (isPortalAt(level, offset)) {
                checkAdjacent(level, offset, checked, result);
            } else {
                result.add(offset);
            }
        }
    }

    private static boolean isPortalAt(ServerLevel level, BlockPos pos) {
        return isPortal(level.getBlockState(pos));
    }

    private static PortalInfo moveToSafeCoords(ServerLevel level, Entity entity) {

        BlockPos pos = entity.blockPosition();
        if (isSafeAround(level, pos, entity)) {
            return makePortalInfo(entity, entity.position());
        }

        BlockPos safeCoords = findSafeCoords(level, 200, pos, entity);
        if (safeCoords != null) {
            return makePortalInfo(entity, safeCoords.getX(), entity.getY(), safeCoords.getZ());
        }

        safeCoords = findSafeCoords(level, 400, pos, entity);

        if (safeCoords != null) {
            return makePortalInfo(entity, safeCoords.getX(), entity.getY(), safeCoords.getZ());
        }

        return makePortalInfo(entity, entity.position());
    }

    public static boolean isSafeAround(Level level, BlockPos pos, Entity entity) {

        if (!isSafe(level, pos, entity)) {
            return false;
        }

        for (Direction facing : Direction.Plane.HORIZONTAL) {
            if (!isSafe(level, pos.relative(facing, 16), entity)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isSafe(Level level, BlockPos pos, Entity entity) {
        return checkPos(level, pos);
    }

    private static boolean checkPos(Level level, BlockPos pos) {
        return level.getWorldBorder().isWithinBounds(pos);
    }

    @Nullable
    private static BlockPos findSafeCoords(ServerLevel level, int range, BlockPos pos, Entity entity) {
        int attempts = range / 8;
        for (int i = 0; i < attempts; i++) {
            BlockPos dPos = new BlockPos(
                    pos.getX(), 100, pos.getZ());

            if (isSafeAround(level, dPos, entity)) {
                return dPos;
            }
        }
        return null;
    }

    private static void makePortal(Entity entity, ServerLevel level, Vec3 pos) {
        loadSurroundingArea(level, pos);

        BlockPos spot = findPortalCoords(level, pos, blockPos -> isPortalAt(level, blockPos));
        String name = entity.getName().toString();

        if (spot != null) {
            cachePortalCoords(level, pos, spot);
            return;
        }

        spot = findPortalCoords(level, pos, blockpos -> isIdealForPortal(level, blockpos));

        if (spot != null) {
            cachePortalCoords(level, pos, makePortalAt(level, spot));
            return;
        }

        spot = findPortalCoords(level, pos, blockPos -> isOkayForPortal(level, blockPos));

        if (spot != null) {
            cachePortalCoords(level, pos, makePortalAt(level, spot));
            return;
        }

        double yFactor = getYFactor(level);
        cachePortalCoords(level, pos, makePortalAt(level, new BlockPos(entity.getX(), (entity.getY() * yFactor) - 1.0, entity.getZ())));
    }

    private static void loadSurroundingArea(ServerLevel level, Vec3 pos) {

        int x = (int) Math.floor(pos.x) >> 4;
        int z = (int) Math.floor(pos.y) >> 4;

        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                level.getChunk(x + dx, z + dz);
            }
        }
    }

    @Nullable
    private static BlockPos findPortalCoords(ServerLevel level, Vec3 loc, Predicate<BlockPos> predicate) {
        double yFactor = getYFactor(level);
        int entityX = (int) Math.floor(loc.x);
        int entityZ = (int) Math.floor(loc.z);

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        double spotWeight = -1D;
        BlockPos spot = null;

        int range = 16;
        for (int rx = entityX - range; rx <= entityX + range; rx++) {
            double xWeight = (rx + 0.5D) - loc.x;
            for (int rz = entityZ - range; rz <= entityZ + range; rz++) {
                double zWeight = (rz + 0.5D) - loc.z;

                for (int ry = getScanHeight(level, rx, rz); ry >= 0; ry--) {

                    if (!level.isEmptyBlock(pos.set(rx, ry, rz))) {
                        continue;
                    }

                    while (ry > 0 && level.isEmptyBlock(pos.set(rx, ry - 1, rz))) {
                        ry--;
                    }

                    double yWeight = (ry + 0.5D) - loc.y * yFactor;
                    double rPosWeight = xWeight * xWeight + yWeight * yWeight + zWeight * zWeight;

                    if (spotWeight < 0.0D || rPosWeight < spotWeight) {
                        if (predicate.test(pos)) {
                            spotWeight = rPosWeight;
                            spot = pos.immutable();
                        }
                    }
                }
            }
        }

        return spot;
    }

    private static double getYFactor(ServerLevel world) {
        return world.dimension().location().equals(Level.OVERWORLD.location()) ? 2.0 : 0.5;
    }

    private static void cachePortalCoords(ServerLevel level, Vec3 loc, BlockPos pos) {
        int x = (int) Math.floor(loc.x), z = (int) Math.floor(loc.z);
        destinationCoordinateCache.putIfAbsent(level.dimension().location(), Maps.newHashMapWithExpectedSize(4096));
        destinationCoordinateCache.get(level.dimension().location()).put(new ColumnPos(x, z), new PortalPosition(pos, level.getGameTime()));
    }

    private static boolean isIdealForPortal(ServerLevel level, BlockPos pos) {
        for (int potentialZ = 0; potentialZ < 4; potentialZ++) {
            for (int potentialX = 0; potentialX < 4; potentialX++) {
                for (int potentialY = 0; potentialY < 4; potentialY++) {
                    BlockPos tPos = pos.offset(potentialX - 1, potentialY, potentialZ - 1);
                    Material material = level.getBlockState(tPos).getMaterial();
                    if (potentialY == 0 && material != Material.GRASS || potentialY >= 1 && !material.isReplaceable()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static BlockPos makePortalAt(Level level, BlockPos pos) {
        if (level.getBlockState(pos).isAir()) {
            pos = new BlockPos(pos.getX(), 100, pos.getZ());
        } else {
            pos = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ()), pos.getZ());
        }
        ((BorealisPortalBlock) BorealisBlocks.BOREALIS_PORTAL.get()).makePortal(level, pos);
        return pos;
    }

    private static boolean isOkayForPortal(ServerLevel level, BlockPos pos) {
        for (int potentialZ = 0; potentialZ < 4; potentialZ++) {
            for (int potentialX = 0; potentialX < 4; potentialX++) {
                for (int potentialY = 0; potentialY < 4; potentialY++) {
                    BlockPos tPos = pos.offset(potentialX - 1, potentialY, potentialZ - 1);
                    Material material = level.getBlockState(tPos).getMaterial();
                    if (potentialY == 0 && !material.isSolid() && !material.isLiquid() || potentialY >= 1 && !material.isReplaceable()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static PortalInfo makePortalInfo(Entity entity, double x, double y, double z) {
        return makePortalInfo(entity, new Vec3(x, y, z));
    }

    private static PortalInfo makePortalInfo(Entity entity, Vec3 pos) {
        return new PortalInfo(pos, Vec3.ZERO, entity.yRot, entity.xRot);
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentLevel, ServerLevel destLevel, float yaw, Function<Boolean, Entity> repositionEntity) {
        entity.fallDistance = 0;
        return repositionEntity.apply(false);
    }

    static class PortalPosition {
        public final BlockPos pos;
        long lastUpdateTime;

        PortalPosition(BlockPos pos, long time) {
            this.pos = pos;
            this.lastUpdateTime = time;
        }
    }
}