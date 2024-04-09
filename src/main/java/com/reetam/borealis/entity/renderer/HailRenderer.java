package com.reetam.borealis.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.HailEntity;
import com.reetam.borealis.entity.model.HailModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class HailRenderer extends EntityRenderer<HailEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BorealisMod.MODID, "textures/entity/hail.png");

    public HailRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(HailEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        HailModel<HailEntity> model = new HailModel<>(HailModel.createBodyLayer().bakeRoot());
        model.setupAnim(pEntity, 0.0F, 0.0F, pEntity.tickCount + pPartialTicks, 0.0F, 0.0F);
        model.renderToBuffer(pPoseStack, pBuffer.getBuffer(model.renderType(getTextureLocation(pEntity))), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(HailEntity hailEntity) {
        return TEXTURE;
    }
}
