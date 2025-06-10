package com.reetam.borealis.block.fluid;

import com.reetam.borealis.registry.BorealisBlocks;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import org.joml.Vector3f;

import java.util.HashMap;

public class TankRecipe {

    public static final HashMap<Block, TankType> RECIPES = new HashMap<>();

    public enum TankType implements StringRepresentable {

        ALM_OIL("alm_oil", BorealisBlocks.ALMS.get(), BorealisBlocks.CRACKED_ALMS.get()),
        EMPTY("empty"),
        HOT_SPRING_WATER("hot_spring_water", BorealisBlocks.PUMICE_GEYSER.get()),
        LATEX("latex", BorealisBlocks.BRUMAL_LOG.get(), BorealisBlocks.BRUMAL_WOOD.get()),
        MARROW("marrow", BorealisBlocks.PETRIFIED_WOOD.get(), BorealisBlocks.BONE_DRY_WOOD.get()),
        PETROL("petrol"),
        QUICKSILVER("quicksilver"),
        SYRUP("syrup", BorealisBlocks.SWEETWOOD.get(), BorealisBlocks.SWEETWOOD_LOG.get(), BorealisBlocks.STRIPPED_SWEETWOOD.get(), BorealisBlocks.STRIPPED_SWEETWOOD_LOG.get())
        ;

        private final String name;
        private final Vector3f color;

        TankType(String name) {
            this.name = name;
            this.color = new Vector3f(0, 0, 0);
        }

        TankType(String name, Vector3f color) {
            this.name = name;
            this.color = color;
        }

        TankType(String name, Block... inputs) {
            this.name = name;
            for (Block block : inputs) {
                TankRecipe.RECIPES.put(block, this);
            }
            this.color = new Vector3f(0, 0, 0);
        }

        TankType(String name, Vector3f color, Block...inputs) {
            this.name = name;
            for (Block block : inputs) {
                TankRecipe.RECIPES.put(block, this);
            }
            this.color = color;
        }

        public String toString() {
            return this.name;
        }

        public String getSerializedName() {
            return this.name;
        }

        public Vector3f color() {
            return this.color;
        }
    }
}
