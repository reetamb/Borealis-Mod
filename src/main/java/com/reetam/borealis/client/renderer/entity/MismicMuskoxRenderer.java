package com.reetam.borealis.client.renderer.entity;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.client.model.MismicMuskoxModel;
import com.reetam.borealis.client.renderer.layer.MismicMuskoxCarpetLayer;
import com.reetam.borealis.client.renderer.layer.MismicMuskoxGlowLayer;
import com.reetam.borealis.entity.MismicMuskoxEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MismicMuskoxRenderer extends MobRenderer<MismicMuskoxEntity, MismicMuskoxModel<MismicMuskoxEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BorealisMod.MODID, "textures/entity/mismic_muskox.png");

    public MismicMuskoxRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MismicMuskoxModel<>(), 0.7F);
        this.addLayer(new MismicMuskoxCarpetLayer<>(this));
        this.addLayer(new MismicMuskoxGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(MismicMuskoxEntity entity) {
        return TEXTURE;
    }
}