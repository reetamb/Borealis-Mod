package com.reetam.borealis.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.TakaheEntity;
import com.reetam.borealis.entity.model.TakaheModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class TakaheHatLayer<T extends TakaheEntity, M extends TakaheModel<T>> extends RenderLayer<T, M> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "textures/entity/takahe_hat.png");

    public TakaheHatLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entitylivingbase, float limbSwing, float limbSwingAmount, float partialTicks, float ageTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbase.getHat()) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.getParentModel(), TEXTURE, poseStack, buffer, packedLight, entitylivingbase, limbSwing, limbSwingAmount, ageTicks, netHeadYaw, headPitch, partialTicks, 16777215);
        }
    }

}
