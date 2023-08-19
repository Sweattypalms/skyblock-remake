package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.core.events.SkyblockAbilityUseEvent;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IUsageCost;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkyblockAbilityUseListener implements Listener {

    @EventHandler
    public void onUse(SkyblockAbilityUseEvent event) {
        if(!(event.getAbility() instanceof IUsageCost costAbility)) return;
        costAbility.getCost().forEach((stat, value) -> {
            if(event.getSkyblockPlayer().getLiveStats().get(stat) < value) {
                event.setCancelled(true);
                String message = "$cYou don't have enough " + stat.getName().toLowerCase() + " to use this ability!";
                message = PlaceholderFormatter.format(message);
                // replace intelligence with mana cause why not
                event.getSkyblockPlayer().getPlayer().sendMessage(message);
                return;
            }
            event.getSkyblockPlayer().getLiveStats().put(stat, event.getSkyblockPlayer().getLiveStats().get(stat) - value);
        });
    }
}
