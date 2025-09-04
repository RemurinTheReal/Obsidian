package com.remurinthereal.obsidian.api.datagen;

import com.remurinthereal.obsidian.api.RegistrationSupplier;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.HashSet;
import java.util.function.Supplier;

public abstract class ExtendedLanguageProvider extends LanguageProvider {
    private final HashSet<String> ADDED_CONFIG_PATHS = new HashSet<>();

    public ExtendedLanguageProvider(PackOutput output, String modID, String locale) {
        super(output, modID, locale);
    }

    public void addItem(Supplier<? extends Item> key, String name, String description) {
        add(key.get(), name, description);
    }

    public void add(Item key, String name, String description) {
        add(key.getDescriptionId(), name);
        add(key.getDescriptionId() + ".desc", description);
    }

    public void addItemStack(Supplier<ItemStack> key, String name, String description) {
        add(key.get(), name, description);
    }

    public void add(ItemStack key, String name, String description) {
        add(key.getDescriptionId(), name);
        add(key.getDescriptionId() + ".desc", description);
    }

    public void addPotion(RegistrationSupplier<Potion> key, String potionName, String splashPotionName, String lingeringPotionName, String tippedArrowName) {
        addPotion(key.getHolder(), potionName, splashPotionName, lingeringPotionName, tippedArrowName);
    }

    public void addPotion(Holder<Potion> key, String potionName, String splashPotionName, String lingeringPotionName, String tippedArrowName) {
        add(Items.POTION.getDescriptionId() + ".effect." + key.getRegisteredName(), potionName);
        add(Items.SPLASH_POTION.getDescriptionId() + ".effect." + key.getRegisteredName(), splashPotionName);
        add(Items.LINGERING_POTION.getDescriptionId() + ".effect." + key.getRegisteredName(), lingeringPotionName);
        add(Items.TIPPED_ARROW.getDescriptionId() + ".effect." + key.getRegisteredName(), tippedArrowName);
    }

    public void addBiome(ResourceKey<Biome> key, String name) {
        add(key.location().toLanguageKey("biome"), name);
    }

    public void addJukeboxSong(ResourceKey<JukeboxSong> key, String name) {
        add(key.location().toLanguageKey("jukebox_song"), name);
    }

    public void add(ModConfigSpec.ConfigValue<?> key, String... path) {
        for(int i = 0; i < path.length && i < key.getPath().size(); i++) {
            String resolvedKey = "configuration." + getName().substring(getName().lastIndexOf(": ") + 2) + "." + key.getPath().reversed().get(i);
            if (ADDED_CONFIG_PATHS.contains(resolvedKey)) {
                continue;
            }

            add(resolvedKey, path[i]);
            ADDED_CONFIG_PATHS.add(resolvedKey);
        }
    }
}
