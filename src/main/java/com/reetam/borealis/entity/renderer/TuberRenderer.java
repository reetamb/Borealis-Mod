package com.reetam.borealis.entity.renderer;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.TuberEntity;
import com.reetam.borealis.entity.model.HummingbirdModel;
import com.reetam.borealis.entity.model.TuberModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TuberRenderer extends MobRenderer<TuberEntity, TuberModel<TuberEntity>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "textures/entity/tuber.png");

    public TuberRenderer(EntityRendererProvider.Context context) {
        super(context, new TuberModel<>(context.bakeLayer(TuberModel.LAYER_LOCATION)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(TuberEntity tuberEntity) {
        return TEXTURE;
    }
}
