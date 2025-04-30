package com.reetam.borealis.block.fluid;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.reetam.borealis.item.SilverToolBuilder;
import com.reetam.borealis.item.SilverToolItem;
import com.reetam.borealis.item.SilverTools;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.List;
import java.util.function.Supplier;

public class QuicksilverBlock extends LiquidBlock {

    public QuicksilverBlock(Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid.get(), properties.noCollission().strength(100F).noLootTable().randomTicks());
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        if (rand.nextInt(10) == 0) level.addParticle(ParticleTypes.SCRAPE, pos.getX(), pos.getY(), pos.getZ(), 0.0, 0.0, 0.0);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (rand.nextInt(5) == 0) {
            SilverToolBuilder tool = SilverTools.tools.stream().toList().get(rand.nextInt(SilverTools.tools.size()));
            ItemInput input = new ItemInput(Holder.direct(BorealisItems.SILVER_BLADE.get()),
                    DataComponentPatch.builder()
                            .set(DataComponents.TOOL, tool.tool())
                            .set(DataComponents.ITEM_NAME, Component.literal("Silver " + tool.formattedName()))
                            .set(DataComponents.LORE, new ItemLore(List.of(Component.literal("Works as: " + tool.worksOn()))))
                            .set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(SilverTools.TOOLS.get(tool)))
                            .set(DataComponents.ATTRIBUTE_MODIFIERS, SilverToolItem.createAttributes((int) tool.damage(), 1.6F))
                            .build());
            try {
                ItemStack stack = input.createItemStack(1, true);
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack));
            } catch (CommandSyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livingEntityIn) {
            livingEntityIn.hurt(level.damageSources().magic(), 4.0F);
        }
    }
}
