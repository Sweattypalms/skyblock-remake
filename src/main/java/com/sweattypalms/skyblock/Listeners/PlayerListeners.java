package com.sweattypalms.skyblock.Listeners;

import com.sweattypalms.skyblock.scoreboard.ScoreBoards;
import com.sweattypalms.skyblock.utils.ItemUtil;
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
     player.getInventory().setItem(8 , ItemUtil.createNamedItemStack(Material.NETHER_STAR ,
             ChatColor.GREEN + "Skyblock Menu"));
        ScoreBoards.setScoreboard(player);
    }
}
