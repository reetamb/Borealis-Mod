package com.reetam.borealis.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.entity.model.HummingbirdModel;
import com.reetam.borealis.entity.HummingbirdEntity;

@OnlyIn(Dist.CLIENT)
public class HummingbirdRenderer extends MobRenderer<HummingbirdEntity, HummingbirdModel<HummingbirdEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BorealisMod.MODID, "textures/entity/hummingbird.png");

    public HummingbirdRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HummingbirdModel<>(), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(HummingbirdEntity entity) {
        return TEXTURE;
    }
}