package com.sweattypalms.skyblock.core.listeners;

import com.sweattypalms.skyblock.core.stats.SkyblockPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        SkyblockPlayer skyblockPlayer = new SkyblockPlayer(event.getPlayer());
    }
}
