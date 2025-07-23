package com.reetam.borealis.block.fluid;

import com.reetam.borealis.registry.BorealisBlocks;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TankWindowBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape SOUTH = Block.box(5, 5, 0, 11, 11, 5);
    private static final VoxelShape NORTH = Block.box(5, 5, 11, 11, 11, 16);
    private static final VoxelShape EAST = Block.box(0, 5, 5, 5, 11, 11);
    private static final VoxelShape WEST = Block.box(11, 5, 5, 16, 11, 11);

    public TankWindowBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return world.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).is(BorealisBlocks.INSULATED_TANK);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(20) != 0) return;
        if (!(level.getBlockState(pos.below())).is(BorealisBlocks.INSULATED_TANK)) return;
        if (!(level.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).is(BorealisTags.Blocks.TAPPABLE))) return;
        if (level.getBlockState(pos.below()).getValue(TankBlock.LEVEL) == 4) return;
        BlockState newState = level.getBlockState(pos.below());
        TankRecipe.TankType type = TankRecipe.RECIPES.get(level.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).getBlock());
        newState = newState.setValue(TankBlock.TYPE, type);
        newState = newState.setValue(TankBlock.LEVEL, newState.getValue(TankBlock.LEVEL) + 1);
        level.setBlock(pos.below(), newState, 3);
        level.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0, 0);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SOUTH;
            case EAST -> EAST;
            case WEST -> WEST;
            default -> NORTH;
        };
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState otherState, LevelAccessor level, BlockPos pos, BlockPos otherPos) {
        return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, otherState, level, pos, otherPos);
    }
}
