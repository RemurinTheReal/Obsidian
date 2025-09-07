package com.remurinthereal.obsidian.api.item;

import com.remurinthereal.obsidian.api.registry.RegistrationHelper;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Helper class for platform-agnostic {@link CreativeModeTab} modification.
 *
 * @author Remurin
 */
public final class CreativeModeTabHelper {
    private CreativeModeTabHelper() {
        throw new AssertionError("CreativeModeTabHelper should not be instantiated");
    }

    /**
     * Modify a specific creative mode tab identified by the given resource key.
     * <p>
     * The provided consumer receives a {@link CreativeModeTabModifier} which can be used to modify the tab's entries.
     *
     * @param resourceKey the resource key of the creative mode tab to modify
     * @param consumer the consumer that applies modifications
     */
    @ExpectPlatform
    public static void modify(ResourceKey<CreativeModeTab> resourceKey, Consumer<CreativeModeTabModifier> consumer) {
        throw new AssertionError();
    }

    /**
     * Modify all creative mode tabs.
     * <p>
     * The provided consumer receives a {@link CreativeModeTabModifier} which can be used to modify tab entries.
     *
     * @param consumer the consumer that applies modifications
     */
    @ExpectPlatform
    public static void modifyAll(Consumer<CreativeModeTabModifier> consumer) {
        throw new AssertionError();
    }

    /**
     * Registers a new creative mode tab with a specified title and icon.
     *
     * @param resourceLocation the id for the creative tab
     * @param title the title component displayed for the tab
     * @param icon a supplier for the tab's icon {@link ItemStack}
     * @return the {@link ResourceKey} associated with the registered creative mode tab
     */
    public static ResourceKey<CreativeModeTab> register(ResourceLocation resourceLocation, Component title, Supplier<ItemStack> icon) {
        return register(resourceLocation, builder -> {
            builder.title(title);
            builder.icon(icon);
        });
    }
    /**
     * Registers a new creative mode tab.
     *
     * @param resourceLocation the id for the creative tab
     * @param consumer a consumer that configures the {@link CreativeModeTab.Builder}
     * @return the {@link ResourceKey} associated with the registered creative mode tab
     */

    @SuppressWarnings("unchecked")
    public static ResourceKey<CreativeModeTab> register(ResourceLocation resourceLocation, Consumer<CreativeModeTab.Builder> consumer) {
        return (ResourceKey<CreativeModeTab>) RegistrationHelper.register(BuiltInRegistries.CREATIVE_MODE_TAB, resourceLocation, () -> {
            CreativeModeTab.Builder builder = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0);
            consumer.accept(builder);

            return builder.build();
        }).getKey();
    }
}
