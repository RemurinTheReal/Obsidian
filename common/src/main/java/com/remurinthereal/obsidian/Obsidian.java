package com.remurinthereal.obsidian;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Obsidian {
    public static final String MOD_ID = "obsidian";
    public static final Logger LOGGER = LogManager.getLogger("Obsidian");

    public static void init() {
        LOGGER.info("Starting common init...");
    }

    /**
     * @deprecated You likely do not want to use this method, as it defines a {@link ResourceLocation} for Obsidian.
     */
    @Deprecated
    public static ResourceLocation makeResourceLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
