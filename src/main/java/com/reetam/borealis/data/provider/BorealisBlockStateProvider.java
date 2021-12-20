package com.reetam.borealis.data.provider;

import com.reetam.borealis.BorealisMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public abstract class BorealisBlockStateProvider extends BlockStateProvider {

    public BorealisBlockStateProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, BorealisMod.MODID, fileHelper);
    }

    protected ResourceLocation texture(String name) {
        return modLoc("block/" + name);
    }

    protected String name(Supplier<? extends Block> block) {
        return block.get().getRegistryName().getPath();
    }

    public void block(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    public void log(Supplier<? extends RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), texture(name));
    }

    public void wood(Supplier<? extends Block> block, Supplier<? extends RotatedPillarBlock> baseBlock) {
        ConfiguredModel wood = new ConfiguredModel(models().cubeAll(name(block), texture(name(baseBlock) + "_side")));
        getVariantBuilder(block.get()).partialState().setModels(wood);
    }

    private void crossBlock(Supplier<? extends Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state ->
                ConfiguredModel.builder()
                        .modelFile(model)
                        .build());
    }

    public void crossBlock(Supplier<? extends Block> block) {
        crossBlock(block, models().cross(name(block), texture(name(block))));
    }

    public void stairs(Supplier<? extends StairBlock> block, Supplier<? extends Block> fullBlock) {
        stairsBlock(block.get(), texture(name(fullBlock)));
    }

    public void slab(Supplier<? extends SlabBlock> block, Supplier<? extends Block> fullBlock) {
        slabBlock(block.get(), texture(name(fullBlock)), texture(name(fullBlock)));
    }

    public void fence(Supplier<? extends FenceBlock> block, Supplier<? extends Block> fullBlock) {
        fenceBlock(block.get(), texture(name(fullBlock)));
        fenceColumn(block, name(fullBlock));
    }

    private void fenceColumn(Supplier<? extends FenceBlock> block, String side) {
        String baseName = block.get().getRegistryName().toString();
        fourWayBlock(block.get(),
                models().fencePost(baseName + "_post", texture(side)),
                models().fenceSide(baseName + "_side", texture(side)));
    }

    public void door(Supplier<? extends DoorBlock> block, String name) {
        doorBlock(block.get(), texture(name + "_door_bottom"), texture(name + "_door_top"));
    }

    public void trapdoor(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoorBlock(block.get(), texture(name + "_trapdoor"), true);
    }
}