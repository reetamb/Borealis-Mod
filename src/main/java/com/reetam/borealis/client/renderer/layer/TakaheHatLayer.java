package com.reetam.borealis.client.renderer.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.client.model.TakaheModel;
import com.reetam.borealis.entity.TakaheEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class TakaheHatLayer<T extends TakaheEntity, M extends TakaheModel<T>> extends LayerRenderer<T, M> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BorealisMod.MODID, "textures/entity/takahe_hat.png");

    public TakaheHatLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.getHat()) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.getParentModel(), TEXTURE, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
        }
    }

}
