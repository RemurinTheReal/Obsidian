package com.remurinthereal.obsidian.core.mixin;

import com.remurinthereal.obsidian.api.platform.PlatformHelper;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {
    @Redirect(method = "containerTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;"))
    private Object containerTick(OptionInstance<Object> instance) {
        return PlatformHelper.isDev() || (boolean)instance.get();
    }

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;"))
    private Object init(OptionInstance<Object> instance) {
        return PlatformHelper.isDev() || (boolean)instance.get();
    }
}
