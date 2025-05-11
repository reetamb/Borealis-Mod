package com.reetam.borealis.registry;

import com.reetam.borealis.BorealisMod;
import com.reetam.borealis.block.kiln.KilnMenu;
import com.reetam.borealis.block.kiln.KilnRecipe;
import com.reetam.borealis.block.kiln.KilnScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BorealisMenus {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, BorealisMod.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, BorealisMod.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, BorealisMod.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<KilnMenu>> KILN_MENU = MENU_TYPES.register("kiln", () -> new MenuType<>(KilnMenu::new, FeatureFlags.VANILLA_SET));
    public static final DeferredHolder<RecipeType<?>, RecipeType<KilnRecipe>> KILN_RECIPE = RECIPE_TYPES.register("kiln", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(BorealisMod.MODID, "kiln")));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<KilnRecipe>> KILN_SERIALIZER = RECIPE_SERIALIZERS.register("kiln", () -> new SimpleCookingSerializer<>(KilnRecipe::new, 100));

    public static final RecipeBookType KILN = RecipeBookType.valueOf("BOREALIS_KILN");
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(BorealisMenus.KILN_MENU.get(), KilnScreen::new);
    }
}
