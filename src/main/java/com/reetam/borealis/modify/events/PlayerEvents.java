package com.reetam.borealis.modify.events;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingGetProjectileEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber
public class PlayerEvents {
    @SubscribeEvent
    public static void getProjectileEvent(LivingGetProjectileEvent event) {
        BorealisMod.LOGGER.error("running event");
        if (event.getEntity() instanceof Player player) {
            BorealisMod.LOGGER.error("is player");
            if (player.getInventory().hasAnyOf(Set.of(BorealisItems.KYANITE_ARROW.get()))) {
                BorealisMod.LOGGER.error("has kyanite arrow");
                event.setProjectileItemStack(player.getInventory().getItem(player.getInventory().findSlotMatchingItem(new ItemStack(BorealisItems.KYANITE_ARROW.get()))));
                BorealisMod.LOGGER.error("found arrow");
            }
        }
    }
}
