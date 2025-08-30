package com.remurinthereal.obsidian.platform;

import com.remurinthereal.obsidian.api.RegistrationSupplier;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public final class RegistrationHelper {
    @ExpectPlatform
    public static <T> RegistrationSupplier<? extends T> register(Registry<T> registry, ResourceLocation resourceLocation, Supplier<? extends T> supplier) {
        throw new AssertionError();
    }
}
