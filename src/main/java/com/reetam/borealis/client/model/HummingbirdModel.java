package com.reetam.borealis.client.model;
// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import com.reetam.borealis.entity.HummingbirdEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class HummingbirdModel<T extends HummingbirdEntity> extends EntityModel<T> {
	private final ModelRenderer TAIL;
	private final ModelRenderer BODY;
	private final ModelRenderer WING;

	public HummingbirdModel() {
		textureWidth = 48;
		textureHeight = 48;

		TAIL = new ModelRenderer(this);
		TAIL.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(TAIL, 0.0F, 0.0F, 0.4363F);
		TAIL.setTextureOffset(8, 18).addBox(2.0F, -5.0F, -4.0F, 8.0F, 0.0F, 8.0F, 0.0F, false);

		BODY = new ModelRenderer(this);
		BODY.setRotationPoint(-0.5F, 24.0F, 0.0F);
		setRotationAngle(BODY, 0.0F, 0.0F, 1.0472F);
		BODY.setTextureOffset(0, 0).addBox(-16.0F, -6.0F, -3.0F, 16.0F, 6.0F, 6.0F, 0.0F, false);
		BODY.setTextureOffset(0, 18).addBox(-14.0F, -2.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		WING = new ModelRenderer(this);
		WING.setRotationPoint(0.5F, 20.0F, -2.0F);
		setRotationAngle(WING, 0.0F, 0.0F, -0.0873F);
		WING.setTextureOffset(0, 12).addBox(-4.0F, -8.0F, -1.001F, 12.0F, 6.0F, 0.0F, 0.0F, false);
		WING.setTextureOffset(0, 12).addBox(-4.0F, -8.0F, 5.0F, 12.0F, 6.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

//	@Override
//	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
//		//previously the render function, render code was moved to a method below
//	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		TAIL.render(matrixStack, buffer, packedLight, packedOverlay);
		BODY.render(matrixStack, buffer, packedLight, packedOverlay);
		WING.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}