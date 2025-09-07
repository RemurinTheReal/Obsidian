package com.remurinthereal.obsidian.api.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

/**
 * Helper class for abstracting platform-specific registration.
 *
 * @author Remurin
 */
public final class RegistrationHelper {
    private RegistrationHelper() {
        throw new AssertionError("RegistrationHelper should not be instantiated");
    }

    /**
     * Queues an object for registry, and returns a {@link RegistrationSupplier} for accessing its info and it, once registered.
     *
     * @param registry the target registry
     * @param resourceLocation the object's id
     * @param supplier a supplier for object creation
     * @return a {@link RegistrationSupplier} for the registered object
     */
    @ExpectPlatform
    public static <T, V extends T> RegistrationSupplier<V> register(Registry<T> registry, ResourceLocation resourceLocation, Supplier<V> supplier) {
        throw new AssertionError();
    }
}
