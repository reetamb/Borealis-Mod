package com.reetam.borealis.entity.renderer;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.TakaheEntity;
import com.reetam.borealis.entity.model.TakaheModel;
import com.reetam.borealis.entity.renderer.layer.TakaheCrestLayer;
import com.reetam.borealis.entity.renderer.layer.TakaheHatLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TakaheRenderer extends MobRenderer<TakaheEntity, TakaheModel<TakaheEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BorealisMod.MODID, "textures/entity/takahe.png");

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