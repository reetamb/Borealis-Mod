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


public class HummingbirdModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "hummingbird"), "main");
	private final ModelPart wingLeft;
	private final ModelPart wingRight;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart head;

	public HummingbirdModel(ModelPart root) {
		this.wingLeft = root.getChild("wingLeft");
		this.wingRight = root.getChild("wingRight");
		this.body = root.getChild("body");
		this.tail = this.body.getChild("tail");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition wingLeft = partdefinition.addOrReplaceChild("wingLeft", CubeListBuilder.create().texOffs(24, 8).addBox(0.0F, -1.0F, 0.0F, 9.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 18.0F, -4.0F));

		PartDefinition wingRight = partdefinition.addOrReplaceChild("wingRight", CubeListBuilder.create().texOffs(24, 8).mirror().addBox(-9.0F, -1.0F, 0.0F, 9.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 18.0F, -4.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 8).addBox(-3.0F, -3.0003F, -3.055F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(20, 28).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 20.0F, -3.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -0.4982F, -0.3021F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.75F, 2.25F, -0.9163F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 22).addBox(-2.5F, -2.4896F, -1.2633F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(24, 14).addBox(-1.0F, -1.4896F, -7.2133F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -6.25F, 0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		wingLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		wingRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}