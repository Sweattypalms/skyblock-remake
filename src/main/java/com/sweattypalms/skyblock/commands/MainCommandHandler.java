package com.sweattypalms.skyblock.commands;

import com.sweattypalms.skyblock.gui.GUIType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Objects;

public class MainCommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(@Nullable CommandSender commandSender,
                             @Nullable Command command,
                             @Nullable String s,
                             @Nullable String[] strings) {

        if(commandSender == null || command == null || s == null || strings == null) return false;
        if(!(commandSender instanceof Player player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }
        Objects.requireNonNull(GUIType.Example_Gui.getGUI()).open(player);
        player.sendMessage(ChatColor.GREEN + "Hello, " + player.getName() + "!");
        return true;
    }
}
