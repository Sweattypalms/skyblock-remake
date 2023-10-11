package com.sweattypalms.skyblock.core.items.builder.abilities;


import org.bukkit.event.Event;

import java.util.List;

public interface Ability {
    String getName();
    default boolean nameVisible(){
        return true;
    }
    List<String> getDescription();

    void apply(Event event);
}
