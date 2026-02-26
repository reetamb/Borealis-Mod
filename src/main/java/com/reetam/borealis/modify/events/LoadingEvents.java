package com.reetam.borealis.modify.events;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.item.components.FrozenFoodType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoadingEvents {

    public static void onModelRegister(ModelEvent.RegisterAdditional event) {
        for (FrozenFoodType type : FrozenFoodType.values()) {
            event.register(new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "item/frozen_" + type.getSerializedName()), "standalone"));
        }
    }
    public static void onModelBake(ModelEvent.ModifyBakingResult event) {
        Map<FrozenFoodType, BakedModel> overrideModels = new HashMap<>();
        for (FrozenFoodType type : FrozenFoodType.values()) {
            overrideModels.put(type, event.getModels().get(
                    new ModelResourceLocation(
                            ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "item/frozen_" + type.getSerializedName()), "standalone")
                    )
            );
        }

        if (overrideModels.isEmpty()) {
            return;
        }

        Map<ModelResourceLocation, BakedModel> models = event.getModels();
        for (var key : Set.copyOf(models.keySet())) {
            models.put(key, new ComponentOverrideBakedModel(models.get(key), overrideModels));
        }
    }
}
