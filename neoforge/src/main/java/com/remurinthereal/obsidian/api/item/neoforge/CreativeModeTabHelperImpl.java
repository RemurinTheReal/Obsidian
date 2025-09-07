package com.remurinthereal.obsidian.api.item.neoforge;

import com.remurinthereal.obsidian.core.Obsidian;
import com.remurinthereal.obsidian.api.item.CreativeModeTabModifier;
import com.remurinthereal.obsidian.api.item.CreativeModeTabHelper;
import com.remurinthereal.obsidian.api.registry.RegistrationHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * {@link CreativeModeTabHelper}
 *
 * @author Remurin
 */
@ApiStatus.Internal
public final class CreativeModeTabHelperImpl {
    private CreativeModeTabHelperImpl() {
        throw new AssertionError("CreativeModeTabHelperImpl should not be instantiated");
    }

    private static final HashMap<ResourceKey<CreativeModeTab>, List<Consumer<CreativeModeTabModifier>>> EVENTS = new HashMap<>();
    private static final List<Consumer<CreativeModeTabModifier>> ALL_EVENTS = new ArrayList<>();

    public static void modify(ResourceKey<CreativeModeTab> creativeModeTab, Consumer<CreativeModeTabModifier> consumer) {
        EVENTS.computeIfAbsent(creativeModeTab, key -> new ArrayList<>()).add(consumer);
    }

    public static void modifyAll(Consumer<CreativeModeTabModifier> consumer) {
        ALL_EVENTS.add(consumer);
    }

    @SuppressWarnings("unchecked")
    public static ResourceKey<CreativeModeTab> register(ResourceLocation resourceLocation, Consumer<CreativeModeTab.Builder> consumer) {
        return (ResourceKey<CreativeModeTab>) RegistrationHelper.register(BuiltInRegistries.CREATIVE_MODE_TAB, resourceLocation, () -> {
            CreativeModeTab.Builder builder = CreativeModeTab.builder();
            consumer.accept(builder);

            return builder.build();
        }).getKey();
    }

    @SubscribeEvent
    public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        ALL_EVENTS.forEach(x -> x.accept(new CreativeModeTabModifierImpl(event, event.getTab(), event.getTabKey())));
        if (EVENTS.containsKey(event.getTabKey())) {
            EVENTS.get(event.getTabKey()).forEach(x -> x.accept(new CreativeModeTabModifierImpl(event, event.getTab(), event.getTabKey())));
        }
    }

    private record CreativeModeTabModifierImpl(BuildCreativeModeTabContentsEvent buildCreativeModeTabContentsEvent, CreativeModeTab creativeModeTab, ResourceKey<CreativeModeTab> resourceKey) implements CreativeModeTabModifier {
        @Override
            public CreativeModeTab.ItemDisplayParameters getDisplayParameters() {
                return buildCreativeModeTabContentsEvent.getParameters();
            }

            public CreativeModeTab getTab() {
                return creativeModeTab;
            }

            public ResourceKey<CreativeModeTab> getTabKey() {
                return resourceKey;
            }

            @Override
            public void append(Object... entries) {
                for (Object entry : entries) {
                    switch (entry instanceof Supplier<?> supplier ? supplier.get() : entry) {
                        case ItemStack itemStack -> buildCreativeModeTabContentsEvent.accept(itemStack);
                        case ItemLike itemLike -> buildCreativeModeTabContentsEvent.accept(itemLike);
                        default -> {
                        }
                    }
                }
            }

            @Override
            public void prepend(Object... entries) {
                for (Object entry : entries) {
                    switch (entry instanceof Supplier<?> supplier ? supplier.get() : entry) {
                        case ItemStack itemStack ->
                                buildCreativeModeTabContentsEvent.insertFirst(itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        case ItemLike itemLike ->
                                buildCreativeModeTabContentsEvent.insertFirst(itemLike.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        default -> {
                        }
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
                    buildCreativeModeTabContentsEvent.getParentEntries().remove(entry);
                    buildCreativeModeTabContentsEvent.getSearchEntries().remove(entry);
                }
            }

            private void insertAfter(Object targetEntry, Object... entries) {
                var obj = targetEntry instanceof Supplier<?> supplier ? supplier.get() : targetEntry;
                if (obj instanceof ItemLike target) {
                    var resolvedTarget = target.asItem().getDefaultInstance();
                    for (Object entry : entries) {
                        switch (entry instanceof Supplier<?> supplier ? supplier.get() : entry) {
                            case ItemStack itemStack ->
                                    buildCreativeModeTabContentsEvent.insertAfter(resolvedTarget, itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                            case ItemLike itemLike ->
                                    buildCreativeModeTabContentsEvent.insertAfter(resolvedTarget, itemLike.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                            default -> {
                            }
                        }
                    }
                } else if (obj instanceof ItemStack target) {
                    for (Object entry : entries) {
                        switch (entry instanceof Supplier<?> supplier ? supplier.get() : entry) {
                            case ItemStack itemStack ->
                                    buildCreativeModeTabContentsEvent.insertAfter(target, itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                            case ItemLike itemLike ->
                                    buildCreativeModeTabContentsEvent.insertAfter(target, itemLike.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                            default -> {
                            }
                        }
                    }
                }
            }

            private void insertBefore(Object targetEntry, Object... entries) {
                var obj = targetEntry instanceof Supplier<?> supplier ? supplier.get() : targetEntry;
                if (obj instanceof ItemLike target) {
                    var resolvedTarget = target.asItem().getDefaultInstance();
                    for (Object entry : entries) {
                        switch (entry instanceof Supplier<?> supplier ? supplier.get() : entry) {
                            case ItemStack itemStack ->
                                    buildCreativeModeTabContentsEvent.insertBefore(resolvedTarget, itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                            case ItemLike itemLike ->
                                    buildCreativeModeTabContentsEvent.insertBefore(resolvedTarget, itemLike.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                            default -> {
                            }
                        }
                    }
                } else if (obj instanceof ItemStack target) {
                    for (Object entry : entries) {
                        switch (entry instanceof Supplier<?> supplier ? supplier.get() : entry) {
                            case ItemStack itemStack ->
                                    buildCreativeModeTabContentsEvent.insertBefore(target, itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                            case ItemLike itemLike ->
                                    buildCreativeModeTabContentsEvent.insertBefore(target, itemLike.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                            default -> {
                            }
                        }
                    }
                }
            }
        }
}