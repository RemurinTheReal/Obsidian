package com.remurinthereal.obsidian.core.client;

import com.remurinthereal.obsidian.core.Obsidian;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ObsidianClient {
    public static final String MOD_ID = Obsidian.MOD_ID;
    public static final Logger LOGGER = LogManager.getLogger("Obsidian Client");

    public static void init() {
        LOGGER.info("Started common init.");
    }
}
