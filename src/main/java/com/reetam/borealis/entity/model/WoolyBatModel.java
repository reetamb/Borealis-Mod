package com.reetam.borealis.entity.model;
// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.reetam.borealis.BorealisMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;


public class WoolyBatModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "wooly_bat"), "main");
	private final ModelPart head;
	private final ModelPart face;
	private final ModelPart body;
	private final ModelPart feet;
	private final ModelPart leftWing3;
	private final ModelPart leftWing4;
	private final ModelPart rightWing3;
	private final ModelPart rightWing4;
	private final ModelPart bb_main;

	public WoolyBatModel(ModelPart root) {
		this.head = root.getChild("head");
		this.face = this.head.getChild("face");
		this.body = root.getChild("body");
		this.feet = this.body.getChild("feet");
		this.leftWing3 = root.getChild("leftWing3");
		this.leftWing4 = this.leftWing3.getChild("leftWing4");
		this.rightWing3 = root.getChild("rightWing3");
		this.rightWing4 = this.rightWing3.getChild("rightWing4");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(56, 0).addBox(-8.0F, -12.0F, -1.0F, 16.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 1.0F));

		PartDefinition face = head.addOrReplaceChild("face", CubeListBuilder.create().texOffs(53, 29).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(96, 0).addBox(-3.0F, -6.0F, -9.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 24).addBox(-6.0F, -18.0F, -6.0F, 12.0F, 16.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.0F, -20.0F, -7.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition feet = body.addOrReplaceChild("feet", CubeListBuilder.create().texOffs(92, 10).addBox(-10.0F, 2.0F, -7.0F, 10.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -4.0F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition leftWing3 = partdefinition.addOrReplaceChild("leftWing3", CubeListBuilder.create().texOffs(56, 16).addBox(-12.0F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 52).mirror().addBox(-12.0F, 0.0F, 0.0F, 16.0F, 19.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0152F, 5.1736F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition leftWing4 = leftWing3.addOrReplaceChild("leftWing4", CubeListBuilder.create().texOffs(56, 12).addBox(-18.5808F, -0.7736F, -0.9F, 18.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 44).mirror().addBox(-18.0F, 0.0F, 0.1F, 18.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-11.134F, -0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition rightWing3 = partdefinition.addOrReplaceChild("rightWing3", CubeListBuilder.create().texOffs(56, 16).mirror().addBox(-1.0F, -1.0F, -1.0F, 13.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 52).addBox(-4.0F, 0.0F, 0.0F, 16.0F, 19.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0152F, 5.1736F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition rightWing4 = rightWing3.addOrReplaceChild("rightWing4", CubeListBuilder.create().texOffs(56, 12).mirror().addBox(0.5808F, -0.7736F, -0.9F, 18.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(48, 44).addBox(0.0F, 0.0F, 0.1F, 18.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.134F, -0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(-28, -14).addBox(-24.0F, -32.0F, 8.0F, 16.0F, 32.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		leftWing3.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		rightWing3.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}