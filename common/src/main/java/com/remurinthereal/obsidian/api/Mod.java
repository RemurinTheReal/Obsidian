package com.remurinthereal.obsidian.api;

import java.nio.file.Path;
import java.util.List;

/**
 * Represents a mod in a platform-agnostic way, abstracting platform-specific mod containers.
 *
 * @author Remurin
 */
public interface Mod {

    /**
     * @return the id for this mod
     */
    String getID();

    /**
     * @return the mod's version as a {@link String}
     */
    String getVersion();

    /**
     * @return the mod's display name
     */
    String getName();

    /**
     * @return the mod's description
     */
    String getDescription();

    /**
     * @return a {@link List} of the mod ids this mod depends on
     */
    List<String> getDependencies();

    /**
     * @return the {@link Path} of this mod JAR on disk
     */
    Path getFilePath();
}
