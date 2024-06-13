package com.reetam.borealis.block.property;

import net.minecraft.util.StringRepresentable;

public enum PermafrostCover implements StringRepresentable {
    CLEAR("clear"),
    SNOWY("snowy"),
    ICY("icy"),
    SUGARY("sugary");

    private final String name;

    PermafrostCover(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
