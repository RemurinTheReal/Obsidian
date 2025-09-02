package com.remurinthereal.obsidian.api.platform.neoforge;

import com.google.common.collect.ImmutableList;
import com.remurinthereal.obsidian.api.platform.PlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforgespi.language.IModInfo;

import java.util.List;

public final class PlatformHelperImpl {
    public static List<String> getDependencies(String modID) {
        return ModList.get().getModContainerById(modID).map(modContainer -> {
            return modContainer.getModInfo().getDependencies().stream().map(IModInfo.ModVersion::getModId).toList();
        }).orElseGet(List::of);
    }

    public static List<String> getDependents(String modID) {
        ImmutableList.Builder<String> builder = new ImmutableList.Builder<>();
        for (var mod : ModList.get().getMods()) {
            for (var dependency : mod.getDependencies()) {
                if (!dependency.getModId().equals(modID)) {
                    continue;
                }

                builder.add(mod.getModId());
            }
        }
        return builder.build();
    }

    public static boolean isDev() {
        return !FMLLoader.isProduction();
    }

    public static boolean isModLoaded(String modID) {
        return ModList.get().isLoaded(modID);
    }

    public static PlatformHelper.Platform getPlatform() {
        return PlatformHelper.Platform.NeoForge;
    }
}
