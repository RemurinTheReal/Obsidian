package com.remurinthereal.obsidian.core.neoforge;

import com.remurinthereal.obsidian.core.ObsidianConfig;
import com.remurinthereal.obsidian.api.item.neoforge.CreativeModeTabHelperImpl;
import com.remurinthereal.obsidian.api.registry.neoforge.RegistrationHelperImpl;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import com.remurinthereal.obsidian.core.Obsidian;
import net.neoforged.fml.config.ModConfig;
import org.apache.logging.log4j.Logger;

@Mod(Obsidian.MOD_ID)
public final class ObsidianNeoForge {
    public static final String MOD_ID = Obsidian.MOD_ID;
    public static final Logger LOGGER = Obsidian.LOGGER;

    public ObsidianNeoForge(ModContainer modContainer, IEventBus eventBus) {
        eventBus.register(CreativeModeTabHelperImpl.class);
        eventBus.register(RegistrationHelperImpl.class);

        Obsidian.init();

        modContainer.registerConfig(ModConfig.Type.CLIENT, ObsidianConfig.CLIENT_CONFIG);
    }
}
