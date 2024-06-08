package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
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
    public static final RegistryObject<EntityType<ThrusherEntity>> THRUSHER = ENTITIES.register("thrusher",
            () ->  EntityType.Builder.of(ThrusherEntity::new, MobCategory.CREATURE)
                    .sized(1.4F, 1.4F).clientTrackingRange(10).build("thrusher"));
    public static final RegistryObject<EntityType<HailEntity>> HAIL = ENTITIES.register("hail",
            () -> EntityType.Builder.of(HailEntity::new, MobCategory.MISC)
                    .sized(1.0F, 1.0F).clientTrackingRange(10).build("hail"));
    public static final RegistryObject<EntityType<KyaniteArrowEntity>> KYANITE_ARROW = ENTITIES.register("kyanite_arrow",
            () -> EntityType.Builder.of(KyaniteArrowEntity::new, MobCategory.MISC)
                    .sized(1.0F, 1.0F).clientTrackingRange(10).build("kyanite_arrow"));

    public static void registerSpawnPlacements() {
        // SpawnPlacements.register(HUMMINGBIRD.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HummingbirdEntity::checkHummingbirdSpawnRules);
        // SpawnPlacements.register(TAKAHE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TakaheEntity::checkTakaheSpawnRules);
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(HUMMINGBIRD.get(), HummingbirdEntity.registerAttributes().build());
        event.put(TAKAHE.get(), TakaheEntity.registerAttributes().build());
        event.put(THRUSHER.get(), ThrusherEntity.registerAttributes().build());
    }
}