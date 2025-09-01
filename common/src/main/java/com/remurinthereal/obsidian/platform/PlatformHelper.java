package com.remurinthereal.obsidian.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;

public final class PlatformHelper {
    @ExpectPlatform
    public static boolean isDev() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String modID) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Platform getPlatform() {
        throw new AssertionError();
    }

    public enum Platform {
        Fabric,
        @Deprecated Forge,
        NeoForge,
        @Deprecated Quilt
    }
}
