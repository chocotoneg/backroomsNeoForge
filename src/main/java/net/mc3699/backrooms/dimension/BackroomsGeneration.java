package net.mc3699.backrooms.dimension;

import net.mc3699.backrooms.BackroomsMod;
import net.mc3699.backrooms.blocks.ModBlocks;
import net.mc3699.utility.BlockFill;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;

import java.util.List;
import java.util.Random;

@EventBusSubscriber
public class BackroomsGeneration {

    public static final ResourceKey<Level> BACKROOMS_DIM_KEY =
            ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(BackroomsMod.MODID, "backrooms"));

    public static final ResourceLocation BACKROOMS_L1_BIOME_KEY =
            ResourceLocation.fromNamespaceAndPath(BackroomsMod.MODID, "backrooms_level_one");

    public static final Random random = new Random();

    private static final PerlinNoise UnlitRoomNoise = PerlinNoise.create(RandomSource.create(), List.of(1,5,9));
    private static final PerlinNoise EmptyAreaNoise = PerlinNoise.create(RandomSource.create(), List.of(2,1,9));
    private static final PerlinNoise PlusRoomNoise = PerlinNoise.create(RandomSource.create(), List.of(3,2,1));

    private static boolean isChunkInNoise(int chunkX, int chunkZ, PerlinNoise noise, double threshold)
    {
        double scale = 0.1;
        double noiseValue = noise.getValue(chunkX * scale, chunkZ * scale, 0.0);
        return noiseValue > threshold;
    }

    @SubscribeEvent
    public static void backroomsChunkGen(ChunkEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel level) {
            if (event.isNewChunk()) {
                if (level.dimension() == BACKROOMS_DIM_KEY) {
                    genFromNoise(event.getChunk());
                }
            }
        }
    }


    public static void genFromNoise(ChunkAccess chunk)
    {
        // DO NOT REMOVE!
        generateBeams(chunk);

        if(isChunkInNoise(chunk.getPos().x, chunk.getPos().z, PlusRoomNoise, 0.02))
        {
            generateCrossShape(chunk);
            generateLights(chunk);
            return;
        }


        if(!isChunkInNoise(chunk.getPos().x,chunk.getPos().z, UnlitRoomNoise, 0.2)) {
            generateLights(chunk);
        }

        if(!isChunkInNoise(chunk.getPos().x,chunk.getPos().z, EmptyAreaNoise, 0.25)) {
            generateWalls(chunk);
        }




    }

    public static void generateLights(ChunkAccess chunk)
    {
        ChunkPos chunkPos = chunk.getPos();
        for(int x = 0; x < 15; x = x + 4)
        {
            for(int z = 0; z < 15; z = z + 4)
            {
                chunk.setBlockState(chunkPos.getWorldPosition().offset(x,L1_CEILING_LEVEL,z), ModBlocks.TILE_LIGHT.get().defaultBlockState(), true);
                if(chunk.getBlockState(chunkPos.getWorldPosition().offset(x,L1_CEILING_LEVEL-1,z)).is(Blocks.AIR))
                {
                    chunk.setBlockState(chunkPos.getWorldPosition().offset(x,L1_CEILING_LEVEL-1,z), Blocks.LIGHT.defaultBlockState().setValue(BlockStateProperties.LEVEL, 15), false);
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
                chunk.setBlockState(chunk.getPos().getWorldPosition().offset(x,-55, z),
                    Blocks.STRIPPED_OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Z), true);
            }
        }
    }



    private final static int L1_FLOOR_LEVEL = -62;
    private final static int L1_CEILING_LEVEL = -57;


    public static void generateWalls(ChunkAccess chunk)
    {
        int startX = random.nextInt(0, 15);
        int startZ = random.nextInt(0, 15);
        int length = random.nextInt(5, 15);
        int wallThickness = random.nextInt(0,4);

        if(random.nextBoolean())
        {
            BlockFill.fillArea(chunk, startX, L1_FLOOR_LEVEL, startZ, startX+length, L1_CEILING_LEVEL, startZ+wallThickness, ModBlocks.YELLOW_WALLPAPER.get());
        } else {
            BlockFill.fillArea(chunk, startX, L1_FLOOR_LEVEL, startZ, startX+wallThickness, L1_CEILING_LEVEL, startZ+length, ModBlocks.YELLOW_WALLPAPER.get());
        }

    }

    public static void fillWall(ChunkAccess chunk, int startX, int startZ, int endX, int endZ, int heightOffset, BlockState block)
    {

        for(int height = L1_FLOOR_LEVEL; height < (L1_CEILING_LEVEL -heightOffset)+1; height++)
        {
            for(int ix = startX; ix < endX; ix++)
            {
                for(int iz = startZ; iz < endZ; iz++)
                {
                    chunk.setBlockState(chunk.getPos().getWorldPosition().offset(ix,height,iz), block, true);
                }
            }
        }
    }


    public static void generateCrossShape(ChunkAccess chunk)
    {
        fillWall(chunk, 6, 3, 9, 12, 0, ModBlocks.YELLOW_WALLPAPER.get().defaultBlockState());
        fillWall(chunk, 3, 6, 12,9, 0, ModBlocks.YELLOW_WALLPAPER.get().defaultBlockState());
    }
}

