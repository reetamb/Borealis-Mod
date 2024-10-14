package com.reetam.borealis.modify.events;

import com.reetam.borealis.registry.BorealisItems;
import com.reetam.borealis.registry.world.BorealisDimensions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingGetProjectileEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.Set;

public class PlayerEvents {
    public static void loadKyaniteArrowEvent(LivingGetProjectileEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getInventory().hasAnyOf(Set.of(BorealisItems.KYANITE_ARROW.get()))) {
                // TODO: this should sort by closest arrow to hotbar/slot 1
                event.setProjectileItemStack(player.getInventory().getItem(player.getInventory().findSlotMatchingItem(new ItemStack(BorealisItems.KYANITE_ARROW.get()))));
            }
        }
    }

    public static void burnInAtmosphereEvent(LivingIncomingDamageEvent event) {
        DamageSource source = event.getSource();
        Entity entity = event.getEntity();
        Level level = entity.level();
        if (source == level.damageSources().fellOutOfWorld() && level.dimension() == BorealisDimensions.BOREALIS) {
            if (entity.getY() <= level.getMinBuildHeight()) {
                event.setCanceled(true);
                entity.igniteForSeconds(2);
                entity.hurt(level.damageSources().onFire(), event.getAmount());
            }
        }
    }
}
