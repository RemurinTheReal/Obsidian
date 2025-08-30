package com.remurinthereal.obsidian.platform;

import com.remurinthereal.obsidian.api.CreativeModeTabEvent;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Consumer;

public final class CreativeModeTabHelper {
    @ExpectPlatform
    public static void modify(ResourceKey<CreativeModeTab> resourceKey, Consumer<CreativeModeTabEvent> consumer) {
        throw new AssertionError();
    }
}
