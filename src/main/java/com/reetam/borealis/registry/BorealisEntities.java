package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.BorealisBoatEntity;
import com.reetam.borealis.entity.HummingbirdEntity;
import com.reetam.borealis.entity.TakaheEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = "borealis", bus = Mod.EventBusSubscriber.Bus.MOD)
public class BorealisEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BorealisMod.MODID);

    public static final RegistryObject<EntityType<BorealisBoatEntity>> BOAT = ENTITIES.register("boat",
            () -> EntityType.Builder.<BorealisBoatEntity>of(BorealisBoatEntity::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));

    public static final RegistryObject<EntityType<HummingbirdEntity>> HUMMINGBIRD = ENTITIES.register("hummingbird",
            () -> EntityType.Builder.of(HummingbirdEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 0.9F).clientTrackingRange(10).build("hummingbird"));
    public static final RegistryObject<EntityType<TakaheEntity>> TAKAHE = ENTITIES.register("takahe",
            () -> EntityType.Builder.of(TakaheEntity::new, MobCategory.CREATURE)
                    .sized(0.8F, 0.8F).clientTrackingRange(10).build("takahe"));


    public static void registerSpawnPlacements() {
        // SpawnPlacements.register(HUMMINGBIRD.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HummingbirdEntity::checkHummingbirdSpawnRules);
        // SpawnPlacements.register(TAKAHE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TakaheEntity::checkTakaheSpawnRules);
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(HUMMINGBIRD.get(), HummingbirdEntity.registerAttributes().build());
        event.put(TAKAHE.get(), TakaheEntity.registerAttributes().build());
    }
}