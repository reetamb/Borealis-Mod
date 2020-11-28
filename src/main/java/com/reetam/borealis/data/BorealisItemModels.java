package com.reetam.borealis.data;

import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.data.provider.BorealisItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BorealisItemModels extends BorealisItemModelProvider {

    public BorealisItemModels(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    protected void registerModels() {
        itemBlock(BorealisBlocks.frostfir_planks);
    }
}
