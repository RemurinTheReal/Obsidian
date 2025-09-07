package com.remurinthereal.obsidian.core.datagen;

import com.remurinthereal.obsidian.core.neoforge.ObsidianNeoForge;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ObsidianNeoForge.MOD_ID)
public final class ObsidianDataGen {
    
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        var generator = event.getGenerator();

        var packOutput = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        var existingFileHelper = event.getExistingFileHelper();

        if (event.includeClient()) onGatherDataClient(event, packOutput, lookupProvider, existingFileHelper);
        if (event.includeServer()) onGatherDataServer(event, packOutput, lookupProvider, existingFileHelper);
    }

    private static void onGatherDataClient(GatherDataEvent event, PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        event.addProvider(new ObsidianEnglishLanguageProvider(packOutput));
    }

    private static void onGatherDataServer(GatherDataEvent event, PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {}
}
