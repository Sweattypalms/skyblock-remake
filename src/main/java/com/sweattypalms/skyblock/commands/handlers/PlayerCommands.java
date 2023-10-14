package com.sweattypalms.skyblock.commands.handlers;

import com.sweattypalms.skyblock.commands.Command;
import com.sweattypalms.skyblock.slayers.gui.SlayerSelectorGUI;
import com.sweattypalms.skyblock.ui.GUIRouter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerCommands {
    public static String BATPHONE_COMMAND = "?slayer_gui";
    @Command(name = "?slayer_gui", description = "Opens the slayer menu")
    public void slayerCommand(Player player, String[] args) {
        SlayerSelectorGUI slayerSelectorGUI = new SlayerSelectorGUI();
        GUIRouter.openGUI(player, slayerSelectorGUI);
    }


    @Command(name = "hub", description = "Hub command")
    public void hubCommand(Player player){
        World world = Bukkit.getWorld("skyblock_hub");
        assert world != null;
        player.teleport(world.getSpawnLocation());
    }
}
