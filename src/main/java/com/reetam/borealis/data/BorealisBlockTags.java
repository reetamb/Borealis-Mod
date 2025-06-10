package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.rootplacers.AboveRootPlacement;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BorealisBlockTags extends BlockTagsProvider {

    public BorealisBlockTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, registries, BorealisMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Borealis Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        blocksetTags();
        modTags();
        minecraftTags();
        forgeTags();
        progressionTags();
    }
    private void blocksetTags() {
        tag(BorealisTags.Blocks.BLOCKSET_BRUMAL)
                .add(
                        BorealisBlocks.BRUMAL_BUTTON.get(), BorealisBlocks.BRUMAL_PRESSURE_PLATE.get(),
                        BorealisBlocks.BRUMAL_DOOR.get(), BorealisBlocks.BRUMAL_TRAPDOOR.get(),
                        BorealisBlocks.BRUMAL_FENCE.get(), BorealisBlocks.BRUMAL_FENCE_GATE.get(),
                        BorealisBlocks.BRUMAL_LOG.get(), BorealisBlocks.BRUMAL_WOOD.get(), BorealisBlocks.STRIPPED_BRUMAL_LOG.get(), BorealisBlocks.STRIPPED_BRUMAL_WOOD.get(),
                        BorealisBlocks.BRUMAL_PLANKS.get(),
                        BorealisBlocks.BRUMAL_SLAB.get(), BorealisBlocks.BRUMAL_STAIRS.get(),
                        BorealisBlocks.BRUMAL_SIGN.get(), BorealisBlocks.BRUMAL_WALL_SIGN.get(),
                        BorealisBlocks.BRUMAL_HANGING_SIGN.get(), BorealisBlocks.BRUMAL_WALL_HANGING_SIGN.get()
                    );
        tag(BorealisTags.Blocks.BLOCKSET_SWEETWOOD)
                .add(
                        BorealisBlocks.SWEETWOOD_BUTTON.get(), BorealisBlocks.SWEETWOOD_PRESSURE_PLATE.get(),
                        BorealisBlocks.SWEETWOOD_DOOR.get(), BorealisBlocks.SWEETWOOD_TRAPDOOR.get(),
                        BorealisBlocks.SWEETWOOD_FENCE.get(), BorealisBlocks.SWEETWOOD_FENCE_GATE.get(),
                        BorealisBlocks.SWEETWOOD_LOG.get(), BorealisBlocks.SWEETWOOD.get(), BorealisBlocks.STRIPPED_SWEETWOOD_LOG.get(), BorealisBlocks.STRIPPED_SWEETWOOD.get(),
                        BorealisBlocks.SWEETWOOD_PLANKS.get(),
                        BorealisBlocks.SWEETWOOD_SLAB.get(), BorealisBlocks.SWEETWOOD_STAIRS.get(),
                        BorealisBlocks.SWEETWOOD_SIGN.get(), BorealisBlocks.SWEETWOOD_WALL_SIGN.get(),
                        BorealisBlocks.SWEETWOOD_HANGING_SIGN.get(), BorealisBlocks.SWEETWOOD_WALL_HANGING_SIGN.get()
                );
        tag(BorealisTags.Blocks.BLOCKSET_SOAPSTONE)
                .add(
                        BorealisBlocks.SOAPSTONE.get(), BorealisBlocks.SOAPSTONE_SLAB.get(), BorealisBlocks.SOAPSTONE_STAIRS.get(), BorealisBlocks.SOAPSTONE_WALL.get(),
                        BorealisBlocks.SOAPSTONE_BRICKS.get(), BorealisBlocks.SOAPSTONE_BRICK_SLAB.get(), BorealisBlocks.SOAPSTONE_BRICK_STAIRS.get(), BorealisBlocks.SOAPSTONE_BRICK_WALL.get(),
                        BorealisBlocks.SOAPSTONE_TILES.get(), BorealisBlocks.SOAPSTONE_TILE_SLAB.get(), BorealisBlocks.SOAPSTONE_TILE_STAIRS.get(), BorealisBlocks.SOAPSTONE_TILE_WALL.get(),
                        BorealisBlocks.SOAPSTONE_BUTTON.get(), BorealisBlocks.SOAPSTONE_PRESSURE_PLATE.get()
                );
        tag(BorealisTags.Blocks.BLOCKSET_CARAMELIZED)
                .add(
                        BorealisBlocks.CARAMELIZED_BUTTON.get(), BorealisBlocks.CARAMELIZED_PRESSURE_PLATE.get(),
                        BorealisBlocks.CARAMELIZED_DOOR.get(), BorealisBlocks.CARAMELIZED_TRAPDOOR.get(),
                        BorealisBlocks.CARAMELIZED_FENCE.get(), BorealisBlocks.CARAMELIZED_FENCE_GATE.get(),
                        BorealisBlocks.CARAMELIZED_LOG.get(), BorealisBlocks.CARAMELIZED_WOOD.get(), BorealisBlocks.STRIPPED_CARAMELIZED_LOG.get(), BorealisBlocks.STRIPPED_CARAMELIZED_WOOD.get(),
                        BorealisBlocks.CARAMELIZED_PLANKS.get(),
                        BorealisBlocks.CARAMELIZED_SLAB.get(), BorealisBlocks.CARAMELIZED_STAIRS.get(),
                        BorealisBlocks.CARAMELIZED_SIGN.get(), BorealisBlocks.CARAMELIZED_WALL_SIGN.get(),
                        BorealisBlocks.CARAMELIZED_HANGING_SIGN.get(), BorealisBlocks.CARAMELIZED_WALL_HANGING_SIGN.get()
                );
    }
    
    private void modTags() {
        tag(BorealisTags.Blocks.BASE_STONE_BOREALIS)
                .add(BorealisBlocks.SOAPSTONE.get())
                .add(BorealisBlocks.SLATE.get());
        tag(BorealisTags.Blocks.BRUMAL_LOGS)
                .add(BorealisBlocks.BRUMAL_LOG.get())
                .add(BorealisBlocks.BRUMAL_WOOD.get())
                .add(BorealisBlocks.STRIPPED_BRUMAL_LOG.get())
                .add(BorealisBlocks.STRIPPED_BRUMAL_WOOD.get());
        tag(BorealisTags.Blocks.SWEETWOOD_LOGS)
                .add(BorealisBlocks.SWEETWOOD_LOG.get())
                .add(BorealisBlocks.SWEETWOOD.get())
                .add(BorealisBlocks.STRIPPED_SWEETWOOD_LOG.get())
                .add(BorealisBlocks.STRIPPED_SWEETWOOD.get());
        tag(BorealisTags.Blocks.CARAMELIZED_LOGS)
                .add(BorealisBlocks.CARAMELIZED_LOG.get())
                .add(BorealisBlocks.CARAMELIZED_WOOD.get())
                .add(BorealisBlocks.STRIPPED_CARAMELIZED_LOG.get())
                .add(BorealisBlocks.STRIPPED_CARAMELIZED_WOOD.get());
        tag(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)
                .add(BorealisBlocks.KYANITE_CABLE.get())
                .add(BorealisBlocks.KYANITE_FLAGSTONE.get())
                .add(BorealisBlocks.KYANITE_ORE.get());
        tag(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS)
                .add(Blocks.SNOW)
                .add(BorealisBlocks.SUGAR_SNOW.get());
        tag(BorealisTags.Blocks.SNOWY_BLOCKS)
                .add(Blocks.SNOW)
                .add(Blocks.SNOW_BLOCK)
                .add(BorealisBlocks.FIRN.get());
        tag(BorealisTags.Blocks.SUGARY_BLOCKS)
                .add(BorealisBlocks.SUGAR_SNOW.get())
                .add(BorealisBlocks.SUGAR_SNOW_BLOCK.get());
        tag(BorealisTags.Blocks.ICY_BLOCKS)
                .add(Blocks.ICE)
                .add(Blocks.PACKED_ICE)
                .add(Blocks.BLUE_ICE);
        tag(BorealisTags.Blocks.SOAPSTONE_ORE_REPLACEABLES)
                .add(BorealisBlocks.SOAPSTONE.get());
        tag(BorealisTags.Blocks.MINEABLE_WITH_SWORD)
                .addTag(BlockTags.SWORD_EFFICIENT)
                .add(Blocks.COBWEB, Blocks.BAMBOO, Blocks.BAMBOO_SAPLING);
        tag(BorealisTags.Blocks.TAPPABLE)
                .add(BorealisBlocks.BRUMAL_LOG.get())
                .add(BorealisBlocks.BRUMAL_WOOD.get())
                .add(BorealisBlocks.SWEETWOOD_LOG.get())
                .add(BorealisBlocks.SWEETWOOD.get())
                .add(BorealisBlocks.STRIPPED_SWEETWOOD_LOG.get())
                .add(BorealisBlocks.STRIPPED_SWEETWOOD.get())
                .add(BorealisBlocks.PETRIFIED_WOOD.get())
                .add(BorealisBlocks.BONE_DRY_WOOD.get())
                .add(BorealisBlocks.PUMICE_GEYSER.get())
                .add(BorealisBlocks.ALMS.get())
                .add(BorealisBlocks.CRACKED_ALMS.get());
    }
    
    private void minecraftTags() {
        tag(BlockTags.PLANKS)
                .add(BorealisBlocks.BRUMAL_PLANKS.get())
                .add(BorealisBlocks.SWEETWOOD_PLANKS.get())
                .add(BorealisBlocks.CARAMELIZED_PLANKS.get());
        tag(BlockTags.WOODEN_BUTTONS)
                .add(BorealisBlocks.BRUMAL_BUTTON.get())
                .add(BorealisBlocks.SWEETWOOD_BUTTON.get())
                .add(BorealisBlocks.CARAMELIZED_BUTTON.get());
        tag(BlockTags.WOODEN_DOORS)
                .add(BorealisBlocks.BRUMAL_DOOR.get())
                .add(BorealisBlocks.SWEETWOOD_DOOR.get())
                .add(BorealisBlocks.CARAMELIZED_DOOR.get());
        tag(BlockTags.WOODEN_STAIRS)
                .add(BorealisBlocks.BRUMAL_STAIRS.get())
                .add(BorealisBlocks.SWEETWOOD_STAIRS.get())
                .add(BorealisBlocks.CARAMELIZED_STAIRS.get());
        tag(BlockTags.WOODEN_SLABS)
                .add(BorealisBlocks.BRUMAL_SLAB.get())
                .add(BorealisBlocks.SWEETWOOD_SLAB.get())
                .add(BorealisBlocks.CARAMELIZED_SLAB.get());
        tag(BlockTags.WOODEN_FENCES)
                .add(BorealisBlocks.BRUMAL_FENCE.get())
                .add(BorealisBlocks.SWEETWOOD_FENCE.get())
                .add(BorealisBlocks.CARAMELIZED_FENCE.get());
        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(BorealisTags.Blocks.BRUMAL_LOGS)
                .addTag(BorealisTags.Blocks.SWEETWOOD_LOGS)
                .addTag(BorealisTags.Blocks.CARAMELIZED_LOGS)
                .add(BorealisBlocks.PETRIFIED_WOOD.get());
        tag(BlockTags.LOGS)
                .addTag(BorealisTags.Blocks.BRUMAL_LOGS)
                .addTag(BorealisTags.Blocks.SWEETWOOD_LOGS)
                .addTag(BorealisTags.Blocks.CARAMELIZED_LOGS)
                .add(BorealisBlocks.PETRIFIED_WOOD.get());
        tag(BlockTags.ENDERMAN_HOLDABLE)
                .add(BorealisBlocks.FIRN.get())
                .add(BorealisBlocks.WILLOWY_FIRN.get())
                .add(BorealisBlocks.SUGAR_SNOW_BLOCK.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(BorealisBlocks.BRUMAL_PRESSURE_PLATE.get())
                .add(BorealisBlocks.SWEETWOOD_PRESSURE_PLATE.get())
                .add(BorealisBlocks.CARAMELIZED_PRESSURE_PLATE.get());
        tag(BlockTags.STONE_PRESSURE_PLATES)
                .add(BorealisBlocks.SOAPSTONE_PRESSURE_PLATE.get());
        tag(BlockTags.WALLS)
                .add(BorealisBlocks.SOAPSTONE_WALL.get())
                .add(BorealisBlocks.SOAPSTONE_TILE_WALL.get())
                .add(BorealisBlocks.SOAPSTONE_BRICK_WALL.get());
        tag(BlockTags.LEAVES)
                .add(BorealisBlocks.BRUMAL_LEAVES.get())
                .add(BorealisBlocks.FROSTFIR_LEAVES.get())
                .add(BorealisBlocks.SWEETWOOD_LEAVES.get())
                .add(BorealisBlocks.GLAZED_LEAVES.get());
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(BorealisBlocks.BRUMAL_TRAPDOOR.get())
                .add(BorealisBlocks.SWEETWOOD_TRAPDOOR.get())
                .add(BorealisBlocks.CARAMELIZED_TRAPDOOR.get());
        tag(BlockTags.FENCE_GATES)
                .add(BorealisBlocks.BRUMAL_FENCE_GATE.get())
                .add(BorealisBlocks.SWEETWOOD_FENCE_GATE.get())
                .add(BorealisBlocks.CARAMELIZED_FENCE_GATE.get());
        tag(BlockTags.STANDING_SIGNS)
                .add(BorealisBlocks.BRUMAL_SIGN.get())
                .add(BorealisBlocks.SWEETWOOD_SIGN.get())
                .add(BorealisBlocks.CARAMELIZED_SIGN.get());;
        tag(BlockTags.WALL_SIGNS)
                .add(BorealisBlocks.BRUMAL_WALL_SIGN.get())
                .add(BorealisBlocks.SWEETWOOD_WALL_SIGN.get())
                .add(BorealisBlocks.CARAMELIZED_WALL_SIGN.get());
        tag(BlockTags.CEILING_HANGING_SIGNS)
                .add(BorealisBlocks.BRUMAL_HANGING_SIGN.get())
                .add(BorealisBlocks.SWEETWOOD_HANGING_SIGN.get())
                .add(BorealisBlocks.CARAMELIZED_HANGING_SIGN.get());
        tag(BlockTags.WALL_HANGING_SIGNS)
                .add(BorealisBlocks.BRUMAL_WALL_HANGING_SIGN.get())
                .add(BorealisBlocks.SWEETWOOD_WALL_HANGING_SIGN.get())
                .add(BorealisBlocks.CARAMELIZED_WALL_HANGING_SIGN.get());
        tag(BlockTags.SIGNS)
                .addTag(BlockTags.STANDING_SIGNS).addTag(BlockTags.WALL_SIGNS);
        tag(BlockTags.ALL_HANGING_SIGNS)
                .addTag(BlockTags.CEILING_HANGING_SIGNS).addTag(BlockTags.WALL_HANGING_SIGNS);
        tag(BlockTags.DIRT)
                .add(BorealisBlocks.FIRN.get())
                .add(BorealisBlocks.WILLOWY_FIRN.get())
                .add(BorealisBlocks.SUGAR_SNOW_BLOCK.get())
                .add(BorealisBlocks.LICHEN_BLOCK.get());
        tag(BlockTags.FLOWERS)
                .add(BorealisBlocks.BRUMELIAD.get())
                .add(BorealisBlocks.HOLLY.get())
                .add(BorealisBlocks.WINTER_VIOLA.get())
                .add(BorealisBlocks.WINTER_FIDDLE.get())
                .add(BorealisBlocks.WALL_WINTER_FIDDLE.get())
                .add(BorealisBlocks.WINTER_CELLO.get());
        tag(BlockTags.CLIMBABLE).add(BorealisBlocks.BOREALIS_PORTAL.get());
    }
    
    private void forgeTags() {
        tag(Tags.Blocks.COBBLESTONES)
                .add(BorealisBlocks.SOAPSTONE.get())
                .add(BorealisBlocks.SLATE.get());
        tag(Tags.Blocks.FENCE_GATES_WOODEN)
                .addTag(BlockTags.FENCE_GATES);
        tag(Tags.Blocks.FENCES_WOODEN)
                .add(BorealisBlocks.BRUMAL_FENCE.get())
                .add(BorealisBlocks.SWEETWOOD_FENCE.get())
                .add(BorealisBlocks.CARAMELIZED_FENCE.get());
        tag(Tags.Blocks.STONES)
                .add(BorealisBlocks.SOAPSTONE.get())
                .add(BorealisBlocks.SLATE.get());
    }

    private void progressionTags() {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BorealisTags.Blocks.BLOCKSET_BRUMAL)
                .addTag(BorealisTags.Blocks.BLOCKSET_SWEETWOOD)
                .addTag(BorealisTags.Blocks.BLOCKSET_CARAMELIZED)
                .add(BorealisBlocks.PETRIFIED_WOOD.get(), BorealisBlocks.PETRIFIED_WOOD_BRICKS.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(BorealisTags.Blocks.BLOCKSET_SOAPSTONE)
                .add(BorealisBlocks.SLATE.get(), BorealisBlocks.SLATE_PILLAR.get(), BorealisBlocks.SLATE_TILES.get(), BorealisBlocks.STARRY_SLATE.get(), BorealisBlocks.STARRY_SLATE_TILES.get())
                .add(BorealisBlocks.PUMICE.get(), BorealisBlocks.PUMICE_GEYSER.get(), BorealisBlocks.GYPSUM.get())
                .add(BorealisBlocks.KYANITE_ORE.get(), BorealisBlocks.TANZANITE_BLOCK.get(), BorealisBlocks.TANZANITE_ORE.get(), BorealisBlocks.HAILSTONE.get(), BorealisBlocks.KYANITE_FLAGSTONE.get(), BorealisBlocks.KYANITE_CABLE.get(), BorealisBlocks.KYANITE_BULB.get())
                .add(BorealisBlocks.PETRIFIED_WOOD.get(), BorealisBlocks.PETRIFIED_WOOD_BRICKS.get(), BorealisBlocks.BONE_DRY_WOOD.get(), BorealisBlocks.BONE_DRY_WOOD_BRICKS.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(BorealisBlocks.SUGAR_SNOW_BLOCK.get(), BorealisBlocks.FIRN.get(), BorealisBlocks.WILLOWY_FIRN.get(), BorealisBlocks.SUGAR_SNOW.get())
                .add(BorealisBlocks.KAOLIN.get())
                .add(BorealisBlocks.PEAT.get())
                .add(BorealisBlocks.CLOUD.get());
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(BorealisBlocks.CLOUD.get())
                .add(BorealisBlocks.BRUMAL_LEAVES.get(), BorealisBlocks.FROSTFIR_LEAVES.get(), BorealisBlocks.SWEETWOOD_LEAVES.get(), BorealisBlocks.GLAZED_LEAVES.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(BorealisBlocks.KYANITE_ORE.get())
                .add(BorealisBlocks.PEAT.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BorealisBlocks.HAILSTONE.get(), BorealisBlocks.TANZANITE_BLOCK.get(), BorealisBlocks.TANZANITE_ORE.get(), BorealisBlocks.KYANITE_CABLE.get(), BorealisBlocks.KYANITE_BULB.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BorealisBlocks.KYANITE_FLAGSTONE.get());
    }
}
