package com.reetam.borealis.item;

import com.reetam.borealis.TRandom;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisFluids;
import com.reetam.borealis.registry.BorealisSounds;
import com.reetam.borealis.registry.BorealisTags;
import com.reetam.borealis.registry.world.BorealisDimensions;
import com.reetam.borealis.world.teleporter.WorldHeightTransition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class HailstoneItem extends Item {

    public HailstoneItem() {
        super(new Properties()
                .stacksTo(1)
                .durability(32)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos center = pContext.getClickedPos().above();
        Level level = pContext.getLevel();
        if (pContext.getClickedFace() != Direction.UP) return InteractionResult.FAIL;

        if (level.dimension() != Level.OVERWORLD && level.dimension() != BorealisDimensions.BOREALIS) return InteractionResult.FAIL;

        boolean brokenFrame;
        for (int r = 2; r <= 5; r++) {
            brokenFrame = false;
            for (int x = -r; x <= r; x++) {
                int z = r - Math.abs(x);
                if (!level.getBlockState(center.offset(x, 0, z)).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS) || !level.getBlockState(center.offset(x, 0, -z)).is(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)) {
                    brokenFrame = true;
                    break;
                }
            }
            if (!brokenFrame) {
                for (int x2 = -r; x2 <= r; x2++) {
                    for (int z2 = -(r - Math.abs(x2)); z2 <= r - Math.abs(x2); z2++) {
                        if (Math.abs(x2) + Math.abs(z2) == r) {
                            if (level.getBlockState(center.offset(x2, 0, z2)).is(BorealisBlocks.KYANITE_CABLE)) {
                                level.setBlock(center.offset(x2, 0, z2), BorealisBlocks.KYANITE_FLAGSTONE.get().defaultBlockState(), 18);
                            }
                        } else if (level.getBlockState(center.offset(x2, 0, z2)).isAir()) {
                            level.setBlock(center.offset(x2, 0, z2), BorealisFluids.PORTAL_FLUID_BLOCK.get().defaultBlockState(), 18);
                        }
                    }
                }
                // level.setBlock(center.above(), BorealisBlocks.MOONFLOWER_BLOCK.get().defaultBlockState(), 18);
                level.playLocalSound(center.getX(), center.getY(), center.getZ(), BorealisSounds.HAILSTONE_FALL.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
                pContext.getItemInHand().hurtAndBreak(pContext.getItemInHand().getDamageValue(), pContext.getPlayer(), LivingEntity.getSlotForHand(pContext.getHand()));
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player pPlayer, InteractionHand pUsedHand) {
        Vec3 pos = pPlayer.position().add(0, 1, 0);

        if (level instanceof ServerLevel serverLevel) {
            if (level.dimension() == Level.OVERWORLD && pPlayer.getY() >= level.getMaxBuildHeight() + 2) {
                DimensionTransition transition = WorldHeightTransition.toBorealis(serverLevel, pPlayer);
                pPlayer.changeDimension(transition);
                return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));

            } else if (level.dimension() == BorealisDimensions.BOREALIS && pPlayer.getY() <= level.getMinBuildHeight() - 2) {
                DimensionTransition transition = WorldHeightTransition.toOverworld(serverLevel, pPlayer);
                pPlayer.changeDimension(transition);
                return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
            }
        }

        particles(level, pos, pPlayer);
        hurtEntities(level, pos);
        areaEffect(level, pPlayer);

        pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, LivingEntity.getSlotForHand(pUsedHand));
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }

    private void particles(Level level, Vec3 pos, Player player) {
        RandomSource random = level.getRandom();

        for (int i = 0; i < 20; i++) {
            TRandom part = new TRandom(random, -0.5, 0.25);
            List.of(ParticleTypes.WHITE_ASH, ParticleTypes.SNOWFLAKE, ParticleTypes.WHITE_SMOKE)
                    .forEach((particle) -> level.addParticle(particle, pos.x, pos.y, pos.z, part.next(), part.next(), part.next()));
        }
        level.playSound(player, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), SoundEvents.SNOW_BREAK, SoundSource.PLAYERS);
    }

    private void hurtEntities(Level level, Vec3 pos) {
        level.getEntities(null, new AABB(pos.add(-5, -2, -5), pos.add(5, 2, 5))).forEach((entity) -> {
            if (!(entity instanceof Player) && entity instanceof LivingEntity living) {
                for (float i = 0.1F; i < 1; i += 0.1F) {
                    level.addParticle(ParticleTypes.END_ROD, pos.x, pos.y, pos.z, i * (living.getX() - pos.x), i * (living.getY() - pos.y), i * (living.getZ() - pos.z));
                }
                living.hurt(level.damageSources().freeze(), 2F);
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
            }
        });
    }

    private void areaEffect(Level level, Player player) {
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                for (int k = -1; k <= 3; k++) {
                    if (i * i + j * j <= 9) {
                        BlockPos disk = player.getBlockPosBelowThatAffectsMyMovement().offset(i, k, j);
                        BlockState state = level.getBlockState(disk);
                        if (state.is(Blocks.WATER) && k == 0) {
                            level.setBlock(disk, Blocks.FROSTED_ICE.defaultBlockState(), 3);
                        }
                        if (state.is(BlockTags.CROPS) || state.is(BlockTags.FIRE)) {
                            level.destroyBlock(disk, state.is(BlockTags.CROPS));
                        }
                        if (state.hasProperty(BlockStateProperties.LIT)) {
                            level.setBlock(disk, state.setValue(BlockStateProperties.LIT, false), 3);
                        }
                        if (state.is(Blocks.MAGMA_BLOCK)) {
                            level.setBlock(disk, Blocks.NETHERRACK.defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }
}
