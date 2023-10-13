package com.sweattypalms.skyblock.core.events.def;

import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class SkyblockAbilityUseEvent extends SkyblockPlayerEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final SkyblockPlayer skyblockPlayer;
    @Getter
    private final Ability ability;
    private boolean cancelled = false;

    public SkyblockAbilityUseEvent(SkyblockPlayer skyblockPlayer, Ability ability) {
        this.skyblockPlayer = skyblockPlayer;
        this.ability = ability;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public SkyblockPlayer getSkyblockPlayer() {
        return this.skyblockPlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
