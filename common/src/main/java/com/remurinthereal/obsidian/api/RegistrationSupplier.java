package com.remurinthereal.obsidian.api;

import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public interface RegistrationSupplier<T> extends Supplier<T> {
    @Override T get();
    ResourceKey<? super T> getKey();
}
