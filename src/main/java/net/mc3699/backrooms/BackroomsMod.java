package net.mc3699.backrooms;

import net.mc3699.backrooms.blocks.ModBlockEntities;
import net.mc3699.backrooms.blocks.ModBlocks;
import net.mc3699.backrooms.blocks.entity.NullzoneBlockEntity;
import net.mc3699.backrooms.dimension.BackroomsGeneration;
import net.mc3699.backrooms.entity.ModEntities;
import net.mc3699.backrooms.entity.client.HowlerRenderer;
import net.mc3699.backrooms.entity.client.blockRenderer.NullzoneBlockEntityRenderer;
import net.mc3699.backrooms.items.ModItems;
import net.mc3699.backrooms.sound.ModSounds;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.dimension.DimensionType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.awt.*;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BackroomsMod.MODID)
public class BackroomsMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "mcbr";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public BackroomsMod(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModSounds.register(modEventBus);
        NeoForge.EVENT_BUS.addListener(BackroomsGeneration::backroomsChunkGen);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    @EventBusSubscriber(modid = BackroomsMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.HOWLER_ENTITY, HowlerRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.NULLZONE_BLOCK_ENTITY.get(), NullzoneBlockEntityRenderer::new);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

}
