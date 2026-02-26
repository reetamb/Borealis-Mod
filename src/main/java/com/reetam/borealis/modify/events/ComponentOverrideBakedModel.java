package com.reetam.borealis.modify.events;

import com.reetam.borealis.item.components.FrozenFoodType;
import com.reetam.borealis.registry.BorealisItems;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class ComponentOverrideBakedModel extends BakedModelWrapper<BakedModel> {
    private final Map<FrozenFoodType, BakedModel> overrideModels; // load/cache separately

    public ComponentOverrideBakedModel(BakedModel original, Map<FrozenFoodType, BakedModel> models) {
        super(original);
        this.overrideModels = models;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand) {
        return super.getQuads(state, side, rand);
    }

    @Override
    public ItemOverrides getOverrides() {
        return new ItemOverrides() {
            @Override
            public BakedModel resolve(BakedModel model, ItemStack stack,
                                      ClientLevel level, LivingEntity entity, int seed) {
                if (stack.has(BorealisItems.Components.FROZEN)) {
                    return overrideModels.get(stack.get(BorealisItems.Components.FROZEN));
                }
                return originalModel.getOverrides().resolve(model, stack, level, entity, seed);
            }
        };
    }
}