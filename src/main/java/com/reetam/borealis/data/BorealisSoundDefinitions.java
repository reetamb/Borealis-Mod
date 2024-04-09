package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisSounds;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class BorealisSoundDefinitions extends SoundDefinitionsProvider {
    public BorealisSoundDefinitions(PackOutput output, ExistingFileHelper helper) {
        super(output, BorealisMod.MODID, helper);
    }

    @Override
    public void registerSounds() {
        add(BorealisSounds.BOREALIS_PORTAL_CHIME, definition()
                .with(sound("borealis:block/borealis_portal"))
                .subtitle("Borealis Portal opens"));
        add(BorealisSounds.HAILSTONE_FALL, definition()
                .with(sound("minecraft:block/bell/resonate"))
                .subtitle("Hailstone echoes"));
    }
}
