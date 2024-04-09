package com.reetam.borealis.item;

import com.reetam.borealis.block.BorealisPortalBlock;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class HailstoneItem extends Item {

    public HailstoneItem() {
        super(new Properties()
                .stacksTo(1)
                .durability(1)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if (level.getBlockState(pos).is(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS)) {
            if ((level.getBlockState(pos.west()).is(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS)) &&
                    (level.getBlockState(pos.north()).is(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS)) &&
                    (level.getBlockState(pos.south()).is(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS)) &&
                    (level.getBlockState(pos.east()).is(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS))) {
                if ((level.getBlockState(pos.west(2)).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) &&
                        (level.getBlockState(pos.north(2)).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) &&
                        (level.getBlockState(pos.south(2)).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) &&
                        (level.getBlockState(pos.east(2)).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS))) {
                    if ((level.getBlockState(pos.west().north()).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) &&
                            (level.getBlockState(pos.north().east()).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) &&
                            (level.getBlockState(pos.south().west()).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) &&
                            (level.getBlockState(pos.east().south()).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS))) {
                        ((BorealisPortalBlock) BorealisBlocks.BOREALIS_PORTAL.get()).makePortal(level, pos);
                        level.playSound(context.getPlayer(), pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 2.5F, 1.0F);
                        level.playSound(context.getPlayer(), pos, SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 2.5F, 1.0F);
                        level.addParticle(ParticleTypes.EFFECT, pos.getX(), pos.getY() + 2, pos.getZ(), 0.5, 1.5, 0.5);
                        return InteractionResult.sidedSuccess(level.isClientSide());
                    }
                }
            }
        }

        return InteractionResult.FAIL;
    }
}
