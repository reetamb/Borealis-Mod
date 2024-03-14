package com.reetam.borealis.entity.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.BorealisBoatEntity;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

public class BorealisBoatRenderer extends EntityRenderer<BorealisBoatEntity> {
    
    private final Map<BorealisBoatEntity.Type, Pair<ResourceLocation, BoatModel>> boatResources;

    public BorealisBoatRenderer(EntityRendererProvider.Context renderContext) {
        super(renderContext);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(BorealisBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap((boatType) -> boatType,
                (boatType) -> Pair.of(new ResourceLocation(BorealisMod.MODID, "textures/entity/boats/" + boatType.getName() + ".png"), new BoatModel(renderContext.bakeLayer(boatLayer(boatType))))));
    }

    public static ModelLayerLocation boatLayer(BorealisBoatEntity.Type boatType) {
        return new ModelLayerLocation(new ResourceLocation(BorealisMod.MODID, "boats/" + boatType.getName()), "main");
    }

    @Override
    public void render(BorealisBoatEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        pMatrixStack.translate(0.0D, 0.375D, 0.0D);
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(180.0F - pEntityYaw));
        float f = (float)pEntity.getHurtTime() - pPartialTicks;
        float f1 = pEntity.getDamage() - pPartialTicks;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            pMatrixStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)pEntity.getHurtDir()));
        }

        float bubbleAngle = pEntity.getBubbleAngle(pPartialTicks);
        if (!Mth.equal(bubbleAngle, 0.0F)) {
            pMatrixStack.mulPose(new Quaternionf().setAngleAxis(pEntity.getBubbleAngle(pPartialTicks) * 0.017453292F, 1.0F, 0.0F, 1.0F));
        }

        Pair<ResourceLocation, BoatModel> pair = this.boatResources.get(pEntity.getBorealisBoatType());
        ResourceLocation resourcelocation = (ResourceLocation)pair.getFirst();
        BoatModel boatmodel = (BoatModel)pair.getSecond();
        pMatrixStack.scale(-1.0F, -1.0F, 1.0F);
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        boatmodel.setupAnim(pEntity, pPartialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(boatmodel.renderType(resourcelocation));
        boatmodel.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!pEntity.isUnderWater()) {
            VertexConsumer vertexconsumer1 = pBuffer.getBuffer(RenderType.waterMask());
            boatmodel.waterPatch().render(pMatrixStack, vertexconsumer1, pPackedLight, OverlayTexture.NO_OVERLAY);
        }

        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(BorealisBoatEntity boat) {
        return this.boatResources.get(boat.getBorealisBoatType()).getFirst();
    }
}
