package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import com.reetam.borealis.data.provider.BorealisBlockstateProvider;

public class BorealisBlockstates extends BorealisBlockstateProvider {

    public BorealisBlockstates(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        block(BorealisBlocks.cloud);
    }
}
