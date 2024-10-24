package net.mc3699.backrooms.blocks.entity;

import net.mc3699.backrooms.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class NullzoneBlockEntity extends BlockEntity {
    private BlockState fakeState;


    public BlockState getFakeState() {
        return fakeState;
    }

    public void setFakeState(BlockState state) {
        fakeState = state;
        setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if(fakeState != null)
        {
            tag.putInt("fakeState", Block.getId(fakeState));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if(tag.contains("fakeState"))
        {
            fakeState = Block.stateById(tag.getInt("fakeState"));
        }
    }

    public NullzoneBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.NULLZONE_BLOCK_ENTITY.get(), pos, blockState);
    }


}
