package com.remurinthereal.obsidian.core.datagen;

import com.remurinthereal.obsidian.api.ExtendedLanguageProvider;
import com.remurinthereal.obsidian.core.ObsidianConfig;
import com.remurinthereal.obsidian.core.neoforge.ObsidianNeoForge;
import net.minecraft.data.PackOutput;

public class ObsidianEnglishLanguageProvider extends ExtendedLanguageProvider {
    private static final String LOCALE = "en_us";

    public ObsidianEnglishLanguageProvider(PackOutput output) {
        super(output, ObsidianNeoForge.MOD_ID, LOCALE);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void addTranslations() {
        add(ObsidianConfig.BRANDING_COMPACT_VERSION, "Compact Branding", "NeoForge Branding");
    }
}
