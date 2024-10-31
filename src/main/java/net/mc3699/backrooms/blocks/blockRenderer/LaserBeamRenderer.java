package net.mc3699.backrooms.blocks.blockRenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.mc3699.backrooms.blocks.LaserBlock;
import net.mc3699.backrooms.blocks.entity.LaserBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.awt.*;

public class LaserBeamRenderer implements BlockEntityRenderer<LaserBlockEntity> {

    private BlockEntityRendererProvider.Context ctx;

    public LaserBeamRenderer(BlockEntityRendererProvider.Context context) {
        ctx = context;
    }


    @Override
    public void render(LaserBlockEntity laserBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
            if(laserBlockEntity.getBlockState().getValue(LaserBlock.EMITTING))
            {
                poseStack.pushPose();
                poseStack.translate(0.5,0.5,0.5);



                switch (laserBlockEntity.getBlockState().getValue(LaserBlock.FACING))
                {
                    case NORTH -> poseStack.mulPose(Axis.XP.rotationDegrees(90));
                    case SOUTH -> poseStack.mulPose(Axis.XP.rotationDegrees(-90));
                    case EAST  -> poseStack.mulPose(Axis.ZP.rotationDegrees(90));
                    case WEST  -> poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
                }

                VertexConsumer builder = multiBufferSource.getBuffer(RenderType.lines());
                drawLine(poseStack, builder, 0,0,0,0,4,0);
                drawLine(poseStack, builder, 0.25f,0,0,0.25f,4,0);
                drawLine(poseStack, builder, -0.25f,0,0,-0.25f,4,0);
                drawLine(poseStack, builder, 0,0,0.25f,0,4,0.25f);
                drawLine(poseStack, builder, 0,0,-0.25f,0,4,-0.25f);
                poseStack.popPose();
            }
    }

    @Override
    public boolean shouldRenderOffScreen(LaserBlockEntity blockEntity) {
        return true;
    }

    private static void drawLine(PoseStack poseStack, VertexConsumer vertexConsumer, float x1, float y1, float z1, float x2, float y2, float z2)
    {
        vertexConsumer.addVertex(poseStack.last().pose(), x1,y1,z1)
                .setColor(255, 255, 0, 255)
                .setNormal(poseStack.last(), 0,1,0);
        vertexConsumer.addVertex(poseStack.last().pose(), x2,y2,z2)
                .setColor(255,255,0,255)
                .setNormal(poseStack.last(), 0,1,0);
    }
}