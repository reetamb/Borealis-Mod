package com.reetam.borealis.entity.model;
// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17 with Mojang mappings
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


public class HummingbirdModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "hummingbird"), "main");
	private final ModelPart wingLeft;
	private final ModelPart wingRight;
	private final ModelPart body;
	private final ModelPart head;

	private float flapSpeed = 0.4F;
	private float flapDist = 1.6F;

	public HummingbirdModel(ModelPart root) {
		this.wingLeft = root.getChild("wing_left");
		this.wingRight = root.getChild("wing_right");
		this.body = root.getChild("body");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("wing_left", CubeListBuilder.create().texOffs(26, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(26, 4).mirror().addBox(0.0F, 0.0F, 0.0F, 9.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 18.0F, -4.0F));

		partdefinition.addOrReplaceChild("wing_right", CubeListBuilder.create().texOffs(26, 0).addBox(-9.0F, -1.0F, -1.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(26, 4).addBox(-9.0F, 0.0F, 0.0F, 9.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 18.0F, -4.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.0003F, -3.055F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -3.0F, 0.6545F, 0.0F, 0.0F));

		body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(44, 9).addBox(-2.5F, -1.0982F, -0.3021F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(30, 17).addBox(-3.5F, -0.4982F, 3.6979F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.75F, 2.25F, -0.9163F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-2.5F, -5.4896F, -2.2633F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(23, 23).addBox(-1.0F, -4.4896F, -8.2133F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -6.25F, 0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		wingLeft.yRot += flapSpeed;
		wingRight.yRot -= flapSpeed;

		if (wingLeft.yRot >= flapDist) flapSpeed = -0.4F;
		else if (wingLeft.yRot <= -flapDist) flapSpeed = 0.4F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		wingLeft.render(poseStack, buffer, packedLight, packedOverlay);
		wingRight.render(poseStack, buffer, packedLight, packedOverlay);
		body.render(poseStack, buffer, packedLight, packedOverlay);
		head.render(poseStack, buffer, packedLight, packedOverlay);
	}
}