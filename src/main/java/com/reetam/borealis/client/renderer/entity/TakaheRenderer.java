package com.reetam.borealis.client.renderer.entity;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.client.model.TakaheModel;
import com.reetam.borealis.client.renderer.layer.TakaheCrestLayer;
import com.reetam.borealis.client.renderer.layer.TakaheHatLayer;
import com.reetam.borealis.entity.TakaheEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TakaheRenderer extends MobRenderer<TakaheEntity, TakaheModel<TakaheEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BorealisMod.MODID, "textures/entity/takahe.png");

    public TakaheRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TakaheModel<>(), 0.5F);
        this.addLayer(new TakaheHatLayer<>(this));
        this.addLayer(new TakaheCrestLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(TakaheEntity entity) {
        return TEXTURE;
    }
}