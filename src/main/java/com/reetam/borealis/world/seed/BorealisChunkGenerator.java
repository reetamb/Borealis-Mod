package com.reetam.borealis.world.seed;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.function.Supplier;

public class BorealisChunkGenerator extends NoiseBasedChunkGenerator {

    public static final Codec<BorealisChunkGenerator> CODEC = RecordCodecBuilder.create(
            (instance) -> instance.group(
                    RegistryLookupCodec.create(Registry.NOISE_REGISTRY)
                        .forGetter((chunkGenerator -> chunkGenerator.noises)),
                    BiomeSource.CODEC.fieldOf("biome_source")
                            .forGetter((chunkGenerator) -> chunkGenerator.biomeSource),
                    Codec.LONG.fieldOf("seed")
                            .orElseGet(SeedHolder::getSeed)
                            .forGetter((chunkGenerator) -> chunkGenerator.seed),
                    NoiseGeneratorSettings.CODEC.fieldOf("settings")
                            .forGetter((chunkGenerator) -> chunkGenerator.settings))
                    .apply(instance, instance.stable(BorealisChunkGenerator::new)));

    public BorealisChunkGenerator(Registry<NormalNoise.NoiseParameters> noise, BiomeSource source, long seed, Supplier<NoiseGeneratorSettings> settings) {
        super(noise, source, seed, settings);
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return new BorealisChunkGenerator(this.noises, this.biomeSource.withSeed(seed), seed, this.settings);
    }
}
