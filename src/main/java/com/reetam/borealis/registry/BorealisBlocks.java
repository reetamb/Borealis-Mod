package com.reetam.borealis.registry;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.reetam.borealis.BorealisMod;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class BorealisBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BorealisMod.MODID);

    public static final RegistryObject<Block> soapstone = registerBlock("soapstone", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)));
    public static final RegistryObject<Block> polished_soapstone = registerBlock("polished_soapstone", () -> new Block(AbstractBlock.Properties.from(Blocks.SMOOTH_STONE)));
    private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        BorealisItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends Block> block) {
        return (RegistryObject<T>)baseRegister(name, block, BorealisBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().group(BorealisItemGroups.BLOCKS_GROUP));
    }
}