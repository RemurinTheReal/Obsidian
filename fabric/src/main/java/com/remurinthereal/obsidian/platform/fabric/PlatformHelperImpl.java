package com.remurinthereal.obsidian.platform.fabric;

import com.remurinthereal.obsidian.platform.PlatformHelper;
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
