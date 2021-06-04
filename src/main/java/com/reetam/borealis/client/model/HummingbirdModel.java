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
		texWidth = 64;
		texHeight = 32;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 4.0F);
		setRotationAngle(body, 0.0873F, 0.0F, 0.0F);
		body.texOffs(44, 2).addBox(-1.0F, -14.0F, -15.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);
		body.texOffs(0, 0).addBox(-4.0F, -16.0F, -8.0F, 8.0F, 16.0F, 8.0F, 0.0F, false);

		wingLeft = new ModelRenderer(this);
		wingLeft.setPos(4.0F, 13.0F, 3.0F);
		wingLeft.texOffs(32, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);
		wingLeft.texOffs(0, 4).addBox(0.1F, -1.0F, -1.0F, 0.0F, 8.0F, 20.0F, 0.0F, false);

		wingRight = new ModelRenderer(this);
		wingRight.setPos(-4.0F, 13.0F, 3.0F);
		wingRight.texOffs(32, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 8.0F, 0.0F, true);
		wingRight.texOffs(0, 4).addBox(-0.1F, -1.0F, -1.0F, 0.0F, 8.0F, 20.0F, 0.0F, true);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, 24.0F, 4.0F);
		setRotationAngle(tail, -0.5236F, 0.0F, 0.0F);
		tail.texOffs(22, 14).addBox(-6.0F, 0.0F, 0.0F, 12.0F, 0.0F, 10.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		wingLeft.render(matrixStack, buffer, packedLight, packedOverlay);
		wingRight.render(matrixStack, buffer, packedLight, packedOverlay);
		tail.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}