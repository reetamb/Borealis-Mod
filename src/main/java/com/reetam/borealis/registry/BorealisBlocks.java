package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.*;
import com.reetam.borealis.block.entity.*;
import com.reetam.borealis.world.tree.Grower;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class BorealisBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, BorealisMod.MODID);

    public static final BlockSetType SET_BRUMAL = new BlockSetType(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "brumal").toString());
    public static final BlockSetType SET_FROSTFIR = new BlockSetType(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "frostfir").toString());
    public static final BlockSetType SET_SWEETWOOD = new BlockSetType(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "sweetwood").toString());
    public static final BlockSetType SET_CARAMELIZED = new BlockSetType(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "caramelized").toString());
    public static final BlockSetType SET_SOAPSTONE = new BlockSetType(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "soapstone").toString());

    public static final WoodType WOODSET_BRUMAL = WoodType.register(new WoodType(SET_BRUMAL.name(), SET_BRUMAL));
    public static final WoodType WOODSET_FROSTFIR = WoodType.register(new WoodType(SET_FROSTFIR.name(), SET_FROSTFIR));
    public static final WoodType WOODSET_SWEETWOOD = WoodType.register(new WoodType(SET_SWEETWOOD.name(), SET_SWEETWOOD));
    public static final WoodType WOODSET_CARAMELIZED = WoodType.register(new WoodType(SET_CARAMELIZED.name(), SET_CARAMELIZED));

    public static final DeferredHolder<Block, Block> BOREALIS_PORTAL = BLOCKS.register("borealis_portal", BorealisPortalBlock::new);
    public static final DeferredHolder<Block, RotatedPillarBlock> KYANITE_FLAGSTONE = registerBlock("kyanite_flagstone", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.AMETHYST).destroyTime(40.0F)));

    public static final DeferredHolder<Block, Block> SOAPSTONE = registerBlock("soapstone", () -> new Block(Template.soapstone()));
    public static final DeferredHolder<Block, Block> SOAPSTONE_BRICKS = registerBlock("soapstone_bricks", () -> new Block(Template.soapstone()));
    public static final DeferredHolder<Block, Block> SOAPSTONE_TILES = registerBlock("soapstone_tiles", () -> new Block(Template.soapstone()));
    public static final DeferredHolder<Block, StairBlock> SOAPSTONE_STAIRS = registerBlock("soapstone_stairs", () -> new StairBlock(BorealisBlocks.SOAPSTONE.get().defaultBlockState(), Template.soapstone().noOcclusion()));
    public static final DeferredHolder<Block, StairBlock> SOAPSTONE_BRICK_STAIRS = registerBlock("soapstone_brick_stairs", () -> new StairBlock(BorealisBlocks.SOAPSTONE_BRICKS.get().defaultBlockState(), Template.soapstone().noOcclusion()));
    public static final DeferredHolder<Block, StairBlock> SOAPSTONE_TILE_STAIRS = registerBlock("soapstone_tile_stairs", () -> new StairBlock(BorealisBlocks.SOAPSTONE_TILES.get().defaultBlockState(), Template.soapstone().noOcclusion()));
    public static final DeferredHolder<Block, SlabBlock> SOAPSTONE_SLAB = registerBlock("soapstone_slab", () -> new SlabBlock(Template.soapstone().noOcclusion()));
    public static final DeferredHolder<Block, SlabBlock> SOAPSTONE_BRICK_SLAB = registerBlock("soapstone_brick_slab", () -> new SlabBlock(Template.soapstone().noOcclusion()));
    public static final DeferredHolder<Block, SlabBlock> SOAPSTONE_TILE_SLAB = registerBlock("soapstone_tile_slab", () -> new SlabBlock(Template.soapstone().noOcclusion()));
    public static final DeferredHolder<Block, WallBlock> SOAPSTONE_WALL = registerBlock("soapstone_wall", () -> new WallBlock(Template.soapstone().noOcclusion()));
    public static final DeferredHolder<Block, WallBlock> SOAPSTONE_BRICK_WALL = registerBlock("soapstone_brick_wall", () -> new WallBlock(Template.soapstone().noOcclusion()));
    public static final DeferredHolder<Block, WallBlock> SOAPSTONE_TILE_WALL = registerBlock("soapstone_tile_wall", () -> new WallBlock(Template.soapstone().noOcclusion()));
    public static final DeferredHolder<Block, ButtonBlock> SOAPSTONE_BUTTON = registerBlock("soapstone_button", () -> new ButtonBlock(SET_SOAPSTONE, 20, Template.soapstone().noOcclusion().noCollission()));
    public static final DeferredHolder<Block, PressurePlateBlock> SOAPSTONE_PRESSURE_PLATE = registerBlock("soapstone_pressure_plate", () -> new PressurePlateBlock(SET_SOAPSTONE, Template.soapstone().noOcclusion().noCollission()));

    public static final DeferredHolder<Block, Block> SLATE = registerBlock("slate", () -> new Block(Template.slate()));
    public static final DeferredHolder<Block, RotatedPillarBlock> SLATE_PILLAR = registerBlock("slate_pillar", () -> new RotatedPillarBlock(Template.slate()));
    public static final DeferredHolder<Block, Block> SLATE_TILES = registerBlock("slate_tiles", () -> new Block(Template.slate()));
    public static final DeferredHolder<Block, Block> STARRY_SLATE = registerBlock("starry_slate", () -> new Block(Template.slate().mapColor(MapColor.WARPED_HYPHAE)));
    public static final DeferredHolder<Block, Block> STARRY_SLATE_TILES = registerBlock("starry_slate_tiles", () -> new Block(Template.slate().mapColor(MapColor.WARPED_HYPHAE)));

    public static final DeferredHolder<Block, Block> PUMICE = registerBlock("pumice", () -> new PumiceBlock(Template.pumice()));
    public static final DeferredHolder<Block, Block> PUMICE_GEYSER = registerBlock("pumice_geyser", () -> new PumiceGeyserBlock(Template.pumice()));
    public static final DeferredHolder<Block, Block> SCORIA = registerBlock("scoria", () -> new Block(Template.soapstone().sound(SoundType.BASALT).mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final DeferredHolder<Block, Block> PETRIFIED_WOOD = registerBlock("petrified_wood", () -> new Block(Template.petrifiedWood()));
    public static final DeferredHolder<Block, Block> PETRIFIED_WOOD_BRICKS = registerBlock("petrified_wood_bricks", () -> new Block(Template.petrifiedWood()));
    public static final DeferredHolder<Block, Block> BONE_DRY_WOOD = registerBlock("bone_dry_wood", () -> new Block(Template.petrifiedWood().mapColor(MapColor.SAND)));
    public static final DeferredHolder<Block, Block> BONE_DRY_WOOD_BRICKS = registerBlock("bone_dry_wood_bricks", () -> new Block(Template.petrifiedWood().mapColor(MapColor.SAND)));

    public static final DeferredHolder<Block, StandingSignBlock> BRUMAL_SIGN = BLOCKS.register("brumal_sign", () -> new BorealisStandingSignBlock(Template.sign(MapColor.COLOR_LIGHT_BLUE), WOODSET_BRUMAL));
    public static final DeferredHolder<Block, WallSignBlock> BRUMAL_WALL_SIGN = BLOCKS.register("brumal_wall_sign", () -> new BorealisWallSignBlock(Template.sign(MapColor.COLOR_LIGHT_BLUE), WOODSET_BRUMAL));
    public static final DeferredHolder<Block, CeilingHangingSignBlock> BRUMAL_HANGING_SIGN = BLOCKS.register("brumal_hanging_sign", () -> new BorealisCeilingHangingSignBlock(Template.sign(MapColor.COLOR_LIGHT_BLUE), WOODSET_BRUMAL));
    public static final DeferredHolder<Block, WallHangingSignBlock> BRUMAL_WALL_HANGING_SIGN = BLOCKS.register("brumal_wall_hanging_sign", () -> new BorealisWallHangingSignBlock(Template.sign(MapColor.COLOR_LIGHT_BLUE), WOODSET_BRUMAL));
    public static final DeferredHolder<Block, SaplingBlock> BRUMAL_SAPLING = registerBlock("brumal_sapling", () -> new SaplingBlock(Grower.of("brumal"), Template.plant()));
    public static final DeferredHolder<Block, FlowerPotBlock> POTTED_BRUMAL_SAPLING = BLOCKS.register("potted_brumal_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BRUMAL_SAPLING, Template.pot()));
    public static final DeferredHolder<Block, Block> BRUMAL_PLANKS = registerBlock("brumal_planks", () -> new Block(Template.brumal()));
    public static final DeferredHolder<Block, RotatedPillarBlock> BRUMAL_LOG = registerBlock("brumal_log", () -> new RotatedPillarBlock(Template.log(MapColor.COLOR_LIGHT_BLUE, MapColor.STONE)));
    public static final DeferredHolder<Block, RotatedPillarBlock> BRUMAL_WOOD = registerBlock("brumal_wood", () -> new RotatedPillarBlock(Template.brumal().mapColor(MapColor.STONE)));
    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_BRUMAL_LOG = registerBlock("stripped_brumal_log", () -> new RotatedPillarBlock(Template.brumal()));
    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_BRUMAL_WOOD = registerBlock("stripped_brumal_wood", () -> new RotatedPillarBlock(Template.brumal()));
    public static final DeferredHolder<Block, Block> BRUMAL_LEAVES = registerBlock("brumal_leaves", () -> new LeavesBlock(Template.leaves().mapColor(MapColor.DIAMOND)));
    public static final DeferredHolder<Block, StairBlock> BRUMAL_STAIRS = registerBlock("brumal_stairs", () -> new StairBlock( BorealisBlocks.BRUMAL_PLANKS.get().defaultBlockState(), Template.brumal().noOcclusion()));
    public static final DeferredHolder<Block, SlabBlock> BRUMAL_SLAB = registerBlock("brumal_slab", () -> new SlabBlock(Template.brumal().noOcclusion()));
    public static final DeferredHolder<Block, FenceBlock> BRUMAL_FENCE = registerBlock("brumal_fence", () -> new FenceBlock(Template.brumal().noOcclusion()));
    public static final DeferredHolder<Block, FenceGateBlock> BRUMAL_FENCE_GATE = registerBlock("brumal_fence_gate", () -> new FenceGateBlock(WOODSET_BRUMAL, Template.brumal().noOcclusion()));
    public static final DeferredHolder<Block, DoorBlock> BRUMAL_DOOR = registerBlock("brumal_door", () -> new DoorBlock(SET_BRUMAL, Template.brumal().noOcclusion()));
    public static final DeferredHolder<Block, TrapDoorBlock> BRUMAL_TRAPDOOR = registerBlock("brumal_trapdoor", () -> new TrapDoorBlock(SET_BRUMAL, Template.brumal().noOcclusion()));
    public static final DeferredHolder<Block, ButtonBlock> BRUMAL_BUTTON = registerBlock("brumal_button", () -> new ButtonBlock(SET_BRUMAL, 30, Template.brumal().noOcclusion().noCollission()));
    public static final DeferredHolder<Block, PressurePlateBlock> BRUMAL_PRESSURE_PLATE = registerBlock("brumal_pressure_plate", () -> new PressurePlateBlock(SET_BRUMAL, Template.brumal().noOcclusion().noCollission()));

    public static final DeferredHolder<Block, StandingSignBlock> FROSTFIR_SIGN = BLOCKS.register("frostfir_sign", () -> new BorealisStandingSignBlock(Template.sign(MapColor.TERRACOTTA_BLUE), WOODSET_FROSTFIR));
    public static final DeferredHolder<Block, WallSignBlock> FROSTFIR_WALL_SIGN = BLOCKS.register("frostfir_wall_sign", () -> new BorealisWallSignBlock(Template.sign(MapColor.TERRACOTTA_BLUE), WOODSET_FROSTFIR));
    public static final DeferredHolder<Block, CeilingHangingSignBlock> FROSTFIR_HANGING_SIGN = BLOCKS.register("frostfir_hanging_sign", () -> new BorealisCeilingHangingSignBlock(Template.sign(MapColor.TERRACOTTA_BLUE), WOODSET_FROSTFIR));
    public static final DeferredHolder<Block, WallHangingSignBlock> FROSTFIR_WALL_HANGING_SIGN = BLOCKS.register("frostfir_wall_hanging_sign", () -> new BorealisWallHangingSignBlock(Template.sign(MapColor.TERRACOTTA_BLUE), WOODSET_FROSTFIR));
    public static final DeferredHolder<Block, SaplingBlock> FROSTFIR_SAPLING = registerBlock("frostfir_sapling", () -> new SaplingBlock(Grower.of("frostfir"), Template.plant()));
    public static final DeferredHolder<Block, FlowerPotBlock> POTTED_FROSTFIR_SAPLING = BLOCKS.register("potted_frostfir_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, FROSTFIR_SAPLING, Template.pot()));
    public static final DeferredHolder<Block, Block> FROSTFIR_PLANKS = registerBlock("frostfir_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, RotatedPillarBlock> FROSTFIR_LOG = registerBlock("frostfir_log", () -> new RotatedPillarBlock(Template.log(MapColor.TERRACOTTA_BLUE, MapColor.COLOR_BLACK)));
    public static final DeferredHolder<Block, RotatedPillarBlock> FROSTFIR_WOOD = registerBlock("frostfir_wood", () -> new RotatedPillarBlock(Template.frostfir().mapColor(MapColor.COLOR_BLACK)));
    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_FROSTFIR_LOG = registerBlock("stripped_frostfir_log", () -> new RotatedPillarBlock(Template.frostfir()));
    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_FROSTFIR_WOOD = registerBlock("stripped_frostfir_wood", () -> new RotatedPillarBlock(Template.frostfir()));
    public static final DeferredHolder<Block, Block> FROSTFIR_LEAVES = registerBlock("frostfir_leaves", () -> new LeavesBlock(Template.leaves().mapColor(MapColor.TERRACOTTA_BLUE)));
    public static final DeferredHolder<Block, StairBlock> FROSTFIR_STAIRS = registerBlock("frostfir_stairs", () -> new StairBlock( BorealisBlocks.FROSTFIR_PLANKS.get().defaultBlockState(),Template.frostfir().noOcclusion()));
    public static final DeferredHolder<Block, SlabBlock> FROSTFIR_SLAB = registerBlock("frostfir_slab", () -> new SlabBlock(Template.frostfir().noOcclusion()));
    public static final DeferredHolder<Block, FenceBlock> FROSTFIR_FENCE = registerBlock("frostfir_fence", () -> new FenceBlock(Template.frostfir().noOcclusion()));
    public static final DeferredHolder<Block, FenceGateBlock> FROSTFIR_FENCE_GATE = registerBlock("frostfir_fence_gate", () -> new FenceGateBlock(WOODSET_FROSTFIR, Template.frostfir().noOcclusion()));
    public static final DeferredHolder<Block, DoorBlock> FROSTFIR_DOOR = registerBlock("frostfir_door", () -> new DoorBlock(SET_FROSTFIR, Template.frostfir().noOcclusion()));
    public static final DeferredHolder<Block, TrapDoorBlock> FROSTFIR_TRAPDOOR = registerBlock("frostfir_trapdoor", () -> new TrapDoorBlock(SET_FROSTFIR, Template.frostfir().noOcclusion()));
    public static final DeferredHolder<Block, ButtonBlock> FROSTFIR_BUTTON = registerBlock("frostfir_button", () -> new ButtonBlock(SET_FROSTFIR, 30, Template.frostfir().noOcclusion().noCollission()));
    public static final DeferredHolder<Block, PressurePlateBlock> FROSTFIR_PRESSURE_PLATE = registerBlock("frostfir_pressure_plate", () -> new PressurePlateBlock(SET_FROSTFIR, Template.frostfir().noOcclusion().noCollission()));

    public static final DeferredHolder<Block, StandingSignBlock> SWEETWOOD_SIGN = BLOCKS.register("sweetwood_sign", () -> new BorealisStandingSignBlock(Template.sign(MapColor.SAND), WOODSET_SWEETWOOD));
    public static final DeferredHolder<Block, WallSignBlock> SWEETWOOD_WALL_SIGN = BLOCKS.register("sweetwood_wall_sign", () -> new BorealisWallSignBlock(Template.sign(MapColor.SAND), WOODSET_SWEETWOOD));
    public static final DeferredHolder<Block, CeilingHangingSignBlock> SWEETWOOD_HANGING_SIGN = BLOCKS.register("sweetwood_hanging_sign", () -> new BorealisCeilingHangingSignBlock(Template.sign(MapColor.SAND), WOODSET_SWEETWOOD));
    public static final DeferredHolder<Block, WallHangingSignBlock> SWEETWOOD_WALL_HANGING_SIGN = BLOCKS.register("sweetwood_wall_hanging_sign", () -> new BorealisWallHangingSignBlock(Template.sign(MapColor.SAND), WOODSET_SWEETWOOD));
    public static final DeferredHolder<Block, SaplingBlock> SWEETWOOD_SAPLING = registerBlock("sweetwood_sapling", () -> new SaplingBlock(Grower.of("cotton"), Template.plant()));
    public static final DeferredHolder<Block, FlowerPotBlock> POTTED_SWEETWOOD_SAPLING = BLOCKS.register("potted_sweetwood_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SWEETWOOD_SAPLING, Template.pot()));
    public static final DeferredHolder<Block, Block> SWEETWOOD_PLANKS = registerBlock("sweetwood_planks", () -> new Block(Template.sweetwood()));
    public static final DeferredHolder<Block, RotatedPillarBlock> SWEETWOOD_LOG = registerBlock("sweetwood_log", () -> new RotatedPillarBlock(Template.log(MapColor.SAND, MapColor.WOOL)));
    public static final DeferredHolder<Block, RotatedPillarBlock> SWEETWOOD = registerBlock("sweetwood", () -> new RotatedPillarBlock(Template.sweetwood().mapColor(MapColor.WOOL)));
    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_SWEETWOOD_LOG = registerBlock("stripped_sweetwood_log", () -> new RotatedPillarBlock(Template.sweetwood()));
    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_SWEETWOOD = registerBlock("stripped_sweetwood", () -> new RotatedPillarBlock(Template.sweetwood()));
    public static final DeferredHolder<Block, Block> SWEETWOOD_LEAVES = registerBlock("sweetwood_leaves", () -> new LeavesBlock(Template.leaves().mapColor(MapColor.COLOR_PINK)));
    public static final DeferredHolder<Block, StairBlock> SWEETWOOD_STAIRS = registerBlock("sweetwood_stairs", () -> new StairBlock( BorealisBlocks.SWEETWOOD_PLANKS.get().defaultBlockState(), Template.sweetwood().noOcclusion()));
    public static final DeferredHolder<Block, SlabBlock> SWEETWOOD_SLAB = registerBlock("sweetwood_slab", () -> new SlabBlock(Template.sweetwood().noOcclusion()));
    public static final DeferredHolder<Block, FenceBlock> SWEETWOOD_FENCE = registerBlock("sweetwood_fence", () -> new FenceBlock(Template.sweetwood().noOcclusion()));
    public static final DeferredHolder<Block, FenceGateBlock> SWEETWOOD_FENCE_GATE = registerBlock("sweetwood_fence_gate", () -> new FenceGateBlock(WOODSET_SWEETWOOD, Template.sweetwood().noOcclusion()));
    public static final DeferredHolder<Block, DoorBlock> SWEETWOOD_DOOR = registerBlock("sweetwood_door", () -> new DoorBlock(SET_SWEETWOOD, Template.sweetwood().noOcclusion()));
    public static final DeferredHolder<Block, TrapDoorBlock> SWEETWOOD_TRAPDOOR = registerBlock("sweetwood_trapdoor", () -> new TrapDoorBlock(SET_SWEETWOOD, Template.sweetwood().noOcclusion()));
    public static final DeferredHolder<Block, ButtonBlock> SWEETWOOD_BUTTON = registerBlock("sweetwood_button", () -> new ButtonBlock(SET_SWEETWOOD, 30, Template.sweetwood().noOcclusion().noCollission()));
    public static final DeferredHolder<Block, PressurePlateBlock> SWEETWOOD_PRESSURE_PLATE = registerBlock("sweetwood_pressure_plate", () -> new PressurePlateBlock(SET_SWEETWOOD, Template.sweetwood().noOcclusion().noCollission()));

    public static final DeferredHolder<Block, StandingSignBlock> CARAMELIZED_SIGN = BLOCKS.register("caramelized_sign", () -> new BorealisStandingSignBlock(Template.sign(MapColor.TERRACOTTA_ORANGE, false), WOODSET_CARAMELIZED));
    public static final DeferredHolder<Block, WallSignBlock> CARAMELIZED_WALL_SIGN = BLOCKS.register("caramelized_wall_sign", () -> new BorealisWallSignBlock(Template.sign(MapColor.TERRACOTTA_ORANGE, false), WOODSET_CARAMELIZED));
    public static final DeferredHolder<Block, CeilingHangingSignBlock> CARAMELIZED_HANGING_SIGN = BLOCKS.register("caramelized_hanging_sign", () -> new BorealisCeilingHangingSignBlock(Template.sign(MapColor.TERRACOTTA_ORANGE, false), WOODSET_CARAMELIZED));
    public static final DeferredHolder<Block, WallHangingSignBlock> CARAMELIZED_WALL_HANGING_SIGN = BLOCKS.register("caramelized_wall_hanging_sign", () -> new BorealisWallHangingSignBlock(Template.sign(MapColor.TERRACOTTA_ORANGE, false), WOODSET_CARAMELIZED));
    public static final DeferredHolder<Block, Block> CARAMELIZED_PLANKS = registerBlock("caramelized_planks", () -> new Block(Template.caramel()));
    public static final DeferredHolder<Block, RotatedPillarBlock> CARAMELIZED_LOG = registerBlock("caramelized_log", () -> new RotatedPillarBlock(Template.log(MapColor.TERRACOTTA_ORANGE, MapColor.TERRACOTTA_BROWN, false)));
    public static final DeferredHolder<Block, RotatedPillarBlock> CARAMELIZED_WOOD = registerBlock("caramelized_wood", () -> new RotatedPillarBlock(Template.caramel().mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_CARAMELIZED_LOG = registerBlock("stripped_caramelized_log", () -> new RotatedPillarBlock(Template.caramel()));
    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_CARAMELIZED_WOOD = registerBlock("stripped_caramelized_wood", () -> new RotatedPillarBlock(Template.caramel()));
    public static final DeferredHolder<Block, Block> GLAZED_LEAVES = registerBlock("glazed_leaves", () -> new LeavesBlock(Template.leaves().mapColor(MapColor.COLOR_YELLOW)));
    public static final DeferredHolder<Block, StairBlock> CARAMELIZED_STAIRS = registerBlock("caramelized_stairs", () -> new StairBlock( BorealisBlocks.CARAMELIZED_PLANKS.get().defaultBlockState(), Template.caramel().noOcclusion()));
    public static final DeferredHolder<Block, SlabBlock> CARAMELIZED_SLAB = registerBlock("caramelized_slab", () -> new SlabBlock(Template.caramel().noOcclusion()));
    public static final DeferredHolder<Block, FenceBlock> CARAMELIZED_FENCE = registerBlock("caramelized_fence", () -> new FenceBlock(Template.caramel().noOcclusion()));
    public static final DeferredHolder<Block, FenceGateBlock> CARAMELIZED_FENCE_GATE = registerBlock("caramelized_fence_gate", () -> new FenceGateBlock(WOODSET_CARAMELIZED, Template.caramel().noOcclusion()));
    public static final DeferredHolder<Block, DoorBlock> CARAMELIZED_DOOR = registerBlock("caramelized_door", () -> new DoorBlock(SET_CARAMELIZED, Template.caramel().noOcclusion()));
    public static final DeferredHolder<Block, TrapDoorBlock> CARAMELIZED_TRAPDOOR = registerBlock("caramelized_trapdoor", () -> new TrapDoorBlock(SET_CARAMELIZED, Template.caramel().noOcclusion()));
    public static final DeferredHolder<Block, ButtonBlock> CARAMELIZED_BUTTON = registerBlock("caramelized_button", () -> new ButtonBlock(SET_CARAMELIZED, 30, Template.caramel().noOcclusion().noCollission()));
    public static final DeferredHolder<Block, PressurePlateBlock> CARAMELIZED_PRESSURE_PLATE = registerBlock("caramelized_pressure_plate", () -> new PressurePlateBlock(SET_CARAMELIZED, Template.caramel().noOcclusion().noCollission()));

    public static final DeferredHolder<Block, RotatedPillarBlock> KYANITE_ORE = registerBlock("kyanite_ore", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_ORE).lightLevel((blockstate) -> 8)));
    public static final DeferredHolder<Block, Block> TANZANITE_ORE = registerBlock("tanzanite_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));
    public static final DeferredHolder<Block, Block> TANZANITE_BLOCK = registerBlock("tanzanite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).sound(SoundType.AMETHYST)));
    public static final DeferredHolder<Block, Block> HAILSTONE = registerBlock("hailstone_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRYING_OBSIDIAN)));

    public static final DeferredHolder<Block, Block> PERMAFROST = registerBlock("permafrost", () -> new PermafrostBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).sound(SoundType.GRAVEL)));
    public static final DeferredHolder<Block, Block> PERMAFROST_RUBBLE = registerBlock("permafrost_rubble", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> LIVING_SNOW_BLOCK = registerBlock("living_snow_block", () -> new LivingSnowBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)));
    public static final DeferredHolder<Block, Block> SUGAR_SNOW_BLOCK = registerBlock("sugar_snow_block", () -> new LivingSnowBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)));
    public static final DeferredHolder<Block, Block> SUGAR_SNOW = registerBlock("sugar_snow", () -> new SugarSnowBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW)));
    public static final DeferredHolder<Block, Block> CLOUD = registerBlock("cloud", () -> new CloudBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).sound(SoundType.WOOL).destroyTime(0.2F).strength(0.4F).noCollission().requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> PEAT = registerBlock("peat", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL).sound(SoundType.BASALT).requiresCorrectToolForDrops().destroyTime(1.0F)));

    public static final DeferredHolder<Block, KyaniteArrowBlock> EMBEDDED_KYANITE_ARROW = BLOCKS.register("embedded_kyanite_arrow", () -> new KyaniteArrowBlock(BlockBehaviour.Properties.of().noCollission().lightLevel((blockstate) -> 15)));
    public static final DeferredHolder<Block, RotatedPillarBlock> KYANITE_CABLE = registerBlock("kyanite_cable", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()));
    public static final DeferredHolder<Block, Block> KYANITE_BULB = registerBlock("kyanite_bulb", () -> new Block(BlockBehaviour.Properties.of()));

    public static final DeferredHolder<Block, FluoriteBlock> FLUORITE = registerBlock("fluorite", () -> new FluoriteBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final DeferredHolder<Block, StaticFieldBlock> STATIC_FIELD = registerBlock("static_field", () -> new StaticFieldBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));

    public static final DeferredHolder<Block, TallGrassBlock> HOLLY = registerBlock("holly", () -> new TallGrassBlock(Template.plant()));
    public static final DeferredHolder<Block, Block> LICHEN_BLOCK = registerBlock("lichen_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK)));
    public static final DeferredHolder<Block, MisteriaBody> MISTERIA_BODY = BLOCKS.register("misteria_plant", () -> new MisteriaBody(Template.plant()));
    public static final DeferredHolder<Block, MisteriaHead> MISTERIA_HEAD = registerBlock("misteria", () -> new MisteriaHead(Template.plant()));
    public static final DeferredHolder<Block, ShadedBushBlock> BRUMELIAD = registerBlock("brumeliad", () -> new ShadedBushBlock(Template.plant(), BlockTags.DIRT));
    public static final DeferredHolder<Block, ShadedBushBlock> WINTER_VIOLA = registerBlock("winter_viola", () -> new ShadedBushBlock(Template.plant(), List.of(BlockTags.LOGS, BlockTags.DIRT)));
    public static final DeferredHolder<Block, ShadedBushBlock> WINTER_FIDDLE = BLOCKS.register("winter_fiddle", () -> new ShadedBushBlock(Template.plant().offsetType(BlockBehaviour.OffsetType.XZ), BlockTags.LOGS));
    public static final DeferredHolder<Block, WallPlantBlock> WALL_WINTER_FIDDLE = BLOCKS.register("wall_winter_fiddle", () -> new WallPlantBlock(BlockTags.LOGS, Template.plant()));
    public static final DeferredHolder<Block, ShadedDoublePlantBlock> WINTER_CELLO = registerBlock("winter_cello", () -> new ShadedDoublePlantBlock(Template.plant(), BlockTags.DIRT));
    public static final DeferredHolder<Block, AlmsBlock> ALMS = registerBlock("alms", () -> new AlmsBlock(Template.petrifiedWood().mapColor(MapColor.DIRT)));
    public static final DeferredHolder<Block, AlmsCrackedBlock> CRACKED_ALMS = registerBlock("cracked_alms", () -> new AlmsCrackedBlock(Template.petrifiedWood().mapColor(MapColor.GOLD)));

    public static final DeferredHolder<Block, Block> CINNABAR = registerBlock("cinnabar", () -> new Block(Template.soapstone().sound(SoundType.BASALT).mapColor(MapColor.TERRACOTTA_MAGENTA)));

    public static final DeferredHolder<Block, KilnBlock> KILN = registerBlock("kiln", () -> new KilnBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOKER)));

    private static <T extends Block> DeferredHolder<Block, T> baseRegister(String name, Supplier<? extends T> block, Function<DeferredHolder<Block, T>, Supplier<? extends Item>> item) {
        DeferredHolder<Block, T> register = BLOCKS.register(name, block);
        BorealisItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <T extends Block> DeferredHolder<Block, T> registerBlock(String name, Supplier<? extends Block> block) {
        return (DeferredHolder<Block, T>) baseRegister(name, block, BorealisBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final DeferredHolder<Block, T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
    }

    private static class Template {
        protected static BlockBehaviour.Properties soapstone() {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE);
        }
        protected static BlockBehaviour.Properties slate() {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.TERRACOTTA_BLUE);
        }
        protected static BlockBehaviour.Properties pumice() {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_BASALT)
                    .strength(1.0F, 3.0F)
                    .instrument(NoteBlockInstrument.HAT)
                    .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY);
        }
        protected static BlockBehaviour.Properties petrifiedWood() {
            return BlockBehaviour.Properties.of()
                    .strength(1.5F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASS)
                    .mapColor(MapColor.TERRACOTTA_BLUE);
        }
        protected static BlockBehaviour.Properties brumal() {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .mapColor(MapColor.COLOR_LIGHT_BLUE);
        }
        protected static BlockBehaviour.Properties frostfir() {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .mapColor(MapColor.TERRACOTTA_BLUE);
        }
        protected static BlockBehaviour.Properties sweetwood() {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .mapColor(MapColor.SAND);
        }
        protected static BlockBehaviour.Properties caramel() {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS)
                    .mapColor(MapColor.TERRACOTTA_ORANGE);
        }
        protected static BlockBehaviour.Properties log(MapColor top, MapColor side, boolean flammable) {
            BlockBehaviour.Properties prop = BlockBehaviour.Properties.of().mapColor((state) ->
                    state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD);
            if (flammable) prop = prop.ignitedByLava();
            return prop;
        }

        protected static BlockBehaviour.Properties log(MapColor top, MapColor side) { return log(top, side, true); }
        protected static BlockBehaviour.Properties sign(MapColor color, boolean flammable) {
            BlockBehaviour.Properties prop = BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN).mapColor(color);
            if (flammable) prop = prop.ignitedByLava();
            return prop;
        }
        protected static BlockBehaviour.Properties sign(MapColor color) { return sign(color, true); }

        protected static BlockBehaviour.Properties leaves() {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES);
        }

        protected static BlockBehaviour.Properties plant() {
            return BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_GREEN)
                    .noCollission().instabreak().sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY);
        }

        protected static BlockBehaviour.Properties pot() {
            return BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY).mapColor(MapColor.DIRT);
        }
    }
}