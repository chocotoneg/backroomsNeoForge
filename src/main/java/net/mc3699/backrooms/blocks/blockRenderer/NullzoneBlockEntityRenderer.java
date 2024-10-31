package net.mc3699.backrooms.blocks.blockRenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.mc3699.backrooms.blocks.entity.NullzoneBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class NullzoneBlockEntityRenderer implements BlockEntityRenderer<NullzoneBlockEntity> {

    public final BlockRenderDispatcher renderDispatcher;

    public NullzoneBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.renderDispatcher = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(@NotNull NullzoneBlockEntity nullzoneBlockEntity, float v, @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i, int i1) {
        BlockState fakeBlockState = Blocks.SAND.defaultBlockState();
        if(fakeBlockState != null)
        {
            renderDispatcher.renderSingleBlock(fakeBlockState, poseStack,  multiBufferSource, i, i1);
        }
    }
}
