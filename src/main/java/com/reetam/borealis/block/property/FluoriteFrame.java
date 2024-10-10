//
// Copy of net.minecraft.world.level.portal.PortalShape
//

package com.reetam.borealis.block.property;

import com.reetam.borealis.block.StaticFieldBlock;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Predicate;

public class FluoriteFrame {
    private static final int MIN_WIDTH = 2;
    public static final int MAX_WIDTH = 21;
    private static final int MIN_HEIGHT = 2;
    public static final int MAX_HEIGHT = 21;
    private static final BlockBehaviour.StatePredicate FRAME = (state, level, pos) -> state.is(BorealisBlocks.FLUORITE.get());
    private static final float SAFE_TRAVEL_MAX_ENTITY_XY = 4.0F;
    private static final double SAFE_TRAVEL_MAX_VERTICAL_DELTA = 1.0;
    private final LevelAccessor level;
    private final Direction.Axis axis;
    private final Direction rightDir;
    private int numStaticBlocks;
    @Nullable
    private BlockPos bottomLeft;
    private int height;
    private final int width;

    public static Optional<FluoriteFrame> findEmptyFluoriteFrame(LevelAccessor pLevel, BlockPos pBottomLeft, Direction.Axis pAxis) {
        return findFluoriteFrame(pLevel, pBottomLeft, (frame) -> frame.isValid() && frame.numStaticBlocks == 0, pAxis);
    }

    public static Optional<FluoriteFrame> findFluoriteFrame(LevelAccessor pLevel, BlockPos pBottomLeft, Predicate<FluoriteFrame> pPredicate, Direction.Axis pAxis) {
        Optional<FluoriteFrame> optional = Optional.of(new FluoriteFrame(pLevel, pBottomLeft, pAxis)).filter(pPredicate);
        if (optional.isPresent()) {
            return optional;
        } else {
            Direction.Axis direction$axis = pAxis == Axis.X ? Axis.Z : Axis.X;
            return Optional.of(new FluoriteFrame(pLevel, pBottomLeft, direction$axis)).filter(pPredicate);
        }
    }

    public FluoriteFrame(LevelAccessor pLevel, BlockPos pBottomLeft, Direction.Axis pAxis) {
        this.level = pLevel;
        this.axis = pAxis;
        this.rightDir = pAxis == Axis.X ? Direction.WEST : Direction.SOUTH;
        this.bottomLeft = this.calculateBottomLeft(pBottomLeft);
        if (this.bottomLeft == null) {
            this.bottomLeft = pBottomLeft;
            this.width = 1;
            this.height = 1;
        } else {
            this.width = this.calculateWidth();
            if (this.width > 0) {
                this.height = this.calculateHeight();
            }
        }
        this.numStaticBlocks = 0;
    }

    @Nullable
    private BlockPos calculateBottomLeft(BlockPos pPos) {
        for(int i = Math.max(this.level.getMinBuildHeight(), pPos.getY() - MAX_HEIGHT); pPos.getY() > i && isEmpty(this.level.getBlockState(pPos.below())); pPos = pPos.below()) {
        }

        Direction direction = this.rightDir.getOpposite();
        int j = this.getDistanceUntilEdgeAboveFrame(pPos, direction) - 1;
        return j < 0 ? null : pPos.relative(direction, j);
    }

    private int calculateWidth() {
        int i = this.getDistanceUntilEdgeAboveFrame(this.bottomLeft, this.rightDir);
        return i >= MIN_WIDTH && i <= MAX_WIDTH ? i : 0;
    }

