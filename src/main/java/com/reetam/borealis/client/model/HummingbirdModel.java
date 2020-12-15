package com.reetam.borealis.client.model;// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.reetam.borealis.entity.HummingbirdEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HummingbirdModel<T extends HummingbirdEntity> extends EntityModel<T> {
	private final ModelRenderer wings;
	private final ModelRenderer body;

	public HummingbirdModel() {
		textureWidth = 64;
		textureHeight = 64;

		wings = new ModelRenderer(this);
		wings.setRotationPoint(-6.0F, 19.0F, -6.0F);
		wings.setTextureOffset(1, 14).addBox(2.99F, -5.0F, 4.0F, 0.0F, 5.0F, 11.0F, 0.0F, false);
		wings.setTextureOffset(1, 9).addBox(9.01F, -5.0F, 4.0F, 0.0F, 5.0F, 11.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		wings.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}