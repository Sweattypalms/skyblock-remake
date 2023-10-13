package com.sweattypalms.skyblock.core.events.def;

import lombok.Getter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

import javax.annotation.Nullable;

@Getter
public class SkyblockDeathEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final LivingEntity deadEntity;
    @Nullable
    private final LivingEntity damager;
    private final DeathCause cause;

    public SkyblockDeathEvent(LivingEntity damager, LivingEntity deadEntity) {
        this.damager = damager;
        this.deadEntity = deadEntity;
        this.cause = this.damager instanceof Player ? DeathCause.PLAYER : DeathCause.ENTITY;
    }

    public SkyblockDeathEvent(LivingEntity deadEntity, DeathCause cause) {
        this.damager = null;
        this.deadEntity = deadEntity;
        this.cause = cause;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public enum DeathCause {
        PLAYER,
        ENTITY,
        FALL,
        VOID,
        OTHER;

        DeathCause() {
        }

        public static DeathCause getCause(EntityDamageEvent.DamageCause cause) {
            return switch (cause) {
                case ENTITY_ATTACK, ENTITY_SWEEP_ATTACK, PROJECTILE -> ENTITY;
                case FALL -> FALL;
                case VOID -> VOID;
                default -> OTHER;
            };
        }
    }
}
