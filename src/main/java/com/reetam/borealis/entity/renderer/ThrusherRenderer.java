package com.reetam.borealis.entity.renderer;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.ThrusherEntity;
import com.reetam.borealis.entity.model.ThrusherModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ThrusherRenderer extends MobRenderer<ThrusherEntity, ThrusherModel<ThrusherEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BorealisMod.MODID, "textures/entity/thrusher.png");

    public ThrusherRenderer(EntityRendererProvider.Context context) {
        super(context, new ThrusherModel<>(context.bakeLayer(ThrusherModel.LAYER_LOCATION)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrusherEntity entity) {
        return TEXTURE;
    }
}
