package net.mc3699.backrooms.entity;

import net.mc3699.backrooms.BackroomsMod;
import net.mc3699.backrooms.entity.behavior.HowlerEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;

public class ModEntities {

    // Howler Entity
    public static final ResourceKey<EntityType<?>> HOWLER_ENTITY_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(BackroomsMod.MODID, "howler"));

    public static EntityType<HowlerEntity> HOWLER_ENTITY;


    public static void registerEntities(RegisterEvent event)
    {
        event.register(Registries.ENTITY_TYPE, registry -> {
            HOWLER_ENTITY = EntityType.Builder.<HowlerEntity>of(HowlerEntity::new, MobCategory.MONSTER).sized(0.5f, 2.5f).build("howler");
            registry.register(HOWLER_ENTITY_KEY, HOWLER_ENTITY);
        });
    }


    public static void register(IEventBus eventBus)
    {
        eventBus.addListener(ModEntities::registerEntities);
    }

}
