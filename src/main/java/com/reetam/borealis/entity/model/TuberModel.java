package com.reetam.borealis.entity.model;
// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.TuberEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class TuberModel<T extends TuberEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "tuber"), "main");
	private final ModelPart body;
	private final ModelPart rightEar;
	private final ModelPart leftEar;
	private final ModelPart rightHand;
	private final ModelPart rightFoot;
	private final ModelPart leftHand;
	private final ModelPart leftFoot;
	private final ModelPart tail;

	public TuberModel(ModelPart root) {
		this.body = root.getChild("body");
		this.rightEar = root.getChild("rightEar");
		this.leftEar = root.getChild("leftEar");
		this.rightHand = root.getChild("rightHand");
		this.rightFoot = root.getChild("rightFoot");
		this.leftHand = root.getChild("leftHand");
		this.leftFoot = root.getChild("leftFoot");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.0F, -5.0F, 5.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(9, 19).addBox(-1.5F, -3.5F, -6.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 25.0F, 1.0F));

		PartDefinition rightEar = partdefinition.addOrReplaceChild("rightEar", CubeListBuilder.create().texOffs(14, 14).addBox(-3.0F, 0.0F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 20.0F, -1.5F, 0.0F, 0.0F, -1.0472F));

		PartDefinition leftEar = partdefinition.addOrReplaceChild("leftEar", CubeListBuilder.create().texOffs(14, 17).addBox(0.0F, 0.0F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 20.0F, -1.5F, 0.0F, 0.0F, 1.0472F));

		PartDefinition rightHand = partdefinition.addOrReplaceChild("rightHand", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 23.0F, -2.0F));

		PartDefinition rightFoot = partdefinition.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 23.0F, 4.0F));

		PartDefinition leftHand = partdefinition.addOrReplaceChild("leftHand", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 23.0F, -2.0F));

		PartDefinition leftFoot = partdefinition.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 23.0F, 4.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 14).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, 6.0F, -1.309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(TuberEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.rightFoot.xRot = Mth.cos(limbSwing * 0.6662F) * 6 * limbSwingAmount;
		this.leftFoot.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 4 * limbSwingAmount;
		this.leftHand.xRot = Mth.cos(limbSwing * 0.6662F) * 4 * limbSwingAmount;
		this.rightHand.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 4 * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		rightEar.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		leftEar.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		rightHand.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		rightFoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		leftHand.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		leftFoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}