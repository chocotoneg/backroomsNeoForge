package net.mc3699.backrooms.blocks;

import net.mc3699.backrooms.BackroomsMod;
import net.mc3699.backrooms.blocks.entity.NullzoneBlockEntity;
import net.mc3699.backrooms.blocks.entity.PrototypeBlockEntity;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, BackroomsMod.MODID);

    public static final Supplier<BlockEntityType<PrototypeBlockEntity>> PROTOTYPE_BLOCK_ENTITY = BLOCK_ENTITIES.register("prototype_block_entity",
            () -> BlockEntityType.Builder.of(
                    PrototypeBlockEntity::new,
                    ModBlocks.PROTOTYPE.get()
            ).build(null));

    public static final Supplier<BlockEntityType<NullzoneBlockEntity>> NULLZONE_BLOCK_ENTITY = BLOCK_ENTITIES.register("nullzone_block_entity",
            () -> BlockEntityType.Builder.of(
                    NullzoneBlockEntity::new,
                    ModBlocks.NULLZONE.get()
            ).build(null));

    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}
