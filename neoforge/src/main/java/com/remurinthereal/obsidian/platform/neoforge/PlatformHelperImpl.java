package com.remurinthereal.obsidian.platform.neoforge;

import com.remurinthereal.obsidian.platform.PlatformHelper;
import net.neoforged.fml.ModList;

public final class PlatformHelperImpl {
    public static boolean isModLoaded(String modID) {
        return ModList.get().isLoaded(modID);
    }

    public static PlatformHelper.ModLoader getModLoader() {
        return PlatformHelper.ModLoader.NeoForge;
    }
}
