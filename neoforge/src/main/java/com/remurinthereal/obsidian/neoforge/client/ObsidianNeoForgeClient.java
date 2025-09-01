package com.remurinthereal.obsidian.neoforge.client;

import com.remurinthereal.obsidian.Obsidian;
import com.remurinthereal.obsidian.client.ObsidianClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = Obsidian.MOD_ID, dist = Dist.CLIENT)
public final class ObsidianNeoForgeClient {
    public ObsidianNeoForgeClient() {
        ObsidianClient.init();
    }
}
