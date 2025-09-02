package com.remurinthereal.obsidian.api.platform.fabric;

import com.google.common.collect.ImmutableList;
import com.remurinthereal.obsidian.api.platform.PlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModDependency;

import java.util.List;

public final class PlatformHelperImpl {
    public static List<String> getDependencies(String modID) {
        return FabricLoader.getInstance().getModContainer(modID).map(modContainer -> {
            return modContainer.getMetadata().getDependencies().stream().map(ModDependency::getModId).toList();
        }).orElseGet(List::of);
    }

    public static List<String> getDependents(String modID) {
        ImmutableList.Builder<String> builder = new ImmutableList.Builder<>();
        for (var mod : FabricLoader.getInstance().getAllMods()) {
            for (var dependency : mod.getMetadata().getDependencies()) {
                if (!dependency.getModId().equals(modID)) {
                    continue;
                }

                builder.add(mod.getMetadata().getId());
            }
        }
        return builder.build();
    }

    public static boolean isDev() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static boolean isModLoaded(String modID) {
        return FabricLoader.getInstance().isModLoaded(modID);
    }

    public static PlatformHelper.Platform getPlatform() {
        return PlatformHelper.Platform.Fabric;
    }
}
