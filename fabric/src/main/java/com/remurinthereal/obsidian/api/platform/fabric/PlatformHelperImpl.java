package com.remurinthereal.obsidian.api.platform.fabric;

import com.remurinthereal.obsidian.api.platform.PlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

public final class PlatformHelperImpl {
    public static boolean isDev() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
    public static boolean isModLoaded(String modID) {
        return FabricLoader.getInstance().isModLoaded(modID);
    }

    public static PlatformHelper.Platform getPlatform() {
        return PlatformHelper.Platform.Fabric;
    }
}
