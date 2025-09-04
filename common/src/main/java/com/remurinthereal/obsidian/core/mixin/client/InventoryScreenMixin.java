package com.remurinthereal.obsidian.core.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.remurinthereal.obsidian.api.platform.PlatformHelper;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Ensures the operator Creative Mode Tab is always enabled when in dev environments.
 * @author Remurin
 */
@Mixin(InventoryScreen.class)
public final class InventoryScreenMixin {
    @WrapOperation(method = "containerTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;"))
    private <T> Object containerTick(OptionInstance<T> instance, Operation<T> original) {
        return PlatformHelper.isDevEnvironment() || (Boolean)original.call(instance);
    }

    @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;"))
    private <T> Object init(OptionInstance<T> instance, Operation<T> original) {
        return PlatformHelper.isDevEnvironment() || (Boolean)original.call(instance);
    }
}
