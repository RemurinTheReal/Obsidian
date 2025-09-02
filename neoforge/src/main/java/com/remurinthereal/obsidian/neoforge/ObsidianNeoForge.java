package com.remurinthereal.obsidian.neoforge;

import com.remurinthereal.obsidian.api.platform.neoforge.CreativeModeTabHelperImpl;
import com.remurinthereal.obsidian.api.platform.neoforge.RegistrationHelperImpl;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import com.remurinthereal.obsidian.Obsidian;
import net.neoforged.fml.config.ModConfig;

@Mod(Obsidian.MOD_ID)
public final class ObsidianNeoForge {
    public ObsidianNeoForge(ModContainer modContainer, IEventBus eventBus) {
        eventBus.register(CreativeModeTabHelperImpl.class);
        Obsidian.init();

        modContainer.registerConfig(ModConfig.Type.CLIENT, ObsidianConfig.CLIENT_CONFIG);

        RegistrationHelperImpl.registerAll(eventBus);
    }
}
