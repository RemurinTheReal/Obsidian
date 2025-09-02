package com.remurinthereal.obsidian.api.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.util.List;

public final class PlatformHelper {
    @ExpectPlatform
    public static List<String> getDependencies() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static List<String> getDependents() {
        throw new AssertionError();
    }

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
