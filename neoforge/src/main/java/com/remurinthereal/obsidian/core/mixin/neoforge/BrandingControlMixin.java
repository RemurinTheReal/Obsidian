package com.remurinthereal.obsidian.core.mixin.neoforge;

import net.minecraft.DetectedVersion;
import net.neoforged.fml.ModList;
import net.neoforged.fml.i18n.FMLTranslations;
import net.neoforged.neoforge.forge.snapshots.ForgeSnapshotsMod;
import net.neoforged.neoforge.internal.BrandingControl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
@Mixin(BrandingControl.class)
public class BrandingControlMixin {
    @Shadow
    private static List<String> brandings;

    @Shadow
    private static List<String> brandingsNoMC;

    /**
     * @author Remurin
     * @reason I don't car
     */
    @Overwrite
    private static void computeBranding() {
        if (brandings != null) {
            return;
        }

        brandings = List.of("Minecraft " + DetectedVersion.BUILT_IN.getName() + "/" + FMLTranslations.parseMessage("fml.menu.branding", ForgeSnapshotsMod.BRANDING_NAME, ModList.get().size()));
        brandingsNoMC = List.copyOf(brandings);
    }
}
