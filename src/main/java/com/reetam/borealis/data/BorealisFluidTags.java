package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class BorealisFluidTags extends FluidTagsProvider {

    public BorealisFluidTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, BorealisMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Borealis Fluid Tags";
    }

    @Override
    protected void addTags() {
        tag(FluidTags.WATER)
                .add(BorealisFluids.HOT_SPRING_WATER_SOURCE.get());
    }

    protected TagsProvider.TagAppender<Fluid> tag(TagKey<Fluid> tag) {
        return super.tag(tag);
    }
}
