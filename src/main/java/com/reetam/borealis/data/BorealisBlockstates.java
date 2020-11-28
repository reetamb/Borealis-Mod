package com.reetam.borealis.data;

import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import com.reetam.borealis.data.provider.BorealisBlockstateProvider;

public class BorealisBlockstates extends BorealisBlockstateProvider {

    public BorealisBlockstates(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //
    }
}
