package com.reetam.borealis.registry;

import com.reetam.borealis.client.model.MismicMuskoxModel;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.*;

public class BorealisEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, BorealisMod.MODID);

    public static final EntityType<HummingbirdEntity> hummingbird = EntityType.Builder.<HummingbirdEntity>create(HummingbirdEntity::new, EntityClassification.CREATURE)
            .size(0.9F, 0.9F).trackingRange(10).build("hummingbird");
    public static final EntityType<TakaheEntity> takahe = EntityType.Builder.<TakaheEntity>create(TakaheEntity::new, EntityClassification.CREATURE)
            .size(0.8F, 0.8F).trackingRange(10).build("takahe");
    public static final EntityType<MismicMuskoxEntity> mismic_muskox = EntityType.Builder.<MismicMuskoxEntity>create(MismicMuskoxEntity::new, EntityClassification.CREATURE)
            .size(1.0F, 1.4F).trackingRange(10).build("mismic_muskox");

    public static final RegistryObject<EntityType<HummingbirdEntity>> HUMMINGBIRD = ENTITIES.register(
            "hummingbird", () -> hummingbird);
    public static final  RegistryObject<EntityType<TakaheEntity>> TAKAHE = ENTITIES.register(
            "takahe", () -> takahe);
    public static final  RegistryObject<EntityType<MismicMuskoxEntity>> MISMIC_MUSKOX = ENTITIES.register(
            "mismic_muskox", () -> mismic_muskox);

    public static void spawnPlacements() {
        EntitySpawnPlacementRegistry.register(HUMMINGBIRD.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HummingbirdEntity::canHummingbirdSpawn);
        EntitySpawnPlacementRegistry.register(TAKAHE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, TakaheEntity::canTakaheSpawn);
        EntitySpawnPlacementRegistry.register(MISMIC_MUSKOX.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MismicMuskoxEntity::canMuskoxSpawn);
    }

    public static void entityAttributes() {
        GlobalEntityTypeAttributes.put(HUMMINGBIRD.get(), HummingbirdEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(TAKAHE.get(), TakaheEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(MISMIC_MUSKOX.get(), MismicMuskoxEntity.registerAttributes().create());
    }
}