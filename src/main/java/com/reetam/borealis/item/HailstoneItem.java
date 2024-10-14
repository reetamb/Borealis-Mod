package com.reetam.borealis.item;

import com.reetam.borealis.registry.BorealisTags;
import com.reetam.borealis.registry.world.BorealisDimensions;
import com.reetam.borealis.world.teleporter.WorldHeightTransition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class HailstoneItem extends Item {

    public HailstoneItem() {
        super(new Properties()
                .stacksTo(1)
                .durability(1)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player pPlayer, InteractionHand pUsedHand) {
        Vec3 pos = pPlayer.position().add(0, 1, 0);

        for (int i = 0; i < 20; i++) {
            level.addParticle(ParticleTypes.WHITE_ASH, pos.x, pos.y, pos.z, (level.random.nextFloat() - 0.5) / 4, (level.random.nextFloat() - 0.5) / 4, (level.random.nextFloat() - 0.5) / 4);
            level.addParticle(ParticleTypes.SNOWFLAKE, pos.x, pos.y, pos.z, (level.random.nextFloat() - 0.5) / 4, (level.random.nextFloat() - 0.5) / 4, (level.random.nextFloat() - 0.5) / 4);
            level.addParticle(ParticleTypes.WHITE_SMOKE, pos.x, pos.y, pos.z, (level.random.nextFloat() - 0.5) / 4, (level.random.nextFloat() - 0.5) / 4, (level.random.nextFloat() - 0.5) / 4);
        }
        level.playSound(pPlayer, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), SoundEvents.SNOW_BREAK, SoundSource.PLAYERS);
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
        level.getEntities(null, new AABB(pos.add(-5, -2, -5), pos.add(5, 2, 5))).forEach((entity) -> {
            if (!(entity instanceof Player) && entity instanceof LivingEntity living) {
                for (float i = 0.1F; i < 1; i += 0.1F) {
                    level.addParticle(ParticleTypes.GLOW_SQUID_INK, pos.x, pos.y, pos.z, i * (living.getX() - pos.x), i * (living.getY() - pos.y), i * (living.getZ() - pos.z));
                }
                living.hurt(level.damageSources().freeze(), 2F);
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
            }
        });
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                for (int k = -1; k <= 3; k++) {
                    if (i * i + j * j <= 9) {
                        BlockPos disk = pPlayer.getBlockPosBelowThatAffectsMyMovement().offset(i, k, j);
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
                    }
                }
            }
        }
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }
}
