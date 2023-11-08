package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.core.player.PlayerManager;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;

import java.util.HashMap;

public class CooldownManager extends PlayerManager {

    /**
     * This is used to get last use time of abilities
     */
    private final HashMap<String, Long> cooldowns = new HashMap<>();

    public CooldownManager(SkyblockPlayer skyblockPlayer) {
        super(skyblockPlayer);
    }

    public void setCooldown(String ability) {
        this.cooldowns.put(ability, System.currentTimeMillis());
    }

    public long getCooldown(String ability) {
        return this.cooldowns.getOrDefault(ability, 0L);
    }

    public boolean hasCooldown(String ability) {
        return this.cooldowns.containsKey(ability);
    }

    /**
     * Checks if the ability is on cooldown
     * @param ability The ability to check (e.g. "shortbow")
     * @param cooldown The cooldown in milliseconds
     * @return true if the ability is not on cooldown or the cooldown has passed
     */
    public boolean can(String ability, long cooldown) {
        return !hasCooldown(ability) || System.currentTimeMillis() - getCooldown(ability) >= cooldown;
    }
}
