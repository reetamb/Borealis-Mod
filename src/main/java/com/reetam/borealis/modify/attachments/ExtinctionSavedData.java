package com.reetam.borealis.modify.attachments;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;

public class ExtinctionSavedData extends SavedData {
    //We create a field that'll hold the data when we get a Java instance of it.
    private final HashMap<EntityType<?>, Integer> deaths = new HashMap<>();

    //This is used as an instruction of how to serialize our Map, we use UUIDUtil's Codec, and the primitive Codec for String.
    //This allows us to (de)serialize complex objects like maps or objects not supported by default.
    private static final Codec<Map<EntityType<?>, Integer>> MAP_CODEC = Codec.unboundedMap(BuiltInRegistries.ENTITY_TYPE.byNameCodec(), Codec.INT);
    //We use the above Codec to make a Codec that'll serialize our whole storage instance.
    private static final Codec<ExtinctionSavedData> CODEC = RecordCodecBuilder.create(inst ->
            inst.group(
                    MAP_CODEC.fieldOf("deaths").forGetter(ExtinctionSavedData::getDeaths)
            ).apply(inst, ExtinctionSavedData::new)
    );


    //Constructors for our storage instance. Followed by various utilities for setting and getting the data.
    public ExtinctionSavedData() {}
    public ExtinctionSavedData(Map<EntityType<?>, Integer> map) {
        this.deaths.putAll(map);
    }
    public Map<EntityType<?>, Integer> getDeaths() {
        return deaths;
    }

    public void setDataEntry(EntityType<?> type, Integer amount) {
        this.deaths.put(type, amount);
        //Whenever we change the data in the storage object, we must use setDirty to tell Neo to save it persistently.
        this.setDirty();
    }

    public void clearDataEntry(EntityType<?> type) {
        this.deaths.remove(type);
        this.setDirty();
    }

    public int getDeathsFromEntityType(EntityType<?> type) {
        return this.deaths.get(type) == null ? 0 : this.deaths.get(type);
    }

    //Method used to load data from the serialized NBT code.
    public static ExtinctionSavedData load(CompoundTag tag, HolderLookup.Provider provider) {
        return CODEC.parse(NbtOps.INSTANCE, tag)
                .resultOrPartial()
                .orElse(new ExtinctionSavedData());
    }

    /*
     This method will be used by NeoForge to save our data when it's set to "dirty" from our operations.
     We won't call it directly ourselves, but by setting our data as "dirty",
     it makes it so that when the dimensional storage saves, it'll know to save our data's changes as well.
     Overriding makes it so that when the superclass equivalent is called on this class's instance, it calls the override instead.
    */
    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        CODEC.encodeStart(NbtOps.INSTANCE, this)
                .resultOrPartial()
                .ifPresent(r -> {
                    if(r instanceof CompoundTag c) {
                        tag.merge(c);
                    }
                });
        return tag;
    }

    //say we want to use a ServerPlayer to get an instance of our storage, we could use something like this to get an instance.
    //This wouldn't have to be here, nor be a standard method, but this is an example of a helper method to get an instance.
    public static ExtinctionSavedData fromServerPlayer(ServerPlayer player) {
        return player.getServer().overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<ExtinctionSavedData>(ExtinctionSavedData::new, ExtinctionSavedData::load), "deaths"
        );
    }
}
