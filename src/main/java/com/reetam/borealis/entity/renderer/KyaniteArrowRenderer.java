package com.reetam.borealis.entity.renderer;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.KyaniteArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class KyaniteArrowRenderer extends ArrowRenderer<KyaniteArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BorealisMod.MODID, "textures/entity/kyanite_arrow.png");
    public KyaniteArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(KyaniteArrowEntity kyaniteArrowEntity) {
        return TEXTURE;
    }
}
