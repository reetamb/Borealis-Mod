package com.reetam.borealis.item;

import com.reetam.borealis.block.property.FluoriteFrame;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class FleeceItem extends Item {
    public FleeceItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        Direction direction = pContext.getHorizontalDirection();

        Optional<FluoriteFrame> isFrame = isFluoriteFrame(level, pos, direction);
        if (isFrame.isPresent()) {
            FluoriteFrame frame = isFrame.get();
            frame.createStaticField();
            return InteractionResult.SUCCESS;
        }
        return super.useOn(pContext);
    }

    public static Optional<FluoriteFrame> isFluoriteFrame(Level pLevel, BlockPos pPos, Direction pDirection) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();
        boolean foundFluorite = false;

        for(Direction direction : Direction.values()) {
            if (pLevel.getBlockState(blockpos$mutableblockpos.set(pPos).move(direction)).is(BorealisBlocks.FLUORITE.get())) {
                foundFluorite = true;
                break;
            }
        }

        if (!foundFluorite) {
            return Optional.empty();
        } else {
            Direction.Axis direction$axis = pDirection.getAxis().isHorizontal() ? pDirection.getCounterClockWise().getAxis() : Direction.Plane.HORIZONTAL.getRandomAxis(pLevel.random);
            return FluoriteFrame.findEmptyFluoriteFrame(pLevel, pPos, direction$axis);
        }
    }
}
