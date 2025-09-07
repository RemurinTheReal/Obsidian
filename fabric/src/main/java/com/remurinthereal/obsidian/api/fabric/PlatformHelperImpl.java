package com.remurinthereal.obsidian.api.fabric;

import com.remurinthereal.obsidian.api.Mod;
import com.remurinthereal.obsidian.api.Platform;
import com.remurinthereal.obsidian.api.PlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModDependency;
import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * {@link PlatformHelper}
 *
 * @author Remurin
 */
@ApiStatus.Internal
public final class PlatformHelperImpl {
    private PlatformHelperImpl() {
        throw new AssertionError("PlatformHelperImpl should not be instantiated");
    }

    public static boolean isDevEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static Optional<Mod> getMod(String modID) {
        return FabricLoader.getInstance().getModContainer(modID).map(ModImpl::new);
    }

    public static boolean isModLoaded(String modID) {
        return FabricLoader.getInstance().isModLoaded(modID);
    }

    public static Platform getPlatform() {
        return Platform.Fabric;
    }

    private record ModImpl(ModContainer modContainer) implements Mod {
        @Override
        public String getId() {
            return modContainer.getMetadata().getId();
        }

        @Override
        public String getVersion() {
            return modContainer.getMetadata().getVersion().getFriendlyString();
        }

        @Override
        public String getName() {
            return modContainer.getMetadata().getName();
        }

        @Override
        public String getDescription() {
            return modContainer.getMetadata().getDescription();
        }

        @Override
        public List<String> getDependencies() {
            return modContainer.getMetadata().getDependencies().stream().map(ModDependency::getModId).toList();
        }

        @Override
        public Path getFilePath() {
            return modContainer.getRootPaths().getFirst();
        }
    }
}
