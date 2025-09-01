package com.remurinthereal.obsidian.api.platform.fabric;

import com.remurinthereal.obsidian.api.RegistrationSupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public final class RegistrationHelperImpl {
    public static <T, V extends T> RegistrationSupplier<V> register(Registry<T> registry, ResourceLocation resourceLocation, Supplier<V> supplier) {
        return new FabricRegistrationSupplier<>(registry, Registry.register(registry, resourceLocation, supplier.get()));
    }

    public record FabricRegistrationSupplier<T, V extends T>(Registry<T> registry, V item) implements RegistrationSupplier<V> {
        @Override
        public V get() {
            return item;
        }

        @Override
        public ResourceKey<T> getKey() {
            return registry.getResourceKey(item).orElse(null);
        }
    }
}
