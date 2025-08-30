package com.remurinthereal.obsidian.platform.neoforge;

import com.remurinthereal.obsidian.api.CreativeModeTabEvent;
import com.remurinthereal.obsidian.api.RegistrationSupplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public final class CreativeModeTabHelperImpl {
    private static final HashMap<CreativeModeTab, List<Consumer<CreativeModeTabEvent>>> EVENTS = new HashMap<>();

    private static final HashMap<RegistrationSupplier<CreativeModeTab>, List<Consumer<CreativeModeTabEvent>>> REGISTRATION_SUPPLIERS = new HashMap<>();
    private static final HashMap<ResourceKey<CreativeModeTab>, List<Consumer<CreativeModeTabEvent>>> RESOURCE_KEYS = new HashMap<>();

    public static void modify(CreativeModeTab creativeModeTab, Consumer<CreativeModeTabEvent> consumer) {
        EVENTS.computeIfAbsent(creativeModeTab, key -> new ArrayList<>()).add(consumer);
    }

    public static void modify(ResourceKey<CreativeModeTab> creativeModeTab, Consumer<CreativeModeTabEvent> consumer) {
        RESOURCE_KEYS.computeIfAbsent(creativeModeTab, key -> new ArrayList<>()).add(consumer);
    }

    public static void modify(RegistrationSupplier<CreativeModeTab> creativeModeTab, Consumer<CreativeModeTabEvent> consumer) {
        REGISTRATION_SUPPLIERS.computeIfAbsent(creativeModeTab, key -> new ArrayList<>()).add(consumer);
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        REGISTRATION_SUPPLIERS.forEach((key, value) -> {
            EVENTS.computeIfAbsent(key.get(), innerKey -> new ArrayList<>()).addAll(value);
        });
        RESOURCE_KEYS.forEach((key, value) -> {
            EVENTS.computeIfAbsent(BuiltInRegistries.CREATIVE_MODE_TAB.get(key), innerKey -> {
                return new ArrayList<>();
            }).addAll(value);
        });

        REGISTRATION_SUPPLIERS.clear();
        RESOURCE_KEYS.clear();
    }

    @SubscribeEvent
    public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (EVENTS.containsKey(event.getTab())) {
            EVENTS.get(event.getTab()).forEach(x -> x.accept(new NeoForgeCreativeModeTabEvent(event)));
        }
    }

    public record NeoForgeCreativeModeTabEvent(BuildCreativeModeTabContentsEvent event) implements CreativeModeTabEvent {
        @Override
        public void add(ItemStack... entries) {
            for (ItemStack entry : entries) {
                event.accept(entry);
            }
        }

        @Override
        public void addAfter(ItemStack targetEntry, ItemStack... entries) {
            for (ItemStack entry : entries) {
                event.insertAfter(targetEntry, entry, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }

        @Override
        public void addBefore(ItemStack targetEntry, ItemStack... entries) {
            for (ItemStack entry : entries) {
                event.insertBefore(targetEntry, entry, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }

        @Override
        public void remove(ItemStack... entries) {
            List<ItemStack> list = List.of(entries);

            event.getParentEntries().removeIf(list::contains);
            event.getSearchEntries().removeIf(list::contains);
        }
    }
}
