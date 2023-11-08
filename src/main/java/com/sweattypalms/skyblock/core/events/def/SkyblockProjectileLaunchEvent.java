package com.sweattypalms.skyblock.core.events.def;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SkyblockProjectileLaunchEvent<T extends Projectile> extends SkyblockPlayerEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    @Getter
    private final SkyblockPlayer skyblockPlayer;

    @Getter @Setter
    private List<T> projectiles;

    private boolean cancelled;


    public SkyblockProjectileLaunchEvent(SkyblockPlayer skyblockPlayer, List<T> projectiles) {
        this.skyblockPlayer = skyblockPlayer;
        this.projectiles = projectiles;
    }

    public SkyblockProjectileLaunchEvent(SkyblockPlayer skyblockPlayer) {
        this.skyblockPlayer = skyblockPlayer;
        this.projectiles = new ArrayList<>();
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
