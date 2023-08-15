package com.sweattypalms.skyblock.core.events;

import com.sweattypalms.skyblock.core.stats.SkyblockPlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SkyblockPlayerDamageEntityEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;


    @Getter
    private final LivingEntity entity;
    @Getter
    private final SkyblockPlayer skyblockPlayer;
    @Getter
    private final Player player;
    @Getter
    private final DamageType damageType;


    @Getter
    @Setter
    private double multiplier = 1;
    @Getter
    @Setter
    private double damage;

    /* Ability damage  */
    @Getter @Setter
    private double abilityScaling;
    @Getter @Setter
    private double specialAbilityDamage;
    /* Ability damage  */

    public SkyblockPlayerDamageEntityEvent(LivingEntity entity, Player player, DamageType damageType) {
        this.entity = entity;
        this.player = player;
        this.skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        this.damageType = damageType;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }


    public void addMultiplier(int a) {
        multiplier *= a;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public enum DamageType {
        MELEE,
        ARROW,
        MAGIC,
        ABILITY
    }
}
