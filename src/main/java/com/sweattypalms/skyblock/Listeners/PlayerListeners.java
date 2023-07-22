package com.sweattypalms.skyblock.Listeners;

import com.sweattypalms.skyblock.utils.SkyUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
      Player player = event.getPlayer();
     player.getInventory().setItem(8 , SkyUtils.createNamedItemStack(Material.NETHER_STAR ,
             ChatColor.YELLOW + "Skyblock Menu"));
    }
}
