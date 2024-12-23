package com.reetam.borealis;

import net.minecraft.util.RandomSource;

public class TRandom {
    RandomSource source;
    double offset;
    double multiple;
    public TRandom(RandomSource randomSource, double offset, double multiple) {
        this.source = randomSource;
        this.offset = offset;
        this.multiple = multiple;
    }

    public float next() {
        return (float) (multiple * (source.nextFloat() + offset));
    }
}
