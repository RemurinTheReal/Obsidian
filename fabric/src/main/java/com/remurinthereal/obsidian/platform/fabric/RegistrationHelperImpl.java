package com.remurinthereal.obsidian.platform.fabric;

import com.remurinthereal.obsidian.api.RegistrationSupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public final class RegistrationHelperImpl {
    public static <T> RegistrationSupplier<? extends T> register(Registry<T> registry, ResourceLocation resourceLocation, Supplier<? extends T> supplier) {
        return new FabricRegistrationSupplier<T>(registry, Registry.register(registry, resourceLocation, supplier.get()));
    }

    public record FabricRegistrationSupplier<T>(Registry<T> registry, T item) implements RegistrationSupplier<T> {
        @Override
        public T get() {
            return item;
        }

        @Override
        public ResourceKey<T> getKey() {
            return registry.getResourceKey(item).orElse(null);
        }
    }
}
