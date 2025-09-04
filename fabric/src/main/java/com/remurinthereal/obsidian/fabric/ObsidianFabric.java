package com.remurinthereal.obsidian.fabric;

import net.fabricmc.api.ModInitializer;

import com.remurinthereal.obsidian.Obsidian;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTab;
import org.apache.logging.log4j.Logger;

public final class ObsidianFabric implements ModInitializer {
    public static final String MOD_ID = Obsidian.MOD_ID;
    public static final Logger LOGGER = Obsidian.LOGGER;
    @Override
    public void onInitialize() {
        Obsidian.init();
    }
}
