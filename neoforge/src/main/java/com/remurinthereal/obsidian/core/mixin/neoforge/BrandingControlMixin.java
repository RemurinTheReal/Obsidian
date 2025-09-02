package com.remurinthereal.obsidian.core.mixin.neoforge;

import com.google.common.collect.ImmutableList;
import com.remurinthereal.obsidian.neoforge.ObsidianConfig;
import net.minecraft.DetectedVersion;
import net.neoforged.fml.ModList;
import net.neoforged.fml.i18n.FMLTranslations;
import net.neoforged.neoforge.forge.snapshots.ForgeSnapshotsMod;
import net.neoforged.neoforge.internal.BrandingControl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("UnstableApiUsage")
@Mixin(value = BrandingControl.class, priority = Integer.MAX_VALUE)
public final class BrandingControlMixin {
    @Redirect(method = "computeBranding", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList$Builder;add(Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList$Builder;", ordinal = 0))
    private static ImmutableList.Builder<String> removeMinecraftBranding(ImmutableList.Builder<String> instance, Object element) {
        return ObsidianConfig.BRANDING_COMPACT_VERSION.get() ? instance : instance.add((String)element);
    }
    @Redirect(method = "computeBranding", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList$Builder;build()Lcom/google/common/collect/ImmutableList;"))
    private static ImmutableList<String> flipBrandings(ImmutableList.Builder<String> instance) {
        return ObsidianConfig.BRANDING_COMPACT_VERSION.get() ? instance.build().reverse() : instance.build();
    }

    @Redirect(method = "computeBranding", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList$Builder;add(Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList$Builder;", ordinal = 1))
    private static ImmutableList.Builder<String> mergeDefaultBrandings(ImmutableList.Builder<String> instance, Object element) {
        if (!ObsidianConfig.BRANDING_COMPACT_VERSION.get()) {
            return instance.add((String)element);
        }

        final String MINECRAFT = "Minecraft " + DetectedVersion.BUILT_IN.getName();
        final String NEOFORGE = FMLTranslations.parseMessage("fml.menu.branding", ForgeSnapshotsMod.BRANDING_NAME, ModList.get().size());

        return instance.add(MINECRAFT + "/" + NEOFORGE);
    }
}
