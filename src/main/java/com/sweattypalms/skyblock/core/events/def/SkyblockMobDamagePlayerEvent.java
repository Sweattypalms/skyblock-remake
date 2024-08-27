package com.sweattypalms.skyblock.core.events.def;

import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.mobs.builder.MobAttributes;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class SkyblockMobDamagePlayerEvent extends SkyblockPlayerEvent implements Cancellable {
    public static final HandlerList HANDLERS = new HandlerList();

    @Getter
    private final SkyblockPlayer skyblockPlayer;
    @Getter
    private final SkyblockMob skyblockMob;

    @Getter
    @Setter
    private double damage;
    @Getter
    private double additiveMultiplier = 0;
    @Getter
    private double multiplicativeMultiplier = 1;

    @Getter
    @Setter
    private boolean preCalc = true;

    @Getter
    @Setter
    private ItemStack defenseItem;
    @Getter
    @Setter
    private Ability ability;

    private boolean cancelled = false;

    public SkyblockMobDamagePlayerEvent(Player player, LivingEntity livingEntity) {
        this.skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        this.skyblockMob = SkyblockMob.getSkyblockMob(livingEntity);
        this.damage = skyblockMob.getAttribute(MobAttributes.DAMAGE);
    }

    /**
     * @param amount Amount in percent. 80% etc
     */
    public void addAdditiveMultiplierPercent(double amount) {
        additiveMultiplier += amount;
    }

    /**
     * @param percent Amount in percent. 80% etc
     */
    public void addMultiplicativeMultiplierPercent(double percent) {
        this.multiplicativeMultiplier *= (1 + (percent / 100));
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