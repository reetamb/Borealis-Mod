package com.reetam.borealis.entity.model;
// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.TakaheEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;


public class ButterbirdModel<T extends TakaheEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "butterbird"), "main");
	private final ModelPart body;
	private final ModelPart arms;
	private final ModelPart tail;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart leftWing;
	private final ModelPart rightWing;
	private final ModelPart head;
	private final ModelPart beak;

	public ButterbirdModel(ModelPart root) {
		this.body = root.getChild("body");
		this.arms = this.body.getChild("arms");
		this.tail = this.body.getChild("tail");
		this.rightLeg = this.body.getChild("rightLeg");
		this.leftLeg = this.body.getChild("leftLeg");
		this.leftWing = this.body.getChild("leftWing");
		this.rightWing = this.body.getChild("rightWing");
		this.head = this.body.getChild("head");
		this.beak = this.head.getChild("beak");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -18.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 26).addBox(-6.0F, -18.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).mirror().addBox(-4.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -8.0F, -5.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(24, 0).addBox(2.0F, 0.0F, 0.0F, 12.0F, 0.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).mirror().addBox(-10.0F, 0.0F, 0.0F, 12.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -18.0F, 6.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(40, 56).mirror().addBox(-3.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(41, 57).mirror().addBox(-6.0F, -0.1F, -5.0F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(40, 56).addBox(1.0F, -6.1F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(41, 57).addBox(0.0F, -0.1F, -5.0F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(40, 16).addBox(8.01F, 0.0F, -11.0F, 2.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -18.0F, 6.0F));

		PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(40, 16).addBox(-6.01F, 0.0F, -11.0F, 2.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -18.0F, 6.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 52).addBox(-2.0F, -15.0F, -13.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -4.0F, 2.0F));

		PartDefinition beak = head.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(24, 53).addBox(-1.5F, -11.5F, 3.0F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, -16.0F, 0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(TakaheEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}