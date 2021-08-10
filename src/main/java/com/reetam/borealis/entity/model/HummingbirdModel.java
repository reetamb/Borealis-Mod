package com.reetam.borealis.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class HummingbirdModel<T extends Entity> extends EntityModel<T> {

	private final ModelRenderer hummingbird;
	private final ModelRenderer body;
	private final ModelRenderer wingLeft;
	private final ModelRenderer wingRight;
	private final ModelRenderer tail;
	private final ModelRenderer head;

	float flap = 0.4F;

	public HummingbirdModel() {
		texWidth = 64;
		texHeight = 32;

		hummingbird = new ModelRenderer(this);
		hummingbird.setPos(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, -4.0F, -3.0F);
		hummingbird.addChild(body);
		setRotationAngle(body, 0.6545F, 0.0F, 0.0F);
		body.texOffs(0, 0).addBox(-3.0F, -5.0003F, -3.055F, 6.0F, 10.0F, 6.0F, 0.0F, false);

		wingLeft = new ModelRenderer(this);
		wingLeft.setPos(3.0F, -2.0F, 0.0F);
		body.addChild(wingLeft);
		setRotationAngle(wingLeft, -0.5236F, 0.0F, 0.0F);
		wingLeft.texOffs(26, 0).addBox(-1.0F, -1.0F, -1.0F, 10.0F, 1.0F, 2.0F, 0.0F, true);
		wingLeft.texOffs(26, 4).addBox(0.0F, 0.0F, 0.0F, 9.0F, 5.0F, 0.0F, 0.0F, true);

		wingRight = new ModelRenderer(this);
		wingRight.setPos(-3.0F, -2.0F, 0.0F);
		body.addChild(wingRight);
		setRotationAngle(wingRight, -0.5236F, 0.0F, 0.0F);
		wingRight.texOffs(26, 0).addBox(-9.0F, -1.0F, -1.0F, 10.0F, 1.0F, 2.0F, 0.0F, false);
		wingRight.texOffs(26, 4).addBox(-9.0F, 0.0F, 0.0F, 9.0F, 5.0F, 0.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, 4.75F, 2.25F);
		body.addChild(tail);
		setRotationAngle(tail, -0.9163F, 0.0F, 0.0F);
		tail.texOffs(44, 9).addBox(-2.5F, -1.0982F, -0.3021F, 5.0F, 2.0F, 5.0F, 0.0F, false);
		tail.texOffs(30, 17).addBox(-3.5F, -0.4982F, 3.6979F, 7.0F, 0.0F, 7.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -7.0F, -3.25F);
		hummingbird.addChild(head);
		setRotationAngle(head, 0.1309F, 0.0F, 0.0F);
		head.texOffs(0, 18).addBox(-2.5F, -5.8812F, -5.2376F, 5.0F, 6.0F, 6.0F, 0.0F, false);
		head.texOffs(23, 23).addBox(-1.0F, -4.8812F, -11.1876F, 2.0F, 1.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ticksExisted, float headYaw, float headPitch) {
		wingLeft.yRot += flap;
		wingRight.yRot -= flap;

		if (wingLeft.yRot >= 0.8) {
			flap = -0.4F;
		} else if (wingLeft.yRot <= -0.8) {
			flap = 0.4F;
		}
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		hummingbird.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}