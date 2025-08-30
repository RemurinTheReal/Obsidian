package com.remurinthereal.obsidian.platform.fabric;

import com.remurinthereal.obsidian.platform.PlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

public final class PlatformHelperImpl {
    public static boolean isModLoaded(String modID) {
        return FabricLoader.getInstance().isModLoaded(modID);
    }

    public static PlatformHelper.ModLoader getModLoader() {
        return PlatformHelper.ModLoader.Fabric;
    }
}
