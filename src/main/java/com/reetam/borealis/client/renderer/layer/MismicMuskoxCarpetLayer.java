package com.reetam.borealis.client.renderer.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.client.model.MismicMuskoxModel;
import com.reetam.borealis.entity.MismicMuskoxEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class MismicMuskoxCarpetLayer<T extends MismicMuskoxEntity, M extends MismicMuskoxModel<T>> extends LayerRenderer<T, M> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BorealisMod.MODID, "textures/entity/mismic_muskox_carpet.png");

    public MismicMuskoxCarpetLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.getCarpet()) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.getParentModel(), TEXTURE, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
        }
    }

}
