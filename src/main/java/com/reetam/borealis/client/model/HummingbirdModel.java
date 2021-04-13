package com.reetam.borealis.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HummingbirdModel<T extends Entity> extends EntityModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer wingLeft;
	private final ModelRenderer wingRight;
	private final ModelRenderer tail;

	public HummingbirdModel() {
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 4.0F);
		setRotationAngle(body, 0.0873F, 0.0F, 0.0F);
		body.setTextureOffset(44, 2).addBox(-1.0F, -14.0F, -15.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-4.0F, -16.0F, -8.0F, 8.0F, 16.0F, 8.0F, 0.0F, false);

		wingLeft = new ModelRenderer(this);
		wingLeft.setRotationPoint(4.0F, 13.0F, 3.0F);
		wingLeft.setTextureOffset(32, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);
		wingLeft.setTextureOffset(0, 4).addBox(0.1F, -1.0F, -1.0F, 0.0F, 8.0F, 20.0F, 0.0F, false);

		wingRight = new ModelRenderer(this);
		wingRight.setRotationPoint(-4.0F, 13.0F, 3.0F);
		wingRight.setTextureOffset(32, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 8.0F, 0.0F, true);
		wingRight.setTextureOffset(0, 4).addBox(-0.1F, -1.0F, -1.0F, 0.0F, 8.0F, 20.0F, 0.0F, true);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 24.0F, 4.0F);
		setRotationAngle(tail, -0.5236F, 0.0F, 0.0F);
		tail.setTextureOffset(22, 14).addBox(-6.0F, 0.0F, 0.0F, 12.0F, 0.0F, 10.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		wingLeft.render(matrixStack, buffer, packedLight, packedOverlay);
		wingRight.render(matrixStack, buffer, packedLight, packedOverlay);
		tail.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}