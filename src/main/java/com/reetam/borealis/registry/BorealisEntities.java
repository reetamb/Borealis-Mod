package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.*;
import com.reetam.borealis.entity.model.HummingbirdModel;
import com.reetam.borealis.entity.model.TakaheModel;
import com.reetam.borealis.entity.model.ThrusherModel;
import com.reetam.borealis.entity.renderer.*;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BorealisEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, BorealisMod.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<BorealisBoatEntity>> BOAT = ENTITIES.register("boat",
            () -> EntityType.Builder.<BorealisBoatEntity>of(BorealisBoatEntity::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));

    public static final DeferredHolder<EntityType<?>, EntityType<HummingbirdEntity>> HUMMINGBIRD = ENTITIES.register("hummingbird",
            () -> EntityType.Builder.of(HummingbirdEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 0.9F).clientTrackingRange(10).build("hummingbird"));
    public static final DeferredHolder<EntityType<?>, EntityType<TakaheEntity>> TAKAHE = ENTITIES.register("takahe",
            () -> EntityType.Builder.of(TakaheEntity::new, MobCategory.CREATURE)
                    .sized(0.8F, 0.8F).clientTrackingRange(10).build("takahe"));
    public static final DeferredHolder<EntityType<?>, EntityType<ThrusherEntity>> THRUSHER = ENTITIES.register("thrusher",
            () ->  EntityType.Builder.of(ThrusherEntity::new, MobCategory.CREATURE)
                    .sized(1.4F, 1.4F).clientTrackingRange(10).build("thrusher"));
    public static final DeferredHolder<EntityType<?>, EntityType<HailEntity>> HAIL = ENTITIES.register("hail",
            () -> EntityType.Builder.of(HailEntity::new, MobCategory.MISC)
                    .sized(1.0F, 1.0F).clientTrackingRange(10).build("hail"));
    public static final DeferredHolder<EntityType<?>, EntityType<KyaniteArrowEntity>> KYANITE_ARROW = ENTITIES.register("kyanite_arrow",
            () -> EntityType.Builder.of(KyaniteArrowEntity::new, MobCategory.MISC)
                    .sized(1.0F, 1.0F).clientTrackingRange(10).build("kyanite_arrow"));

    public static void registerSpawnPlacements() {
        // SpawnPlacements.register(HUMMINGBIRD.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HummingbirdEntity::checkHummingbirdSpawnRules);
        // SpawnPlacements.register(TAKAHE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TakaheEntity::checkTakaheSpawnRules);
    }

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(BorealisEntities.HUMMINGBIRD.get(), HummingbirdEntity.registerAttributes().build());
        event.put(BorealisEntities.TAKAHE.get(), TakaheEntity.registerAttributes().build());
        event.put(BorealisEntities.THRUSHER.get(), ThrusherEntity.registerAttributes().build());
    }
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BorealisEntities.HUMMINGBIRD.get(), HummingbirdRenderer::new);
        event.registerEntityRenderer(BorealisEntities.TAKAHE.get(), TakaheRenderer::new);
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
        event.registerLayerDefinition(ThrusherModel.LAYER_LOCATION, ThrusherModel::createBodyLayer);

        for (BorealisBoatEntity.Type boatType : BorealisBoatEntity.Type.values()) {
            event.registerLayerDefinition(BorealisBoatRenderer.boatLayer(boatType), BoatModel::createBodyModel);
        }
    }
}