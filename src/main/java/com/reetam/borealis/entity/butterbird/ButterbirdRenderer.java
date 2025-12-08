package com.reetam.borealis.entity.butterbird;

import com.reetam.borealis.BorealisMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ButterbirdRenderer extends MobRenderer<ButterbirdEntity, ButterbirdModel<ButterbirdEntity>> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "textures/entity/butterbird.png");

    public ButterbirdRenderer(EntityRendererProvider.Context context) {
        super(context, new ButterbirdModel<>(context.bakeLayer(ButterbirdModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(ButterbirdEntity entity) {
        return TEXTURE;
    }
}