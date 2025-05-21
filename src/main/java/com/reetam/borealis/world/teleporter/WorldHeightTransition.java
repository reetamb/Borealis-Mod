package com.reetam.borealis.world.teleporter;

import com.reetam.borealis.registry.BorealisItems;
import com.reetam.borealis.registry.BorealisTags;
import com.reetam.borealis.registry.world.BorealisDimensions;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WorldHeightTransition {

    public static DimensionTransition toBorealis(ServerLevel level, Entity entity) {
        ServerLevel destination = level.getServer().getLevel(BorealisDimensions.BOREALIS);
        Vec3 pos = entity.adjustSpawnLocation(destination, entity.blockPosition()).getCenter();
        freezeFood(entity);
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

    private static void freezeFood(Entity entity) {
        if (entity instanceof Player player) {
            for (int i = 0; i <= player.getInventory().getContainerSize(); i++) {
                ItemStack item = player.getInventory().getItem(i);
                ItemStack frozen = item;
                boolean flag = false;
                if (item.is(BorealisTags.Items.MEAT)) {
                    frozen = item.transmuteCopy(BorealisItems.FROZEN_STEW.get());
                    flag = true;
                }
                else if (item.is(BorealisTags.Items.PRODUCE)) {
                    frozen = item.transmuteCopy(BorealisItems.FROZEN_STEW.get());
                    flag = true;
                }
                else if (item.is(BorealisTags.Items.STEW)) {
                    frozen = item.transmuteCopy(BorealisItems.FROZEN_STEW.get());
                    flag = true;
                }

                if (flag) {
                    frozen.applyComponents(item.getComponents());
                    player.getInventory().setItem(i, frozen);
                }
            }
        }
    }
}
