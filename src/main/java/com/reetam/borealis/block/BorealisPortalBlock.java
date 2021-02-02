package com.reetam.borealis.block;

import com.reetam.borealis.registry.BorealisSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.*;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisDimensions;
import com.reetam.borealis.world.BorealisTeleporter;

import javax.annotation.Nullable;
import java.util.Random;

public class BorealisPortalBlock extends Block {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);

    public BorealisPortalBlock() {
        super(Properties.create(Material.PORTAL)
                .hardnessAndResistance(-1F)
                .doesNotBlockMovement()
                .setLightLevel((state) -> 10)
                .noDrops()
        );
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, BorealisSounds.BOREALIS_PORTAL_CHIME, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        double d0 = (double)pos.getX() + rand.nextDouble();
        double d1 = (double)pos.getY() + rand.nextDouble();
        double d2 = (double)pos.getZ() + rand.nextDouble();
        double d3 = (rand.nextFloat()-0.5)/(rand.nextInt(8)+3);
        double d4 = 0.1;
        double d5 = (rand.nextFloat()-0.5)/(rand.nextInt(8)+3);
        int j = rand.nextInt(2) * 2 - 1;
        if (!worldIn.getBlockState(pos.west()).isIn(this) && !worldIn.getBlockState(pos.east()).isIn(this)) {
            d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
            d3 = (rand.nextFloat() / 6) * j;
        } else {
            d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
            d5 = (rand.nextFloat() / 6) * j;
        }

        worldIn.addParticle(ParticleTypes.CLOUD, d0, d1, d2, d3, d4, d5);

    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public boolean makePortal(IWorld worldIn, BlockPos pos) {
        worldIn.setBlockState(pos, BorealisBlocks.borealis_portal.get().getDefaultState(), 18);

        worldIn.setBlockState(pos.west(), BorealisBlocks.borealis_portal.get().getDefaultState(), 18);
        worldIn.setBlockState(pos.east(), BorealisBlocks.borealis_portal.get().getDefaultState(), 18);
        worldIn.setBlockState(pos.south(), BorealisBlocks.borealis_portal.get().getDefaultState(), 18);
        worldIn.setBlockState(pos.north(), BorealisBlocks.borealis_portal.get().getDefaultState(), 18);

        if (worldIn.getBlockState(pos.west().down()).isAir()) {
            worldIn.setBlockState(pos.west().down(), Blocks.PACKED_ICE.getDefaultState(), 18);
        }
        if (worldIn.getBlockState(pos.east().down()).isAir()) {
            worldIn.setBlockState(pos.east().down(), Blocks.PACKED_ICE.getDefaultState(), 18);
        }
        if (worldIn.getBlockState(pos.south().down()).isAir()) {
            worldIn.setBlockState(pos.south().down(), Blocks.PACKED_ICE.getDefaultState(), 18);
        }
        if (worldIn.getBlockState(pos.north().down()).isAir()) {
            worldIn.setBlockState(pos.north().down(), Blocks.PACKED_ICE.getDefaultState(), 18);
        }
        if (worldIn.getBlockState(pos.down()).isAir()) {
            worldIn.setBlockState(pos.down(), Blocks.PACKED_ICE.getDefaultState(), 18);
        }
        if (worldIn.getBlockState(pos.down(2)).isAir()) {
            worldIn.setBlockState(pos.down(2), Blocks.PACKED_ICE.getDefaultState(), 18);
        }

        worldIn.setBlockState(pos.west(2), Blocks.PACKED_ICE.getDefaultState(), 18);
        worldIn.setBlockState(pos.north(2), Blocks.PACKED_ICE.getDefaultState(), 18);
        worldIn.setBlockState(pos.east(2), Blocks.PACKED_ICE.getDefaultState(), 18);
        worldIn.setBlockState(pos.south(2), Blocks.PACKED_ICE.getDefaultState(), 18);

        worldIn.setBlockState(pos.west().north(), Blocks.PACKED_ICE.getDefaultState(), 18);
        worldIn.setBlockState(pos.north().east(), Blocks.PACKED_ICE.getDefaultState(), 18);
        worldIn.setBlockState(pos.east().south(), Blocks.PACKED_ICE.getDefaultState(), 18);
        worldIn.setBlockState(pos.south().west(), Blocks.PACKED_ICE.getDefaultState(), 18);

        return true;
    }

//    public boolean trySpawnPortal(IWorld worldIn, BlockPos pos) {
//        BorealisPortalBlock.Size BorealisPortalBlock$size = this.isPortal(worldIn, pos);
//        if (BorealisPortalBlock$size != null && !onTrySpawnPortal(worldIn, pos, BorealisPortalBlock$size)) {
//            BorealisPortalBlock$size.placePortalBlocks();
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public static boolean onTrySpawnPortal(IWorld world, BlockPos pos, BorealisPortalBlock.Size size) {
//        return MinecraftForge.EVENT_BUS.post(new PortalSpawnEvent(world, pos, world.getBlockState(pos), size));
//    }
//
//    @Cancelable
//    public static class PortalSpawnEvent extends BlockEvent {
//        private final BorealisPortalBlock.Size size;
//
//        public PortalSpawnEvent(IWorld world, BlockPos pos, BlockState state, BorealisPortalBlock.Size size)
//        {
//            super(world, pos, state);
//            this.size = size;
//        }
//
//        public BorealisPortalBlock.Size getPortalSize()
//        {
//            return size;
//        }
//    }
//
//    @Nullable
//    public BorealisPortalBlock.Size isPortal(IWorld worldIn, BlockPos pos) {
//        BorealisPortalBlock.Size BorealisPortalBlock$size = new Size(worldIn, pos, Direction.Axis.X);
//        if (BorealisPortalBlock$size.isValid() && BorealisPortalBlock$size.portalBlockCount == 0) {
//            return BorealisPortalBlock$size;
//        } else {
//            BorealisPortalBlock.Size BorealisPortalBlock$size1 = new Size(worldIn, pos, Direction.Axis.Z);
//            return BorealisPortalBlock$size1.isValid() && BorealisPortalBlock$size1.portalBlockCount == 0 ? BorealisPortalBlock$size1 : null;
//        }
//    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        if(!entity.isPassenger() && !entity.isBeingRidden() && entity.isNonBoss()) {
            if(entity.func_242280_ah()) {
                entity.func_242279_ag();
            }
            else {
                if(!entity.world.isRemote && !pos.equals(entity.field_242271_ac)) {
                    entity.field_242271_ac = pos.toImmutable();
                }
                World entityWorld = entity.world;
                if(entityWorld != null) {
                    MinecraftServer minecraftserver = entityWorld.getServer();
                    RegistryKey<World> destination = entity.world.getDimensionKey() == BorealisDimensions.borealis_w ? World.OVERWORLD : BorealisDimensions.borealis_w;
                    if(minecraftserver != null) {
                        ServerWorld destinationWorld = minecraftserver.getWorld(destination);
                        if(destinationWorld != null && minecraftserver.getAllowNether() && !entity.isPassenger()) {
                            entity.world.getProfiler().startSection("borealis_portal");
                            entity.func_242279_ag();
                            entity.changeDimension(destinationWorld, new BorealisTeleporter());
                            entity.world.getProfiler().endSection();
                        }
                    }
                }
            }
        }
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

//    public static class Size {
//        private final IWorld world;
//        private final Direction.Axis axis;
//        private final Direction rightDir;
//        private final Direction leftDir;
//        private int portalBlockCount;
//        @Nullable
//        private BlockPos bottomLeft;
//        private int height;
//        private int width;
//
//        public Size(IWorld worldIn, BlockPos pos, Direction.Axis axisIn) {
//            this.world = worldIn;
//            this.axis = axisIn;
//            if (axisIn == Direction.Axis.X) {
//                this.leftDir = Direction.EAST;
//                this.rightDir = Direction.WEST;
//            } else {
//                this.leftDir = Direction.NORTH;
//                this.rightDir = Direction.SOUTH;
//            }
//
//            for(BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && this.canConnect(worldIn.getBlockState(pos.down())); pos = pos.down()) {
//                ;
//            }
//
//            int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;
//            if (i >= 0) {
//                this.bottomLeft = pos.offset(this.leftDir, i);
//                this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
//                if (this.width < 2 || this.width > 21) {
//                    this.bottomLeft = null;
//                    this.width = 0;
//                }
//            }
//
//            if (this.bottomLeft != null) {
//                this.height = this.calculatePortalHeight();
//            }
//
//        }
//
//        protected int getDistanceUntilEdge(BlockPos pos, Direction directionIn) {
//            int i;
//            for(i = 0; i < 22; ++i) {
//                BlockPos blockpos = pos.offset(directionIn, i);
//                if (!this.canConnect(this.world.getBlockState(blockpos)) || !(this.world.getBlockState(blockpos.down()).getBlock() == Blocks.PACKED_ICE)) {
//                    break;
//                }
//            }
//
//            BlockPos framePos = pos.offset(directionIn, i);
//            return this.world.getBlockState(framePos).getBlock() == Blocks.PACKED_ICE ? i : 0;
//        }
//
//        public int getHeight() {
//            return this.height;
//        }
//
//        public int getWidth() {
//            return this.width;
//        }
//
//        protected int calculatePortalHeight() {
//            label56:
//            for(this.height = 0; this.height < 21; ++this.height) {
//                for(int i = 0; i < this.width; ++i) {
//                    BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
//                    BlockState blockstate = this.world.getBlockState(blockpos);
//                    if (!this.canConnect(blockstate)) {
//                        break label56;
//                    }
//
//                    Block block = blockstate.getBlock();
//                    if (block == BorealisBlocks.borealis_portal.get()) {
//                        ++this.portalBlockCount;
//                    }
//
//                    if (i == 0) {
//                        BlockPos framePos = blockpos.offset(this.leftDir);
//                        if (!(this.world.getBlockState(framePos).getBlock() == Blocks.PACKED_ICE)) {
//                            break label56;
//                        }
//                    } else if (i == this.width - 1) {
//                        BlockPos framePos = blockpos.offset(this.rightDir);
//                        if (!(this.world.getBlockState(framePos).getBlock() == Blocks.PACKED_ICE)) {
//                            break label56;
//                        }
//                    }
//                }
//            }
//
//            for(int j = 0; j < this.width; ++j) {
//                BlockPos framePos = this.bottomLeft.offset(this.rightDir, j).up(this.height);
//                if (!(this.world.getBlockState(framePos).getBlock() == Blocks.PACKED_ICE)) {
//                    this.height = 0;
//                    break;
//                }
//            }
//
//            if (this.height <= 21 && this.height >= 3) {
//                return this.height;
//            } else {
//                this.bottomLeft = null;
//                this.width = 0;
//                this.height = 0;
//                return 0;
//            }
//        }
//
//        protected boolean canConnect(BlockState pos) {
//            Block block = pos.getBlock();
//            return pos.isAir() || block == BorealisBlocks.borealis_portal.get();
//        }
//
//        public boolean isValid() {
//            return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
//        }
//
//        public void placePortalBlocks() {
//            for(int i = 0; i < this.width; ++i) {
//                BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);
//
//                for(int j = 0; j < this.height; ++j) {
//                    this.world.setBlockState(blockpos.up(j), BorealisBlocks.borealis_portal.get().getDefaultState(), 18);
//                }
//            }
//
//        }
//
//        private boolean isPortalCountValidForSize() {
//            return this.portalBlockCount >= this.width * this.height;
//        }
//
//        public boolean validatePortal() {
//            return this.isValid() && this.isPortalCountValidForSize();
//        }
//    }
}