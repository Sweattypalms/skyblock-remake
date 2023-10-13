package com.sweattypalms.skyblock.slayers.events;

import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerEvent;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.slayers.Slayer;
import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class SlayerStartEvent extends SkyblockPlayerEvent implements Cancellable {

    @Getter
    private final SkyblockPlayer skyblockPlayer;
    @Getter
    private final Slayer slayer;
    private static final HandlerList HANDLERS = new HandlerList();

    private boolean isCancelled = false;

    public SlayerStartEvent(SkyblockPlayer skyblockPlayer, Slayer slayer) {
        this.skyblockPlayer = skyblockPlayer;
        this.slayer = slayer;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return this.HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }
}
