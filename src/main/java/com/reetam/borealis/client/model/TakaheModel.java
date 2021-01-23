package com.reetam.borealis.client.model;
// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class TakaheModel<T extends Entity> extends EntityModel<T> {
	private final ModelRenderer beak;
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public TakaheModel() {
		textureWidth = 64;
		textureHeight = 64;

		beak = new ModelRenderer(this);
		beak.setRotationPoint(0.0F, 19.0F, 4.0F);
		setRotationAngle(beak, 0.3491F, 0.0F, 0.0F);
		beak.setTextureOffset(0, 18).addBox(-1.5F, -12.4745F, -12.4302F, 3.0F, 4.0F, 5.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.setTextureOffset(16, 18).addBox(-2.5F, -14.25F, -7.7F, 5.0F, 6.0F, 5.0F, 0.0F, false);
		head.setTextureOffset(0, 35).addBox(-3.0F, -14.75F, -8.25F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(0, 28).addBox(-1.5F, -18.75F, -6.75F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 11.0F, -2.0F);
		setRotationAngle(body, -0.3491F, 0.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-3.5F, -0.46F, -0.9799F, 7.0F, 8.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(28, 2).addBox(3.4F, 0.061F, 0.9209F, 1.0F, 7.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(28, 2).addBox(-4.5F, 0.061F, 0.9209F, 1.0F, 7.0F, 8.0F, 0.0F, true);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(0.0F, 19.0F, 1.0F);
		rightLeg.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		rightLeg.setTextureOffset(33, 17).addBox(-4.0F, 5.0F, -3.95F, 4.0F, 0.0F, 3.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(2.0F, 20.0F, 1.0F);
		leftLeg.setTextureOffset(26, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		leftLeg.setTextureOffset(33, 17).addBox(-2.0F, 4.0F, -4.0F, 4.0F, 0.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		beak.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}