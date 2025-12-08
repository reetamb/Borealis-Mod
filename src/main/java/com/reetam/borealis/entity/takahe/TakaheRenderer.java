package com.reetam.borealis.entity.takahe;

import com.reetam.borealis.BorealisMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TakaheRenderer extends MobRenderer<TakaheEntity, TakaheModel<TakaheEntity>> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "textures/entity/takahe.png");

    public TakaheRenderer(EntityRendererProvider.Context context) {
        super(context, new TakaheModel<>(context.bakeLayer(TakaheModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new TakaheHatLayer<>(this));
        this.addLayer(new TakaheCrestLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(TakaheEntity entity) {
        return TEXTURE;
    }
}