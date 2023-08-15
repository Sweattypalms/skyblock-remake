package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.core.events.SkyblockPlayerDamageEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkyblockDamageListener implements Listener {
    @EventHandler
    public void onSkyblockPlayerDamageEntity(SkyblockPlayerDamageEntityEvent event) {
        event.setDamage(0);
        System.out.printf(
                "Player %s damaged %s for %s damage%n",
                event.getPlayer().getName(),
                event.getEntity().getType(),
                event.getDamage()
                );
    }
}
