package com.reetam.borealis.item;

import com.reetam.borealis.registry.BorealisItems;
import com.reetam.borealis.registry.BorealisTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SilverToolBuilder {

    private final ArrayList<TagKey<Block>> canMine;
    private final String name;
    private int value = 0;

    public SilverToolBuilder(String name) {
        this.canMine = new ArrayList<>();
        this.name = name;
    }

    public String name() {
        return name;
    }
    public SilverToolBuilder sword() {
        canMine.add(BorealisTags.Blocks.MINEABLE_WITH_SWORD);
        value += 1;
        return this;
    }
    public SilverToolBuilder axe() {
        canMine.add(BlockTags.MINEABLE_WITH_AXE);
        value += 2;
        return this;
    }
    public SilverToolBuilder pickaxe() {
        canMine.add(BlockTags.MINEABLE_WITH_PICKAXE);
        value += 4;
        return this;
    }
    public SilverToolBuilder shovel() {
        canMine.add(BlockTags.MINEABLE_WITH_SHOVEL);
        value += 8;
        return this;
    }
    public SilverToolBuilder hoe() {
        canMine.add(BlockTags.MINEABLE_WITH_HOE);
        value += 16;
        return this;
    }

    public String formattedName() {
        return name().substring(0, 1).toUpperCase() + name().substring(1);
    }

    public String worksOn() {
        StringBuilder status = new StringBuilder();
        for (TagKey<Block> tag : canMine) {
            if (tag == BorealisTags.Blocks.MINEABLE_WITH_SWORD) status.append("Sword, ");
            if (tag == BlockTags.MINEABLE_WITH_AXE) status.append("Axe, ");
            if (tag == BlockTags.MINEABLE_WITH_PICKAXE) status.append("Pickaxe, ");
            if (tag == BlockTags.MINEABLE_WITH_SHOVEL) status.append("Shovel, ");
            if (tag == BlockTags.MINEABLE_WITH_HOE) status.append("Hoe, ");
        }
        if (status.isEmpty()) return "None";
        status.delete(status.length() - 2, status.length() - 1);
        return status.toString();
    }

    protected int value() {
        return value;
    }

    public float damage() {
        float damage = 0;

        for (TagKey<Block> tag : canMine) {
            if (tag == BorealisTags.Blocks.MINEABLE_WITH_SWORD) damage += 3;
            if (tag == BlockTags.MINEABLE_WITH_AXE) damage += 5;
            if (tag == BlockTags.MINEABLE_WITH_PICKAXE) damage += 1;
            if (tag == BlockTags.MINEABLE_WITH_SHOVEL) damage += 1.5F;
            if (tag == BlockTags.MINEABLE_WITH_HOE) damage += 0;
        }

        String bits = Integer.toString(this.value(), 2);
        return damage / ((bits.length() - bits.replace("1", "").length()));
    }

    public Tool tool() {
        List<Tool.Rule> rules = new ArrayList<>();
        rules.add(Tool.Rule.deniesDrops(BorealisItems.Tiers.SILVER.getIncorrectBlocksForDrops()));
        for (TagKey<Block> tag : canMine) {
            rules.add(new Tool.Rule(BuiltInRegistries.BLOCK.getOrCreateTag(tag), Optional.of(BorealisItems.Tiers.SILVER.getSpeed()), Optional.of(true)));
        }

        return new Tool(rules, 1.0F, 1);
    }
}