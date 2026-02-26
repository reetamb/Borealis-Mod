package com.reetam.borealis.modify.events;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.data.trigger.BorealisTriggers;
import com.reetam.borealis.modify.attachments.ExtinctionSavedData;
import com.reetam.borealis.registry.BorealisItems;
import com.reetam.borealis.registry.world.BorealisDimensions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.event.entity.living.*;

import java.util.Set;
import java.util.UUID;

public class PlayerEvents {
    public static void loadKyaniteArrowEvent(LivingGetProjectileEvent event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide()) {
            if (player.getInventory().hasAnyOf(Set.of(BorealisItems.KYANITE_ARROW.get()))) {
                int kyaniteSlot = player.getInventory().findSlotMatchingItem(new ItemStack(BorealisItems.KYANITE_ARROW.get()));

                if (player.getInventory().hasAnyOf(Set.of(Items.ARROW))) {
                    int arrowSlot = player.getInventory().findSlotMatchingItem(new ItemStack(Items.ARROW));
                    event.setProjectileItemStack(player.getInventory().getItem(Math.min(kyaniteSlot, arrowSlot)));
                } else {
                    event.setProjectileItemStack(player.getInventory().getItem(kyaniteSlot));
                }
            }
        }


    }

    public static void burnInAtmosphereEvent(LivingIncomingDamageEvent event) {
        DamageSource source = event.getSource();
        Entity entity = event.getEntity();
        Level level = entity.level();
        if (source == level.damageSources().fellOutOfWorld() && level.dimension() == BorealisDimensions.BOREALIS) {
            if (entity.getY() <= BorealisMod.MIN_HEIGHT && !entity.fireImmune()) {
                event.setCanceled(true);
                entity.igniteForSeconds(2);
                entity.hurt(level.damageSources().onFire(), event.getAmount());
            }
        }
    }

    public static void reducedFallDamageEvent(LivingFallEvent event) {
        if (event.getEntity().level().dimension() != BorealisDimensions.BOREALIS) return;
        event.setDistance(event.getDistance() * BorealisMod.FALL_FACTOR);
    }

    public static void onDeathEvent(LivingDeathEvent event) {
        if (event.getEntity().level().dimension() != BorealisDimensions.BOREALIS) return;
        if (event.getEntity().level().getServer() == null) return;
        ExtinctionSavedData deathData = event.getEntity().level().getServer().getLevel(BorealisDimensions.BOREALIS).getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(ExtinctionSavedData::new, ExtinctionSavedData::load), "deaths");
        deathData.setDataEntry(event.getEntity().getType(), deathData.getDeathsFromEntityType(event.getEntity().getType()) + 1);
    }

    public static void useItemEvent(LivingEntityUseItemEvent.Start event) {
        if (event.getItem().has(BorealisItems.Components.FROZEN)) {
            event.setCanceled(true);
            if (event.getEntity() instanceof ServerPlayer player) {
                BorealisTriggers.TRY_FROZEN_FOOD.get().trigger(player, event.getItem());
            }
        }
    }
}
