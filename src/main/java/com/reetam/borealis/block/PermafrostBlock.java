package com.reetam.borealis.block;

import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.common.IPlantable;

public class PermafrostBlock extends Block {
    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;
    public static final BooleanProperty SUGARY = BooleanProperty.create("sugary");
    public static final BooleanProperty ICY = BooleanProperty.create("icy");

    public PermafrostBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(SNOWY, Boolean.FALSE));
        this.registerDefaultState(this.stateDefinition.any().setValue(SUGARY, Boolean.FALSE));
        this.registerDefaultState(this.stateDefinition.any().setValue(ICY, Boolean.FALSE));
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter getter, BlockPos pos, Direction facing, IPlantable plantable) {
        return true;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing != Direction.UP ?
                super.updateShape(state, facing, facingState, level, currentPos, facingPos) :
                state.setValue(SNOWY, facingState.is(BorealisTags.Blocks.SNOWY_BLOCKS))
                    .setValue(SUGARY, facingState.is(BorealisTags.Blocks.SUGARY_BLOCKS))
                    .setValue(ICY, facingState.is(BorealisTags.Blocks.ICY_BLOCKS));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().above());
        return this.defaultBlockState()
                .setValue(SNOWY, blockstate.is(BorealisTags.Blocks.SNOWY_BLOCKS))
                .setValue(SUGARY, blockstate.is(BorealisTags.Blocks.SUGARY_BLOCKS))
                .setValue(ICY, blockstate.is(BorealisTags.Blocks.ICY_BLOCKS));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SNOWY, SUGARY, ICY);
    }
}