package net.mc3699.backrooms.dimension;

import net.mc3699.backrooms.BackroomsMod;
import net.mc3699.backrooms.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ChunkGenerationTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.profiling.jfr.event.ChunkGenerationEvent;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;



@EventBusSubscriber
public class BackroomsGeneration {

    public static final ResourceKey<Level> BACKROOMS_DIM_KEY =
            ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(BackroomsMod.MODID, "backrooms"));

    @SubscribeEvent
    public static void backroomsChunkGen(ChunkEvent.Load event)
    {
        if(event.getLevel() instanceof ServerLevel level)
        {
            if(level.dimension() == BACKROOMS_DIM_KEY)
            {
                level.getServer().submit(() ->
                {
                   ChunkPos chunkPos = event.getChunk().getPos();
                   level.setBlock(chunkPos.getMiddleBlockPosition(-57), ModBlocks.TILE_LIGHT.get().defaultBlockState(), 2);
                });
            }
        }
    }

    private static void generateLights(ServerLevel level, ChunkPos chunkPos)
    {
        for(int lightOffset = -4; lightOffset < 4; lightOffset = lightOffset + 2)
        {
            level.setBlock(chunkPos.getMiddleBlockPosition(-57).offset(lightOffset,0,0), ModBlocks.TILE_LIGHT.get().defaultBlockState(), 2);
            level.setBlock(chunkPos.getMiddleBlockPosition(-57).offset(lightOffset,0,0), ModBlocks.TILE_LIGHT.get().defaultBlockState(), 2);
        }
    }
}
