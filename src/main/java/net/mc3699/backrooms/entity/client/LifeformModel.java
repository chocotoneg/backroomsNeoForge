package net.mc3699.backrooms.entity.client;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.mc3699.backrooms.entity.animation.ModAnimationDefinitions;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LifeformModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart body_lower;
	private final ModelPart leg_left;
	private final ModelPart left_upper;
	private final ModelPart left_lower;
	private final ModelPart leg_right;
	private final ModelPart right_upper;
	private final ModelPart right_lower;
	private final ModelPart body_upper;
	private final ModelPart arm_root;
	private final ModelPart arm_left;
	private final ModelPart upper_left;
	private final ModelPart lower_left;
	private final ModelPart arm_right;
	private final ModelPart upper_right;
	private final ModelPart lower_right;
	private final ModelPart head;

	public LifeformModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body_lower = this.root.getChild("body_lower");
		this.leg_left = this.body_lower.getChild("leg_left");
		this.left_upper = this.leg_left.getChild("left_upper");
		this.left_lower = this.left_upper.getChild("left_lower");
		this.leg_right = this.body_lower.getChild("leg_right");
		this.right_upper = this.leg_right.getChild("right_upper");
		this.right_lower = this.right_upper.getChild("right_lower");
		this.body_upper = this.body_lower.getChild("body_upper");
		this.arm_root = this.body_upper.getChild("arm_root");
		this.arm_left = this.body_upper.getChild("arm_left");
		this.upper_left = this.arm_left.getChild("upper_left");
		this.lower_left = this.upper_left.getChild("lower_left");
		this.arm_right = this.body_upper.getChild("arm_right");
		this.upper_right = this.arm_right.getChild("upper_right");
		this.lower_right = this.upper_right.getChild("lower_right");
		this.head = this.body_upper.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition body_lower = root.addOrReplaceChild("body_lower", CubeListBuilder.create().texOffs(16, 40).addBox(-1.0F, -37.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 26.0F, 0.0F));

		PartDefinition leg_left = body_lower.addOrReplaceChild("leg_left", CubeListBuilder.create(), PartPose.offset(0.0F, -30.0F, 0.0F));

		PartDefinition root_r1 = leg_left.addOrReplaceChild("root_r1", CubeListBuilder.create().texOffs(24, 34).addBox(-1.0622F, -0.1077F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition left_upper = leg_left.addOrReplaceChild("left_upper", CubeListBuilder.create().texOffs(40, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.75F, 10.0F, 0.0F));

		PartDefinition left_lower = left_upper.addOrReplaceChild("left_lower", CubeListBuilder.create().texOffs(8, 40).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition leg_right = body_lower.addOrReplaceChild("leg_right", CubeListBuilder.create(), PartPose.offset(0.0F, -31.0F, 0.0F));

		PartDefinition root_r2 = leg_right.addOrReplaceChild("root_r2", CubeListBuilder.create().texOffs(32, 36).addBox(-0.9378F, -0.1077F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition right_upper = leg_right.addOrReplaceChild("right_upper", CubeListBuilder.create().texOffs(0, 40).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.75F, 11.0F, 0.0F));

		PartDefinition right_lower = right_upper.addOrReplaceChild("right_lower", CubeListBuilder.create().texOffs(40, 12).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition body_upper = body_lower.addOrReplaceChild("body_upper", CubeListBuilder.create().texOffs(32, 18).addBox(-1.0F, -16.0F, -1.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -37.0F, 0.0F));

		PartDefinition arm_root = body_upper.addOrReplaceChild("arm_root", CubeListBuilder.create(), PartPose.offset(12.0F, -13.0F, 0.0F));

		PartDefinition arm_root_r1 = arm_root.addOrReplaceChild("arm_root_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -23.0F, -1.0F, 2.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition arm_left = body_upper.addOrReplaceChild("arm_left", CubeListBuilder.create(), PartPose.offset(11.0F, -13.0F, 0.0F));

		PartDefinition upper_left = arm_left.addOrReplaceChild("upper_left", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition arm_r1 = upper_left.addOrReplaceChild("arm_r1", CubeListBuilder.create().texOffs(8, 16).addBox(-1.0F, -22.0F, -1.0F, 2.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition lower_left = upper_left.addOrReplaceChild("lower_left", CubeListBuilder.create(), PartPose.offset(22.0F, 0.0F, 0.0F));

		PartDefinition forearm_r1 = lower_left.addOrReplaceChild("forearm_r1", CubeListBuilder.create().texOffs(24, 16).addBox(-1.0F, -16.0F, -1.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition arm_right = body_upper.addOrReplaceChild("arm_right", CubeListBuilder.create(), PartPose.offset(-11.0F, -13.0F, 0.0F));

		PartDefinition upper_right = arm_right.addOrReplaceChild("upper_right", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition arm_r2 = upper_right.addOrReplaceChild("arm_r2", CubeListBuilder.create().texOffs(16, 16).addBox(-1.0F, -22.0F, -1.0F, 2.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0087F, -1.5708F));

		PartDefinition lower_right = upper_right.addOrReplaceChild("lower_right", CubeListBuilder.create(), PartPose.offset(-22.0F, 0.0F, 0.0F));

		PartDefinition forearm_r2 = lower_right.addOrReplaceChild("forearm_r2", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, -16.0F, -1.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0087F, -1.5708F));

		PartDefinition head = body_upper.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}


	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(ModAnimationDefinitions.lifeform_gen1_walk, v1, v2, 1f, 2f);
	}
}