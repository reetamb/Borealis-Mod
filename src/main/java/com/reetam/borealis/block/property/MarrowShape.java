package com.reetam.borealis.block.property;

import net.minecraft.util.StringRepresentable;

public enum MarrowShape implements StringRepresentable {
    SOURCE("source"),
    MIDDLE("middle"),
    END("end"),
    SOLO("solo")
    ;

    private final String name;

    MarrowShape(String p_156018_) {
        this.name = p_156018_;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
