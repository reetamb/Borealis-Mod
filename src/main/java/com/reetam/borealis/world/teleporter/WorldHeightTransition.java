package com.reetam.borealis.world.teleporter;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.item.components.FrozenFoodType;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import com.reetam.borealis.registry.BorealisTags;
import com.reetam.borealis.registry.world.BorealisDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

public class WorldHeightTransition {

    public static DimensionTransition toBorealis(ServerLevel level, Entity entity) {
        ServerLevel destination = level.getServer().getLevel(BorealisDimensions.BOREALIS);
        Vec3 pos = entity.adjustSpawnLocation(destination, entity.blockPosition()).getCenter();
        freezeFood(entity);
        return new DimensionTransition(
                destination,
                pos.y() >= BorealisMod.MIN_HEIGHT + 32 ? pos : new Vec3(pos.x(), BorealisMod.MIN_HEIGHT+(0.375*BorealisMod.HEIGHT), pos.z()),
                entity.getDeltaMovement(),
                entity.getYRot(),
                entity.getXRot(),
                DimensionTransition.PLAY_PORTAL_SOUND.then(WorldHeightTransition::placeCloud)
        );
    }

    private static DimensionTransition.PostDimensionTransition placeCloud(Entity entity) {
        entity.placePortalTicket(BlockPos.containing(entity.position()));
        int r = 2;
        for (int i = -r; i <= r; i++) {
            for (int j = -r; j <= r; j++) {
                if (!(i*i+j*j <= r*r)) continue;
                entity.level().setBlock(entity.getBlockPosBelowThatAffectsMyMovement().offset(i,0,j), BorealisBlocks.CLOUD.get().defaultBlockState(), 3);
            }
        }
        entity.level().setBlock(entity.getBlockPosBelowThatAffectsMyMovement(), BorealisBlocks.CLOUD.get().defaultBlockState(), 3);
        return DimensionTransition.PLACE_PORTAL_TICKET;
    }

    public static DimensionTransition toOverworld(ServerLevel level, Entity entity) {
        ServerLevel destination = level.getServer().getLevel(Level.OVERWORLD);
        thawFood(entity);
        return new DimensionTransition(
                destination,
                entity.adjustSpawnLocation(destination, entity.blockPosition()).getCenter(),
                entity.getDeltaMovement(),
                entity.getYRot(),
                entity.getXRot(),
                DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET)
        );
    }

    private static void freezeFood(Entity entity) {
        if (entity instanceof Player player) {
            for (int i = 0; i <= player.getInventory().getContainerSize(); i++) {
                ItemStack item = player.getInventory().getItem(i);
                if (item.has(DataComponents.FOOD)) {
                    if (item.is(BorealisTags.Items.MEAT)) {
                        item.set(BorealisItems.Components.FROZEN.get(), FrozenFoodType.MEAT);
                    } else if (item.is(BorealisTags.Items.PRODUCE)) {
                        item.set(BorealisItems.Components.FROZEN.get(), FrozenFoodType.PRODUCE);
                    } else if (item.is(BorealisTags.Items.STEW)) {
                        item.set(BorealisItems.Components.FROZEN.get(), FrozenFoodType.STEW);
                    } else {
                        item.set(BorealisItems.Components.FROZEN.get(), FrozenFoodType.MUSH);
                    }
                }
            }
        }
    }

    private static void thawFood(Entity entity) {
        if (entity instanceof Player player) {
            for (int i = 0; i <= player.getInventory().getContainerSize(); i++) {
                ItemStack item = player.getInventory().getItem(i);
                if (item.has(BorealisItems.Components.FROZEN.get())) {
                    item.remove(BorealisItems.Components.FROZEN);
                }
            }
        }
    }
}
