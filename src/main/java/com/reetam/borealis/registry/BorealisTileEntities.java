package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
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
                    BorealisBlocks.TANZANITE_BLOCK.get())
                    .build(null));

}
