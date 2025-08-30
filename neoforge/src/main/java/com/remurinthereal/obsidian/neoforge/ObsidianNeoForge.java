package com.remurinthereal.obsidian.neoforge;

import com.remurinthereal.obsidian.platform.neoforge.CreativeModeTabHelperImpl;
import com.remurinthereal.obsidian.platform.neoforge.RegistrationHelperImpl;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import com.remurinthereal.obsidian.Obsidian;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Obsidian.MOD_ID)
public final class ObsidianNeoForge {
    public ObsidianNeoForge(IEventBus eventBus) {
        NeoForge.EVENT_BUS.register(CreativeModeTabHelperImpl.class);
        Obsidian.init();

        RegistrationHelperImpl.registerAll(eventBus);
    }
}
