package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.tileentities.BorealisSignTileEntity;
import com.reetam.borealis.block.tileentities.TanzaniteBlockTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BorealisTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, BorealisMod.MODID);

    public static final RegistryObject<TileEntityType<TanzaniteBlockTileEntity>> TANZANITE_BLOCK = TILE_ENTITIES.register(
            "tanzanite_block",
            () -> TileEntityType.Builder.of(
                    TanzaniteBlockTileEntity::new,
                    BorealisBlocks.TANZANITE_BLOCK.get()
            ).build(null));
    public static final RegistryObject<TileEntityType<BorealisSignTileEntity>> BOREALIS_SIGN = TILE_ENTITIES.register(
            "borealis_sign",
            () -> TileEntityType.Builder.of(
                    BorealisSignTileEntity::new,
                    BorealisBlocks.BRUMAL_SIGN.get(),
                    BorealisBlocks.BRUMAL_WALL_SIGN.get(),
                    BorealisBlocks.FROSTFIR_SIGN.get(),
                    BorealisBlocks.FROSTFIR_WALL_SIGN.get(),
                    BorealisBlocks.SACCHARINE_SIGN.get(),
                    BorealisBlocks.SACCHARINE_WALL_SIGN.get()
            ).build(null));
}
