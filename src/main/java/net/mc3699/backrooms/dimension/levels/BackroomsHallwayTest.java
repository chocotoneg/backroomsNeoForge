package net.mc3699.backrooms.dimension.levels;

import net.mc3699.backrooms.dimension.util.GenUtil;
import net.mc3699.utility.BlockFill;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;

public class BackroomsHallwayTest {

    private static int FLOOR_HEIGHT = -37;


    private static void buildHallwayWall(ChunkAccess chunk, int x)
    {
        // Wall Trim
        BlockFill.fillArea(chunk, x, FLOOR_HEIGHT+1, 0, x, FLOOR_HEIGHT+1, 15, Blocks.GRAY_CONCRETE);

        // Wall
        BlockFill.fillArea(chunk, x, FLOOR_HEIGHT+2, 0, x, FLOOR_HEIGHT+3, 15, Blocks.WHITE_CONCRETE);
        BlockFill.fillArea(chunk, x, FLOOR_HEIGHT+4, 0, x, FLOOR_HEIGHT+4, 15, Blocks.ORANGE_CONCRETE);
        BlockFill.fillArea(chunk, x, FLOOR_HEIGHT+5, 0, x, FLOOR_HEIGHT+5, 15, Blocks.WHITE_CONCRETE);
    }


    public static void generateChunk(ChunkAccess chunk)
    {

        // Ceiling
        GenUtil.fillLayer(chunk, FLOOR_HEIGHT + 5, Blocks.STONE);



        // Floor
        GenUtil.fillLayer(chunk, FLOOR_HEIGHT, Blocks.SMOOTH_STONE);


        for(int i = 0; i < 15; i = i + 5)
        {
            buildHallwayWall(chunk, i);
        }

        for(int i = 0; i < 15; i = i + 2)
        {
            BlockFill.fillArea(chunk, 0, FLOOR_HEIGHT+5, i, 15, FLOOR_HEIGHT+5, i, Blocks.GLOWSTONE);
        }

        chunk.initializeLightSources();
    }

}
