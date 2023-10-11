package com.sweattypalms.skyblock.ui;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {
    @EventHandler
    public void onInventoryCLick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!(event.getClickedInventory().getHolder() instanceof BaseGUI gui)) return;
        event.setCancelled(true);

        ClickableItem item = gui.getItemAt(event.getSlot());

        if (item == null) return;

        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked());

        GUIItemClickEvent itemClickEvent = new GUIItemClickEvent(item, event.getCursor(), skyblockPlayer);
        Bukkit.getPluginManager().callEvent(itemClickEvent);
    }

    @EventHandler
    public void onGUIItemClick(GUIItemClickEvent event) {
        assert event.getClickedItem().getItem().getItemMeta() != null;
        event.getClickedItem().click(event.getSkyblockPlayer().getPlayer());
    }
}
