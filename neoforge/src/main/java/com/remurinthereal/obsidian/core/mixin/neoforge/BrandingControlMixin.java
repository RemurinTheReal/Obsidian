package com.remurinthereal.obsidian.core.mixin.neoforge;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.remurinthereal.obsidian.neoforge.ObsidianConfig;
import net.minecraft.DetectedVersion;
import net.neoforged.fml.ModList;
import net.neoforged.fml.i18n.FMLTranslations;
import net.neoforged.neoforge.forge.snapshots.ForgeSnapshotsMod;
import net.neoforged.neoforge.internal.BrandingControl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@SuppressWarnings("UnstableApiUsage")
@Mixin(value = BrandingControl.class, priority = Integer.MAX_VALUE)
public final class BrandingControlMixin {
    @WrapOperation(method = "computeBranding", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList$Builder;add(Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList$Builder;", ordinal = 0))
    private static <E> ImmutableList.Builder<E> removeMinecraftBranding(ImmutableList.Builder<E> instance, E element, Operation<ImmutableList.Builder<E>> original) {
        return ObsidianConfig.BRANDING_COMPACT_VERSION.get() ? instance : original.call(instance, element);
    }
    @WrapOperation(method = "computeBranding", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList$Builder;build()Lcom/google/common/collect/ImmutableList;"))
    private static <E> ImmutableList<E> flipBrandings(ImmutableList.Builder<E> instance, Operation<ImmutableList<E>> original) {
        return ObsidianConfig.BRANDING_COMPACT_VERSION.get() ? original.call(instance).reverse() : original.call(instance);
    }

    @SuppressWarnings("unchecked")
    @WrapOperation(method = "computeBranding", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList$Builder;add(Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList$Builder;", ordinal = 1))
    private static <E> ImmutableList.Builder<E> mergeDefaultBrandings(ImmutableList.Builder<E> instance, E element, Operation<ImmutableList.Builder<E>> original) {
        if (!ObsidianConfig.BRANDING_COMPACT_VERSION.get()) {
            return original.call(instance, element);
        }

        final String MINECRAFT = "Minecraft " + DetectedVersion.BUILT_IN.getName();
        final String NEOFORGE = FMLTranslations.parseMessage("fml.menu.branding", ForgeSnapshotsMod.BRANDING_NAME, ModList.get().size());

        return instance.add((E)(MINECRAFT + "/" + NEOFORGE));
    }
}
