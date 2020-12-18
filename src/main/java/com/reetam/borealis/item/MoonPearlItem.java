package com.reetam.borealis.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.reetam.borealis.block.BorealisPortalBlock;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisDimensions;
import com.reetam.borealis.registry.BorealisItemGroups;

import net.minecraft.item.Item.Properties;

public class MoonPearlItem extends Item {

    public MoonPearlItem() {
        super(new Properties()
                .group(BorealisItemGroups.ITEMS_GROUP)
                .maxStackSize(1)
                .maxDamage(1)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if(context.getPlayer() != null) {
            if(context.getPlayer().world.getDimensionKey() == BorealisDimensions.borealis_w || context.getPlayer().world.getDimensionKey() == World.OVERWORLD) {
                for(Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos framePos = context.getPos().offset(direction);
                    if(((BorealisPortalBlock) BorealisBlocks.borealis_portal.get()).trySpawnPortal(context.getWorld(), framePos)) {
                        context.getItem().damageItem(1, context.getPlayer(), (playerEntity) -> playerEntity.sendBreakAnimation(context.getHand()));
                        return ActionResultType.CONSUME;
                    }
                    else return ActionResultType.FAIL;
                }
            }
        }
        return ActionResultType.FAIL;
    }

}