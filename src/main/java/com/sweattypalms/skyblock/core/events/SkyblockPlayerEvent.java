package com.sweattypalms.skyblock.core.events;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.event.Event;

public abstract class SkyblockPlayerEvent extends Event {
    public abstract SkyblockPlayer getSkyblockPlayer();
}
