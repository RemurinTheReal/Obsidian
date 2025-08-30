package com.remurinthereal.obsidian.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Consumer;

public final class CreativeModeTabHelper {
    @ExpectPlatform
    public static void modify(ResourceKey<CreativeModeTab> resourceKey, Consumer<CreativeModeTab> consumer) {
        throw new AssertionError();
    }
}
