package com.sweattypalms.skyblock.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UtilCommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player player)){
            commandSender.sendMessage("Only players can use this command!");
            return true;
        }
        switch (command.getName()){
            case "gms":
                player.setGameMode(GameMode.SURVIVAL);
                break;
            case "gmc":
                player.setGameMode(GameMode.CREATIVE);
                break;
            case "gmss":
                player.setGameMode(GameMode.SPECTATOR);
                break;
            default:
                player.sendMessage("Unknown command!");
                break;
        }
        return true;
    }
}
