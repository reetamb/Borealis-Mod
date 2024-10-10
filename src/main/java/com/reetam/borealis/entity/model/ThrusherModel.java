package com.reetam.borealis.entity.model;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ThrusherModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "thrusher"), "main");
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart frontLeftWingLeg;
	private final ModelPart frontRightWingLeg;
	private final ModelPart backLeftLeg;
	private final ModelPart backRightLeg;

	public ThrusherModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.frontLeftWingLeg = root.getChild("frontLeftWingLeg");
		this.frontRightWingLeg = root.getChild("frontRightWingLeg");
		this.backLeftLeg = root.getChild("backLeftLeg");
		this.backRightLeg = root.getChild("backRightLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(38, 40).addBox(-5.0F, -17.2938F, -5.2261F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(96, 0).addBox(3.0F, -27.2938F, -7.2261F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(96, 0).addBox(-3.0F, -27.2938F, -7.2261F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(74, 30).addBox(-3.0F, -17.2938F, -11.2261F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(98, 28).addBox(-4.0F, -11.2938F, -3.2261F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(80, 30).addBox(-3.0F, -10.3109F, -11.2261F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -8.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -9.654F, -12.0F, 16.0F, 12.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(66, 46).addBox(-8.0F, -9.654F, 12.0F, 16.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition frontLeftWingLeg = partdefinition.addOrReplaceChild("frontLeftWingLeg", CubeListBuilder.create().texOffs(112, 0).addBox(12.15F, -4.0F, 0.5F, 0.0F, 18.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(76, 0).mirror().addBox(6.6F, -4.0F, -3.5F, 6.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.8F, 10.0F, -8.0F));

		PartDefinition frontRightWingLeg = partdefinition.addOrReplaceChild("frontRightWingLeg", CubeListBuilder.create().texOffs(76, 0).addBox(-3.0F, -4.0F, -3.5F, 6.0F, 18.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(112, 0).addBox(-2.55F, -4.0F, 0.5F, 0.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.8F, 10.0F, -8.0F));

		PartDefinition backLeftLeg = partdefinition.addOrReplaceChild("backLeftLeg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -4.0F, -2.5F, 6.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(48, 3).mirror().addBox(-4.6F, 11.9F, -6.5F, 10.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.8F, 12.0F, 6.5F));

		PartDefinition backRightLeg = partdefinition.addOrReplaceChild("backRightLeg", CubeListBuilder.create().texOffs(48, 4).addBox(-5.0F, 11.9F, -7.5F, 10.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -4.1F, -2.5F, 6.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.8F, 12.0F, 6.5F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.backRightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.backLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.frontLeftWingLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.frontRightWingLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		frontLeftWingLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		frontRightWingLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		backLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		backRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}