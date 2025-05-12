package com.reetam.borealis.block.kiln;

import com.reetam.borealis.registry.BorealisBlockEntities;
import com.reetam.borealis.registry.BorealisMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class KilnBlockEntity extends AbstractFurnaceBlockEntity {
    public KilnBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BorealisBlockEntities.KILN.get(), pPos, pBlockState, BorealisMenus.KILN_RECIPE.get());
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("Kiln");
    }

    protected int getBurnDuration(ItemStack fuel) {
        return super.getBurnDuration(fuel) / 2;
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new KilnMenu(i, inventory, this, this.dataAccess);
    }
}
