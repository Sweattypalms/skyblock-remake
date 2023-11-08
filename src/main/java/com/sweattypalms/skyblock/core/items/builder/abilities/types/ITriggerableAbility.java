package com.sweattypalms.skyblock.core.items.builder.abilities.types;

import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import org.bukkit.event.Event;

public interface ITriggerableAbility extends Ability {
    TriggerType getTriggerType();
    boolean trigger(Event event);
}