package com.remurinthereal.obsidian.fabric;

import net.fabricmc.api.ModInitializer;

import com.remurinthereal.obsidian.Obsidian;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

public final class ObsidianFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Obsidian.init();
    }
}
