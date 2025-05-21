package com.reetam.borealis.item;

import com.reetam.borealis.BorealisMod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SilverTools {

    public static Map<SilverToolBuilder, Integer> TOOLS = new HashMap<>();
    public static Map<Integer, SilverToolBuilder> IDS = new HashMap<>();
    public static List<SilverToolBuilder> tools;
    public static void initialize() {
        add(named("blade"));

        add(named("sword").sword()); // ✓
        add(named("saw").sword().axe()); // ✓
        add(named("poleaxe").sword().axe().pickaxe()); // ✓
        add(named("warhammer").sword().axe().pickaxe().shovel()); // ✓
        add(named("halberd").sword().axe().pickaxe().shovel().hoe()); // ✓
        add(named("pike").sword().axe().pickaxe().hoe()); // ✓
        add(named("battleaxe").sword().axe().shovel()); // ✓
        add(named("cleaver").sword().axe().shovel().hoe()); // ✓
        add(named("machete").sword().axe().hoe()); // ✓
        add(named("spear").sword().pickaxe()); // ✓
        add(named("gouge").sword().pickaxe().shovel()); // ✓
        add(named("crowbar").sword().pickaxe().shovel().hoe()); // ✓
        add(named("scythe").sword().pickaxe().hoe()); // ✓
        add(named("spade").sword().shovel()); // ✓
        add(named("pitchfork").sword().shovel().hoe()); // ✓
        add(named("rake").sword().hoe()); // ✓

        add(named("hatchet").axe()); // ✓
        add(named("chisel").axe().pickaxe()); // ✓
        add(named("paxel").axe().pickaxe().shovel()); // ✓
        add(named("mattock").axe().pickaxe().shovel().hoe()); // ✓
        add(named("adze").axe().pickaxe().hoe()); // ✓
        add(named("plow").axe().shovel()); // ✓
        add(named("axe").axe().shovel().hoe()); // ✓
        add(named("sickle").axe().hoe()); // ✓

        add(named("hammer").pickaxe()); // ✓
        add(named("drill").pickaxe().shovel()); // ✓
        add(named("claws").pickaxe().shovel().hoe()); // ✓
        add(named("pickaxe").pickaxe().hoe()); // ✓

        add(named("shovel").shovel()); // ✓
        add(named("trowel").shovel().hoe()); // ✓

        add(named("hoe").hoe()); // ✓

        tools = SilverTools.TOOLS.keySet().stream().toList();

        BorealisMod.LOGGER.debug(IDS.toString());
    }

    private static SilverToolBuilder named(String name) {
        return new SilverToolBuilder(name);
    }

    private static void add(SilverToolBuilder builder) {
        TOOLS.put(builder, builder.value());
        IDS.put(builder.value(), builder);
    }
}

