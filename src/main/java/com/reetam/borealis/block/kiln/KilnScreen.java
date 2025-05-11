package com.reetam.borealis.block.kiln;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KilnScreen extends AbstractFurnaceScreen<KilnMenu> {
    private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/blast_furnace/lit_progress");
    private static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/blast_furnace/burn_progress");
    private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/container/blast_furnace.png");

    public KilnScreen(KilnMenu menu, Inventory inventory, Component component) {
        super(menu, new KilnRecipeBookComponent(), inventory, component, TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
    }
}
