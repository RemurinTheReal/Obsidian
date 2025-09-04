package com.remurinthereal.obsidian.client;

import com.remurinthereal.obsidian.Obsidian;
import org.apache.logging.log4j.Logger;

public final class ObsidianClient {
    public static final String MOD_ID = Obsidian.MOD_ID;
    public static final Logger LOGGER = Obsidian.LOGGER;

    public static void init() {
        LOGGER.info("Starting clientside common init...");
    }
}
