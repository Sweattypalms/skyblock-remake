package com.sweattypalms.skyblock.core.events;

import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class SkyblockPlayerDamageEntityEvent extends SkyblockPlayerEvent implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    @Getter
    private final SkyblockMob skyblockMob;
    @Getter
    private final LivingEntity entity;
    @Getter
    private final SkyblockPlayer skyblockPlayer;
    @Getter
    private final Player player;
    @Getter
    private final DamageType damageType;
    private boolean isCancelled;
    @Getter private double additiveMultiplier = 0;
    @Getter private double multiplicativeMultiplier = 1;

    @Getter @Setter private boolean isCrit = false;
    @Getter @Setter private boolean isForcedCrit = false;

    /**
     *
     * @param amount Amount in percent. 80% etc
     */
    public void addAdditiveMultiplierPercent(double amount) {
        additiveMultiplier += amount;
    }

    /**
     *
     * @param percent Amount in percent. 80% etc
     */
    public void addMultiplicativeMultiplierPercent(double percent) {
        this.multiplicativeMultiplier *= (1 + (percent / 100));
    }

    @Getter
    @Setter
    private double damage;

    /* Ability damage  */
    @Getter @Setter
    private ItemStack abilityItem;
    @Getter @Setter
    private Ability ability;
    /* Ability damage  */

    public SkyblockPlayerDamageEntityEvent(LivingEntity entity, Player player, DamageType damageType) {
        if (((CraftEntity) entity).getHandle() instanceof ISkyblockMob skyblockMob){
            this.skyblockMob = skyblockMob.getSkyblockMob();
        } else{
            this.skyblockMob = null;
        }
        this.entity = entity;
        this.skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        this.player = player;
        this.damageType = damageType;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
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
