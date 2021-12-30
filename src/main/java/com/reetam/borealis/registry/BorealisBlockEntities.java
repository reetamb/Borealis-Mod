package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.entity.BorealisSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class BorealisBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, BorealisMod.MODID);

    public static final RegistryObject<BlockEntityType<BorealisSignBlockEntity>> BOREALIS_SIGN = BLOCK_ENTITIES.register("undergarden_sign", () ->
            BlockEntityType.Builder.of(BorealisSignBlockEntity::new,
                    BorealisBlocks.BRUMAL_SIGN.get(),
                    BorealisBlocks.BRUMAL_WALL_SIGN.get(),
                    BorealisBlocks.FROSTFIR_SIGN.get(),
                    BorealisBlocks.FROSTFIR_WALL_SIGN.get(),
                    BorealisBlocks.SACCHARINE_SIGN.get(),
                    BorealisBlocks.SACCHARINE_WALL_SIGN.get()
            ).build(null));
}