package com.remurinthereal.obsidian.mixin.api;

import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.mojang.serialization.Decoder;
import com.remurinthereal.obsidian.api.resource.ResourceModifier;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.Resource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Handles running the resource modifications in a (hopefully) safe way.
 * @author Remurin
 */
@Mixin(RegistryDataLoader.class)
final class RegistryDataLoaderMixin {

    @SuppressWarnings("DiscouragedShift")
    @Inject(method = "loadElementFromResource", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lcom/mojang/serialization/Decoder;parse(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;"))
    private static <T> void storeResourceKey(WritableRegistry<T> writableRegistry, Decoder<T> decoder, RegistryOps<JsonElement> registryOps, ResourceKey<T> resourceKey, Resource resource, RegistrationInfo registrationInfo, CallbackInfo ci, @Local LocalRef<JsonElement> jsonElement) {
        ResourceModifier.run(resourceKey, jsonElement.get());
    }
}
