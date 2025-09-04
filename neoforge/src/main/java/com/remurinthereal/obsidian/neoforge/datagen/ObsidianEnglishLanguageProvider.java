package com.remurinthereal.obsidian.neoforge.datagen;

import com.remurinthereal.obsidian.api.datagen.ExtendedLanguageProvider;
import com.remurinthereal.obsidian.neoforge.ObsidianConfig;
import com.remurinthereal.obsidian.neoforge.ObsidianNeoForge;
import net.minecraft.data.PackOutput;

public class ObsidianEnglishLanguageProvider extends ExtendedLanguageProvider {
    private static final String LOCALE = "en_us";

    public ObsidianEnglishLanguageProvider(PackOutput output) {
        super(output, ObsidianNeoForge.MOD_ID, LOCALE);
    }

    @Override
    protected void addTranslations() {
        add(ObsidianConfig.BRANDING_COMPACT_VERSION, "Compact Branding", "NeoForge Branding");
    }
}
