package com.reetam.borealis.world.teleporter;

import com.reetam.borealis.registry.world.BorealisDimensions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

public class WorldHeightTransition {

    public static DimensionTransition toBorealis(ServerLevel level, Entity entity) {
        ServerLevel destination = level.getServer().getLevel(BorealisDimensions.BOREALIS);
        Vec3 pos = entity.adjustSpawnLocation(destination, entity.blockPosition()).getCenter();
        return new DimensionTransition(
                destination,
                pos.y() >= 32 ? pos : pos.add(0, 120 - pos.y(), 0),
                entity.getDeltaMovement(),
                entity.getYRot(),
                entity.getXRot(),
                DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET)
        );
    }

    public static DimensionTransition toOverworld(ServerLevel level, Entity entity) {
        ServerLevel destination = level.getServer().getLevel(Level.OVERWORLD);

        return new DimensionTransition(
                destination,
                entity.adjustSpawnLocation(destination, entity.blockPosition()).getCenter(),
                entity.getDeltaMovement(),
                entity.getYRot(),
                entity.getXRot(),
                DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET)
        );
    }
}
