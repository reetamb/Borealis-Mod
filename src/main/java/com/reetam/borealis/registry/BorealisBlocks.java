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

    public static final RegistryObject<Block> slate = registerBlock("slate", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)));
    public static final RegistryObject<Block> polished_slate = registerBlock("polished_slate", () -> new Block(AbstractBlock.Properties.from(Blocks.SMOOTH_STONE)));
    public static final RegistryObject<Block> kyanite_ore = registerBlock("kyanite_ore", () -> new Block(OreBlock.Properties.from(Blocks.EMERALD_ORE)));
    public static final RegistryObject<Block> slate_bricks = registerBlock("slate_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE_BRICKS)));

    public static final RegistryObject<StairsBlock> slate_stairs = registerBlock("slate_stairs", () -> new StairsBlock(() -> BorealisBlocks.slate.get().getDefaultState(), AbstractBlock.Properties.from(BorealisBlocks.slate.get()).notSolid()));
    public static final RegistryObject<StairsBlock> polished_slate_stairs = registerBlock("polished_slate_stairs", () -> new StairsBlock(() -> BorealisBlocks.polished_slate.get().getDefaultState(), AbstractBlock.Properties.from(BorealisBlocks.polished_slate.get()).notSolid()));
    public static final RegistryObject<StairsBlock> slate_brick_stairs = registerBlock("slate_brick_stairs", () -> new StairsBlock(() -> BorealisBlocks.slate_bricks.get().getDefaultState(), AbstractBlock.Properties.from(BorealisBlocks.slate_bricks.get()).notSolid()));

    public static final RegistryObject<SlabBlock> slate_slab = registerBlock("slate_slab", () -> new SlabBlock(AbstractBlock.Properties.from(BorealisBlocks.slate.get()).notSolid()));
    public static final RegistryObject<SlabBlock> polished_slate_slab = registerBlock("polished_slate_slab", () -> new SlabBlock(AbstractBlock.Properties.from(BorealisBlocks.polished_slate.get()).notSolid()));
    public static final RegistryObject<SlabBlock> slate_brick_slab = registerBlock("slate_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(BorealisBlocks.slate_bricks.get()).notSolid()));

    public static final RegistryObject<WallBlock> slate_wall = registerBlock("slate_wall", () -> new WallBlock(AbstractBlock.Properties.from(BorealisBlocks.slate.get()).notSolid()));
    public static final RegistryObject<WallBlock> polished_slate_wall = registerBlock("polished_slate_wall", () -> new WallBlock(AbstractBlock.Properties.from(BorealisBlocks.polished_slate.get()).notSolid()));
    public static final RegistryObject<WallBlock> slate_brick_wall = registerBlock("slate_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(BorealisBlocks.slate_bricks.get()).notSolid()));


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