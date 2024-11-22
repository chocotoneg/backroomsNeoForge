package net.mc3699.backrooms.dimension;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;

import java.util.List;

public class GenNoise {
    public static final PerlinNoise UnlitRoomNoise = PerlinNoise.create(RandomSource.create(), List.of(1,5,9));
    public static final PerlinNoise EmptyAreaNoise = PerlinNoise.create(RandomSource.create(), List.of(2,1,9));
    public static final PerlinNoise PlusRoomNoise = PerlinNoise.create(RandomSource.create(), List.of(3,2,1));
}
