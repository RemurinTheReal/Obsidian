package com.remurinthereal.obsidian.api.platform.fabric;

import com.remurinthereal.obsidian.api.CreativeModeTabModifier;
import com.remurinthereal.obsidian.api.platform.CreativeModeTabHelper;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * {@link CreativeModeTabHelper}
 *
 * @author Remurin
 */
final class CreativeModeTabHelperImpl {
    public static void modify(ResourceKey<CreativeModeTab> resourceKey, Consumer<CreativeModeTabModifier> consumer) {
        ItemGroupEvents.modifyEntriesEvent(resourceKey).register(event -> {
            consumer.accept(new CreativeModeTabModifierImpl(resourceKey, event));
        });
    }

    public static void modifyAll(Consumer<CreativeModeTabModifier> consumer) {
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register((creativeModeTab, event) -> {
            consumer.accept(new CreativeModeTabModifierImpl(creativeModeTab, event));
        });
    }

    public static ResourceKey<CreativeModeTab> register(ResourceLocation resourceLocation, Consumer<CreativeModeTab.Builder> consumer) {
        CreativeModeTab.Builder builder = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0);
        consumer.accept(builder);

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, resourceLocation, builder.build());
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, resourceLocation);
    }

    private static class CreativeModeTabModifierImpl implements CreativeModeTabModifier {
        private final FabricItemGroupEntries fabricItemGroupEntries;
        private CreativeModeTab creativeModeTab;
        private ResourceKey<CreativeModeTab> resourceKey;

        public CreativeModeTabModifierImpl(CreativeModeTab creativeModeTab, FabricItemGroupEntries fabricItemGroupEntries) {
            this.creativeModeTab = creativeModeTab;
            this.fabricItemGroupEntries = fabricItemGroupEntries;
        }

        public CreativeModeTabModifierImpl(ResourceKey<CreativeModeTab> creativeModeTab, FabricItemGroupEntries fabricItemGroupEntries) {
            this.fabricItemGroupEntries = fabricItemGroupEntries;
            this.resourceKey = creativeModeTab;
        }

        @Override
        public CreativeModeTab.ItemDisplayParameters getDisplayParameters() {
            return fabricItemGroupEntries.getContext();
        }

        public CreativeModeTab getTab() {
            if (creativeModeTab == null) {
                creativeModeTab = BuiltInRegistries.CREATIVE_MODE_TAB.get(resourceKey);
            }

            return creativeModeTab;
        }

        public ResourceKey<CreativeModeTab> getTabKey() {
            if (resourceKey == null) {
                resourceKey = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(creativeModeTab).orElse(null);
            }

            return resourceKey;
        }

        @Override
        public void append(Object... entries) {
            for (Object entry : entries) {
                switch (entry instanceof Supplier<?> supplier ? supplier.get() : entry) {
                    case ItemStack itemStack -> fabricItemGroupEntries.accept(itemStack);
                    case ItemLike itemLike -> fabricItemGroupEntries.accept(itemLike);
                    default -> {}
                }
            }
        }

        @Override
        public void prepend(Object... entries) {
            for (Object entry : entries) {
                switch (entry instanceof Supplier<?> supplier ? supplier.get() : entry) {
                    case ItemStack itemStack -> fabricItemGroupEntries.prepend(itemStack);
                    case ItemLike itemLike -> fabricItemGroupEntries.prepend(itemLike);
                    default -> {}
                }
            }
        }

        @Override
        public void insert(Object targetEntry, InsertType insertType, Object... entries) {
            if (insertType == InsertType.AFTER) {
                insertAfter(targetEntry, entries);
                return;
            }

            insertBefore(targetEntry, entries);
        }

        @Override
        @SuppressWarnings("SuspiciousMethodCalls")
        public void remove(Object... targetEntries) {
            for (Object entry : targetEntries) {
                fabricItemGroupEntries.getDisplayStacks().remove(entry);
                fabricItemGroupEntries.getSearchTabStacks().remove(entry);
            }
        }

        private void insertAfter(Object targetEntry, Object... entries) {
            var obj = targetEntry instanceof Supplier<?> supplier ? supplier.get() : targetEntry;
            if (obj instanceof ItemLike target) {
                for (Object entry : entries) {
                    switch (entry instanceof Supplier<?> supplier ? supplier.get() : entry) {
                        case ItemStack itemStack -> fabricItemGroupEntries.addAfter(target, itemStack);
                        case ItemLike itemLike -> fabricItemGroupEntries.addAfter(target, itemLike);
                        default -> {}
                    }
                }
            } else if (obj instanceof ItemStack target) {
                for (Object entry : entries) {
                    switch (entry instanceof Supplier<?> supplier ? supplier.get() : entry) {
                        case ItemStack itemStack -> fabricItemGroupEntries.addAfter(target, itemStack);
                        case ItemLike itemLike -> fabricItemGroupEntries.addAfter(target, itemLike);
                        default -> {}
                    }
                }
            }
        }

        private void insertBefore(Object targetEntry, Object... entries) {
            var obj = targetEntry instanceof Supplier<?> supplier ? supplier.get() : targetEntry;
            if (obj instanceof ItemLike target) {
                for (Object entry : entries) {
                    switch (entry instanceof Supplier<?> supplier ? supplier.get() : entry) {
                        case ItemStack itemStack -> fabricItemGroupEntries.addBefore(target, itemStack);
                        case ItemLike itemLike -> fabricItemGroupEntries.addBefore(target, itemLike);
                        default -> {}
                    }
                }
            } else if (obj instanceof ItemStack target) {
                for (Object entry : entries) {
                    switch (entry instanceof Supplier<?> supplier ? supplier.get() : entry) {
                        case ItemStack itemStack -> fabricItemGroupEntries.addBefore(target, itemStack);
                        case ItemLike itemLike -> fabricItemGroupEntries.addBefore(target, itemLike);
                        default -> {}
                    }
                }
            }
        }
    }
}
