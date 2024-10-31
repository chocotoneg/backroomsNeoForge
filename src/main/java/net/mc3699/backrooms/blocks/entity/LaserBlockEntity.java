package net.mc3699.backrooms.blocks.entity;

import net.mc3699.backrooms.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class LaserBlockEntity extends BlockEntity {

    boolean laserEnabled = false;

    public LaserBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.LASER_BLOCK_ENTITY.get(), pos, blockState);
    }


}
