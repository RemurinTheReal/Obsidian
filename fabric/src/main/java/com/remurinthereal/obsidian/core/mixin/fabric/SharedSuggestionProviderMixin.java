package com.remurinthereal.obsidian.core.mixin.fabric;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.commands.SharedSuggestionProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Removes the minecraft namespace prerequisite for suggestions. Similar to NeoForge.
 * @author Remurin
 */
@Mixin(SharedSuggestionProvider.class)
public interface SharedSuggestionProviderMixin {
    @WrapOperation(method = "filterResources(Ljava/lang/Iterable;Ljava/lang/String;Ljava/util/function/Function;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z"))
    private static boolean filterResources(String instance, Object o, Operation<Boolean> original) {
        return true;
    }
}
