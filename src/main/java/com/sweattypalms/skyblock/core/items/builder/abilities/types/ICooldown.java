package com.sweattypalms.skyblock.core.items.builder.abilities.types;

import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerEvent;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.event.Event;

public interface ICooldown extends Ability {
    /**
     * @return the cooldown in milliseconds
     */
    long getCooldown(SkyblockPlayer skyblockPlayer);

    default void setLastUse(SkyblockPlayer skyblockPlayer) {
        skyblockPlayer.setLastUseTime(getName(), System.currentTimeMillis());
    }

    default long getLastUse(SkyblockPlayer skyblockPlayer) {
        return skyblockPlayer.getLastUseTime(getName());
    }

    default boolean isOnCooldown(SkyblockPlayer skyblockPlayer) {
        return System.currentTimeMillis() - getLastUse(skyblockPlayer) < getCooldown(skyblockPlayer);
    }

    @Override
    default void apply(Event event) {
        if (!(event instanceof SkyblockPlayerEvent skyblockPlayerEvent)) return;
        SkyblockPlayer skyblockPlayer = skyblockPlayerEvent.getSkyblockPlayer();
        setLastUse(skyblockPlayer);
    }
}
