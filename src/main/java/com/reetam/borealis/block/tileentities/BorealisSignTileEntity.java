package com.reetam.borealis.block.tileentities;

import com.reetam.borealis.registry.BorealisTileEntities;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class BorealisSignTileEntity extends SignTileEntity {
    @Override
    public TileEntityType<?> getType() {
        return BorealisTileEntities.BOREALIS_SIGN.get();
    }
}
