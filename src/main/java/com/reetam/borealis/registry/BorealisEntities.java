package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.butterbird.ButterbirdEntity;
import com.reetam.borealis.entity.butterbird.ButterbirdModel;
import com.reetam.borealis.entity.butterbird.ButterbirdRenderer;
import com.reetam.borealis.entity.hummingbird.HummingbirdEntity;
import com.reetam.borealis.entity.hummingbird.HummingbirdModel;
import com.reetam.borealis.entity.hummingbird.HummingbirdRenderer;
import com.reetam.borealis.entity.nonliving.*;
import com.reetam.borealis.entity.takahe.TakaheEntity;
import com.reetam.borealis.entity.takahe.TakaheModel;
import com.reetam.borealis.entity.thrusher.ThrusherEntity;
import com.reetam.borealis.entity.thrusher.ThrusherModel;
import com.reetam.borealis.entity.thrusher.ThrusherRenderer;
import com.reetam.borealis.entity.tuber.TuberEntity;
import com.reetam.borealis.entity.tuber.TuberModel;
import com.reetam.borealis.entity.takahe.TakaheRenderer;
import com.reetam.borealis.entity.tuber.TuberRenderer;
import com.reetam.borealis.modify.SpawningRules;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BorealisEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, BorealisMod.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<BorealisBoatEntity>> BOAT = ENTITIES.register("boat",
            () -> EntityType.Builder.<BorealisBoatEntity>of(BorealisBoatEntity::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));

    public static final DeferredHolder<EntityType<?>, EntityType<HailEntity>> HAIL = ENTITIES.register("hail",
            () -> EntityType.Builder.of(HailEntity::new, MobCategory.MISC)
                    .sized(1.0F, 1.0F).clientTrackingRange(10).build("hail"));

    public static final DeferredHolder<EntityType<?>, EntityType<KyaniteArrowEntity>> KYANITE_ARROW = ENTITIES.register("kyanite_arrow",
            () -> EntityType.Builder.of(KyaniteArrowEntity::new, MobCategory.MISC)
                    .sized(1.0F, 1.0F).clientTrackingRange(10).build("kyanite_arrow"));

    public static final DeferredHolder<EntityType<?>, EntityType<HummingbirdEntity>> HUMMINGBIRD = creature("hummingbird", HummingbirdEntity::new, 0.9F, 0.9F);
    public static final DeferredHolder<EntityType<?>, EntityType<TakaheEntity>> TAKAHE = creature("takahe", TakaheEntity::new, 0.8F, 0.8F);
    public static final DeferredHolder<EntityType<?>, EntityType<ButterbirdEntity>> BUTTERBIRD = creature("butterbird", ButterbirdEntity::new, 0.8F, 0.8F);
    public static final DeferredHolder<EntityType<?>, EntityType<ThrusherEntity>> THRUSHER = creature("thrusher", ThrusherEntity::new, 1.4F, 1.4F);
    public static final DeferredHolder<EntityType<?>, EntityType<TuberEntity>> TUBER = creature("tuber", TuberEntity::new, 0.5F, 0.5F);

    public static final DeferredHolder<Item, SpawnEggItem> HUMMINGBIRD_SPAWN_EGG = spawnEgg(BorealisEntities.HUMMINGBIRD,
                    0x9CE542, 0xFF446D);
    public static final DeferredHolder<Item, SpawnEggItem> TAKAHE_SPAWN_EGG = spawnEgg(BorealisEntities.TAKAHE,
                    0x2F329F, 0x518A65);
    public static final DeferredHolder<Item, SpawnEggItem> BUTTERBIRD_SPAWN_EGG = spawnEgg(BorealisEntities.BUTTERBIRD,
            0x3333CC, 0xF67727);
    public static final DeferredHolder<Item, SpawnEggItem> THRUSHER_SPAWN_EGG = spawnEgg(BorealisEntities.THRUSHER,
                    0xD8EBFF, 0x32749E);
    public static final DeferredHolder<Item, SpawnEggItem> TUBER_SPAWN_EGG = spawnEgg(BorealisEntities.TUBER,
            0x381D1D, 0x5A071D);

    public static <T extends LivingEntity> DeferredHolder<EntityType<?>, EntityType<T>> creature(String name, EntityType.EntityFactory<T> of, float width, float height) {
        return ENTITIES.register(name,
                () -> EntityType.Builder.of(of, MobCategory.CREATURE)
                        .sized(width, height).clientTrackingRange(10).build(name));
    }
    public static <T extends Mob> DeferredHolder<Item, SpawnEggItem> spawnEgg(DeferredHolder<EntityType<?>, EntityType<T>> mob, int background, int foreground) {
        return BorealisItems.ITEMS.register(mob.getRegisteredName().split(":")[1] + "_spawn_egg", () -> new SpawnEggItem(mob.get(), background, foreground, new Item.Properties()));
    }

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(BorealisEntities.HUMMINGBIRD.get(), HummingbirdEntity.registerAttributes().build());
        event.put(BorealisEntities.TAKAHE.get(), TakaheEntity.registerAttributes().build());
        event.put(BorealisEntities.BUTTERBIRD.get(), ButterbirdEntity.registerAttributes().build());
        event.put(BorealisEntities.TUBER.get(), TuberEntity.registerAttributes().build());
        event.put(BorealisEntities.THRUSHER.get(), ThrusherEntity.registerAttributes().build());
    }
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BorealisEntities.HUMMINGBIRD.get(), HummingbirdRenderer::new);
        event.registerEntityRenderer(BorealisEntities.TAKAHE.get(), TakaheRenderer::new);
        event.registerEntityRenderer(BorealisEntities.BUTTERBIRD.get(), ButterbirdRenderer::new);
        event.registerEntityRenderer(BorealisEntities.TUBER.get(), TuberRenderer::new);
        event.registerEntityRenderer(BorealisEntities.THRUSHER.get(), ThrusherRenderer::new);

        event.registerEntityRenderer(BorealisEntities.BOAT.get(), BorealisBoatRenderer::new);
        event.registerEntityRenderer(BorealisEntities.HAIL.get(), HailRenderer::new);
        event.registerEntityRenderer(BorealisEntities.KYANITE_ARROW.get(), KyaniteArrowRenderer::new);
        event.registerBlockEntityRenderer(BorealisBlockEntities.BOREALIS_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(BorealisBlockEntities.BOREALIS_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HummingbirdModel.LAYER_LOCATION, HummingbirdModel::createBodyLayer);
        event.registerLayerDefinition(TakaheModel.LAYER_LOCATION, TakaheModel::createBodyLayer);
        event.registerLayerDefinition(ButterbirdModel.LAYER_LOCATION, ButterbirdModel::createBodyLayer);
        event.registerLayerDefinition(TuberModel.LAYER_LOCATION, TuberModel::createBodyLayer);
        event.registerLayerDefinition(ThrusherModel.LAYER_LOCATION, ThrusherModel::createBodyLayer);

        for (BorealisBoatEntity.Type boatType : BorealisBoatEntity.Type.values()) {
            event.registerLayerDefinition(BorealisBoatRenderer.boatLayer(boatType), BoatModel::createBodyModel);
        }
    }

    public static void spawnRestrictions(RegisterSpawnPlacementsEvent event) {
        event.register(BorealisEntities.HUMMINGBIRD.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HummingbirdEntity::spawningRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(BorealisEntities.TAKAHE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TakaheEntity::spawningRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(BorealisEntities.BUTTERBIRD.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ButterbirdEntity::spawningRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(BorealisEntities.TUBER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TuberEntity::spawningRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(BorealisEntities.THRUSHER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ThrusherEntity::spawningRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityType.ZOMBIE, null, null, SpawningRules::undeadSpawningRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(EntityType.SKELETON, null, null, SpawningRules::undeadSpawningRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(EntityType.ZOMBIE_VILLAGER, null, null, SpawningRules::zombieVillagerSpawningRules, RegisterSpawnPlacementsEvent.Operation.AND);
    }
}