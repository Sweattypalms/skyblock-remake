package com.sweattypalms.skyblock.core.items.builder.abilities;


import com.sweattypalms.skyblock.core.stats.SkyblockPlayer;
import org.bukkit.event.Event;

public interface Ability {
    TriggerType getTriggerType();

    boolean trigger(Event event);

    void apply(SkyblockPlayer player, Event event);
}
