package com.reetam.borealis.registry;

import com.google.common.collect.Maps;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.*;
import com.reetam.borealis.world.gen.tree.BrumalTree;
import com.reetam.borealis.world.gen.tree.FrostfirTree;
import com.reetam.borealis.world.gen.tree.SaccharineTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class BorealisBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BorealisMod.MODID);

    public static final WoodType BRUMAL_WOODTYPE = WoodType.create(new ResourceLocation(BorealisMod.MODID, "brumal").toString());
    public static final WoodType FROSTFIR_WOODTYPE = WoodType.create(new ResourceLocation(BorealisMod.MODID, "frostfir").toString());
    public static final WoodType SACCHARINE_WOODTYPE = WoodType.create(new ResourceLocation(BorealisMod.MODID, "saccharine").toString());

    public static final RegistryObject<FlowingFluidBlock> HOT_SPRING_WATER = BLOCKS.register("hot_spring_water", () -> new HotSpringWaterBlock(
            BorealisFluids.hot_spring_water_source, AbstractBlock.Properties.of(Material.WATER)));

    public static final RegistryObject<Block> BOREALIS_PORTAL = BLOCKS.register("borealis_portal", BorealisPortalBlock::new);

    public static final RegistryObject<Block> SOAPSTONE = registerBlock("soapstone", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> SOAPSTONE_BRICKS = registerBlock("soapstone_bricks", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> SOAPSTONE_TILES = registerBlock("soapstone_tiles", () -> new Block(AbstractBlock.Properties.copy(Blocks.SMOOTH_STONE)));
    public static final RegistryObject<StairsBlock> SOAPSTONE_STAIRS = registerBlock("soapstone_stairs", () -> new StairsBlock(() -> BorealisBlocks.SOAPSTONE.get().defaultBlockState(), AbstractBlock.Properties.copy(BorealisBlocks.SOAPSTONE.get()).noOcclusion()));
    public static final RegistryObject<StairsBlock> SOAPSTONE_BRICK_STAIRS = registerBlock("soapstone_brick_stairs", () -> new StairsBlock(() -> BorealisBlocks.SOAPSTONE_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.copy(BorealisBlocks.SOAPSTONE_BRICKS.get()).noOcclusion()));
    public static final RegistryObject<StairsBlock> SOAPSTONE_TILE_STAIRS = registerBlock("soapstone_tile_stairs", () -> new StairsBlock(() -> BorealisBlocks.SOAPSTONE_TILES.get().defaultBlockState(), AbstractBlock.Properties.copy(BorealisBlocks.SOAPSTONE_TILES.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> SOAPSTONE_SLAB = registerBlock("soapstone_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BorealisBlocks.SOAPSTONE.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> SOAPSTONE_BRICK_SLAB = registerBlock("soapstone_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BorealisBlocks.SOAPSTONE_BRICKS.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> SOAPSTONE_TILE_SLAB = registerBlock("soapstone_tile_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BorealisBlocks.SOAPSTONE_TILES.get()).noOcclusion()));
    public static final RegistryObject<WallBlock> SOAPSTONE_WALL = registerBlock("soapstone_wall", () -> new WallBlock(AbstractBlock.Properties.copy(BorealisBlocks.SOAPSTONE.get()).noOcclusion()));
    public static final RegistryObject<WallBlock> SOAPSTONE_BRICK_WALL = registerBlock("soapstone_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(BorealisBlocks.SOAPSTONE_BRICKS.get()).noOcclusion()));
    public static final RegistryObject<WallBlock> SOAPSTONE_TILE_WALL = registerBlock("soapstone_tile_wall", () -> new WallBlock(AbstractBlock.Properties.copy(BorealisBlocks.SOAPSTONE_TILES.get()).noOcclusion()));
    public static final RegistryObject<WoodButtonBlock> SOAPSTONE_BUTTON = registerBlock("soapstone_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(BorealisBlocks.SOAPSTONE_TILES.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> SOAPSTONE_PRESSURE_PLATE = registerBlock("soapstone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(BorealisBlocks.SOAPSTONE_TILES.get()).noOcclusion().noCollission()));

    public static final RegistryObject<Block> SLATE = registerBlock("slate", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<RotatedPillarBlock> SLATE_PILLAR = registerBlock("slate_pillar", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> SLATE_TILES = registerBlock("slate_tiles", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> PUMICE = registerBlock("pumice", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> PUMICE_GEYSER = registerBlock("pumice_geyser", PumiceGeyserBlock::new);
    public static final RegistryObject<Block> TRAVERTINE = registerBlock("travertine", () -> new Block(AbstractBlock.Properties.copy(Blocks.BASALT)));

    public static final RegistryObject<Block> PETRIFIED_WOOD = registerBlock("petrified_wood", () -> new Block(AbstractBlock.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> PETRIFIED_WOOD_BRICKS = registerBlock("petrified_wood_bricks", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<StandingSignBlock> BRUMAL_SIGN = BLOCKS.register("brumal_sign", () -> new BorealisStandingSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_SIGN), BRUMAL_WOODTYPE));
    public static final RegistryObject<WallSignBlock> BRUMAL_WALL_SIGN = BLOCKS.register("brumal_wall_sign", () -> new BorealisWallSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_WALL_SIGN), BRUMAL_WOODTYPE));
    public static final RegistryObject<SaplingBlock> BRUMAL_SAPLING = registerBlock("brumal_sapling", () -> new BorealisSaplingBlock(new BrumalTree()));
    public static final RegistryObject<FlowerPotBlock> POTTED_BRUMAL_SAPLING = BLOCKS.register("potted_brumal_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BRUMAL_SAPLING, AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> BRUMAL_PLANKS = registerBlock("brumal_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> BRUMAL_LOG = registerBlock("brumal_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> BRUMAL_WOOD = registerBlock("brumal_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_BRUMAL_LOG = registerBlock("stripped_brumal_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_BRUMAL_WOOD = registerBlock("stripped_brumal_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> BRUMAL_LEAVES = registerBlock("brumal_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<StairsBlock> BRUMAL_STAIRS = registerBlock("brumal_stairs", () -> new StairsBlock(() -> BorealisBlocks.BRUMAL_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(BorealisBlocks.BRUMAL_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> BRUMAL_SLAB = registerBlock("brumal_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BorealisBlocks.BRUMAL_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<FenceBlock> BRUMAL_FENCE = registerBlock("brumal_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(BorealisBlocks.BRUMAL_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<FenceGateBlock> BRUMAL_FENCE_GATE = registerBlock("brumal_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(BorealisBlocks.BRUMAL_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<DoorBlock> BRUMAL_DOOR = registerBlock("brumal_door", () -> new DoorBlock(AbstractBlock.Properties.copy(BorealisBlocks.BRUMAL_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> BRUMAL_TRAPDOOR = registerBlock("brumal_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(BorealisBlocks.BRUMAL_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<WoodButtonBlock> BRUMAL_BUTTON = registerBlock("brumal_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(BorealisBlocks.BRUMAL_PLANKS.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> BRUMAL_PRESSURE_PLATE = registerBlock("brumal_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(BorealisBlocks.BRUMAL_PLANKS.get()).noOcclusion().noCollission()));

    public static final RegistryObject<StandingSignBlock> FROSTFIR_SIGN = BLOCKS.register("frostfir_sign", () -> new BorealisStandingSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_SIGN), FROSTFIR_WOODTYPE));
    public static final RegistryObject<WallSignBlock> FROSTFIR_WALL_SIGN = BLOCKS.register("frostfir_wall_sign", () -> new BorealisWallSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_WALL_SIGN), FROSTFIR_WOODTYPE));
    public static final RegistryObject<SaplingBlock> FROSTFIR_SAPLING = registerBlock("frostfir_sapling", () -> new BorealisSaplingBlock(new FrostfirTree()));
    public static final RegistryObject<FlowerPotBlock> POTTED_FROSTFIR_SAPLING = BLOCKS.register("potted_frostfir_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, FROSTFIR_SAPLING, AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> FROSTFIR_PLANKS = registerBlock("frostfir_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> FROSTFIR_LOG = registerBlock("frostfir_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> FROSTFIR_WOOD = registerBlock("frostfir_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_FROSTFIR_LOG = registerBlock("stripped_frostfir_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_FROSTFIR_WOOD = registerBlock("stripped_frostfir_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> FROSTFIR_LEAVES = registerBlock("frostfir_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<StairsBlock> FROSTFIR_STAIRS = registerBlock("frostfir_stairs", () -> new StairsBlock(() -> BorealisBlocks.FROSTFIR_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> FROSTFIR_SLAB = registerBlock("frostfir_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<FenceBlock> FROSTFIR_FENCE = registerBlock("frostfir_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<FenceGateBlock> FROSTFIR_FENCE_GATE = registerBlock("frostfir_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<DoorBlock> FROSTFIR_DOOR = registerBlock("frostfir_door", () -> new DoorBlock(AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> FROSTFIR_TRAPDOOR = registerBlock("frostfir_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<WoodButtonBlock> FROSTFIR_BUTTON = registerBlock("frostfir_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> FROSTFIR_PRESSURE_PLATE = registerBlock("frostfir_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion().noCollission()));

    public static final RegistryObject<StandingSignBlock> SACCHARINE_SIGN = BLOCKS.register("saccharine_sign", () -> new BorealisStandingSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_SIGN), SACCHARINE_WOODTYPE));
    public static final RegistryObject<WallSignBlock> SACCHARINE_WALL_SIGN = BLOCKS.register("saccharine_wall_sign", () -> new BorealisWallSignBlock(AbstractBlock.Properties.copy(Blocks.OAK_WALL_SIGN), SACCHARINE_WOODTYPE));
    public static final RegistryObject<SaplingBlock> SACCHARINE_SAPLING = registerBlock("saccharine_sapling", () -> new BorealisSaplingBlock(new SaccharineTree()));
    public static final RegistryObject<FlowerPotBlock> POTTED_SACCHARINE_SAPLING = BLOCKS.register("potted_saccharine_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SACCHARINE_SAPLING, AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> SACCHARINE_PLANKS = registerBlock("saccharine_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> SACCHARINE_LOG = registerBlock("saccharine_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> SACCHARINE_WOOD = registerBlock("saccharine_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SACCHARINE_LOG = registerBlock("stripped_saccharine_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SACCHARINE_WOOD = registerBlock("stripped_saccharine_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> SACCHARINE_LEAVES = registerBlock("saccharine_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<StairsBlock> SACCHARINE_STAIRS = registerBlock("saccharine_stairs", () -> new StairsBlock(() -> BorealisBlocks.FROSTFIR_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<SlabBlock> SACCHARINE_SLAB = registerBlock("saccharine_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<FenceBlock> SACCHARINE_FENCE = registerBlock("saccharine_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<FenceGateBlock> SACCHARINE_FENCE_GATE = registerBlock("saccharine_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<DoorBlock> SACCHARINE_DOOR = registerBlock("saccharine_door", () -> new DoorBlock(AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> SACCHARINE_TRAPDOOR = registerBlock("saccharine_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<WoodButtonBlock> SACCHARINE_BUTTON = registerBlock("saccharine_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion().noCollission()));
    public static final RegistryObject<PressurePlateBlock> SACCHARINE_PRESSURE_PLATE = registerBlock("saccharine_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(BorealisBlocks.FROSTFIR_PLANKS.get()).noOcclusion().noCollission()));

    public static final RegistryObject<Block> KYANITE_ORE = registerBlock("kyanite_ore", () -> new Block(AbstractBlock.Properties.copy(Blocks.EMERALD_ORE)));
    public static final RegistryObject<Block> TANZANITE_ORE = registerBlock("tanzanite_ore", () -> new Block(AbstractBlock.Properties.copy(Blocks.DIAMOND_ORE)));
    public static final RegistryObject<Block> TANZANITE_BLOCK = registerBlock("tanzanite_block", () -> new TanzaniteBlock(AbstractBlock.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> PERMAFROST = registerBlock("permafrost", () -> new PermafrostBlock(AbstractBlock.Properties.copy(Blocks.DIRT).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> PERMAFROST_RUBBLE = registerBlock("permafrost_rubble", () -> new Block(AbstractBlock.Properties.copy(Blocks.COARSE_DIRT).harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> LIVING_SNOW_BLOCK = registerBlock("living_snow_block", () -> new LivingSnowBlock(AbstractBlock.Properties.copy(Blocks.SNOW_BLOCK)));
    public static final RegistryObject<Block> SUGAR_SNOW_BLOCK = registerBlock("sugar_snow_block", () -> new LivingSnowBlock(AbstractBlock.Properties.copy(Blocks.SNOW_BLOCK)));
    public static final RegistryObject<Block> SUGAR_SNOW = registerBlock("sugar_snow", () -> new SugarSnowBlock(AbstractBlock.Properties.copy(Blocks.SNOW)));
    public static final RegistryObject<Block> CLOUD = registerBlock("cloud", () -> new CloudBlock(AbstractBlock.Properties.copy(Blocks.WHITE_WOOL)));

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

    public static void registerWoodTypes() {
        WoodType.register(BorealisBlocks.BRUMAL_WOODTYPE);
        WoodType.register(BorealisBlocks.FROSTFIR_WOODTYPE);
        WoodType.register(BorealisBlocks.SACCHARINE_WOODTYPE);
    }

    public static void registerFlowerPots() {
        FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;

        pot.addPlant(BRUMAL_SAPLING.getId(), POTTED_BRUMAL_SAPLING);
        pot.addPlant(FROSTFIR_SAPLING.getId(), POTTED_FROSTFIR_SAPLING);
        pot.addPlant(SACCHARINE_SAPLING.getId(), POTTED_SACCHARINE_SAPLING);
    }

    public static void registerAxeStrips() {
        AxeItem.STRIPABLES = Maps.newHashMap(AxeItem.STRIPABLES);

        AxeItem.STRIPABLES.put(BRUMAL_LOG.get(), STRIPPED_BRUMAL_LOG.get());
        AxeItem.STRIPABLES.put(BRUMAL_WOOD.get(), STRIPPED_BRUMAL_WOOD.get());
        AxeItem.STRIPABLES.put(FROSTFIR_LOG.get(), STRIPPED_FROSTFIR_LOG.get());
        AxeItem.STRIPABLES.put(FROSTFIR_WOOD.get(), STRIPPED_FROSTFIR_WOOD.get());
        AxeItem.STRIPABLES.put(SACCHARINE_LOG.get(), STRIPPED_SACCHARINE_LOG.get());
        AxeItem.STRIPABLES.put(SACCHARINE_WOOD.get(), STRIPPED_SACCHARINE_WOOD.get());
    }

    public static void registerHoeTills() {
        HoeItem.TILLABLES = Maps.newHashMap(HoeItem.TILLABLES);

        HoeItem.TILLABLES.put(PERMAFROST_RUBBLE.get(), PERMAFROST.get().defaultBlockState());
    }

    public static void registerComposts() {
        ComposterBlock.add(0.3F, BorealisBlocks.BRUMAL_LEAVES.get());
        ComposterBlock.add(0.3F, BorealisBlocks.FROSTFIR_LEAVES.get());
        ComposterBlock.add(0.3F, BorealisBlocks.SACCHARINE_LEAVES.get());
        ComposterBlock.add(0.3F, BorealisBlocks.BRUMAL_SAPLING.get());
        ComposterBlock.add(0.3F, BorealisBlocks.FROSTFIR_SAPLING.get());
        ComposterBlock.add(0.3F, BorealisBlocks.SACCHARINE_SAPLING.get());
    }
}