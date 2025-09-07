package com.remurinthereal.obsidian.mixin.neoforge.core.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Removes the NeoForge branding from the window title. Similar to Fabric.
 * @author Remurin
 */
@Mixin(Minecraft.class)
public final class MinecraftMixin {
    @WrapOperation(method = "createTitle", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(C)Ljava/lang/StringBuilder;", ordinal = 0))
    private StringBuilder removeBlankSpace(StringBuilder instance, char c, Operation<StringBuilder> original) {
        return instance;
    }

    @WrapOperation(method = "createTitle", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;", ordinal = 0))
    private StringBuilder removeNeoForgeBranding(StringBuilder instance, String str, Operation<StringBuilder> original) {
        return instance;
    }
}
