package com.remurinthereal.obsidian.api.registry.neoforge;

import com.remurinthereal.obsidian.api.registry.RegistrationSupplier;
import com.remurinthereal.obsidian.api.registry.RegistrationHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * {@link RegistrationHelper}
 *
 * @author Remurin
 */
@ApiStatus.Internal
public final class RegistrationHelperImpl {
    private RegistrationHelperImpl() {
        throw new AssertionError("RegistrationHelperImpl should not be instantiated");
    }

    private static final HashMap<String, HashMap<Registry<?>, DeferredRegister<?>>> DEFERRED_REGISTERS = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T, V extends T> RegistrationSupplier<V> register(Registry<T> registry, ResourceLocation resourceLocation, Supplier<V> supplier) {
        var hashMap = DEFERRED_REGISTERS.computeIfAbsent(resourceLocation.getNamespace(), key -> new HashMap<>());
        var deferredRegister = (DeferredRegister<T>)hashMap.computeIfAbsent(registry, key -> DeferredRegister.create(key, resourceLocation.getNamespace()));

        return new RegistrationSupplierImpl<>((DeferredHolder<V, ? extends V>)deferredRegister.register(resourceLocation.getPath(), supplier));
    }

    @SubscribeEvent
    private static void registerAll(FMLConstructModEvent event) {
        var eventBus = ModLoadingContext.get().getActiveContainer().getEventBus();
        if (eventBus == null) return;

        DEFERRED_REGISTERS.values().stream().flatMap(map -> {
            return map.values().stream();
        }).forEach(deferredRegister -> deferredRegister.register(eventBus));

        DEFERRED_REGISTERS.clear();
    }

    private record RegistrationSupplierImpl<T>(DeferredHolder<T, ? extends T> registryObject) implements RegistrationSupplier<T> {
        @Override
        public T get() {
            return registryObject.get();
        }

        @Override
        public Holder<T> getHolder() {
            return registryObject;
        }

        @Override
        public ResourceLocation getID() {
            return registryObject.getId();
        }

        @Override
        public ResourceKey<? super T> getKey() {
            return registryObject.getKey();
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof RegistrationSupplier<?> supplier && supplier.getID() == getID();
        }
    }
}
