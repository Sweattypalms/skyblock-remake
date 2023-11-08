package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.core.events.def.SkyblockProjectilePrelaunchEvent;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.CooldownManager;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static com.sweattypalms.skyblock.core.helpers.DamageCalculator.getShortbowCooldown;

public class WatchYoJett implements Listener {

    @EventHandler
    public void onTryProjectile(SkyblockProjectilePrelaunchEvent event) {
        CooldownManager cooldownManager = event.getSkyblockPlayer().getCooldownManager();

        boolean can = cooldownManager.can("shortbow", getShortbowCooldown(event.getSkyblockPlayer()));

        if(!can){
            event.getSkyblockPlayer().sendMessage("$cYou can't use that yet!");
            event.setCancelled(true);
            return;
        }

        cooldownManager.setCooldown("shortbow");
    }


}
