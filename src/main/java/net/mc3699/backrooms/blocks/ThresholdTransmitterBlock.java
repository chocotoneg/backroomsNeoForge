package net.mc3699.backrooms.blocks;

import net.mc3699.backrooms.blocks.entity.PrototypeBlockEntity;
import net.mc3699.backrooms.dimension.BackroomsGeneration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import qouteall.imm_ptl.core.api.PortalAPI;
import qouteall.imm_ptl.core.portal.Portal;
import qouteall.imm_ptl.core.portal.PortalManipulation;
import qouteall.imm_ptl.core.portal.PortalUtils;

public class ThresholdTransmitterBlock extends Block {
    public ThresholdTransmitterBlock(Properties properties) {
        super(properties);
    }

    private void createPortal(Level level, BlockPos pos, ResourceKey<Level> targetDimension, Vec3 targetPosition)
    {
        if(level != null)
        {
            Portal portal = Portal.ENTITY_TYPE.create(level.getServer().getLevel(level.dimension()));
            assert portal != null;
            portal.setOriginPos(new Vec3(pos.getX()+0.5, pos.getY()+1.01, pos.getZ()+0.5));
            portal.setDestination(targetPosition);
            portal.setDestinationDimension(targetDimension);
            portal.setOrientationAndSize(
                    new Vec3(0,0,1),
                    new Vec3(1,0,0),
                    1.5,
                    1.5
            );
            PortalManipulation.makePortalRound(portal, 30);
            portal.level().addFreshEntity(portal);
        }
    }

    private void deletePortal(Level level, BlockPos pos)
    {
        // Delete portal entity
        AABB portalSearchArea = new AABB(pos.getX() - 2, pos.getY() - 2, pos.getZ() - 2, pos.getX() + 3, pos.getY() + 2, pos.getZ() + 2);
        for(Portal removablePortal : level.getEntitiesOfClass(Portal.class, portalSearchArea))
        {
            PortalAPI.removeGlobalPortal(level.getServer().getLevel(level.dimension()), removablePortal);
        }
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if(!level.isClientSide())
        {
            if(level.dimension() == BackroomsGeneration.BACKROOMS_DIM_KEY)
            {
                createPortal(level, pos, Level.OVERWORLD, new Vec3(pos.getX(), 322, pos.getZ()));
            } else {
                createPortal(level, pos, BackroomsGeneration.BACKROOMS_DIM_KEY, new Vec3(pos.getX(), -57, pos.getZ()));
            }
        }
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(!level.isClientSide())
        {
            deletePortal(level,pos);
        }
    }
}
