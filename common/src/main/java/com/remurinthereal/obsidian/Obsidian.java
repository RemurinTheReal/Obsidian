package com.remurinthereal.obsidian;


import net.minecraft.resources.ResourceLocation;

public final class Obsidian {
    public static final String MOD_ID = "obsidian";

    public static void init() {

    }

    public static ResourceLocation makeResourceLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
