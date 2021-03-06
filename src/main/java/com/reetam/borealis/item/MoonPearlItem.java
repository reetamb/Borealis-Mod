package com.reetam.borealis.item;

import com.reetam.borealis.block.BorealisPortalBlock;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoonPearlItem extends Item {

    public MoonPearlItem() {
        super(new Properties()
                .tab(BorealisItems.Groups.BOREALIS_ITEMS)
                .stacksTo(1)
                .durability(1)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {

        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if (world.getBlockState(pos).getBlock() == Blocks.SNOW) {
            if ( (world.getBlockState(pos.west()).getBlock() == Blocks.SNOW) &&
                    (world.getBlockState(pos.north()).getBlock() == Blocks.SNOW) &&
                    (world.getBlockState(pos.south()).getBlock() == Blocks.SNOW) &&
                    (world.getBlockState(pos.east()).getBlock() == Blocks.SNOW)) {
                if ( (world.getBlockState(pos.west(2)).getBlock() == Blocks.PACKED_ICE) &&
                        (world.getBlockState(pos.north(2)).getBlock() == Blocks.PACKED_ICE) &&
                        (world.getBlockState(pos.south(2)).getBlock() == Blocks.PACKED_ICE) &&
                        (world.getBlockState(pos.east(2)).getBlock() == Blocks.PACKED_ICE)) {
                    if ( (world.getBlockState(pos.west().north()).getBlock() == Blocks.PACKED_ICE) &&
                            (world.getBlockState(pos.north().east()).getBlock() == Blocks.PACKED_ICE) &&
                            (world.getBlockState(pos.south().west()).getBlock() == Blocks.PACKED_ICE) &&
                            (world.getBlockState(pos.east().south()).getBlock() == Blocks.PACKED_ICE)) {
                        ((BorealisPortalBlock) BorealisBlocks.borealis_portal.get()).makePortal(world, pos);
                        world.setRainLevel(1.0F);
                    }
                }
            }
        }

        return ActionResultType.sidedSuccess(world.isClientSide());
    }
}
