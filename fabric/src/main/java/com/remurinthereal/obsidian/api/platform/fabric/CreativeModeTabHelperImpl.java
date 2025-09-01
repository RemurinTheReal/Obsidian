package com.remurinthereal.obsidian.api.platform.fabric;

import com.remurinthereal.obsidian.api.CreativeModeTabEvent;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public final class CreativeModeTabHelperImpl {
    public static void modify(ResourceKey<CreativeModeTab> resourceKey, Consumer<CreativeModeTabEvent> consumer) {
        ItemGroupEvents.modifyEntriesEvent(resourceKey).register(event -> {
            consumer.accept(new FabricCreativeModeTabEvent(event));
        });
    }

    public record FabricCreativeModeTabEvent(FabricItemGroupEntries event) implements CreativeModeTabEvent {
        @Override
        public void add(ItemStack... entries) {
            for (ItemStack entry : entries) {
                event.accept(entry);
            }
        }

        @Override
        public void addAfter(ItemStack targetEntry, ItemStack... entries) {
            event.addAfter(targetEntry, entries);
        }

        @Override
        public void addBefore(ItemStack targetEntry, ItemStack... entries) {
            event.addBefore(targetEntry, entries);
        }

        @Override
        public void remove(ItemStack... entries) {
            for (ItemStack entry : entries) {
                int searchIndex = event.getSearchTabStacks().indexOf(entry);
                if (searchIndex != -1) event.getSearchTabStacks().remove(searchIndex);

                int displayIndex = event.getDisplayStacks().indexOf(entry);
                if (displayIndex != -1) event.getDisplayStacks().remove(displayIndex);
            }
        }
    }
}
