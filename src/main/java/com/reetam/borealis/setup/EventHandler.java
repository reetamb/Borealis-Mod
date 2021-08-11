package com.reetam.borealis.setup;

import com.reetam.borealis.client.renderer.fluid.FluidRenderer;
import net.minecraftforge.eventbus.api.IEventBus;

public class EventHandler {
    public static void addEvents(IEventBus forgeBus) {
        forgeBus.addListener(FluidRenderer::fluidOverlay);
    }
}
