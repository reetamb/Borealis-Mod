package com.reetam.borealis.block;

import com.reetam.borealis.block.tileentities.BorealisSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BorealisWallSignBlock extends WallSignBlock {

    public BorealisWallSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new BorealisSignTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
