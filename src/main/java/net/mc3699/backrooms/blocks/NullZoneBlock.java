package net.mc3699.backrooms.blocks;

import net.mc3699.backrooms.blocks.entity.NullzoneBlockEntity;
import net.mc3699.backrooms.dimension.BackroomsGeneration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import qouteall.imm_ptl.core.api.PortalAPI;
import qouteall.imm_ptl.core.portal.Portal;
import qouteall.imm_ptl.core.portal.PortalManipulation;

public class NullZoneBlock extends Block implements EntityBlock {
    public NullZoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }


    private void createPortal(Level level, BlockPos pos, ResourceKey<Level> targetDimension, Vec3 targetPosition)
    {
        if(level != null)
        {
            // Create new portal entity
            Portal portal = Portal.ENTITY_TYPE.create(level.getServer().getLevel(level.dimension()));
            assert portal != null;
            portal.setOriginPos(new Vec3(pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5));
            portal.setDestination(targetPosition);
            portal.setDestinationDimension(targetDimension);
            portal.setOrientationAndSize(
                    new Vec3(0,0,1),
                    new Vec3(1,0,0),
                    1,
                    1
            );
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
        BlockPos copyPos = pos.north();
        BlockState adjacentState = level.getBlockState(copyPos);

        if(!level.isClientSide())
        {
            createPortal(level, pos, BackroomsGeneration.BACKROOMS_DIM_KEY, new Vec3(pos.getX(), -57, pos.getZ()));

            if(level.getBlockEntity(pos) instanceof NullzoneBlockEntity nullEnt)
            {
                nullEnt.setFakeState(adjacentState);
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

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new NullzoneBlockEntity(blockPos, blockState);
    }

}
