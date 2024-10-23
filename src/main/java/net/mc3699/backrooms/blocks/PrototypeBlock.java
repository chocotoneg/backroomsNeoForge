package net.mc3699.backrooms.blocks;

import net.mc3699.backrooms.blocks.entity.PrototypeBlockEntity;
import net.mc3699.backrooms.dimension.BackroomsGeneration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import qouteall.imm_ptl.core.portal.Portal;
import qouteall.imm_ptl.core.portal.PortalManipulation;

import javax.sound.sampled.Port;
import java.util.function.Predicate;

public class PrototypeBlock extends Block implements EntityBlock {
    public PrototypeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return ModBlockEntities.PROTOTYPE_BLOCK_ENTITY.get().create(blockPos,blockState);
    }

    private void createPortal(PrototypeBlockEntity prototype, Level level, BlockPos pos)
    {
        // Create new portal entity
        Portal portal = Portal.ENTITY_TYPE.create(level);
        assert portal != null;
        portal.setOriginPos(new Vec3(pos.getX()+0.5, pos.getY()+1.1, pos.getZ()+0.5));
        portal.setDestination(new Vec3(pos.getX(), -60, pos.getZ()));
        portal.setDestinationDimension(BackroomsGeneration.BACKROOMS_DIM_KEY);
        portal.setOrientationAndSize(
                new Vec3(0,0,1),
                new Vec3(1,0,0),
                1,
                1
        );
        PortalManipulation.makePortalRound(portal, 30);
        portal.level().addFreshEntity(portal);
        prototype.setHasPortal(true);
    }

    private void deletePortal(Level level, PrototypeBlockEntity prototype, BlockPos pos)
    {
        // Delete portal entity
        AABB portalSearchArea = new AABB(pos.getX() - 2, pos.getY() - 2, pos.getZ() - 2, pos.getX() + 3, pos.getY() + 2, pos.getZ() + 2);
        for(Entity entity : level.getEntities(null, portalSearchArea))
        {
            entity.remove(Entity.RemovalReason.DISCARDED);
        }
        prototype.setHasPortal(false);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(!level.isClientSide())
        {
            if(level.getBlockEntity(pos) instanceof PrototypeBlockEntity prototype)
            {
                if(prototype.hasPortal())
                {
                    deletePortal(level, prototype, pos);
                } else {
                    createPortal(prototype, level, pos);
                }
            }
        }

        return InteractionResult.FAIL;
    }
}
