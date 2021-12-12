package com.reetam.borealis.setup.events;

import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockEvents extends EventHandler {

    @SubscribeEvent
    public void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        ItemStack heldItem = event.getItemStack();
        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);

        if (state.is(BorealisTags.Blocks.SACCHARINE_LOGS)) {
            if (heldItem.getItem() instanceof AxeItem) {
                world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.PAPER)));
            }
        }
    }
}
