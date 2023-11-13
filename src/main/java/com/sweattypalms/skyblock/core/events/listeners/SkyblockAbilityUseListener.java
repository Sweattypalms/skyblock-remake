package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.core.enchants.EnchantManager;
import com.sweattypalms.skyblock.core.events.def.SkyblockAbilityUseEvent;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IUsageCost;
import com.sweattypalms.skyblock.core.player.sub.stats.StatsManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkyblockAbilityUseListener implements Listener {

    @EventHandler
    public void onUse(SkyblockAbilityUseEvent event) {
        EnchantManager.run(event.getSkyblockPlayer().getPlayer(), event);

        if (!(event.getAbility() instanceof IUsageCost costAbility)) return;

        costAbility.getCost(event.getSkyblockPlayer()).forEach((stat, value) -> {
            value = value * event.getCostMultiplier();

            if (event.getSkyblockPlayer().getStatsManager().getLiveStats().get(stat) < value) {
                event.setCancelled(true);
                String message = "$cYou don't have enough " + stat.getName().toLowerCase() + " to use this ability!";
                message = PlaceholderFormatter.format(message);
                message = message.replace("intelligence", "mana");
                event.getSkyblockPlayer().getPlayer().sendMessage(message);
                return;
            }
            StatsManager statsManager = event.getSkyblockPlayer().getStatsManager();
            statsManager.setLiveStat(stat, statsManager.getLiveStats().get(stat) - value);
        });
    }
}
