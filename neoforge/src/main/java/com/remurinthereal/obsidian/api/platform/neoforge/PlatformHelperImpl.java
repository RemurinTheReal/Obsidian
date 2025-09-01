package com.remurinthereal.obsidian.api.platform.neoforge;

import com.remurinthereal.obsidian.api.platform.PlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public final class PlatformHelperImpl {
    public static boolean isDev() {
        return !FMLLoader.isProduction();
    }

    public static boolean isModLoaded(String modID) {
        return ModList.get().isLoaded(modID);
    }

    public static PlatformHelper.Platform getPlatform() {
        return PlatformHelper.Platform.NeoForge;
    }
}
