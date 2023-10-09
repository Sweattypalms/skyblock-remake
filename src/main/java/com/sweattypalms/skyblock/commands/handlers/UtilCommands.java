package com.sweattypalms.skyblock.commands.handlers;

import com.sweattypalms.skyblock.commands.Command;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UtilCommands {

    @Command(name = "gms", description = "Change gamemode to survival", op = true)
    public void gmsCommand(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.sendMessage(ChatColor.RED + "Your gamemode has been updated to survival!");
    }

    @Command(name = "gmc", description = "Change gamemode to creative", op = true)
    public void gmcCommand(Player player) {

        player.setGameMode(GameMode.CREATIVE);
        player.sendMessage(ChatColor.RED + "Your gamemode has been updated to creative!");
    }

    @Command(name = "gmss", description = "Change gamemode to spectator", op = true)
    public void gmssCommand(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(ChatColor.RED + "Your gamemode has been updated to spectator!");


    }


    @Command(name = "fix_inventory", description = "Fix inventory")
    public void fixInventoryCommand(Player player) {
        player.updateInventory();
        player.sendMessage(ChatColor.RED + "Your inventory has been updated!");
    }
}
