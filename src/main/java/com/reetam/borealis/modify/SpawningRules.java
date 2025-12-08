package com.reetam.borealis.modify;

import com.reetam.borealis.modify.attachments.ExtinctionSavedData;
import com.reetam.borealis.registry.world.BorealisDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;

public class SpawningRules {

    public static boolean undeadSpawningRules(EntityType<? extends Entity> entity, ServerLevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random) {
        ExtinctionSavedData deathsData = level.getLevel().getDataStorage().computeIfAbsent(new SavedData.Factory<ExtinctionSavedData>(ExtinctionSavedData::new, ExtinctionSavedData::load), "deaths");

        return level.getLevel().dimension() != BorealisDimensions.BOREALIS ||
                level.getRandom().nextInt(5) < deathsData.getDeathsFromEntityType(EntityType.PLAYER);
    }

    public static boolean zombieVillagerSpawningRules(EntityType<? extends Entity> entity, ServerLevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random) {
        ExtinctionSavedData deathsData = level.getLevel().getDataStorage().computeIfAbsent(new SavedData.Factory<ExtinctionSavedData>(ExtinctionSavedData::new, ExtinctionSavedData::load), "deaths");

        return level.getLevel().dimension() != BorealisDimensions.BOREALIS ||
                level.getRandom().nextInt(5) < deathsData.getDeathsFromEntityType(EntityType.VILLAGER);
    }
}
