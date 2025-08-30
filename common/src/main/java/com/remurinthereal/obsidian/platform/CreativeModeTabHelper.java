package com.remurinthereal.obsidian.platform;

import com.remurinthereal.obsidian.api.CreativeModeTabEvent;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class CreativeModeTabHelper {
    @ExpectPlatform
    public static void modify(ResourceKey<CreativeModeTab> resourceKey, Consumer<CreativeModeTabEvent> consumer) {
        throw new AssertionError();
    }

    public static ResourceKey<? extends CreativeModeTab> register(ResourceLocation resourceLocation, Supplier<CreativeModeTab> creativeModeTab) {
        return RegistrationHelper.register(BuiltInRegistries.CREATIVE_MODE_TAB, resourceLocation, creativeModeTab).getKey();
    }
}
