package net.mc3699.backrooms.blocks;

import net.mc3699.backrooms.dimension.BackroomsGeneration;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import qouteall.imm_ptl.core.portal.Portal;
import qouteall.imm_ptl.core.portal.shape.PortalShape;

import javax.sound.sampled.Port;

public class PrototypeBlock extends Block {
    public PrototypeBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(!level.isClientSide())
        {
            Portal portal = new Portal(Portal.ENTITY_TYPE,  level);
            portal.setOriginPos(new Vec3(pos.getX()+0.5,pos.getY()+2,pos.getZ()+0.5));
            portal.setDestinationDimension(BackroomsGeneration.BACKROOMS_DIM_KEY);
            portal.setDestination(new Vec3(pos.getX(), -61, pos.getZ()));
            portal.setOrientationAndSize(
                    new Vec3(1,0,0),
                    new Vec3(0,1,0),
                    1,
                    2);
            level.addFreshEntity(portal);
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }
}
