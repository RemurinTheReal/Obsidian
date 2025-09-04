package com.remurinthereal.obsidian.api.platform.fabric;

import com.remurinthereal.obsidian.api.RegistrationSupplier;
import com.remurinthereal.obsidian.api.platform.RegistrationHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

/**
 * {@link RegistrationHelper}
 *
 * @author Remurin
 */
final class RegistrationHelperImpl {

    private RegistrationHelperImpl() {}

    public static <T, V extends T> RegistrationSupplier<V> register(Registry<T> registry, ResourceLocation resourceLocation, Supplier<V> supplier) {
        return new RegistrationSupplierImpl<>(registry, Registry.register(registry, resourceLocation, supplier.get()));
    }

    private record RegistrationSupplierImpl<T, V extends T>(Registry<T> registry, V item) implements RegistrationSupplier<V> {
        @Override
        public V get() {
            return item;
        }

        @Override
        @SuppressWarnings({"unchecked", "OptionalGetWithoutIsPresent"})
        public Holder<V> getHolder() {
            return (Holder<V>) registry.getHolder(getID()).get();
        }

        @Override
        public ResourceLocation getID() {
            return registry.getKey(item);
        }

        @Override
        public ResourceKey<T> getKey() {
            return registry.getResourceKey(item).orElse(null);
        }


        @Override
        public boolean equals(Object other) {
            return other instanceof RegistrationSupplier<?> supplier && supplier.getID() == getID();
        }
    }
}
