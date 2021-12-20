package com.reetam.borealis.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.TakaheEntity;
import com.reetam.borealis.entity.model.TakaheModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class TakaheCrestLayer<T extends TakaheEntity, M extends TakaheModel<T>> extends RenderLayer<T, M> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BorealisMod.MODID, "textures/entity/takahe_crest.png");

    public TakaheCrestLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.getHat()) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.getParentModel(), TEXTURE, poseStack, buffer, packedLight, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
        }
    }

}
