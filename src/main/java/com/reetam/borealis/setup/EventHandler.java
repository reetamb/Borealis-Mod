package com.reetam.borealis.setup;

import com.reetam.borealis.client.renderer.BorealisFluidRenderer;
import net.minecraftforge.eventbus.api.IEventBus;

public class EventHandler {
    public static void addEvents(IEventBus forgeBus) {
        forgeBus.addListener(BorealisFluidRenderer::fluidOverlay);
    }
}
