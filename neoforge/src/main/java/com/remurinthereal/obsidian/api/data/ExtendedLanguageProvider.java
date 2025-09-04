package com.remurinthereal.obsidian.api.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public abstract class ExtendedLanguageProvider extends LanguageProvider {
    public ExtendedLanguageProvider(PackOutput output, String modID, String locale) {
        super(output, modID, locale);
    }
}
