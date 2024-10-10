package com.reetam.borealis.registry.world;

import com.reetam.borealis.BorealisMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BorealisRegistrySets extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
//    {
//        public HolderLookup.Provider buildPatch(RegistryAccess registries, HolderLookup.Provider lookup) {
//            BuildState state = this.createState(registries);
//            Map<ResourceKey<? extends Registry<?>>, RegistryContents<?>> map = new HashMap<>();
//            state.collectReferencedRegistries().forEach((p_272339_) -> {
//                map.put(p_272339_.key(), p_272339_);
//            });
//            this.entries.stream().map((RegistryStub<?> stub) -> stub.collectChanges(state)).forEach((contents) -> map.put(contents.key(), contents));
//            Stream<HolderLookup.RegistryLookup<?>> stream = registries.registries().map((entry) -> entry.value().asLookup());
//            HolderLookup.Provider holderlookup$provider = HolderLookup.Provider
//                    .create(Stream.concat(stream, map.values().stream().map(RegistrySetBuilder.RegistryContents::buildAsLookup).peek(state::addOwner)));
//            state.fillMissingHolders(lookup);
//            // don't validate missing holder values
//            state.throwOnError();
//            return holderlookup$provider;
//        }
//    }
            .add(Registries.CONFIGURED_FEATURE, BorealisConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, BorealisPlacedFeatures::bootstrap)
            .add(Registries.BIOME, BorealisBiomes::bootstrap)
            .add(Registries.NOISE_SETTINGS, BorealisWorld::bootstrapNoise)
            .add(Registries.DIMENSION_TYPE, BorealisWorld::bootstrapDimensionType)
            .add(Registries.LEVEL_STEM, BorealisWorld::bootstrapLevelStem);
    public BorealisRegistrySets(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of("minecraft", BorealisMod.MODID));
    }
}