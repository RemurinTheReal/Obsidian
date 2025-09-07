package com.remurinthereal.obsidian.api.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

/**
 * Represents a platform-agnostic system for {@link CreativeModeTab} modification.
 *
 * @author Remurin
 */
public interface CreativeModeTabModifier {

    /**
     * @return the current {@link CreativeModeTab}
     */
    CreativeModeTab getTab();

    /**
     * @return the current tab's {@link ResourceKey}
     */
    ResourceKey<CreativeModeTab> getTabKey();

    /**
     * @return the {@link CreativeModeTab.ItemDisplayParameters} for the current tab
     */
    CreativeModeTab.ItemDisplayParameters getDisplayParameters();

    /**
     * Appends a collection of entries to the end of the current tab.
     *
     * @param entries the entries to append
     */
    void append(Object... entries);

    /**
     * Prepends a collection of entries to the beginning of the current tab.
     *
     * @param entries the entries to prepend
     */
    void prepend(Object... entries);

    /**
     * Inserts entries before or after a specific target entry in the current tab.
     * If the target entry is not present, entries are appended to the end of the tab.
     *
     * @param targetEntry the entry to insert relative to
     * @param insertType  the point (before or after) to insert the entries
     * @param entries     the entries to insert
     */
    void insert(Object targetEntry, InsertType insertType, Object... entries);

    /**
     * Inserts entries after a specific target entry in the current tab.
     *
     * @param targetEntry the entry to insert after
     * @param entries     the entries to insert
     */
    default void insert(Object targetEntry, Object... entries) {
        insert(targetEntry, InsertType.AFTER, entries);
    }

    /**
     * Removes the target entries from the current tab, if present.
     *
     * @param targetEntries the entries to remove
     */
    void remove(Object... targetEntries);

    /**
     * Defines where entries should be inserted relative to another.
     */
    enum InsertType {
        AFTER,
        BEFORE
    }
}
