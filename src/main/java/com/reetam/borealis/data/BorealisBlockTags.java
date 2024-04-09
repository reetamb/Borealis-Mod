package com.reetam.borealis.data;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

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
                        BorealisBlocks.BRUMAL_SIGN.get(), BorealisBlocks.BRUMAL_WALL_SIGN.get()
                    );
        tag(BorealisTags.Blocks.BLOCKSET_FROSTFIR)
                .add(
                        BorealisBlocks.FROSTFIR_BUTTON.get(), BorealisBlocks.FROSTFIR_PRESSURE_PLATE.get(),
                        BorealisBlocks.FROSTFIR_DOOR.get(), BorealisBlocks.FROSTFIR_TRAPDOOR.get(),
                        BorealisBlocks.FROSTFIR_FENCE.get(), BorealisBlocks.FROSTFIR_FENCE_GATE.get(),
                        BorealisBlocks.FROSTFIR_LOG.get(), BorealisBlocks.FROSTFIR_WOOD.get(), BorealisBlocks.STRIPPED_FROSTFIR_LOG.get(), BorealisBlocks.STRIPPED_FROSTFIR_WOOD.get(),
                        BorealisBlocks.FROSTFIR_PLANKS.get(),
                        BorealisBlocks.FROSTFIR_SLAB.get(), BorealisBlocks.FROSTFIR_STAIRS.get(),
                        BorealisBlocks.FROSTFIR_SIGN.get(), BorealisBlocks.FROSTFIR_WALL_SIGN.get()
                );
        tag(BorealisTags.Blocks.BLOCKSET_SACCHARINE)
                .add(
                        BorealisBlocks.SACCHARINE_BUTTON.get(), BorealisBlocks.SACCHARINE_PRESSURE_PLATE.get(),
                        BorealisBlocks.SACCHARINE_DOOR.get(), BorealisBlocks.SACCHARINE_TRAPDOOR.get(),
                        BorealisBlocks.SACCHARINE_FENCE.get(), BorealisBlocks.SACCHARINE_FENCE_GATE.get(),
                        BorealisBlocks.SACCHARINE_LOG.get(), BorealisBlocks.SACCHARINE_WOOD.get(), BorealisBlocks.STRIPPED_SACCHARINE_LOG.get(), BorealisBlocks.STRIPPED_SACCHARINE_WOOD.get(),
                        BorealisBlocks.SACCHARINE_PLANKS.get(),
                        BorealisBlocks.SACCHARINE_SLAB.get(), BorealisBlocks.SACCHARINE_STAIRS.get(),
                        BorealisBlocks.SACCHARINE_SIGN.get(), BorealisBlocks.SACCHARINE_WALL_SIGN.get()
                );
        tag(BorealisTags.Blocks.BLOCKSET_SOAPSTONE)
                .add(
                        BorealisBlocks.SOAPSTONE.get(), BorealisBlocks.SOAPSTONE_SLAB.get(), BorealisBlocks.SOAPSTONE_STAIRS.get(), BorealisBlocks.SOAPSTONE_WALL.get(),
                        BorealisBlocks.SOAPSTONE_BRICKS.get(), BorealisBlocks.SOAPSTONE_BRICK_SLAB.get(), BorealisBlocks.SOAPSTONE_BRICK_STAIRS.get(), BorealisBlocks.SOAPSTONE_BRICK_WALL.get(),
                        BorealisBlocks.SOAPSTONE_TILES.get(), BorealisBlocks.SOAPSTONE_TILE_SLAB.get(), BorealisBlocks.SOAPSTONE_TILE_STAIRS.get(), BorealisBlocks.SOAPSTONE_TILE_WALL.get(),
                        BorealisBlocks.SOAPSTONE_BUTTON.get(), BorealisBlocks.SOAPSTONE_PRESSURE_PLATE.get()
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
        tag(BorealisTags.Blocks.FROSTFIR_LOGS)
                .add(BorealisBlocks.FROSTFIR_LOG.get())
                .add(BorealisBlocks.FROSTFIR_WOOD.get())
                .add(BorealisBlocks.STRIPPED_FROSTFIR_LOG.get())
                .add(BorealisBlocks.STRIPPED_FROSTFIR_WOOD.get());
        tag(BorealisTags.Blocks.SACCHARINE_LOGS)
                .add(BorealisBlocks.SACCHARINE_LOG.get())
                .add(BorealisBlocks.SACCHARINE_WOOD.get())
                .add(BorealisBlocks.STRIPPED_SACCHARINE_LOG.get())
                .add(BorealisBlocks.STRIPPED_SACCHARINE_WOOD.get());
        tag(BorealisTags.Blocks.PORTAL_FRAME_BLOCKS)
                .add(Blocks.ICE)
                .add(Blocks.PACKED_ICE)
                .add(Blocks.BLUE_ICE)
                .add(Blocks.SNOW_BLOCK);
        tag(BorealisTags.Blocks.PORTAL_CENTER_BLOCKS)
                .add(Blocks.SNOW)
                .add(BorealisBlocks.SUGAR_SNOW.get());
        tag(BorealisTags.Blocks.SNOWY_BLOCKS)
                .add(Blocks.SNOW)
                .add(Blocks.SNOW_BLOCK)
                .add(BorealisBlocks.LIVING_SNOW_BLOCK.get());
        tag(BorealisTags.Blocks.SUGARY_BLOCKS)
                .add(BorealisBlocks.SUGAR_SNOW.get())
                .add(BorealisBlocks.SUGAR_SNOW_BLOCK.get());
        tag(BorealisTags.Blocks.ICY_BLOCKS)
                .add(Blocks.ICE)
                .add(Blocks.PACKED_ICE)
                .add(Blocks.BLUE_ICE);
        tag(BorealisTags.Blocks.SOAPSTONE_ORE_REPLACEABLES)
                .add(BorealisBlocks.SOAPSTONE.get());
    }
    
    private void minecraftTags() {
        tag(BlockTags.SAPLINGS)
                .add(BorealisBlocks.BRUMAL_SAPLING.get())
                .add(BorealisBlocks.FROSTFIR_SAPLING.get())
                .add(BorealisBlocks.SACCHARINE_SAPLING.get());
        tag(BlockTags.PLANKS)
                .add(BorealisBlocks.BRUMAL_PLANKS.get())
                .add(BorealisBlocks.FROSTFIR_PLANKS.get())
                .add(BorealisBlocks.SACCHARINE_PLANKS.get());
        tag(BlockTags.WOODEN_BUTTONS)
                .add(BorealisBlocks.BRUMAL_BUTTON.get())
                .add(BorealisBlocks.FROSTFIR_BUTTON.get())
                .add(BorealisBlocks.SACCHARINE_BUTTON.get());
        tag(BlockTags.WOODEN_DOORS)
                .add(BorealisBlocks.BRUMAL_DOOR.get())
                .add(BorealisBlocks.FROSTFIR_DOOR.get())
                .add(BorealisBlocks.SACCHARINE_DOOR.get());
        tag(BlockTags.WOODEN_STAIRS)
                .add(BorealisBlocks.BRUMAL_STAIRS.get())
                .add(BorealisBlocks.FROSTFIR_STAIRS.get())
                .add(BorealisBlocks.SACCHARINE_STAIRS.get());
        tag(BlockTags.WOODEN_SLABS)
                .add(BorealisBlocks.BRUMAL_SLAB.get())
                .add(BorealisBlocks.FROSTFIR_SLAB.get())
                .add(BorealisBlocks.SACCHARINE_SLAB.get());
        tag(BlockTags.WOODEN_FENCES)
                .add(BorealisBlocks.BRUMAL_FENCE.get())
                .add(BorealisBlocks.FROSTFIR_FENCE.get())
                .add(BorealisBlocks.SACCHARINE_FENCE.get());
        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(BorealisTags.Blocks.BRUMAL_LOGS)
                .addTag(BorealisTags.Blocks.FROSTFIR_LOGS)
                .addTag(BorealisTags.Blocks.SACCHARINE_LOGS);
        tag(BlockTags.LOGS)
                .addTag(BorealisTags.Blocks.BRUMAL_LOGS)
                .addTag(BorealisTags.Blocks.FROSTFIR_LOGS)
                .addTag(BorealisTags.Blocks.SACCHARINE_LOGS);
        tag(BlockTags.ENDERMAN_HOLDABLE)
                .add(BorealisBlocks.LIVING_SNOW_BLOCK.get())
                .add(BorealisBlocks.SUGAR_SNOW_BLOCK.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(BorealisBlocks.BRUMAL_PRESSURE_PLATE.get())
                .add(BorealisBlocks.FROSTFIR_PRESSURE_PLATE.get())
                .add(BorealisBlocks.SACCHARINE_PRESSURE_PLATE.get());
        tag(BlockTags.STONE_PRESSURE_PLATES)
                .add(BorealisBlocks.SOAPSTONE_PRESSURE_PLATE.get());
        tag(BlockTags.WALLS)
                .add(BorealisBlocks.SOAPSTONE_WALL.get())
                .add(BorealisBlocks.SOAPSTONE_TILE_WALL.get())
                .add(BorealisBlocks.SOAPSTONE_BRICK_WALL.get());
        tag(BlockTags.LEAVES)
                .add(BorealisBlocks.BRUMAL_LEAVES.get())
                .add(BorealisBlocks.FROSTFIR_LEAVES.get())
                .add(BorealisBlocks.SACCHARINE_LEAVES.get());
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(BorealisBlocks.BRUMAL_TRAPDOOR.get())
                .add(BorealisBlocks.FROSTFIR_TRAPDOOR.get())
                .add(BorealisBlocks.SACCHARINE_TRAPDOOR.get());
        tag(BlockTags.FENCE_GATES)
                .add(BorealisBlocks.BRUMAL_FENCE_GATE.get())
                .add(BorealisBlocks.FROSTFIR_FENCE_GATE.get())
                .add(BorealisBlocks.SACCHARINE_FENCE_GATE.get());
        tag(BlockTags.STANDING_SIGNS)
                .add(BorealisBlocks.BRUMAL_SIGN.get())
                .add(BorealisBlocks.FROSTFIR_SIGN.get())
                .add(BorealisBlocks.SACCHARINE_SIGN.get());
        tag(BlockTags.WALL_SIGNS)
                .add(BorealisBlocks.BRUMAL_WALL_SIGN.get())
                .add(BorealisBlocks.FROSTFIR_WALL_SIGN.get())
                .add(BorealisBlocks.SACCHARINE_WALL_SIGN.get());
        tag(BlockTags.SIGNS)
                .add(BorealisBlocks.BRUMAL_SIGN.get())
                .add(BorealisBlocks.FROSTFIR_SIGN.get())
                .add(BorealisBlocks.SACCHARINE_SIGN.get())
                .add(BorealisBlocks.BRUMAL_WALL_SIGN.get())
                .add(BorealisBlocks.FROSTFIR_WALL_SIGN.get())
                .add(BorealisBlocks.SACCHARINE_WALL_SIGN.get());
        tag(BlockTags.DIRT)
                .add(BorealisBlocks.LIVING_SNOW_BLOCK.get())
                .add(BorealisBlocks.SUGAR_SNOW_BLOCK.get())
                .add(BorealisBlocks.PERMAFROST.get())
                .add(BorealisBlocks.PERMAFROST_RUBBLE.get());
    }
    
    private void forgeTags() {
        tag(Tags.Blocks.COBBLESTONE)
                .add(BorealisBlocks.SOAPSTONE.get())
                .add(BorealisBlocks.SLATE.get());
        tag(Tags.Blocks.FENCE_GATES_WOODEN)
                .add(BorealisBlocks.BRUMAL_FENCE_GATE.get())
                .add(BorealisBlocks.FROSTFIR_FENCE_GATE.get())
                .add(BorealisBlocks.SACCHARINE_FENCE_GATE.get());
        tag(Tags.Blocks.FENCES_WOODEN)
                .add(BorealisBlocks.BRUMAL_FENCE.get())
                .add(BorealisBlocks.FROSTFIR_FENCE.get())
                .add(BorealisBlocks.SACCHARINE_FENCE.get());
        tag(Tags.Blocks.STONE)
                .add(BorealisBlocks.SOAPSTONE.get())
                .add(BorealisBlocks.SLATE.get());
    }

    private void progressionTags() {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BorealisTags.Blocks.BLOCKSET_BRUMAL)
                .addTag(BorealisTags.Blocks.BLOCKSET_FROSTFIR)
                .addTag(BorealisTags.Blocks.BLOCKSET_SACCHARINE);
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(BorealisTags.Blocks.BLOCKSET_SOAPSTONE)
                .add(BorealisBlocks.SLATE.get(), BorealisBlocks.SLATE_PILLAR.get(), BorealisBlocks.SLATE_TILES.get(), BorealisBlocks.STARRY_SLATE.get(), BorealisBlocks.STARRY_SLATE_TILES.get())
                .add(BorealisBlocks.PUMICE.get(), BorealisBlocks.PUMICE_GEYSER.get(), BorealisBlocks.TRAVERTINE.get())
                .add(BorealisBlocks.KYANITE_ORE.get(), BorealisBlocks.TANZANITE_BLOCK.get(), BorealisBlocks.TANZANITE_ORE.get(), BorealisBlocks.HAILSTONE.get())
                .add(BorealisBlocks.PETRIFIED_WOOD.get(), BorealisBlocks.PETRIFIED_WOOD_BRICKS.get(), BorealisBlocks.BONE_DRY_WOOD.get(), BorealisBlocks.BONE_DRY_WOOD_BRICKS.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(BorealisBlocks.SUGAR_SNOW_BLOCK.get(), BorealisBlocks.LIVING_SNOW_BLOCK.get(), BorealisBlocks.SUGAR_SNOW.get())
                .add(BorealisBlocks.PERMAFROST.get(), BorealisBlocks.PERMAFROST_RUBBLE.get());
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(BorealisBlocks.CLOUD.get())
                .add(BorealisBlocks.BRUMAL_LEAVES.get(), BorealisBlocks.FROSTFIR_LEAVES.get(), BorealisBlocks.SACCHARINE_LEAVES.get());
        tag(BlockTags.SWORD_EFFICIENT)
                .add(BorealisBlocks.CLOUD.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(BorealisBlocks.KYANITE_ORE.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BorealisBlocks.HAILSTONE.get(), BorealisBlocks.TANZANITE_BLOCK.get(), BorealisBlocks.TANZANITE_ORE.get());
    }
}
