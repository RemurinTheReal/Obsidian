package com.remurinthereal.obsidian.core;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class ObsidianConfig {
    public static final ModConfigSpec CLIENT_CONFIG;
    public static final ModConfigSpec.ConfigValue<Boolean> BRANDING_COMPACT_VERSION;

    private static final String BRANDING_COMPACT_VERSION_COMMENT = "If true, Minecraft and NeoForge's version text on the title screen will be merged.";

    static {
        final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        BRANDING_COMPACT_VERSION = BUILDER.comment(BRANDING_COMPACT_VERSION_COMMENT).define("neoforge_branding.compact_version", true);
        CLIENT_CONFIG = BUILDER.build();
    }
}
