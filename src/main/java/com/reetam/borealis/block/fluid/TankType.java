package com.reetam.borealis.block.fluid;

import net.minecraft.util.StringRepresentable;

public enum TankType implements StringRepresentable {
    ALM_OIL("alm_oil"),
    EMPTY("empty"),
    HOT_SPRING_WATER("hot_spring_water"),
    LATEX("latex"),
    MARROW("marrow"),
    PETROL("petrol"),
    QUICKSILVER("quicksilver"),
    SYRUP("syrup")
    ;

    private final String name;

    TankType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
