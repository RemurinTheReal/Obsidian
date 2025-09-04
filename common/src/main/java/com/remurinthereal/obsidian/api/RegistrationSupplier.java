package com.remurinthereal.obsidian.api;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

/**
 * Variation of {@link Supplier} that abstracts registry access for use in cross-platform mods.
 *
 * @author Remurin
 */
public interface RegistrationSupplier<T> extends Supplier<T> {
    /**
     * Gets the supplied object as it's stored within its {@link Registry}.
     *
     * @return the supplied object, or null if it has not been registered
     */
    @Override T get();

    /**
     * @return the {@link Holder} equating to this supplier
     */
    Holder<T> getHolder();

    /**
     * Gets the {@link ResourceLocation} for the supplied object within its {@link Registry}.
     *
     * @return the {@link ResourceLocation} for the supplied object
     */
    ResourceLocation getID();

    /**
     * @return the {@link ResourceKey} for the supplied object
     */
    ResourceKey<? super T> getKey();
}
