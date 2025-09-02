package com.remurinthereal.obsidian.core.mixin.neoforge.client;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public final class MinecraftMixin {
    @Redirect(method = "createTitle", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(C)Ljava/lang/StringBuilder;", ordinal = 0))
    private StringBuilder removeBlankSpace(StringBuilder instance, char c) {
        return instance;
    }

    @Redirect(method = "createTitle", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;", ordinal = 0))
    private StringBuilder removeNeoForgeBranding(StringBuilder instance, String str) {
        return instance;
    }
}
