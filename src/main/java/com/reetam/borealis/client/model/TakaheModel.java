package com.reetam.borealis.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class TakaheModel<T extends Entity> extends EntityModel<T> {
	private final ModelRenderer beak;
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public TakaheModel() {
		textureWidth = 64;
		textureHeight = 32;

		beak = new ModelRenderer(this);
		beak.setRotationPoint(0.0F, 19.0F, 4.0F);
		setRotationAngle(beak, 0.3491F, 0.0F, 0.0F);
		beak.setTextureOffset(21, 0).addBox(-1.5F, -9.9543F, -12.8155F, 3.0F, 4.0F, 5.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.setTextureOffset(44, 0).addBox(-2.5F, -12.0F, -7.2F, 5.0F, 5.0F, 5.0F, 0.0F, false);
		head.setTextureOffset(0, 16).addBox(-3.0F, -12.75F, -7.75F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(24, 16).addBox(-2.0F, -16.75F, -6.75F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(44, 7).addBox(0.0F, -15.0F, -6.25F, 0.0F, 3.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 11.0F, -2.0F);
		setRotationAngle(body, -0.3491F, 0.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-3.0F, 1.2182F, 0.3451F, 6.0F, 7.0F, 9.0F, 0.0F, false);
		body.setTextureOffset(48, 10).addBox(2.9F, 1.2392F, 1.2459F, 1.0F, 6.0F, 7.0F, 0.0F, false);
		body.setTextureOffset(48, 10).addBox(-4.0F, 1.2392F, 1.2459F, 1.0F, 6.0F, 7.0F, 0.0F, true);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(0.0F, 19.0F, 1.0F);
		rightLeg.setTextureOffset(0, 0).addBox(-2.75F, 1.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		rightLeg.setTextureOffset(15, 16).addBox(-3.75F, 5.0F, -4.95F, 4.0F, 0.0F, 3.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(2.0F, 20.0F, 1.0F);
		leftLeg.setTextureOffset(0, 0).addBox(-1.25F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		leftLeg.setTextureOffset(15, 16).addBox(-2.25F, 4.0F, -5.0F, 4.0F, 0.0F, 3.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
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