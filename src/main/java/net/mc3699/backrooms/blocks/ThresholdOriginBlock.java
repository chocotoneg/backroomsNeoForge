package net.mc3699.backrooms.blocks;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import de.nick1st.imm_ptl.events.PortalEvent;
import net.mc3699.backrooms.dimension.BackroomsGeneration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import qouteall.imm_ptl.core.api.PortalAPI;
import qouteall.imm_ptl.core.portal.Portal;
import qouteall.imm_ptl.core.portal.PortalManipulation;
import qouteall.imm_ptl.core.portal.PortalUtils;
import qouteall.imm_ptl.peripheral.portal_generation.PortalHelperForm;

public class ThresholdOriginBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    protected ThresholdOriginBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED,false));
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    Vec3 NORTH_ORIENTATION = new Vec3(1,0,0);
    Vec3 SOUTH_ORIENTATION = new Vec3(-1,0,0);
    Vec3 EAST_ORIENTATION = new Vec3(0,0,1);
    Vec3 WEST_ORIENTATION = new Vec3(0,0,-1);

    Vec3 NORTH_OFFSET = new Vec3(0,2,-0.001);
    Vec3 SOUTH_OFFSET = new Vec3(1,2,+1.001);
    Vec3 EAST_OFFSET = new Vec3(1.001,0.5,0.5);
    Vec3 WEST_OFFSET = new Vec3(-0.001,0.5,0.5);


    private void createPortalWithOffset(Level level, BlockPos pos, Vec3 orientOffset, Vec3 transOffset)
    {
        Portal portal = createPortal(level, pos, BackroomsGeneration.BACKROOMS_DIM_KEY, new Vec3(pos.getX(),-60,pos.getZ()), new Vec3(0,1,0), orientOffset, transOffset);
        PortalManipulation.completeBiWayPortal(portal, Portal.ENTITY_TYPE);
    }

    private void placeByDirection(Level level, BlockPos pos)
    {
        if(!level.isClientSide())
        {
            if(level.getBlockState(pos).getValue(FACING) == Direction.NORTH)
            {
                createPortalWithOffset(level,pos,NORTH_ORIENTATION,NORTH_OFFSET);
            }
            if(level.getBlockState(pos).getValue(FACING) == Direction.SOUTH)
            {
                createPortalWithOffset(level,pos,SOUTH_ORIENTATION,SOUTH_OFFSET);
            }
            if(level.getBlockState(pos).getValue(FACING) == Direction.EAST)
            {
                createPortalWithOffset(level,pos,EAST_ORIENTATION,EAST_OFFSET);
            }
            if(level.getBlockState(pos).getValue(FACING) == Direction.WEST)
            {
                createPortalWithOffset(level,pos,WEST_ORIENTATION,WEST_OFFSET);
            }

        }

    }

    private void processRedstone(Level level, BlockPos pos)
    {
        boolean isPowered = level.hasNeighborSignal(pos);
        boolean wasPowered = level.getBlockState(pos).getValue(POWERED);

        if(isPowered != wasPowered)
        {
            placeByDirection(level,pos);
        } else {
            deletePortal(level, pos);
        }
    }

    private Portal createPortal(Level level, BlockPos pos, ResourceKey<Level> targetDimension, Vec3 targetPosition, Vec3 orient1, Vec3 orient2, Vec3 offset)
    {
        if(level != null)
        {
            // Overworld Portal
            Portal portal = Portal.ENTITY_TYPE.create(level.getServer().getLevel(level.dimension()));
            assert portal != null;
            portal.setOriginPos(new Vec3(pos.getX()+offset.x, pos.getY()+offset.y, pos.getZ()+offset.z));
            portal.setDestination(targetPosition);
            portal.setDestinationDimension(targetDimension);
            portal.setOrientationAndSize(
                    orient1,
                    orient2,
                    4,
                    2
            );
            portal.level().addFreshEntity(portal);
            return portal;
        }
        return null;
    }



    private void deletePortal(Level level, BlockPos pos)
    {
        // Delete portal entity
        AABB portalSearchArea = new AABB(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1, pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);        for(Portal removablePortal : level.getEntitiesOfClass(Portal.class, portalSearchArea))
        {
            PortalAPI.removeGlobalPortal(level.getServer().getLevel(level.dimension()), removablePortal);
            PortalManipulation.removeConnectedPortals(removablePortal, Portal::myUnsetRemoved);
        }
    }


    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if(!level.isClientSide())
        {
            processRedstone(level,pos);
        }
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        deletePortal(level, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(POWERED);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }


}
