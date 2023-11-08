package com.sweattypalms.skyblock.core.items.builder.abilities.types;

import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.event.Event;

public interface PassiveAbility extends Ability {
    void onTick(SkyblockPlayer player);

    default void apply(Event event) {
    }
}
