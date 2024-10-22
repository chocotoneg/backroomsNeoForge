package net.mc3699.backrooms.dimension;

import net.mc3699.backrooms.BackroomsMod;
import net.mc3699.backrooms.blocks.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ChunkGenerationTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.profiling.jfr.event.ChunkGenerationEvent;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.ChunkEvent;

import java.util.Random;
import java.util.concurrent.TimeUnit;


@EventBusSubscriber
public class BackroomsGeneration {

    public static final ResourceKey<Level> BACKROOMS_DIM_KEY =
            ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(BackroomsMod.MODID, "backrooms"));

    public static final Random random = new Random();

    @SubscribeEvent
    public static void backroomsChunkGen(ChunkEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel level) {
            if (event.isNewChunk()) {
                if (level.dimension() == BACKROOMS_DIM_KEY) {
                    ChunkPos chunkPos = event.getChunk().getPos();
                    generateBeams(event.getChunk());
                    generateLights(event.getChunk());
                    generateWalls(event.getChunk());
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
                if(chunk.getBlockState(chunkPos.getWorldPosition().offset(x,-58,z)).is(Blocks.AIR))
                {
                    chunk.setBlockState(chunkPos.getWorldPosition().offset(x,-58,z), Blocks.LIGHT.defaultBlockState().setValue(BlockStateProperties.LEVEL, 15), false);
                }
            }
        }
    }

    public static void generateBeams(ChunkAccess chunk)
    {
        for(int x = 0; x < 16; x = x + 2)
        {
            for(int z = 0; z < 16; z++)
            {
                chunk.setBlockState(chunk.getPos().getWorldPosition().offset(x,-55, z), Blocks.STRIPPED_OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Z), true);
            }
        }
    }

    private final static int FLOOR_LEVEL = -62;
    private final static int CEILING_LEVEL = -58;



    public static void generateWalls(ChunkAccess chunk)
    {
        int startX = random.nextInt(0, 15);
        int startZ = random.nextInt(0, 15);
        int length = random.nextInt(4, 15);

        if(random.nextBoolean())
        {
            fillWall(chunk, startX, startZ, startX+length, startZ+1, ModBlocks.YELLOW_WALLPAPER.get().defaultBlockState());
        } else {
            fillWall(chunk, startX, startZ, startX+1, startZ+length, ModBlocks.YELLOW_WALLPAPER.get().defaultBlockState());
        }

    }

    public static void fillWall(ChunkAccess chunk, int startX, int startZ, int endX, int endZ, BlockState block)
    {

        for(int height = FLOOR_LEVEL; height < CEILING_LEVEL+1; height++)
        {
            for(int ix = startX; ix < endX; ix++)
            {
                for(int iz = startZ; iz < endZ; iz++)
                {
                    chunk.setBlockState(chunk.getPos().getWorldPosition().offset(ix,height,iz), block, true);
                    //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Generating wall block"));
                }
            }

        }


    }
}
