package com.reetam.borealis.entity.renderer.layer;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.model.MismicMuskoxModel;
import com.reetam.borealis.entity.MismicMuskoxEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MismicMuskoxGlowLayer<T extends MismicMuskoxEntity, M extends MismicMuskoxModel<T>> extends AbstractEyesLayer<T, M> {

    private static final RenderType TEXTURE = RenderType.eyes(new ResourceLocation(BorealisMod.MODID, "textures/entity/mismic_muskox_glow.png"));

    public MismicMuskoxGlowLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType renderType() {
        return TEXTURE;
    }
}
