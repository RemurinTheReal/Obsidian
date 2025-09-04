package com.remurinthereal.obsidian.api.platform;

import com.remurinthereal.obsidian.api.Mod;
import com.remurinthereal.obsidian.core.Environment;
import com.remurinthereal.obsidian.core.Platform;
import dev.architectury.injectables.annotations.ExpectPlatform;

/**
 * Helper class for accessing platform-specific and environment-specific information in a platform-agnostic way.
 *
 * @author Remurin
 */
public final class PlatformHelper {
    private PlatformHelper() {}

    /**
     * @return {@code true} if the game is running in a development environment, otherwise {@code false}
     */
    @ExpectPlatform
    public static boolean isDevEnvironment() {
        throw new AssertionError();
    }

    /**
     * @return the mod with the given id if present, otherwise {@code null}
     */

    @ExpectPlatform
    public static Mod getMod(String modID) {
        throw new AssertionError();
    }

    /**
     * Checks if a mod with the given mod id is loaded.
     *
     * @param modID the mod id to check for
     * @return {@code true} if the mod is loaded, otherwise {@code false}
     */
    @ExpectPlatform
    public static boolean isModLoaded(String modID) {
        throw new AssertionError();
    }

    /**
     * @return the current platform
     */
    @ExpectPlatform
    public static Platform getPlatform() {
        throw new AssertionError();
    }

    /**
     * @return the current environment
     */
    @ExpectPlatform
    public static Environment getEnvironment() {
        throw new AssertionError();
    }
}
