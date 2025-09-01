package com.remurinthereal.obsidian.platform.neoforge;

import com.remurinthereal.obsidian.api.RegistrationSupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.function.Supplier;

public final class RegistrationHelperImpl {
    private static final HashMap<String, HashMap<Registry<?>, DeferredRegister<?>>> DEFERRED_REGISTERS = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T, V extends T> RegistrationSupplier<V> register(Registry<T> registry, ResourceLocation resourceLocation, Supplier<V> supplier) {
        var hashMap = DEFERRED_REGISTERS.computeIfAbsent(resourceLocation.getNamespace(), key -> new HashMap<>());
        var deferredRegister = (DeferredRegister<T>)hashMap.computeIfAbsent(registry, key -> DeferredRegister.create(key, resourceLocation.getNamespace()));
        return new NeoForgeRegistrationSupplier<>((DeferredHolder<V, ? extends V>)deferredRegister.register(resourceLocation.getPath(), supplier));
    }

    public static void registerAll(IEventBus eventBus) {
        DEFERRED_REGISTERS.values().stream().flatMap(map -> {
            return map.values().stream();
        }).forEach(deferredRegister -> deferredRegister.register(eventBus));

        DEFERRED_REGISTERS.clear();
    }

    public record NeoForgeRegistrationSupplier<T>(DeferredHolder<T, ? extends T> registryObject) implements RegistrationSupplier<T> {
        @Override
        public T get() {
            return registryObject.get();
        }

        @Override
        public ResourceKey<? super T> getKey() {
            return registryObject.getKey();
        }
    }
}
