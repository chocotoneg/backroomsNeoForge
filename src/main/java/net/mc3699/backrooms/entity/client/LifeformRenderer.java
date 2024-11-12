package net.mc3699.backrooms.entity.client;

import net.mc3699.backrooms.BackroomsMod;
import net.mc3699.backrooms.entity.behavior.LifeformEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LifeformRenderer extends MobRenderer<LifeformEntity, LifeformModel<LifeformEntity>> {
    public LifeformRenderer(EntityRendererProvider.Context context) {
        super(context, new LifeformModel<>(context.bakeLayer(ModModelLayers.LIFEFORM_GEN_1_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(LifeformEntity lifeformEntity) {
        return ResourceLocation.fromNamespaceAndPath(BackroomsMod.MODID, "textures/entity/lifeform_gen_1.png");
    }
}
