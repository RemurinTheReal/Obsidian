package com.remurinthereal.obsidian.api.util;

import com.google.gson.*;

import java.util.regex.Pattern;

/**
 * Utilities class to assist with modifying json elements at runtime.
 *
 * @author Remurin
 */
public final class JsonUtil {
    private static final Pattern INDEX_PATTERN = Pattern.compile("([a-zA-Z0-9_\\-]+)\\[(\\d+)]");

    /**
     * Retrieves a nested {@link JsonArray} from a {@link JsonObject} based on the given path.
     *
     * @param jsonObject the root json object
     * @param memberPath dot-separated path with optional array indices
     * @return the {@link JsonArray} at the given path
     * @throws IllegalStateException if the element at the path is not a {@link JsonArray} or the path is invalid
     */
    public static JsonArray getAsJsonArray(JsonObject jsonObject, String memberPath) {
        return get(jsonObject, memberPath).getAsJsonArray();
    }

    /**
     * Retrieves a nested {@link JsonObject} from a {@link JsonObject} based on the given path.
     *
     * @param jsonObject the root json object
     * @param memberPath dot-separated path with optional array indices
     * @return the {@link JsonObject} at the given path
     * @throws IllegalStateException if the element at the path is not a {@link JsonObject} or the path is invalid
     */
    public static JsonObject getAsJsonObject(JsonObject jsonObject, String memberPath) {
        return get(jsonObject, memberPath).getAsJsonObject();
    }

    /**
     * Retrieves a nested {@link JsonPrimitive} from a {@link JsonObject} based on the given path.
     *
     * @param jsonObject the root json object
     * @param memberPath dot-separated path with optional array indices
     * @return the {@link JsonPrimitive} at the given path
     * @throws IllegalStateException if the element at the path is not a {@link JsonPrimitive} or the path is invalid
     */
    public static JsonPrimitive getAsJsonPrimitive(JsonObject jsonObject, String memberPath) {
        return get(jsonObject, memberPath).getAsJsonPrimitive();
    }

    /**
     * Retrieves a nested {@link JsonElement} from a {@link JsonObject} based on the given path.
     *
     * @param jsonObject the root json object
     * @param memberPath dot-separated path with optional array indices
     * @return the {@link JsonElement} at the given path
     * @throws IllegalStateException if the path is invalid
     */
    public static JsonElement get(JsonObject jsonObject, String memberPath) {
        JsonElement current = jsonObject;
        for (String segment : memberPath.split("\\.")) {
            var matcher = INDEX_PATTERN.matcher(segment);
            if (!matcher.matches()) {
                current = jsonObject.getAsJsonObject(segment);
                continue;
            }

            String key = matcher.group(1);

            var currentElement = jsonObject.get(key);
            if (!currentElement.isJsonArray()) {
                throw new IllegalStateException();
            }

            current = currentElement.getAsJsonArray().get(Integer.parseInt(matcher.group(2)));
        }
        return current;
    }
}
