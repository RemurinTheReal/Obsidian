package com.remurinthereal.obsidian.platform.fabric;

import com.remurinthereal.obsidian.api.RegistrationSupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public final class RegistrationHelperImpl {
    public static <T> RegistrationSupplier<? extends T> register(Registry<T> registry, ResourceLocation resourceLocation, Supplier<? extends T> supplier) {
        T obj = Registry.register(registry, resourceLocation, supplier.get());
        return () -> obj;
    }
}
