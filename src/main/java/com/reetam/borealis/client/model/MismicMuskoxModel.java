package com.reetam.borealis.client.model;
// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class MismicMuskoxModel<T extends Entity> extends EntityModel<T> {
	private final ModelRenderer Body;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;

	public MismicMuskoxModel() {
		textureWidth = 128;
		textureHeight = 128;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 5.0F, 2.0F);
		body.setTextureOffset(44, 48).addBox(-6.0F, -3.0F, -10.0F, 12.0F, 10.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-6.5F, -4.0F, -11.0F, 13.0F, 14.0F, 22.0F, 0.0F, false);
		body.setTextureOffset(0, 36).addBox(-8.0F, -7.0F, -12.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		body.setTextureOffset(48, 0).addBox(-7.0F, -5.0F, 4.0F, 14.0F, 13.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(48, 36).addBox(-6.0F, -9.0F, -10.0F, 12.0F, 2.0F, 9.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 6.0F, -9.0F);
		head.setTextureOffset(16, 4).addBox(-1.0F, -2.0F, -6.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(6, 16).addBox(-3.5F, -1.0F, -6.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 16).addBox(1.5F, -1.0F, -6.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		ModelRenderer head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, 0.0F, -3.0F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, -0.2618F, 0.0F, 0.0F);
		head_r1.setTextureOffset(0, 68).addBox(-4.0F, -4.0F, -3.0F, 8.0F, 10.0F, 6.0F, 0.0F, false);

		ModelRenderer bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(1.0F, -4.0F, -1.0F);
		head.addChild(bone2);


		ModelRenderer cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(4.0F, 1.611F, -1.1849F);
		bone2.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.2618F, 0.0F, -0.1745F);
		cube_r1.setTextureOffset(12, 0).addBox(1.5F, 1.0F, -1.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		ModelRenderer cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(4.25F, 2.111F, -1.5849F);
		bone2.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.2618F, 0.0F, -0.1745F);
		cube_r2.setTextureOffset(81, 30).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);

		ModelRenderer cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone2.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.3927F, 0.0F, 0.1745F);
		cube_r3.setTextureOffset(70, 21).addBox(-1.25F, -4.0F, -4.0F, 6.0F, 4.0F, 5.0F, 0.0F, false);

		ModelRenderer bone = new ModelRenderer(this);
		bone.setRotationPoint(-1.0F, -4.0F, -4.0F);
		head.addChild(bone);
		setRotationAngle(bone, 0.0F, 3.1416F, 0.0F);


		ModelRenderer cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(4.0F, 1.611F, -1.0849F);
		bone.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.2618F, 0.0F, -0.1745F);
		cube_r4.setTextureOffset(14, 14).addBox(1.4566F, 1.2119F, -1.3397F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		ModelRenderer cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(4.25F, 2.111F, -1.5849F);
		bone.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.2618F, 0.0F, -0.1745F);
		cube_r5.setTextureOffset(82, 82).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);

		ModelRenderer cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.3927F, 0.0F, 0.1745F);
		cube_r6.setTextureOffset(60, 78).addBox(-1.1458F, -2.9F, -3.7739F, 6.0F, 4.0F, 5.0F, 0.0F, false);

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(4.0F, 12.0F, 7.0F);
		leg0.setTextureOffset(44, 78).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-4.0F, 12.0F, 7.0F);
		leg1.setTextureOffset(28, 68).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(4.0F, 12.0F, -6.0F);
		leg2.setTextureOffset(0, 36).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-4.0F, 12.0F, -6.0F);
		leg3.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.leg0.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		leg0.render(matrixStack, buffer, packedLight, packedOverlay);
		leg1.render(matrixStack, buffer, packedLight, packedOverlay);
		leg2.render(matrixStack, buffer, packedLight, packedOverlay);
		leg3.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}