package com.remurinthereal.obsidian.api.platform.neoforge;

import com.google.common.collect.ImmutableList;
import com.remurinthereal.obsidian.api.Mod;
import com.remurinthereal.obsidian.api.platform.PlatformHelper;
import com.remurinthereal.obsidian.core.Platform;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforgespi.language.IModInfo;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * {@link PlatformHelper}
 *
 * @author Remurin
 */
final class PlatformHelperImpl {
    public static boolean isDevEnvironment() {
        return !FMLLoader.isProduction();
    }

    public static Optional<Mod> getMod(String modID) {
        return ModList.get().getModContainerById(modID).map(ModImpl::new);
    }
    public static boolean isModLoaded(String modID) {
        return ModList.get().isLoaded(modID);
    }

    public static Platform getPlatform() {
        return Platform.NeoForge;
    }
    private record ModImpl(ModContainer modContainer) implements Mod {
        @Override
        public String getID() {
            return modContainer.getModId();
        }

        @Override
        public String getVersion() {
            return modContainer.getModInfo().getVersion().toString();
        }

        @Override
        public String getName() {
            return modContainer.getModInfo().getDisplayName();
        }

        @Override
        public String getDescription() {
            return modContainer.getModInfo().getDescription();
        }

        @Override
        public List<String> getDependencies() {
            return modContainer.getModInfo().getDependencies().stream().map(IModInfo.ModVersion::getModId).toList();
        }

        @Override
        public Path getFilePath() {
            return modContainer.getModInfo().getOwningFile().getFile().getFilePath();
        }
    }
}
