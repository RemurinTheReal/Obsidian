package com.remurinthereal.obsidian.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;

public final class PlatformHelper {
    @ExpectPlatform
    public static boolean isModLoaded(String modID) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static ModLoader getModLoader() {
        throw new AssertionError();
    }

    public enum ModLoader {
        Fabric,
        @Deprecated Forge,
        NeoForge,
        @Deprecated Quilt
    }
}
