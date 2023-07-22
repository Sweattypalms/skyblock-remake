package com.sweattypalms.skyblock.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
        if (event.getClickedInventory() == null) return;
        Player player = (Player) event.getWhoClicked();
        GUI gui = GUI.GUI_MAP.get(player.getUniqueId());
        if (gui == null) return;
        GUI.GUIItem item = gui.get(event.getSlot());
        if (item == null) return;
        item.run(event);
        if (!item.canPickup()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        GUI.GUI_MAP.remove(event.getPlayer().getUniqueId());
    }
}
