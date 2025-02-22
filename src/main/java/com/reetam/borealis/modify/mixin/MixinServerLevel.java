package com.reetam.borealis.modify.mixin;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.HailEntity;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class MixinServerLevel {
    @Inject(at = @At(value = "HEAD"), method = "tickChunk(Lnet/minecraft/world/level/chunk/LevelChunk;I)V")
    public void tickChunk(LevelChunk chunk, int randomTickSpeed, CallbackInfo ci) {
        ServerLevel level = (ServerLevel) (Object) this;
        ChunkPos chunkPos = chunk.getPos();
        int x = chunkPos.getMinBlockX(); int z = chunkPos.getMinBlockZ();
        BlockPos pos = level.getBlockRandomPos(x, 0, z, 15);
        Biome biome = level.getBiome(level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, pos)).value();
        BlockPos hailPos;
        if (level.isRaining() && level.isThundering() && biome.shouldSnow(level, pos) && level.random.nextInt(300000 / randomTickSpeed) == 0) {
            hailPos = new BlockPos(pos.getX(), level.getMaxBuildHeight(), pos.getZ());
            BorealisMod.LOGGER.error("Hailstone fell at " + hailPos);
            HailEntity hail = HailEntity.fall(level, hailPos);
            hail.setStartPos(hailPos);
            level.addFreshEntity(hail);
        }
    }

}
