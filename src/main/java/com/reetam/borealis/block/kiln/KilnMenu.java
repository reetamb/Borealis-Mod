package com.reetam.borealis.block.kiln;

import com.reetam.borealis.registry.BorealisMenus;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class KilnMenu extends AbstractFurnaceMenu {
    public KilnMenu(int id, Inventory inventory) {
        super(BorealisMenus.KILN_MENU.get(), BorealisMenus.KILN_RECIPE.get(), BorealisMenus.KILN, id, inventory);
    }

    public KilnMenu(int id, Inventory inventory, Container container, ContainerData data) {
        super(BorealisMenus.KILN_MENU.get(), BorealisMenus.KILN_RECIPE.get(), BorealisMenus.KILN, id, inventory, container, data);
    }
}
