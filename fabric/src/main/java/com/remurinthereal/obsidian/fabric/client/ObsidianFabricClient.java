package com.remurinthereal.obsidian.fabric.client;

import com.remurinthereal.obsidian.client.ObsidianClient;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.Logger;

public final class ObsidianFabricClient implements ClientModInitializer {
    public static final String MOD_ID = ObsidianClient.MOD_ID;
    public static final Logger LOGGER = ObsidianClient.LOGGER;
    @Override
    public void onInitializeClient() {
        ObsidianClient.init();
    }
}
