package com.sweattypalms.skyblock.core.events.def;

import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class SkyblockMobDamagePlayerEvent extends SkyblockPlayerEvent implements Cancellable {
    public static final HandlerList HANDLERS = new HandlerList();

    private final SkyblockPlayer skyblockPlayer;
    private final SkyblockMob skyblockMob;

    private double damage;

    private boolean cancelled = false;
    public SkyblockMobDamagePlayerEvent(SkyblockPlayer skyblockPlayer, SkyblockMob skyblockMob) {
        this.skyblockPlayer = skyblockPlayer;
        this.skyblockMob = skyblockMob;
    }

    public SkyblockMobDamagePlayerEvent(SkyblockPlayer skyblockPlayer, LivingEntity livingEntity) {
        this.skyblockPlayer = skyblockPlayer;
        this.skyblockMob = SkyblockMob.getSkyblockMob(livingEntity);
    }

    public SkyblockMobDamagePlayerEvent(Player player, LivingEntity livingEntity) {
        this.skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        this.skyblockMob = SkyblockMob.getSkyblockMob(livingEntity);
    }

    public SkyblockMob getSkyblockMob() {
        return skyblockMob;
    }

    @Override
    public SkyblockPlayer getSkyblockPlayer() {
        return skyblockPlayer;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }


    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }


    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
