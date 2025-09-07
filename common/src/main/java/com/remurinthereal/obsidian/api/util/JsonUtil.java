package com.remurinthereal.obsidian.api.util;

import com.google.gson.*;

import java.util.regex.Pattern;

public final class JsonUtil {
    private static final Pattern INDEX_PATTERN = Pattern.compile("([a-zA-Z0-9_\\-]+)\\[(\\d+)]");

    public static JsonArray getAsJsonArray(JsonObject jsonObject, String memberPath) {
        return get(jsonObject, memberPath).getAsJsonArray();
    }

    public static JsonObject getAsJsonObject(JsonObject jsonObject, String memberPath) {
        return get(jsonObject, memberPath).getAsJsonObject();
    }

    public static JsonPrimitive getAsJsonPrimitive(JsonObject jsonObject, String memberPath) {
        return get(jsonObject, memberPath).getAsJsonPrimitive();
    }

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
