package net.mc3699.backrooms.dimension.levels;

import net.mc3699.backrooms.blocks.ModBlocks;
import net.mc3699.backrooms.dimension.util.GenUtil;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;

public class BackroomsL2 {

    public static final int L2_FLOOR_HEIGHT = -53;
    public static final int L2_CEILING_HEIGHT = -48;


    public static void generateChunk(ChunkAccess chunk)
    {
        GenUtil.fillLayer(chunk, L2_FLOOR_HEIGHT, Blocks.SMOOTH_STONE);
        GenUtil.fillLayer(chunk, L2_CEILING_HEIGHT, Blocks.DARK_OAK_PLANKS);
        GenUtil.generateBasicWalls(chunk, L2_FLOOR_HEIGHT, L2_CEILING_HEIGHT, Blocks.WHITE_CONCRETE);
        GenUtil.generateLights(chunk, L2_CEILING_HEIGHT);
    }

}
