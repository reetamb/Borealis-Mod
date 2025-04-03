package com.reetam.borealis.world.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class CoverTopLayerConfiguration implements FeatureConfiguration {

    public static final Codec<CoverTopLayerConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockStateProvider.CODEC.fieldOf("state").forGetter((config) -> config.state), Biome.CODEC.fieldOf("biome").forGetter((config) -> config.biome)).apply(instance, CoverTopLayerConfiguration::new));

    public final BlockStateProvider state;
    public final Holder<Biome> biome;

    public CoverTopLayerConfiguration(BlockStateProvider state, Holder<Biome> biome) {
        this.state = state;
        this.biome = biome;
    }
}
