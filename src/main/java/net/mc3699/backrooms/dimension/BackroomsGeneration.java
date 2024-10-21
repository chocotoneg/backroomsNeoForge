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
import net.minecraft.world.level.chunk.ChunkAccess;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.ChunkEvent;

import java.util.concurrent.TimeUnit;


@EventBusSubscriber
public class BackroomsGeneration {

    public static final ResourceKey<Level> BACKROOMS_DIM_KEY =
            ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(BackroomsMod.MODID, "backrooms"));

    @SubscribeEvent
    public static void backroomsChunkGen(ChunkEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel level) {
            if (event.isNewChunk()) {
                if (level.dimension() == BACKROOMS_DIM_KEY) {
                    ChunkPos chunkPos = event.getChunk().getPos();
                    generateLights(event.getChunk());
                }
            }
        }
    }

    public static void generateLights(ChunkAccess chunk)
    {
        ChunkPos chunkPos = chunk.getPos();
        for(int x = 0; x < 15; x = x + 4)
        {
            for(int z = 0; z < 15; z = z + 4)
            {
                chunk.setBlockState(chunkPos.getWorldPosition().offset(x,-57,z), ModBlocks.TILE_LIGHT.get().defaultBlockState(), true);
            }
        }
    }
}
