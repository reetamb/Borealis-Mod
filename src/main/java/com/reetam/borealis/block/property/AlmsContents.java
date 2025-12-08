package com.reetam.borealis.block.property;

import net.minecraft.util.StringRepresentable;

public enum AlmsContents implements StringRepresentable {
    EMPTY("empty"),
    NUT("nut"),
    EGG("egg")
    ;

    private final String name;

    AlmsContents(String nameIn) {
        this.name = nameIn;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
