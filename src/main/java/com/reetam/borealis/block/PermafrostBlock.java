package com.reetam.borealis.block;

import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.IPlantable;

public class PermafrostBlock extends Block {
    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;
    public static final BooleanProperty SUGARY = BooleanProperty.create("sugary");
    public static final BooleanProperty ICY = BooleanProperty.create("icy");

    public PermafrostBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(SNOWY, Boolean.FALSE));
        this.registerDefaultState(this.stateDefinition.any().setValue(SUGARY, Boolean.FALSE));
        this.registerDefaultState(this.stateDefinition.any().setValue(ICY, Boolean.FALSE));
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        return true;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return facing != Direction.UP ?
                super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos) :
                stateIn.setValue(SNOWY, facingState.is(BorealisTags.Blocks.SNOWY_BLOCKS))
                    .setValue(SUGARY, facingState.is(BorealisTags.Blocks.SUGARY_BLOCKS))
                    .setValue(ICY, facingState.is(BorealisTags.Blocks.ICY_BLOCKS));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().above());
        return this.defaultBlockState()
                .setValue(SNOWY, blockstate.is(BorealisTags.Blocks.SNOWY_BLOCKS))
                .setValue(SUGARY, blockstate.is(BorealisTags.Blocks.SUGARY_BLOCKS))
                .setValue(ICY, blockstate.is(BorealisTags.Blocks.ICY_BLOCKS));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(SNOWY, SUGARY, ICY);
    }
}