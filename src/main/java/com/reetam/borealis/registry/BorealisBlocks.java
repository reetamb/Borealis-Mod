package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class BorealisBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BorealisMod.MODID);

    public static final RegistryObject<FlowingFluidBlock> hot_spring_water = BLOCKS.register("hot_spring_water", () -> new HotSpringWaterBlock(
            BorealisFluids.hot_spring_water_source, AbstractBlock.Properties.of(Material.WATER)));

    public static final RegistryObject<Block> borealis_portal = BLOCKS.register("borealis_portal", BorealisPortalBlock::new);

    public static final RegistryObject<Block> soapstone = registerBlock("soapstone", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> soapstone_bricks = registerBlock("soapstone_bricks", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> soapstone_tiles = registerBlock("soapstone_tiles", () -> new Block(AbstractBlock.Properties.copy(Blocks.SMOOTH_STONE)));
    public static final RegistryObject<StairsBlock> soapstone_stairs = registerBlock("soapstone_stairs", () -> new StairsBlock(() -> BorealisBlocks.soapstone.get().defaultBlockState(), AbstractBlock.Properties.copy(BorealisBlocks.soapstone.get()).noOcclusion()));
    public static final RegistryObject<StairsBlock> soapstone_brick_stairs = registerBlock("soapstone_brick_stairs", () -> new StairsBlock(() -> BorealisBlocks.soapstone_bricks.get().defaultBlockState(), AbstractBlock.Properties.copy(BorealisBlocks.soapstone_bricks.get()).noOcclusion()));
    public static final RegistryObject<StairsBlock> soapstone_tile_stairs = registerBlock("soapstone_tile_stairs", () -> new StairsBlock(() -> BorealisBlocks.soapstone_tiles.get().defaultBlockState(), AbstractBlock.Properties.copy(BorealisBlocks.soapstone_tiles.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> soapstone_slab = registerBlock("soapstone_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BorealisBlocks.soapstone.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> soapstone_brick_slab = registerBlock("soapstone_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BorealisBlocks.soapstone_bricks.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> soapstone_tile_slab = registerBlock("soapstone_tile_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BorealisBlocks.soapstone_tiles.get()).noOcclusion()));
    public static final RegistryObject<WallBlock> soapstone_wall = registerBlock("soapstone_wall", () -> new WallBlock(AbstractBlock.Properties.copy(BorealisBlocks.soapstone.get()).noOcclusion()));
    public static final RegistryObject<WallBlock> soapstone_brick_wall = registerBlock("soapstone_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(BorealisBlocks.soapstone_bricks.get()).noOcclusion()));
    public static final RegistryObject<WallBlock> soapstone_tile_wall = registerBlock("soapstone_tile_wall", () -> new WallBlock(AbstractBlock.Properties.copy(BorealisBlocks.soapstone_tiles.get()).noOcclusion()));
    public static final RegistryObject<WoodButtonBlock> soapstone_button = registerBlock("soapstone_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(BorealisBlocks.soapstone_tiles.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> soapstone_pressure_plate = registerBlock("soapstone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(BorealisBlocks.soapstone_tiles.get()).noOcclusion().noCollission()));

    public static final RegistryObject<Block> slate = registerBlock("slate", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<RotatedPillarBlock> slate_pillar = registerBlock("slate_pillar", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> slate_tiles = registerBlock("slate_tiles", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> pumice = registerBlock("pumice", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> pumice_geyser = registerBlock("pumice_geyser", PumiceGeyserBlock::new);
    public static final RegistryObject<Block> travertine = registerBlock("travertine", () -> new Block(AbstractBlock.Properties.copy(Blocks.BASALT)));

    //    public static final RegistryObject<SaplingBlock> brumal_sapling = registerBlock("brumal_sapling", () -> new BorealisSaplingBlock(new BrumalTree()));
    public static final RegistryObject<Block> brumal_planks = registerBlock("brumal_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> brumal_log = registerBlock("brumal_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> brumal_wood = registerBlock("brumal_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> stripped_brumal_log = registerBlock("stripped_brumal_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> stripped_brumal_wood = registerBlock("stripped_brumal_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> brumal_leaves = registerBlock("brumal_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<StairsBlock> brumal_stairs = registerBlock("brumal_stairs", () -> new StairsBlock(() -> BorealisBlocks.brumal_planks.get().defaultBlockState(), AbstractBlock.Properties.copy(BorealisBlocks.brumal_planks.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> brumal_slab = registerBlock("brumal_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BorealisBlocks.brumal_planks.get()).noOcclusion()));
    public static final RegistryObject<FenceBlock> brumal_fence = registerBlock("brumal_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(BorealisBlocks.brumal_planks.get()).noOcclusion()));
    public static final RegistryObject<FenceGateBlock> brumal_fence_gate = registerBlock("brumal_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(BorealisBlocks.brumal_planks.get()).noOcclusion()));
    public static final RegistryObject<DoorBlock> brumal_door = registerBlock("brumal_door", () -> new DoorBlock(AbstractBlock.Properties.copy(BorealisBlocks.brumal_planks.get()).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> brumal_trapdoor = registerBlock("brumal_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(BorealisBlocks.brumal_planks.get()).noOcclusion()));
    public static final RegistryObject<WoodButtonBlock> brumal_button = registerBlock("brumal_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(BorealisBlocks.brumal_planks.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> brumal_pressure_plate = registerBlock("brumal_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(BorealisBlocks.brumal_planks.get()).noOcclusion().noCollission()));

    //    public static final RegistryObject<SaplingBlock> frostfir_sapling = registerBlock("frostfir_sapling", () -> new BorealisSaplingBlock(new FrostfirTree()));
    public static final RegistryObject<Block> frostfir_planks = registerBlock("frostfir_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> frostfir_log = registerBlock("frostfir_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> frostfir_wood = registerBlock("frostfir_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> stripped_frostfir_log = registerBlock("stripped_frostfir_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> stripped_frostfir_wood = registerBlock("stripped_frostfir_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> frostfir_leaves = registerBlock("frostfir_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<StairsBlock> frostfir_stairs = registerBlock("frostfir_stairs", () -> new StairsBlock(() -> BorealisBlocks.frostfir_planks.get().defaultBlockState(), AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> frostfir_slab = registerBlock("frostfir_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion()));
    public static final RegistryObject<FenceBlock> frostfir_fence = registerBlock("frostfir_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion()));
    public static final RegistryObject<FenceGateBlock> frostfir_fence_gate = registerBlock("frostfir_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion()));
    public static final RegistryObject<DoorBlock> frostfir_door = registerBlock("frostfir_door", () -> new DoorBlock(AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> frostfir_trapdoor = registerBlock("frostfir_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion()));
    public static final RegistryObject<WoodButtonBlock> frostfir_button = registerBlock("frostfir_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> frostfir_pressure_plate = registerBlock("frostfir_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion().noCollission()));

    //    public static final RegistryObject<SaplingBlock> frostfir_sapling = registerBlock("saccharine_sapling", () -> new BorealisSaplingBlock(new FrostfirTree()));
    public static final RegistryObject<Block> saccharine_planks = registerBlock("saccharine_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> saccharine_log = registerBlock("saccharine_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> saccharine_wood = registerBlock("saccharine_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> stripped_saccharine_log = registerBlock("stripped_saccharine_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> stripped_saccharine_wood = registerBlock("stripped_saccharine_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> saccharine_leaves = registerBlock("saccharine_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<StairsBlock> saccharine_stairs = registerBlock("saccharine_stairs", () -> new StairsBlock(() -> BorealisBlocks.frostfir_planks.get().defaultBlockState(), AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> saccharine_slab = registerBlock("saccharine_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion()));
    public static final RegistryObject<FenceBlock> saccharine_fence = registerBlock("saccharine_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion()));
    public static final RegistryObject<FenceGateBlock> saccharine_fence_gate = registerBlock("saccharine_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion()));
    public static final RegistryObject<DoorBlock> saccharine_door = registerBlock("saccharine_door", () -> new DoorBlock(AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> saccharine_trapdoor = registerBlock("saccharine_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion()));
    public static final RegistryObject<WoodButtonBlock> saccharine_button = registerBlock("saccharine_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> saccharine_pressure_plate = registerBlock("saccharine_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(BorealisBlocks.frostfir_planks.get()).noOcclusion().noCollission()));

    public static final RegistryObject<Block> kyanite_ore = registerBlock("kyanite_ore", () -> new Block(AbstractBlock.Properties.copy(Blocks.EMERALD_ORE)));
    public static final RegistryObject<Block> tanzanite_ore = registerBlock("tanzanite_ore", () -> new Block(AbstractBlock.Properties.copy(Blocks.DIAMOND_ORE)));
    public static final RegistryObject<Block> tanzanite_block = registerBlock("tanzanite_block", () -> new Block(AbstractBlock.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> permafrost = registerBlock("permafrost", () -> new PermafrostBlock(AbstractBlock.Properties.copy(Blocks.DIRT).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> permafrost_rubble = registerBlock("permafrost_rubble", () -> new Block(AbstractBlock.Properties.copy(Blocks.COARSE_DIRT).harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> living_snow_block = registerBlock("living_snow_block", () -> new LivingSnowBlock(AbstractBlock.Properties.copy(Blocks.SNOW_BLOCK)));
    public static final RegistryObject<Block> sugar_snow_block = registerBlock("sugar_snow_block", () -> new LivingSnowBlock(AbstractBlock.Properties.copy(Blocks.SNOW_BLOCK)));
    public static final RegistryObject<Block> sugar_snow = registerBlock("sugar_snow", () -> new SugarSnowBlock(AbstractBlock.Properties.copy(Blocks.SNOW)));
    public static final RegistryObject<Block> cloud = registerBlock("cloud", () -> new CloudBlock(AbstractBlock.Properties.copy(Blocks.WHITE_WOOL)));

    private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        BorealisItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends Block> block) {
        return (RegistryObject<T>)baseRegister(name, block, BorealisBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().tab(BorealisItems.Groups.BOREALIS_ITEMS));
    }
}