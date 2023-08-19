package com.sweattypalms.skyblock.core.events;

import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SkyblockInteractEvent extends Event implements ISkyblockPlayerEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final SkyblockPlayer skyblockPlayer;
    private final TriggerType interactType;

    public SkyblockInteractEvent(SkyblockPlayer skyblockPlayer, TriggerType interactType) {
        this.skyblockPlayer = skyblockPlayer;
        this.interactType = interactType;
    }

    @Override
    public SkyblockPlayer getSkyblockPlayer() {
        return this.skyblockPlayer;
    }

    public TriggerType getInteractType() {
        return this.interactType;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
