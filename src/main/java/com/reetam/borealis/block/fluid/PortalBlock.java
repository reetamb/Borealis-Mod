package com.reetam.borealis.block.fluid;

import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisFluids;
import com.reetam.borealis.registry.world.BorealisDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.portal.DimensionTransition;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;

public class PortalBlock extends LiquidBlock implements Portal {
    public PortalBlock(Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid.get(), properties.noCollission().strength(100F).noLootTable().lightLevel((state) -> 10));
    }

    @Override
    protected boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return (pLevel.getBlockState(pPos.below()).isFaceSturdy(pLevel, pPos.below(), Direction.UP));
    }

    @Override
    protected void neighborChanged(BlockState thisState, Level pLevel, BlockPos thisPos, Block neighborBlock, BlockPos neighborPos, boolean pIsMoving) {
        if (neighborPos.getY() - thisPos.getY() == 0) {
            pLevel.setBlock(thisPos, Blocks.AIR.defaultBlockState(), 3);
        }
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return ItemStack.EMPTY;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, pos);
        }
    }

    @Override
    public int getPortalTransitionTime(ServerLevel pLevel, Entity pEntity) {
        return pEntity instanceof Player ? 1 : 0;
    }

    @Nullable
    @Override
    public DimensionTransition getPortalDestination(ServerLevel pLevel, Entity pEntity, BlockPos pPos) {
        ResourceKey<Level> targetDimension = pLevel.dimension() == Level.OVERWORLD ? BorealisDimensions.BOREALIS : Level.OVERWORLD;
        ServerLevel serverlevel = pLevel.getServer().getLevel(targetDimension);
        BlockPos exitPos = pLevel.getWorldBorder().clampToBounds(pPos);

        if (serverlevel == null) {
            return null;
        } else {
            // search for portal in other dimension
            Optional<Integer> top = Optional.empty();
            Optional<BlockPos> portalPos = Optional.empty();
            for (int height = serverlevel.getMaxBuildHeight(); height >= serverlevel.getMinBuildHeight(); height--) {
                if (serverlevel.getBlockState(exitPos.atY(height)).is(BorealisFluids.PORTAL_FLUID_BLOCK)) {
                    portalPos = Optional.of(exitPos.atY(height));
                    break;
                }
                if (!serverlevel.getBlockState(exitPos.atY(height)).isAir() && top.isEmpty()) {
                    top = Optional.of(height + 1);
                }
            }

//            pLevel.getPoiManager().ensureLoadedAndValid(serverlevel, exitPos, 4);
//            Optional<BlockPos> portalPos = serverlevel.getPoiManager().getInSquare(type -> type.is(BorealisDimensions.BOREALIS_PORTAL_POI), exitPos, 4, PoiManager.Occupancy.ANY)
//                    .map(PoiRecord::getPos)
//                    .filter(pLevel.getWorldBorder()::isWithinBounds)
//                    .filter((pos) -> pLevel.getBlockState(pos).is(BorealisFluids.PORTAL_FLUID_BLOCK))
//                    .min(Comparator.<BlockPos>comparingDouble(p_352046_ -> p_352046_.distSqr(pPos)).thenComparingInt(Vec3i::getY));

            // if there is a portal:
            if (portalPos.isPresent()) {
                BlockState state = pLevel.getBlockState(portalPos.get());

                BlockPos outPos = portalPos.get().above();
                for (int x = -3; x <= 3; x++) {
                    for (int z = -3; z <= 3; z++) {
                        if (pLevel.getBlockState(portalPos.get().offset(x, 1, z)).is(BorealisBlocks.MOONFLOWER_BLOCK)) {
                            outPos = portalPos.get().offset(x, 1, z);
                        }
                    }
                }
                if (outPos == portalPos.get().above()) {
                    serverlevel.setBlock(outPos, BorealisBlocks.MOONFLOWER_BLOCK.get().defaultBlockState(), 3);
                }

                return new DimensionTransition(
                        serverlevel,
                        outPos.getBottomCenter(),
                        pEntity.getDeltaMovement(),
                        pEntity.getYRot(),
                        pEntity.getXRot(),
                        DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET)
                );
            // if there is no portal
            } else {
                if (top.isPresent()) {
                    exitPos = exitPos.atY(top.get());
                } else {
                    int y = serverlevel.getHeight(Heightmap.Types.WORLD_SURFACE, exitPos.getX(), exitPos.getZ());
                    if (y <= serverlevel.getMinBuildHeight()) {
                        y = serverlevel.getMinBuildHeight() + 16;
                    }
                    exitPos = exitPos.atY(y);
                }

                makePortal(serverlevel, exitPos);

                return new DimensionTransition(
                        serverlevel,
                        exitPos.getBottomCenter(),
                        pEntity.getDeltaMovement(),
                        pEntity.getYRot(),
                        pEntity.getXRot(),
                        DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET));
            }
        }
        // return null;
    }

    @Override
    public Transition getLocalTransition() {
        return Portal.Transition.CONFUSION;
    }

    private void makePortal(ServerLevel level, BlockPos centerPos) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -(2 - Math.abs(x)); z <= 2 - Math.abs(x); z++) {
                BlockState state = Math.abs(x) + Math.abs(z) == 2 ? BorealisBlocks.KYANITE_FLAGSTONE.get().defaultBlockState() : BorealisFluids.PORTAL_FLUID_BLOCK.get().defaultBlockState();
                level.setBlock(centerPos.offset(x, 0, z), state, 18);
                level.setBlock(centerPos.offset(x, -1, z), BorealisBlocks.KYANITE_FLAGSTONE.get().defaultBlockState(), 3);
            }
        }
    }
}
