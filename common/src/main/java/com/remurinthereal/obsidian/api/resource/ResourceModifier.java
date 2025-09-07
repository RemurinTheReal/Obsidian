package com.remurinthereal.obsidian.api.resource;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.remurinthereal.obsidian.core.Obsidian;
import net.minecraft.resources.ResourceKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public final class ResourceModifier {
    private static final HashMap<ResourceKey<?>, List<Consumer<JsonObject>>> RESOURCE_MODIFICATIONS = new HashMap<>();

    public static void addModification(ResourceKey<?> resourceKey, Consumer<JsonObject> consumer) {
        RESOURCE_MODIFICATIONS.computeIfAbsent(resourceKey, key -> new ArrayList<>()).add(consumer);
    }

    public static <T> void run(ResourceKey<T> resourceKey, JsonElement jsonElement) {
        if (!jsonElement.isJsonObject()) {
            Obsidian.LOGGER.warn("Attempted to run resource modification for {}, but supplied jsonElement is not a Json Object!", resourceKey);
            return;
        }

        run(resourceKey, jsonElement.getAsJsonObject());
    }

    private static <T> void run(ResourceKey<T> resourceKey, JsonObject jsonObject) {
        if (!RESOURCE_MODIFICATIONS.containsKey(resourceKey)) {
            return;
        }

        List<Consumer<JsonObject>> modifications = RESOURCE_MODIFICATIONS.get(resourceKey);

        Obsidian.LOGGER.info("Running {} modifications for resource at {}.", modifications.size(), resourceKey.location());
        for (Consumer<JsonObject> next : modifications) {
            try {
                next.accept(jsonObject);
            } catch (Exception exception) {
                Obsidian.LOGGER.error("Exception trying to run a modification for resource at {}.", resourceKey.location(), exception);
            }
        }
    }
}
