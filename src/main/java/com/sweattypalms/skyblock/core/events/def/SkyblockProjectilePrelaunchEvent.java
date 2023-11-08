package com.sweattypalms.skyblock.core.events.def;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Mainly used for Shortbows
 */
@Getter
public class SkyblockProjectilePrelaunchEvent extends SkyblockPlayerEvent implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    private final SkyblockPlayer skyblockPlayer;

    @Setter
    private boolean cancelled;

    public SkyblockProjectilePrelaunchEvent(SkyblockPlayer skyblockPlayer) {
        this.skyblockPlayer = skyblockPlayer;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
