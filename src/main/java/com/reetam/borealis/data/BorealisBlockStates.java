package com.reetam.borealis.data;

import com.reetam.borealis.block.*;
import com.reetam.borealis.block.property.PermafrostCover;
import com.reetam.borealis.data.provider.BorealisBlockStateProvider;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class BorealisBlockStates extends BorealisBlockStateProvider {

    public BorealisBlockStates(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override @NotNull
    public String getName() {
        return "Borealis Block States";
    }

    @Override
    protected void registerStatesAndModels() {
        block(BorealisBlocks.SOAPSTONE);
        block(BorealisBlocks.SOAPSTONE_TILES);
        block(BorealisBlocks.SOAPSTONE_BRICKS);
        logBlock(BorealisBlocks.KYANITE_ORE.get());
        stairs(BorealisBlocks.SOAPSTONE_STAIRS, BorealisBlocks.SOAPSTONE);
        stairs(BorealisBlocks.SOAPSTONE_TILE_STAIRS, BorealisBlocks.SOAPSTONE_TILES);
        stairs(BorealisBlocks.SOAPSTONE_BRICK_STAIRS, BorealisBlocks.SOAPSTONE_BRICKS);
        slab(BorealisBlocks.SOAPSTONE_SLAB, BorealisBlocks.SOAPSTONE);
        slab(BorealisBlocks.SOAPSTONE_TILE_SLAB, BorealisBlocks.SOAPSTONE_TILES);
        slab(BorealisBlocks.SOAPSTONE_BRICK_SLAB, BorealisBlocks.SOAPSTONE_BRICKS);
        wallBlock(BorealisBlocks.SOAPSTONE_WALL.get(), texture("soapstone"));
        wallBlock(BorealisBlocks.SOAPSTONE_TILE_WALL.get(), texture("soapstone_tiles"));
        wallBlock(BorealisBlocks.SOAPSTONE_BRICK_WALL.get(), texture("soapstone_bricks"));
        block(BorealisBlocks.SLATE);
        log(BorealisBlocks.SLATE_PILLAR, "slate_pillar");
        block(BorealisBlocks.SLATE_TILES);
        block(BorealisBlocks.PUMICE);
        block(BorealisBlocks.SCORIA);
        block(BorealisBlocks.PETRIFIED_WOOD);
        block(BorealisBlocks.PETRIFIED_WOOD_BRICKS);
        block(BorealisBlocks.LIVING_SNOW_BLOCK);
        block(BorealisBlocks.SUGAR_SNOW_BLOCK);
        block(BorealisBlocks.PERMAFROST_RUBBLE);
        buttonBlock(BorealisBlocks.SOAPSTONE_BUTTON.get(), texture("soapstone"));
        pressurePlateBlock(BorealisBlocks.SOAPSTONE_PRESSURE_PLATE.get(), texture("soapstone"));
        block(BorealisBlocks.TANZANITE_ORE);
        block(BorealisBlocks.TANZANITE_BLOCK);
        block(BorealisBlocks.STARRY_SLATE);
        block(BorealisBlocks.STARRY_SLATE_TILES);
        block(BorealisBlocks.CLOUD);
        block(BorealisBlocks.HAILSTONE);
        block(BorealisBlocks.BONE_DRY_WOOD);
        block(BorealisBlocks.BONE_DRY_WOOD_BRICKS);

        getVariantBuilder(BorealisBlocks.PERMAFROST.get())
                .partialState().with(PermafrostBlock.COVER, PermafrostCover.CLEAR).modelForState()
                    .modelFile(this.models().cubeAll("permafrost", texture("permafrost"))).addModel()
                .partialState().with(PermafrostBlock.COVER, PermafrostCover.ICY).modelForState()
                        .modelFile(this.models().cubeBottomTop("icy_permafrost", texture("icy_permafrost_side"), texture("permafrost"), mcLoc("block/packed_ice"))).addModel()
                .partialState().with(PermafrostBlock.COVER, PermafrostCover.SNOWY).modelForState()
                    .modelFile(this.models().cubeBottomTop("snowy_permafrost", texture("snowy_permafrost_side"), texture("permafrost"), mcLoc("block/snow"))).addModel()
                .partialState().with(PermafrostBlock.COVER, PermafrostCover.SUGARY).modelForState()
                    .modelFile(this.models().cubeBottomTop("sugary_permafrost", texture("sugary_permafrost_side"), texture("permafrost"), texture("sugar_snow_block"))).addModel();

        simpleBlock(BorealisBlocks.PUMICE_GEYSER.get(), this.models().cubeTop("pumice_geyser", texture("pumice"), texture("pumice_geyser")));
        block(BorealisBlocks.PEAT);

        getVariantBuilder(BorealisBlocks.EMBEDDED_KYANITE_ARROW.get())
                .partialState().with(KyaniteArrowBlock.FACING, Direction.UP).modelForState()
                        .modelFile(this.models().cross("up_kyanite_arrow", texture("kyanite_arrow"))).rotationX(0).addModel()
                .partialState().with(KyaniteArrowBlock.FACING, Direction.DOWN).modelForState()
                        .modelFile(this.models().cross("down_kyanite_arrow", texture("kyanite_arrow"))).rotationX(180).addModel()
                .partialState().with(KyaniteArrowBlock.FACING, Direction.SOUTH).modelForState()
                        .modelFile(this.models().cross("south_kyanite_arrow", texture("kyanite_arrow"))).rotationX(90).rotationY(180).addModel()
                .partialState().with(KyaniteArrowBlock.FACING, Direction.WEST).modelForState()
                        .modelFile(this.models().cross("west_kyanite_arrow", texture("kyanite_arrow"))).rotationX(90).rotationY(-90).addModel()
                .partialState().with(KyaniteArrowBlock.FACING, Direction.NORTH).modelForState()
                        .modelFile(this.models().cross("north_kyanite_arrow", texture("kyanite_arrow"))).rotationX(90).rotationY(0).addModel()
                .partialState().with(KyaniteArrowBlock.FACING, Direction.EAST).modelForState()
                        .modelFile(this.models().cross("east_kyanite_arrow", texture("kyanite_arrow"))).rotationX(90).rotationY(90).addModel();
        log(BorealisBlocks.KYANITE_CABLE, "kyanite_cable");
        block(BorealisBlocks.KYANITE_BULB);

        getVariantBuilder(BorealisBlocks.FLUORITE.get())
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 0).modelForState().modelFile(this.models().cubeAll("infrared_fluorite", texture("infrared_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 1).modelForState().modelFile(this.models().cubeAll("infrared_fluorite", texture("infrared_fluorite"))).addModel()
                //    .partialState().with(FluoriteBlock.STATIC_LEVEL, 1).modelForState().modelFile(this.models().cubeAll("radiant_fluorite", texture("radiant_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 2).modelForState().modelFile(this.models().cubeAll("radiant_fluorite", texture("radiant_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 3).modelForState().modelFile(this.models().cubeAll("radiant_fluorite", texture("radiant_fluorite"))).addModel()
                //    .partialState().with(FluoriteBlock.STATIC_LEVEL, 3).modelForState().modelFile(this.models().cubeAll("neon_fluorite", texture("neon_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 4).modelForState().modelFile(this.models().cubeAll("neon_fluorite", texture("neon_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 5).modelForState().modelFile(this.models().cubeAll("neon_fluorite", texture("neon_fluorite"))).addModel()
                //    .partialState().with(FluoriteBlock.STATIC_LEVEL, 5).modelForState().modelFile(this.models().cubeAll("phosphorescent_fluorite", texture("phosphorescent_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 6).modelForState().modelFile(this.models().cubeAll("phosphorescent_fluorite", texture("phosphorescent_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 7).modelForState().modelFile(this.models().cubeAll("phosphorescent_fluorite", texture("phosphorescent_fluorite"))).addModel()
                //    .partialState().with(FluoriteBlock.STATIC_LEVEL, 7).modelForState().modelFile(this.models().cubeAll("luminescent_fluorite", texture("luminescent_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 8).modelForState().modelFile(this.models().cubeAll("luminescent_fluorite", texture("luminescent_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 9).modelForState().modelFile(this.models().cubeAll("luminescent_fluorite", texture("luminescent_fluorite"))).addModel()
                //    .partialState().with(FluoriteBlock.STATIC_LEVEL, 9).modelForState().modelFile(this.models().cubeAll("cobalt_fluorite", texture("cobalt_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 10).modelForState().modelFile(this.models().cubeAll("cobalt_fluorite", texture("cobalt_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 11).modelForState().modelFile(this.models().cubeAll("cobalt_fluorite", texture("cobalt_fluorite"))).addModel()
                //    .partialState().with(FluoriteBlock.STATIC_LEVEL, 11).modelForState().modelFile(this.models().cubeAll("ultraviolet_fluorite", texture("ultraviolet_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 12).modelForState().modelFile(this.models().cubeAll("ultraviolet_fluorite", texture("ultraviolet_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 13).modelForState().modelFile(this.models().cubeAll("ultraviolet_fluorite", texture("ultraviolet_fluorite"))).addModel()
                //    .partialState().with(FluoriteBlock.STATIC_LEVEL, 13).modelForState().modelFile(this.models().cubeAll("amaranthine_fluorite", texture("amaranthine_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 14).modelForState().modelFile(this.models().cubeAll("amaranthine_fluorite", texture("amaranthine_fluorite"))).addModel()
                .partialState().with(FluoriteBlock.STATIC_LEVEL, 15).modelForState().modelFile(this.models().cubeAll("amaranthine_fluorite", texture("amaranthine_fluorite"))).addModel()
                 //   .partialState().with(FluoriteBlock.STATIC_LEVEL, 15).modelForState().modelFile(this.models().cubeAll("infrared_fluorite", texture("infrared_fluorite"))).addModel()
        ;
        paneBlockWithRenderType(BorealisBlocks.STATIC_FIELD.get(), texture("static_field"), texture("static_field"), "cutout");
        simpleBlock(BorealisBlocks.HOLLY.get(), models().getExistingFile(modLoc("holly")));
        getVariantBuilder(BorealisBlocks.WINTER_CELLO.get())
                .partialState().with(ShadedDoublePlantBlock.HALF, DoubleBlockHalf.UPPER).modelForState().modelFile(this.models().cross("winter_cello_top", texture("winter_cello_top"))).addModel()
                .partialState().with(ShadedDoublePlantBlock.HALF, DoubleBlockHalf.LOWER).modelForState().modelFile(this.models().cross("winter_cello_bottom", texture("winter_cello_bottom"))).addModel();
        getVariantBuilder(BorealisBlocks.ALMS.get())
                .forAllStates((test) -> ConfiguredModel.builder().modelFile(this.models().cube("alms", texture("alms_top"), texture("alms_top"), texture("alms_end"), texture("alms_end"), texture("alms_side"), texture("alms_side")).texture("particle", texture("alms_side"))).build());
        getVariantBuilder(BorealisBlocks.CRACKED_ALMS.get())
                .partialState().with(AlmsCrackedBlock.EMPTY, false).modelForState().modelFile(this.models().cube("cracked_alms", texture("alms_top"), texture("cracked_alms_top"), texture("alms_end"), texture("alms_end"), texture("cracked_alms_side"), texture("cracked_alms_side")).texture("particle", texture("alms_side"))).addModel()
                .partialState().with(AlmsCrackedBlock.EMPTY, true).modelForState().modelFile(this.models().cube("alms_shell", texture("alms_top"), texture("alms_shell_top"), texture("alms_end"), texture("alms_end"), texture("alms_shell_side"), texture("alms_shell_side")).texture("particle", texture("alms_side"))).addModel();

        block(BorealisBlocks.LICHEN_BLOCK);
        block(BorealisBlocks.CINNABAR);
        wood();
    }

    private void wood() {
        block(BorealisBlocks.BRUMAL_PLANKS);
        log(BorealisBlocks.BRUMAL_LOG, "brumal_log");
        wood(BorealisBlocks.BRUMAL_WOOD, BorealisBlocks.BRUMAL_LOG);
        log(BorealisBlocks.STRIPPED_BRUMAL_LOG, "stripped_brumal_log");
        wood(BorealisBlocks.STRIPPED_BRUMAL_WOOD, BorealisBlocks.STRIPPED_BRUMAL_LOG);
        block(BorealisBlocks.BRUMAL_LEAVES);
        stairs(BorealisBlocks.BRUMAL_STAIRS, BorealisBlocks.BRUMAL_PLANKS);
        slab(BorealisBlocks.BRUMAL_SLAB, BorealisBlocks.BRUMAL_PLANKS);
        fenceBlock(BorealisBlocks.BRUMAL_FENCE.get(), texture("brumal_planks"));
        fenceGateBlock(BorealisBlocks.BRUMAL_FENCE_GATE.get(), texture("brumal_planks"));
        door(BorealisBlocks.BRUMAL_DOOR, "brumal");
        trapdoor(BorealisBlocks.BRUMAL_TRAPDOOR, "brumal");
        crossBlock(BorealisBlocks.BRUMAL_SAPLING);
        buttonBlock(BorealisBlocks.BRUMAL_BUTTON.get(), texture("brumal_planks"));
        pressurePlateBlock(BorealisBlocks.BRUMAL_PRESSURE_PLATE.get(), texture("brumal_planks"));
        signBlock(BorealisBlocks.BRUMAL_SIGN.get(), BorealisBlocks.BRUMAL_WALL_SIGN.get(), texture("brumal_planks"));

        crossBlock(BorealisBlocks.MISTERIA_HEAD);
        crossBlock(BorealisBlocks.WINTER_FIDDLE);

        block(BorealisBlocks.FROSTFIR_PLANKS);
        log(BorealisBlocks.FROSTFIR_LOG, "frostfir_log");
        wood(BorealisBlocks.FROSTFIR_WOOD, BorealisBlocks.FROSTFIR_LOG);
        log(BorealisBlocks.STRIPPED_FROSTFIR_LOG, "stripped_frostfir_log");
        wood(BorealisBlocks.STRIPPED_FROSTFIR_WOOD, BorealisBlocks.STRIPPED_FROSTFIR_LOG);
        block(BorealisBlocks.FROSTFIR_LEAVES);
        stairs(BorealisBlocks.FROSTFIR_STAIRS, BorealisBlocks.FROSTFIR_PLANKS);
        slab(BorealisBlocks.FROSTFIR_SLAB, BorealisBlocks.FROSTFIR_PLANKS);
        fenceBlock(BorealisBlocks.FROSTFIR_FENCE.get(), texture("frostfir_planks"));
        fenceGateBlock(BorealisBlocks.FROSTFIR_FENCE_GATE.get(), texture("frostfir_planks"));
        door(BorealisBlocks.FROSTFIR_DOOR, "frostfir");
        trapdoor(BorealisBlocks.FROSTFIR_TRAPDOOR, "frostfir");
        crossBlock(BorealisBlocks.FROSTFIR_SAPLING);
        buttonBlock(BorealisBlocks.FROSTFIR_BUTTON.get(), texture("frostfir_planks"));
        pressurePlateBlock(BorealisBlocks.FROSTFIR_PRESSURE_PLATE.get(), texture("frostfir_planks"));
        signBlock(BorealisBlocks.FROSTFIR_SIGN.get(), BorealisBlocks.FROSTFIR_WALL_SIGN.get(), texture("frostfir_planks"));
        
        block(BorealisBlocks.SWEETWOOD_PLANKS);
        log(BorealisBlocks.SWEETWOOD_LOG, "sweetwood_log");
        wood(BorealisBlocks.SWEETWOOD, BorealisBlocks.SWEETWOOD_LOG);
        log(BorealisBlocks.STRIPPED_SWEETWOOD_LOG, "stripped_sweetwood_log");
        wood(BorealisBlocks.STRIPPED_SWEETWOOD, BorealisBlocks.STRIPPED_SWEETWOOD_LOG);
        block(BorealisBlocks.SWEETWOOD_LEAVES);
        stairs(BorealisBlocks.SWEETWOOD_STAIRS, BorealisBlocks.SWEETWOOD_PLANKS);
        slab(BorealisBlocks.SWEETWOOD_SLAB, BorealisBlocks.SWEETWOOD_PLANKS);
        fenceBlock(BorealisBlocks.SWEETWOOD_FENCE.get(), texture("sweetwood_planks"));
        fenceGateBlock(BorealisBlocks.SWEETWOOD_FENCE_GATE.get(), texture("sweetwood_planks"));
        door(BorealisBlocks.SWEETWOOD_DOOR, "sweetwood");
        trapdoor(BorealisBlocks.SWEETWOOD_TRAPDOOR, "sweetwood");
        crossBlock(BorealisBlocks.SWEETWOOD_SAPLING);
        buttonBlock(BorealisBlocks.SWEETWOOD_BUTTON.get(), texture("sweetwood_planks"));
        pressurePlateBlock(BorealisBlocks.SWEETWOOD_PRESSURE_PLATE.get(), texture("sweetwood_planks"));
        signBlock(BorealisBlocks.SWEETWOOD_SIGN.get(), BorealisBlocks.SWEETWOOD_WALL_SIGN.get(), texture("sweetwood_planks"));
        
        block(BorealisBlocks.CARAMELIZED_PLANKS);
        log(BorealisBlocks.CARAMELIZED_LOG, "caramelized_log");
        wood(BorealisBlocks.CARAMELIZED_WOOD, BorealisBlocks.CARAMELIZED_LOG);
        log(BorealisBlocks.STRIPPED_CARAMELIZED_LOG, "stripped_caramelized_log");
        wood(BorealisBlocks.STRIPPED_CARAMELIZED_WOOD, BorealisBlocks.STRIPPED_CARAMELIZED_LOG);
        block(BorealisBlocks.GLAZED_LEAVES);
        stairs(BorealisBlocks.CARAMELIZED_STAIRS, BorealisBlocks.CARAMELIZED_PLANKS);
        slab(BorealisBlocks.CARAMELIZED_SLAB, BorealisBlocks.CARAMELIZED_PLANKS);
        fenceBlock(BorealisBlocks.CARAMELIZED_FENCE.get(), texture("caramelized_planks"));
        fenceGateBlock(BorealisBlocks.CARAMELIZED_FENCE_GATE.get(), texture("caramelized_planks"));
        door(BorealisBlocks.CARAMELIZED_DOOR, "caramelized");
        trapdoor(BorealisBlocks.CARAMELIZED_TRAPDOOR, "caramelized");
        buttonBlock(BorealisBlocks.CARAMELIZED_BUTTON.get(), texture("caramelized_planks"));
        pressurePlateBlock(BorealisBlocks.CARAMELIZED_PRESSURE_PLATE.get(), texture("caramelized_planks"));
        signBlock(BorealisBlocks.CARAMELIZED_SIGN.get(), BorealisBlocks.CARAMELIZED_WALL_SIGN.get(), texture("caramelized_planks"));
    }
}
