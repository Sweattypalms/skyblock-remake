package com.sweattypalms.skyblock.Listeners;

import com.sweattypalms.skyblock.gui.GUIType;
import com.sweattypalms.skyblock.scoreboard.ScoreBoards;
import com.sweattypalms.skyblock.utils.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;


public class PlayerListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
      Player player = event.getPlayer();
     player.getInventory().setItem(8 , ItemUtil.createNamedItemStack(Material.NETHER_STAR ,
             ChatColor.GREEN + "Skyblock Menu"));
        ScoreBoards.setScoreboard(player);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getAction().name().contains("RIGHT_CLICK")) {
            ItemStack heldItem = event.getItem();
            if (heldItem != null && heldItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Skyblock Menu")) {
               event.setCancelled(true);
               GUIType.SkyBlock_Menu.getGUI().open(event.getPlayer());
            }
        }
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player player = e.getPlayer();
        if (Objects.requireNonNull(e.getItemDrop().getItemStack().getItemMeta()).getDisplayName().equals(ChatColor.GREEN + "Skyblock Menu")){
            e.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You cannot drop this item");
        }
    }

}
