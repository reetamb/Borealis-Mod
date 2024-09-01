package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.*;
import com.reetam.borealis.block.entity.*;
import com.reetam.borealis.world.tree.Grower;
import net.minecraft.core.Direction;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class BorealisBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BorealisMod.MODID);

    public static final BlockSetType SET_BRUMAL = new BlockSetType(new ResourceLocation(BorealisMod.MODID, "brumal").toString());
    public static final BlockSetType SET_FROSTFIR = new BlockSetType(new ResourceLocation(BorealisMod.MODID, "frostfir").toString());
    public static final BlockSetType SET_SWEETWOOD = new BlockSetType(new ResourceLocation(BorealisMod.MODID, "sweetwood").toString());
    public static final BlockSetType SET_CARAMELIZED = new BlockSetType(new ResourceLocation(BorealisMod.MODID, "caramelized").toString());
    public static final BlockSetType SET_SOAPSTONE = new BlockSetType(new ResourceLocation(BorealisMod.MODID, "soapstone").toString());

    public static final WoodType WOODSET_BRUMAL = WoodType.register(new WoodType(SET_BRUMAL.name(), SET_BRUMAL));
    public static final WoodType WOODSET_FROSTFIR = WoodType.register(new WoodType(SET_FROSTFIR.name(), SET_FROSTFIR));
    public static final WoodType WOODSET_SWEETWOOD = WoodType.register(new WoodType(SET_SWEETWOOD.name(), SET_SWEETWOOD));
    public static final WoodType WOODSET_CARAMELIZED = WoodType.register(new WoodType(SET_CARAMELIZED.name(), SET_CARAMELIZED));

    public static final RegistryObject<Block> BOREALIS_PORTAL = BLOCKS.register("borealis_portal", BorealisPortalBlock::new);

    public static final RegistryObject<Block> SOAPSTONE = registerBlock("soapstone", () -> new Block(Template.soapstone()));
    public static final RegistryObject<Block> SOAPSTONE_BRICKS = registerBlock("soapstone_bricks", () -> new Block(Template.soapstone()));
    public static final RegistryObject<Block> SOAPSTONE_TILES = registerBlock("soapstone_tiles", () -> new Block(Template.soapstone()));
    public static final RegistryObject<StairBlock> SOAPSTONE_STAIRS = registerBlock("soapstone_stairs", () -> new StairBlock(() -> BorealisBlocks.SOAPSTONE.get().defaultBlockState(), Template.soapstone().noOcclusion()));
    public static final RegistryObject<StairBlock> SOAPSTONE_BRICK_STAIRS = registerBlock("soapstone_brick_stairs", () -> new StairBlock(() -> BorealisBlocks.SOAPSTONE_BRICKS.get().defaultBlockState(), Template.soapstone().noOcclusion()));
    public static final RegistryObject<StairBlock> SOAPSTONE_TILE_STAIRS = registerBlock("soapstone_tile_stairs", () -> new StairBlock(() -> BorealisBlocks.SOAPSTONE_TILES.get().defaultBlockState(), Template.soapstone().noOcclusion()));
    public static final RegistryObject<SlabBlock> SOAPSTONE_SLAB = registerBlock("soapstone_slab", () -> new SlabBlock(Template.soapstone().noOcclusion()));
    public static final RegistryObject<SlabBlock> SOAPSTONE_BRICK_SLAB = registerBlock("soapstone_brick_slab", () -> new SlabBlock(Template.soapstone().noOcclusion()));
    public static final RegistryObject<SlabBlock> SOAPSTONE_TILE_SLAB = registerBlock("soapstone_tile_slab", () -> new SlabBlock(Template.soapstone().noOcclusion()));
    public static final RegistryObject<WallBlock> SOAPSTONE_WALL = registerBlock("soapstone_wall", () -> new WallBlock(Template.soapstone().noOcclusion()));
    public static final RegistryObject<WallBlock> SOAPSTONE_BRICK_WALL = registerBlock("soapstone_brick_wall", () -> new WallBlock(Template.soapstone().noOcclusion()));
    public static final RegistryObject<WallBlock> SOAPSTONE_TILE_WALL = registerBlock("soapstone_tile_wall", () -> new WallBlock(Template.soapstone().noOcclusion()));
    public static final RegistryObject<ButtonBlock> SOAPSTONE_BUTTON = registerBlock("soapstone_button", () -> new ButtonBlock(Template.soapstone().noOcclusion().noCollission(), SET_SOAPSTONE, 20, false));
    public static final RegistryObject<PressurePlateBlock> SOAPSTONE_PRESSURE_PLATE = registerBlock("soapstone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, Template.soapstone().noOcclusion().noCollission(), SET_SOAPSTONE));

    public static final RegistryObject<Block> SLATE = registerBlock("slate", () -> new Block(Template.slate()));
    public static final RegistryObject<RotatedPillarBlock> SLATE_PILLAR = registerBlock("slate_pillar", () -> new RotatedPillarBlock(Template.slate()));
    public static final RegistryObject<Block> SLATE_TILES = registerBlock("slate_tiles", () -> new Block(Template.slate()));
    public static final RegistryObject<Block> STARRY_SLATE = registerBlock("starry_slate", () -> new Block(Template.slate().mapColor(MapColor.WARPED_HYPHAE)));
    public static final RegistryObject<Block> STARRY_SLATE_TILES = registerBlock("starry_slate_tiles", () -> new Block(Template.slate().mapColor(MapColor.WARPED_HYPHAE)));

    public static final RegistryObject<Block> PUMICE = registerBlock("pumice", () -> new PumiceBlock(Template.pumice()));
    public static final RegistryObject<Block> PUMICE_GEYSER = registerBlock("pumice_geyser", () -> new PumiceGeyserBlock(Template.pumice()));
    public static final RegistryObject<Block> SCORIA = registerBlock("scoria", () -> new Block(Template.soapstone().sound(SoundType.BASALT).mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryObject<Block> PETRIFIED_WOOD = registerBlock("petrified_wood", () -> new Block(Template.petrifiedWood()));
    public static final RegistryObject<Block> PETRIFIED_WOOD_BRICKS = registerBlock("petrified_wood_bricks", () -> new Block(Template.petrifiedWood()));
    public static final RegistryObject<Block> BONE_DRY_WOOD = registerBlock("bone_dry_wood", () -> new Block(Template.petrifiedWood().mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> BONE_DRY_WOOD_BRICKS = registerBlock("bone_dry_wood_bricks", () -> new Block(Template.petrifiedWood().mapColor(MapColor.SAND)));

    public static final RegistryObject<StandingSignBlock> BRUMAL_SIGN = BLOCKS.register("brumal_sign", () -> new BorealisStandingSignBlock(Template.sign(MapColor.COLOR_LIGHT_BLUE), WOODSET_BRUMAL));
    public static final RegistryObject<WallSignBlock> BRUMAL_WALL_SIGN = BLOCKS.register("brumal_wall_sign", () -> new BorealisWallSignBlock(Template.sign(MapColor.COLOR_LIGHT_BLUE), WOODSET_BRUMAL));
    public static final RegistryObject<CeilingHangingSignBlock> BRUMAL_HANGING_SIGN = BLOCKS.register("brumal_hanging_sign", () -> new BorealisCeilingHangingSignBlock(Template.sign(MapColor.COLOR_LIGHT_BLUE), WOODSET_BRUMAL));
    public static final RegistryObject<WallHangingSignBlock> BRUMAL_WALL_HANGING_SIGN = BLOCKS.register("brumal_wall_hanging_sign", () -> new BorealisWallHangingSignBlock(Template.sign(MapColor.COLOR_LIGHT_BLUE), WOODSET_BRUMAL));
    public static final RegistryObject<SaplingBlock> BRUMAL_SAPLING = registerBlock("brumal_sapling", () -> new SaplingBlock(Grower.of("brumal"), Template.plant()));
    public static final RegistryObject<FlowerPotBlock> POTTED_BRUMAL_SAPLING = BLOCKS.register("potted_brumal_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BRUMAL_SAPLING, Template.pot()));
    public static final RegistryObject<Block> BRUMAL_PLANKS = registerBlock("brumal_planks", () -> new Block(Template.brumal()));
    public static final RegistryObject<RotatedPillarBlock> BRUMAL_LOG = registerBlock("brumal_log", () -> new RotatedPillarBlock(Template.log(MapColor.COLOR_LIGHT_BLUE, MapColor.STONE)));
    public static final RegistryObject<RotatedPillarBlock> BRUMAL_WOOD = registerBlock("brumal_wood", () -> new RotatedPillarBlock(Template.brumal().mapColor(MapColor.STONE)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_BRUMAL_LOG = registerBlock("stripped_brumal_log", () -> new RotatedPillarBlock(Template.brumal()));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_BRUMAL_WOOD = registerBlock("stripped_brumal_wood", () -> new RotatedPillarBlock(Template.brumal()));
    public static final RegistryObject<Block> BRUMAL_LEAVES = registerBlock("brumal_leaves", () -> new LeavesBlock(Template.leaves().mapColor(MapColor.DIAMOND)));
    public static final RegistryObject<StairBlock> BRUMAL_STAIRS = registerBlock("brumal_stairs", () -> new StairBlock(() -> BorealisBlocks.BRUMAL_PLANKS.get().defaultBlockState(), Template.brumal().noOcclusion()));
    public static final RegistryObject<SlabBlock> BRUMAL_SLAB = registerBlock("brumal_slab", () -> new SlabBlock(Template.brumal().noOcclusion()));
    public static final RegistryObject<FenceBlock> BRUMAL_FENCE = registerBlock("brumal_fence", () -> new FenceBlock(Template.brumal().noOcclusion()));
    public static final RegistryObject<FenceGateBlock> BRUMAL_FENCE_GATE = registerBlock("brumal_fence_gate", () -> new FenceGateBlock(Template.brumal().noOcclusion(), WOODSET_BRUMAL));
    public static final RegistryObject<DoorBlock> BRUMAL_DOOR = registerBlock("brumal_door", () -> new DoorBlock(Template.brumal().noOcclusion(), SET_BRUMAL));
    public static final RegistryObject<TrapDoorBlock> BRUMAL_TRAPDOOR = registerBlock("brumal_trapdoor", () -> new TrapDoorBlock(Template.brumal().noOcclusion(), SET_BRUMAL));
    public static final RegistryObject<ButtonBlock> BRUMAL_BUTTON = registerBlock("brumal_button", () -> new ButtonBlock(Template.brumal().noOcclusion().noCollission(), SET_BRUMAL, 30, true));
    public static final RegistryObject<PressurePlateBlock> BRUMAL_PRESSURE_PLATE = registerBlock("brumal_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Template.brumal().noOcclusion().noCollission(), SET_BRUMAL));

    public static final RegistryObject<StandingSignBlock> FROSTFIR_SIGN = BLOCKS.register("frostfir_sign", () -> new BorealisStandingSignBlock(Template.sign(MapColor.TERRACOTTA_BLUE), WOODSET_FROSTFIR));
    public static final RegistryObject<WallSignBlock> FROSTFIR_WALL_SIGN = BLOCKS.register("frostfir_wall_sign", () -> new BorealisWallSignBlock(Template.sign(MapColor.TERRACOTTA_BLUE), WOODSET_FROSTFIR));
    public static final RegistryObject<CeilingHangingSignBlock> FROSTFIR_HANGING_SIGN = BLOCKS.register("frostfir_hanging_sign", () -> new BorealisCeilingHangingSignBlock(Template.sign(MapColor.TERRACOTTA_BLUE), WOODSET_FROSTFIR));
    public static final RegistryObject<WallHangingSignBlock> FROSTFIR_WALL_HANGING_SIGN = BLOCKS.register("frostfir_wall_hanging_sign", () -> new BorealisWallHangingSignBlock(Template.sign(MapColor.TERRACOTTA_BLUE), WOODSET_FROSTFIR));
    public static final RegistryObject<SaplingBlock> FROSTFIR_SAPLING = registerBlock("frostfir_sapling", () -> new SaplingBlock(Grower.of("frostfir"), Template.plant()));
    public static final RegistryObject<FlowerPotBlock> POTTED_FROSTFIR_SAPLING = BLOCKS.register("potted_frostfir_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, FROSTFIR_SAPLING, Template.pot()));
    public static final RegistryObject<Block> FROSTFIR_PLANKS = registerBlock("frostfir_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> FROSTFIR_LOG = registerBlock("frostfir_log", () -> new RotatedPillarBlock(Template.log(MapColor.TERRACOTTA_BLUE, MapColor.COLOR_BLACK)));
    public static final RegistryObject<RotatedPillarBlock> FROSTFIR_WOOD = registerBlock("frostfir_wood", () -> new RotatedPillarBlock(Template.frostfir().mapColor(MapColor.COLOR_BLACK)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_FROSTFIR_LOG = registerBlock("stripped_frostfir_log", () -> new RotatedPillarBlock(Template.frostfir()));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_FROSTFIR_WOOD = registerBlock("stripped_frostfir_wood", () -> new RotatedPillarBlock(Template.frostfir()));
    public static final RegistryObject<Block> FROSTFIR_LEAVES = registerBlock("frostfir_leaves", () -> new LeavesBlock(Template.leaves().mapColor(MapColor.TERRACOTTA_BLUE)));
    public static final RegistryObject<StairBlock> FROSTFIR_STAIRS = registerBlock("frostfir_stairs", () -> new StairBlock(() -> BorealisBlocks.FROSTFIR_PLANKS.get().defaultBlockState(),Template.frostfir().noOcclusion()));
    public static final RegistryObject<SlabBlock> FROSTFIR_SLAB = registerBlock("frostfir_slab", () -> new SlabBlock(Template.frostfir().noOcclusion()));
    public static final RegistryObject<FenceBlock> FROSTFIR_FENCE = registerBlock("frostfir_fence", () -> new FenceBlock(Template.frostfir().noOcclusion()));
    public static final RegistryObject<FenceGateBlock> FROSTFIR_FENCE_GATE = registerBlock("frostfir_fence_gate", () -> new FenceGateBlock(Template.frostfir().noOcclusion(), WOODSET_FROSTFIR));
    public static final RegistryObject<DoorBlock> FROSTFIR_DOOR = registerBlock("frostfir_door", () -> new DoorBlock(Template.frostfir().noOcclusion(), SET_FROSTFIR));
    public static final RegistryObject<TrapDoorBlock> FROSTFIR_TRAPDOOR = registerBlock("frostfir_trapdoor", () -> new TrapDoorBlock(Template.frostfir().noOcclusion(), SET_FROSTFIR));
    public static final RegistryObject<ButtonBlock> FROSTFIR_BUTTON = registerBlock("frostfir_button", () -> new ButtonBlock(Template.frostfir().noOcclusion().noCollission(), SET_FROSTFIR, 30, true));
    public static final RegistryObject<PressurePlateBlock> FROSTFIR_PRESSURE_PLATE = registerBlock("frostfir_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Template.frostfir().noOcclusion().noCollission(), SET_FROSTFIR));

    public static final RegistryObject<StandingSignBlock> SWEETWOOD_SIGN = BLOCKS.register("sweetwood_sign", () -> new BorealisStandingSignBlock(Template.sign(MapColor.SAND), WOODSET_SWEETWOOD));
    public static final RegistryObject<WallSignBlock> SWEETWOOD_WALL_SIGN = BLOCKS.register("sweetwood_wall_sign", () -> new BorealisWallSignBlock(Template.sign(MapColor.SAND), WOODSET_SWEETWOOD));
    public static final RegistryObject<CeilingHangingSignBlock> SWEETWOOD_HANGING_SIGN = BLOCKS.register("sweetwood_hanging_sign", () -> new BorealisCeilingHangingSignBlock(Template.sign(MapColor.SAND), WOODSET_SWEETWOOD));
    public static final RegistryObject<WallHangingSignBlock> SWEETWOOD_WALL_HANGING_SIGN = BLOCKS.register("sweetwood_wall_hanging_sign", () -> new BorealisWallHangingSignBlock(Template.sign(MapColor.SAND), WOODSET_SWEETWOOD));
    public static final RegistryObject<SaplingBlock> SWEETWOOD_SAPLING = registerBlock("sweetwood_sapling", () -> new SaplingBlock(Grower.of("cotton"), Template.plant()));
    public static final RegistryObject<FlowerPotBlock> POTTED_SWEETWOOD_SAPLING = BLOCKS.register("potted_sweetwood_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SWEETWOOD_SAPLING, Template.pot()));
    public static final RegistryObject<Block> SWEETWOOD_PLANKS = registerBlock("sweetwood_planks", () -> new Block(Template.sweetwood()));
    public static final RegistryObject<RotatedPillarBlock> SWEETWOOD_LOG = registerBlock("sweetwood_log", () -> new RotatedPillarBlock(Template.log(MapColor.SAND, MapColor.WOOL)));
    public static final RegistryObject<RotatedPillarBlock> SWEETWOOD = registerBlock("sweetwood", () -> new RotatedPillarBlock(Template.sweetwood().mapColor(MapColor.WOOL)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SWEETWOOD_LOG = registerBlock("stripped_sweetwood_log", () -> new RotatedPillarBlock(Template.sweetwood()));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SWEETWOOD = registerBlock("stripped_sweetwood", () -> new RotatedPillarBlock(Template.sweetwood()));
    public static final RegistryObject<Block> SWEETWOOD_LEAVES = registerBlock("sweetwood_leaves", () -> new LeavesBlock(Template.leaves().mapColor(MapColor.COLOR_PINK)));
    public static final RegistryObject<StairBlock> SWEETWOOD_STAIRS = registerBlock("sweetwood_stairs", () -> new StairBlock(() -> BorealisBlocks.SWEETWOOD_PLANKS.get().defaultBlockState(), Template.sweetwood().noOcclusion()));
    public static final RegistryObject<SlabBlock> SWEETWOOD_SLAB = registerBlock("sweetwood_slab", () -> new SlabBlock(Template.sweetwood().noOcclusion()));
    public static final RegistryObject<FenceBlock> SWEETWOOD_FENCE = registerBlock("sweetwood_fence", () -> new FenceBlock(Template.sweetwood().noOcclusion()));
    public static final RegistryObject<FenceGateBlock> SWEETWOOD_FENCE_GATE = registerBlock("sweetwood_fence_gate", () -> new FenceGateBlock(Template.sweetwood().noOcclusion(), WOODSET_SWEETWOOD));
    public static final RegistryObject<DoorBlock> SWEETWOOD_DOOR = registerBlock("sweetwood_door", () -> new DoorBlock(Template.sweetwood().noOcclusion(), SET_SWEETWOOD));
    public static final RegistryObject<TrapDoorBlock> SWEETWOOD_TRAPDOOR = registerBlock("sweetwood_trapdoor", () -> new TrapDoorBlock(Template.sweetwood().noOcclusion(), SET_SWEETWOOD));
    public static final RegistryObject<ButtonBlock> SWEETWOOD_BUTTON = registerBlock("sweetwood_button", () -> new ButtonBlock(Template.sweetwood().noOcclusion().noCollission(), SET_SWEETWOOD, 30, true));
    public static final RegistryObject<PressurePlateBlock> SWEETWOOD_PRESSURE_PLATE = registerBlock("sweetwood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Template.sweetwood().noOcclusion().noCollission(), SET_SWEETWOOD));

    public static final RegistryObject<StandingSignBlock> CARAMELIZED_SIGN = BLOCKS.register("caramelized_sign", () -> new BorealisStandingSignBlock(Template.sign(MapColor.TERRACOTTA_ORANGE, false), WOODSET_CARAMELIZED));
    public static final RegistryObject<WallSignBlock> CARAMELIZED_WALL_SIGN = BLOCKS.register("caramelized_wall_sign", () -> new BorealisWallSignBlock(Template.sign(MapColor.TERRACOTTA_ORANGE, false), WOODSET_CARAMELIZED));
    public static final RegistryObject<CeilingHangingSignBlock> CARAMELIZED_HANGING_SIGN = BLOCKS.register("caramelized_hanging_sign", () -> new BorealisCeilingHangingSignBlock(Template.sign(MapColor.TERRACOTTA_ORANGE, false), WOODSET_CARAMELIZED));
    public static final RegistryObject<WallHangingSignBlock> CARAMELIZED_WALL_HANGING_SIGN = BLOCKS.register("caramelized_wall_hanging_sign", () -> new BorealisWallHangingSignBlock(Template.sign(MapColor.TERRACOTTA_ORANGE, false), WOODSET_CARAMELIZED));
    public static final RegistryObject<Block> CARAMELIZED_PLANKS = registerBlock("caramelized_planks", () -> new Block(Template.caramel()));
    public static final RegistryObject<RotatedPillarBlock> CARAMELIZED_LOG = registerBlock("caramelized_log", () -> new RotatedPillarBlock(Template.log(MapColor.TERRACOTTA_ORANGE, MapColor.TERRACOTTA_BROWN)));
    public static final RegistryObject<RotatedPillarBlock> CARAMELIZED_WOOD = registerBlock("caramelized_wood", () -> new RotatedPillarBlock(Template.caramel().mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_CARAMELIZED_LOG = registerBlock("stripped_caramelized_log", () -> new RotatedPillarBlock(Template.caramel()));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_CARAMELIZED_WOOD = registerBlock("stripped_caramelized_wood", () -> new RotatedPillarBlock(Template.caramel()));
    public static final RegistryObject<Block> GLAZED_LEAVES = registerBlock("glazed_leaves", () -> new LeavesBlock(Template.leaves().mapColor(MapColor.COLOR_YELLOW)));
    public static final RegistryObject<StairBlock> CARAMELIZED_STAIRS = registerBlock("caramelized_stairs", () -> new StairBlock(() -> BorealisBlocks.CARAMELIZED_PLANKS.get().defaultBlockState(), Template.caramel().noOcclusion()));
    public static final RegistryObject<SlabBlock> CARAMELIZED_SLAB = registerBlock("caramelized_slab", () -> new SlabBlock(Template.caramel().noOcclusion()));
    public static final RegistryObject<FenceBlock> CARAMELIZED_FENCE = registerBlock("caramelized_fence", () -> new FenceBlock(Template.caramel().noOcclusion()));
    public static final RegistryObject<FenceGateBlock> CARAMELIZED_FENCE_GATE = registerBlock("caramelized_fence_gate", () -> new FenceGateBlock(Template.caramel().noOcclusion(), WOODSET_CARAMELIZED));
    public static final RegistryObject<DoorBlock> CARAMELIZED_DOOR = registerBlock("caramelized_door", () -> new DoorBlock(Template.caramel().noOcclusion(), SET_CARAMELIZED));
    public static final RegistryObject<TrapDoorBlock> CARAMELIZED_TRAPDOOR = registerBlock("caramelized_trapdoor", () -> new TrapDoorBlock(Template.caramel().noOcclusion(), SET_CARAMELIZED));
    public static final RegistryObject<ButtonBlock> CARAMELIZED_BUTTON = registerBlock("caramelized_button", () -> new ButtonBlock(Template.caramel().noOcclusion().noCollission(), SET_CARAMELIZED, 30, true));
    public static final RegistryObject<PressurePlateBlock> CARAMELIZED_PRESSURE_PLATE = registerBlock("caramelized_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Template.caramel().noOcclusion().noCollission(), SET_CARAMELIZED));

    public static final RegistryObject<RotatedPillarBlock> KYANITE_ORE = registerBlock("kyanite_ore", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE).lightLevel((blockstate) -> 8)));
    public static final RegistryObject<Block> TANZANITE_ORE = registerBlock("tanzanite_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)));
    public static final RegistryObject<Block> TANZANITE_BLOCK = registerBlock("tanzanite_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> HAILSTONE = registerBlock("hailstone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRYING_OBSIDIAN)));

    public static final RegistryObject<Block> PERMAFROST = registerBlock("permafrost", () -> new PermafrostBlock(BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> PERMAFROST_RUBBLE = registerBlock("permafrost_rubble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIVING_SNOW_BLOCK = registerBlock("living_snow_block", () -> new LivingSnowBlock(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)));
    public static final RegistryObject<Block> SUGAR_SNOW_BLOCK = registerBlock("sugar_snow_block", () -> new LivingSnowBlock(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)));
    public static final RegistryObject<Block> SUGAR_SNOW = registerBlock("sugar_snow", () -> new SugarSnowBlock(BlockBehaviour.Properties.copy(Blocks.SNOW)));
    public static final RegistryObject<Block> CLOUD = registerBlock("cloud", () -> new CloudBlock(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK).sound(SoundType.WOOL).destroyTime(0.2F).strength(0.4F).noCollission().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PEAT = registerBlock("peat", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRAVEL).sound(SoundType.BASALT).requiresCorrectToolForDrops().destroyTime(1.0F)));

    public static final RegistryObject<KyaniteArrowBlock> EMBEDDED_KYANITE_ARROW = BLOCKS.register("embedded_kyanite_arrow", () -> new KyaniteArrowBlock(BlockBehaviour.Properties.of().noCollission().lightLevel((blockstate) -> 15)));
    public static final RegistryObject<RotatedPillarBlock> KYANITE_CABLE = registerBlock("kyanite_cable", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()));
    public static final RegistryObject<Block> KYANITE_BULB = registerBlock("kyanite_bulb", () -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistryObject<FluoriteBlock> FLUORITE = registerBlock("fluorite", () -> new FluoriteBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)));
    public static final RegistryObject<StaticFieldBlock> STATIC_FIELD = registerBlock("static_field", () -> new StaticFieldBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE)));

    public static final RegistryObject<BushBlock> HOLLY = registerBlock("holly", () -> new BushBlock(Template.plant()));
    public static final RegistryObject<Block> LICHEN_BLOCK = registerBlock("lichen_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)));
    public static final RegistryObject<MisteriaBody> MISTERIA_BODY = BLOCKS.register("misteria_plant", () -> new MisteriaBody(Template.plant()));
    public static final RegistryObject<MisteriaHead> MISTERIA_HEAD = registerBlock("misteria", () -> new MisteriaHead(Template.plant()));
    public static final RegistryObject<ShadedBushBlock> BRUMELIAD = registerBlock("brumeliad", () -> new ShadedBushBlock(Template.plant(), BlockTags.DIRT));
    public static final RegistryObject<ShadedBushBlock> WINTER_VIOLA = registerBlock("winter_viola", () -> new ShadedBushBlock(Template.plant(), List.of(BlockTags.LOGS, BlockTags.DIRT)));
    public static final RegistryObject<ShadedBushBlock> WINTER_VIOLIN = BLOCKS.register("winter_violin", () -> new ShadedBushBlock(Template.plant().offsetType(BlockBehaviour.OffsetType.XZ), BlockTags.LOGS));
    public static final RegistryObject<WallPlantBlock> WALL_WINTER_VIOLIN = BLOCKS.register("wall_winter_violin", () -> new WallPlantBlock(BlockTags.LOGS, Template.plant()));
    public static final RegistryObject<ShadedDoublePlantBlock> WINTER_CELLO = registerBlock("winter_cello", () -> new ShadedDoublePlantBlock(Template.plant(), BlockTags.DIRT));
    public static final RegistryObject<AlmsBlock> ALMS = registerBlock("alms", () -> new AlmsBlock(Template.petrifiedWood().mapColor(MapColor.DIRT)));
    public static final RegistryObject<AlmsCrackedBlock> CRACKED_ALMS = registerBlock("cracked_alms", () -> new AlmsCrackedBlock(Template.petrifiedWood().mapColor(MapColor.GOLD)));

    public static final RegistryObject<Block> CINNABAR = registerBlock("cinnabar", () -> new Block(Template.soapstone().sound(SoundType.BASALT).mapColor(MapColor.TERRACOTTA_MAGENTA)));

    public static final RegistryObject<KilnBlock> KILN = registerBlock("kiln", () -> new KilnBlock(BlockBehaviour.Properties.copy(Blocks.SMOKER)));

    private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        BorealisItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends Block> block) {
        return (RegistryObject<T>) baseRegister(name, block, BorealisBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
    }

    private static class Template {
        protected static BlockBehaviour.Properties soapstone() {
            return BlockBehaviour.Properties.copy(Blocks.STONE)
                    .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE);
        }
        protected static BlockBehaviour.Properties slate() {
            return BlockBehaviour.Properties.copy(Blocks.STONE)
                    .mapColor(MapColor.TERRACOTTA_BLUE);
        }
        protected static BlockBehaviour.Properties pumice() {
            return BlockBehaviour.Properties.copy(Blocks.SMOOTH_BASALT)
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
            return BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .mapColor(MapColor.COLOR_LIGHT_BLUE);
        }
        protected static BlockBehaviour.Properties frostfir() {
            return BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .mapColor(MapColor.TERRACOTTA_BLUE);
        }
        protected static BlockBehaviour.Properties sweetwood() {
            return BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .mapColor(MapColor.SAND);
        }
        protected static BlockBehaviour.Properties caramel() {
            return BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS)
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
            BlockBehaviour.Properties prop = BlockBehaviour.Properties.copy(Blocks.OAK_SIGN).mapColor(color);
            if (flammable) prop = prop.ignitedByLava();
            return prop;
        }
        protected static BlockBehaviour.Properties sign(MapColor color) { return sign(color, true); }

        protected static BlockBehaviour.Properties leaves() {
            return BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES);
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