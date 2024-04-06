package com.reetam.borealis.data;

import com.reetam.borealis.data.provider.BorealisLangProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraftforge.registries.RegistryObject;

public class BorealisLang extends BorealisLangProvider {

    public BorealisLang(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        creativeTab(BorealisItems.Tabs.BOREALIS_ITEMS, "Borealis Items");

        for (RegistryObject<Block> block : BorealisBlocks.BLOCKS.getEntries()) {
            if (!(block.get() instanceof WallSignBlock)) {
                assumeBlockItem(block);
            }
        }
        assumeBlockItem(BorealisItems.KYANITE_CRYSTAL);
        assumeBlockItem(BorealisItems.MOON_PEARL);
        assumeBlockItem(BorealisItems.HOT_SPRING_WATER_BUCKET);
        assumeBlockItem(BorealisItems.HUMMINGBIRD_SPAWN_EGG);
        assumeBlockItem(BorealisItems.TAKAHE_SPAWN_EGG);
        assumeBlockItem(BorealisItems.THRUSHER_SPAWN_EGG);
        assumeBlockItem(BorealisItems.TANZANITE);
        assumeBlockItem(BorealisItems.BRUMAL_BOAT);
        assumeBlockItem(BorealisItems.FROSTFIR_BOAT);
        assumeBlockItem(BorealisItems.SACCHARINE_BOAT);
        assumeBlockItem(BorealisItems.HAT);

    }
}
