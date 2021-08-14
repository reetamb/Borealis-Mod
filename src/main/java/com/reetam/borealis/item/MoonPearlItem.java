package com.reetam.borealis.item;

import com.reetam.borealis.block.BorealisPortalBlock;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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
        if (world.getBlockState(pos).is(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS)) {
            if ((world.getBlockState(pos.west()).is(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS)) &&
                    (world.getBlockState(pos.north()).is(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS)) &&
                    (world.getBlockState(pos.south()).is(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS)) &&
                    (world.getBlockState(pos.east()).is(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS))) {
                if ((world.getBlockState(pos.west(2)).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) &&
                        (world.getBlockState(pos.north(2)).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) &&
                        (world.getBlockState(pos.south(2)).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) &&
                        (world.getBlockState(pos.east(2)).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS))) {
                    if ((world.getBlockState(pos.west().north()).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) &&
                            (world.getBlockState(pos.north().east()).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) &&
                            (world.getBlockState(pos.south().west()).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) &&
                            (world.getBlockState(pos.east().south()).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS))) {
                        ((BorealisPortalBlock) BorealisBlocks.BOREALIS_PORTAL.get()).makePortal(world, pos);
                        world.playSound(context.getPlayer(), pos, SoundEvents.BELL_RESONATE, SoundCategory.BLOCKS, 2.5F, 1.0F);
                        world.addParticle(ParticleTypes.EFFECT, pos.getX(), pos.getY() + 2, pos.getZ(), 0.5, 1.5, 0.5);
                    }
                }
            }
        }

        return ActionResultType.sidedSuccess(world.isClientSide());
    }
}
