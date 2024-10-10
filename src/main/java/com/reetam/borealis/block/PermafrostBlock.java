package com.reetam.borealis.block;

import com.reetam.borealis.block.property.PermafrostCover;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.neoforged.neoforge.common.util.TriState;

public class PermafrostBlock extends Block {
    public static final EnumProperty<PermafrostCover> COVER = EnumProperty.create("cover", PermafrostCover.class);

    public PermafrostBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(COVER, PermafrostCover.CLEAR));
    }

    @Override
    public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
        return TriState.TRUE;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing != Direction.UP ?
                super.updateShape(state, facing, facingState, level, currentPos, facingPos) :
                chooseCover(facingState);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().above());
        return chooseCover(blockstate);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COVER);
    }

    private BlockState chooseCover(BlockState target) {
        if (target.is(BorealisTags.Blocks.SNOWY_BLOCKS)) return this.defaultBlockState().setValue(COVER, PermafrostCover.SNOWY);
        else if (target.is(BorealisTags.Blocks.ICY_BLOCKS)) return this.defaultBlockState().setValue(COVER, PermafrostCover.ICY);
        else if (target.is(BorealisTags.Blocks.SUGARY_BLOCKS)) return this.defaultBlockState().setValue(COVER, PermafrostCover.SUGARY);
        else return this.defaultBlockState().setValue(COVER, PermafrostCover.CLEAR);
    }
}