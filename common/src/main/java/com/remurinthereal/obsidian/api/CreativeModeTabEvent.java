package com.remurinthereal.obsidian.api;

import net.minecraft.world.item.ItemStack;

public interface CreativeModeTabEvent {
    void add(ItemStack... entries);
    void addAfter(ItemStack targetEntry, ItemStack... entries);
    void addBefore(ItemStack targetEntry, ItemStack... entries);
    void remove(ItemStack... entries);
}
