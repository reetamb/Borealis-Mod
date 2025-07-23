package com.reetam.borealis.block.fluid;

import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class TankBlock extends Block {

    public static final EnumProperty<TankRecipe.TankType> TYPE = EnumProperty.create("fluid", TankRecipe.TankType.class);
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 4);
    public static final BooleanProperty WINDOW_NORTH = BooleanProperty.create("window_north");
    public static final BooleanProperty WINDOW_EAST = BooleanProperty.create("window_east");
    public static final BooleanProperty WINDOW_SOUTH = BooleanProperty.create("window_south");
    public static final BooleanProperty WINDOW_WEST = BooleanProperty.create("window_west");

    public TankBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(TYPE, TankRecipe.TankType.EMPTY)
                .setValue(LEVEL, 0)
                .setValue(WINDOW_NORTH, false)
                .setValue(WINDOW_EAST, false)
                .setValue(WINDOW_SOUTH, false)
                .setValue(WINDOW_WEST, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE, LEVEL, WINDOW_NORTH, WINDOW_EAST, WINDOW_SOUTH, WINDOW_WEST);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(TYPE, TankRecipe.TankType.EMPTY).setValue(LEVEL, 0);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState otherState, LevelAccessor level, BlockPos pos, BlockPos otherPos) {
        return (state
                .setValue(WINDOW_NORTH, level.getBlockState(pos.north()).is(BorealisBlocks.TANK_WINDOW))
                .setValue(WINDOW_EAST, level.getBlockState(pos.east()).is(BorealisBlocks.TANK_WINDOW))
                .setValue(WINDOW_SOUTH, level.getBlockState(pos.south()).is(BorealisBlocks.TANK_WINDOW))
                .setValue(WINDOW_WEST, level.getBlockState(pos.west()).is(BorealisBlocks.TANK_WINDOW))
        );
    }
}
