package com.sweattypalms.skyblock.commands.handlers;

import com.sweattypalms.skyblock.commands.Command;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

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
}
