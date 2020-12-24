package com.reetam.borealis.client.model;// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HummingbirdModel<T extends Entity> extends EntityModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer wings;
	private final ModelRenderer tail;

	public HummingbirdModel() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(3.0F, 24.0F, -3.0F);
		setRotationAngle(body, 0.0873F, 0.0F, 0.0F);
		body.setTextureOffset(24, 0).addBox(-4.0F, -11.6934F, -4.8824F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-6.0F, -14.0F, 0.0F, 6.0F, 14.0F, 6.0F, 0.0F, false);

		wings = new ModelRenderer(this);
		wings.setRotationPoint(-9.0F, -6.0038F, 0.0F);
		body.addChild(wings);
		wings.setTextureOffset(0, 20).addBox(1.99F, -4.0076F, 3.8257F, 1.0F, 6.0F, 12.0F, 0.0F, true);
		wings.setTextureOffset(0, 20).addBox(9.01F, -4.0076F, 3.8257F, 1.0F, 6.0F, 12.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(3.0F, 22.0F, -3.0F);
		setRotationAngle(tail, -0.1745F, 0.0F, 0.0F);
		tail.setTextureOffset(24, 8).addBox(-6.5F, -0.1875F, 5.7744F, 7.0F, 1.0F, 7.0F, 0.0F, false);
		tail.setTextureOffset(24, 16).addBox(-5.5F, -0.1875F, 5.7744F, 5.0F, 1.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		setRotationAngle(wings, (float) (0.08 + Math.sin(5 * entity.ticksExisted) / 2), 0.0F, 0.0F);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		tail.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}