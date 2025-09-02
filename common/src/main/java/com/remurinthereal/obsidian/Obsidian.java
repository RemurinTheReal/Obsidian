package com.remurinthereal.obsidian;

import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Obsidian {
    public static final String MOD_ID = "obsidian";
    public static final Logger LOGGER = LogManager.getLogger("Obsidian");

    public static void init() {

    }

    public static ResourceLocation makeResourceLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
