package com.reetam.borealis;

import com.google.common.collect.ImmutableMap;
import com.reetam.borealis.item.BorealisItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.data.DataGenerator;
import net.minecraft.dispenser.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.reetam.borealis.registry.*;

import java.util.HashMap;
import java.util.Map;

@Mod(BorealisMod.MODID)
public class BorealisMod {

    public static final String MODID = "borealis";

    public BorealisMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

//        UGEntityTypes.ENTITIES.register(bus);
        BorealisBlocks.BLOCKS.register(bus);
        BorealisItems.ITEMS.register(bus);
//        UGFeatures.FEATURES.register(bus);
//        UGCarvers.CARVERS.register(bus);
//        UGEffects.EFFECTS.register(bus);
//        UGPotions.POTIONS.register(bus);
//        UGFluids.FLUIDS.register(bus);
//        UGParticleTypes.PARTICLES.register(bus);
//        UGTileEntities.TEs.register(bus);
//        UGStructures.STRUCTURES.register(bus);
    }
}