package com.remurinthereal.obsidian.core.fabric;

import net.fabricmc.api.ModInitializer;

import com.remurinthereal.obsidian.core.Obsidian;
import org.apache.logging.log4j.Logger;

public final class ObsidianFabric implements ModInitializer {
    public static final String MOD_ID = Obsidian.MOD_ID;
    public static final Logger LOGGER = Obsidian.LOGGER;

    @Override
    public void onInitialize() {
        Obsidian.init();
    }
}
