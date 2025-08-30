package com.remurinthereal.obsidian.api;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface CreativeModeTabEvent {
    public void add(ItemStack... entries);
    public void addAfter(ItemStack targetEntry, ItemStack... entries);
    public void addBefore(ItemStack targetEntry, ItemStack... entries);

    public void remove(ItemStack... entries);
}
