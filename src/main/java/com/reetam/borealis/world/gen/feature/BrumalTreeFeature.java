package com.reetam.borealis.world.gen.feature;

import com.mojang.serialization.Codec;
import java.util.Random;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.IntegrityProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class BrumalTreeFeature extends Feature<NoFeatureConfig> {
    private static final ResourceLocation MEDIUM_BRUMAL_TREE_01 = new ResourceLocation(BorealisMod.MODID, "brumal_tree/medium_brumal_tree_1");
    private static final ResourceLocation MEDIUM_BRUMAL_TREE_02 = new ResourceLocation(BorealisMod.MODID, "brumal_tree/medium_brumal_tree_2");
    private static final ResourceLocation MEDIUM_BRUMAL_TREE_03 = new ResourceLocation(BorealisMod.MODID, "brumal_tree/medium_brumal_tree_3");
    private static final ResourceLocation[] MEDIUM_BRUMAL_TREES = new ResourceLocation[]{MEDIUM_BRUMAL_TREE_01, MEDIUM_BRUMAL_TREE_02, MEDIUM_BRUMAL_TREE_03};

    private static final ResourceLocation SMALL_BRUMAL_TREE_01 = new ResourceLocation(BorealisMod.MODID, "brumal_tree/small_brumal_tree_1");
    private static final ResourceLocation SMALL_BRUMAL_TREE_02 = new ResourceLocation(BorealisMod.MODID, "brumal_tree/small_brumal_tree_2");
    private static final ResourceLocation SMALL_BRUMAL_TREE_03 = new ResourceLocation(BorealisMod.MODID, "brumal_tree/small_brumal_tree_3");
    private static final ResourceLocation[] SMALL_BRUMAL_TREES = new ResourceLocation[]{SMALL_BRUMAL_TREE_01, SMALL_BRUMAL_TREE_02, SMALL_BRUMAL_TREE_03};

    public BrumalTreeFeature(Codec<NoFeatureConfig> p_i231955_1_) {
        super(p_i231955_1_);
    }

    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        Rotation rotation = Rotation.randomRotation(rand);

        TemplateManager templatemanager = reader.getWorld().getServer().getTemplateManager();

        Template template;
        if (rand.nextInt(1) == 0) {
            template = templatemanager.getTemplateDefaulted(MEDIUM_BRUMAL_TREES[rand.nextInt(MEDIUM_BRUMAL_TREES.length)]);
        } else {
            template = templatemanager.getTemplateDefaulted(SMALL_BRUMAL_TREES[rand.nextInt(SMALL_BRUMAL_TREES.length)]);
        }
        ChunkPos chunkpos = new ChunkPos(pos);
        MutableBoundingBox mutableboundingbox = new MutableBoundingBox(chunkpos.getXStart(), 0, chunkpos.getZStart(), chunkpos.getXEnd(), 256, chunkpos.getZEnd());
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(mutableboundingbox).setRandom(rand).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
        BlockPos blockpos = template.transformedSize(rotation);

        int j = rand.nextInt(16 - blockpos.getX());
        int k = rand.nextInt(16 - blockpos.getZ());
        int l = 256;

        for(int i1 = 0; i1 < blockpos.getX(); ++i1) {
            for(int j1 = 0; j1 < blockpos.getZ(); ++j1) {
                l = Math.min(l, reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX() + i1 + j, pos.getZ() + j1 + k));
            }
        }

        BlockPos blockpos1 = template.getZeroPositionWithTransform(pos.add(j, l, k), Mirror.NONE, rotation);

        IntegrityProcessor integrityprocessor = new IntegrityProcessor(1.0F);
        placementsettings.clearProcessors().addProcessor(integrityprocessor);
        if (reader.getBlockState(blockpos1).getBlock() != BorealisBlocks.lichen_block.get() && reader.getBlockState(blockpos1).getBlock() != BorealisBlocks.permafrost.get()) {
            template.func_237146_a_(reader, blockpos1, blockpos1, placementsettings, rand, 4);
        }
        return true;
    }
}
