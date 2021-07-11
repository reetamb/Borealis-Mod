package com.reetam.borealis.world;

public class SeedHolder {

    private static long seed = 0;

    public static void putInSeed(long seedInput) {
        seed = seedInput;
    }

    public static long getSeed() {
        return seed;
    }
}
