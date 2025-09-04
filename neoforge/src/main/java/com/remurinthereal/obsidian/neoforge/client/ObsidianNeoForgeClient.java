package com.remurinthereal.obsidian.neoforge.client;

import com.remurinthereal.obsidian.Obsidian;
import com.remurinthereal.obsidian.client.ObsidianClient;
import net.minecraft.commands.SharedSuggestionProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforgespi.Environment;
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
