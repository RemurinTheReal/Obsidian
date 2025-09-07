package com.remurinthereal.obsidian.core.neoforge.client;

import com.remurinthereal.obsidian.core.Obsidian;
import com.remurinthereal.obsidian.core.client.ObsidianClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.apache.logging.log4j.Logger;

@Mod(value = Obsidian.MOD_ID, dist = Dist.CLIENT)
public final class ObsidianNeoForgeClient {
    public static final String MOD_ID = ObsidianClient.MOD_ID;
    public static final Logger LOGGER = ObsidianClient.LOGGER;

    public ObsidianNeoForgeClient(ModContainer modContainer) {
        ObsidianClient.init();

        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
