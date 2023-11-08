package com.sweattypalms.skyblock.core.enchants.builder;

import org.bukkit.event.Event;

public interface ITriggerableEnchant {
    boolean should(Event event);
    void execute(int level, Event event);
}
