package com.remurinthereal.obsidian.api.data;

import net.minecraft.Util;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.neoforge.common.data.LanguageProvider;

public abstract class ExtendedLanguageProvider extends LanguageProvider {
    public ExtendedLanguageProvider(PackOutput output, String modID, String locale) {
        super(output, modID, locale);
    }
}