    private int getDistanceUntilEdgeAboveFrame(BlockPos pPos, Direction pDirection) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int i = 0; i <= MAX_WIDTH; ++i) {
            blockpos$mutableblockpos.set(pPos).move(pDirection, i);
            BlockState blockstate = this.level.getBlockState(blockpos$mutableblockpos);
            if (!isEmpty(blockstate)) {
                if (FRAME.test(blockstate, this.level, blockpos$mutableblockpos)) {
                    return i;
                }
                break;
            }

            BlockState blockstate1 = this.level.getBlockState(blockpos$mutableblockpos.move(Direction.DOWN));
            if (!FRAME.test(blockstate1, this.level, blockpos$mutableblockpos)) {
                break;
            }
        }

        return 0;
    }

    private int calculateHeight() {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int i = this.getDistanceUntilTop(blockpos$mutableblockpos);
        return i >= MIN_HEIGHT && i <= MAX_HEIGHT && this.hasTopFrame(blockpos$mutableblockpos, i) ? i : 0;
    }

    private boolean hasTopFrame(BlockPos.MutableBlockPos pPos, int pDistanceToTop) {
        for(int i = 0; i < this.width; ++i) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.set(this.bottomLeft).move(Direction.UP, pDistanceToTop).move(this.rightDir, i);
            if (!FRAME.test(this.level.getBlockState(blockpos$mutableblockpos), this.level, blockpos$mutableblockpos)) {
                return false;
            }
        }

        return true;
    }

    private int getDistanceUntilTop(BlockPos.MutableBlockPos pPos) {
        for(int i = 0; i < MAX_HEIGHT; ++i) {
            pPos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, -1);
            if (!FRAME.test(this.level.getBlockState(pPos), this.level, pPos)) {
                return i;
            }

            pPos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, this.width);
            if (!FRAME.test(this.level.getBlockState(pPos), this.level, pPos)) {
                return i;
            }

            for(int j = 0; j < this.width; ++j) {
                pPos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, j);
                BlockState blockstate = this.level.getBlockState(pPos);
                if (!isEmpty(blockstate)) {
                    return i;
                }

                if (blockstate.is(BorealisBlocks.STATIC_FIELD.get())) {
                    ++this.numStaticBlocks;
                }
            }
        }

        return MAX_HEIGHT;
    }

    private static boolean isEmpty(BlockState pState) {
        return pState.isAir() || pState.is(BorealisBlocks.STATIC_FIELD.get());
    }

    public boolean isValid() {
        return this.bottomLeft != null && this.width >= MIN_WIDTH && this.width <= MAX_WIDTH && this.height >= MIN_HEIGHT && this.height <= MAX_HEIGHT;
    }

    public void createStaticField() {
        BlockState blockstate = BorealisBlocks.STATIC_FIELD.get().defaultBlockState()
                .setValue(StaticFieldBlock.EAST, this.axis.test(Direction.EAST))
                .setValue(StaticFieldBlock.SOUTH, this.axis.test(Direction.SOUTH))
                .setValue(StaticFieldBlock.WEST, this.axis.test(Direction.WEST))
                .setValue(StaticFieldBlock.NORTH, this.axis.test(Direction.NORTH));
        BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDir, this.width - 1)).forEach((pos) -> this.level.setBlock(pos, blockstate, 18));
    }

    public boolean isComplete() {
        return this.isValid() && this.numStaticBlocks == this.width * this.height;
    }

    public static Vec3 getRelativePosition(BlockUtil.FoundRectangle pFoundRectangle, Direction.Axis pAxis, Vec3 pPos, EntityDimensions pEntityDimensions) {
        double d0 = (double)pFoundRectangle.axis1Size - (double)pEntityDimensions.width();
        double d1 = (double)pFoundRectangle.axis2Size - (double)pEntityDimensions.height();
        BlockPos blockpos = pFoundRectangle.minCorner;
        double d2;
        if (d0 > 0.0) {
            float f = (float)blockpos.get(pAxis) + pEntityDimensions.width() / 2.0F;
            d2 = Mth.clamp(Mth.inverseLerp(pPos.get(pAxis) - (double)f, 0.0, d0), 0.0, 1.0);
        } else {
            d2 = 0.5;
        }

        double d4;
        Direction.Axis direction$axis1;
        if (d1 > 0.0) {
            direction$axis1 = Axis.Y;
            d4 = Mth.clamp(Mth.inverseLerp(pPos.get(direction$axis1) - (double)blockpos.get(direction$axis1), 0.0, d1), 0.0, 1.0);
        } else {
            d4 = 0.0;
        }

        direction$axis1 = pAxis == Axis.X ? Axis.Z : Axis.X;
        double d3 = pPos.get(direction$axis1) - ((double)blockpos.get(direction$axis1) + 0.5);
        return new Vec3(d2, d4, d3);
    }

    private static Vec3 findCollisionFreePosition(Vec3 pPos, ServerLevel pLevel, Entity pEntity, EntityDimensions pDimensions) {
        if (!(pDimensions.width() > 4.0F) && !(pDimensions.height() > 4.0F)) {
            double d0 = (double)pDimensions.height() / 2.0;
            Vec3 vec3 = pPos.add(0.0, d0, 0.0);
            VoxelShape voxelshape = Shapes.create(AABB.ofSize(vec3, (double)pDimensions.width(), 0.0, (double)pDimensions.width()).expandTowards(0.0, 1.0, 0.0).inflate(1.0E-6));
            Optional<Vec3> optional = pLevel.findFreePosition(pEntity, voxelshape, vec3, (double)pDimensions.width(), (double)pDimensions.height(), (double)pDimensions.width());
            Optional<Vec3> optional1 = optional.map((p_259019_) -> {
                return p_259019_.subtract(0.0, d0, 0.0);
            });
            return (Vec3)optional1.orElse(pPos);
        } else {
            return pPos;
        }
    }
}
