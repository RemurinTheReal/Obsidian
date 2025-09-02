package com.remurinthereal.obsidian.neoforge.client;

import com.remurinthereal.obsidian.Obsidian;
import com.remurinthereal.obsidian.client.ObsidianClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Obsidian.MOD_ID, dist = Dist.CLIENT)
public final class ObsidianNeoForgeClient {
    public ObsidianNeoForgeClient(ModContainer modContainer) {
        ObsidianClient.init();

        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
