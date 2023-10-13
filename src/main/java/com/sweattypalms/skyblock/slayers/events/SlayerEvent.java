package com.sweattypalms.skyblock.slayers.events;

import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerEvent;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.slayers.ISlayerMob;
import lombok.Getter;
import org.bukkit.event.HandlerList;

public class SlayerEvent extends SkyblockPlayerEvent {
    private static final HandlerList HANDLERS = new HandlerList();
    @Getter
    private final ISlayerMob slayerMob;
    private final SkyblockPlayer skyblockPlayer;

    public SlayerEvent(ISlayerMob slayerMob, SkyblockPlayer skyblockPlayer) {
        this.slayerMob = slayerMob;
        this.skyblockPlayer = skyblockPlayer;
    }

    @Override
    public SkyblockPlayer getSkyblockPlayer() {
        return this.skyblockPlayer;
    }
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
