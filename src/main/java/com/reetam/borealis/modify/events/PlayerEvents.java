package com.reetam.borealis.modify.events;

import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingGetProjectileEvent;

import java.util.Set;

public class PlayerEvents {
    public static void getProjectileEvent(LivingGetProjectileEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getInventory().hasAnyOf(Set.of(BorealisItems.KYANITE_ARROW.get()))) {
                // TODO: this should sort by closest arrow to hotbar/slot 1
                event.setProjectileItemStack(player.getInventory().getItem(player.getInventory().findSlotMatchingItem(new ItemStack(BorealisItems.KYANITE_ARROW.get()))));
            }
        }
    }
}
